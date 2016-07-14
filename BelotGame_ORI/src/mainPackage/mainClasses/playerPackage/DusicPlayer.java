package mainPackage.mainClasses.playerPackage;

import java.util.ArrayList;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;
import mainPackage.mainClasses.searchPackage.FinalStateTry;
import mainPackage.mainClasses.supportPackage.Percent;
import mainPackage.mainClasses.supportPackage.PercentagePacker;

public class DusicPlayer extends Player {

	@Override
	public void chooseAdut() {
		// TODO Auto-generated method stub

	}

	public void chooseAdut(int trenutniPlayer) { // posto su sad svi playeri
													// instanca iste klase
													// salje se ovaj int ali to
													// ce nestati
		int theAdut = 0;
		for (Card c : playerCards) {
			if (c.getCardNumber() == 12) {
				theAdut = c.getCardSuit();
			}
		}
		if (AppCore.getInstance().getLastToPlay() == trenutniPlayer) {
			AppCore.adut = Flags.SRCE;
			Flags.DEAL_32 = true;
			System.out.println("Player " + (trenutniPlayer - 1) + " javio srce");
			if (trenutniPlayer - 1 == 1)
				MainState.player1Choise = "Player " + (trenutniPlayer - 1) + " chooses harts";
			else if (trenutniPlayer - 1 == 2)
				MainState.player2Choise = "Player " + (trenutniPlayer - 1) + " chooses harts";
			else
				MainState.player3Choise = "Player " + (trenutniPlayer - 1) + " chooses harts";
		} else {
			if (theAdut == 0) {
				System.out.println("Player " + (trenutniPlayer - 1) + " has passed");
				if (trenutniPlayer - 1 == 1)
					MainState.player1Choise = "Player " + (trenutniPlayer - 1) + "has passed";
				else if (trenutniPlayer - 1 == 2)
					MainState.player2Choise = "Player " + (trenutniPlayer - 1) + "has passed";
				else
					MainState.player3Choise = "Player " + (trenutniPlayer - 1) + "has passed";
			} else {
				AppCore.adut = theAdut;
				Flags.DEAL_32 = true;
				String sysoString;
				if (theAdut == Flags.SRCE) {
					sysoString = "SRCE";
				} else if (theAdut == Flags.LIST) {
					sysoString = "LIST";
				} else if (theAdut == Flags.TIKVA) {
					sysoString = "TIKVA";
				} else {
					sysoString = "ZIR";
				}
				System.out.println("Player " + (trenutniPlayer - 1) + " javio " + sysoString);
				if (trenutniPlayer - 1 == 1)
					MainState.player1Choise = "Player " + (trenutniPlayer - 1) + " chooses " + sysoString;
				else if (trenutniPlayer - 1 == 2)
					MainState.player2Choise = "Player " + (trenutniPlayer - 1) + " chooses " + sysoString;
				else
					MainState.player3Choise = "Player " + (trenutniPlayer - 1) + " chooses " + sysoString;
			}
		}
	}

	public void playCard() {
		ArrayList<Card> legalCards = null;
		if (MainState.droppedCards.isEmpty()) {
			legalCards = playerCards;
		} else {
			legalCards = getLegalCards();
		}
		
		System.out.println("\n=============================\nBabic");
		ArrayList<FinalStateTry> states = new ArrayList<>();
		PercentagePacker packer = new PercentagePacker();
		for(int i=0;	i<legalCards.size();	i++){
			Card c = legalCards.get(i);
			Percent targetPerc = new Percent(c);
			targetPerc.setCardIndex(i);
			packer.getPercentages().add(targetPerc);
			states.add(new FinalStateTry(Flags.COMP_TOP_ON_PLAY, c, playerCards, null, Flags.COMP_TOP_ON_PLAY, Flags.SEARCH_NO_MOD, 0));
		}
		
		for(int i=0;	i<states.size();	i++){
			states.get(i).simulate();
			packer.getPercentages().get(i).setNumberOfPoints(states.get(i).getPointsWon());
			packer.getPercentages().get(i).setPercentage(
														 (float)states.get(i).getRoundsWon()/
														 (float)states.get(i).getRoundsPlayed());
			System.out.println(packer.getPercentages().get(i));
		}
		System.out.println(packer.getMaximumPercentageIndex() + "<-- Max percentage index");
		System.out.println(packer.getMinimumPercentageIndex() + "<-- Min percentage index");
		System.out.println(packer.getMaximumPercent().getPercentage() + "% <-- Max percentage");
		System.out.println(packer.getMinimumPercent().getPercentage() + "% <-- Min percentage");
		System.out.println("\n===========================");
		
		int index = 0;
		if(packer.getIndex_MaxPtsTolr(90) != -1){
			index = packer.getIndex_MaxPtsTolr(90);
		}else{
			for(int i=60;	i>=0;	i-=10){
				if(packer.getIndex_MinPtsTolr(i) != -1){
					index = packer.getIndex_MinPtsTolr(i);
					break;
				}
			}
		}
		
		Card dropped = legalCards.remove(index);
		
		MainState.droppedCards.put(Flags.COMP_TOP_ON_PLAY, dropped);
		
		
		for (int i = 0; i < playerCards.size(); i++) {
			if (playerCards.get(i).getCardSuit() == dropped.getCardSuit()
					&& playerCards.get(i).getCardNumber() == dropped.getCardNumber()) {
				playerCards.remove(i);
				break;
			}
		}
		
		MainState.topCardNumber--;
		
		AppCore.getInstance().setNextToPlay(Flags.COMP_LEFT_ON_PLAY);
		if (AppCore.getInstance().getFirstToPlay() == Flags.COMP_TOP_ON_PLAY) {
			AppCore.getInstance().setColorDown(MainState.droppedCards.get(Flags.COMP_TOP_ON_PLAY).getCardSuit());
		}
	}
}
