package mainPackage.mainClasses.searchPackage;

import java.util.ArrayList;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.gameStatePackage.MainState;
import mainPackage.mainClasses.playerPackage.Player;

public class FinalSimPlayer extends Player{

	public FinalSimPlayer(ArrayList<Card> removeTheseCards){
		for(Card c : AppCore.getInstance().getCards()){
			boolean found = false;
			for(int i=0;	i<removeTheseCards.size();	i++){
				if(removeTheseCards.get(i).getCardName().equals(c.getCardName())){
					found = true;
					break;
				}
			}
			if(!found){
				playerCards.add(c);
			}
		}
	}
	
	public void chooseAdut(){};public void playCard() {}
}
