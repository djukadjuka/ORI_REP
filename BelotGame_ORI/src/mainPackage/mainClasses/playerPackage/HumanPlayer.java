package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;

public class HumanPlayer {
	private ArrayList<Card> playerCards;
	private int srceInd, tikvaInd, listInd, zirInd;
	private Declaration declarations = new Declaration();

	public HumanPlayer() {
		playerCards = new ArrayList<>();
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
	
	public void handleDeclarations() {

		if (declaration1001(Flags.SRCE)) {
			//System.out.println("1001 u srcu");
			declarations.addDeclaration(1001, -1, Flags.SRCE);
		} else if (declaration1001(Flags.TIKVA)) {
			//System.out.println("1001 u tikvi");
			declarations.addDeclaration(1001, -1, Flags.TIKVA);
		} else if (declaration1001(Flags.LIST)) {
			//System.out.println("1001 u listu");
			declarations.addDeclaration(1001, -1, Flags.LIST);
		} else if (declaration1001(Flags.ZIR)) {
			//System.out.println("1001 u ziru");
			declarations.addDeclaration(1001, -1, Flags.ZIR);
		}
		// ---------------------------
		if (declarationFourSame(10)) {
			//System.out.println("4 setke");
			declarations.addDeclaration(100, -1, 10);
		}
		if (declarationFourSame(13)) {
			//System.out.println("4 kuje");
			declarations.addDeclaration(100, -1, 13);
		}
		if (declarationFourSame(14)) {
			//System.out.println("4 kralja");
			declarations.addDeclaration(100, -1, 14);
		}
		if (declarationFourSame(15)) {
//			System.out.println("4 keca");
			declarations.addDeclaration(100, -1, 15);
		}
		if (declarationFourSame(9)) {
//			System.out.println("4 vetke");
			declarations.addDeclaration(150, -1, -1);
		}
		if (declarationFourSame(12)) {
//			System.out.println("4 cacana");
			declarations.addDeclaration(200, -1, -1);
		}
		// -----------------------------

//		System.out.println("srce:");
		declaration20_50_100(srceInd, tikvaInd, Flags.SRCE);
//		System.out.println("tikva:");
		declaration20_50_100(tikvaInd, listInd, Flags.TIKVA);
//		System.out.println("list:");
		declaration20_50_100(listInd, zirInd, Flags.LIST);
//		System.out.println("zir:");
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

		for (int j = 0; j < dec.size(); j++) {
			System.out.println(dec.get(j) + " " + to.get(j));
		}

	}
	
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
				System.out.println(c);
			}
		}
		return ret;
	}
	
	public ArrayList<Card> getLegalCards(){
		Card firstCard = MainState.droppedCards.get(AppCore.getInstance().getFirstToPlay());
		System.out.println(firstCard);
		System.out.println("\nBabiscese : ");
		if(firstCard.getCardSuit() == AppCore.adut){			// adut
			System.out.print("-Adut dole");
			if(imaAduta()){										//ima aduta
				System.out.print("-ima aduta");
				if(imaJacegAduta()){							//ima jaceg aduta
					System.out.print("-ima jaceg");
					return dajJaceAdute();
				}else{											//nema jaceg aduta
					System.out.print("-nema jaceg");
					return dajSveAdute();
				}
			}else{												//nema aduta
				System.out.print("-nema aduta");
				return playerCards;
			}
		}else{							//nije adut
			System.out.print("-Prva nije adut");
			if(seceno()){			//seceno
				System.out.print("-neko seko");
				if(imaBoju()){									//ima boju
					System.out.print("-ima boju");
					return dajSveUBoji(firstCard.getCardSuit());
				}else{											//nema boju
					System.out.print("-nema boju");
					if(imaAduta()){								//ima aduta
						System.out.print("-ima aduta");
						if(imaJacegAduta()){
							System.out.print("-ima jaceg aduta");
							return dajJaceAdute();
						}else{
							System.out.print("-nema jaceg aduta");
							return dajSveAdute();
						}
					}else{										//nema aduta
						System.out.print("-mane aduta");
						return playerCards;
					}
				}
			}else{												//neseceno
				System.out.print("-niko seko");
				if(imaBoju()){
					System.out.print("-ima boju");
					if(imaJacuBoju(firstCard.getCardSuit())){
						System.out.print("-ima jacu boju");
						return dajSveJaceUBoji(firstCard.getCardSuit());
					}else{
						System.out.print("-nema jacu boju");
						return dajSveUBoji(firstCard.getCardSuit());
					}
				}else{
					System.out.print("-nema boju");
					if(imaAduta()){
						System.out.print("-ima aduta");
						return dajSveAdute();
					}else{
						System.out.print("-mane aduta");
						return playerCards;
					}
				}
			}
		}
	}

	public Declaration getDeclarations() {
		return declarations;
	}

	public void dealCardToPlayer(Card card) {
		playerCards.add(card);
	}

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

	public int getCardNumber() {
		return playerCards.size();
	}

	public void playCard(int i) {
		Card dropped = playerCards.remove(i);
		
		MainState.droppedCards.put(Flags.HUMAN_ON_PLAY, dropped);
	
		AppCore.getInstance().setNextToPlay(Flags.COMP_RIGHT_ON_PLAY);
		if (AppCore.getInstance().getFirstToPlay() == Flags.HUMAN_ON_PLAY) {
			AppCore.getInstance().setColorDown(MainState.droppedCards.get(Flags.HUMAN_ON_PLAY).getCardSuit());
		}
	}

	public void clearCards() {
		playerCards.clear();
	}

	public Card getCardAt(int i) {
		return playerCards.get(i);
	}

	private class Compare implements Comparator<Card> {

		@Override
		public int compare(Card o1, Card o2) {
			// TODO Auto-generated method stub
			return o1.getCardNumber() - o2.getCardNumber();
		}

	}
	
}