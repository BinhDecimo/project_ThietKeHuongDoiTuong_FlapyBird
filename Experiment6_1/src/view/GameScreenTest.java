package view;

import javax.swing.JFrame;

import controller.GameController;
import controller.GameControllerInterface;
import model.Bird;
import model.GameModel;
import model.GameModelInterface;

public class GameScreenTest {
	
	public static void main(String[] args) {
		
		// Create the JFrame
        JFrame mainFrame = new JFrame("Flappy Bird Test");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(360, 640);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);

        // Initialize the GameModel
        GameModelInterface gameModel = new GameModel();

        // Create the Bird character
        Bird bird = new Bird(45, 320, 34, 24, null, gameModel);  // Pass null for the image, assuming it will be set in the GameScreen
        
        gameModel.setCharacter(bird);

        // Initialize the GameController
        GameController gameController = new GameController(gameModel, mainFrame);

        // Create the GameScreen and set it in the JFrame
        GameScreen gameScreen = new GameScreen(mainFrame, gameModel, gameController);
        mainFrame.setContentPane(gameScreen);

        // Make the JFrame visible
        mainFrame.setVisible(true);

        // Start the game
        gameController.startGame();
    }
		
	

}
