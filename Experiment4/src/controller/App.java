package controller;

import javax.swing.JFrame;
import view.StartScreen;
import model.Bird;
import model.Character;

public class App {

    private JFrame frame;
    private int boardWidth = 360;
    private int boardHeight = 640;

    public App() {
        frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Character character = new Bird(boardWidth / 8, boardHeight / 2, 34, 24, null);
        GameController gameController = new GameController(null, frame);
        StartScreen startScreen = new StartScreen(frame, character);

        frame.getContentPane().add(startScreen);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
    }
}
