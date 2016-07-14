package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;

public abstract class Player {

	public abstract void chooseAdut();
	public abstract void playCard();
	
	protected ArrayList<Card> playerCards = new ArrayList<Card>();
	protected int srceInd, tikvaInd, listInd, zirInd;
	protected Declaration declarations = new Declaration();
	
	public ArrayList<Card> getAllCards(){
		return playerCards;
	}
	
	public void runThrough(){
		for(int i=0;	i<8;	i++){
			if(playerCards.get(i).getCardSuit() == AppCore.adut){
				if(playerCards.get(i).getCardNumber() == 12){
					playerCards.get(i).setCardValue(20);
				}else if(playerCards.get(i).getCardNumber() == 9){
					playerCards.get(i).setCardValue(14);
				}
			}
		}
	}
	
	////// za dozvoljene karte koje moze da igra
	public boolean imaBoju(){
		for(Card card : playerCards){
			if(card.getCardSuit() == MainState.droppedCards.get(AppCore.getInstance().getFirstToPlay()).getCardSuit()){
				return true;
			}
		}
		return false;
	}
	
	public boolean imaAduta(){
		for(Card card : playerCards){
			if(card.getCardSuit() == AppCore.adut){
				return true;
			}
		}
		return false;
	}
	
	public boolean imaJacegAduta(){
		Card najjaciAdut = null;
		for(Card c : MainState.droppedCards.values()){
			if(c.getCardSuit() == AppCore.adut){
				if(najjaciAdut == null){
					najjaciAdut = c;
				}else if(najjaciAdut.getCardValue() < c.getCardValue()){
					najjaciAdut = c;
				}
			}
		}
		
		for(Card c : playerCards){
			if(c.getCardSuit() == AppCore.adut && c.getCardValue() > najjaciAdut.getCardValue())
				return true;
		}
		
		return false;
	}

	public ArrayList<Card> dajJaceAdute(){
		ArrayList<Card> ret = new ArrayList<Card>();
		Card najjaciAdut = null;
		for(Card c : MainState.droppedCards.values()){
			if(c.getCardSuit() == AppCore.adut){
				if(najjaciAdut == null){
					najjaciAdut = c;
				}else if(najjaciAdut.getCardValue() < c.getCardValue()){
					najjaciAdut = c;
				}
			}
		}
		for(Card c : playerCards){
			if(c.getCardSuit() == AppCore.adut && c.getCardValue() > najjaciAdut.getCardValue()){
				ret.add(c);
			}
		}	
		return ret;
	}
	
	public ArrayList<Card> dajSveAdute(){
		ArrayList<Card> ret = new ArrayList<Card>();
		for(Card c : playerCards){
			if(c.getCardSuit() == AppCore.adut){
				ret.add(c);
			}
		}
		return ret;
	}

	public boolean seceno(){
		for(Card card : MainState.droppedCards.values()){
			/*System.out.println(card);
			System.out.println(AppCore.adut);*/
			if(card.getCardSuit() == AppCore.adut){
				return true;
			}
		}		
		return false;
	}
	
	public ArrayList<Card> dajSveUBoji(int boja){
		ArrayList<Card> ret = new ArrayList<Card>();
		for(Card card : playerCards ){
			if(card.getCardSuit() == boja){
				ret.add(card);
			}
		}
		return ret;
	}
	
	public boolean imaJacuBoju(int boja){
		Card najjaciCard = null;
		for(Card c : MainState.droppedCards.values()){
			if(c.getCardSuit() == boja){
				if(najjaciCard == null){
					najjaciCard = c;
				}else if(najjaciCard.getCardValue() < c.getCardValue()){
					najjaciCard = c;
				}
			}
		}
		
		for(Card c : playerCards){
			if(c.getCardSuit() == boja && c.getCardValue() > najjaciCard.getCardValue())
				return true;
		}
		
		return false;
	}
	
	public ArrayList<Card> dajSveJaceUBoji(int boja){
		ArrayList<Card> ret = new ArrayList<Card>();
		Card najjacaUBojiNaPolju = null;
		for(Card c : MainState.droppedCards.values()){
			if(najjacaUBojiNaPolju == null){
				if(c.getCardSuit() == boja)
					najjacaUBojiNaPolju = c;
			}else if(najjacaUBojiNaPolju.getCardValue() < c.getCardValue() && c.getCardSuit() == boja){
				najjacaUBojiNaPolju = c;
			}
		}
		for(Card c : playerCards){
			if(c.getCardValue() > najjacaUBojiNaPolju.getCardValue() && c.getCardSuit() == boja){
				ret.add(c);
			}
		}
		return ret;
	}
	
	public ArrayList<Card> getLegalCards(){
		Card firstCard = MainState.droppedCards.get(AppCore.getInstance().getFirstToPlay());
		if(firstCard.getCardSuit() == AppCore.adut){			// adut
			if(imaAduta()){										//ima aduta
				if(imaJacegAduta()){							//ima jaceg aduta
					return dajJaceAdute();
				}else{											//nema jaceg aduta
					return dajSveAdute();
				}
			}else{												//nema aduta
				return playerCards;
			}
		}else{							//nije adut
			if(seceno()){			//seceno
				if(imaBoju()){									//ima boju
					return dajSveUBoji(firstCard.getCardSuit());
				}else{											//nema boju
					if(imaAduta()){								//ima aduta
						if(imaJacegAduta()){
							return dajJaceAdute();
						}else{
							return dajSveAdute();
						}
					}else{										//nema aduta
						return playerCards;
					}
				}
			}else{												//neseceno
				if(imaBoju()){
					if(imaJacuBoju(firstCard.getCardSuit())){
						return dajSveJaceUBoji(firstCard.getCardSuit());
					}else{
						return dajSveUBoji(firstCard.getCardSuit());
					}
				}else{
					if(imaAduta()){
						return dajSveAdute();
					}else{
						return playerCards;
					}
				}
			}
		}
	}
	
	public void dealCardToPlayer(Card card){
		if(playerCards.size() < 8)
			playerCards.add(card);
	}
	
	public Card getCardFromPlayer(Card card){
		for(int i = 0; i < playerCards.size(); i++){
			if(playerCards.get(i) == card){
				return playerCards.get(i);
			}
		}
		return null;
	}
	
	public Card getCardByIndex(int i){
		return playerCards.get(i);
	}
	
	public void dropCard(Card card){
		playerCards.remove(card);
	}
	
	public void dropCardIndex(int i){
		playerCards.remove(i);
	}
	
	public int getCardNumber(){
		return playerCards.size();
	}
	
	public void clearCards(){
		playerCards.clear();
	}
	
	//////////////////// deklaracije
	
	public void handleDeclarations() {

		if (declaration1001(Flags.SRCE)) {
			System.out.println("1001 u srcu");
			declarations.addDeclaration(1001, -1, Flags.SRCE);
		} else if (declaration1001(Flags.TIKVA)) {
			System.out.println("1001 u tikvi");
			declarations.addDeclaration(1001, -1, Flags.TIKVA);
		} else if (declaration1001(Flags.LIST)) {
			System.out.println("1001 u listu");
			declarations.addDeclaration(1001, -1, Flags.LIST);
		} else if (declaration1001(Flags.ZIR)) {
			System.out.println("1001 u ziru");
			declarations.addDeclaration(1001, -1, Flags.ZIR);
		}
		// ---------------------------
		if (declarationFourSame(10)) {
			declarations.addDeclaration(100, -1, 10);
		}
		if (declarationFourSame(13)) {
			declarations.addDeclaration(100, -1, 13);
		}
		if (declarationFourSame(14)) {
			declarations.addDeclaration(100, -1, 14);
		}
		if (declarationFourSame(15)) {
			declarations.addDeclaration(100, -1, 15);
		}
		if (declarationFourSame(9)) {
			declarations.addDeclaration(150, -1, -1);
		}
		if (declarationFourSame(12)) {
			declarations.addDeclaration(200, -1, -1);
		}
		// -----------------------------

		declaration20_50_100(srceInd, tikvaInd, Flags.SRCE);
		declaration20_50_100(tikvaInd, listInd, Flags.TIKVA);
		declaration20_50_100(listInd, zirInd, Flags.LIST);
		declaration20_50_100(zirInd, playerCards.size(), Flags.ZIR);

	}

	public boolean declaration1001(int suit) {
		for (int i = 0; i < playerCards.size(); i++) {

			if (playerCards.get(i).getCardSuit() != suit)
				return false;

		}
		return true;
	}

	public boolean declarationFourSame(int number) {
		int count = 0;
		for (int i = 0; i < playerCards.size(); i++) {
			if (playerCards.get(i).getCardNumber() == number)
				count++;
		}
		if (count == 4)
			return true;
		else
			return false;
	}

	public void declaration20_50_100(int startInd, int endInd, int suit) {

		ArrayList<String> dec = new ArrayList<>();
		ArrayList<Integer> to = new ArrayList<>();

		int count = 0, i;
		for (i = startInd + 1; i < endInd; i++) {
			int compare;
			if (playerCards.get(i).getCardNumber() == 12)
				compare = playerCards.get(i).getCardNumber() - 2;
			else
				compare = playerCards.get(i).getCardNumber() - 1;
			if (compare == playerCards.get(i - 1).getCardNumber()) {
				count++;
			} else {
				if (count == 2) {
					dec.add("20");
					to.add(playerCards.get(i - 1).getCardNumber());
					declarations.addDeclaration(20, playerCards.get(i - 1).getCardNumber(), suit);
				} else if (count == 3) {
					dec.add("50");
					to.add(playerCards.get(i - 1).getCardNumber());
					declarations.addDeclaration(50, playerCards.get(i - 1).getCardNumber(), suit);
				} else if (count == 4) {
					dec.add("100");
					to.add(playerCards.get(i - 1).getCardNumber());
					declarations.addDeclaration(100, playerCards.get(i - 1).getCardNumber(), suit);
				} else if (count == 5) {
					dec.add("100");
					to.add(playerCards.get(i - 1).getCardNumber());
					declarations.addDeclaration(100, playerCards.get(i - 1).getCardNumber(), suit);
				} else if (count == 6) {
					dec.add("100");
					to.add(playerCards.get(i - 1).getCardNumber());
					declarations.addDeclaration(100, playerCards.get(i - 1).getCardNumber(), suit);
				}
				count = 0;
			}
		}

		if (count == 2) {
			dec.add("20");
			to.add(playerCards.get(i - 1).getCardNumber());
			declarations.addDeclaration(20, playerCards.get(i - 1).getCardNumber(), suit);
		} else if (count == 3) {
			dec.add("50");
			to.add(playerCards.get(i - 1).getCardNumber());
			declarations.addDeclaration(50, playerCards.get(i - 1).getCardNumber(), suit);
		} else if (count == 4) {
			dec.add("100");
			to.add(playerCards.get(i - 1).getCardNumber());
			declarations.addDeclaration(100, playerCards.get(i - 1).getCardNumber(), suit);
		} else if (count == 5) {
			dec.add("100");
			to.add(playerCards.get(i - 1).getCardNumber());
			declarations.addDeclaration(100, playerCards.get(i - 1).getCardNumber(), suit);
		} else if (count == 6) {
			dec.add("100");
			to.add(playerCards.get(i - 1).getCardNumber());
			declarations.addDeclaration(100, playerCards.get(i - 1).getCardNumber(), suit);
		}

/*		for (int j = 0; j < dec.size(); j++) {
			System.out.println(dec.get(j) + " " + to.get(j));
		}
*/
	}

	public Declaration getDeclarations() {
		return declarations;
	}
	
	/////////////// sortiranje karata
	
	public void sortCards() {
		Collections.sort(playerCards, new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				return o1.getCardSuit() - o2.getCardSuit();
			}
		});

		srceInd = 0;
		tikvaInd = listInd = zirInd = -1;

		if (playerCards.get(0).getCardSuit() == Flags.TIKVA)
			tikvaInd = 0;
		else if ((playerCards.get(0).getCardSuit() == Flags.LIST)) {
			tikvaInd = 0;
			listInd = 0;
		} else if (playerCards.get(0).getCardSuit() == Flags.ZIR) {
			tikvaInd = 0;
			listInd = 0;
			zirInd = 0;
		}

		for (int i = 0; i < playerCards.size(); i++) {
			if ((playerCards.get(i).getCardSuit()) == Flags.TIKVA) {
				tikvaInd = i;
				if (i == playerCards.size() - 1)
					zirInd = playerCards.size();
				break;
			}
		}
		for (int i = 0; i < playerCards.size(); i++) {
			if ((playerCards.get(i).getCardSuit()) == Flags.LIST) {
				listInd = i;
				if (i == playerCards.size() - 1)
					zirInd = playerCards.size();
				break;
			}
		}
		for (int i = 0; i < playerCards.size(); i++) {
			if ((playerCards.get(i).getCardSuit()) == Flags.ZIR) {
				zirInd = i;
				break;
			}
		}

		if (tikvaInd == -1)
			tikvaInd = srceInd;
		if (listInd == -1)
			listInd = tikvaInd;
		if (zirInd == -1)
			zirInd = playerCards.size();

		Collections.sort(playerCards.subList(0, tikvaInd), new Compare());
		Collections.sort(playerCards.subList(tikvaInd, listInd), new Compare());
		Collections.sort(playerCards.subList(listInd, zirInd), new Compare());
		Collections.sort(playerCards.subList(zirInd, playerCards.size()), new Compare());
	}
	
	private class Compare implements Comparator<Card> {

		@Override
		public int compare(Card o1, Card o2) {
			// TODO Auto-generated method stub
			return o1.getCardNumber() - o2.getCardNumber();
		}

	}
	
}
