package vanlanduyt.java.gamestatemanager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import vanlanduyt.java.game.InputHandler;
import vanlanduyt.java.game.gfx.Background;



public class MenuState extends GameState {

	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = {"Start", "Help", "Quit"};
	private InputHandler input;
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
	
		try {
			bg = new Background("/Backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("American Typewriter", Font.PLAIN, 28);
			
			font = new Font ("Skia", Font.PLAIN, 12);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 
	
	public void init() {
	
	}

	public void update(InputHandler input) {

		this.input = input;
		
		if (input.enter.isPressed()) {
			select();
		}
		
		if (input.up.isPressed()) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		
		if (input.down.isPressed()) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}	
		bg.update();
	}

	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(Color.WHITE);
		g.fillRect(70, 40, 180, 150);
		g.setColor(Color.BLACK);
		g.drawRect(70, 40, 180, 150);
		
		g.setColor(titleColor);;
		g.setFont(titleFont);
		g.drawString("Pixel Blast", 80, 70);
		
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if ( i == currentChoice) {
				g.setColor(Color.BLACK);;
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 145, 140 + i * 15);
		}
	}
	private void select() {
		
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		
		if (currentChoice == 1) {
			
		}
		
		if (currentChoice == 2) {
			System.exit(0);
		}
	}
/*	

	public void keyPressed(int k) {
		
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	public void keyReleased(int k) {
		
	}
*/


}
