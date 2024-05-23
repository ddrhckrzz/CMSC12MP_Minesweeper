package up.tac.cmsc12.mp.ui.frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.ViewController;

public class MainPanel extends JPanel {
    private MainButtonPanel buttons;
    public MainPanel(){
        init_layout();
        add_panels();

    }

    private void init_layout(){
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxlayout);
        setBackground(null);
    }

    private void add_panels(){
        buttons = new MainButtonPanel();
        add(buttons);
    }

    public void bind_buttons(ViewController controller){
        buttons.bind_buttons(controller);
    }
}
