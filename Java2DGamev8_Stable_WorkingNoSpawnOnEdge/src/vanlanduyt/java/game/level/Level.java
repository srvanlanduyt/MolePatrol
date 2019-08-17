package vanlanduyt.java.game.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.imageio.ImageIO;

import vanlanduyt.java.game.Game;
import vanlanduyt.java.game.entities.Entity;
import vanlanduyt.java.game.entities.Mower;
import vanlanduyt.java.game.entities.Player;
import vanlanduyt.java.game.gfx.Booms;
import vanlanduyt.java.game.gfx.MowerBooms;
import vanlanduyt.java.game.gfx.Screen;
import vanlanduyt.java.game.level.tiles.Tile;

public class Level {
	
	private static final Tile Tile = null;
	public static byte[] tiles; 
	public int width;
	public int height;
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Mower> liveMowers = new ArrayList<Mower>();
	public Stack<Mower> spareMowers = new Stack<>();
	public List<Booms> booms = new ArrayList<Booms>();
	private String imagePath;
	private BufferedImage image;
	
	

	
	public Player player;
	public Mower mower1;
	public Mower mower2;
	public Mower mower3;
	public Mower mower4;
	public Mower mower5;
	public Mower mower6;
	public Mower mower7;
	public Mower mower8;
	public Mower mower9;
	public Mower mower10;
	public Mower mower11;
	public Mower mower12;
	public Mower mower13;
	public Mower mower14;
	public Mower mower15;
	public Mower mower16;
	public Mower mower17;
	public Mower mower18;
	public Mower mower19;
	public Mower mower20;
	public Mower mower21;
	public Mower mower22;
	public Mower mower23;
	public Mower mower24;
	public Mower mower25;
	public Mower mower26;
	public Mower mower27;
	public Mower mower28;
	public Mower mower29;
	public Mower mower30;
	public Mower mower31;
	public Mower mower32;
	public Mower mower33;
	public Mower mower34;
	public Mower mower35;
	public Mower mower36;
	public Mower mower37;
	public Mower mower38;
	public Mower mower39;
	public Mower mower40;
	public Mower mower41;
	public Mower mower42;
	public Mower mower43;
	public Mower mower44;
	public Mower mower45;
	public Mower mower46;
	public Mower mower47;
	public Mower mower48;
	public Mower mower49;
	public Mower mower50;
	
	//random generator, returns 1 - level.width-2
	public int xNum() {
		return ((int)(Math.random() * (width - 1)));
	}
	
	//random generator, returns 1 - level.height-2
	public int yNum() {	
		return ((int)(Math.random() * (height - 1)));
	}

	// The hashed out lines below load files from the res file, leaving only
	// what is needed for the mathematic level generator.
	public Level(String imagePath) {
//		if (imagePath !=null) {
//			this.imagePath = imagePath;
//			this.loadLevelFromFile();
//		} else {
			this.width = 32;
			this.height = 32;
			tiles = new byte[width * height];
			this.generateLevel();
//		}
	}
 	
//	private void loadLevelFromFile() {
//		try {
//			this.image = ImageIO.read(Level.class.getResource(this.imagePath));
//			this.width = image.getWidth();
//			this.height = image.getHeight();
//			tiles = new byte [width * height];
//			this.loadTiles();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	private void loadTiles() {
		int[] tileColors = this.image.getRGB(0, 0, width, height, null, 0, width);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileCheck: for (Tile t : Tile.tiles) {
					if (t != null && t.getLevelColor() == tileColors[x + y * width]) {
						this.tiles[x + y * width] = t.getId();
						break tileCheck;
					}
				}
			}
		}
	}
	
	
	private void saveLevelToFile() {
		try {
			ImageIO.write(image, "png", new File(Level.class.getResource(this.imagePath).getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void alterTile(int x, int y, Tile newTile) {
		this.tiles[x/8 + y/8 * width] = newTile.getId();
		if (image != null) {
			if (this.getTile(x, y).getId() == 3);
			image.setRGB(x,  y, newTile.getLevelColor());
		}
	}
	
 	//Mathematic level generator
 	public void generateLevel() {
 		for(int y = 0; y < height; y++) {
 			for(int x = 0; x < width; x++) {
 				if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
 					tiles[x + y * width] = Tile.STONE.getId(); 
 				} else if (x * y % 100 < 100) {
	 				tiles[x + y * width] = Tile.ANIMATEDGRASS.getId(); 
 				} else {
 					tiles[x + y * width] = Tile.STONE.getId(); 
 				}					
 			}
 		}
 	
		//loading the ents
		player = new Player(this, xNum() * 8, yNum() * 8, Game.input); 
				
		mower1 = new Mower(this, "Mower1", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower2 = new Mower(this, "Mower2", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower3 = new Mower(this, "Mower3", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower4 = new Mower(this, "Mower4", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower5 = new Mower(this, "Mower5", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower6 = new Mower(this, "Mower6", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower7 = new Mower(this, "Mower7", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower8 = new Mower(this, "Mower8", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower9 = new Mower(this, "Mower9", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower10 = new Mower(this, "Mower10", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower11 = new Mower(this, "Mower12", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower12 = new Mower(this, "Mower13", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower13 = new Mower(this, "Mower14", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower14 = new Mower(this, "Mower15", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower15 = new Mower(this, "Mower16", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower16 = new Mower(this, "Mower17", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower17 = new Mower(this, "Mower18", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower18 = new Mower(this, "Mower19", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower19 = new Mower(this, "Mower20", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower20 = new Mower(this, "Mower21", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower21 = new Mower(this, "Mower21", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower22 = new Mower(this, "Mower22", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower23 = new Mower(this, "Mower23", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower24 = new Mower(this, "Mower24", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower25 = new Mower(this, "Mower25", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower26 = new Mower(this, "Mower26", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower27 = new Mower(this, "Mower27", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower28 = new Mower(this, "Mower28", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower29 = new Mower(this, "Mower29", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower30 = new Mower(this, "Mower30", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower31 = new Mower(this, "Mower31", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower32 = new Mower(this, "Mower32", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower33 = new Mower(this, "Mower33", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower34 = new Mower(this, "Mower34", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower35 = new Mower(this, "Mower35", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower36 = new Mower(this, "Mower36", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower37 = new Mower(this, "Mower37", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower38 = new Mower(this, "Mower38", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower39 = new Mower(this, "Mower39", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower40 = new Mower(this, "Mower40", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower41 = new Mower(this, "Mower41", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower42 = new Mower(this, "Mower42", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower43 = new Mower(this, "Mower43", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower44 = new Mower(this, "Mower44", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower45 = new Mower(this, "Mower45", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower46 = new Mower(this, "Mower45", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		mower47 = new Mower(this, "Mower47", xNum() * 8, yNum() * 8, 1, Mower.MowerDir());
		mower48 = new Mower(this, "Mower48", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower49 = new Mower(this, "Mower49", xNum() * 8, yNum() * 8, 2, Mower.MowerDir());
		mower50 = new Mower(this, "Mower50", xNum() * 8, yNum() * 8, 1, Mower.MowerDir()); 
		
		spareMowers.push(mower1);
		spareMowers.push(mower2);
		spareMowers.push(mower3);
		spareMowers.push(mower4);
		spareMowers.push(mower5);
		spareMowers.push(mower6);
		spareMowers.push(mower7);
		spareMowers.push(mower8);
		spareMowers.push(mower9);
		spareMowers.push(mower10);
		spareMowers.push(mower11);
		spareMowers.push(mower12);
		spareMowers.push(mower13);
		spareMowers.push(mower14);
		spareMowers.push(mower15);
		spareMowers.push(mower16);
		spareMowers.push(mower17);
		spareMowers.push(mower18);
		spareMowers.push(mower19);
		spareMowers.push(mower20);
		spareMowers.push(mower21);
		spareMowers.push(mower22);
		spareMowers.push(mower23);
		spareMowers.push(mower24);
		spareMowers.push(mower25);
		spareMowers.push(mower26);
		spareMowers.push(mower27);
		spareMowers.push(mower28);
		spareMowers.push(mower29);
		spareMowers.push(mower30);
		spareMowers.push(mower31);
		spareMowers.push(mower32);
		spareMowers.push(mower33);
		spareMowers.push(mower34);
		spareMowers.push(mower35);
		spareMowers.push(mower36);
		spareMowers.push(mower37);
		spareMowers.push(mower38);
		spareMowers.push(mower39);
		spareMowers.push(mower40);
		spareMowers.push(mower41);
		spareMowers.push(mower42);
		spareMowers.push(mower43);
		spareMowers.push(mower44);
		spareMowers.push(mower45);
		spareMowers.push(mower46);
		spareMowers.push(mower47);
		spareMowers.push(mower48);
		spareMowers.push(mower49);
		spareMowers.push(mower50);

		
		
		//Starts the player in the first non-solid square
		while (this.getTile((player.getPlayerX() / 8), (player.getPlayerY() / 8)).isSolid()) {
			for (int y = player.getPlayerY(); y < this.height; y++) {
				player.y++;
				player.x = 0;
				for (int x = player.getPlayerX(); x < this.width; x++) {
					player.x++;
				}
			}
		}
	
		//starts spareMowers on non-solid tiles
		//STILL NOT WORKING 100%
		for (int i = 0; i < spareMowers.size(); i++) {	
			if (getTile((spareMowers.get(i).getMowerX() / 8), (spareMowers.get(i).getMowerY() / 8)).isSolid()) {
				spareMowers.get(i).setMowerX(20);
				spareMowers.get(i).setMowerY(20);
				System.out.println("spareMowerMadeItHappen");
			}
		}	
		
		
		
		
		addEntity(player);

			
	}
 	
	public void tick() {
		for (Entity e : entities) {
			e.tick();
		}
		for (Tile t : Tile.tiles) {
			if (t == null) {
				break;
			}
			t.tick();	
		}
		
		for (Mower m : liveMowers) {
			m.tick();
		}
		for (Booms b : booms) {
			b.tick();
		}
		
		if (liveMowers.size() < 5) {
			if (spareMowers.size() > 0) {
				addLiveMower(spareMowers.pop());
			}
		}
		
 
		if (mower1.collision) { 
			mower1.collision = false;
			MowerBooms mowerBoom = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower1.getMowerX(), mower1.getMowerY(), true);
			removeLiveMower(mower1);
			addBoom(mowerBoom);
			if (mowerBoom.isAlive == false) {
				removeBoom(mowerBoom);
			}
		}
		if (mower2.collision) {
			mower2.collision = false;
			MowerBooms mowerBoom2 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower2.getMowerX(), mower2.getMowerY(), true);
			removeLiveMower(mower2);
			addBoom(mowerBoom2);
			if (mowerBoom2.isAlive == false) {
				removeBoom(mowerBoom2);
			}
		}
		if (mower3.collision) {
			mower3.collision = false;
			MowerBooms mowerBoom3 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower3.getMowerX(), mower3.getMowerY(), true);
			removeLiveMower(mower3);
			addBoom(mowerBoom3);
			if (mowerBoom3.isAlive == false) {
				removeBoom(mowerBoom3);
			}
		}		
		if (mower4.collision) { 
			mower4.collision = false;
			MowerBooms mowerBoom4 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower4.getMowerX(), mower4.getMowerY(), true);
			removeLiveMower(mower4);
			addBoom(mowerBoom4);
			if (mowerBoom4.isAlive == false) {
				removeBoom(mowerBoom4);
			}
		}
		if (mower5.collision) {
			mower5.collision = false;
			MowerBooms mowerBoom5 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower5.getMowerX(), mower5.getMowerY(), true);
			removeLiveMower(mower5);
			addBoom(mowerBoom5);
			if (mowerBoom5.isAlive == false) {
				removeBoom(mowerBoom5);
			}
		}
		if (mower6.collision) {
			mower6.collision = false;
			MowerBooms mowerBoom6 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower6.getMowerX(), mower6.getMowerY(), true);
			removeLiveMower(mower6);
			addBoom(mowerBoom6);
			if (mowerBoom6.isAlive == false) {
				removeBoom(mowerBoom6);
			}
		}	
		if (mower7.collision) { 
			mower7.collision = false;
			MowerBooms mowerBoom7 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower7.getMowerX(), mower7.getMowerY(), true);
			removeLiveMower(mower7);
			addBoom(mowerBoom7);
			if (mowerBoom7.isAlive == false) {
				removeBoom(mowerBoom7);
			}
		}
		if (mower8.collision) {
			mower8.collision = false;
			MowerBooms mowerBoom8 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower8.getMowerX(), mower8.getMowerY(), true);
			removeLiveMower(mower8);
			addBoom(mowerBoom8);
			if (mowerBoom8.isAlive == false) {
				removeBoom(mowerBoom8);
			}
		}
		if (mower9.collision) {
			mower9.collision = false;
			MowerBooms mowerBoom9 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower9.getMowerX(), mower9.getMowerY(), true);
			removeLiveMower(mower9);
			addBoom(mowerBoom9);
			if (mowerBoom9.isAlive == false) {
				removeBoom(mowerBoom9);
			}
		}
		if (mower10.collision) {
			mower10.collision = false;
			MowerBooms mowerBoom10 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower10.getMowerX(), mower10.getMowerY(), true);
			removeLiveMower(mower10);
			addBoom(mowerBoom10);
			if (mowerBoom10.isAlive == false) {
				removeBoom(mowerBoom10);
			}
		}

		if (mower11.collision) { 
			mower11.collision = false;
			MowerBooms mowerBoom11 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower11.getMowerX(), mower11.getMowerY(), true);
			removeLiveMower(mower11);
			addBoom(mowerBoom11);
			if (mowerBoom11.isAlive == false) {
				removeBoom(mowerBoom11);
			}
		}
		if (mower12.collision) {
			mower12.collision = false;
			MowerBooms mowerBoom12 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower12.getMowerX(), mower12.getMowerY(), true);
			removeLiveMower(mower12);
			addBoom(mowerBoom12);
			if (mowerBoom12.isAlive == false) {
				removeBoom(mowerBoom12);
			}
		}
		if (mower13.collision) {
			mower13.collision = false;
			MowerBooms mowerBoom13 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower13.getMowerX(), mower13.getMowerY(), true);
			removeLiveMower(mower13);
			addBoom(mowerBoom13);
			if (mowerBoom13.isAlive == false) {
				removeBoom(mowerBoom13);
			}
		}		
		if (mower14.collision) { 
			mower14.collision = false;
			MowerBooms mowerBoom14 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower14.getMowerX(), mower14.getMowerY(), true);
			removeLiveMower(mower14);
			addBoom(mowerBoom14);
			if (mowerBoom14.isAlive == false) {
				removeBoom(mowerBoom14);
			}
		}
		if (mower15.collision) {
			mower15.collision = false;
			MowerBooms mowerBoom15 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower15.getMowerX(), mower15.getMowerY(), true);
			removeLiveMower(mower15);
			addBoom(mowerBoom15);
			if (mowerBoom15.isAlive == false) {
				removeBoom(mowerBoom15);
			}
		}
		if (mower16.collision) {
			mower16.collision = false;
			MowerBooms mowerBoom16 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower16.getMowerX(), mower16.getMowerY(), true);
			removeLiveMower(mower16);
			addBoom(mowerBoom16);
			if (mowerBoom16.isAlive == false) {
				removeBoom(mowerBoom16);
			}
		}	
		if (mower17.collision) { 
			mower17.collision = false;
			MowerBooms mowerBoom17 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower17.getMowerX(), mower17.getMowerY(), true);
			removeLiveMower(mower17);
			addBoom(mowerBoom17);
			if (mowerBoom17.isAlive == false) {
				removeBoom(mowerBoom17);
			}
		}
		if (mower18.collision) {
			mower18.collision = false;
			MowerBooms mowerBoom18 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower18.getMowerX(), mower18.getMowerY(), true);
			removeLiveMower(mower18);
			addBoom(mowerBoom18);
			if (mowerBoom18.isAlive == false) {
				removeBoom(mowerBoom18);
			}
		}
		if (mower19.collision) {
			mower19.collision = false;
			MowerBooms mowerBoom19 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower19.getMowerX(), mower19.getMowerY(), true);
			removeLiveMower(mower19);
			addBoom(mowerBoom19);
			if (mowerBoom19.isAlive == false) {
				removeBoom(mowerBoom19);
			}
		}
		if (mower20.collision) {
			mower20.collision = false;
			MowerBooms mowerBoom20 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower20.getMowerX(), mower20.getMowerY(), true);
			removeLiveMower(mower20);
			addBoom(mowerBoom20);
			if (mowerBoom20.isAlive == false) {
				removeBoom(mowerBoom20);
			}
		}
		if (mower21.collision) { 
			mower21.collision = false;
			MowerBooms mowerBoom21 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower21.getMowerX(), mower21.getMowerY(), true);
			removeLiveMower(mower21);
			addBoom(mowerBoom21);
			if (mowerBoom21.isAlive == false) {
				removeBoom(mowerBoom21);
			}
		}
		if (mower22.collision) {
			mower22.collision = false;
			MowerBooms mowerBoom22 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower22.getMowerX(), mower22.getMowerY(), true);
			removeLiveMower(mower22);
			addBoom(mowerBoom22);
			if (mowerBoom22.isAlive == false) {
				removeBoom(mowerBoom22);
			}
		}
		if (mower23.collision) {
			mower23.collision = false;
			MowerBooms mowerBoom23 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower23.getMowerX(), mower23.getMowerY(), true);
			removeLiveMower(mower23);
			addBoom(mowerBoom23);
			if (mowerBoom23.isAlive == false) {
				removeBoom(mowerBoom23);
			}
		}		
		if (mower24.collision) { 
			mower24.collision = false;
			MowerBooms mowerBoom24 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower24.getMowerX(), mower24.getMowerY(), true);
			removeLiveMower(mower24);
			addBoom(mowerBoom24);
			if (mowerBoom24.isAlive == false) {
				removeBoom(mowerBoom24);
			}
		}
		if (mower25.collision) {
			mower25.collision = false;
			MowerBooms mowerBoom25 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower25.getMowerX(), mower25.getMowerY(), true);
			removeLiveMower(mower25);
			addBoom(mowerBoom25);
			if (mowerBoom25.isAlive == false) {
				removeBoom(mowerBoom25);
			}
		}
		if (mower26.collision) {
			mower26.collision = false;
			MowerBooms mowerBoom26 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower26.getMowerX(), mower26.getMowerY(), true);
			removeLiveMower(mower26);
			addBoom(mowerBoom26);
			if (mowerBoom26.isAlive == false) {
				removeBoom(mowerBoom26);
			}
		}	
		if (mower27.collision) { 
			mower27.collision = false;
			MowerBooms mowerBoom27 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower27.getMowerX(), mower27.getMowerY(), true);
			removeLiveMower(mower27);
			addBoom(mowerBoom27);
			if (mowerBoom27.isAlive == false) {
				removeBoom(mowerBoom27);
			}
		}
		if (mower28.collision) {
			mower28.collision = false;
			MowerBooms mowerBoom28 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower28.getMowerX(), mower28.getMowerY(), true);
			removeLiveMower(mower28);
			addBoom(mowerBoom28);
			if (mowerBoom28.isAlive == false) {
				removeBoom(mowerBoom28);
			}
		}
		if (mower29.collision) {
			mower29.collision = false;
			MowerBooms mowerBoom29 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower29.getMowerX(), mower29.getMowerY(), true);
			removeLiveMower(mower29);
			addBoom(mowerBoom29);
			if (mowerBoom29.isAlive == false) {
				removeBoom(mowerBoom29);
			}
		}
		if (mower30.collision) {
			mower30.collision = false;
			MowerBooms mowerBoom30 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower30.getMowerX(), mower30.getMowerY(), true);
			removeLiveMower(mower30);
			addBoom(mowerBoom30);
			if (mowerBoom30.isAlive == false) {
				removeBoom(mowerBoom30);
			}
		}	
		if (mower31.collision) { 
			mower31.collision = false;
			MowerBooms mowerBoom31 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower31.getMowerX(), mower31.getMowerY(), true);
			removeLiveMower(mower31);
			addBoom(mowerBoom31);
			if (mowerBoom31.isAlive == false) {
				removeBoom(mowerBoom31);
			}
		}
		if (mower32.collision) {
			mower32.collision = false;
			MowerBooms mowerBoom32 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower32.getMowerX(), mower32.getMowerY(), true);
			removeLiveMower(mower32);
			addBoom(mowerBoom32);
			if (mowerBoom32.isAlive == false) {
				removeBoom(mowerBoom32);
			}
		}
		if (mower33.collision) {
			mower33.collision = false;
			MowerBooms mowerBoom33 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower33.getMowerX(), mower33.getMowerY(), true);
			removeLiveMower(mower33);
			addBoom(mowerBoom33);
			if (mowerBoom33.isAlive == false) {
				removeBoom(mowerBoom33);
			}
		}		
		if (mower34.collision) { 
			mower34.collision = false;
			MowerBooms mowerBoom34 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower34.getMowerX(), mower34.getMowerY(), true);
			removeLiveMower(mower34);
			addBoom(mowerBoom34);
			if (mowerBoom34.isAlive == false) {
				removeBoom(mowerBoom34);
			}
		}
		if (mower35.collision) {
			mower35.collision = false;
			MowerBooms mowerBoom35 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower35.getMowerX(), mower35.getMowerY(), true);
			removeLiveMower(mower35);
			addBoom(mowerBoom35);
			if (mowerBoom35.isAlive == false) {
				removeBoom(mowerBoom35);
			}
		}
		if (mower36.collision) {
			mower36.collision = false;
			MowerBooms mowerBoom36 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower36.getMowerX(), mower36.getMowerY(), true);
			removeLiveMower(mower36);
			addBoom(mowerBoom36);
			if (mowerBoom36.isAlive == false) {
				removeBoom(mowerBoom36);
			}
		}	
		if (mower37.collision) { 
			mower37.collision = false;
			MowerBooms mowerBoom37 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower37.getMowerX(), mower37.getMowerY(), true);
			removeLiveMower(mower37);
			addBoom(mowerBoom37);
			if (mowerBoom37.isAlive == false) {
				removeBoom(mowerBoom37);
			}
		}
		if (mower38.collision) {
			mower38.collision = false;
			MowerBooms mowerBoom38 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower38.getMowerX(), mower38.getMowerY(), true);
			removeLiveMower(mower38);
			addBoom(mowerBoom38);
			if (mowerBoom38.isAlive == false) {
				removeBoom(mowerBoom38);
			}
		}
		if (mower39.collision) {
			mower39.collision = false;
			MowerBooms mowerBoom39 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower39.getMowerX(), mower39.getMowerY(), true);
			removeLiveMower(mower39);
			addBoom(mowerBoom39);
			if (mowerBoom39.isAlive == false) {
				removeBoom(mowerBoom39);
			}
		}
		if (mower40.collision) {
			mower40.collision = false;
			MowerBooms mowerBoom40 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower40.getMowerX(), mower40.getMowerY(), true);
			removeLiveMower(mower40);
			addBoom(mowerBoom40);
			if (mowerBoom40.isAlive == false) {
				removeBoom(mowerBoom40);
			}
		}
		if (mower41.collision) { 
			mower41.collision = false;
			MowerBooms mowerBoom41 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower41.getMowerX(), mower41.getMowerY(), true);
			removeLiveMower(mower41);
			addBoom(mowerBoom41);
			if (mowerBoom41.isAlive == false) {
				removeBoom(mowerBoom41);
			}
		}
		if (mower42.collision) {
			mower42.collision = false;
			MowerBooms mowerBoom42 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower42.getMowerX(), mower42.getMowerY(), true);
			removeLiveMower(mower42);
			addBoom(mowerBoom42);
			if (mowerBoom42.isAlive == false) {
				removeBoom(mowerBoom42);
			}
		}
		if (mower43.collision) {
			mower43.collision = false;
			MowerBooms mowerBoom43 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower43.getMowerX(), mower43.getMowerY(), true);
			removeLiveMower(mower43);
			addBoom(mowerBoom43);
			if (mowerBoom43.isAlive == false) {
				removeBoom(mowerBoom43);
			}
		}		
		if (mower44.collision) { 
			mower44.collision = false;
			MowerBooms mowerBoom44 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower44.getMowerX(), mower44.getMowerY(), true);
			removeLiveMower(mower44);
			addBoom(mowerBoom44);
			if (mowerBoom44.isAlive == false) {
				removeBoom(mowerBoom44);
			}
		}
		if (mower45.collision) {
			mower45.collision = false;
			MowerBooms mowerBoom45 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower45.getMowerX(), mower45.getMowerY(), true);
			removeLiveMower(mower45);
			addBoom(mowerBoom45);
			if (mowerBoom45.isAlive == false) {
				removeBoom(mowerBoom45);
			}
		}
		if (mower46.collision) {
			mower46.collision = false;
			MowerBooms mowerBoom46 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower46.getMowerX(), mower46.getMowerY(), true);
			removeLiveMower(mower46);
			addBoom(mowerBoom46);
			if (mowerBoom46.isAlive == false) {
				removeBoom(mowerBoom46);
			}
		}	
		if (mower47.collision) { 
			mower47.collision = false;
			MowerBooms mowerBoom47 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower47.getMowerX(), mower47.getMowerY(), true);
			removeLiveMower(mower47);
			addBoom(mowerBoom47);
			if (mowerBoom47.isAlive == false) {
				removeBoom(mowerBoom47);
			}
		}
		if (mower48.collision) {
			mower48.collision = false;
			MowerBooms mowerBoom48 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower48.getMowerX(), mower48.getMowerY(), true);
			removeLiveMower(mower48);
			addBoom(mowerBoom48);
			if (mowerBoom48.isAlive == false) {
				removeBoom(mowerBoom48);
			}
		}
		if (mower49.collision) {
			mower49.collision = false;
			MowerBooms mowerBoom49 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower49.getMowerX(), mower49.getMowerY(), true);
			removeLiveMower(mower49);
			addBoom(mowerBoom49);
			if (mowerBoom49.isAlive == false) {
				removeBoom(mowerBoom49);
			}
		}
		if (mower50.collision) {
			mower50.collision = false;
			MowerBooms mowerBoom50 = new MowerBooms(System.currentTimeMillis(), System.currentTimeMillis() + 25, mower50.getMowerX(), mower50.getMowerY(), true);
			removeLiveMower(mower50);
			addBoom(mowerBoom50);
			if (mowerBoom50.isAlive == false) {
				removeBoom(mowerBoom50);
			}
		}	
	}
	
	
	public void renderTiles(Screen screen, int xOffset, int yOffset) {
		if (xOffset < 0) {
			xOffset = 0;
		}
		if (xOffset > ((width << 3) - screen.width)) {
			xOffset = ((width << 3) - screen.width);
		}
		if (yOffset < 0) {
			yOffset = 0;
		}
		if (yOffset > ((height << 3) - screen.height)) {
			yOffset = ((height << 3) - screen.height);
		}
		
		
		if (screen.width > width * 8) {
			xOffset = (screen.width - (width* 8)) / 2 * -1;
		}
		if (screen.height > height * 8) {
			yOffset = (screen.height - (height * 8)) / 2 * -1;
		}
		
		screen.setOffset(xOffset, yOffset);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) { 
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}
		}
	}
	
	
	public void renderEntities(Screen screen) {
		for (Entity e : entities) {
			e.render(screen);
		}
	}
	
	public void renderMowers(Screen screen) {
		for (Mower m : liveMowers) {
			m.render(screen);;
		}
	}

	public void renderBooms(Screen screen) {
		for (Booms b : booms) {
			b.render(screen);
		}
	}

	
	public Tile getTile(int x, int y) {
		if (0 > x || x >= width || 0 > y || y >= height) {
			return Tile.VOID;
		}
		return Tile.tiles[tiles[x + y * width]];
	}
	
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	

	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
	
	public void addLiveMower(Mower mower) {
		this.liveMowers.add(mower);
	}
	
	public void removeLiveMower(Mower mower) {
		this.liveMowers.remove(mower);
	}
	
	public void addSpareMower(Mower mower) {
		this.spareMowers.add(mower);
	}
	
	public void removeSpareMower(Mower mower) {
		this.spareMowers.remove(mower);
	}
	
	
	public void addBoom(Booms boom) {
		this.booms.add(boom);
	}
	
	
	public void removeBoom(Booms boom) {
		this.booms.remove(boom);
	}	
}
