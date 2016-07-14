package mainPackage;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.gameStatePackage.MainState;
import mainPackage.mainClasses.gameStatePackage.WelcomeState;

public class MainClass extends StateBasedGame{
	
	private static final String GAME_NAME = "Belot ORI 2016";
	public static final int MENU = 1;
	public static final int WELCOME = 0;
	
	public MainClass(String name) throws SlickException {
		super(name);
		this.addState(new WelcomeState(WELCOME));
		this.addState(new MainState(MENU));
	}
	
	public static void main(String ada[]){
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new MainClass(GAME_NAME));
			//appgc.setFullscreen(true);
			appgc.setDisplayMode(Flags.WINDOW_WIDTH,Flags.WINDOW_HEIGHT, false);
			appgc.setShowFPS(false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(WELCOME).init(gc, this);
		this.getState(MENU).init(gc, this);
		this.enterState(MENU);
	}
}