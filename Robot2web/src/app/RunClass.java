package app;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;
import app.LineFollower;

public class RunClass {
    private static DataExchange DE;
    private static LineFollower LFObj;
    private static ObstacleDetector ODObj;
    private static SoundWave soundwave;
    private static SendRobotData robotdata;

    public static void main(String[] args) {
        DE = new DataExchange();
        LFObj = new LineFollower(DE);
        soundwave = new SoundWave();
        ODObj = new ObstacleDetector(DE, soundwave);
        robotdata = new SendRobotData();
        
        Thread lineFollowerThread = new Thread(LFObj);
        Thread obstacleDetectorThread = new Thread(ODObj);
        Thread sendRobotDataThread = new Thread(robotdata);
        
        
//      Wait for the Enter key press to start
        LCD.drawString("Press Enter to start", 0, 1);
        while (!Button.ENTER.isDown()) {}

//      Clear the LCD before starting
        LCD.clear();

//      Start the line follower thread
        lineFollowerThread.start();
        obstacleDetectorThread.start();
        
        //Yashodha
        robotdata.setLineFollower(LFObj);
        sendRobotDataThread.start();
        
//      Wait for Escape key press to exit
//      LCD.drawString("Press Escape to exit", 0, 4);
        while (!Button.ESCAPE.isDown()) {}

//      Stop the motors
        Motor.C.stop(true);
        Motor.B.stop(true);

//      Clear the LCD and exit
        LCD.clear();
//      LCD.drawString("Program finished", 0, 1);
        Delay.msDelay(500);
        System.exit(0);
    }
}


