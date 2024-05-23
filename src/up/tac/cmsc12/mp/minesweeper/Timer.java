package up.tac.cmsc12.mp.minesweeper;

import java.text.DecimalFormat;

import javax.swing.JLabel;

import up.tac.cmsc12.mp.ui.buttons.CellListener;

public class Timer extends Thread{
    private int timeSeconds = 0;
    private boolean running, paused;
    JLabel timerLabel;
    DecimalFormat rounder = new DecimalFormat("0.000");

    public Timer(JLabel timerLabel){
        running = true;
        paused = false;
        this.timerLabel = timerLabel;
        this.start();
    }

    public void rerun(){
        running = true;
        timeSeconds = 0;
        CellListener.resetFirstClicked();
    }

    @Override
    public void run(){
        while(true){
            if(!running || paused || !CellListener.getFirstClicked()){//essentially disables the thread when paused
                System.out.print(""); //i have no idea why i need this line of code im losing it
                continue;
            }
                        
            timerLabel.setText("Time Elapsed: " + timeSeconds + "s");
            Minesweeper.refreshBackground();
            try {
                Thread.sleep(1000);
                timeSeconds += 1;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e){
                System.out.println("the fuck is happening");
            }
        }
    }

    public void stopTimer(){
        running = false;
    }

    public void togglePause(){
        paused = !paused;
    }

    public int getTime(){
        return timeSeconds;
    }
}
