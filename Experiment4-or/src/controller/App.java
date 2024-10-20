    package controller;

import javax.swing.JFrame;
import view.GameScreen;
import model.Bird;
import model.Pipe;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        int boardWidth = 360;
        int boardHeight = 640;

        ArrayList<Pipe> pipes = new ArrayList<>();
        Bird character = new Bird(boardWidth / 8, boardHeight / 2, 34, 24, null, null);  // Image will be set by GameScreen

        GameScreen gameScreen = new GameScreen(character, pipes);
        GameController controller = new GameController(gameScreen);

        // Cập nhật lại GameController trong Bird
        character.setGameController(controller);

        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameScreen);
        frame.pack();
        frame.setVisible(true);
    }
}
