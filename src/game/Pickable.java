package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Pickable {
	int x, y;
	String value;
	boolean exit;
	
	Animation animation;
	
	public Pickable(String value, int x, int y) throws SlickException{
		this.x = x * Play.TILESIZE;
		this.y = y * Play.TILESIZE;
		this.value = value;
		
		if(!value.equals("exit")){
			Image[] pSprites = new Image[8];
			Image spriteSheet = new Image("res/powerups/" + value + ".png");
			for(int i = 0 ; i < pSprites.length; i++){
				pSprites[i] = spriteSheet.getSubImage(i*20, 0, 20, 20);
			}
			
			int a = 100, b = 150;
			
			animation = new Animation(pSprites, new int[] { a,a,a,a,b,b,b,b } , true);			
		}
		else{
			Image[] pSprites = new Image[2];
			pSprites[0] = new Image("res/TP1.png");
			pSprites[1] = new Image("res/TP2.png");
			
			animation = new Animation(pSprites, new int[] { 300, 300 } , true);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, Play.TILESIZE, Play.TILESIZE);
	}
}