package game;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{
	
	public static int TILESIZE = 20;
	private int stateID = Game.play;
	private int currentLevel;
	private Image[] levelText;
	
	private int respawnCounter, respawnTime;
		
	public static Level level;
	public static Player player;
	
	public Image arrows, space;
	
	public static ArrayList<Pickable> pickables;
	public static ArrayList<Mob> mobs;

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		currentLevel = 1;
		initLevel(currentLevel);
		
		arrows = new Image("res/arrows.png");
		space = new Image("res/space.png");
		
		Image levelSprite = new Image("res/level.png");
		levelText = new Image[11];
		levelText[0] = levelSprite.getSubImage(0, 0, 40, 10);
		levelText[1] = levelSprite.getSubImage(0, 12, 4, 10);
		
		for(int i = 0; i < 8; i++){
			levelText[i+2] = levelSprite.getSubImage(10 * i + 6, 12, 8, 10);
		}
		
		levelText[10] = levelSprite.getSubImage(86, 12, 14, 10);
		
		respawnTime = 2000;
	}
	
	public void initLevel(int l) throws SlickException{
		if(currentLevel <= 10){
			pickables = new ArrayList<Pickable>();
			mobs = new ArrayList<Mob>();
			level = new Level(l);
			player = new Player(level.startX, level.startY);
			respawnCounter = 0;			
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		level.map.render(0,0);
		if(player.alive)
			g.drawAnimation(player.animation , player.x, player.y);
		
		g.drawImage(levelText[0], 370, 10);
		g.drawImage(levelText[currentLevel], 370 + levelText[0].getWidth() + 10, 10);
		
		if(currentLevel == 1){
			g.drawImage(arrows, 12 * TILESIZE, 16 * TILESIZE);
		}
		else if(currentLevel == 2){
			g.drawImage(space, 4 * TILESIZE, 19 * TILESIZE);
		}
		
		// loop and render mobs
		for (Iterator<Mob> iterator = mobs.iterator(); iterator.hasNext(); ) {
			Mob m = iterator.next();
			if(m.alive)
				g.drawAnimation(m.animation, m.x, m.y);
			else
				iterator.remove();
		}
		
		// loop and render picakbles
		for (Iterator<Pickable> iterator = pickables.iterator(); iterator.hasNext(); ) {
			Pickable p = iterator.next();
			g.drawAnimation(p.animation, p.x, p.y);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(player.alive){
			//movement controls
			if(input.isKeyPressed(Input.KEY_SPACE) && player.onGround()){
				player.jump();
			}
			
			player.gravity();
			
			if(input.isKeyDown(Input.KEY_LEFT)){
				player.move(2);
				
			}
			else if(input.isKeyDown(Input.KEY_RIGHT)){
				player.move(3);
			}
			else{
				player.animation = (player.direction == 'r')? player.lIdle : player.rIdle;
			}
			
			if(!player.onGround())
				player.animation = (player.direction == 'r')? player.rJump : player.lJump;
			
			player.sprite = player.animation.getCurrentFrame();
			
			if(input.isKeyPressed(Input.KEY_R)){
				initLevel(currentLevel);
			}
			
			checkCollisions();			
		}
		else{
			respawnCounter += delta;
			if(respawnCounter > respawnTime){
				initLevel(currentLevel);
			}
		}
	
		// loop all mobs
		for (Iterator<Mob> iterator = mobs.iterator(); iterator.hasNext();) {
			Mob m = iterator.next();
			m.move();
			m.gravity();
		}
		
		if(currentLevel > 10){
			currentLevel = 0;
			sbg.enterState(Game.end);
		}
	}
	
	public void checkCollisions() throws SlickException{		
		
		// loop all pickables
		for (Iterator<Pickable> iterator = pickables.iterator(); iterator.hasNext();) {
			Pickable p = iterator.next();

			if (p.getBounds().intersects(player.getBounds())) {
				if(!p.value.equals("exit")){
					level.destroyColors(p.value);
					Sounds.pup1.play();
					iterator.remove();
				}
				else{
					if(pickables.size() < 2){
						initLevel(++currentLevel);
						Sounds.exit.play();
						iterator.remove();
					}
				}
			}		
		}
		
		// loop mobs and check if collide with player
		for (Iterator<Mob> iterator = mobs.iterator(); iterator.hasNext();) {
			Mob m = iterator.next();
			
			if(m.getBounds().intersects(player.getBounds())){
				Sounds.die.play();
				player.alive = false;
			}
		}
		
	}

	public int getID() {
		return stateID;
	}

}