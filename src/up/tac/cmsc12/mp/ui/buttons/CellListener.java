package up.tac.cmsc12.mp.ui.buttons;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import up.tac.cmsc12.mp.minesweeper.Minesweeper;

public class CellListener{
    public CellListener(Cells[][] board, Cells cell, int row, int col){
        cell.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                cell.updateText();
                if(cell.getVal()==0){ //auto clear only executes if the cell pressed is empty
                    Minesweeper.autoClear(board, row, col);
                }
            }
        });
    }
}
