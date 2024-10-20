package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Bird;
import model.Character;
import model.CollisionManager;
import model.Pipe;

public class GameScreen extends JPanel implements ActionListener, KeyListener {
	//width and height of boardGame
	private int boardWidth = 360;
	private int boardHeight = 640;
	
	//image
	private Image backgroundImg;
	private Image characterImg;
	private Image topPipeImg, bottomPipeImg;
	
	//các biến của lớp khác
	private Character character;
	public ArrayList<Pipe> pipes;
	private Random random = new Random();
	private CollisionManager collision;
	
	//các biến trong game screen
	private Timer gameLoop,placePipeTimer;
    private boolean gameOver = false;
    private double score = 0;
    
 // Static reference to the current GameScreen instance
    private static GameScreen instance;
    
    public GameScreen() {
    	 instance = this; // Assign this instance to the static reference
    	
    	setPreferredSize(new Dimension(boardWidth, boardHeight));
    	setFocusable(true);
		addKeyListener(this);
		
		//image
		loadImage();
//		backgroundImg = new ImageIcon(getClass().getResource("/resources/images/flappybirdbg.png")).getImage();
//		characterImg = new ImageIcon(getClass().getResource("/resources/images/flappybird.png")).getImage();
//      topPipeImg = new ImageIcon(getClass().getResource("/resources/images/toppipe.png")).getImage();
//      bottomPipeImg = new ImageIcon(getClass().getResource("/resources/images/bottompipe.png")).getImage();
        
        //new các đối tượng
        character = new Bird(boardWidth/8, boardWidth/2, 34, 24, characterImg);
        pipes = new ArrayList<Pipe>();
        collision = new CollisionManager();
        
        placePipeTimer = new Timer(1500, e -> placePipes());
        placePipeTimer.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }
    
    

	public static GameScreen getInstance() {
		return instance;
	}



	public static void setInstance(GameScreen instance) {
		GameScreen.instance = instance;
	}



	private void loadImage() {
        // Sử dụng getClass().getResource để tải hình ảnh từ thư mục resources
        URL imageUrlBird = getClass().getResource("/resources/flappybird.png");
        URL imageUrlBackground = getClass().getResource("/resources/flappybirdbg.png");
        URL imageUrlTopPipe = getClass().getResource("/resources/toppipe.png");
        URL imageUrlBottomPipe = getClass().getResource("/resources/bottompipe.png");
//        URL imageUrlBirdC = getClass().getResource("/resources/birdC.png");
        //URL imageUrlPlaneC = getClass().getResource("/resources/PlaneC.png");
        //URL imageUrlHeroC = getClass().getResource("/resources/HeroC.png");
        
        if (imageUrlBird != null) {
            characterImg = new ImageIcon(imageUrlBird).getImage();
        } else {
            System.err.println("Could not find image file: /resources/flappybird.png");
        }

        if (imageUrlBackground != null) {
            backgroundImg = new ImageIcon(imageUrlBackground).getImage();
        } else {
            System.err.println("Could not find image file: /resources/flappybirdbg.png");
        }

        if (imageUrlTopPipe != null) {
            topPipeImg = new ImageIcon(imageUrlTopPipe).getImage();
        } else {
            System.err.println("Could not find image file: /resources/toppipe.png");
        }

        if (imageUrlBottomPipe != null) {
            bottomPipeImg = new ImageIcon(imageUrlBottomPipe).getImage();
        } else {
            System.err.println("Could not find image file: /resources/bottompipe.png");
        }
//        if (imageUrlBirdC != null) {
//            Image birdCImage = new ImageIcon(imageUrlBirdC).getImage();
//            characterImg = birdCImage.getScaledInstance(34, 24, Image.SCALE_SMOOTH);
//        } else {
//            System.err.println("Could not find image file: /resources/birdC.png");
//        }
//        if (imageUrlPlaneC != null) {
//            bottomPipeImg = new ImageIcon(imageUrlPlaneC).getImage();
//        } else {
//            System.err.println("Could not find image file: /resources/bottompipe.png");
//        }
//        if (imageUrlHeroC != null) {
//            bottomPipeImg = new ImageIcon(imageUrlHeroC).getImage();
//        } else {
//            System.err.println("Could not find image file: /resources/bottompipe.png");
//        }
        
    }

    
    public void placePipes() {
        int randomPipeY = (int) (boardHeight / 2 - Math.random() * (boardHeight / 4));
        int openingSpace = boardHeight / 4;

        Pipe topPipe = new Pipe(boardWidth, randomPipeY - boardHeight / 2, 64, 512, topPipeImg);
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(boardWidth, topPipe.getY() + topPipe.getHeight() + openingSpace, 64, 512, bottomPipeImg);
        pipes.add(bottomPipe);
    }
    
    public ArrayList<Pipe> getPipes() {
		return pipes;
	}

	public void setPipes(ArrayList<Pipe> pipes) {
		this.pipes = pipes;
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	private void draw(Graphics g) {
		//background
        g.drawImage(backgroundImg, 0, 0, this.boardWidth, this.boardHeight, null);

        //bird
        g.drawImage(characterImg, character.getX(), character.getY(), character.getWidth(), character.getHeight(), null);

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        //score
        g.setColor(Color.white);

        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        }
        else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
		
	}
	
	public void move() {
	    character.move();
	    for (Pipe pipe : pipes) {
	        pipe.move();
	        if (!pipe.isPassed() && character.getX() > pipe.getX() + pipe.getWidth()) {
	            score += 0.5; //mỗi pipe là 0.5, mỗi lần qua là 2 pipe
	            pipe.setPassed(true);
	        }
	        if(collision.checkCollision(character, pipe)) {
	        	gameOver = true; 
	        }
	    }
	    if (character.getY() > boardHeight) {
	        gameOver = true;
	    }
	}

	
	@Override
	public void actionPerformed(ActionEvent e) { //called every x milliseconds by gameLoop timer
		move();
		repaint();
		if (gameOver) {
			placePipeTimer.stop();
			gameLoop.stop();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			character.setVelocityY(-9);
			if (gameOver) {
				character.setY(boardHeight / 2);
				character.setVelocityY(0);
				character = new Bird(boardWidth / 8, boardHeight / 2, 34, 24, characterImg); // Reset to default character
				pipes.clear();
				gameOver = false;
				score = 0;
				gameLoop.start();
				placePipeTimer.start();
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S) { // Key 'S' to activate skill
            if (character instanceof Bird) {
                ((Bird) character).useSkill();
            } 
        }
		
	}
	
	
	//không dùng đến
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	
	
	

}
