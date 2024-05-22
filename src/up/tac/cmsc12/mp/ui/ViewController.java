package up.tac.cmsc12.mp.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextField;

import up.tac.cmsc12.mp.ui.frames.*;

public class ViewController {
    public static final String HOME = "HOME";

    private String currentView = null;
    private List<String> previousViews;
    private Map<String, Component> mapNames;

    private MainFrame mainFrame;
    private Container parent;
    private CardLayout cardLayout;

    public ViewController(MainFrame mainFrame, CardLayout cardLayout) {
        this.mainFrame = mainFrame;
        parent = mainFrame.getCardPanel();
        this.cardLayout = cardLayout;
        previousViews = new ArrayList<>(5);
        mapNames = new HashMap<>(10);
    }

    public Container getParent() {
        return parent;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void addView(Component comp, String name) {
        mapNames.put(name, comp);
        getParent().add(comp, name);
    }

    public void removeView(Component comp, String name) {
        mapNames.remove(name);
        getParent().remove(comp);
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
            String name = previousViews.removeLast();
            getCardLayout().show(getParent(), name);
            currentView = name;
        } else {
            home();
        }
    }

    public void view(String name) {
        if (mapNames.containsKey(name)) {
            if (currentView != null) {
                previousViews.add(currentView);
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
