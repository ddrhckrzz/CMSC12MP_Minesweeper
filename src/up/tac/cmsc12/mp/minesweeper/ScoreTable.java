package up.tac.cmsc12.mp.minesweeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

public class ScoreTable extends DefaultTableModel{
    private int difficulty;
    public ScoreTable(int difficulty){
        super(new String[]{"Time", "Username"}, 0);
        this.difficulty = difficulty;
        readFromFile();
    }

    private void readFromFile() {
        String path = null;
        switch (difficulty) {
            case 1:
                path = ScoreHandler.PATH + "BeginnerScores.txt";
                break;
            
            case 2:
                path = ScoreHandler.PATH + "IntermediateScores.txt";
                break;

            case 3:
                path = ScoreHandler.PATH+ "ExpertScores.txt";
                break;

            case 4:
                path = ScoreHandler.PATH + "MasterScores.txt";
                break;

            case 5:
                path = ScoreHandler.PATH + "LegendScores.txt";
                break;

            default:
                System.out.println("something is wrong if you see this");
                break;
        }
        if(path != null) {
            try {
                File f = new File(path);
                if(f.exists()) {
                    Scanner reader = new Scanner(f);
                    String[] data = new String[2];
                    while(reader.hasNextLine()) {
                        data = reader.nextLine().split(", ");
                        if(!data[0].isEmpty()){
                            try {
                                int m, s;
                                int t = Integer.parseInt(data[0]);
                                m = t / 60;
                                s = t % 60;
                                if (m > 0) {
                                    data[0] = m + "m " + s + "s";
                                } else {
                                    data[0] = s + "s";
                                }
                                addRow(data);
                            } catch (NumberFormatException e) {
                                // eh
                            }
                        }
                    }
                    reader.close();
                }
            } catch (FileNotFoundException e) {
                // do nothing. it doesn't matter if the file is there or not
            }
        }
    }

    public void updateTable() {
        setRowCount(0);
        readFromFile();
    }
}
