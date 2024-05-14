package up.tac.cmsc12.mp.ui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import up.tac.cmsc12.mp.ui.frames.DifficultyPanel;

public class DifficultyListener implements ActionListener{
    private DifficultyPanel diffPanel;

    public DifficultyListener(DifficultyPanel diffPanel) {
        this.diffPanel = diffPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String key = actionEvent.getActionCommand();
        if (key.equals("Back")) {
            diffPanel.goBack();
        } else if (key.equals("Main Menu")) {
            diffPanel.goHome();
        } else if (key.equals("CUSTOM")) {
            diffPanel.goTo(DifficultyPanel.CUSTOM_PANEL);
        } else if (key.equals("BEGINNER")) {
            diffPanel.generateGamePanel(1);
        } else if (key.equals("INTERMEDIATE")) {
            diffPanel.generateGamePanel(2);
        } else if (key.equals("EXPERT")) {
            diffPanel.generateGamePanel(3);
        } else if (key.equals("MASTER")) {
            diffPanel.generateGamePanel(4);
        } else if (key.equals("LEGEND")) {
            diffPanel.generateGamePanel(5);
        } else if (key.equals("START")) {
            diffPanel.generateGamePanel(0);
        }
    }

}
