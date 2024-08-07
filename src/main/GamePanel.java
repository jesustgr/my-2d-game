package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16 x 16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48 x 48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; // game clock
	Player player = new Player(this, keyH);
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //drawing for this component done in off-screen painting buffer (improve rendering performance)
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		
		gameThread = new Thread(this); // passing GamePanel class to Thread constructor 
		gameThread.start();	
	}

	// SLEEP METHOD GAME LOOP
	@Override
//	public void run() { // automatically called when starting gameThread
//		
//		double drawInterval = 1000000000/FPS; // draw screen every 0.0167 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		// GAME LOOP
//		while(gameThread != null ) {
//			
//			// 1. UPDATE: update info (ex. character position)
//			update();
//			
//			// 2. DRAW: draw the screen with updated info
//			repaint(); // calls paintComponent method
//			
//			
//			try {
//				double remainingTime = nextDrawTime- System.nanoTime();
//				remainingTime = remainingTime / 1000000; // convert nano to milli
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
	// DELTA METHOD GAME LOOP 
	public void run() {
		
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}		
	}
	public void update() {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; // Graphics2D library has more useful functions
		
		tileM.draw(g2); // draw tiles first
		
		player.draw(g2); // then draw player
		
		g2.dispose(); // saves memory
	}
}





