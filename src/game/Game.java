package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	
	public static final int GWIDTH = 800,
						    GHEIGHT = 600;
	
	public static final int menu = 0;
	public static final int play = 1;
	public static final int end = 2;

	public Game() throws SlickException {
		super("Mono");
		new Sounds();
		this.addState(new Menu());
		this.addState(new End());
		this.addState(new Play());
	
		this.enterState(menu);
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer appgc = new AppGameContainer(new Game()); // make a window that holds Game
		appgc.setDisplayMode(GWIDTH, GHEIGHT, false); // width, height, fullscreen
		appgc.setTargetFrameRate(60);
		appgc.setShowFPS(false);
		appgc.start();
	}

	public void initStatesList(GameContainer gc) throws SlickException {
	}

}
