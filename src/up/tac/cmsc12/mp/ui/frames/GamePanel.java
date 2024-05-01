package up.tac.cmsc12.mp.ui.frames;

import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.buttons.Cells;

public class GamePanel extends JPanel {
    private static final int DEFAULT_ROWS = 9;
    private static final int DEFAULT_COLS = 9;
    private static final int DEFAULT_NO_OF_MINES = 10;
    private int rows;
    private int cols;
    private Cells[][] board;

    public GamePanel(){
        rows = DEFAULT_ROWS;
        cols = DEFAULT_COLS;
        board = new Cells[rows][cols];
    }
}
