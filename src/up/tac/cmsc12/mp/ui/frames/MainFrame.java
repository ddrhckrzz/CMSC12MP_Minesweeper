package up.tac.cmsc12.mp.ui.frames;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    /*
     * TODO: Complete this thing idk
     * 
     * To access the assets folder from here the path is:
     * "../../../../../../assets/"
     * ... a bit long may have to move the assets folder out of here later.
     * 
     */
    private String title_name;
    public MainFrame(){
        // TODO: Add other UI stuff here and in this folder in general.
        title_name = "Minesweeper!";
        setTitle(title_name);
    }

    public void start(){
        setVisible(true);
    }

}
