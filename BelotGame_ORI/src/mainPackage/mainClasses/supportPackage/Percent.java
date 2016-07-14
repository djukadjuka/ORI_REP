package mainPackage.mainClasses.supportPackage;

import mainPackage.mainClasses.Card;

public class Percent {
	private int cardIndex;
	private float percentage;
	private float numberOfPoints;
	private Card targetCard;
	
	public Percent(Card c) {
		targetCard = c;
	}
	
	public float getNumberOfPoints(){
		return numberOfPoints;
	}
	
	public void setNumberOfPoints(float numpt){
		numberOfPoints = numpt;
	}
	
	public int getCardIndex(){
		return cardIndex;
	}
	
	public void setCardIndex(int index){
		cardIndex = index;
	}
	
	public float getPercentage(){
		return percentage*100;
	}
	
	public void setPercentage(float x){
		percentage = x;
	}
	
	public Card getCard(){
		return targetCard;
	}
	
	public void setCard(Card card){
		targetCard = card;
	}
	
	public String toString(){
		return "Chance to win with card [ " + targetCard + " ] is : " + percentage*100 + " %" + "\n" +
			   "Points won with card [" + targetCard + "] : " + numberOfPoints + "";
	}
	
}