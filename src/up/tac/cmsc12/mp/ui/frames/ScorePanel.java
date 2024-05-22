package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import up.tac.cmsc12.mp.minesweeper.ScoreTable;
import up.tac.cmsc12.mp.ui.ViewController;

public class ScorePanel extends JPanel {
    private ViewController controller;
    private ScoreTable[] scores = new ScoreTable[5];
    private JTable scoreTable;
    public ScorePanel(ViewController controller) {
        this.controller = controller;
        for (int i = 0; i < scores.length; i++) {
            scores[i] = new ScoreTable(i + 1);
        }
        scoreTable = new JTable(scores[0]);
        init_layout();
        add_panels();
        this.controller.setScoreArray(scores);
        scoreTable.setVisible(true);
        scoreTable.setEnabled(false);
        scoreTable.getTableHeader().setReorderingAllowed(false);
    }

    private void init_layout() {
        setLayout(new BorderLayout(12, 12));
    }

    private void add_panels() {
        add(new JScrollPane(scoreTable), BorderLayout.CENTER);
        add(makeBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel makeBottomPanel() {
        JPanel bottomPanel = new JPanel();
        JLabel label = new JLabel("Switch Difficulty Leaderboard: ");
        String[] difficulty = {"BEGINNER", "INTERMEDIATE", "EXPERT", "MASTER", "LEGEND"};
        JComboBox<String> comboBox = new JComboBox<String>(difficulty);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index = comboBox.getSelectedIndex();
                scoreTable.setModel(scores[index]);
            }
        });
        bottomPanel.add(label);
        bottomPanel.add(comboBox);
        return bottomPanel;
    }
}
