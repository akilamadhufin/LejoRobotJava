package app;

import lejos.hardware.Sound;

/**
 * <b>Date: </b> 02/04/2024
 * 
 * @author
 * <ul>
 * <li>Yashodha</li>
 * </ul>
 * 
 * @version 1.0
 * <br>
 * <br>

 * <b>Functionality: </b> <br>
 * The SoundWave.java class likely handles sound output on the robot, 
 * enabling it to emit auditory signals for various purposes such as signalling events, 
 * providing feedback, or alerting about obstacles..
 */

public class SoundWave implements Runnable {
    @Override
    public void run() {
        int[] notes = {4, 25, 500, 600, 700, 800, 900,1000,50,40,20,4,4, 25, 500, 600, 700, 800, 900,1000,50,40,20,4};
        int[] timing = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};

        int totalTiming = 0;

        for (int i = 0; i < timing.length; i++) {
            int duration = timing[i]; // Get the duration at index 'i'
            totalTiming = totalTiming + duration; // Add the current duration to the total
        }

        long startTime = System.currentTimeMillis();
        long currentTime = startTime;

        while(currentTime - startTime < totalTiming) {
            for(int i = 0; i < notes.length; i++) {
                Sound.playTone(notes[i], timing[i]);              
                try {
                    Thread.sleep(timing[i]);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }               
                currentTime = System.currentTimeMillis();     
            }
        }
    }
}
