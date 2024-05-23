package up.tac.cmsc12.mp.ui.frames;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.GBCUtils;
import up.tac.cmsc12.mp.ui.ViewController;
import up.tac.cmsc12.mp.ui.buttons.CustomButton;

public class CreditsPanel extends JPanel {
    ViewController controller;

    public CreditsPanel(ViewController controller) {
        this.controller = controller;
        init_layout();
        add_buttons();
    }

    private void init_layout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0, 0));
    }

    private void add_buttons() {
        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setLayout(new GridBagLayout());
        buttonWrapper.setBackground(new Color(0, 0, 0, 0));
        GridBagConstraints c = new GridBagConstraints();
        GBCUtils gbcu = new GBCUtils(c);

        CustomButton credits = new CustomButton("CREDITS");
        CustomButton avila = new CustomButton("AVILA, ALEX IAN");
        CustomButton lagramada = new CustomButton("LAGRAMADA, JUDE ANTHONY");
        CustomButton lagarto = new CustomButton("LAGARTO, NYK DAVID");
        CustomButton martillo = new CustomButton("MARTILLO, ANDREI ERNEST");

        gbcu.setInsets(4, 0, 4, 0);
        gbcu.setAnchorAndFill(GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        int gridx = 0, gridy = 0;
        gbcu.setGBC(gridx,gridy);
        buttonWrapper.add(credits, c);
        gridy++;
        gbcu.setGBC(gridx, gridy);
        buttonWrapper.add(avila, c);
        gridy++;
        gbcu.setGBC(gridx, gridy);
        buttonWrapper.add(lagramada, c);
        gridy++;
        gbcu.setGBC(gridx, gridy);
        buttonWrapper.add(lagarto, c);
        gridy++;
        gbcu.setGBC(gridx, gridy);
        buttonWrapper.add(martillo, c);

        add(buttonWrapper);
    }
}
