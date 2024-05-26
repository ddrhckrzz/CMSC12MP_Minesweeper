package up.tac.cmsc12.mp.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.Timer;

import up.tac.cmsc12.mp.ui.buttons.CellListener;

public class TimeHandler{
    private int timeSeconds;
    private boolean running, paused;
    private JLabel timerLabel;
    private ActionListener timeListener;
    private Timer swingTimer;

    public TimeHandler(JLabel timerLabel){
        timeSeconds = 0;
        running = false;
        paused = false;
        this.timerLabel = timerLabel;
        if (timeListener == null) {
            setTimeListener();
        }
        swingTimer = new Timer(1000, timeListener);
    }

    private void setTimeListener() {
        timeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CellListener.getFirstClicked() == false){
                    return;
                }
                timeSeconds += 1;
                updateTime();
            }
        };
    }

    public boolean isRunning() {
        return running;
    }

    public void rerun(){
        paused = false;
        running = false;
        timeSeconds = 0;
        updateTime();
        CellListener.resetFirstClicked();
    }

    public void startTimer() {
        swingTimer.start();
    }

    public void continueTimer() {
        startTimer(); // both do same thing for swing timer
    }

    public void pauseTimer() {
        stopTimer(); // both do the same thing for the swing timer
    }

    // @Override
    // public void run(){
    //     while(true){
    //         if(!running || paused || !CellListener.getFirstClicked()){//essentially disables the thread when paused
    //             // System.out.print(""); //i have no idea why i need this line of code im losing it
    //             // continue;
    //         } else {
    //             try {
    //                 Thread.sleep(1000);
    //                 timeSeconds += 1;
    //             } catch (InterruptedException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //         updateTime();
    //     }
    // }

    public void stopTimer(){
        swingTimer.stop();
    }

    public void togglePause(){
        if (paused) {
            continueTimer();
        } else {
            pauseTimer();
        }
        paused = !paused;
    }

    public int getTime(){
        return timeSeconds;
    }

    public void updateTime(){
        int timeMinutes = timeSeconds / 60;
        int timeHours = timeMinutes / 60;
        String text = "Time Elapsed: ";
        if (timeHours > 0) {
            text += timeHours + "h ";
        }
        if (timeMinutes > 0) {
            text += (timeMinutes % 60) + "m ";
        }
        text += (timeSeconds % 60) + "s";
        timerLabel.setText(text);
    }
}
