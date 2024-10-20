package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;
import model.Character;
import model.Observer;

public class RankingScreen extends JPanel implements Observer {
    private JFrame frame;
    private int boardWidth = 360;
    private int boardHeight = 640;
    private double bestScore;
    private Character character;
    private Image backgroundImg;

    // Buttons
    private int buttonWidth = 100;
    private int buttonHeight = 40;
    private int xBackButton, yButtons;
    private int xLinkButton, yLinkButton;

    public RankingScreen(JFrame frame, Character character, double bestScore) {
        this.frame = frame;
        this.character = character;
        this.bestScore = bestScore;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);

        // Load background image
        loadBackgroundImage();

        // Button positions
        xBackButton = (boardWidth / 2) - (buttonWidth / 2);
        yButtons = boardHeight - 200;
        xLinkButton = (boardWidth / 2) - (buttonWidth / 2);
        yLinkButton = yButtons + buttonHeight + 10;

        // Set layout to null for absolute positioning
        setLayout(null);

        // Add back button
        JButton backButton = new JButton("Quay Về");
        backButton.setBounds(xBackButton, yButtons, buttonWidth, buttonHeight);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset character position before starting new game
                character.reset(boardWidth / 8, boardHeight / 2);
                
                GameScreen gameScreen = new GameScreen(frame, character);
                frame.setContentPane(gameScreen);
                frame.pack();
                gameScreen.requestFocusInWindow();
                gameScreen.startGame();
            }
        });
        add(backButton);

        // Add link button
        JButton linkButton = new JButton("Go to Link");
        linkButton.setBounds(xLinkButton, yLinkButton, buttonWidth, buttonHeight);
        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.example.com"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(linkButton);
    }

    private void loadBackgroundImage() {
        URL imageUrlBackground = getClass().getResource("/resources/flappybirdbg.png");
        if (imageUrlBackground != null) {
            backgroundImg = new ImageIcon(imageUrlBackground).getImage();
        } else {
            System.err.println("Could not find image file: /resources/flappybirdbg.png");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        }
        draw(g);
    }

    private void draw(Graphics g) {
        // Title
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Ranking", boardWidth / 2 - 70, 100);

        // Best Score
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Cao nhất: " + (int) bestScore, boardWidth / 2 - 60, 200);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}
