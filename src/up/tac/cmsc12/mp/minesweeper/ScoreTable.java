package up.tac.cmsc12.mp.minesweeper;

import javax.swing.table.DefaultTableModel;

public class ScoreTable extends DefaultTableModel{
    /*
     * TODO: add file I/O to this so we can save scoredata to and from a file.
     */
    private int difficulty;
    public ScoreTable(int difficulty){
        super(new String[]{"Rank", "Username", "Time"}, 0);
        this.difficulty = difficulty;
    }

    private void readScores() {
        
    }
}
