package up.tac.cmsc12.mp.ui.frames;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainButtonPanel extends JPanel{
    private GridLayout layout;
    private JButton startButton;
    private JButton scoreButton;
    private JButton optionsButton;
    private JButton exitButton;
    private JLabel logoPane = new JLabel("MINESWEEPER");
    public MainButtonPanel(){
        init_layout();
        init_buttons();
    }

    private void init_layout(){
        layout = new GridLayout(0,1);
        setLayout(layout);
    }

    private void init_buttons(){
        startButton = new JButton("New Game");
        scoreButton = new JButton("Leaderboards");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");
        add(logoPane);
        add(startButton);
        add(scoreButton);
        add(optionsButton);
        add(exitButton);
    }

    protected void bind_buttons(){
        // TODO: finish logic of this, I'm not too sure if this'll be needed

    }
}
