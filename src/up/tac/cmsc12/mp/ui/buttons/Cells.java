package up.tac.cmsc12.mp.ui.buttons;
import java.awt.Insets;
import javax.swing.JButton;

import up.tac.cmsc12.mp.minesweeper.Minesweeper;

public class Cells extends JButton {
    private int val; // 0 means empty, 9 means mine, everything else just tells how many mines there are around it
    private boolean isClear = false, flagged = false;
    private static int noOfMines = 0, noOfFlags = 0, noOfFound = 0;
    public Cells(){
        setFocusable(false);
        setMargin(new Insets(0, 0, 0, 0));
    }

    public void setVal(int val){
        this.val = val;
    }

    public void incrementVal(){
        if(val < 9)
        val++;
    }

    public void setToMine(){ //separate from setVal for code readability??
        val = 9;
        noOfMines++;
    }
    public static int getNoOfMines(){
        return noOfMines;
    }

    public void setFlagged(){
        Minesweeper.flagging(this, isClear);
        // if(noOfFound == noOfMines){
        //     Minesweeper.victory();
        //     return;
        // }
    }

    public void revFlag(){
        flagged = !flagged;
    }

    public static void addNoOfFlags(int i){
        noOfFlags += i;
    }

    public static void addNoOfFound(int i){
        noOfFound += i;
    }

    public static int getNoOfFlags(){
        return noOfFlags;
    }

    public static int getNoOfFound(){
        return noOfFlags;
    }
    
    /*public void setFlagged(){         //keep this here incase the logic goes wrong                             
        if(isClear){  //cant flag if it is a cleared cell
            return;
        }
        if(!flagged){
            if(noOfFlags == noOfMines){ //cannot flag more than the number of mines
                return;
            }
            setText("F");
            setEnabled(false);
            if(val == 9){
                noOfFound++;
            }
            noOfFlags++;
        }
        else{
            setText("");
            setEnabled(true);
            if(val == 9){
                noOfFound--;
            }
            noOfFlags--;
        }
        flagged = !flagged; //reverses boolean flag value
        if(noOfFound == noOfMines){
            System.out.println("You Win");
            return;
        }
    }*/

    public boolean getFlagged(){
       return flagged;
    }

    public int getVal(){
        return val;
    }
    
    public boolean isClear(){
        return isClear;
    }

    public void updateText(){
        if(flagged){  //cannot clear a flagged cell
            return;
        }
        if (val==0) {
            setText("");
        } else if (val < 9) {
            setText(String.valueOf(val));
        } else {
            setText("💣");
            Minesweeper.defeat();
        }
        setEnabled(false);
        isClear = true;
    }
}
