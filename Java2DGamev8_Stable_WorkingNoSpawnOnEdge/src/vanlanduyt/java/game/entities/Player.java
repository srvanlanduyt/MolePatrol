package vanlanduyt.java.game.entities;

import vanlanduyt.java.game.InputHandler;
import vanlanduyt.java.game.gfx.Colors;
import vanlanduyt.java.game.gfx.Screen;
import vanlanduyt.java.game.level.Level;

public class Player extends Mob{
	
	private InputHandler input;
	private int color = Colors.get(-1, 111, 145, 543);
	private int scale = 1;

	//constructor for new player
	public Player(Level level, int x, int y, InputHandler input) {		
		super(level, "Player", x, y, 1);
		this.input = input;
	}
	
	

	public int getPlayerX() {
		return x;
	}



	public int getPlayerY() {
		return y;
	}



	public void tick() {
		//setting direction values
		int xa = 0; 
		int ya = 0;
		
		//up = 0
		if (input.up.isPressed()) {
			ya--;
		}
		//down = 1 
		if (input.down.isPressed()) {
			ya++;
		}
		//left = 2
		if (input.left.isPressed()) {
			xa--;
		}
		//right = 3
		if (input.right.isPressed()) {
			xa++;
		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya); 
			isMoving = true;	
		} else {
			isMoving = false;
		}
	}

	//render the Player
	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 28;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;
		
		//selecting tiles of player image
		if (movingDir == 0) {
		} else if (movingDir == 1) {
			xTile += 2;
			
		} else if (movingDir == 2) {
			flipTop = 1;
			flipBottom = 1;
			if (((numSteps >> walkingSpeed) & 1) % 2 == 0) {
				xTile += 4;
			} else {
				xTile += 6;
			}
		} else if (movingDir == 3) {
			flipTop = 0;
			flipBottom = 0;
			if (((numSteps >> walkingSpeed) & 1) % 2 == 0) {
			xTile += 4;
			} else {
				xTile += 6;
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
	
	public boolean hasCollided(int xa, int ya) {
		int xMin = 0; 
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}	
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		
		return false;
	}
}	
