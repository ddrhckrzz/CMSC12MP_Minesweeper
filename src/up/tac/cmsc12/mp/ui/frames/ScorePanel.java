package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        scoreTable.setOpaque(true);
        scoreTable.setFillsViewportHeight(true);
        scoreTable.setBackground(new Color(0, 0, 0, 0));
        scoreTable.getTableHeader().setBackground(new Color(0, 0, 0, 0));
        setBackground(new Color(0, 0, 0, 0));
    }

    private void init_layout() {
        setLayout(new BorderLayout(12, 12));
    }

    private void add_panels() {
        JScrollPane holder = new JScrollPane(scoreTable);
        holder.setBackground(new Color(0, 0, 0, 0));
        add(holder, BorderLayout.CENTER);
        add(makeBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel makeBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0,0,0,0));
        JLabel label = new JLabel("Switch Difficulty Leaderboard: ");
        String[] difficulty = {"BEGINNER", "INTERMEDIATE", "EXPERT", "MASTER", "LEGEND"};
        JComboBox<String> comboBox = new JComboBox<String>(difficulty);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index = comboBox.getSelectedIndex();
                scoreTable.setModel(scores[index]);
                repaint();
            }
        });
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        bottomPanel.add(label);
        bottomPanel.add(comboBox);
        return bottomPanel;
    }
}
