package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.minesweeper.Minesweeper;
import up.tac.cmsc12.mp.ui.ViewController;

public class MainFrame extends JFrame {
    /*
     * TODO: Complete this thing idk
     * 
     * To access the assets folder from here the path is:
     * "assets/"
     * 
     */
    // environment constants
    private final static String TITLENAME = "Minesweeper! In Java™";
    public final static String GAME_PANEL = "Game Panel";
    public final static String OPTIONS_PANEL = "Options Panel";
    public final static String SCORE_PANEL = "Score Panel";

    // class variables
    private ViewController viewController;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel navPanel;
    private MainPanel mainMenu;
    private OptionsPanel optionsPanel;
    private GamePanel gamePanel;
    private ChooseDifficulty difficultyChooser;
    private ScorePanel scorePanel;
    private String currentView;

    public MainFrame(){
        // TODO: Add other UI stuff here and in this folder in general.
        setTitle(TITLENAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init_layout();
        add_panels();
        toggleNavVisiblity(); // navigation panel is invisble by default
        Minesweeper.setViewController(viewController); // very important
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
        cardPanel = new JPanel(cardLayout);
        viewController = new ViewController(this, cardLayout);
        setPreferredSize(new Dimension(640, 480));
        setSize(getPreferredSize());
        setLocationRelativeTo(null);
    }


    private void add_panels() {
        mainMenu = new MainPanel();
        gamePanel = new GamePanel();
        difficultyChooser = new ChooseDifficulty(viewController);
        mainMenu.bind_buttons(viewController);
        scorePanel = new ScorePanel(viewController);
        navPanel = makeNavPanel();

        // add to controller
        viewController.addView(mainMenu, ViewController.HOME);
        viewController.addView(gamePanel, GAME_PANEL);
        viewController.addView(scorePanel, SCORE_PANEL);

        // add cardPanel and navPanel to the frame
        add(cardPanel);
        add(navPanel, BorderLayout.NORTH);
    }

    private JPanel makeNavPanel() {
        JPanel navPanel = new JPanel();
        // navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.LINE_AXIS));
        JButton back = new JButton("Back");
        JButton home = new JButton("Main Menu");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewController.previous();
            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewController.home();
            }
        });
        navPanel.add(back);
        navPanel.add(home);
        return navPanel;
    }

    public void start(){
        viewController.home();
        setVisible(true);
    }
}
