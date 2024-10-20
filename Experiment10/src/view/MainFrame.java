package view;

import javax.swing.JFrame;

import controller.CharacterFactory;
import controller.GameController;
import controller.GameControllerInterface;
import model.Bird;
import model.GameModel;
import model.GameModelInterface;

public class MainFrame extends JFrame {
	
	public static void main(String[] args) {
//        GameModelInterface gameModel = new GameModel();
//        CharacterFactory factory = new CharacterFactory(gameModel);
//        
//        JFrame mainFrame1 = new JFrame("StartScreen 1");
//        mainFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame1.setSize(360, 640);
//        mainFrame1.setResizable(false);
//        mainFrame1.setLocationRelativeTo(null);
//        GameControllerInterface gameController1 = new GameController(gameModel, mainFrame1, factory);
//        StartScreen startScreen1 = new StartScreen(mainFrame1, gameModel, gameController1);
//        mainFrame1.setContentPane(startScreen1);
//        mainFrame1.setVisible(true);
//
//        
		
		GameModelInterface gameModel = new GameModel();
        CharacterFactory factory = new CharacterFactory(gameModel);
        
        int numFrames = 3;
        JFrame[] frames = new JFrame[numFrames];
        GameControllerInterface[] gameControllers = new GameControllerInterface[numFrames];
        StartScreen[] startScreens = new StartScreen[numFrames];
        
        for (int i = 0; i < numFrames; i++) {
            frames[i] = new JFrame("StartScreen " + (i + 1));
            frames[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frames[i].setSize(360, 640);
            frames[i].setResizable(false);
            frames[i].setLocationRelativeTo(null);
            
            gameControllers[i] = new GameController(gameModel, frames[i], factory);
            startScreens[i] = new StartScreen(frames[i], gameModel, gameControllers[i]);
            
            frames[i].setContentPane(startScreens[i]);
            frames[i].setVisible(true);
        }
    

    }

}
