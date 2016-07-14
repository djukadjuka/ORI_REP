package mainPackage.mainClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import mainPackage.mainClasses.gameStatePackage.MainState;
import mainPackage.mainClasses.playerPackage.BabicPlayer;
import mainPackage.mainClasses.playerPackage.DjukaPlayer;
import mainPackage.mainClasses.playerPackage.DusicPlayer;
import mainPackage.mainClasses.playerPackage.HumanPlayer;

public class AppCore {
	public static int adut = 0; // 1 - srce, 2 - tikva, 3 - list, 4 - zir
	public static int zvaoAduta = 0;	//1 human - 2 right - 3 top - 4 left
	private static AppCore instance;
	
	private static ArrayList<Card> cards;
	private int nextToPlay = 1; // human uvek igra prvi
	private int firstToPlay = 1;
	private int lastToPlay = 4;
	private HumanPlayer hm = new HumanPlayer();
	private BabicPlayer babic = new BabicPlayer();
	private DusicPlayer dusic = new DusicPlayer();
	private DjukaPlayer djuka = new DjukaPlayer();
	
	private int colorDown;
	
	//TEST SCORE
	public static int fullScore = 0;
	public static int cicrcleScore = 0;
	public static int team1Score = 0;
	public static int team2Score = 0;
	
	public static int fullTeam1Score = 0;
	public static int fullTeam2Score = 0;
	
	public void runThroughCards(){
		hm.runThrough();
		babic.runThrough();
		dusic.runThrough();
		djuka.runThrough();
	}
	public void PRVIIGRAC(){
		Card maxCard = MainState.droppedCards.get(firstToPlay);
		Card maxAdutCard = null;
		boolean adutDown = false;
		
		cicrcleScore = 0;
		
		//SAMO SRACUNAJ REZULTAT KRUGA
		for(Card card : MainState.droppedCards.values()){
			if(card.getCardValue() < 0){
				continue;
			}
			cicrcleScore += card.getCardValue();
		}
		
		//PRONADJI ADUTA DAL JE BILO I POSTAVI GA NA MAX ADUT
		for(Card card : MainState.droppedCards.values()){
			if(card.getCardSuit() == adut){
				maxAdutCard = card;
				adutDown = true;
				break;
			}
		}
		
		//AKO IMA ADUTA
		if(adutDown){
			//NADJI NAJJACEG ADUTA OSTALO TE NE INTERESUJE
			for(Card c : MainState.droppedCards.values()){
				if(c.getCardSuit() == adut && c.getCardValue() > maxAdutCard.getCardValue()){
					maxAdutCard = c;
				}
			}
			
			//NADJI KLJUUC TE KARTE I TO POSTAVI KAO PRVI IGRAC
			Iterator iter = MainState.droppedCards.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry par = (Entry) iter.next();
				Card karta = (Card) par.getValue();
				int kljuc = (int) par.getKey();
				if(karta.getCardValue() == maxAdutCard.getCardValue() && karta.getCardSuit() == maxAdutCard.getCardSuit()){
					firstToPlay = kljuc;
					nextToPlay = kljuc;
					if(firstToPlay - 1 == 0){
						lastToPlay = 4;
					}else{
						lastToPlay = firstToPlay - 1;
					}
				}
			}
		}else{
			//AKO NBEMA ADUTA NADJI NAJJACU KARTU
			for(Card c : MainState.droppedCards.values()){
				if(c.getCardValue() > maxCard.getCardValue() && c.getCardSuit() == maxCard.getCardSuit()){
					maxCard = c;
				}
			}
			//UZMI JKLJUC TE KARTE I POSTAVI JE ZA PRVOG KOJI IGRA
			Iterator iter = MainState.droppedCards.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry par = (Entry) iter.next();
				Card karta = (Card) par.getValue();
				int kljuc = (int) par.getKey();
				if(karta.getCardValue() == maxCard.getCardValue() && karta.getCardSuit() == maxCard.getCardSuit()){
					firstToPlay = kljuc;
					nextToPlay = kljuc;
					if(firstToPlay - 1 == 0){
						lastToPlay = 4;
					}else{
						lastToPlay = firstToPlay - 1;
					}
				}
			}
		}
		if(firstToPlay == Flags.HUMAN_ON_PLAY || firstToPlay == Flags.COMP_TOP_ON_PLAY){
			team1Score += cicrcleScore;
		}else{
			team2Score += cicrcleScore;
		}
	}
	public int getColordDown(){
		return colorDown;
	}
	public void setColorDown(int col){
		colorDown = col;
	}
	private AppCore(){
		init();
	}
	private void init(){
		cards = new ArrayList<Card>();
	}
	public static AppCore getInstance(){
		if(instance == null){
			instance = new AppCore();
		}
		return instance;
	}
	public void shuffleCards(){
		long seed = System.nanoTime();	// za random seed
		cards.clear();					// da se pobiju sve karte
		/** lista mora biti prazna pre ovoga inace sokovi*/
		try {
			initCards();				// napuni listu sa svim kartama
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
		Collections.shuffle(cards, new Random(seed));	//promesaj sve karte
		zvaoAduta = 0;
		team1Score = 0;
		team2Score = 0;
		fullScore = 162;
	}
	public void initCards() throws SlickException{
		cards.add(new Card("Srce 7",-3,Flags.SRCE,7,new Image("Karte/SRCE_7.jpg"),new Image("Karte/SRCE_7.jpg")));
		cards.add(new Card("Srce 8",-2,Flags.SRCE,8,new Image("Karte/SRCE_8.jpg"),new Image("Karte/SRCE_8.jpg")));
		cards.add(new Card("Srce 9",-1,Flags.SRCE,9,new Image("Karte/SRCE_9.jpg"),new Image("Karte/SRCE_9.jpg")));
		cards.add(new Card("Srce 10",10,Flags.SRCE,10,new Image("Karte/SRCE_10.jpg"),new Image("Karte/SRCE_10.jpg")));
		cards.add(new Card("Srce J",2,Flags.SRCE,12,new Image("Karte/SRCE_J.jpg"),new Image("Karte/SRCE_J.jpg")));
		cards.add(new Card("Srce Q",3,Flags.SRCE,13,new Image("Karte/SRCE_Q.jpg"),new Image("Karte/SRCE_Q.jpg")));
		cards.add(new Card("Srce K",4,Flags.SRCE,14,new Image("Karte/SRCE_K.jpg"),new Image("Karte/SRCE_K.jpg")));
		cards.add(new Card("Srce A",11,Flags.SRCE,15,new Image("Karte/SRCE_A.jpg"),new Image("Karte/SRCE_A.jpg")));
		
		cards.add(new Card("List 7",-3,Flags.LIST,7,new Image("Karte/LIST_7.jpg"),new Image("Karte/LIST_7.jpg")));
		cards.add(new Card("List 8",-2,Flags.LIST,8,new Image("Karte/LIST_8.jpg"),new Image("Karte/LIST_8.jpg")));
		cards.add(new Card("List 9",-1,Flags.LIST,9,new Image("Karte/LIST_9.jpg"),new Image("Karte/LIST_9.jpg")));
		cards.add(new Card("List 10",10,Flags.LIST,10,new Image("Karte/LIST_10.jpg"),new Image("Karte/LIST_10.jpg")));
		cards.add(new Card("List J",2,Flags.LIST,12,new Image("Karte/LIST_J.jpg"),new Image("Karte/LIST_J.jpg")));
		cards.add(new Card("List Q",3,Flags.LIST,13,new Image("Karte/LIST_Q.jpg"),new Image("Karte/LIST_Q.jpg")));
		cards.add(new Card("List K",4,Flags.LIST,14,new Image("Karte/LIST_K.jpg"),new Image("Karte/LIST_K.jpg")));
		cards.add(new Card("List A",11,Flags.LIST,15,new Image("Karte/LIST_A.jpg"),new Image("Karte/LIST_A.jpg")));
		
		cards.add(new Card("Zir 7",-3,Flags.ZIR,7,new Image("Karte/ZIR_7.jpg"),new Image("Karte/ZIR_7.jpg")));
		cards.add(new Card("Zir 8",-2,Flags.ZIR,8,new Image("Karte/ZIR_8.jpg"),new Image("Karte/ZIR_8.jpg")));
		cards.add(new Card("Zir 9",-1,Flags.ZIR,9,new Image("Karte/ZIR_9.jpg"),new Image("Karte/ZIR_9.jpg")));
		cards.add(new Card("Zir 10",10,Flags.ZIR,10,new Image("Karte/ZIR_10.jpg"),new Image("Karte/ZIR_10.jpg")));
		cards.add(new Card("Zir J",2,Flags.ZIR,12,new Image("Karte/ZIR_J.jpg"),new Image("Karte/ZIR_J.jpg")));
		cards.add(new Card("Zir Q",3,Flags.ZIR,13,new Image("Karte/ZIR_Q.jpg"),new Image("Karte/ZIR_Q.jpg")));
		cards.add(new Card("Zir K",4,Flags.ZIR,14,new Image("Karte/ZIR_K.jpg"),new Image("Karte/ZIR_K.jpg")));
		cards.add(new Card("Zir A",11,Flags.ZIR,15,new Image("Karte/ZIR_A.jpg"),new Image("Karte/ZIR_A.jpg")));
		
		cards.add(new Card("Tikva 7",-3,Flags.TIKVA,7,new Image("Karte/TIKVA_7.jpg"),new Image("Karte/TIKVA_7.jpg")));
		cards.add(new Card("Tikva 8",-2,Flags.TIKVA,8,new Image("Karte/TIKVA_8.jpg"),new Image("Karte/TIKVA_8.jpg")));
		cards.add(new Card("Tikva 9",-1,Flags.TIKVA,9,new Image("Karte/TIKVA_9.jpg"),new Image("Karte/TIKVA_9.jpg")));
		cards.add(new Card("Tikva 10",10,Flags.TIKVA,10,new Image("Karte/TIKVA_10.jpg"),new Image("Karte/TIKVA_10.jpg")));
		cards.add(new Card("Tikva J",2,Flags.TIKVA,12,new Image("Karte/TIKVA_J.jpg"),new Image("Karte/TIKVA_J.jpg")));
		cards.add(new Card("Tikva Q",3,Flags.TIKVA,13,new Image("Karte/TIKVA_Q.jpg"),new Image("Karte/TIKVA_Q.jpg")));
		cards.add(new Card("Tikva K",4,Flags.TIKVA,14,new Image("Karte/TIKVA_K.jpg"),new Image("Karte/TIKVA_K.jpg")));
		cards.add(new Card("Tikva A",11,Flags.TIKVA,15,new Image("Karte/TIKVA_A.jpg"),new Image("Karte/TIKVA_A.jpg")));
	}
	public static void setAdut(int a){
		
		if(a == adut)
			return;
		
		for(Card karta : cards){ // da se vetka i unter starog aduta vrate na 0 i 2
			if(adut == karta.getCardSuit()){
				if(karta.getCardNumber() == 9){
					karta.setCardValue(0);
				}else if(karta.getCardNumber() == 12){
					karta.setCardValue(2);
				}
			}
		}
		
		adut = a;
		
		for(Card karta : cards){ // da se vrednosti novog aduta postave
			
			if(adut == karta.getCardSuit()){
				if(karta.getCardNumber() == 9){
					karta.setCardValue(14);
				}else if(karta.getCardNumber() == 12){
					karta.setCardValue(20);
				}
			}
		}
	}
	
	public void startGame() throws InterruptedException{
		while(true){
			
			shuffleCards();
			
			Thread.sleep(1000);
			for(int i=0; i < 24; i++){
				if(i%4 == 0){
					hm.dealCardToPlayer(cards.get(i));
				} else if(i%4 == 1){
					babic.dealCardToPlayer(cards.get(i));
				} else if(i%4 == 2){
					dusic.dealCardToPlayer(cards.get(i));
				} else{
					djuka.dealCardToPlayer(cards.get(i));
				}
			}
			
			Thread.sleep(1000);
			
			for(int i=24; i < 32; i++){
				if(i%4 == 0){
					hm.dealCardToPlayer(cards.get(i));
				} else if(i%4 == 1){
					babic.dealCardToPlayer(cards.get(i));
				} else if(i%4 == 2){
					dusic.dealCardToPlayer(cards.get(i));
				} else{
					djuka.dealCardToPlayer( cards.get(i));
				}
			}
			
			Thread.sleep(1000);
			declarations();
			
			for(int k=0; k < 8; k++) {
				for(int i=0; i < 4; i++){
					
					switch (nextToPlay) {
					case 1: // human
						Flags.HUMAN_TO_DROP_CARD = true;
						nextToPlay = 2;
						break;
					case 2:
						Flags.PLAYER1_TO_DROP_CARD = true;
						//p1.playCard();
						nextToPlay = 3;
						break;
					case 3:
						Flags.PLAYER1_TO_DROP_CARD = true;
						//p2.playCard();
						nextToPlay = 4;
						break;
					case 4:
						Flags.PLAYER3_TO_DROP_CARD = true;
						//p3.playCard();
						nextToPlay = 1;
						break;
					default:
						break;
					}
					
				}
			}
			
//			hm.clearCards();
//			p1.clearCards();
//			p2.clearCards();
//			p3.clearCards();
			setAdut(Flags.SRCE);
		}
	}
	
	public HumanPlayer getHumanPlayer(){
		return hm;
	}
	
	public BabicPlayer getBabicPlayer(){
		return babic;
	}
	
	public DusicPlayer getDusicPlayer(){
		return dusic;
	}
	
	public DjukaPlayer getDjukaPlayer(){
		return djuka;
	}
	
	public void declarations(){
	}
	
	public int getNextToPlay() {
		return nextToPlay;
	}
	
	public void setNextToPlay(int nextToPlay) {
		this.nextToPlay = nextToPlay;
	}
	
	public int getFirstToPlay() {
		return firstToPlay;
	}
	
	public void setFirstToPlay(int firstToPlay) {
		this.firstToPlay = firstToPlay;
	}
	
	public int getLastToPlay() {
		return lastToPlay;
	}
	
	public void setLastToPlay(int lastToPlay) {
		this.lastToPlay = lastToPlay;
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
}
