package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Mob extends Character{
	
	int a, b;

	public Mob(int startX, int startY, int a, int b, float speed) throws SlickException {
		super(startX, startY);
		direction = 'r';
		jumping = false;
		
		this.a = a;
		this.b = b;

		sprite = new Image("res/idleMime.png").getSubImage(0, 0, 23, 69);
		
		//run
		Image[] rRunSprites = new Image[4];
		rRunSprites[0] = new Image("res/mob/RwalkZombie1.png");
		rRunSprites[1] = new Image("res/mob/RwalkZombie2.png");
		rRunSprites[2] = new Image("res/mob/RwalkZombie3.png");
		rRunSprites[3] = new Image("res/mob/RwalkZombie4.png");
		rRun = new Animation(rRunSprites, durationRun, true);
		
		Image[] lRunSprites = new Image[4];
		lRunSprites[0] = new Image("res/mob/LwalkZombie1.png");
		lRunSprites[1] = new Image("res/mob/LwalkZombie2.png");
		lRunSprites[2] = new Image("res/mob/LwalkZombie3.png");
		lRunSprites[3] = new Image("res/mob/LwalkZombie4.png");
		lRun = new Animation(lRunSprites, durationRun, true);
		
		animation = rRun;
		
		y -= sprite.getHeight();
		this.speed = speed;
	}
	
	public void move(){
		if(direction == 'r'){
			if(x < b * Play.TILESIZE && !isBlocked(x + speed + sprite.getWidth(), y))
				super.move(3);
			else
				direction = 'l';
			
			animation = rRun;
		}
		else if (direction == 'l'){
			if(x > a * Play.TILESIZE && !isBlocked(x - speed, y))
				super.move(2);
			else
				direction = 'r';
			
			animation = lRun;
		}
		
		sprite = animation.getCurrentFrame();
	}

}
