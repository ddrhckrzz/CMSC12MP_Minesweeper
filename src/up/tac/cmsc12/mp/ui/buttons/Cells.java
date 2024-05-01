package up.tac.cmsc12.mp.ui.buttons;

import java.awt.Insets;

import javax.swing.JButton;

public class Cells extends JButton {
    private int val; // 0 means empty, 9 means mine, everything else just tells how many mines there are around it

    public Cells(){
        setFocusable(false);
        setMargin(new Insets(0, 0, 0, 0));
    }

    public void setVal(int val){
        this.val = val;
    }

    public int getVal(){
        return val;
    }

    public void updateText(){
        if (val==0) {
            setText("");
        } else if (val < 9) {
            setText(String.valueOf(val));
        } else {
            setText("💣");
        }
        setEnabled(false);
    }

}
