package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DifficultyPanel extends JPanel {
    public static final String DIFFICULTY_PANEL = "DIFFICULTY";
    public static final String CUSTOM_DIFFICULTY = "CUSTOM DIFFICULTY";
    private CardLayout cardLayout;
    private JPanel difficultyPanel;
    private JPanel customDifficultyPanel;
    private JTextField rowsField;
    private JTextField colsField;
    private JTextField totalMinesField;
    private GamePanel gamePanel;
    public DifficultyPanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        init_layout();
    }

    private void init_layout(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        difficultyPanel = makeDifficultyPanel();
        customDifficultyPanel = makeCustomDifficultyPanel();
        cardLayout.addLayoutComponent(difficultyPanel, DIFFICULTY_PANEL);
        cardLayout.addLayoutComponent(customDifficultyPanel, CUSTOM_DIFFICULTY);
    }

    private JPanel makeDifficultyPanel(){
        // panels within panels within panels holy f*ck
        JPanel buttonsPanel = new JPanel();
        JPanel wrapper = new JPanel();
        JPanel topPanel = new JPanel();
        wrapper.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setMaximumSize(new Dimension(360, 510));
        JButton back = new JButton("Back");
        JButton home = new JButton("Main Menu");
        // the home button and back button on the top left
        topPanel.add(back);
        topPanel.add(home);
        wrapper.add(topPanel, BorderLayout.NORTH);
        // making the different buttons
        JButton beginner = new JButton("BEGINNER");
        JButton intermediate = new JButton("INTERMEDIATE");
        JButton expert = new JButton("EXPERT");
        JButton master = new JButton("MASTER");
        JButton legend = new JButton("LEGEND");
        JButton custom = new JButton("CUSTOM");
        // TODO: add actonlisteners to all of these

        // adding buttons to the button panel
        // GridBagConstraints is amazing but man...
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.BOTH;
        setGBC(c, 1.0, 1.0);
        buttonsPanel.add(beginner, c);
        buttonsPanel.add(intermediate, c);
        buttonsPanel.add(expert, c);
        buttonsPanel.add(master, c);
        buttonsPanel.add(legend, c);
        buttonsPanel.add(custom, c);
        // finally...
        wrapper.add(buttonsPanel, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel makeCustomDifficultyPanel(){
        // even more panels within panels shenanigans
        JPanel fieldsPanel = new JPanel();
        JPanel wrapper = new JPanel();
        JPanel topPanel = new JPanel();
        wrapper.setLayout(new BorderLayout());
        fieldsPanel.setLayout(new GridBagLayout());
        fieldsPanel.setMaximumSize(new Dimension(360, 510));
        JButton back = new JButton("Back");
        JButton home = new JButton("Main Menu");
        // the home button and back button on the top left
        topPanel.add(back);
        topPanel.add(home);
        wrapper.add(topPanel, BorderLayout.NORTH);
        // gridbag stuff again wowee
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        setGBC(c, 1.0, 1.0);
        JLabel topLabel = new JLabel("CUSTOM GAME SETUP");
        JLabel rowsLabel = new JLabel("No. of Rows (max: 250): ");
        JLabel colsLabel = new JLabel("No. of Columns (max: 250): ");
        JLabel totalMinesLabel = new JLabel("No. of Mines (max: (rows * cols) - 1): ");
        JButton startCustom = new JButton("START");
        rowsField = new JTextField(10);
        colsField = new JTextField(10);
        totalMinesField = new JTextField(10);
        c.gridwidth = 2;
        c.gridheight = 2;
        setGBC(c, 0, 0);
        fieldsPanel.add(topLabel, c);
        c.gridheight = 1;
        c.gridwidth = 1;
        setGBC(c, 0, 1, 0.5, 0.5);
        fieldsPanel.add(rowsLabel,c);
        setGBC(c, 1, 1);
        fieldsPanel.add(rowsField, c);
        setGBC(c, 0, 2);
        fieldsPanel.add(colsLabel, c);
        setGBC(c, 1, 2);
        fieldsPanel.add(colsField, c);
        setGBC(c, 0, 3);
        fieldsPanel.add(totalMinesLabel, c);
        setGBC(c, 1, 3);
        fieldsPanel.add(totalMinesField, c);
        c.gridwidth = 2;
        setGBC(c, 0, 4);
        fieldsPanel.add(startCustom, c);
        // finally
        wrapper.add(fieldsPanel, BorderLayout.NORTH);
        return wrapper;
    }

    private void add_panels(){
        add(difficultyPanel);
        add(customDifficultyPanel);
    }

    private void setGBC(GridBagConstraints gbc, int gridx, int gridy){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
    }

    private void setGBC(GridBagConstraints gbc, int gridx, int gridy, double weightx, double weighty){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }

    private void setGBC(GridBagConstraints gbc, double weightx, double weighty){
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }

    public void goTo(String key){
        cardLayout.show(this, key);
    }

    public void clearFields(){
        rowsField.setText("");
        colsField.setText("");
        totalMinesField.setText("");
    }
}
