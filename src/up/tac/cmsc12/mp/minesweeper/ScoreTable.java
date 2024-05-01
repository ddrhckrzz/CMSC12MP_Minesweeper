package up.tac.cmsc12.mp.minesweeper;

import javax.swing.table.DefaultTableModel;

public class ScoreTable extends DefaultTableModel{
    /*
     * TODO: add file I/O to this so we can save scoredata to and from a file.
     */
    public ScoreTable(){
        super(new String[]{"Rank", "Username", "Time"}, 0);
    }
}
