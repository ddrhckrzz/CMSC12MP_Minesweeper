package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    /*
     * TODO: Complete this thing idk
     * 
     * To access the assets folder from here the path is:
     * "../../../../../../assets/"
     * ... a bit long may have to move the assets folder out of here later.
     * 
     */
    // environment constants
    private final static String TITLENAME = "Minesweeper!";
    public final static String MAIN_PANEL = "Main Menu";
    public final static String GAME_PANEL = "Game Panel";
    public final static String OPTIONS_PANEL = "Options Panel";
    public final static String SCORE_PANEL = "Score Panel";

    // class variables
    private CardLayout cardLayout;
    private MainPanel mainMenu;
    private OptionsPanel optionsPanel;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;

    public MainFrame(){
        // TODO: Add other UI stuff here and in this folder in general.
        setTitle(TITLENAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init_layout();
        init_panels();
    }

    private void init_layout(){
        cardLayout = new CardLayout(10,10);
        setLayout(cardLayout);
        setPreferredSize(new Dimension(640, 480));
        setSize(getPreferredSize());
    }

    private void init_panels(){
        mainMenu = new MainPanel();
        cardLayout.addLayoutComponent(mainMenu, MAIN_PANEL);
        add(mainMenu);
    }

    public void start(){
        cardLayout.show(getContentPane(), MAIN_PANEL);
        setVisible(true);
    }

    public void goTo(String key){
        cardLayout.show(getContentPane(), key);
    }
}
