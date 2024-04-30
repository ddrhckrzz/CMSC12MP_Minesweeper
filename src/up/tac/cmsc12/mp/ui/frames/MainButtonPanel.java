package up.tac.cmsc12.mp.ui.frames;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainButtonPanel extends JPanel {
    private GridLayout layout;
    private JButton startButton;
    private JButton scoreButton;
    private JButton optionsButton;
    private JButton exitButton;
    public MainButtonPanel(){
        init_layout();
        init_buttons();
    }

    private void init_layout(){
        layout = new GridLayout(0,1, 20, 20);
        setLayout(layout);
        setBorder(new EmptyBorder(40, 20, 20 , 40));
    }

    private void init_buttons(){
        startButton = new JButton("New Game");
        scoreButton = new JButton("Leaderboards");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");
        add(startButton);
        add(scoreButton);
        add(optionsButton);
        add(exitButton);
    }

    protected void bind_buttons(){
        // TODO: finish logic of this, I'm not too sure if this'll be needed
    }
}
