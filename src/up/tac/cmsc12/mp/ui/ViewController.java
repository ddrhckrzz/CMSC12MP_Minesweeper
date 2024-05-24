package up.tac.cmsc12.mp.ui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import up.tac.cmsc12.mp.minesweeper.Minesweeper;
import up.tac.cmsc12.mp.minesweeper.ScoreTable;
import up.tac.cmsc12.mp.ui.panels.*;

public class ViewController {
    public static final String HOME = "HOME";

    private String currentView = null;
    private List<String> previousViews;
    private Map<String, Component> mapNames;
    private ScoreTable[] scores = new ScoreTable[5];

    private MainFrame mainFrame;
    private Container parent;
    private CardLayout cardLayout;

    public ViewController(MainFrame mainFrame, CardLayout cardLayout) {
        this.mainFrame = mainFrame;
        parent = mainFrame.getCardPanel();
        this.cardLayout = cardLayout;
        previousViews = new ArrayList<>(5);
        mapNames = new HashMap<>(10);
        Thread t = new Thread(){
            public void run(){
                while(true){
                    Minesweeper.refreshBackground();
                }    
            }
        };
        t.start();
    }

    public Container getParent() {
        return parent;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JFrame getFrame() {
        return mainFrame;
    }

    public void addView(Component comp, String name) {
        mapNames.put(name, comp);
        getParent().add(comp, name);
    }

    public void removeView(Component comp, String name) {
        mapNames.remove(name);
        getParent().remove(comp);
    }

    public void setScoreArray(ScoreTable[] scores) {
        this.scores = scores;
    }

    public void updateScores() {
        for (ScoreTable score : scores) {
            score.updateTable();
        }
    }

    public String victory() {
        String name = JOptionPane.showInputDialog("Enter name here:");
        while (name == null || name.isBlank()) {
            int c;
            c = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to cancel this operation?\n(Your score will not be recorded)", 
                "Confirm", 
                JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) {
                break;
            }
            else {
                name = JOptionPane.showInputDialog("Enter name here:");
            }
        }
        home();
        return name;
    }

    public void lose() {
        int choice = JOptionPane.showOptionDialog(null, 
                "Restart game?",
                "You lost!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"RESTART", "GO BACK TO MAIN MENU"},
                null);
        if(choice == JOptionPane.YES_OPTION) {
            previous();
            Minesweeper.refreshBackground();
        } else {
            home();
        }
    }

    private void clearCustomFields() {
        if (currentView != null) {
            if (currentView.equals(ChooseDifficulty.CUSTOM_PANEL)) {
                if (mapNames.containsKey(ChooseDifficulty.CUSTOM_PANEL)) {
                        JPanel customPanel = (JPanel) mapNames.get(ChooseDifficulty.CUSTOM_PANEL);
                        Component[] componentList = customPanel.getComponents();
                        for (Component component : componentList) {
                            if (component instanceof JTextField) {
                                ((JTextField)component).setText("");
                            }
                        }
                    }
            }
        }
    }

    public void home() {
        clearCustomFields();
        currentView = null;
        previousViews.clear();
        getCardLayout().show(getParent(), HOME);
        if (mainFrame.isNavVisible()) {
            mainFrame.toggleNavVisiblity();
        }
    }

    public void previous() {
        clearCustomFields();
        if (!previousViews.isEmpty()) {
            if (!currentView.equals(previousViews.getLast())) {
                String name = previousViews.removeLast();
                getCardLayout().show(getParent(), name);
                currentView = name;
            } else {
                previousViews.removeLast();
                previous();
            }
        } else {
            home();
        }
    }

    public void view(String name) {
        if (mapNames.containsKey(name)) {
            if (currentView != null) {
                if (!currentView.equals(MainFrame.GAME_PANEL) ) {
                    if (!previousViews.contains(currentView)) {
                        previousViews.add(currentView);
                    }
                }
            }
            getCardLayout().show(getParent(), name);
            currentView = name;
        }
        if (!mainFrame.isNavVisible()) {
            mainFrame.toggleNavVisiblity();
        }
    }

    public void generateGameBoard(int rows, int cols, int totalMines) {
        if (mapNames.containsKey(MainFrame.GAME_PANEL)) {
            GamePanel gamePanel = (GamePanel) mapNames.get(MainFrame.GAME_PANEL);
            gamePanel.generate_board(rows, cols, totalMines);
        }
    }

    public void generateGameBoard(int difficulty) {
        if (mapNames.containsKey(MainFrame.GAME_PANEL)) {
            GamePanel gamePanel = (GamePanel) mapNames.get(MainFrame.GAME_PANEL);
            gamePanel.generate_board(difficulty);
        }
    }

    public String getCurrentView() {
        return currentView;
    }
}
