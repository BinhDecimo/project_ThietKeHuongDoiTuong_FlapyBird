package view;

import javax.swing.JFrame;

import controller.CharacterFactory;
import controller.GameController;
import model.Bird;
import model.GameModel;
import model.GameModelInterface;

public class MainFrame extends JFrame {
	
	public static void main(String[] args) {
		  JFrame mainFrame = new JFrame("Flappy Bird Test");
	        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        mainFrame.setSize(360, 640);
	        mainFrame.setResizable(false);
	        mainFrame.setLocationRelativeTo(null);

	        GameModelInterface gameModel = new GameModel();
	        CharacterFactory factory = new CharacterFactory(gameModel);
		    GameController gameController = new GameController(gameModel, mainFrame, factory);
		    StartScreen start = new StartScreen(mainFrame, gameModel, gameController);
		    mainFrame.setContentPane(start);

		    // Make the JFrame visible
		    mainFrame.setVisible(true);
	}

}
