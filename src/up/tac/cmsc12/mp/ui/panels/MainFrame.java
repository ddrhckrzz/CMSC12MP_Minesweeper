package up.tac.cmsc12.mp.ui.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.minesweeper.Minesweeper;
import up.tac.cmsc12.mp.ui.ViewController;
import up.tac.cmsc12.mp.ui.buttons.CustomButton;

public class MainFrame extends JFrame {
    /*
     * 
     * To access the assets folder from here the path is:
     * "assets/"
     * 
     */
    // environment constants
    private final static String TITLENAME = "Minesweeper! In Java™";
    public final static String GAME_PANEL = "Game Panel";
    public final static String CREDITS_PANEL = "Credits Panel";
    public final static String SCORE_PANEL = "Score Panel";

    // class variables
    private ViewController viewController;
    private CardLayout cardLayout;
    private Background cardPanel;
    private JPanel navPanel;
    private MainPanel mainMenu;
    private CreditsPanel creditsPanel;
    private ChooseDifficulty difficultyChooser;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;

    public MainFrame(){
        setTitle(TITLENAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init_layout();
        Minesweeper.setViewController(viewController); // very important
        add_panels();
        toggleNavVisiblity(); // navigation panel is invisble by default
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public boolean isNavVisible() {
        return navPanel.isVisible();
    }

    public void toggleNavVisiblity() {
        if (navPanel.isVisible()) {
            navPanel.setVisible(false);
        } else {
            navPanel.setVisible(true);
        }
        revalidate();
        repaint();
    }

    private void init_layout(){
        cardLayout = new CardLayout(10,10);
        cardPanel = new Background(cardLayout);
        Minesweeper.setBackground(cardPanel);
        cardPanel.setBackground(new Color(0, 0, 0 ,0));
        viewController = new ViewController(this, cardLayout);
        setPreferredSize(new Dimension(1280, 720));
        setSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setLocationRelativeTo(null);
    }


    private void add_panels() {
        mainMenu = new MainPanel();
        gamePanel = new GamePanel();
        difficultyChooser = new ChooseDifficulty();
        scorePanel = new ScorePanel();
        creditsPanel = new CreditsPanel();
        navPanel = makeNavPanel();

        // add to controller
        viewController.addView(mainMenu, ViewController.HOME);
        viewController.addView(gamePanel, GAME_PANEL);
        viewController.addView(scorePanel, SCORE_PANEL);
        viewController.addView(creditsPanel, CREDITS_PANEL);
        viewController.addView(difficultyChooser.getDifficultyPanel(), ChooseDifficulty.DIFFICULTY_PANEL);
        viewController.addView(difficultyChooser.getCustomDifficultyPanel(), ChooseDifficulty.CUSTOM_PANEL);

        // add cardPanel and navPanel to the frame
        add(cardPanel);
        add(navPanel, BorderLayout.NORTH);
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 32){
                    gamePanel.pause();
                }
            }
        });
    }

    private JPanel makeNavPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(109, 139, 185));

        // navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.LINE_AXIS));
        CustomButton back = new CustomButton("Back", 5, 25);
        CustomButton home = new CustomButton("Main Menu", 5, 25);
        
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                viewController.previous();
            }
        });
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                viewController.home();
            }
        });
        navPanel.add(back);
        navPanel.add(home);
        return navPanel;
    }

    public void start(){
        viewController.home();
        validate();
        pack();
        setVisible(true);
    }
}
