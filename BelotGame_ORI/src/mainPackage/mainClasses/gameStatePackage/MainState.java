package mainPackage.mainClasses.gameStatePackage;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import mainPackage.mainClasses.AppCore;
import mainPackage.mainClasses.Card;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.supportPackage.PickAdutDialog;
import mainPackage.mainClasses.supportPackage.PressableRectangle;
import mainPackage.mainClasses.supportPackage.SecondCounter;

public class MainState extends BasicGameState{
	//STVARI
	private int gameStateID;
	private PressableRectangle resetButton;
	private PressableRectangle showResultButton;			//dugme prikazuje globalni rezultat i 
	private PickAdutDialog pickAdutDialog;
	
	private Image listAdut;
	private Image zirAdut;
	private Image tikvaAdut;
	private Image srceAdut;
	private Image noAdut;
	private String djuka = "Djuka";
	private String duka = "Duka";
	private String babson = "Babson";
	//SLIKE
	private Image backgroundImage;
	
	//STATICKE
	public static HashMap<Integer, Card> droppedCards = new HashMap<>();
	public static SecondCounter secondCounter;
	public static int MOUSE_X;
	public static int MOUSE_Y;
	public static int leftCardNumber=0,topCardNumber=0,rightCardNumber=0;
	
	public static HashMap<String,Card> deadCards = new HashMap<String,Card>();
	public static String player1Choise;
	public static String player2Choise;
	public static String player3Choise;
	public static String calledAdut = "";
	public static String zvanja = "";
	public int svaZvanja = 0;
	public boolean zvao1 = false;
	public boolean zvao2 = false;
	
	private UnicodeFont ufont = null;
	
	public MainState(int id) throws SlickException{
		gameStateID = id;
		secondCounter = new SecondCounter();
	}
	
	/**
	 * Poziva se slicno kao konstruktor. Prvo se pozove konstruktor, onda se inicajlizuje
	 * stanje koristeci kontejner. <br/>Prvi parametar te ne interesuje, drugi argument je sam main -> ono sto pokrece igru.
	 * */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = new Image(Flags.POZADINA);
		showResultButton = new PressableRectangle(new Point(Flags.BTN_SHOW_RES_TOPLEFT_X,Flags.BTN_SHOW_RES_TOPLEFT_Y),
				 new Point(Flags.BTN_SHOW_RES_TOPRIGHT_X,Flags.BTN_SHOW_RES_TOPRIGHT_Y),
				 new Point(Flags.BTN_SHOW_RES_BOTTOMLEFT_X,Flags.BTN_SHOW_RES_BOTTOMLEFT_Y),
				 new Point(Flags.BTN_SHOW_RES_BOTTOMRIGHT_X,Flags.BTN_SHOW_RES_BOTTOMRIGHT_X),
				 new Image(Flags.BTN_SHOW_RES_IMAGE));

		resetButton = new PressableRectangle(new Point(Flags.BTN_RESET_TOPLEFT_X,Flags.BTN_RESET_TOPLEFT_Y),
				 new Point(Flags.BTN_RESET_TOPRIGHT_X,Flags.BTN_RESET_TOPRIGHT_Y),
				 new Point(Flags.BTN_RESET_BOTTOMLEFT_X,Flags.BTN_RESET_BOTTOMLEFT_Y),
				 new Point(Flags.BTN_RESET_BOTTOMRIGHT_X,Flags.BTN_RESET_BOTTOMRIGHT_X),
				 new Image(Flags.BTN_RESET_IMAGE));
		pickAdutDialog = new PickAdutDialog();
		listAdut = new Image(Flags.CHOSEN_LIST_ADUT);
		zirAdut = new Image(Flags.CHOSEN_ZIR_ADUT);
		tikvaAdut = new Image(Flags.CHOSEN_TIKVA_ADUT);
		srceAdut = new Image(Flags.CHOSEN_SRCE_ADUT);
		noAdut = new Image(Flags.CHOSEN_NO_ADUT);
		
		try {
		   Font font = new Font("Serif", Font.BOLD, 45);
		   ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
		   ufont.addAsciiGlyphs();
		   ufont.addGlyphs(Flags.WINDOW_WIDTH/2 - 115, Flags.WINDOW_HEIGHT/2);
		   ufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		   ufont.loadGlyphs();
		} catch (SlickException e) {
		   e.printStackTrace();
		}
	}
	
	/**
	 * Poziva se svaki put kad treba da se prikaze nesto na ekranu.
	 * */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//g.drawImage(backgroundImage,0,0);	//Prikaz backgrounda -> zakomentarisao da brze potera igru. vratiti na krajnjim testovima.
		g.drawString("T1 : " + AppCore.team1Score + "| T2 : " + AppCore.team2Score + "\n" +
					 "T1F : " + AppCore.fullTeam1Score + "| T2F : " + AppCore.fullTeam2Score,10,10);
		g.drawString(djuka, 10, Flags.WINDOW_HEIGHT/2 - Flags.CARD_BACK_V_HEIGHT/2 - 20);
		g.drawString(babson, Flags.WINDOW_WIDTH - 60, Flags.WINDOW_HEIGHT/2 - Flags.CARD_BACK_V_HEIGHT/2 - 20);
		g.drawString(duka, Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2 - 60, 10);
		g.drawString(zvanja, Flags.WINDOW_WIDTH-300, 10);
		drawInterface(g);
		drawCards(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int DELTA) throws SlickException {
		Input inp = gc.getInput();	//uzmi sav trenutni input
		calibrateMouse(inp);		//namesti koordinate misa
		
		if(Flags.ONE_CIRCLE_PHASE){
			switch(AppCore.getInstance().getNextToPlay()){
				case Flags.HUMAN_ON_PLAY:{
					if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
						dropSelectedCard();
					}
					break;
				}
				case Flags.COMP_RIGHT_ON_PLAY :{
					AppCore.getInstance().getBabicPlayer().playCard();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				case Flags.COMP_TOP_ON_PLAY:{
					AppCore.getInstance().getDusicPlayer().playCard();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				case Flags.COMP_LEFT_ON_PLAY:{
					AppCore.getInstance().getDjukaPlayer().playCard();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		if(Flags.HUMAN_TO_CHOOSE){
			if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				pickAdutDialog.humanChosesAdut(MOUSE_X, MOUSE_Y);		//HUMAN_TO_CHOSE i DEAL_32 se postavlja unutar metoda :
																		//humanChosesAdut -> xClicked -> x.isPressed
			}
			if(AppCore.adut != 0){
				AppCore.zvaoAduta = Flags.HUMAN_ON_PLAY;
			}
		}

		if(Flags.DEAL_24){
			AppCore.getInstance().shuffleCards();
			
			for(int i=0; i < 24; i++){
				if(i%4 == 0){
					AppCore.getInstance().getHumanPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
				} else if(i%4 == 1){
					AppCore.getInstance().getBabicPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					rightCardNumber += 1;
				} else if(i%4 == 2){
					AppCore.getInstance().getDusicPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					topCardNumber += 1;
				} else{
					AppCore.getInstance().getDjukaPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					leftCardNumber += 1;
				}
			}
			AppCore.getInstance().getHumanPlayer().sortCards();
		}
		
		if(Flags.PLAYER1_TO_CHOOSE){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AppCore.getInstance().getBabicPlayer().chooseAdut(2);
			if(AppCore.adut != 0){
				AppCore.zvaoAduta = Flags.COMP_RIGHT_ON_PLAY;
			}
		}
		
		if(Flags.PLAYER2_TO_CHOOSE){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AppCore.getInstance().getDusicPlayer().chooseAdut(3);
			if(AppCore.adut != 0){
				AppCore.zvaoAduta = Flags.COMP_TOP_ON_PLAY;
			}
		}
		
		if(Flags.PLAYER3_TO_CHOOSE){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AppCore.getInstance().getDjukaPlayer().chooseAdut(4);
			if(AppCore.adut != 0){
				AppCore.zvaoAduta = Flags.COMP_LEFT_ON_PLAY;
			}
		}
		
		if(Flags.DEAL_32){
			for(int i=24;i<32;	i++){
				if(i%4 == 0){
					AppCore.getInstance().getHumanPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
				} else if(i%4 == 1){
					AppCore.getInstance().getBabicPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					rightCardNumber += 1;
				} else if(i%4 == 2){
					AppCore.getInstance().getDusicPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					topCardNumber += 1;
				} else{
					AppCore.getInstance().getDjukaPlayer().dealCardToPlayer(AppCore.getInstance().getCards().get(i));
					leftCardNumber += 1;
				}
			}
			for (int i = 0; i < AppCore.getInstance().getBabicPlayer().getCardNumber(); i++) {
				System.out.print(AppCore.getInstance().getBabicPlayer().getCardByIndex(i) + "||");
			}
			System.out.println();
			for (int i = 0; i < AppCore.getInstance().getDusicPlayer().getCardNumber(); i++) {
				System.out.print(AppCore.getInstance().getDusicPlayer().getCardByIndex(i) + "||");
			}
			System.out.println();
			for (int i = 0; i < AppCore.getInstance().getDjukaPlayer().getCardNumber(); i++) {
				System.out.print(AppCore.getInstance().getDjukaPlayer().getCardByIndex(i) + "||");
			}
			AppCore.getInstance().getHumanPlayer().sortCards();
			AppCore.getInstance().getBabicPlayer().sortCards();
			AppCore.getInstance().getDusicPlayer().sortCards();
			AppCore.getInstance().getDjukaPlayer().sortCards();
		}
		
		if(Flags.DECLARATIONS){
			zvanja = "";
			AppCore.getInstance().getHumanPlayer().handleDeclarations();
			
			AppCore.getInstance().getBabicPlayer().handleDeclarations();
			
			
			AppCore.getInstance().getDjukaPlayer().handleDeclarations();
			
			
			// ako postoje javljanja
			if(!(AppCore.getInstance().getHumanPlayer().getDeclarations().getDeclarationCount() == 0 &&
					AppCore.getInstance().getBabicPlayer().getDeclarations().getDeclarationCount() == 0 &&
					AppCore.getInstance().getDusicPlayer().getDeclarations().getDeclarationCount() == 0 &&
					AppCore.getInstance().getDjukaPlayer().getDeclarations().getDeclarationCount() == 0)){
				
				// javlja tim 2
				if(AppCore.getInstance().getHumanPlayer().getDeclarations().getDeclarationCount() == 0 &&
						AppCore.getInstance().getDusicPlayer().getDeclarations().getDeclarationCount() ==0){
					addDeclTo2Score();
				} // javlja tim 1
				else if(AppCore.getInstance().getBabicPlayer().getDeclarations().getDeclarationCount() == 0 &&
						AppCore.getInstance().getDjukaPlayer().getDeclarations().getDeclarationCount() == 0){
					addDeclTo1Score();
				} // pita se ko ima jace javljanje 
				else{
					int maxTim1 = AppCore.getInstance().getHumanPlayer().getDeclarations().getMaxValue() > 
								  AppCore.getInstance().getDusicPlayer().getDeclarations().getMaxValue() ?
								  AppCore.getInstance().getHumanPlayer().getDeclarations().getMaxValue() :
								  AppCore.getInstance().getDusicPlayer().getDeclarations().getMaxValue();
								  
				    int maxTim2 = AppCore.getInstance().getBabicPlayer().getDeclarations().getMaxValue() >
				    			  AppCore.getInstance().getDjukaPlayer().getDeclarations().getMaxValue() ?
				    			  AppCore.getInstance().getBabicPlayer().getDeclarations().getMaxValue() :
				    			  AppCore.getInstance().getDjukaPlayer().getDeclarations().getMaxValue();
					
					
				    // javlja tim1
				    if(maxTim1 > maxTim2){
				    	addDeclTo1Score();
				    } // javlja tim2 
				    else if(maxTim1 < maxTim2){
				    	addDeclTo2Score();
				    } // pitamo se ko ima do vece karte 
				    else{
				    	int maxToTim1 = -5;
				    	int maxToTim2 = -5;
				    	
				    	if(AppCore.getInstance().getHumanPlayer().getDeclarations().getMaxValue() == maxTim1)
				    		maxToTim1 = AppCore.getInstance().getHumanPlayer().getDeclarations().getMaxTo();
				    	if(AppCore.getInstance().getDusicPlayer().getDeclarations().getMaxValue() == maxTim1 &&
				    			AppCore.getInstance().getDusicPlayer().getDeclarations().getMaxTo() > maxToTim1)
				    		maxToTim1 = AppCore.getInstance().getDusicPlayer().getDeclarations().getMaxTo();
				    	
				    	if(AppCore.getInstance().getBabicPlayer().getDeclarations().getMaxValue() == maxTim2)
				    		maxToTim2 = AppCore.getInstance().getBabicPlayer().getDeclarations().getMaxTo();
				    	if(AppCore.getInstance().getDjukaPlayer().getDeclarations().getMaxValue() == maxTim2 &&
				    			AppCore.getInstance().getDjukaPlayer().getDeclarations().getMaxTo() > maxToTim2)
				    		maxToTim2 = AppCore.getInstance().getDjukaPlayer().getDeclarations().getMaxTo();
				    	
				    	if(maxToTim1 > maxToTim2){
				    		addDeclTo1Score();
				    	}
				    	else if(maxToTim1 < maxToTim2){
				    		addDeclTo2Score();
				    	}
				    	else{
				    		if(tim1ToPlay(maxTim1,maxToTim1)){
				    			addDeclTo1Score();
				    		} else{
				    			addDeclTo2Score();
				    		}
				    	}
				    }
				}
				
			}
			AppCore.getInstance().getHumanPlayer().getDeclarations().clearDeclaration();
			AppCore.getInstance().getDjukaPlayer().getDeclarations().clearDeclaration();
			AppCore.getInstance().getDusicPlayer().getDeclarations().clearDeclaration();
			AppCore.getInstance().getBabicPlayer().getDeclarations().clearDeclaration();
		}
		
		flowControl();
	}
	

	public void addDeclTo1Score(){
		zvanja += "Tim 1 :\n\t";
		for(int i=0;	i<AppCore.getInstance().getHumanPlayer().getDeclarations().getDeclarationCount();	i++){
			if(i == 0){
				zvanja+="Human: ";
			}
			AppCore.team1Score += AppCore.getInstance().getHumanPlayer().getDeclarations().getValueAt(i);
			svaZvanja += AppCore.getInstance().getHumanPlayer().getDeclarations().getValueAt(i);
			zvanja += AppCore.getInstance().getHumanPlayer().getDeclarations().getValueAt(i) + " ";
			int toAt = AppCore.getInstance().getHumanPlayer().getDeclarations().getToAt(i);
			int suitAt = AppCore.getInstance().getHumanPlayer().getDeclarations().getSuitAt(i);
			
			if(toAt > 0){
				zvanja += "to " + toAt + " ";
			}
			switch(suitAt){
				case Flags.LIST :{
					zvanja += " in spades.";
					break;
				}
				case Flags.SRCE : {
					zvanja += " in hearts.";
					break;
				}
				case Flags.TIKVA :{
					zvanja += " in pumpkinz.";
					break;
				}
				case Flags.ZIR : {
					zvanja += " in clubs.";
					break;
				}
				case 10 :{
					zvanja += " in tens.";
					break;
				}
				case 13 :{
					zvanja += " in Queens.";
					break;
				}
				case 14 : {
					zvanja += " in Kings.";
					break;
				}
				case 15 : {
					zvanja += " u Aces.";
					break;
				}
				default:{
					
					break;
				}
			}
		}
		for(int i=0;	i<AppCore.getInstance().getDusicPlayer().getDeclarations().getDeclarationCount();	i++){
			if(i == 0){
				zvanja+="\n\tDusic: ";
			}
			AppCore.team1Score += AppCore.getInstance().getDusicPlayer().getDeclarations().getValueAt(i);
			svaZvanja += AppCore.getInstance().getDusicPlayer().getDeclarations().getValueAt(i);
			zvanja += AppCore.getInstance().getDusicPlayer().getDeclarations().getValueAt(i) + " ";
			int toAt = AppCore.getInstance().getDusicPlayer().getDeclarations().getToAt(i);
			int suitAt = AppCore.getInstance().getDusicPlayer().getDeclarations().getSuitAt(i);
			
			if(toAt > 0){
				zvanja += "to " + toAt + " ";
			}
			switch(suitAt){
				case Flags.LIST :{
					zvanja += " in spades.";
					break;
				}
				case Flags.SRCE : {
					zvanja += " in hearts.";
					break;
				}
				case Flags.TIKVA :{
					zvanja += " in pumpkinz.";
					break;
				}
				case Flags.ZIR : {
					zvanja += " in clubs.";
					break;
				}
				case 10 :{
					zvanja += " in tens.";
					break;
				}
				case 13 :{
					zvanja += " in Queens.";
					break;
				}
				case 14 : {
					zvanja += " in Kings.";
					break;
				}
				case 15 : {
					zvanja += " u Aces.";
					break;
				}
				default:{
					
					break;
				}
		}
		zvao1 = true;
	}
}

	public void addDeclTo2Score(){
		zvanja += "Tim 2 :\n\t";
		for(int i=0;	i<AppCore.getInstance().getDjukaPlayer().getDeclarations().getDeclarationCount();	i++){
			if(i == 0){
				zvanja+="Djuka: ";
			}
			AppCore.team2Score += AppCore.getInstance().getDjukaPlayer().getDeclarations().getValueAt(i);
			svaZvanja += AppCore.getInstance().getDjukaPlayer().getDeclarations().getValueAt(i);
			zvanja += AppCore.getInstance().getDjukaPlayer().getDeclarations().getValueAt(i) + " ";
			int toAt = AppCore.getInstance().getDjukaPlayer().getDeclarations().getToAt(i);
			int suitAt = AppCore.getInstance().getDjukaPlayer().getDeclarations().getSuitAt(i);
			
			if(toAt > 0){
				zvanja += "to " + toAt + " ";
			}
			switch(suitAt){
				case Flags.LIST :{
					zvanja += " in spades.";
					break;
				}
				case Flags.SRCE : {
					zvanja += " in hearts.";
					break;
				}
				case Flags.TIKVA :{
					zvanja += " in pumpkinz.";
					break;
				}
				case Flags.ZIR : {
					zvanja += " in clubs.";
					break;
				}
				case 10 :{
					zvanja += " in tens.";
					break;
				}
				case 13 :{
					zvanja += " in Queens.";
					break;
				}
				case 14 : {
					zvanja += " in Kings.";
					break;
				}
				case 15 : {
					zvanja += " u Aces.";
					break;
				}
				default:{
					
					break;
				}
		}
		}
		for(int i=0;	i<AppCore.getInstance().getBabicPlayer().getDeclarations().getDeclarationCount();	i++){
			if(i == 0){
				zvanja+="\n\tBabic: ";
			}
			AppCore.team2Score += AppCore.getInstance().getBabicPlayer().getDeclarations().getValueAt(i);
			svaZvanja += AppCore.getInstance().getBabicPlayer().getDeclarations().getValueAt(i);
			zvanja += AppCore.getInstance().getBabicPlayer().getDeclarations().getValueAt(i) + " ";
			int toAt = AppCore.getInstance().getBabicPlayer().getDeclarations().getToAt(i);
			int suitAt = AppCore.getInstance().getBabicPlayer().getDeclarations().getSuitAt(i);
			
			if(toAt > 0){
				zvanja += "to " + toAt + " ";
			}
			switch(suitAt){
				case Flags.LIST :{
					zvanja += " in spades.";
					break;
				}
				case Flags.SRCE : {
					zvanja += " in hearts.";
					break;
				}
				case Flags.TIKVA :{
					zvanja += " in pumpkinz.";
					break;
				}
				case Flags.ZIR : {
					zvanja += " in clubs.";
					break;
				}
				case 10 :{
					zvanja += " in tens.";
					break;
				}
				case 13 :{
					zvanja += " in Queens.";
					break;
				}
				case 14 : {
					zvanja += " in Kings.";
					break;
				}
				case 15 : {
					zvanja += " u Aces.";
					break;
				}
				default:{
					
					break;
				}
		}
		zvao2 = true;
	}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void flowControl(){
		if(Flags.CALCULATE_DECK_RESULT){
			if(AppCore.fullTeam1Score > 1001 && AppCore.fullTeam2Score > 1001){
				if(AppCore.fullTeam1Score > AppCore.fullTeam2Score){
					System.out.println("Pobedio tim 1");
				}else{
					System.out.println("Pobedio tim 2");
				}
				System.exit(1);
			}else{
				if(AppCore.fullTeam1Score > 1001){
					System.out.println("Pobedio tim 1");
					System.exit(1);
				}else if(AppCore.fullTeam2Score > 1001){
					System.out.println("Pobdeio tim 2");
					System.exit(1);
				}else{
					Flags.CALCULATE_DECK_RESULT = false;
					Flags.DEAL_24_PREFASE = true;
					AppCore.adut = 0;
					int lastDeal = Flags.ON_DEAL;
					if(lastDeal == 4){
						Flags.ON_DEAL = 1;
						AppCore.getInstance().setFirstToPlay(2);
					}else{
						Flags.ON_DEAL = lastDeal + 1;
						if(Flags.ON_DEAL == 4){
							AppCore.getInstance().setFirstToPlay(1);
						}else{
							AppCore.getInstance().setFirstToPlay(Flags.ON_DEAL + 1);
						}
					}
				}
			}
		}
		
		if(Flags.CALCULATE_CIRCLE_RESULT){
			Flags.CALCULATE_CIRCLE_RESULT = false;
			if(AppCore.getInstance().getBabicPlayer().getCardNumber() == 0 &&
				   AppCore.getInstance().getDjukaPlayer().getCardNumber() == 0 &&
				   AppCore.getInstance().getDusicPlayer().getCardNumber() == 0 &&
				   AppCore.getInstance().getHumanPlayer().getCardNumber() == 0
				){
					Flags.CALCULATE_DECK_RESULT = true;
					if(Flags.HUMAN_ON_PLAY == AppCore.getInstance().getFirstToPlay() || Flags.COMP_TOP_ON_PLAY == AppCore.getInstance().getFirstToPlay()){
						AppCore.team1Score += 10;
					}else{
						AppCore.team2Score += 10;
					}
					if(AppCore.zvaoAduta == 1 || AppCore.zvaoAduta == 3){
						if(AppCore.team1Score < AppCore.fullScore/2){
							AppCore.team2Score = 162;
							AppCore.team1Score = 0;
						}
					}else{
						if(AppCore.team2Score < AppCore.fullScore/2){
							AppCore.team1Score = 162;
							AppCore.team2Score = 0;
						}
					}
					if(zvao1){
						if(AppCore.team1Score - svaZvanja == 0){
							AppCore.team1Score = 0;
							AppCore.team2Score = 162 + svaZvanja + 90;
						}
					}else if(zvao2){
						if(AppCore.team2Score - svaZvanja == 0){
							AppCore.team2Score = 0;
							AppCore.team1Score = 162 + svaZvanja + 90;
						}
					}
					AppCore.fullTeam1Score += AppCore.team1Score;
					AppCore.fullTeam2Score += AppCore.team2Score;
					
					AppCore.team1Score = 0;
					AppCore.team2Score = 0;
					zvao1 = false;
					zvao2 = false;
			}else{
				Flags.ONE_CIRCLE_PHASE = true;
			}
		}
		if(Flags.CLEAR_CARDS_ON_TABLE_PHASE){
			Flags.CLEAR_CARDS_ON_TABLE_PHASE = false;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Flags.CALCULATE_CIRCLE_RESULT = true;
			droppedCards.clear();
		}
		
		if(Flags.CONFIG_FIRST_NO_DEAL){
			AppCore.getInstance().PRVIIGRAC();
			Flags.CLEAR_CARDS_ON_TABLE_PHASE = true;
			Flags.CONFIG_FIRST_NO_DEAL = false;
		}
		
		if(Flags.ONE_CIRCLE_PHASE){
			if(droppedCards.size()==4){
				Flags.ONE_CIRCLE_PHASE = false;
				Flags.CONFIG_FIRST_NO_DEAL = true;
				for(Card c : droppedCards.values()){						//NAPUNI PROSLE KARTE TEK KAD PRODJU
					deadCards.put(c.getCardName(), c);
				}
				int ind = 0;
				for(Card c : deadCards.values()){
					if(ind%4 == 0){
						System.out.println("\n=============================");
					}
					System.out.print(c + " ");
					ind ++;
				}
			}
		}
		
		if(Flags.PLAY_PHASE_CONFIG_FIRST){
			int ftp = 0;
			int ltp = 0;
			if(Flags.ON_DEAL+1 == 5){
				ftp = 1;
				ltp = 4;
			}else{
				ftp = Flags.ON_DEAL+1;
				ltp = Flags.ON_DEAL;
			}
			AppCore.getInstance().setFirstToPlay(ftp);
			AppCore.getInstance().setNextToPlay(ftp);
			AppCore.getInstance().setLastToPlay(ltp);
			Flags.PLAY_PHASE_CONFIG_FIRST = false;
			Flags.ONE_CIRCLE_PHASE = true;
		}
		
		if(Flags.DECLARATIONS){
			Flags.DECLARATIONS = false;
			Flags.PLAY_PHASE_CONFIG_FIRST = true;
		}
		
		if(Flags.DECLARATION_PRESTEP) {
			Flags.DECLARATION_PRESTEP = false;
			Flags.DECLARATIONS = true;
		}
		else if(Flags.PLAYER3_TO_CHOOSE){
			Flags.PLAYER3_TO_CHOOSE = false;
			if(AppCore.adut == 0)
				Flags.HUMAN_TO_CHOOSE = true;
		}
		
		else if(Flags.PLAYER2_TO_CHOOSE){
			Flags.PLAYER2_TO_CHOOSE = false;
			if(AppCore.adut == 0)
				Flags.PLAYER3_TO_CHOOSE = true;
		}
		
		else if(Flags.PLAYER1_TO_CHOOSE){
			Flags.PLAYER1_TO_CHOOSE = false;
			if(AppCore.adut == 0) {
				Flags.PLAYER2_TO_CHOOSE = true;
			}
		}
		if(Flags.DIALOG_PRESTEP){
			Flags.DIALOG_PRESTEP = false;
			Flags.PLAYER1_TO_CHOOSE = true;
			return;
		}
		if(Flags.DEAL_24){					//ako si podelio 24 karte, ne mozes vise 24 karte da delis i human moze da bira aduta (za sad..)
			deadCards.clear();
			Flags.DEAL_24 = false;
			
			if(AppCore.getInstance().getFirstToPlay() == 1)
				Flags.HUMAN_TO_CHOOSE = true;
			else if(AppCore.getInstance().getFirstToPlay() == 2)
				Flags.PLAYER1_TO_CHOOSE = true;
			else if(AppCore.getInstance().getFirstToPlay() == 3)
				Flags.PLAYER2_TO_CHOOSE = true;
			else
				Flags.PLAYER3_TO_CHOOSE = true;
		}
		if(Flags.DEAL_24_PREFASE){
			Flags.DEAL_24 = true;
			Flags.DEAL_24_PREFASE = false;
		}
		else if(Flags.DEAL_32){					// ako si podelio 32 karte, ne mozes vise da delis 32 karte
			Flags.DEAL_32 = false;
			
			Flags.PLAYER1_TO_CHOOSE = false;
			Flags.PLAYER2_TO_CHOOSE = false;
			Flags.PLAYER3_TO_CHOOSE = false;
			Flags.HUMAN_TO_CHOOSE = false;
			
			Flags.HUMAN_TO_DROP_CARD = true;
			Flags.DECLARATION_PRESTEP = true;
			AppCore.getInstance().runThroughCards();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private void calibrateMouse(Input in){
		MOUSE_X = in.getMouseX();
		MOUSE_Y = in.getMouseY();
	}
	
	/**
	 * Vraca id od ovog sranja. <br/>Potrebno u mainu da bi mogli da se prebacujemo iz stanja u stanje.
	 * */
	@Override
	public int getID() {
		return gameStateID;
	}

	private void drawCards(Graphics g) throws SlickException{
		int currentOffset = 0;
		
		for (int i = 0; i < AppCore.getInstance().getHumanPlayer().getCardNumber(); i++) {
			g.drawImage(AppCore.getInstance().getHumanPlayer().getCardAt(i).getCardImage(), 
					Flags.CARD_OFFSET_X + currentOffset, 
					Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y);
			currentOffset += Flags.CARD_WIDTH + Flags.CARD_INCREMENT_OFFSET;
		}
	}
	
	private void drawDownCards(Graphics g){
		if(!droppedCards.isEmpty()){
			if(droppedCards.containsKey(Flags.HUMAN_ON_PLAY)){
				droppedCards.get(Flags.HUMAN_ON_PLAY).getCardImage().draw(Flags.WINDOW_WIDTH/2 - Flags.CARD_WIDTH/2
																		 ,Flags.WINDOW_HEIGHT/2 + Flags.CARD_HEIGHT/4);
			}
			if(droppedCards.containsKey(Flags.COMP_LEFT_ON_PLAY)){
				droppedCards.get(Flags.COMP_LEFT_ON_PLAY).getRotatedCardImage().draw(Flags.WINDOW_WIDTH/2 - 2*Flags.CARD_WIDTH
																		 ,Flags.WINDOW_HEIGHT/2 - Flags.CARD_HEIGHT/2);
			}
			if(droppedCards.containsKey(Flags.COMP_TOP_ON_PLAY)){
				droppedCards.get(Flags.COMP_TOP_ON_PLAY).getCardImage().draw(Flags.WINDOW_WIDTH/2 - Flags.CARD_WIDTH/2
																		 ,Flags.WINDOW_HEIGHT/2 - 4*Flags.CARD_HEIGHT/3 + 10);
			}
			if(droppedCards.containsKey(Flags.COMP_RIGHT_ON_PLAY)){
				droppedCards.get(Flags.COMP_RIGHT_ON_PLAY).getRotatedCardImage().draw(Flags.WINDOW_WIDTH/2 + Flags.CARD_WIDTH
																		 ,Flags.WINDOW_HEIGHT/2 - Flags.CARD_HEIGHT/2);
			}
		}
	}
	
	private void dropSelectedCard(){
		int currentOffset = 0;
		for(int i = 0; i < AppCore.getInstance().getHumanPlayer().getCardNumber();i++){
			PressableRectangle cardRec = new PressableRectangle(new Point(Flags.CARD_OFFSET_X+currentOffset,Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y ), 
					new Point(Flags.CARD_OFFSET_X + currentOffset + Flags.CARD_WIDTH,Flags.WINDOW_HEIGHT - Flags.CARD_HEIGHT - Flags.CARD_OFFSET_Y ),
					new Point(Flags.CARD_OFFSET_X + currentOffset, Flags.WINDOW_HEIGHT), new Point(Flags.CARD_OFFSET_X + currentOffset + Flags.CARD_WIDTH, Flags.WINDOW_HEIGHT), AppCore.getInstance().getHumanPlayer().getCardAt(i).getCardImage());
			currentOffset += Flags.CARD_WIDTH + Flags.CARD_INCREMENT_OFFSET;
			if(cardRec.isPressed(MOUSE_X, MOUSE_Y)){
				if(!droppedCards.isEmpty()){
					ArrayList<Card> legal = AppCore.getInstance().getHumanPlayer().getLegalCards();
					Card playedCard = AppCore.getInstance().getHumanPlayer().getCardAt(i);
					for(int j=0;	j<legal.size();	j++){
						if(playedCard.getCardSuit() == legal.get(j).getCardSuit() && playedCard.getCardValue() == legal.get(j).getCardValue()){
							AppCore.getInstance().getHumanPlayer().playCard(i);
							return;
						}
					}
				}else{
					AppCore.getInstance().getHumanPlayer().playCard(i);
				}
			}
		}
		
	}

	private void drawInterface(Graphics g) throws SlickException{
		//g.drawImage(resetButton.getImage(), resetButton.getTopLeft().getX(), resetButton.getTopLeft().getY());
		//g.drawImage(showResultButton.getImage(), showResultButton.getTopLeft().getX(), showResultButton.getTopLeft().getY());
		drawLeftCards(g);
		drawRightCards(g);
		drawTopCards(g);
		changeAdutCorner(g);
		drawDownCards(g);
		if(Flags.HUMAN_TO_CHOOSE){
			drawHumanPickAdut(g);
		}
		
		if(Flags.PLAYER1_TO_CHOOSE){
			drawPlayersPicking(g,1);
		}
		
		if(Flags.PLAYER2_TO_CHOOSE){
			drawPlayersPicking(g, 2);
		}
		
		if(Flags.PLAYER3_TO_CHOOSE){
			drawPlayersPicking(g, 3);
		}
		
		if(Flags.DECLARATIONS || Flags.DECLARATION_PRESTEP){
			drawPlayerChooseAdut(g);
		}
	}
	
	private void drawPlayerChooseAdut(Graphics g){
		// PROMENITI DA PISE KO JE JAVIO
		String adut = "";
		
		if(AppCore.adut == Flags.SRCE)
			adut = "harts";
		else if(AppCore.adut == Flags.TIKVA)
			adut = "pumpkins";
		else if(AppCore.adut == Flags.ZIR)
			adut = "clubs";
		else
			adut = "spades";
		
		ufont.drawString(Flags.WINDOW_WIDTH/2-160, Flags.WINDOW_HEIGHT/2 - 40, "Adut is " + adut);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void drawPlayersPicking(Graphics g,int player){
		ufont.drawString(Flags.WINDOW_WIDTH/2 - 220, Flags.WINDOW_HEIGHT/2 - 40, "Player" + player + " to choose adut");
	}
	
	private void drawHumanPickAdut(Graphics g){
		pickAdutDialog.drawDialog(g);
	}
	
	private void changeAdutCorner(Graphics g){
		int gdeX = Flags.WINDOW_WIDTH - Flags.CHOSEN_ADUT_ICON_WIDTH;
		int gdeY = Flags.WINDOW_HEIGHT - Flags.CHOSEN_ADUT_ICON_HEIGHT;
		if(AppCore.zvaoAduta == 0){
			calledAdut = "None";
		}else if(AppCore.zvaoAduta == Flags.COMP_LEFT_ON_PLAY){
			calledAdut = "Djuka";
		}else if(AppCore.zvaoAduta == Flags.COMP_TOP_ON_PLAY){
			calledAdut = "Duka";
		}else if(AppCore.zvaoAduta == Flags.COMP_RIGHT_ON_PLAY){
			calledAdut = "Babson";
		}else{
			calledAdut = "Human";
		}
		switch(AppCore.adut){
			case 0:	{
				g.drawImage(noAdut, gdeX, gdeY);
				break;
			}
			case Flags.LIST: {
				g.drawImage(listAdut, gdeX, gdeY);
				break;
			}
			case Flags.SRCE: {
				g.drawImage(srceAdut, gdeX, gdeY);
				break;
			}
			case Flags.TIKVA: {
				g.drawImage(tikvaAdut, gdeX, gdeY);
				break;
			}
			case Flags.ZIR: {
				g.drawImage(zirAdut, gdeX, gdeY);
				break;
			}
		}
		g.drawString(calledAdut, gdeX, gdeY- 26);
	}
	
	private void drawLeftCards(Graphics g) throws SlickException{
		if(leftCardNumber == 8){
			g.drawImage(new Image(Flags.CARD_BACK_8_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 7){
			g.drawImage(new Image(Flags.CARD_BACK_7_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 6){
			g.drawImage(new Image(Flags.CARD_BACK_6_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 5){
			g.drawImage(new Image(Flags.CARD_BACK_5_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 4){
			g.drawImage(new Image(Flags.CARD_BACK_4_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 3){
			g.drawImage(new Image(Flags.CARD_BACK_3_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 2){
			g.drawImage(new Image(Flags.CARD_BACK_2_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(leftCardNumber == 1){
			g.drawImage(new Image(Flags.CARD_BACK_1_V), 0, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}
	}
	
	private void drawRightCards(Graphics g) throws SlickException{
		if(rightCardNumber == 8){
			g.drawImage(new Image(Flags.CARD_BACK_8_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 7){
			g.drawImage(new Image(Flags.CARD_BACK_7_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 6){
			g.drawImage(new Image(Flags.CARD_BACK_6_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 5){
			g.drawImage(new Image(Flags.CARD_BACK_5_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 4){
			g.drawImage(new Image(Flags.CARD_BACK_4_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 3){
			g.drawImage(new Image(Flags.CARD_BACK_3_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 2){
			g.drawImage(new Image(Flags.CARD_BACK_2_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}else if(rightCardNumber == 1){
			g.drawImage(new Image(Flags.CARD_BACK_1_V), Flags.WINDOW_WIDTH-Flags.CARD_BACK_V_WIDTH, Flags.WINDOW_HEIGHT/2-Flags.CARD_BACK_V_HEIGHT/2);
		}
	}
	
	public boolean tim1ToPlay(int maxValue,int maxTo){
		
		int player = AppCore.getInstance().getFirstToPlay();
		
		for(int j=0; j < 4; j++){
			switch(player){
			
				case Flags.HUMAN_ON_PLAY :
					for(int i=0; i < AppCore.getInstance().getHumanPlayer().getDeclarations().getDeclarationCount(); i++)
						if(AppCore.getInstance().getHumanPlayer().getDeclarations().getValueAt(i) == maxValue &&
								AppCore.getInstance().getHumanPlayer().getDeclarations().getToAt(i) == maxTo)
							return true;
					player = Flags.COMP_RIGHT_ON_PLAY;
					break;
				
				case Flags.COMP_RIGHT_ON_PLAY :
					for(int i=0; i < AppCore.getInstance().getBabicPlayer().getDeclarations().getDeclarationCount(); i++)
						if(AppCore.getInstance().getBabicPlayer().getDeclarations().getValueAt(i) == maxValue &&
								AppCore.getInstance().getBabicPlayer().getDeclarations().getToAt(i) == maxTo)
							return false;
					player = Flags.COMP_TOP_ON_PLAY;
					break;
				
				case Flags.COMP_TOP_ON_PLAY :
					for(int i=0; i < AppCore.getInstance().getDusicPlayer().getDeclarations().getDeclarationCount(); i++)
						if(AppCore.getInstance().getDusicPlayer().getDeclarations().getValueAt(i) == maxValue &&
								AppCore.getInstance().getDusicPlayer().getDeclarations().getToAt(i) == maxTo)
							return true;
					player = Flags.COMP_LEFT_ON_PLAY;
					break;
					
				case Flags.COMP_LEFT_ON_PLAY :
					for(int i=0; i < AppCore.getInstance().getDjukaPlayer().getDeclarations().getDeclarationCount(); i++)
						if(AppCore.getInstance().getDjukaPlayer().getDeclarations().getValueAt(i) == maxValue &&
								AppCore.getInstance().getDjukaPlayer().getDeclarations().getToAt(i) == maxTo)
							return false;
					player = Flags.HUMAN_ON_PLAY;
					break;
			}
		}
		
		return false;
	}
	
	private void drawTopCards(Graphics g) throws SlickException{
		if(topCardNumber == 8){
			g.drawImage(new Image(Flags.CARD_BACK_8_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 7){
			g.drawImage(new Image(Flags.CARD_BACK_7_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 6){
			g.drawImage(new Image(Flags.CARD_BACK_6_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 5){
			g.drawImage(new Image(Flags.CARD_BACK_5_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 4){
			g.drawImage(new Image(Flags.CARD_BACK_4_H), Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 3){
			g.drawImage(new Image(Flags.CARD_BACK_3_H),Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 2){
			g.drawImage(new Image(Flags.CARD_BACK_2_H),Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}else if(topCardNumber == 1){
			g.drawImage(new Image(Flags.CARD_BACK_1_H),Flags.WINDOW_WIDTH/2 - Flags.CARD_BACK_H_WIDTH/2, 0);
		}
	}
}