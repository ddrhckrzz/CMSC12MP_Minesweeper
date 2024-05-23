package up.tac.cmsc12.mp.minesweeper;

import javax.swing.JLabel;

import up.tac.cmsc12.mp.ui.ViewController;
import up.tac.cmsc12.mp.ui.buttons.Cells;
import up.tac.cmsc12.mp.ui.frames.Background;
import up.tac.cmsc12.mp.ui.frames.GamePanel;

public class Minesweeper {
    /*
     * TODO: Add logic here.
     * This should be the main Minesweeper Model (in the MVC Framework)
     * that handles the boardstate, what to do when certain actions are
     * done, how the board is generated, and also constructors for
     * different board sizes. That could also be done using some
     * integer constants that dictate what board to use maybe you can
     * use those for ez switch-case stuff within the constructor? idk
     * 
     * You can add the other stuff like the Board class that could
     * basically just hold the Minesweeper board data into a 
     * different file in this folder. If you have the VSCode
     * extensions it should automatically add the package line at
     * the very top for you.
     */
    private static Timer timer;
    private static ScoreHandler scoreHandler;
    private static Background background;
    private static ViewController controller;
    

    public static void setTimer(Timer givenTimer){
        timer = givenTimer;
    }

    public static void setScoreHandler(ScoreHandler givenScoreHandler){
        scoreHandler = givenScoreHandler;
    }

    public static void setViewController(ViewController viewcontroller){
        controller = viewcontroller;
    }
  
    public static void setBackground(Background givenBackground){
        background = givenBackground;
    }

    public static void refreshBackground(){
        background.repaint();
    }

    public static void autoClear(Cells[][] cells, int row, int col){
        int[] xAdjacency = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] yAdjacency = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        int rowToUpdate;
        int colToUpdate;
        int cellVal;
        boolean isClear, flagged;
        for(int i = 0; i<9; i++){
            rowToUpdate = row;
            colToUpdate = col;
            try{ //works like an if statement to see if index is within bounds
                rowToUpdate += yAdjacency[i]; 
                colToUpdate += xAdjacency[i];

                cellVal = cells[rowToUpdate][colToUpdate].getVal();
                isClear = cells[rowToUpdate][colToUpdate].isClear();
                flagged = cells[rowToUpdate][colToUpdate].getFlagged();

                if(flagged){
                    continue;
                }
                if(cellVal != 9){
                    cells[rowToUpdate][colToUpdate].updateText();
                }
                if(cellVal < 1 && !isClear){
                    autoClear(cells, rowToUpdate, colToUpdate);
                }    
            }
            catch(ArrayIndexOutOfBoundsException e){
            }
        }
    }

    public static void flagging(Cells cell, boolean isClear){
        JLabel minesLeft = GamePanel.getMinesLabel();
        int noOfFlags, noOfMines, val;
        boolean flagged;
        noOfFlags = Cells.getNoOfFlags();
        noOfMines = Cells.getNoOfMines();
        flagged = cell.getFlagged();
        val = cell.getVal();
        if(isClear){  //cant flag if it is a cleared cell
            return;
        }
        if(!flagged){
            if(noOfFlags == noOfMines){ //cannot flag more than the number of mines
                minesLeft.setText("Mines Left: " + (noOfMines - noOfFlags));
                return;
            }
            cell.flag();
            if(val == 9){
                Cells.addNoOfFound(1);
            }
            Cells.addNoOfFlags(1);
        }
        else{
            cell.setIcon(null);
            if(val == 9){
                Cells.addNoOfFound(-1);
            }
            Cells.addNoOfFlags(-1);
        }

        noOfFlags = Cells.getNoOfFlags();
        minesLeft.setText("Mines Left: " + (noOfMines - noOfFlags));
        refreshBackground();
        cell.revFlag(); //reverses boolean flag value
        if(Cells.getNoOfFound() == Cells.getNoOfMines()){
            victory();
        }
    }

    public static void victory(){
        timer.stopTimer();
        String name = controller.victory();
        if (name != null && !name.isBlank()) {
            scoreHandler.newScore(name, timer.getTime());
            controller.updateScores();
        }
    }

    public static void defeat(){
        timer.stopTimer();
        controller.lose();
    }
}

