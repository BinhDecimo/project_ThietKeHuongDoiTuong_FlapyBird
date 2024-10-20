package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.GameController;
import controller.GameControllerInterface;
import controller.SelectionManager;
import model.GameModel;
import model.GameModelInterface;
import model.SelectionObserver;
import model.Character;
import model.Bird;
import model.Plane;
import model.Hero;

public class SelectionScreen extends JPanel implements SelectionObserver {
	
    private static final ImageIcon BIRD_ICON = new ImageIcon(SelectionScreen.class.getResource("/resources/birdC.png"));
    private static final ImageIcon PLANE_ICON = new ImageIcon(SelectionScreen.class.getResource("/resources/planeC.png"));
    private static final ImageIcon HERO_ICON = new ImageIcon(SelectionScreen.class.getResource("/resources/heroC.png"));
    private JLabel characterLabel;
    private static final int ICON_WIDTH = 200;
    private static final int ICON_HEIGHT = 200;
   // private SelectionManager selectionManager;
    private GameModelInterface gameModel;
    private GameControllerInterface gameController;
    
    private MainGameFrame mainFrame;

    public SelectionScreen(MainGameFrame mainFrame, GameModelInterface gameModel, GameControllerInterface gameController) {
        this.gameModel = gameModel;
        this.gameController = gameController;
        this.gameModel.addObserver(this);
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        Font customFont = new Font("Arial", Font.BOLD, 14);

        JPanel levelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        levelPanel.setBorder(createTitledBorder("Select Level"));
        JLabel level = new JLabel("Level:");
        level.setFont(customFont);
        JRadioButton easy = new JRadioButton("Easy");
        easy.setForeground(Color.decode("#40A578"));
        JRadioButton medium = new JRadioButton("Medium");
        medium.setForeground(Color.decode("#DD761C"));
        JRadioButton hard = new JRadioButton("Hard");
        hard.setForeground(Color.decode("#EE4E4E"));

        ButtonGroup levelGroup = new ButtonGroup();
        levelGroup.add(easy);
        levelGroup.add(medium);
        levelGroup.add(hard);

        levelPanel.add(level);
        levelPanel.add(easy);
        levelPanel.add(medium);
        levelPanel.add(hard);
        levelPanel.setBackground(Color.decode("#B1B2FF"));
        add(levelPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(createCompoundBorder());
        buttonPanel.setBackground(Color.decode("#D8E2DC"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel characterText = new JLabel("Character: ");
        characterText.setFont(customFont);
        buttonPanel.add(characterText, gbc);

        JRadioButton birdButton = new JRadioButton("Bird");
        birdButton.setBackground(Color.decode("#CADEFC"));
        birdButton.setToolTipText("Select Bird Character");
        JRadioButton heroButton = new JRadioButton("Hero");
        heroButton.setToolTipText("Select Hero Character");
        heroButton.setBackground(Color.decode("#CADEFC"));
        JRadioButton planeButton = new JRadioButton("Plane");
        planeButton.setToolTipText("Select Plane Character");
        planeButton.setBackground(Color.decode("#CADEFC"));

        ButtonGroup characterGroup = new ButtonGroup();
        characterGroup.add(birdButton);
        characterGroup.add(heroButton);
        characterGroup.add(planeButton);

        gbc.gridy = 1;
        buttonPanel.add(birdButton, gbc);
        gbc.gridy = 2;
        buttonPanel.add(heroButton, gbc);
        gbc.gridy = 3;
        buttonPanel.add(planeButton, gbc);

        buttonPanel.setBackground(Color.decode("#CADEFC"));

        characterLabel = new JLabel();
        characterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(buttonPanel, gbc);
        gbc.gridx = 1;
        contentPanel.add(characterLabel, gbc);

        add(contentPanel, BorderLayout.CENTER);

        birdButton.addActionListener(e -> {
            characterLabel.setIcon(resizeIcon(BIRD_ICON, ICON_WIDTH, ICON_HEIGHT));
            gameController.getSelection().createCharacter("bird");
        });
        heroButton.addActionListener(e -> {
            characterLabel.setIcon(resizeIcon(HERO_ICON, ICON_WIDTH, ICON_HEIGHT));
            gameController.getSelection().createCharacter("hero");
        });
        planeButton.addActionListener(e -> {
            characterLabel.setIcon(resizeIcon(PLANE_ICON, ICON_WIDTH, ICON_HEIGHT));
            gameController.getSelection().createCharacter("plane");
        });

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userPanel.setBorder(createCompoundBorder());
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(customFont);
        JTextField userField = new JTextField(20);
        JButton startButton = new JButton(resizeIcon(new ImageIcon(SelectionScreen.class.getResource("/resources/start.png")), 70, 30));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        userPanel.add(userLabel);
        userPanel.add(userField);
        userPanel.add(startButton);
        userPanel.setBackground(Color.decode("#FFA62F"));
        add(userPanel, BorderLayout.SOUTH);
    }


    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), title, TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 12));
    }

    private Border createCompoundBorder() {
        Border line = new LineBorder(Color.BLACK, 1, true);
        Border empty = new EmptyBorder(10, 10, 10, 10);
        return new CompoundBorder(line, empty);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    
    @Override
	public void updateSelection() {
		// TODO Auto-generated method stub
		
	}

    

	
}
