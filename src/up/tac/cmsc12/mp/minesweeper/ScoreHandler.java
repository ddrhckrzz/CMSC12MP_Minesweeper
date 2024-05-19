package up.tac.cmsc12.mp.minesweeper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreHandler {
    final static String PATH = "src\\up\\tac\\cmsc12\\mp\\scorefiles\\";
    public ScoreHandler(){
        File file = new File(PATH + "SampleDifficultyScores.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readScores(){

    }

    public void newScore(String name, int time){
        try (FileWriter writer = new FileWriter(PATH + "SampleDifficultyScores.txt", true)) {
            writer.write(time + ", " + name + "\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
