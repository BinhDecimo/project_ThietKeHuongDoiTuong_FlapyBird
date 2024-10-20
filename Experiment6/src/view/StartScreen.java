package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameControllerInterface;
import model.Character;
import model.GameModelInterface;
import model.StateObserver;

public class StartScreen extends JPanel implements StateObserver {
	
    private JButton startButton;
    private JButton exitButton;
    private Image backgroundImg;
    private GameModelInterface gameModel;
    private GameControllerInterface gameControl;
    private JFrame mainFrame;

    public StartScreen(JFrame mainFrame, GameModelInterface gameModel, GameControllerInterface gameControl) {
        this.mainFrame = mainFrame;
        this.gameModel = gameModel;
        this.gameControl = gameControl;

        // Add this StartScreen as an observer to the model
        this.gameModel.addObserver(this);

        setPreferredSize(new Dimension(360, 640));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        loadBackgroundImage();

        startButton = createButton("/resources/start.png", 120, 50);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameControl.startGame();
                mainFrame.getContentPane().removeAll();
                GameScreen gameScreen = new GameScreen(mainFrame, gameModel, gameControl);
                mainFrame.getContentPane().add(gameScreen);
                mainFrame.revalidate();
                gameScreen.requestFocusInWindow();
            }
        });

        exitButton = createButton("/resources/exit.png", 120, 50);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 System.exit(0);
            }
        });

        add(Box.createRigidArea(new Dimension(0, 200)));
        add(startButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(exitButton);
    }

    private void loadBackgroundImage() {
        URL imageUrlBackground = getClass().getResource("/resources/startbg.png");
        if (imageUrlBackground != null) {
            backgroundImg = new ImageIcon(imageUrlBackground).getImage();
        } else {
            System.err.println("Could not find image file: /resources/startbg.png");
        }
    }

    private JButton createButton(String imageUrl, int width, int height) {
        JButton button = new JButton();
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        URL buttonIconUrl = getClass().getResource(imageUrl);
        if (buttonIconUrl != null) {
            ImageIcon icon = new ImageIcon(buttonIconUrl);
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } else {
            System.err.println("Could not find image file: " + imageUrl);
        }

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public void updateState() {
        // Implement this method to update the view based on the model's state changes
    }
}
