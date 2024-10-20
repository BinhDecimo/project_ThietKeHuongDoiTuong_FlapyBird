package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameController;
import controller.GameControllerInterface;
import model.Bird;
import model.Character;
import model.GameModel;
import model.GameModelInterface;

import java.awt.CardLayout;

public class MainGameFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Các tên cho các màn hình khác nhau
    public static final String START_SCREEN = "StartScreen";
    public static final String GAME_SCREEN = "GameScreen";
    public static final String RANKING_SCREEN = "RankingScreen";
    public static final String SELECTION_SCREEN = "SelectionScreen";

    public MainGameFrame() {
        setTitle("Game Title");
        setSize(360, 640); // kích thước khung
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // căn giữa màn hình

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Khởi tạo và thêm các thành phần giao diện khác tại đây
        addScreens();

        add(mainPanel);
        setVisible(true);
    }

    private void addScreens() {
        
        //model and controller
        GameModelInterface model = new GameModel();
        GameControllerInterface controller = new GameController(model, this);
        
        //khởi tạo các view
        StartScreen start = new StartScreen(this, model, controller);
        SelectionScreen selection = new SelectionScreen(this, model, controller);
        GameScreen game = new GameScreen(this, model, controller);
        RankingScreen ranking = new RankingScreen(this, model, controller);
        
        //khởi tạo character
        Character bird = new Bird(45, 320, 34, 24, null, model);  // Pass null for the image, assuming it will be set in the GameScreen
        model.setCharacter(bird);

        // Update character in the controller after setting it in the model
        ((GameController)controller).updateCharacter();
        
        // Thêm các màn hình vào mainPanel với CardLayout
        mainPanel.add(start, START_SCREEN);
        mainPanel.add(selection, SELECTION_SCREEN);
        mainPanel.add(game, GAME_SCREEN);
        mainPanel.add(ranking, RANKING_SCREEN);
        
        // Hiển thị màn hình bắt đầu
        showScreen(START_SCREEN);
    }

    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }

    public static void main(String[] args) {
        // Khởi chạy ứng dụng
        new MainGameFrame();
    }
}
