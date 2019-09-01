package vanlanduyt.java.gamestatemanager;

import java.util.ArrayList;

import vanlanduyt.java.game.Game;
import vanlanduyt.java.game.InputHandler;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;
	private InputHandler input;
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
//		gameStates.add(new Level1State(this));
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update(Game.input);
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
//	public void keyPressed(int k) {
//		gameStates.get(currentState).keyPressed(k);
//	}
	
//	public void keyReleased(int k) {
//		gameStates.get(currentState).keyReleased(k);
//	}
}
