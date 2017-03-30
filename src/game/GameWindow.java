package game;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame{
	GamePanel gamePanel;
	
	public GameWindow() {
		gamePanel = new GamePanel();
		add(gamePanel);	
		gamePanel.setFocusable(true);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}

	public static void main(String[] args) {
		new GameWindow();

	}

}
