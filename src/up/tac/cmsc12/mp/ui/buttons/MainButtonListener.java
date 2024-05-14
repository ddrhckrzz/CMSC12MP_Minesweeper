package up.tac.cmsc12.mp.ui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import up.tac.cmsc12.mp.ui.frames.MainFrame;

public class MainButtonListener implements ActionListener {
    private MainFrame mainFrame;
    
    public MainButtonListener(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String key = actionEvent.getActionCommand();
        if (key.equals("Exit")) {
            System.exit(0);
        } else if (key.equals("New Game")) {
            mainFrame.goTo(MainFrame.GAME_PANEL);
        } else if (key.equals("Leaderboards")) {
            mainFrame.goTo(MainFrame.SCORE_PANEL);
        } else if (key.equals("Options")) {
            mainFrame.goTo(MainFrame.OPTIONS_PANEL);
        } else if (key.equals("Back")) {
            mainFrame.goBack();
        } else if (key.equals("Main Menu")) {
            mainFrame.goHome();
        }
    }

}
