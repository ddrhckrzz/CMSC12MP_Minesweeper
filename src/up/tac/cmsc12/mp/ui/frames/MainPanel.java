package up.tac.cmsc12.mp.ui.frames;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.ViewController;

public class MainPanel extends JPanel {
    private MainButtonPanel buttons;
    public MainPanel(){
        init_layout();
        add_panels();

    }

    private void init_layout(){
        // BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        // setLayout(boxlayout);
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 0, 0 ,0));
    }

    private void add_panels(){
        buttons = new MainButtonPanel();
        JPanel wrapper = new JPanel();
        wrapper.setBackground(new Color(0, 0, 0 ,0));
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.add(buttons);
        add(wrapper);
    }

    public void bind_buttons(ViewController controller){
        buttons.bind_buttons(controller);
    }
}
