package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.InputMismatchException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import up.tac.cmsc12.mp.ui.buttons.DifficultyListener;

public class DifficultyPanel extends JPanel {
    public static final String DIFFICULTY_PANEL = "DIFFICULTY";
    public static final String CUSTOM_PANEL = "CUSTOM";
    private CardLayout cardLayout;
    private JPanel difficultyPanel;
    private JPanel customDifficultyPanel;
    private JTextField rowsField;
    private JTextField colsField;
    private JTextField totalMinesField;
    @SuppressWarnings("unused") // no seriously gamePanel was used here. it's just modified cuz it just generates the board here
    private GamePanel gamePanel;
    private DifficultyListener listener;
    private MainFrame mainFrame;
    private String currentPage;
    public DifficultyPanel(MainFrame mainFrame, GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.mainFrame = mainFrame;
        listener = new DifficultyListener(this);
        init_layout();
        add_panels();
    }

    private void init_layout(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        difficultyPanel = makeDifficultyPanel();
        customDifficultyPanel = makeCustomDifficultyPanel();
        cardLayout.addLayoutComponent(difficultyPanel, DIFFICULTY_PANEL);
        cardLayout.addLayoutComponent(customDifficultyPanel, CUSTOM_PANEL);
        add_panels();
    }

    private JPanel makeDifficultyPanel(){
        // panels within panels within panels holy f*ck
        JPanel buttonsWrapperPanel = new JPanel(); // seriously f*ck Swing
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
        back.addActionListener(listener);
        home.addActionListener(listener);
        beginner.addActionListener(listener);
        intermediate.addActionListener(listener);
        expert.addActionListener(listener);
        master.addActionListener(listener);
        legend.addActionListener(listener);
        custom.addActionListener(listener);
        // adding buttons to the button panel
        // GridBagConstraints is amazing but man...
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.BOTH;
        setGBC(c, 1.0, 1.0);
        setGBC(c, 0, 0);
        buttonsPanel.add(beginner, c);
        setGBC(c, 0, 1);
        buttonsPanel.add(intermediate, c);
        setGBC(c, 0, 2);
        buttonsPanel.add(expert, c);
        setGBC(c, 0, 3);
        buttonsPanel.add(master, c);
        setGBC(c, 0, 4);
        buttonsPanel.add(legend, c);
        setGBC(c, 0, 5);
        buttonsPanel.add(custom, c);
        buttonsWrapperPanel.add(buttonsPanel);
        // finally...
        wrapper.add(buttonsWrapperPanel, BorderLayout.CENTER);
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
        JLabel topLabel = new JLabel("CUSTOM GAME SETUP");
        JLabel rowsLabel = new JLabel("No. of Rows (max: 250): ");
        JLabel colsLabel = new JLabel("No. of Columns (max: 250): ");
        JLabel totalMinesLabel = new JLabel("No. of Mines (max: (rows * cols) - 1): ");
        JButton startCustom = new JButton("START");
        rowsField = new JTextField(10);
        colsField = new JTextField(10);
        totalMinesField = new JTextField(10);
        c.gridwidth = 2;
        c.gridheight = 1;
        setGBC(c, 1.0, 1.0);
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
        // add listeners to the start button
        startCustom.addActionListener(listener);
        // finally
        wrapper.add(fieldsPanel, BorderLayout.CENTER);
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

    public void goBack(){
        if (currentPage.equals(CUSTOM_PANEL)) {
            cardLayout.last(this);
        } else {
            goHome();
        }
    }

    public void goHome(){
        mainFrame.goTo(MainFrame.MAIN_PANEL);
    }

    public void goNext(){
        cardLayout.next(this);
    }

    public void goPrevious(){
        cardLayout.previous(this);
    }

    public void goTo(String key){
        cardLayout.show(this, key);
        currentPage = key;
    }

    public void startPanel(){
        cardLayout.show(this, DIFFICULTY_PANEL);
        currentPage = DIFFICULTY_PANEL;
    }

    public void generateGamePanel(int difficulty) {
        if (difficulty == 0) {
            try {
                int rows = Integer.parseInt(rowsField.getText());
                int cols = Integer.parseInt(colsField.getText());
                int totalMines = Integer.parseInt(totalMinesField.getText());
                if (rows <= 0 | rows > GamePanel.MAX_DIMENSIONS | cols <= 0 | cols > GamePanel.MAX_DIMENSIONS) {
                    throw new InputMismatchException();
                }
                if (totalMines > (rows * cols) - 1) {
                    throw new InputMismatchException();
                }
                gamePanel.generate_board(rows, cols, totalMines);;
                mainFrame.goTo(MainFrame.GAME_PANEL);
            } catch (InputMismatchException ime) {
                JOptionPane.showMessageDialog(this, "Numbers must be within specified bounds", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Input in the fields must be integers within specified bounds", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            gamePanel.generate_board(difficulty);
            mainFrame.goTo(MainFrame.GAME_PANEL);
        }
    }

    public void clearFields(){
        rowsField.setText("");
        colsField.setText("");
        totalMinesField.setText("");
    }
}
