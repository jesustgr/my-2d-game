package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16 x 16 tile
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; // 48 x 48 tile
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	Thread gameThread; // game clock
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //drawing for this component done in off-screen painting buffer (improve rendering performance)	
	}

	public void startGameThread() {
		
		gameThread = new Thread(this); // passing GamePanel class to Thread constructor
		gameThread.start();	
	}

	@Override
	public void run() { // automatically called when starting gameThread
		
		// GAME LOOP
		
	}
}
