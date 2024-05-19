package up.tac.cmsc12.mp.ui.buttons;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import up.tac.cmsc12.mp.minesweeper.*;

public class CellListener extends MouseAdapter{
    private Cells[][] board;
    private Cells cell;
    private int row, col;
    private static boolean first_clicked = false;

    public CellListener(Cells[][] board, Cells cell, int row, int col){
        this.board = board;
        this.cell = cell;
        this.row = row;
        this.col = col;
        cell.addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)){
            clearCell();          
        }
        else if(SwingUtilities.isRightMouseButton(e)){
            flagCell();            
        }
        if(!first_clicked){
            first_clicked = true;
        }
    }

    public void clearCell(){
        if(cell.getFlagged()){ //cant clear cells if flagged
            return;
        }
        cell.updateText();
        if(cell.getVal()==0){ //auto clear only executes if the cell pressed is empty
            Minesweeper.autoClear(board, row, col);
        }
    }
    public void flagCell(){
        cell.setFlagged();
    }

    public static boolean getFirstClicked(){
        return first_clicked;
    }
}
