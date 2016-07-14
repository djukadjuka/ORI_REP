package mainPackage.mainClasses.gameStatePackage;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import mainPackage.MainClass;
import mainPackage.mainClasses.Flags;
import mainPackage.mainClasses.supportPackage.PressableRectangle;

public class WelcomeState extends BasicGameState{
	
	private int gameStateID;
	
	
	private Image playGame;
	private Image exitGame;
	private Image backgroundImage;
	
	private PressableRectangle playButton;
	private PressableRectangle exitButton;
	
	
    public WelcomeState(int id) {
    	gameStateID = id;
    }
	

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		playGame = new Image(Flags.START_GAME_BUTTON);
		exitGame = new Image(Flags.QUIT_GAME_BUTTON);
		backgroundImage = new Image(Flags.WELCOME_BACKGROUND);
		playButton = new PressableRectangle(new Point(Flags.BTN_PLAY_GAME_TOPLEFT_X,Flags.BTN_PLAY_GAME_TOPLEFT_Y),
				 new Point(Flags.BTN_PLAY_GAME_TOPRIGHT_X,Flags.BTN_PLAY_GAME_TOPRIGHT_Y),
				 new Point(Flags.BTN_PLAY_GAME_BOTTOMLEFT_X,Flags.BTN_PLAY_GAME_BOTTOMLEFT_Y),
				 new Point(Flags.BTN_PLAY_GAME_BOTTOMRIGHT_X,Flags.BTN_PLAY_GAME_BOTTOMRIGHT_X),playGame);
		exitButton = new PressableRectangle(new Point(Flags.BTN_QUIT_GAME_TOPLEFT_X,Flags.BTN_QUIT_GAME_TOPLEFT_Y),
				 new Point(Flags.BTN_QUIT_GAME_TOPRIGHT_X,Flags.BTN_QUIT_GAME_TOPRIGHT_Y),
				 new Point(Flags.BTN_QUIT_GAME_BOTTOMLEFT_X,Flags.BTN_QUIT_GAME_BOTTOMLEFT_Y),
				 new Point(Flags.BTN_QUIT_GAME_BOTTOMRIGHT_X,Flags.BTN_QUIT_GAME_BOTTOMRIGHT_X),exitGame);
		
	
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);
		
		g.drawImage(playButton.getImage(), playButton.getTopLeft().getX(), playButton.getTopLeft().getY());
		g.drawImage(exitButton.getImage(), exitButton.getTopLeft().getX(), exitButton.getTopLeft().getY());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		
		Input inp = gc.getInput();
		
		int posX = inp.getMouseX();
		int posY = inp.getMouseY();
		
		if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(playButton.isPressedPQ(posX, posY)){
				sbg.enterState(MainClass.MENU);
			}else if(exitButton.isPressedPQ(posX, posY)){
				gc.exit();
			}
		}
	
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return gameStateID;
	}

}
