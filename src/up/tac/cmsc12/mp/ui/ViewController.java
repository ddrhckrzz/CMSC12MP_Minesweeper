package up.tac.cmsc12.mp.ui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewController {
    public static final String HOME = "HOME";

    private String currentView = null;
    private List<String> previousViews;
    private Map<String, Component> mapNames;

    private Container parent;
    private CardLayout cardLayout;

    public ViewController(Container parent, CardLayout cardLayout) {
        this.parent = parent;
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

    public void goHome() {
        currentView = null;
        previousViews.clear();
        getCardLayout().show(getParent(), HOME);
    }

    public void previousView() {
        if (!previousViews.isEmpty()) {
            String name = previousViews.removeLast();
            getCardLayout().show(getParent(), name);
            currentView = name;
        } else {
            goHome();
        }
    }

    public void goView(String name) {
        if (mapNames.containsKey(name)) {
            if (currentView != null) {
                previousViews.add(currentView);
            }
            getCardLayout().show(getParent(), name);
            currentView = name;
        }
    }
}
