package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Adventure");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); // window displayed according to size/layout of subcomponents (GamePanel)
		
		window.setLocationRelativeTo(null); // window displayed at center of screen
		window.setVisible(true);
		
	}

}
