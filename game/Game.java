/* started summer of 2019 by Steven Van Landuyt at a mole vs mower type game
 * changes to make it a pixel game were just before this edition. 
 */



package vanlanduyt.java.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import vanlanduyt.java.game.entities.Mower;
import vanlanduyt.java.game.entities.Player;
import vanlanduyt.java.game.gfx.Colors;
import vanlanduyt.java.game.gfx.Font;
import vanlanduyt.java.game.gfx.MowerBooms;
import vanlanduyt.java.game.gfx.Screen;
import vanlanduyt.java.game.gfx.SpriteSheet;
import vanlanduyt.java.game.level.Level;
import vanlanduyt.java.game.level.tiles.Tile;
import vanlanduyt.java.gamestatemanager.GameState;
import vanlanduyt.java.gamestatemanager.GameStateManager;
import vanlanduyt.java.gamestatemanager.MenuState;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 360;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Pixle Blast"; 
	public static final double VERSION_NUM = 2.11; 
	
	private JFrame frame;
	public boolean running = false;
	public static int tickCount = 0;
	public static int frames = 0;
	public static InputHandler input;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colors = new int[6 * 6 * 6];
	private Graphics2D g;
	private GameStateManager gsm;

	private Screen screen;
	public Level level;
	
	
	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); 
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}
	
	public void init() {

		gsm = new GameStateManager();
		g = (Graphics2D) image.getGraphics();
		
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					
					colors[index++] = rr << 16 | gg  << 8 | bb;
				}
			}
		}
		
		
		//loading the graphics sheet
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png"));
		input = new InputHandler(this);
		//loading the background
		level = new Level("/levels/level2.png");
	}	

	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	
	public synchronized void stop() {
		running = false;
	}
	
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0; 
		
		init();
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			
			while (delta >= 1) {
				ticks++;	
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (shouldRender) {
				frames++;
//				render();
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;			
				System.out.println(ticks + " ticks, " + frames + " frames");
//				System.out.println("SCORE: " + level.player.score + ", HEALTH: " + level.player.health);
				frames = 0;
				ticks = 0;
			}	
		}	
	}

	
	public void tick() {
		tickCount++;
		update();
		draw();
		drawToScreen();
//		level.tick();

	}
	
	private void update() {
		gsm.update();
	}
	
	private void draw() {
		gsm.draw(g);
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image,  0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy(); 
		if (bs == null) {
			createBufferStrategy(3); 
			return;
		}
		
		int xOffset = level.player.x - (screen.width / 2);
		int yOffset = level.player.y - (screen.height / 2);
		
		level.renderTiles(screen, xOffset, yOffset);
		
		for (int x = 0; x < level.width; x++) {
			int color = Colors.get(-1, -1, -1, 000);
			if (x % 10 == 0 && x!= 0) {
				color = Colors.get(-1, -1, -1, 500);
			}
			Font.render((x % 10) + "", screen, 0 + (x * 8), 0, color, 0x00);
		}
		
		level.renderEntities(screen);
		
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255) pixels[x + y * WIDTH] = colors[colorCode];
			}
		}
		
		level.renderMowers(screen);
		
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255) pixels[x + y * WIDTH] = colors[colorCode];
			}
		}
		
		level.renderBooms(screen);
		
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255) pixels[x + y * WIDTH] = colors[colorCode];
			}
		}	
		
		level.renderShots(screen);
		
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255) pixels[x + y * WIDTH] = colors[colorCode];
			}
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game().start();
	}
}
