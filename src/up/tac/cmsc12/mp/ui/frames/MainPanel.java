package up.tac.cmsc12.mp.ui.frames;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

    private JLabel logoPane = new JLabel("MINESWEEPER");
    private MainButtonPanel buttons;

    public MainPanel(){
        init_layout();
        add_panels();
    }

    private void init_layout(){
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxlayout);
    }

    private void add_panels(){
        buttons = new MainButtonPanel();
        add(logoPane);
        add(buttons);
    }
}
