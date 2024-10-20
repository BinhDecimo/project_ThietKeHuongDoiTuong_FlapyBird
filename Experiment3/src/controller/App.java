package controller;

import javax.swing.JFrame;
import view.GameScreen;

public class App {
    public static void main(String[] args) {
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameScreen gameScreen = new GameScreen();
        frame.add(gameScreen);
        frame.pack();
        gameScreen.requestFocus();
        frame.setVisible(true);
    }
}


