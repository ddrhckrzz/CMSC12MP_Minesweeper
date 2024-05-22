package up.tac.cmsc12.mp.ui.frames;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.ViewController;

public class MainButtonPanel extends JPanel{
    private GridBagLayout layout;
    private GridBagConstraints gbc;
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
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(layout);
        setMaximumSize(new Dimension(390, 540));
    }

    private void init_buttons(){
        startButton = new JButton("New Game");
        scoreButton = new JButton("Leaderboards");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;
        setGBC(0, 0, GridBagConstraints.CENTER, 0.8, 0.8, GridBagConstraints.BOTH);
        logoPane.setHorizontalAlignment(JLabel.CENTER);
        add(logoPane, gbc);
        gbc.ipadx = 20;
        gbc.ipady = 30;
        setGBC(0, 1, GridBagConstraints.CENTER, 0.4, 0.2, GridBagConstraints.BOTH);
        add(startButton, gbc);
        gbc.gridwidth = 1;
        setGBC(0, 2, 0.2, 0.2);
        add(scoreButton, gbc);
        setGBC(1, 2);
        add(optionsButton, gbc);
        gbc.gridwidth = 2;
        setGBC(0, 3, 0.4, 0.2);
        add(exitButton, gbc);
    }

    protected void bind_buttons(ViewController controller){
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.view(ChooseDifficulty.DIFFICULTY_PANEL);
            }
        });
        // scoreButton.addActionListener(mbl);
        // optionsButton.addActionListener(mbl);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void setGBC(int gridx, int gridy){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
    }

    private void setGBC(int gridx, int gridy, double weightx, double weighty){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }

    private void setGBC(int gridx, int gridy, int anchor, double weightx, double weighty, int fill){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
    }
}
