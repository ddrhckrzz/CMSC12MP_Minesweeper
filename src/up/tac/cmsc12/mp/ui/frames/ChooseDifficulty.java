package up.tac.cmsc12.mp.ui.frames;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.InputMismatchException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import up.tac.cmsc12.mp.ui.GBCUtils;
import up.tac.cmsc12.mp.ui.ViewController;
import up.tac.cmsc12.mp.ui.buttons.CustomButton;

public class ChooseDifficulty {
    public static final String DIFFICULTY_PANEL = "Difficulty Panel";
    public static final String CUSTOM_PANEL = "Custom Difficulty Panel";
    private JPanel difficultyPanel;
    private JPanel customDifficultyPanel;
    private JTextField rowsField;
    private JTextField colsField;
    private JTextField totalMinesField;
    private ViewController controller;

    public ChooseDifficulty(ViewController controller) {
        this.controller = controller;
        difficultyPanel = makeDifficultyPanel();
        customDifficultyPanel = makeCustomDifficultyPanel();
        controller.addView(difficultyPanel, DIFFICULTY_PANEL);
        controller.addView(customDifficultyPanel, CUSTOM_PANEL);
    }

    private JPanel makeDifficultyPanel(){
        // panels within panels within panels holy f*ck
        JPanel buttonsPanel = new JPanel();
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new GridBagLayout());

        buttonsPanel.setBackground(null); //around the difficulty buttons
        wrapper.setBackground(null); //the empty space

        buttonsPanel.setMaximumSize(new Dimension(360, 510));
        // making the different buttons
        CustomButton beginner = new CustomButton("BEGINNER", 5, 25);
        CustomButton intermediate = new CustomButton("INTERMEDIATE", 5, 25);
        CustomButton expert = new CustomButton("EXPERT", 5, 25);
        CustomButton master = new CustomButton("MASTER", 5, 25);
        CustomButton legend = new CustomButton("LEGEND", 5, 25);
        CustomButton custom = new CustomButton("CUSTOM", 5, 25);
        // set ActionListeners
        beginner.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                generateGamePanel(1);
            }
        });
        intermediate.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                generateGamePanel(2);
            }
        });
        expert.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                generateGamePanel(3);
            }
        });
        master.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                generateGamePanel(4);
            }
        });
        legend.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                generateGamePanel(5);
            }
        });
        custom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                controller.view(CUSTOM_PANEL);
            }
        });
        // adding buttons to the button panel
        // GridBagConstraints is amazing but man...
        GridBagConstraints c = new GridBagConstraints();
        GBCUtils u = new GBCUtils(c);
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.BOTH;
        u.setGBC(1.0, 1.0);
        u.setGBC(0, 0);
        buttonsPanel.add(beginner, c);
        u.setGBC(0, 1);
        buttonsPanel.add(intermediate, c);
        u.setGBC(0, 2);
        buttonsPanel.add(expert, c);
        u.setGBC(0, 3);
        buttonsPanel.add(master, c);
        u.setGBC(0, 4);
        buttonsPanel.add(legend, c);
        u.setGBC(0, 5);
        buttonsPanel.add(custom, c);
        // finally...
        wrapper.add(buttonsPanel);
        return wrapper;
    }

    private JPanel makeCustomDifficultyPanel(){
        // even more panels within panels shenanigans
        
        JPanel gridwrapper = new JPanel();
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setBackground(null); //around the components
        gridwrapper.setBackground(null); //the empty space
        fieldsPanel.setLayout(new GridBagLayout());
        fieldsPanel.setMaximumSize(new Dimension(420, 270));
        gridwrapper.setLayout(new BoxLayout(gridwrapper, BoxLayout.Y_AXIS));
        // gridbag stuff again wowee
        GridBagConstraints c = new GridBagConstraints();
        GBCUtils u = new GBCUtils(c);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(6, 6, 6, 6);
        JLabel topLabel = new JLabel("CUSTOM GAME SETUP");
        JLabel rowsLabel = new JLabel("No. of Rows (max: " + GamePanel.MAX_DIMENSIONS + "): ");
        JLabel colsLabel = new JLabel("No. of Columns (max: " + GamePanel.MAX_DIMENSIONS + "): ");
        JLabel totalMinesLabel = new JLabel("No. of Mines (max: (rows * cols) - 1): ");
        CustomButton startCustom = new CustomButton("START");
        rowsField = new JTextField(10);
        colsField = new JTextField(10);
        totalMinesField = new JTextField(10);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        c.gridwidth = 2;
        c.gridheight = 1;
        u.setGBC(0, 0);
        u.setGBC(1.0, 0.5);
        fieldsPanel.add(topLabel, c);
        c.gridheight = 1;
        c.gridwidth = 1;
        u.setGBC(0, 1, 0.5, 0.2);
        fieldsPanel.add(rowsLabel,c);
        u.setGBC(1, 1);
        fieldsPanel.add(rowsField, c);
        u.setGBC(0, 2);
        fieldsPanel.add(colsLabel, c);
        u.setGBC(1, 2);
        fieldsPanel.add(colsField, c);
        u.setGBC(0, 3);
        fieldsPanel.add(totalMinesLabel, c);
        u.setGBC(1, 3);
        fieldsPanel.add(totalMinesField, c);
        c.gridwidth = 2;
        u.setGBC(0, 4);
        fieldsPanel.add(startCustom, c);
        // add listeners to the start button
        startCustom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                generateGamePanel(0);
            }
        });
        // finally
        gridwrapper.add(fieldsPanel);
        return gridwrapper;
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
                controller.generateGameBoard(rows, cols, totalMines);
                controller.view(MainFrame.GAME_PANEL);
            } catch (InputMismatchException ime) {
                JOptionPane.showMessageDialog(controller.getParent(), "Numbers must be within specified bounds", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(controller.getParent(), "Input in the fields must be integers within specified bounds", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            controller.generateGameBoard(difficulty);
            controller.view(MainFrame.GAME_PANEL);
        }
    }
}
