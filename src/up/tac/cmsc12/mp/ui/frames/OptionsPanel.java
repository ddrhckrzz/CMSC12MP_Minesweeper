package up.tac.cmsc12.mp.ui.frames;

import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.ViewController;

public class OptionsPanel extends JPanel {
    ViewController controller;

    public OptionsPanel(ViewController controller) {
        this.controller = controller;
        init_layout();
    }

    private void init_layout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void add_buttons() {
        // TODO: add functionality here.
        JPanel buttonPanel = new JPanel(new GridBagLayout());
    }
}
