package mainPackage.mainClasses.searchPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;

public class FinalStateTry {
	
	private FinalStateTry parent;
	private int roundsWon,roundsPlayed;
	private int whoPlays,wonFlag,startPlayer,pointsWon;
	private Card targetCard;
	private ArrayList<FinalStateTry> children = null;
	private ArrayList<Card> removeCards;
	private int mod;
	private int depth;
	
	public int getPointsWon(){
		return pointsWon;
	}
	
	public Card getTargetCard(){
		return targetCard;
	}
	
	public int getRoundsPlayed(){
		return roundsPlayed;
	}
	
	public int getRoundsWon(){
		return roundsWon;
	}
	
	public FinalStateTry(int player,Card tc,ArrayList<Card> removeUs,FinalStateTry parent,int startPlayer,int modd,int depth){
		roundsWon = 0;	roundsPlayed = 0; pointsWon = 0;	mod = modd; this.depth = depth;
		this.parent = parent;
		this.startPlayer = startPlayer;
		if(player>4)
			whoPlays = 1;
		else
			whoPlays = player;
		
		targetCard = tc;
		removeCards = removeUs;
	}
	
	public void makeNextStates(){
		children = new ArrayList<FinalStateTry>();
		ArrayList<Card> cardsToKill = new ArrayList<Card>();
		for(Card c : removeCards){
			cardsToKill.add(c);
		}
		for(Card c : MainState.deadCards.values()){
			cardsToKill.add(c);
		}
		for(Card c : MainState.droppedCards.values()){
			cardsToKill.add(c);
		}
		FinalSimPlayer sim = new FinalSimPlayer(cardsToKill);
		
		ArrayList<Card> legalCards = new ArrayList<Card>();
		legalCards = sim.getLegalCards();
		
		int length = 0;
		if(legalCards.size() >= Flags.BEAM_MAX){
			length = Flags.BEAM_MAX;
		}else{
			length = legalCards.size();
		}
		
		for(int i=0;	i<length;	i++){
			children.add(new FinalStateTry(whoPlays+1, legalCards.get(i), removeCards, this,startPlayer,mod,depth+1));
		}
	}
	
	public void simulate(){
		MainState.droppedCards.put(whoPlays, targetCard);
		if(MainState.droppedCards.size() == 4){
			System.out.println(MainState.droppedCards);
			calculateWin();
			propagateBack(wonFlag,pointsWon);
			MainState.droppedCards.remove(whoPlays);
		}else{
			makeNextStates();
			for(int i=0;	i<children.size();	i++){
				children.get(i).simulate();
			}
			MainState.droppedCards.remove(whoPlays);
		}
	}
	
	public void propagateBack(int won,int points){
		if(parent == null){
			roundsPlayed++;
			roundsWon += won;
			pointsWon += points;
		}else{
			parent.propagateBack(won,points);
		}
	}
	
	private void calculateWin(){
		Card winCard = MainState.droppedCards.get(1);
		int winIndex = 1;
		Iterator iter = MainState.droppedCards.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Entry)iter.next();
			Card tempCard = (Card) entry.getValue();
			int idx = (int) entry.getKey();
			
			if(tempCard.getCardSuit() == winCard.getCardSuit()){
				if(tempCard.getCardValue() > winCard.getCardValue()){
					winCard = tempCard;
					winIndex = idx;
				}
			}else if(tempCard.getCardSuit() == AppCore.adut && winCard.getCardSuit() != AppCore.adut){
				winCard = tempCard;
				winIndex = idx;
			}
		}
		
		if(startPlayer == Flags.COMP_TOP_ON_PLAY){
			if(winIndex == Flags.COMP_TOP_ON_PLAY || winIndex == Flags.HUMAN_ON_PLAY){
				for(Card c : MainState.droppedCards.values()){
					if(c.getCardValue() > 0){
						pointsWon += c.getCardValue();
					}
				}
				wonFlag = 1;
			}else{
				wonFlag = 0;
			}
		}else{
			if(winIndex == Flags.COMP_LEFT_ON_PLAY || winIndex == Flags.COMP_RIGHT_ON_PLAY){
				for(Card c : MainState.droppedCards.values()){
					if(c.getCardValue() > 0){
						pointsWon += c.getCardValue();
					}
				}
				wonFlag = 1;
			}else{
				wonFlag = 0;
			}
		}
	}
}
