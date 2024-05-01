package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.buttons.Cells;

public class GamePanel extends JPanel {
    // TODO: finish logic and stuff.
    private static final int DEFAULT_ROWS = 9;
    private static final int DEFAULT_COLS = 9;
    private static final int DEFAULT_NO_OF_MINES = 10;
    private int rows;
    private int cols;
    private int totalMines;
    private Cells[][] board;
    private JPanel topPanel;
    private JPanel boardPanel;
    private JPanel bottomPanel;

    /**
     * Default constructor that uses default board size
     * of 9x9 and only having 10 mines total.
     * 
     * Currently not fully implemented yet.
     */
    public GamePanel(){
        rows = DEFAULT_ROWS;
        cols = DEFAULT_COLS;
        totalMines = DEFAULT_NO_OF_MINES;
        board = new Cells[rows][cols];
        init_layout();
    }

    private void init_layout(){
        setLayout(new BorderLayout());
        init_topPanel();
        add(topPanel, BorderLayout.NORTH);
        init_board();
        add(boardPanel, BorderLayout.CENTER);
        init_bottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void init_topPanel(){
        topPanel = new JPanel();
        JLabel timer = new JLabel("timer");
        timer.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(timer);
    }

    private void init_bottomPanel(){
        bottomPanel = new JPanel();
        JLabel minesLeft = new JLabel("Mines Left: ");
        minesLeft.setHorizontalAlignment(JLabel.CENTER);
        bottomPanel.add(minesLeft);
    }

    private void init_board(){
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cells();
                boardPanel.add(board[i][j]);
            }
        }
    }
}
