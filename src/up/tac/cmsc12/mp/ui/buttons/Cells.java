package up.tac.cmsc12.mp.ui.buttons;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import up.tac.cmsc12.mp.minesweeper.Minesweeper;

public class Cells extends JButton {
    private int val; // 0 means empty, 9 means mine, everything else just tells how many mines there are around it
    private boolean isClear = false, flagged = false;
    private static int noOfMines = 0, noOfFlags = 0, noOfFound = 0;
    private int r=244,g=240,b=225, h=getHeight();
    
    private ImageIcon icon1 = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\assets\\Minesweeper_1.png");
    private ImageIcon icon2 = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\assets\\Minesweeper_2.png");
    private ImageIcon icon3 = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\assets\\Minesweeper_3.png");
    private ImageIcon icon4 = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\assets\\Minesweeper_4.png");
    private ImageIcon icon5 = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\assets\\Minesweeper_5.png");
    private ImageIcon icon6 = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\assets\\Minesweeper_6.png");
    private ImageIcon icon7 = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\assets\\Minesweeper_7.png");
    private ImageIcon icon8 = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\assets\\Minesweeper_8.png");
    

    private Color c = new Color(r,g,b);

    public Cells(){
        setFocusable(false);
        setMargin(new Insets(0, 0, 0, 0));
        colorButton();
    }

    public static void resetBoard(){
        noOfMines = 0;
        noOfFlags = 0;
        noOfFound = 0;
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
        return noOfFound;
    }

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
            //setIcon(icon1);
            switch(val){
                case 1:
                    setIcon(icon1);
                    break;
                case 2:
                    setIcon(icon2);
                    break;
                case 3:
                    setIcon(icon3);
                    break;
                case 4:
                    setIcon(icon4);
                    break;
                case 5:
                    setIcon(icon5);
                    break;
            }
            
        } else {
            setText("💣");
            Minesweeper.defeat();
        }
        convertColor();
        setEnabled(false);
        isClear = true;
    }

    public void colorButton(){
        setBackground(c);
    }

    public void changeColor(int r,int g, int b){
        this.c = new Color(r,g,b);
        this.r = r;
        this.g = g;
        this.b = b;
        colorButton();
    }

    public void convertColor(){
        changeColor(200,200,200);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g2d);
        if (getIcon() != null) {
            double scaleFactor = Math.min(1.0 * getWidth() / getIcon().getIconWidth(),
                                          1.0 * getHeight() / getIcon().getIconHeight());
            int scaledWidth = (int) (getIcon().getIconWidth() * scaleFactor);
            int scaledHeight = (int) (getIcon().getIconHeight() * scaleFactor);
            
            int x = (getWidth() - scaledWidth) / 2;
            int y = (getHeight() - scaledHeight) / 2;
            
            g2d.drawImage(((ImageIcon) getIcon()).getImage(), x, y, scaledWidth, scaledHeight, this);
        }
        g2d.dispose();
    }
    

}
