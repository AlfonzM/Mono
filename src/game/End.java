package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class End extends BasicGameState{
	private int stateID = Game.end;
	Image menu;
	Color[] bgs;
	int bgcolor;
	int counter;
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menu = new Image("res/end.png");
		bgs = new Color[5];
		bgs[0] = new Color(129, 199, 195, 0);
		bgs[1] = new Color(219, 189, 68, 0);
		bgs[2] = new Color(118, 186, 63, 0);
		bgs[3] = new Color(199, 52, 52, 0);
		bgs[4] = new Color(220, 133, 196, 0);
		
		bgcolor = 0;
		counter = 0;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(bgs[bgcolor]);
		g.drawImage(menu, 0, 0);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_SPACE)){
			Sounds.bleep.play();
			sbg.enterState(Game.menu);
		}
		
		counter+=delta;
		if(counter > 2000){
			bgcolor = (bgcolor+1) %5;
			counter = 0;
		}
		
	}

	public int getID() {
		return stateID;
	}
}