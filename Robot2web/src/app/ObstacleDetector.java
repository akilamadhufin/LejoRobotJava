package app;

import lejos.hardware.Sound;
import lejos.hardware.Sounds;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * <b>Date: </b> 31.03.2024
 * 
 * @author 
 * <ul>
 * <li>Akila</li>
 * <li>Gimhani</li>
 * </ul>
 * 
 * @version 1.0
 * <br>
 * <br>

 * <b>Functionality: </b> <br>
 * The ObstacleDetect.java class is essential for enabling the robot to detect obstacles in its path using ultrasonic sensors. 
 * It allows the robot to autonomously navigate and avoid collisions by interpreting sensor data and making decisions based on predefined thresholds.
 */

public class ObstacleDetector implements Runnable {
    private DataExchange DEObj;
    private SoundWave soundwave; // Reference to SoundWave
    private EV3UltrasonicSensor ultrasonicSensor;
    private SampleProvider distanceProvider;
    private final float securityDistance = 0.25f;
    private int obstacleCount = 0; // Counter to keep track of obstacles

    public ObstacleDetector(DataExchange DE, SoundWave soundwave) {
        DEObj = DE;
        this.soundwave = soundwave;
        Port ultrasonicPort = SensorPort.S1;
        ultrasonicSensor = new EV3UltrasonicSensor(ultrasonicPort);
        distanceProvider = ultrasonicSensor.getDistanceMode();
    }

    public void run() 
    {
        float[] sample = new float[distanceProvider.sampleSize()];
        while (true) 
        {
            distanceProvider.fetchSample(sample, 0);
            float distance = sample[0];
            if (distance > securityDistance) 
            {
                // No obstacle, continue following the line
                DEObj.setCMD(1); // Continue following the line
            } 
            else 
            {
                obstacleCount++;
                if (obstacleCount == 1) 
                {
                    // First obstacle detected, avoid it
                    DEObj.setCMD(2); // Turn left and move outside the line
                    // LCD Output
                    // Play music
                    Thread musicThread = new Thread(soundwave);
                    musicThread.start();
                    LCD.drawString("First obstacle found!", 0, 1);
                    LCD.refresh();

                    // Reset obstacle count after handling the first obstacle
                    obstacleCount = 0;
                }
                else if (obstacleCount > 1) 
                {
                    // More than one obstacle encountered, stop
                    DEObj.setCMD(0); // Stop the robot
                    // LCD Output
                    LCD.clear(); // Clear the screen before displaying new message
                    Sound.beep();
                    LCD.drawString("Second obstacle found!", 0, 1);
                    LCD.refresh();
                }
            }
            Delay.msDelay(100); // Delay to avoid rapid command changes
        }
    }
    
    

}


