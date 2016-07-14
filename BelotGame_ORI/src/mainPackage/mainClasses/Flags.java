package mainPackage.mainClasses;

public class Flags {
	//SEARCH FLAGOVI
	public static final int SEARCH_NO_MOD = 0;
	public static final int SEARCH_BEAM_MOD = 1;
	
	public static final int BEAM_DEPTH_MAX = 1;
	public static final int BEAM_MAX = 3;
	//KO DELI
	public static int ON_DEAL = 4;
	
	public static final int HUMAN_TO_DEAL = 1;
	public static final int COMP_RIGHT_TO_DEAL = 2;
	public static final int COMP_TOP_TO_DEAL = 3;
	public static final int COMP_LEFT_TO_DEAL = 4;
	
	//IGRACI
	public static boolean HUMAN_TO_DROP_CARD = false;
	public static boolean PLAYER1_TO_DROP_CARD = false;
	public static boolean PLAYER2_TO_DROP_CARD = false;
	public static boolean PLAYER3_TO_DROP_CARD = false;
	
	public static boolean DJUKA_MOD = false;
	public static boolean VESELI_MOD = false;
	
	//KO IGRA
	public static int ON_PLAY = 0;
	
	public static final int HUMAN_ON_PLAY = 1;
	public static final int COMP_RIGHT_ON_PLAY = 2;
	public static final int COMP_TOP_ON_PLAY = 3;
	public static final int COMP_LEFT_ON_PLAY = 4;
	
	//STANJA
	public static boolean DEAL_24 = true;						// stanje u kojem se iscrtavaju 6 karata svakom igracu
	public static boolean DEAL_32 = false;						// stanje u kojem se iscrtavaju jos 2 karte svakom igracu 
	public static boolean HUMAN_TO_CHOOSE = false;				// stanje u kojem human bira aduta
	public static boolean PLAYER1_TO_CHOOSE = false;			// nisam video ove tvoje ispod..
	public static boolean PLAYER2_TO_CHOOSE = false;
	public static boolean PLAYER3_TO_CHOOSE = false;
	public static boolean DECLARATIONS = false;
	public static boolean DIALOG_PRESTEP = false;							// za onu siptariju sa dijalogom
	public static boolean DECLARATION_PRESTEP = false;						// za siptariju sa ispisom aduta
	public static boolean PLAY_PHASE_CONFIG_FIRST = false;
	public static boolean ONE_CIRCLE_PHASE = false;
	public static boolean CLEAR_CARDS_ON_TABLE_PHASE = false;
	public static boolean CONFIG_FIRST_NO_DEAL = false;
	public static boolean DEAL_24_PREFASE = false;
	
	public static boolean HUMAN_TO_PLAY = false;
	public static boolean COMP_RIGHT_TO_PLAY = false;
	public static boolean COMP_TOP_TO_PLAY = false;
	public static boolean COMP_LEFT_TO_PLAY = false;
	
	public static boolean CALCULATE_CIRCLE_RESULT = false;		//kalkulisi rezultat jednog kruga
	public static boolean CALCULATE_DECK_RESULT = false;		//kalkulisi rezultat jednog spila
	public static boolean CALCULATE_ROUND_RESULT = false;		//kalkulisi rezultat preko 1001
	
	// BOJE KARATA
	public static final int SRCE = 1;
	public static final int TIKVA = 2;
	public static final int LIST = 3;
	public static final int ZIR = 4;
	
	// GRAFIKA
	public static final String POZADINA = "PozadinaBelotManja.png";
	public static final String BTN_RESET_IMAGE = "Dugmici/RESET_BUTTON.jpg";
	public static final String BTN_SHOW_RES_IMAGE = "Dugmici/SHOW_RESULT_BUTTON.jpg";
	public static final String BTN_PICK_ADUT_IMAGE = "Dugmici/PICK_ADUT.jpg";
	public static final String BTN_LIST_ICON = "Ikonice/LIST_IKONA.jpg";
	public static final String BTN_SRCE_ICON = "Ikonice/SRCE_IKONA.jpg";
	public static final String BTN_ZIR_ICON = "Ikonice/ZIR_IKONA.jpg";
	public static final String BTN_TIKVA_ICON = "Ikonice/TIKVA_IKONA.jpg";
	public static final String BTN_PASS_ICON = "Ikonice/PASS_IKONA.jpg";
	public static final String BTN_PASS_MUS_ICON = "Ikonice/PASS_IKONA_MUS.jpg";
	
	public static final String CARD_BACK_1_H = "OkrenuteKarte/HORIZONTALNO_1.jpg";
	public static final String CARD_BACK_2_H = "OkrenuteKarte/HORIZONTALNO_2.jpg";
	public static final String CARD_BACK_3_H = "OkrenuteKarte/HORIZONTALNO_3.jpg";
	public static final String CARD_BACK_4_H = "OkrenuteKarte/HORIZONTALNO_4.jpg";
	public static final String CARD_BACK_5_H = "OkrenuteKarte/HORIZONTALNO_5.jpg";
	public static final String CARD_BACK_6_H = "OkrenuteKarte/HORIZONTALNO_6.jpg";
	public static final String CARD_BACK_7_H = "OkrenuteKarte/HORIZONTALNO_7.jpg";
	public static final String CARD_BACK_8_H = "OkrenuteKarte/HORIZONTALNO_8.jpg";
	
	public static final String CARD_BACK_1_V = "OkrenuteKarte/VERTIKALNO_1.jpg";
	public static final String CARD_BACK_2_V = "OkrenuteKarte/VERTIKALNO_2.jpg";
	public static final String CARD_BACK_3_V = "OkrenuteKarte/VERTIKALNO_3.jpg";
	public static final String CARD_BACK_4_V = "OkrenuteKarte/VERTIKALNO_4.jpg";
	public static final String CARD_BACK_5_V = "OkrenuteKarte/VERTIKALNO_5.jpg";
	public static final String CARD_BACK_6_V = "OkrenuteKarte/VERTIKALNO_6.jpg";
	public static final String CARD_BACK_7_V = "OkrenuteKarte/VERTIKALNO_7.jpg";
	public static final String CARD_BACK_8_V = "OkrenuteKarte/VERTIKALNO_8.jpg";
	
	//WELCOME STATE BUTTONS
	public static final int BTN_PLAY_GAME_WIDTH = 250;
	public static final int BTN_PLAY_GAME_HEIGHT = 35;
	public static final int BTN_QUIT_GAME_WIDTH = 250;
	public static final int BTN_QUIT_GAME_HEIGHT = 35;
	
	//PLAY BUTTON
	public static final int BTN_PLAY_GAME_TOPLEFT_X = 305;
	public static final int BTN_PLAY_GAME_TOPLEFT_Y = 330;
	
	public static final int BTN_PLAY_GAME_TOPRIGHT_X = 555;
	public static final int BTN_PLAY_GAME_TOPRIGHT_Y = 330;
	
	public static final int BTN_PLAY_GAME_BOTTOMLEFT_X = 305;
	public static final int BTN_PLAY_GAME_BOTTOMLEFT_Y = 365;
	
	public static final int BTN_PLAY_GAME_BOTTOMRIGHT_X = 555;
	public static final int BTN_PLAY_GAME_BOTTOMRIGHT_Y = 365;
	//GOTOV PLAY BUTTON
	
	//QUIT BUTTON
		public static final int BTN_QUIT_GAME_TOPLEFT_X = 305;
		public static final int BTN_QUIT_GAME_TOPLEFT_Y = 380;
		
		public static final int BTN_QUIT_GAME_TOPRIGHT_X = 555;
		public static final int BTN_QUIT_GAME_TOPRIGHT_Y = 380;
		
		public static final int BTN_QUIT_GAME_BOTTOMLEFT_X = 305;
		public static final int BTN_QUIT_GAME_BOTTOMLEFT_Y = 415;
		
		public static final int BTN_QUIT_GAME_BOTTOMRIGHT_X = 555;
		public static final int BTN_QUIT_GAME_BOTTOMRIGHT_Y = 415;
	//GOTOV QUIT BUTTON
	
	
	//GOTOV WELCOME STATE BUTTONS
	
	//WELCOME STATE IMAGES
	public static final String START_GAME_BUTTON = "WelcomeStateDugmici/welcomeStart.png";
	public static final String QUIT_GAME_BUTTON = "WelcomeStateDugmici/welcomeQuit.png";
	public static final String WELCOME_BACKGROUND = "WelcomeStateDugmici/welcomeBackground.png";	
	//GOTOV WELCOME STATE IMAGES
	public static final int CARD_BACK_H_WIDTH = 153;
	public static final int CARD_BACK_H_HEIGHT = 120;
	public static final int CARD_BACK_V_WIDTH = 120;
	public static final int CARD_BACK_V_HEIGHT = 153;
	
	public static final String CHOSEN_LIST_ADUT = "Ikonice/IzabraniAdut/LIST_ADUT.jpg";
	public static final String CHOSEN_SRCE_ADUT = "Ikonice/IzabraniAdut/SRCE_ADUT.jpg";
	public static final String CHOSEN_TIKVA_ADUT = "Ikonice/IzabraniAdut/TIKVA_ADUT.jpg";
	public static final String CHOSEN_ZIR_ADUT = "Ikonice/IzabraniAdut/ZIR_ADUT.jpg";
	public static final String CHOSEN_NO_ADUT = "Ikonice/IzabraniAdut/NO_ADUT.jpg";
	
	public static final int CHOSEN_ADUT_ICON_WIDTH = 65;
	public static final int CHOSEN_ADUT_ICON_HEIGHT = 65;
	
	public static final int BTN_ICON_WIDTH = 35;
	public static final int BTN_ICON_HEIGHT = 34;
	
	public static final int BTN_PASS_WIDTH = 70;
	public static final int BTN_PASS_HEIGHT = 34;
	public static final int BTN_PASS_MUS_WIDTH = 70;
	public static final int BTN_PASS_MUS_HEIGHT = 34;
	
	public static final int BTN_PICK_ADUT_WIDTH = 300;
	public static final int BTN_PICK_ADUT_HEIGHT = 160;
	public static final int BTN_SHOW_RES_WIDTH = 150;
	public static final int BTN_SHOW_RES_HEIGHT = 50;
	public static final int BTN_RESET_WIDTH = 150;
	public static final int BTN_RESET_HEIGHT = 50;
	public static final int CARD_WIDTH = 60;
	public static final int CARD_HEIGHT = 120;
	public static final int WINDOW_WIDTH= 850;
	public static final int WINDOW_HEIGHT = 600;
	public static final int CARD_OFFSET_X = 200;
	public static final int BTN_OFFSET = 10;
	public static final int CARD_OFFSET_Y = 10;
	public static final int CARD_INCREMENT_OFFSET = 10;
	
	
	//PUTANJE
	public static final String KARTE_PUTANJA = "Karte/";
	public static final String DUGMICI_PUTANJA = "Dugmici/";
	
	
	//CARD SPEC
	public static final int CARD_TOPLEFT_X = 0;
	public static final int CARD_TOPLEFT_Y = WINDOW_HEIGHT - CARD_HEIGHT - CARD_OFFSET_Y;
	
	public static final int CARD_TOPRIGHT_X = CARD_WIDTH;
	public static final int CARD_TOPRIGHT_Y = WINDOW_HEIGHT - CARD_HEIGHT - CARD_OFFSET_Y;
	
	public static final int CARD_BOTTOMLEFT_X = 0;
	public static final int CARD_BOTTOMLEFT_Y = WINDOW_HEIGHT - CARD_OFFSET_Y;
	
	public static final int CARD_BOTTOMRIGHT_X = CARD_WIDTH;
	public static final int CARD_BOTTOMRIGHT_Y = WINDOW_HEIGHT - CARD_OFFSET_Y;
	
	//RESET DUGME SPEC
	public static final int BTN_RESET_TOPLEFT_X = 0;
	public static final int BTN_RESET_TOPLEFT_Y = WINDOW_HEIGHT-BTN_RESET_HEIGHT-10;
	
	public static final int BTN_RESET_BOTTOMLEFT_X = 0;
	public static final int BTN_RESET_BOTTOMLEFT_Y = WINDOW_HEIGHT - 10;
	
	public static final int BTN_RESET_TOPRIGHT_X = BTN_RESET_WIDTH;
	public static final int BTN_RESET_TOPRIGHT_Y = WINDOW_HEIGHT-BTN_RESET_HEIGHT-10; 
	
	public static final int BTN_RESET_BOTTOMRIGHT_X = BTN_RESET_WIDTH;
	public static final int BTN_RESET_BOTTOMRIGTH_Y = WINDOW_HEIGHT - 10;
	
	//SHOW RES SPEC
	public static final int BTN_SHOW_RES_TOPLEFT_X = 0;
	public static final int BTN_SHOW_RES_TOPLEFT_Y = WINDOW_HEIGHT - 2*BTN_OFFSET - BTN_RESET_HEIGHT - BTN_SHOW_RES_HEIGHT;
	
	public static final int BTN_SHOW_RES_TOPRIGHT_X = BTN_SHOW_RES_WIDTH;
	public static final int BTN_SHOW_RES_TOPRIGHT_Y = WINDOW_HEIGHT - 2*BTN_OFFSET - BTN_RESET_HEIGHT - BTN_SHOW_RES_HEIGHT;
	
	public static final int BTN_SHOW_RES_BOTTOMLEFT_X = 0;
	public static final int BTN_SHOW_RES_BOTTOMLEFT_Y = WINDOW_HEIGHT - 2*BTN_OFFSET;
	
	public static final int BTN_SHOW_RES_BOTTOMRIGHT_X = BTN_SHOW_RES_WIDTH;
	public static final int BTN_SHOW_RES_BOTTOMRIGHT_Y = WINDOW_HEIGHT - 2*BTN_OFFSET;
	
	//PICK ADUT SPEC
	
	public static final int BTN_PICK_ADUT_TOPLEFT_X = WINDOW_WIDTH/2 - BTN_PICK_ADUT_WIDTH/2;
	public static final int BTN_PICK_ADUT_TOPLEFT_Y = WINDOW_HEIGHT/2 - BTN_PICK_ADUT_HEIGHT/2;
	
	public static final int BTN_PICK_ADUT_TOPRIGHT_X = WINDOW_WIDTH/2 + BTN_PICK_ADUT_WIDTH/2;
	public static final int BTN_PICK_ADUT_TOPRIGHT_Y = WINDOW_HEIGHT/2 - BTN_PICK_ADUT_HEIGHT/2;
	
	public static final int BTN_PICK_ADUT_BOTTOMLEFT_X = WINDOW_WIDTH/2 - BTN_PICK_ADUT_WIDTH/2;
	public static final int BTN_PICK_ADUT_BOTTOMLEFT_Y = WINDOW_HEIGHT/2 + BTN_PICK_ADUT_HEIGHT/2;
	
	public static final int BTN_PICK_ADUT_BOTTOMRIGHT_X = WINDOW_WIDTH/2 + BTN_PICK_ADUT_WIDTH/2;
	public static final int BTN_PICK_ADUT_BOTTOMRIGHT_Y = WINDOW_HEIGHT/2 + BTN_PICK_ADUT_HEIGHT/2;
	
	
	
	//ZIR,LIST,SRCE,TIKVA Y
	public static final int OFF = 10;
	//ZIR
	public static final int BTN_ZIR_X_OFFSET = ((BTN_PICK_ADUT_WIDTH/2)/5)*4 - BTN_ICON_WIDTH*1-10 + OFF*3;
	
	public static final int BTN_ZIR_TOPLEFT_X = WINDOW_WIDTH/2 - BTN_ICON_WIDTH/2 - BTN_ZIR_X_OFFSET;
	public static final int BTN_ZIR_BOTTOMLEFT_X = WINDOW_WIDTH/2 - BTN_ICON_WIDTH/2-BTN_ZIR_X_OFFSET;
	
	public static final int BTN_ZIR_BOTTOMRIGHT_Y = WINDOW_HEIGHT/2 + BTN_ICON_HEIGHT/2;
	public static final int BTN_ZIR_BOTTOMLEFT_Y = WINDOW_HEIGHT/2 + BTN_ICON_HEIGHT/2;
	
	public static final int BTN_ZIR_TOPLEFT_Y = WINDOW_HEIGHT/2 - BTN_ICON_HEIGHT/2;
	public static final int BTN_ZIR_TOPRIGHT_Y = WINDOW_HEIGHT/2 - BTN_ICON_HEIGHT/2;
	
	public static final int BTN_ZIR_TOPRIGHT_X = WINDOW_WIDTH/2 + BTN_ICON_WIDTH/2-BTN_ZIR_X_OFFSET;
	public static final int BTN_ZIR_BOTTOMRIGHT_X = WINDOW_WIDTH/2 + BTN_ICON_WIDTH/2-BTN_ZIR_X_OFFSET;
	
	///LIST
	public static final int BTN_LIST_X_OFFSET = ((BTN_PICK_ADUT_WIDTH/2)/5)*4- BTN_ICON_WIDTH*2-20 + OFF*3;
	
	public static final int BTN_LIST_TOPLEFT_X = WINDOW_WIDTH/2 - BTN_ICON_WIDTH/2 - BTN_LIST_X_OFFSET;
	public static final int BTN_LIST_BOTTOMLEFT_X = WINDOW_WIDTH/2 - BTN_ICON_WIDTH/2-BTN_LIST_X_OFFSET;
	
	public static final int BTN_LIST_TOPRIGHT_X = WINDOW_WIDTH/2 + BTN_ICON_WIDTH/2-BTN_LIST_X_OFFSET;
	public static final int BTN_LIST_BOTTOMRIGHT_X = WINDOW_WIDTH/2 + BTN_ICON_WIDTH/2-BTN_LIST_X_OFFSET;
	
	public static final int BTN_LIST_TOPLEFT_Y = WINDOW_HEIGHT/2 - BTN_ICON_HEIGHT/2;
	public static final int BTN_LIST_TOPRIGHT_Y = WINDOW_HEIGHT/2 - BTN_ICON_HEIGHT/2;
	
	public static final int BTN_LIST_BOTTOMRIGHT_Y = WINDOW_HEIGHT/2 + BTN_ICON_HEIGHT/2;
	public static final int BTN_LIST_BOTTOMLEFT_Y = WINDOW_HEIGHT/2 + BTN_ICON_HEIGHT/2;
	
	///SRCE
	public static final int BTN_SRCE_X_OFFSET = ((BTN_PICK_ADUT_WIDTH/2)/5)*4- BTN_ICON_WIDTH*3-30 + OFF*3;
	
	public static final int BTN_SRCE_TOPLEFT_X = WINDOW_WIDTH/2 - BTN_ICON_WIDTH/2 - BTN_SRCE_X_OFFSET;
	public static final int BTN_SRCE_BOTTOMLEFT_X = WINDOW_WIDTH/2 - BTN_ICON_WIDTH/2-BTN_SRCE_X_OFFSET;
	
	public static final int BTN_SRCE_TOPRIGHT_X = WINDOW_WIDTH/2 + BTN_ICON_WIDTH/2-BTN_SRCE_X_OFFSET;
	public static final int BTN_SRCE_BOTTOMRIGHT_X = WINDOW_WIDTH/2 + BTN_ICON_WIDTH/2-BTN_SRCE_X_OFFSET;
	
	public static final int BTN_SRCE_TOPRIGHT_Y = WINDOW_HEIGHT/2 - BTN_ICON_HEIGHT/2;
	public static final int BTN_SRCE_TOPLEFT_Y = WINDOW_HEIGHT/2 - BTN_ICON_HEIGHT/2;
	
	public static final int BTN_SRCE_BOTTOMRIGHT_Y = WINDOW_HEIGHT/2 + BTN_ICON_HEIGHT/2;
	public static final int BTN_SRCE_BOTTOMLEFT_Y = WINDOW_HEIGHT/2 + BTN_ICON_HEIGHT/2;

	///TIKVA
	public static final int BTN_TIKVA_X_OFFSET = ((BTN_PICK_ADUT_WIDTH/2)/5)*4- BTN_ICON_WIDTH*4-40 + OFF*3;
	public static final int BTN_TIKVA_TOPLEFT_X = WINDOW_WIDTH/2 - BTN_ICON_WIDTH/2 - BTN_TIKVA_X_OFFSET;
	public static final int BTN_TIKVA_BOTTOMLEFT_X = WINDOW_WIDTH/2 - BTN_ICON_WIDTH/2-BTN_TIKVA_X_OFFSET;
	
	public static final int BTN_TIKVA_TOPRIGHT_X = WINDOW_WIDTH/2 + BTN_ICON_WIDTH/2-BTN_TIKVA_X_OFFSET;
	public static final int BTN_TIKVA_BOTTOMRIGHT_X = WINDOW_WIDTH/2 + BTN_ICON_WIDTH/2-BTN_TIKVA_X_OFFSET;
	
	public static final int BTN_TIKVA_TOPLEFT_Y = WINDOW_HEIGHT/2 - BTN_ICON_HEIGHT/2;
	public static final int BTN_TIKVA_TOPRIGHT_Y = WINDOW_HEIGHT/2 - BTN_ICON_HEIGHT/2;
	
	public static final int BTN_TIKVA_BOTTOMRIGHT_Y = WINDOW_HEIGHT/2 + BTN_ICON_HEIGHT/2;
	public static final int BTN_TIKVA_BOTTOMLEFT_Y = WINDOW_HEIGHT/2 + BTN_ICON_HEIGHT/2;
	
	//PASS BUTTON SPEC
	public static final int BTN_PASS_X_OFFSET = ((BTN_PICK_ADUT_WIDTH/2)/5)*4- BTN_PASS_WIDTH*3-30 + OFF*3;
	
	public static final int BTN_PASS_TOPLEFT_X = WINDOW_WIDTH/2 - BTN_PASS_WIDTH/2 - BTN_PASS_X_OFFSET;
	public static final int BTN_PASS_BOTTOMLEFT_X = WINDOW_WIDTH/2 - BTN_PASS_WIDTH/2-BTN_PASS_X_OFFSET;
	
	public static final int BTN_PASS_TOPRIGHT_X = WINDOW_WIDTH/2 + BTN_PASS_WIDTH/2-BTN_PASS_X_OFFSET;
	public static final int BTN_PASS_BOTTOMRIGHT_X = WINDOW_WIDTH/2 + BTN_PASS_WIDTH/2-BTN_PASS_X_OFFSET;
	
	public static final int BTN_PASS_TOPRIGHT_Y = WINDOW_HEIGHT/2 - BTN_PASS_HEIGHT/2;
	public static final int BTN_PASS_TOPLEFT_Y = WINDOW_HEIGHT/2 - BTN_PASS_HEIGHT/2;
	
	public static final int BTN_PASS_BOTTOMRIGHT_Y = WINDOW_HEIGHT/2 + BTN_PASS_HEIGHT/2;
	public static final int BTN_PASS_BOTTOMLEFT_Y = WINDOW_HEIGHT/2 + BTN_PASS_HEIGHT/2;
	
	//PASS BUTTON MUS SPEC
	public static final int BTN_PASS_MUS_X_OFFSET = ((BTN_PICK_ADUT_WIDTH/2)/5)*4- BTN_PASS_MUS_WIDTH*3-30 + OFF*3;
	
	public static final int BTN_PASS_MUS_TOPLEFT_X = WINDOW_WIDTH/2 - BTN_PASS_MUS_WIDTH/2 - BTN_PASS_MUS_X_OFFSET;
	public static final int BTN_PASS_MUS_BOTTOMLEFT_X = WINDOW_WIDTH/2 + BTN_PASS_MUS_WIDTH/2-BTN_PASS_MUS_X_OFFSET;
	
	public static final int BTN_PASS_MUS_TOPRIGHT_X = WINDOW_WIDTH/2 + BTN_PASS_MUS_WIDTH/2-BTN_PASS_MUS_X_OFFSET;
	public static final int BTN_PASS_MUS_BOTTOMRIGHT_X = WINDOW_WIDTH/2 - BTN_PASS_MUS_WIDTH/2-BTN_PASS_MUS_X_OFFSET;
	
	public static final int BTN_PASS_MUS_TOPLEFT_Y = WINDOW_HEIGHT/2 - BTN_PASS_MUS_HEIGHT/2;
	public static final int BTN_PASS_MUS_TOPRIGHT_Y = WINDOW_HEIGHT/2 - BTN_PASS_MUS_HEIGHT/2;

	public static final int BTN_PASS_MUS_BOTTOMRIGHT_Y = WINDOW_HEIGHT/2 + BTN_PASS_MUS_HEIGHT/2;
	public static final int BTN_PASS_MUS_BOTTOMLEFT_Y = WINDOW_HEIGHT/2 + BTN_PASS_MUS_HEIGHT/2;
}
