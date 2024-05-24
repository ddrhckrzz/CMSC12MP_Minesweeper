package up.tac.cmsc12.mp.ui.panels;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.buttons.CustomButton;

public class PauseFrame extends JPanel {
    public PauseFrame(){
        super();
        CustomButton pauseLabel = new CustomButton("PAUSED", 1);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbl);
        gbc.ipadx = 200;
        gbc.ipady = 50;
        add(pauseLabel, gbc);
        setBackground(new Color(0, 0, 0, 0));
    }
}
