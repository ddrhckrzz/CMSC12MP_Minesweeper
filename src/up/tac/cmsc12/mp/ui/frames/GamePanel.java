package up.tac.cmsc12.mp.ui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import up.tac.cmsc12.mp.ui.buttons.Cells;
import up.tac.cmsc12.mp.ui.buttons.CellListener;
import up.tac.cmsc12.mp.minesweeper.Minesweeper;
import up.tac.cmsc12.mp.minesweeper.ScoreHandler;
import up.tac.cmsc12.mp.minesweeper.Timer;

public class GamePanel extends JPanel {
    // ----------------- OVERALL BOARD CONSTANTS -------------------------- //
    private static final int DEFAULT_ROWS = 9;
    private static final int DEFAULT_COLS = 9;
    private static final int DEFAULT_NO_OF_MINES = 10;
    public static final int MAX_DIMENSIONS = 250; // width and height
    public static final int CELL_DIMENSIONS = 30;
    // ---------------- BOARD DIFFICULTY CONSTANTS ------------------------ //
    public static final int BEGINNER_DIMENSIONS = 9;
    public static final int BEGINNER_NO_OF_MINES = 10;
    public static final int INTERMEDIATE_DIMENSIONS = 16;
    public static final int INTERMEDIATE_NO_OF_MINES = 40;
    public static final int EXPERT_DIMENSIONS = 24;
    public static final int EXPERT_NO_OF_MINES = 99;
    public static final int MASTER_DIMENSIONS = 50;
    public static final int MASTER_NO_OF_MINES = 450;
    public static final int LEGEND_DIMENSIONS = 100;
    public static final int LEGEND_NO_OF_MINES = 2000;
    // ------------------------ class variables ---------------------------- //
    private int difficulty;
    private int rows;
    private int cols;
    private int totalMines;
    private Cells[][] board;
    private JPanel bottomPanel;
    private JPanel centerPane;
    private Timer Timer;
    private Font f = new Font("Impact", Font.BOLD, 25);
    private JLabel timer = new JLabel("Time Elapsed: 0s"); //moved because they need to be global to be updated(cant change text)
    private static JLabel minesLeft = new JLabel("Mines Left: ");

    /**
     * Default constructor to initialize the GamePanel
     */
    public GamePanel(){
        init_layout();
        Timer = new Timer(timer);
        timer.setFont(f);
        minesLeft.setFont(f);
        Minesweeper.setTimer(Timer);
    }

    private void init_layout(){
        setLayout(new BorderLayout());
        init_bottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void init_bottomPanel(){
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(109, 139, 185));
        timer.setHorizontalAlignment(JLabel.CENTER);
        minesLeft.setHorizontalAlignment(JLabel.CENTER);
        bottomPanel.add(timer);
        bottomPanel.add(minesLeft);
    }

    public void generate_board(){
        resetBoard();
        if (centerPane != null) {
            remove(centerPane);
        }
        centerPane = new JPanel(new GridBagLayout()); // f*ck me
        centerPane.setBackground(new Color(109, 139, 185)); // set transparent bg
        switch (difficulty) {
            case 0:
                break;
            case 1:
                rows = BEGINNER_DIMENSIONS;
                cols = BEGINNER_DIMENSIONS;
                totalMines = BEGINNER_NO_OF_MINES;
                break;
            
            case 2:
                rows = INTERMEDIATE_DIMENSIONS;
                cols = INTERMEDIATE_DIMENSIONS;
                totalMines = INTERMEDIATE_NO_OF_MINES;
                break;

            case 3:
                rows = EXPERT_DIMENSIONS;
                cols = EXPERT_DIMENSIONS;
                totalMines = EXPERT_NO_OF_MINES;
                break;

            case 4:
                rows = MASTER_DIMENSIONS;
                cols = MASTER_DIMENSIONS;
                totalMines = MASTER_NO_OF_MINES;
                break;

            case 5:
                rows = LEGEND_DIMENSIONS;
                cols = LEGEND_DIMENSIONS;
                totalMines = LEGEND_NO_OF_MINES;
                break;

            default:
                rows = DEFAULT_ROWS;
                cols = DEFAULT_COLS;
                totalMines = DEFAULT_NO_OF_MINES;
                break;
        }
        ScoreHandler sh = new ScoreHandler(difficulty);   //moved here so the scorehandle is able to accept difficulty as a parameter
        Minesweeper.setScoreHandler(sh);
        
        board = new Cells[rows][cols];
        JPanel boardPanel = new JPanel(new GridLayout(rows, cols)){
            @Override
            public Dimension getPreferredSize() {
                // squarify it lmao
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension((int)d.getWidth(), (int)d.getHeight());
                } else if (c != null && c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                int s = (w>h ? h : w);
                return new Dimension(s, s);
            }
        };
        boardPanel.setBackground(new Color(109, 139, 185));
        //boardPanel.setBorder(new LineBorder(Color.BLACK, 1));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cells();
                new CellListener(board, board[i][j], i, j);
                boardPanel.add(board[i][j]);
            }
        }
        centerPane.add(boardPanel);
        add(centerPane, BorderLayout.CENTER);
        addMines();
        revalidate();
        repaint();
    }

    public void generate_board(int difficulty) {
        this.difficulty = difficulty;
        generate_board();
    }

    public void generate_board(int rows, int cols, int totalMines) {
        difficulty = 0;
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        generate_board();
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

    public int getDifficulty() {
        return difficulty;
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

    public void resetBoard(){
        timer.setText("Time Elapsed: 0s");
        Timer.rerun();
        Cells.resetBoard();
    }
}
