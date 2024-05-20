package up.tac.cmsc12.mp.minesweeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreHandler {
    final static String PATH = "src\\up\\tac\\cmsc12\\mp\\scorefiles\\";
    File file;
    public ScoreHandler(){
        file = new File(PATH + "SampleDifficultyScores.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readScores(){

    }

    public void newScore(String nameToSort, int timeToSort){
        int time, lineNumber = 0;
        String temp, toWrite;
        String[] line = new String[2];
        boolean isLast = true;
        Scanner scan;
        try {
            scan = new Scanner(file);
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
            try (FileWriter writer = new FileWriter(PATH + "SampleDifficultyScores.txt", true)){
                writer.write("\n" + toWrite);
                return;
            } catch (IOException e) {
            }
        }
        
        try {
            File tempFile = new File(PATH + "tempScoreFile.txt");
            FileWriter tempFileWriter = new FileWriter(PATH + "tempScoreFile.txt");
            scan = new Scanner(file); //reset scanners to top of file
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
            FileWriter writer = new FileWriter(PATH + "SampleDifficultyScores.txt");
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
            if(tempFile.delete()){
                System.out.println("Deleted");
            }
            else {
                System.out.println("not deleted");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        scan.close();

    }
}
