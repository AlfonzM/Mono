package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Character{
	
	Animation lJump, rJump;
	Animation lIdle, rIdle;
	Image idleSpritesheet;
	int[] durationIdle = { 200, 200, 200, 200 };
	int[] durationJump = { 100, 100 };
	
	Image[] idleSprites;

	public Player(int startX, int startY) throws SlickException {
		super(startX, startY);
		
		//idle
		idleSpritesheet = new Image("res/idleMime.png");
		idleSprites = new Image[4];
		idleSprites[0] = idleSpritesheet.getSubImage(0, 0, 23, 69);
		idleSprites[1] = idleSpritesheet.getSubImage(23, 0, 23, 69);
		idleSprites[2] = idleSpritesheet.getSubImage(46, 0, 23, 69);
		idleSprites[3] = idleSpritesheet.getSubImage(69, 0, 23, 69);
		lIdle = new Animation(idleSprites, durationIdle, true);
		
		idleSprites[0] = idleSpritesheet.getSubImage(0, 69, 23, 69);
		idleSprites[1] = idleSpritesheet.getSubImage(23, 69, 23, 69);
		idleSprites[2] = idleSpritesheet.getSubImage(46, 69, 23, 69);
		idleSprites[3] = idleSpritesheet.getSubImage(69, 69, 23, 69);
		rIdle = new Animation(idleSprites, durationIdle, true);
		
		//run
		Image[] rRunSprites = new Image[4];
		rRunSprites[0] = new Image("res/run/Rrun1.png");
		rRunSprites[1] = new Image("res/run/Rrun2.png");
		rRunSprites[2] = new Image("res/run/Rrun3.png");
		rRunSprites[3] = new Image("res/run/Rrun4.png");
		rRun = new Animation(rRunSprites, durationRun, true);
		
		Image[] lRunSprites = new Image[4];
		lRunSprites[0] = new Image("res/run/Lrun1.png");
		lRunSprites[1] = new Image("res/run/Lrun2.png");
		lRunSprites[2] = new Image("res/run/Lrun3.png");
		lRunSprites[3] = new Image("res/run/Lrun4.png");
		lRun = new Animation(lRunSprites, durationRun, true);
		
		direction = 'r';
		animation = lIdle;
		
		sprite = animation.getCurrentFrame();
		
		// jump
		Image[] jumps = new Image[2];
		jumps[0] = new Image("res/Rjump.png");
		jumps[1] = new Image("res/Rjump.png");
		rJump = new Animation(jumps, durationJump, true);
		
		jumps[0] = new Image("res/Ljump.png");
		jumps[1] = new Image("res/Ljump.png");
		lJump = new Animation(jumps, durationJump, true);
		
		y -= sprite.getHeight();
		
		speed = 3;
		jumping = false;
		jumpHeight = 0;
	}
	
	@Override
	public void move(int dir){
		super.move(dir);
		
		if(dir == 3){
			animation = rRun;
		}
		else if(dir == 2){
			animation = lRun;
		}
		
		sprite = animation.getCurrentFrame();
	}
	
	public void jump(){
		Sounds.jump.play();
		jumping = true;
	}

}
