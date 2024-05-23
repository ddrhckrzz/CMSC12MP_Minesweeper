package up.tac.cmsc12.mp.ui.frames;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.GBCUtils;
import up.tac.cmsc12.mp.ui.ViewController;
import up.tac.cmsc12.mp.ui.buttons.CustomButton;

public class MainButtonPanel extends JPanel{
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private CustomButton startButton;
    private JButton scoreButton;
    private JButton optionsButton;
    private JButton exitButton;
    private JLabel logoPane = new JLabel("MINESWEEPER");
    public MainButtonPanel(){
        init_layout();
        init_buttons();
    }

    private void init_layout(){
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(layout);
        setMaximumSize(new Dimension(390, 540));
    }

    private void init_buttons(){
        startButton = new CustomButton("New Game");
        scoreButton = new JButton("Leaderboards");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;
        GBCUtils u = new GBCUtils(gbc);
        u.setGBC(0, 0, GridBagConstraints.CENTER, 0.8, 0.8, GridBagConstraints.BOTH);
        logoPane.setHorizontalAlignment(JLabel.CENTER);
        add(logoPane, gbc);
        gbc.ipadx = 20;
        gbc.ipady = 30;
        u.setGBC(0, 1, GridBagConstraints.CENTER, 0.4, 0.2, GridBagConstraints.BOTH);
        add(startButton, gbc);
        gbc.gridwidth = 1;
        u.setGBC(0, 2, 0.2, 0.2);
        add(scoreButton, gbc);
        u.setGBC(1, 2);
        add(optionsButton, gbc);
        gbc.gridwidth = 2;
        u.setGBC(0, 3, 0.4, 0.2);
        add(exitButton, gbc);
    }

    protected void bind_buttons(ViewController controller){
        

        startButton.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e) {
                startButton.push();
                
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                startButton.unpush();
                //controller.view(ChooseDifficulty.DIFFICULTY_PANEL);
            }
        });

        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.view(MainFrame.SCORE_PANEL);
            }
        });
        // optionsButton.addActionListener(mbl);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
