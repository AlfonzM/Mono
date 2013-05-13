package game;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.tiled.TiledMap;

public class Level {
	
	public TiledMap map;
	public int startX, startY;
	boolean[][] blocked;
	
	public Level(int level) throws SlickException{
		map = new TiledMap("res/levels/" + level + ".tmx");
		blocked = new boolean[map.getWidth()][map.getHeight()];
		
		for(int x = 0; x < map.getHeight(); x++){
			for(int y = 0; y < map.getWidth(); y++){
				int tileID = map.getTileId(y, x, 0);
				int colorTileID = map.getTileId(y, x, 1);
				
				if("true".equals(map.getTileProperty(tileID, "blocked", "false")) || "true".equals(map.getTileProperty(colorTileID, "blocked", "false")))
					blocked[y][x] = true;
				
				String property = map.getTileProperty(colorTileID, "type", "none");
				if("powerup".equals(property)){
					Play.pickables.add(new Pickable(map.getTileProperty(colorTileID, "color", "none"), y ,x));
				}
				else if("exit".equals(property)){
					Play.pickables.add(new Pickable("exit", y, x));
				}
			}
		}
		
		switch(level){
		case 1:
			startX = 13;
			startY = 12;
			break;
		
		case 2:
			startX = 5;
			startY = 13;
			break;
			
		case 3:
			startX = 6;
			startY = 14;
			break;
			
		case 4:
			startX = 1;
			startY = 3;
			break;
			
		case 5:
			startX = 21;
			startY = 7;
			break;
			
		case 6:
			startX = 17;
			startY = 4;
			Play.mobs.add(new Mob(27, 17, 12, 27, 3));
			break;
			
		case 7:
			startX = 15;
			startY = 7;
			Play.mobs.add(new Mob(14, 18, 5, 21, 3));
			break;
			
		case 8:
			startX = 26;
			startY = 5;
			Play.mobs.add(new Mob(36, 3, 7, 39, 3.2f));
			break;
			
		case 9:
			startX = 4;
			startY = 3;
			Play.mobs.add(new Mob(12, 9, 0, 39, 3));
			Play.mobs.add(new Mob(22, 17, 0, 39, 3));
			Play.mobs.add(new Mob(0, 25, 0, 39, 5));
			break;
			
		case 10:
			startX = 19;
			startY = 3;
			Play.mobs.add(new Mob(1, 11, 1, 13, 3));
			Play.mobs.add(new Mob(23, 11, 13, 25, 3));
			Play.mobs.add(new Mob(30, 11, 26, 38, 3));
			break;
		}
	}
	
	public void destroyColors(String color){
		ArrayList<Point> blocks = new ArrayList<Point>();
		
		for(int x = 0; x < map.getHeight(); x++){
			for(int y = 0; y < map.getWidth(); y++){
				int tileID = map.getTileId(y, x, 1);
				
				String col = map.getTileProperty(tileID, "color", "none");
				
				if(col.equals(color)){
					blocks.add(new Point(x, y));
					map.setTileId(y, x, 1, 7);
					blocked[y][x] = false;
				}
			}
		}
	}
}
