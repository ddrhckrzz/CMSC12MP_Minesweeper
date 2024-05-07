package up.tac.cmsc12.mp.minesweeper;
import up.tac.cmsc12.mp.ui.buttons.Cells;

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
    public static void autoClear(Cells[][] cells, int row, int col){
        int[] xAdjacency = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] yAdjacency = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        int rowToUpdate;
        int colToUpdate;
        int cellVal;
        boolean isClear;
        for(int i = 0; i<9; i++){
            rowToUpdate = row;
            colToUpdate = col;
            try{ //works like an if statement to see if index is within bounds
                rowToUpdate += xAdjacency[i];
                colToUpdate += yAdjacency[i];
                cellVal = cells[rowToUpdate][colToUpdate].getVal();
                isClear = cells[rowToUpdate][colToUpdate].isClear();
                if(cellVal != 9)
                    cells[rowToUpdate][colToUpdate].updateText();
                if(cellVal < 1){
                    if(!isClear){
                        autoClear(cells, rowToUpdate, colToUpdate);
                    }
                }    
            }
            catch(ArrayIndexOutOfBoundsException e){
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
