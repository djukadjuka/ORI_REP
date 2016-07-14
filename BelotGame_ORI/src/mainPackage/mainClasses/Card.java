package mainPackage.mainClasses;

import org.newdawn.slick.Image;

public class Card {
	private int cardValue;
	private int cardSuit;
	private int cardNumber;
	private int heuristicValue;
	private String cardName;
	private Image cardImage;
	private Image rotatedCardImage;
	private boolean isInHand = true;
	private boolean playable = false;
	
	public Card(String cardName,int cardValue,int cardSuit,int cardNumber,Image cardImage,Image rotated){
		heuristicValue = 0;
		this.cardName = cardName;
		this.cardValue = cardValue;
		this.cardSuit = cardSuit;
		this.cardNumber = cardNumber;
		this.cardImage = cardImage;
		this.rotatedCardImage = rotated;
		this.rotatedCardImage.rotate(90);
		this.playable = false;
	}
	
	public Card(int cardValue,int cardSuit,int cardNumber,Image cardImage,String cardName){
		this.cardValue = cardValue;
		this.cardSuit = cardSuit;
		this.cardNumber = cardNumber;
		this.cardImage = cardImage;
		this.cardName = cardName;
	}
	
	public boolean isPlayable() {
		return playable;
	}

	public void setPlayable(boolean playable) {
		this.playable = playable;
	}

	public boolean isInHand() {
		return isInHand;
	}

	public void setInHand(boolean isInHand) {
		this.isInHand = isInHand;
	}

	public Image getRotatedCardImage(){
		return rotatedCardImage;
	}
	public int getCardValue(){
		return cardValue;
	}
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	public int getCardSuit() {
		return cardSuit;
	}
	public void setCardSuit(int cardSuit) {
		this.cardSuit = cardSuit;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getHeuristicValue() {
		return heuristicValue;
	}
	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	@Override
	public String toString(){
		return cardName;
	}
	public boolean isAdut(int suit){
		return cardSuit == suit;
	}
	public Image getCardImage(){
		return cardImage;
	}
}
