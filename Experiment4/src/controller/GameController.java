package controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import model.Bird;
import model.CollisionManager;
import model.Pipe;
import view.GameScreen;
import view.RankingScreen;
import model.Character;

public class GameController implements ActionListener, KeyListener, MouseListener {
    private GameScreen gameScreen;
    private Character character;
    private ArrayList<Pipe> pipes;
    private Random random = new Random();
    private Timer gameLoop, placePipeTimer;
    private boolean gameOver = false;
    private double score = 0;
    private JFrame mainFrame;

    public GameController(GameScreen gameScreen, JFrame mainFrame) {
        this.gameScreen = gameScreen;
        this.character = gameScreen.getCharacter();
        this.pipes = gameScreen.getPipes();
        this.mainFrame = mainFrame;

        gameScreen.addKeyListener(this);
        gameScreen.setFocusable(true);

        placePipeTimer = new Timer(1500, e -> placePipes());
        placePipeTimer.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    private void placePipes() {
        int randomPipeY = (int) (gameScreen.getHeight() / 2 - Math.random() * (gameScreen.getHeight() / 4));
        int openingSpace = gameScreen.getHeight() / 4;

        Pipe topPipe = new Pipe(gameScreen.getWidth(), randomPipeY - gameScreen.getHeight() / 2, 64, 512, gameScreen.getTopPipeImg());
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(gameScreen.getWidth(), topPipe.getY() + topPipe.getHeight() + openingSpace, 64, 512, gameScreen.getBottomPipeImg());
        pipes.add(bottomPipe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            gameScreen.repaint();
        }
    }

    private void move() {
        character.move();
        for (Pipe pipe : pipes) {
            pipe.move();
            if (!pipe.isPassed() && character.getX() > pipe.getX() + pipe.getWidth()) {
                score += 0.5;
                pipe.setPassed(true);
            }
            if (CollisionManager.checkCollision(character, pipe)) {
                endGame();
            }
        }
        if (character.getY() > gameScreen.getHeight()) {
            endGame();
        }
        gameScreen.setScore(score);
    }

    private void endGame() {
        gameOver = true;
        gameScreen.setGameOver(true);
        placePipeTimer.stop();
        gameLoop.stop();

        // Chuyển đến RankingScreen khi trò chơi kết thúc
        SwingUtilities.invokeLater(() -> {
            RankingScreen rankingScreen = new RankingScreen(mainFrame, character, score);
            mainFrame.setContentPane(rankingScreen);
            mainFrame.pack();
            rankingScreen.requestFocusInWindow();
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                resetGame();
            } else {
                character.setVelocityY(-9);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_S) { // Key 'S' to activate skill
            character.useSkill();
        }
    }

    private void resetGame() {
        character.reset(gameScreen.getWidth() / 8, gameScreen.getHeight() / 2);
        pipes.clear();
        gameOver = false;
        score = 0;
        gameScreen.setGameOver(false);
        gameLoop.restart();
        placePipeTimer.restart();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
