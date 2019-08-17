package vanlanduyt.java.game.entities;

import java.util.ArrayList;
import java.util.Random;

import vanlanduyt.java.game.Game;
import vanlanduyt.java.game.gfx.Colors;
import vanlanduyt.java.game.gfx.Screen;
import vanlanduyt.java.game.level.Level;
import vanlanduyt.java.game.level.tiles.Tile;


public class Mower extends Mob {

	public boolean collision = false;
	public ArrayList<Integer> mowerTrail = new ArrayList<Integer>();
    public static boolean tilesAlive = false;
	private int color = Colors.get(-1, 10, 45, 1);
	private int scale = 1; 
	private int dir;
	private int refreshSpeed = 4;
	
	

	
	//constructor for mower
	public Mower(Level level, String name, int x, int y, int speed, int dir) {
		super(level, name, x, y, speed);
		this.dir = dir;
	}
	
	public int getMowerX() {
		return x;
	}
	
	public void setMowerX(int x) {
		this.x = x;
	}
	
	public int getMowerY() {
		return y;
	}
	
	public void setMowerY(int y) {
		this.y = y;
	}
	
	public int getMowerDir() {
		return dir;
	}
	
	public void setMowerDir(int dir) {
		this.dir = dir;
	}
	
	public static int MowerDir() {
		return new Random().nextInt(4);
	}
	
	//mowers move from their origin to the first obstacle in a straight line
	public void tick() {
		
		//setting direction values
		int xa = 0; 
		int ya = 0;
		
		{
			if (dir == 0) {
					ya--;
			}
			if (dir == 1) {
					ya++;
			}	
			if (dir == 2) {
					xa--;
				}	
			if (dir == 3) {
					xa++;
			}	
		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya); 
			isMoving = true;
		} else {
			isMoving = false;
		}
		
		//make ANIMATEDGRASS into GRASS
		if (isMoving) {
			mowerTrail.add(this.getMowerX());
			mowerTrail.add(this.getMowerY());
			byte compareByte = (byte) 3;
			for (int i = 0; i < mowerTrail.size(); i += 2) {
			int tileX = mowerTrail.get(i);
			int tileY = mowerTrail.get(i + 1); 
			if (level.getTile(tileX, tileY).getId() == compareByte);
				level.alterTile(tileX, tileY, Tile.GRASS);
			}
		}
	}
	
	
	//rendering the mower
	public void render(Screen screen) {
		int xTile = 0; 
		int yTile = 26;
		int flipTop = 0;//(Game.tickCount >> refreshSpeed) & 1;
		int flipBottom = 0;//(Game.tickCount >> refreshSpeed) & 1;
				
		//selecting tiles of mower image
		//0 is up
		if (dir == 0) {
			xTile += 6;
			if (((Game.tickCount >> refreshSpeed) & 1) % 2 == 0) {// >> refreshSpeed) & 1) % 2 == 0) {
				xTile -= 2;
			}
		
		//1 is down
		} else if (dir == 1) {
			xTile += 8;
			if (((Game.tickCount >> refreshSpeed) & 1) % 2 == 0) {
				xTile += 2;
			}
		
				
		//2 is left	
		} else if (dir == 2) {
			if (((Game.tickCount >> refreshSpeed) & 1) % 2 == 0) {
				xTile += 2;
			}
			
		//3 is right	
		} else if (dir == 3) {
			flipTop = 1;
			flipBottom = 1;
			if (((Game.tickCount >> refreshSpeed) & 1) % 2 == 0) {
				xTile += 2;
			}
		}

		
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;
		
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, color, flipTop, scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, color, flipTop, scale);
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, color, flipBottom, scale);
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom, scale);
	}
	
	//collision not 100% on the mowers yet
	public boolean hasCollided(int xa, int ya) {
		
		//0 is up
		if (dir == 0) {
			
			int xMin = 0; 
			int xMax = 8;
			int yMin = 0;
			int yMax = 8;
			
			for (int x = xMin; x < xMax; x++) {
				if (isSolidTile(xa, ya, x, yMin)) {
					collision = true;
				}
			}	
			for (int x = xMin; x < xMax; x++) {
			
				if (isSolidTile(xa, ya, x, yMax)) {
					collision = true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMin, y)) {
					collision = true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMax, y)) {
					collision = true;
				}
			}
		}		
		// 1 is down
		if (dir == 1) {
			
			int xMin = 0; 
			int xMax = 10;
			int yMin = 0;
			int yMax = 4;
			
			for (int x = xMin; x < xMax; x++) {
				if (isSolidTile(xa, ya, x, yMin)) {
					collision = true;
				}
			}	
			for (int x = xMin; x < xMax; x++) {
			
				if (isSolidTile(xa, ya, x, yMax)) {
					collision = true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMin, y)) {
					collision = true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMax, y)) {
					collision = true;
				}
			}
		}
		
		//2 is left
		if (dir == 2) {
			
			int xMin = 0; 
			int xMax = 10;
			int yMin = 3;
			int yMax = 7;
			
			for (int x = xMin; x < xMax; x++) {
				if (isSolidTile(xa, ya, x, yMin)) {
					collision = true;
				}
			}	
			for (int x = xMin; x < xMax; x++) {
			
				if (isSolidTile(xa, ya, x, yMax)) {
					collision = true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMin, y)) {
					collision = true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMax, y)) {
					collision = true;
				}
			}
		}
		
		//3 is right
		if (dir == 3) {
			
			int xMin = 0; 
			int xMax = 4;
			int yMin = 3;
			int yMax = 7;
			
			for (int x = xMin; x < xMax; x++) {
				if (isSolidTile(xa, ya, x, yMin)) {
					collision = true;
				}
			}	
			for (int x = xMin; x < xMax; x++) {
			
				if (isSolidTile(xa, ya, x, yMax)) {
					collision = true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMin, y)) {
					collision = true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMax, y)) {
					collision = true;
				}
			}
		}
		return collision;
	}
}
