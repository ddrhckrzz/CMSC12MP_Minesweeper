package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.ui.buttons.Cells;
import up.tac.cmsc12.mp.ui.buttons.CellListener;
import up.tac.cmsc12.mp.minesweeper.Minesweeper;
import up.tac.cmsc12.mp.minesweeper.ScoreHandler;
import up.tac.cmsc12.mp.minesweeper.Timer;

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
    private JLabel timer = new JLabel("Time Elapsed: 0s"); //moved because they need to be global to be updated(cant change text)
    private static JLabel minesLeft = new JLabel("Mines Left: ");

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
        addMines();
        Timer Timer = new Timer(timer);
        Minesweeper.giveTimer(Timer);
        ScoreHandler sh = new ScoreHandler();

        sh.newScore("newest", 20);


        Minesweeper.giveScoreHandler(sh);

    }

    public GamePanel(int rows, int cols, int totalMines){
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        board = new Cells[rows][cols];
        init_layout();
        addMines();
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
        timer.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(timer);
    }

    private void init_bottomPanel(){
        bottomPanel = new JPanel();
        minesLeft.setHorizontalAlignment(JLabel.CENTER);
        bottomPanel.add(minesLeft);
    }

    private void init_board(){
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cells();
                new CellListener(board, board[i][j], i, j);
                boardPanel.add(board[i][j]);
            }
        }
    }

    private void addMines(){
        int minesToPlace = totalMines;
        while(minesToPlace > 0){
            var randomNumber = Math.round(Math.random()* ((rows * cols) - 1)); //calculating random coordinates to place mines
            var rowCoordinate = (int) Math.floor(randomNumber / cols);
            var colCoordinate = (int) randomNumber % cols;

            if(board[rowCoordinate][colCoordinate].getVal() != 9){ //checking if random coordinate is a duplicate
                board[rowCoordinate][colCoordinate].setToMine(); //setting coordinates to be mines
                //board[rowCoordinate][colCoordinate].updateText();   //uncomment this to see mine placement
                setAdjacency(rowCoordinate, colCoordinate);
                minesToPlace--;
                //System.out.println("mine: " + minesToPlace + " coordinate: " + rowCoordinate + ", " + colCoordinate);
            }
            minesLeft.setText("Mines Left: " + totalMines);
        }
    }

    public static JLabel getMinesLabel(){
        return minesLeft;
    }

    private void setAdjacency(int rowCoordinate, int colCoordinate){
        int rowToUpdate;
        int colToUpdate;
        for(int i = 0; i<9; i++){
            int[] xAdjacency = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
            int[] yAdjacency = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
            rowToUpdate = rowCoordinate;
            colToUpdate = colCoordinate;
            try{ //works like an if statement to see if index is within bounds
                rowToUpdate += xAdjacency[i];
                colToUpdate += yAdjacency[i];
                board[rowToUpdate][colToUpdate].incrementVal();
                //board[rowToUpdate][colToUpdate].updateText(); //uncomment this to see mine adjacency
            }
            catch(ArrayIndexOutOfBoundsException e){
            }
        }
    }
}
