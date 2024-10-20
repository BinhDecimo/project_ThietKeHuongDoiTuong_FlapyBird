package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import controller.GameController;
import model.Pipe;
import model.Bird;
import model.Character;
import model.Observer;

public class GameScreen extends JPanel implements Observer {
    private int boardWidth = 360;
    private int boardHeight = 640;

    private Image backgroundImg;
    private Image characterImg;
    private Image topPipeImg, bottomPipeImg;

    private Character character;
    private ArrayList<Pipe> pipes;
    private boolean gameOver = false;
    private double score = 0;
    private GameController gameController;
    private JFrame mainFrame;

    public GameScreen(JFrame mainFrame, Character character) {
        this.mainFrame = mainFrame;
        this.character = character;
        pipes = new ArrayList<Pipe>();
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);

        loadImage();
    }

    public Character getCharacter() {
        return character;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public Image getTopPipeImg() {
        return topPipeImg;
    }

    public Image getBottomPipeImg() {
        return bottomPipeImg;
    }

    private void loadImage() {
        backgroundImg = loadImage("/resources/flappybirdbg.png");
        characterImg = loadImage("/resources/flappybird.png");
        topPipeImg = loadImage("/resources/toppipe.png");
        bottomPipeImg = loadImage("/resources/bottompipe.png");
    }

    private Image loadImage(String path) {
        URL imageUrl = getClass().getResource(path);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Could not find image file: " + path);
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, this.boardWidth, this.boardHeight, null);
        g.drawImage(characterImg, character.getX(), character.getY(), character.getWidth(), character.getHeight(), null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + (int) score, 10, 35);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        if (gameOver) {
            showRankingScreen();
        }
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void startGame() {
        gameController = new GameController(this, mainFrame);
        if (character instanceof Bird) {
            ((Bird) character).setGameController(gameController);
        }
    }

    private void showRankingScreen() {
        RankingScreen rankingScreen = new RankingScreen(mainFrame, character, score);
        mainFrame.setContentPane(rankingScreen);
        mainFrame.pack();
        mainFrame.revalidate();
        rankingScreen.requestFocusInWindow();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }
}
