package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Character {
	Animation rRun, lRun;
	int[] durationRun = { 100, 100, 100, 100 };
	public char direction;
	public float x, y;
	public float speed;
	
	public final int MAXJUMP = 15;
	public final int gravitySpeed = 6;
	public int jumpHeight;
	
	Image sprite;
	Animation animation;
	
	boolean jumping, alive;
	
	public Character(int startX, int startY) throws SlickException{			
		x = Play.TILESIZE * startX;
		y = Play.TILESIZE * startY;
		alive = true;
	}
	
	public void move(int dir){
		// dir
		// 0 = up, 1 = down, 2 = left, 3 = right
		
		switch(dir){
		case 0: // up
			y -= speed;
			break;
			
		case 1: // down
			y += speed;
			break;
			
		case 2: // left
			direction = 'l';
			if(!isBlocked(x - speed, y))
				x -= speed;
			break;
			
		case 3: // right
			direction = 'r';
			if(!isBlocked(x + speed + sprite.getWidth(), y))
				x += speed;
			break;
		}
	}
	
	public boolean isBlocked(float newX, float newY){
		boolean isblocked = false;
		int xBlock = (int)newX / Play.TILESIZE;
		int yBlock = (int)newY / Play.TILESIZE;
		
		Rectangle solidBlock = null;
		Rectangle pBounds = getBounds();
		
		solidBlock = new Rectangle(xBlock * 20, yBlock * 20, Play.TILESIZE, Play.TILESIZE);
		
		if(newX > Game.GWIDTH - sprite.getWidth()/3 || newX < 0){
			isblocked = true;
		}
		else{
			for(int i = 0; i < 4; i++){
				if(Play.level.blocked[xBlock][yBlock+i] && pBounds.intersects(solidBlock))
					isblocked = true;
			}
		}
		
		return isblocked;
	}
	
	public boolean onGround(){
		boolean onground = false;
		int xBlocks[] = new int[3];
		int yBlock = (int) (y + sprite.getHeight()) / Play.TILESIZE;
		
		xBlocks[0] = (int) x / Play.TILESIZE;
		xBlocks[1] = (int) (x + sprite.getWidth()/2 ) / Play.TILESIZE;
		xBlocks[2] = (int) (x + sprite.getWidth()) / Play.TILESIZE;
		
		for(int i = 0 ; i < xBlocks.length; i++){
			if(Play.level.blocked[xBlocks[i]][yBlock])
				onground = true;
		}
		
		return onground;
	}
	
	public void gravity(){
		if(jumping){
			if(jumpHeight < MAXJUMP && !isBlocked(x, y - 5)){
				if(y > 0){
					jumpHeight += 1;
					y -= 5;
				}
				else{
					jumpHeight = 0;
					jumping = false;	
				}
			}
			else{
				jumpHeight = 0;
				jumping = false;
			}
		}
		else if(!onGround()){
			if(y + gravitySpeed + sprite.getHeight() < Game.GHEIGHT)
				y += gravitySpeed;
			else{ // die
				alive = false;
				Sounds.die.play();
			}
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(this.x, this.y, sprite.getWidth(), sprite.getHeight());
	}

}
