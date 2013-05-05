package game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {
	public static Sound jump, pup1, die, exit, bleep;
	
	public Sounds() throws SlickException{
		jump = new Sound("res/sound/jump.wav");
		pup1 = new Sound("res/sound/pup1.wav");
		die = new Sound("res/sound/die.wav");
		exit = new Sound("res/sound/exit.wav");
		bleep = new Sound("res/sound/bleep.wav");
	}
}
