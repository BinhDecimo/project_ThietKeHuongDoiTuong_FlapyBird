package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameControllerInterface;
import model.Bird;
import model.Character;
import model.GameModelInterface;
import model.Pipe;
import model.StateObserver;

public class GameScreen extends JPanel implements StateObserver {
    private int boardWidth = 360;
    private int boardHeight = 640;

    private Image backgroundImg;
    private Image characterImg;
    private Image topPipeImg, bottomPipeImg;

    private GameModelInterface gameModel;
    private GameControllerInterface gameController;
    private JFrame mainFrame;

    public GameScreen(JFrame mainFrame, GameModelInterface gameModel, GameControllerInterface gameController) {
        this.mainFrame = mainFrame;
        this.gameModel = gameModel;
        this.gameController = gameController;

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener((KeyListener) gameController); // Thêm dòng này để lắng nghe sự kiện bàn phím

        loadImage();
        gameModel.addObserver(this);
    }

    public Image getBottomPipeImg() {
        return bottomPipeImg;
    }

    public void setBottomPipeImg(Image bottomPipeImg) {
        this.bottomPipeImg = bottomPipeImg;
    }

    public Image getTopPipeImg() {
        return topPipeImg;
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
        drawDecoratedCircle(g); // Vẽ hình tròn với chữ "S"
    }

    private void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, this.boardWidth, this.boardHeight, null);

        Character character = gameModel.getCharacter();
        if (character != null) {
            g.drawImage(characterImg, character.getX(), character.getY(), character.getWidth(), character.getHeight(), null);
        }

        ArrayList<Pipe> pipes = gameModel.getPipes();
        if (pipes != null) {
            for (Pipe pipe : pipes) {
                g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameModel.isGameOver()) {
            g.drawString("Game Over: " + (int) gameModel.getScore(), 10, 70);
        } else {
            g.drawString(String.valueOf((int) gameModel.getScore()), 10, 35);
        }
    }

    private void drawDecoratedCircle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int circleX = 80;
        int circleY = boardHeight - 615;
        int radius = 20;

        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fill(new Ellipse2D.Double(circleX - radius + 5, circleY - radius + 5, 2 * radius, 2 * radius));

        g2d.setColor(Color.YELLOW);
        g2d.fill(new Ellipse2D.Double(circleX - radius, circleY - radius, 2 * radius, 2 * radius));

        g2d.setColor(Color.ORANGE);
        g2d.draw(new Ellipse2D.Double(circleX - radius, circleY - radius, 2 * radius, 2 * radius));

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));

        Character character = gameModel.getCharacter();
        if (character.isSkillOnCooldown()) {
            int seconds = character.getRemainingCooldownTime() / 1000;
            String cooldownText = String.valueOf(seconds);
            int strWidth = g2d.getFontMetrics().stringWidth(cooldownText);
            g2d.drawString(cooldownText, circleX - strWidth / 2, circleY + radius / 2 - 2);
        } else {
            int strWidth = g2d.getFontMetrics().stringWidth("S");
            g2d.drawString("S", circleX - strWidth / 2, circleY + radius / 2 - 2);
        }
    }



    @Override
    public void updateState() {
        // Cập nhật trạng thái
    }
}
