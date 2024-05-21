package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.View;

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
    private MainPanel mainMenu;
    private OptionsPanel optionsPanel;
    private GamePanel gamePanel;
    private ChooseDifficulty difficultyPanel;
    private ScorePanel scorePanel;
    private String currentView;

    public MainFrame(){
        // TODO: Add other UI stuff here and in this folder in general.
        setTitle(TITLENAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init_layout();
        add_panels();
    }

    private void init_layout(){
        cardLayout = new CardLayout(10,10);
        setLayout(cardLayout);
        viewController = new ViewController(getContentPane(), cardLayout);
        setPreferredSize(new Dimension(640, 480));
        setSize(getPreferredSize());
    }


    private void add_panels() {
        viewController.setNavPanel(makeNavPanel());
        mainMenu = new MainPanel();
        gamePanel = new GamePanel();
        difficultyPanel = new ChooseDifficulty(viewController);
        mainMenu.bind_buttons(viewController);

        // add to controller
        viewController.addView(mainMenu, ViewController.HOME);
        viewController.addView(gamePanel, GAME_PANEL);
    }

    private JPanel makeNavPanel() {
        JPanel navPanel = new JPanel();
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
        navPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        navPanel.add(back);
        navPanel.add(home);
        return navPanel;
    }

    public void start(){
        viewController.home();
        setVisible(true);
    }
}
