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
        } else if (key.equals("Start")) {
            mainFrame.goTo(MainFrame.GAME_PANEL);
        } else if (key.equals("Leaderboards")) {
            mainFrame.goTo(MainFrame.SCORE_PANEL);
        } else if (key.equals("Options")) {
            mainFrame.goTo(MainFrame.OPTIONS_PANEL);
        }
    }

}
