////
////package app;
////
////import lejos.hardware.Button;
////import lejos.hardware.Sound;
////import lejos.hardware.lcd.LCD;
////import lejos.hardware.motor.Motor;
////import lejos.utility.Delay;
////import app.LineFollower;
////
////
////public class RunClass {
////    private static DataExchange DE;
////    private static LineFollower LFObj;
////    private static ObstacleDetector ODObj;
////    private static SoundWave soundwave;
////
////    /**
////     * 
////     * This executes the main method
////     */
////
////    public static void main(String[] args) {
////        DE = new DataExchange();
////        soundwave = new SoundWave();
////        ODObj = new ObstacleDetector(DE, soundwave);
////
////        LFObj = new LineFollower();
////
////        Thread lineFollowerThread = new Thread(LFObj);
////        Thread obstacleDetectorThread = new Thread(ODObj);
////
////        // Wait for the Enter key press to start
////       
////        while (!Button.ENTER.isDown()) {
////           // Sound.beep();
////           // Delay.msDelay(500);
////        	 LCD.drawString("Press Enter to start", 0, 1);
////            
////        }
////        LCD.clear();
////
////        // Start the threads
////        lineFollowerThread.start();
////        obstacleDetectorThread.start();
////
////        // Record the start time
////        long startTime = System.currentTimeMillis();
////
////        // Wait for Escape key press to exit
////        LCD.drawString("Press Escape to exit", 0, 1);
////        while (!Button.ESCAPE.isDown()) {}
////
////        // Stop the motors
////        Motor.C.stop(true);
////        Motor.B.stop(true);
////        
////        // Calculate final elapsed time
////        long currentTime = System.currentTimeMillis();
////        long elapsedTime = (currentTime - startTime) / 1000;
////
////        // Display final elapsed time
////        LCD.clear();
////        LCD.drawString("Final Elapsed Time:", 0, 1);
////        LCD.drawString(elapsedTime + "s", 0, 2);
////        
////        Delay.msDelay(5000);
////
////        // Clear the LCD and exit
////        LCD.clear();
////        LCD.drawString("Program finished", 0, 1);
////        Delay.msDelay(500);
////        System.exit(0);
////    }
////}
//
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
        
        
        // Wait for the Enter key press to start
        LCD.drawString("Press Enter to start", 0, 1);
        while (!Button.ENTER.isDown()) {}

        // Clear the LCD before starting
        LCD.clear();

        // Start the line follower thread
        lineFollowerThread.start();
        obstacleDetectorThread.start();
        
        //Yashodha
        robotdata.setLineFollower(LFObj);
        sendRobotDataThread.start();
        

        // Notify the user that the robot is now following the line
     //   LCD.drawString("Robot is following", 0, 1);
      //  LCD.drawString("the line.", 0, 2);

        // Wait for Escape key press to exit
  //      LCD.drawString("Press Escape to exit", 0, 4);
        while (!Button.ESCAPE.isDown()) {}

        // Stop the motors
        Motor.C.stop(true);
        Motor.B.stop(true);

        // Clear the LCD and exit
        LCD.clear();
   //     LCD.drawString("Program finished", 0, 1);
        Delay.msDelay(500);
        System.exit(0);
    }
}


//package app;
//
//import lejos.hardware.Button;
//import lejos.hardware.lcd.LCD;
//import lejos.hardware.motor.Motor;
//import lejos.utility.Delay;
//
//public class RunClass {
//    private static SendRobotData sendRobotData;
//
//    public static void main(String[] args) {
//        sendRobotData = new SendRobotData();
//        
//        Thread sendRobotDataThread = new Thread(sendRobotData);
//
//        // Wait for the Enter key press to start
//        LCD.drawString("Press Enter to start", 0, 1);
//        while (!Button.ENTER.isDown()) {}
//
//        // Clear the LCD before starting
//        LCD.clear();
//
//        // Start the sendRobotData thread
//        sendRobotDataThread.start();
//
//        // Wait for Escape key press to exit
//        LCD.drawString("Press Escape to exit", 0, 1);
//        while (!Button.ESCAPE.isDown()) {}
//
//        // Stop sending data
//        sendRobotData.stopSending();
//
//        // Wait for the sendRobotData thread to finish
//        try {
//            sendRobotDataThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Clear the LCD and exit
//        LCD.clear();
//        LCD.drawString("Program finished", 0, 1);
//        Delay.msDelay(500);
//        System.exit(0);
//    }
//}

