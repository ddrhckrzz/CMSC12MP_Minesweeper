package up.tac.cmsc12.mp.ui.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private MainFrame mainFrame;
    
    public ButtonListener(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String key = actionEvent.getActionCommand();
        // TODO: finish action listener stuff
        if (key.equals("Exit")) {
            mainFrame.dispose();
        }
    }

}
