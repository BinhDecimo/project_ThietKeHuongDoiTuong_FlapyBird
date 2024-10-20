package view;

import javax.swing.JFrame;

import controller.CharacterFactory;
import controller.GameController;
import controller.GameControllerInterface;
import model.GameModel;
import model.GameModelInterface;

public class MainFramOne {
	
	public static void main(String[] args) {
		GameModelInterface gameModel = new GameModel();
      CharacterFactory factory = new CharacterFactory(gameModel);
      
      JFrame mainFrame1 = new JFrame("StartScreen 1");
      mainFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame1.setSize(360, 640);
      mainFrame1.setResizable(false);
      mainFrame1.setLocationRelativeTo(null);
      GameControllerInterface gameController1 = new GameController(gameModel, mainFrame1, factory);
      StartScreen startScreen1 = new StartScreen(mainFrame1, gameModel, gameController1);
      mainFrame1.setContentPane(startScreen1);
      mainFrame1.setVisible(true);
//
	}

}
