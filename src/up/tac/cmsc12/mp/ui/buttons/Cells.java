package up.tac.cmsc12.mp.ui.buttons;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import up.tac.cmsc12.mp.minesweeper.Minesweeper;

public class Cells extends JButton {
    private int val; // 0 means empty, 9 means mine, everything else just tells how many mines there are around it
    private boolean isClear = false, flagged = false;
    private static int noOfMines = 0, noOfFlags = 0, noOfFound = 0;
    private int r=244,g=240,b=225;
    public static final String ICON_PATH = "icons\\Minesweeper_";
    private ImageIcon icon[] = new ImageIcon[10];
    private ImageIcon flagIcon = new ImageIcon(ICON_PATH + "flag.png");
    private ImageIcon currentIcon;

    

    private Color c = new Color(r,g,b);

    public Cells(){
        setFocusable(false);
        setMargin(new Insets(0, 0, 0, 0));
        colorButton();
        fetchIcons();
    }

    private void fetchIcons() {
        for (int i = 0; i < icon.length; i++) {
            if(i==0) {
                icon[i] = null;
            } else if (i < 9) {
                String iconpath = ICON_PATH + i + ".png";
                icon[i] = new ImageIcon(getClass().getClassLoader().getResource(iconpath));
            } else {
                String iconpath = ICON_PATH + "mine.png";
                icon[i] = new ImageIcon(getClass().getClassLoader().getResource(iconpath));
            }
            
        }
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

    public void flag() {
        setCurrentIcon(flagIcon);
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
        setCurrentIcon(icon[val]);
        if (val==9) {
            Minesweeper.defeat();
        }
        convertColor();
        setEnabled(false);
        isClear = true;
    }

    public void revealMine(){
        if (val == 9) {
            setCurrentIcon(icon[val]);
        }
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

    public void setCurrentIcon(ImageIcon currentIcon) {
        this.currentIcon = currentIcon;
    }

    public ImageIcon getCurrentIcon() {
        return currentIcon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g2d);
        if (getCurrentIcon() != null) {
            double scaleFactor = Math.min(1.0 * getWidth() / getCurrentIcon().getIconWidth(),
                                          1.0 * getHeight() / getCurrentIcon().getIconHeight());
            int scaledWidth = (int) (getCurrentIcon().getIconWidth() * scaleFactor);
            int scaledHeight = (int) (getCurrentIcon().getIconHeight() * scaleFactor);
            
            int x = (getWidth() - scaledWidth) / 2;
            int y = (getHeight() - scaledHeight) / 2;
            
            g2d.drawImage(getCurrentIcon().getImage(), x, y, scaledWidth, scaledHeight, this);
        }
        g2d.dispose();
    }
    

}
