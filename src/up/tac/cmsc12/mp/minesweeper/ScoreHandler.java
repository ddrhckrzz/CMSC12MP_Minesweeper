package up.tac.cmsc12.mp.minesweeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ScoreHandler {
    public final static String PATH = "scores\\";
    private static String PATH_TO_EDIT;
    private File file, BEGINNGER_SCROREFILE, INTERMEDIATE_SCOREFILE, EXPERT_SCOREFILE, MASTER_SCOREFILE, LEGEND_SCOREFILE, fileToEdit;
    private int difficulty;
    public ScoreHandler(int difficulty){
        this.difficulty = difficulty;
        file = new File(PATH + "SampleDifficultyScores.txt");
        BEGINNGER_SCROREFILE = new File(PATH + "BeginnerScores.txt");
        INTERMEDIATE_SCOREFILE = new File(PATH + "IntermediateScores.txt");
        EXPERT_SCOREFILE = new File(PATH + "ExpertScores.txt");
        MASTER_SCOREFILE = new File(PATH + "MasterScores.txt");
        LEGEND_SCOREFILE = new File(PATH + "LegendScores.txt");
        try {
            if(Files.createDirectories(Paths.get(PATH)) != null){ //automatically creates folder directory if DNE
                // System.out.println("Folder Directory Exists");
            }  
            file.createNewFile();
            BEGINNGER_SCROREFILE.createNewFile();
            INTERMEDIATE_SCOREFILE.createNewFile();
            EXPERT_SCOREFILE.createNewFile();
            MASTER_SCOREFILE.createNewFile();
            LEGEND_SCOREFILE.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void newScore(String nameToSort, int timeToSort){
        findFileDifficulty();
        int time, lineNumber = 0;
        String temp, toWrite;
        String[] line = new String[2];
        boolean isLast = true;
        Scanner scan;
        try {
            scan = new Scanner(fileToEdit);
        } catch (FileNotFoundException e) {
            System.out.println("Something has went wrong");
            return;
        }

        toWrite = timeToSort + ", " + nameToSort;

        if(scan.hasNextLine()){ //eats up empty space at top of file
            scan.nextLine();
        }
        while(scan.hasNextLine()){ //loop for finding the position to insert new score
            line = scan.nextLine().split(",");

            time = Integer.parseInt(line[0]);

            if(timeToSort <= time){  
                isLast = false;
                break;
            }
            lineNumber++;
        }

        if(isLast){ //catcher if new score is lastplace
            try (FileWriter writer = new FileWriter(PATH_TO_EDIT, true)){
                writer.write("\n" + toWrite);
                // System.out.println("Score Recorded"); 
                return;
            } catch (IOException e) {
            }
        }
        
        try {
            File tempFile = new File(PATH + "tempScoreFile.txt");
            FileWriter tempFileWriter = new FileWriter(PATH + "tempScoreFile.txt");
            scan = new Scanner(fileToEdit); //reset scanners to top of file
            tempFile.createNewFile();

            if(scan.hasNextLine()){  //eats up empty space at the top of the file
                scan.nextLine();
            }
            for(int i = 0; i < lineNumber; i++){  //copies all entries above inserted score
                temp = scan.nextLine();
                tempFileWriter.write("\n" + temp);
            }

            tempFileWriter.write("\n" + toWrite); //writes inserted score 
 
            while (scan.hasNextLine()) {  //copies all entries below inserted score
                temp = scan.nextLine();
                tempFileWriter.write("\n" + temp);
            }

            tempFileWriter.close(); //close writer to be able to open a scanner
            FileWriter writer = new FileWriter(PATH_TO_EDIT);
            scan = new Scanner(tempFile); //reset scanner to top of temp File
            
            if(scan.hasNextLine()){
                scan.nextLine();
            }
            while (scan.hasNextLine()){  //copies everything from the temporary file to the original file
                toWrite = scan.nextLine();
                writer.write("\n" + toWrite);
            }

            writer.close(); 
            scan.close();
            
            // if(tempFile.delete()){
            //     System.out.println("Deleted");
            // }
            // else {
            //     System.out.println("not deleted");
            // }

        } catch (IOException e) {
            e.printStackTrace();
        } 
        scan.close();
        // System.out.println("Score Recorded");
    }

    private void findFileDifficulty(){
        switch (difficulty) {
            case 1:
                fileToEdit = BEGINNGER_SCROREFILE;
                PATH_TO_EDIT = PATH + "BeginnerScores.txt";
                break;
            
            case 2:
                fileToEdit = INTERMEDIATE_SCOREFILE;
                PATH_TO_EDIT = PATH + "IntermediateScores.txt";
                break;

            case 3:
                fileToEdit = EXPERT_SCOREFILE;
                PATH_TO_EDIT = PATH + "ExpertScores.txt";
                break;

            case 4:
                fileToEdit = MASTER_SCOREFILE;
                PATH_TO_EDIT = PATH + "MasterScores.txt";
                break;

            case 5:
                fileToEdit = LEGEND_SCOREFILE;
                PATH_TO_EDIT = PATH + "LegendScores.txt";
                break;

            default:
                fileToEdit = file;
                PATH_TO_EDIT = PATH + "SampleDifficultyScores.txt";
                break;
        }
    }
}
