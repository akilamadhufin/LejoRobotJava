//
//package app;
//
//import lejos.hardware.Button;
//import lejos.hardware.Sound;
//import lejos.hardware.lcd.LCD;
//import lejos.hardware.motor.Motor;
//import lejos.utility.Delay;
//import app.LineFollower;
//
//
//public class RunClass {
//    private static DataExchange DE;
//    private static LineFollower LFObj;
//    private static ObstacleDetector ODObj;
//    private static SoundWave soundwave;
//
//    /**
//     * 
//     * This executes the main method
//     */
//
//    public static void main(String[] args) {
//        DE = new DataExchange();
//        soundwave = new SoundWave();
//        ODObj = new ObstacleDetector(DE, soundwave);
//
//        LFObj = new LineFollower();
//
//        Thread lineFollowerThread = new Thread(LFObj);
//        Thread obstacleDetectorThread = new Thread(ODObj);
//
//        // Wait for the Enter key press to start
//       
//        while (!Button.ENTER.isDown()) {
//           // Sound.beep();
//           // Delay.msDelay(500);
//        	 LCD.drawString("Press Enter to start", 0, 1);
//            
//        }
//        LCD.clear();
//
//        // Start the threads
//        lineFollowerThread.start();
//        obstacleDetectorThread.start();
//
//        // Record the start time
//        long startTime = System.currentTimeMillis();
//
//        // Wait for Escape key press to exit
//        LCD.drawString("Press Escape to exit", 0, 1);
//        while (!Button.ESCAPE.isDown()) {}
//
//        // Stop the motors
//        Motor.C.stop(true);
//        Motor.B.stop(true);
//        
//        // Calculate final elapsed time
//        long currentTime = System.currentTimeMillis();
//        long elapsedTime = (currentTime - startTime) / 1000;
//
//        // Display final elapsed time
//        LCD.clear();
//        LCD.drawString("Final Elapsed Time:", 0, 1);
//        LCD.drawString(elapsedTime + "s", 0, 2);
//        
//        Delay.msDelay(5000);
//
//        // Clear the LCD and exit
//        LCD.clear();
//        LCD.drawString("Program finished", 0, 1);
//        Delay.msDelay(500);
//        System.exit(0);
//    }
//}






//working code
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

    public static void main(String[] args) {
        DE = new DataExchange();
        LFObj = new LineFollower(DE);
        soundwave = new SoundWave();
        ODObj = new ObstacleDetector(DE, soundwave);
        
        Thread lineFollowerThread = new Thread(LFObj);
        Thread obstacleDetectorThread = new Thread(ODObj);
        
        
        // Wait for the Enter key press to start
        LCD.drawString("Press Enter to start", 0, 1);
        while (!Button.ENTER.isDown()) {}

        // Clear the LCD before starting
        LCD.clear();

        // Start the line follower thread
        lineFollowerThread.start();
       // obstacleDetectorThread.start();
        

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
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import app.LineFollower;
//
//public class RunClass {
//    private static DataExchange DE;
//    private static LineFollower LFObj;
//    private static ObstacleDetector ODObj;
//    private static SoundWave soundwave;
//    private static int run = 0;
//
//    public static void main(String[] args) {
//        DE = new DataExchange();
//        LFObj = new LineFollower(DE);
//        soundwave = new SoundWave();
//        ODObj = new ObstacleDetector(DE, soundwave);
//
//        Thread lineFollowerThread = new Thread(LFObj);
//        Thread obstacleDetectorThread = new Thread(ODObj);
//
//        try {
//            // Query the web service for obstacle detection data
//            URL url = new URL("http://192.168.75.248:8080/rest/legoservice/getrun");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET"); // Specify the request method explicitly
//            conn.setConnectTimeout(5000); // Set a timeout for connection (5 seconds)
//            conn.setReadTimeout(5000); // Set a timeout for reading data (5 seconds)
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                InputStream is = conn.getInputStream();
//                InputStreamReader isr = new InputStreamReader(is);
//                BufferedReader br = new BufferedReader(isr);
//                String line;
//                while ((line = br.readLine()) != null) {
//                    String[] data = line.split("#");
//                    if (data.length >= 2) {
//                        // Adjust the program state based on received data
//                        run = Integer.parseInt(data[2]);
//                        LCD.drawString("State: " + run, 0, 6);
//                    }
//                }
//                // Close resources
//                br.close();
//                isr.close();
//                is.close();
//            } else {
//                System.out.println("Failed to retrieve data. Response code: " + responseCode);
//            }
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error occurred while fetching or processing data!");
//        }
//
//        // Wait for the Enter key press to start
//        LCD.drawString("Press Enter to start", 0, 1);
//        while (!Button.ENTER.isDown()) {}
//
//        // Clear the LCD before starting
//        LCD.clear();
//
//        // Start the line follower thread
//        lineFollowerThread.start();
//        obstacleDetectorThread.start();
//
//        // Main loop to check the value of run and react to other events
//        while (true) {
//            // Check if the stop button is pressed
//            if (Button.ESCAPE.isDown()) {
//                run = 0;
//            }
//
//            // Check if the value of run is 0 to stop the threads
//            if (run == 0) {
//                // Stop the motors
//                Motor.C.stop(true);
//                Motor.B.stop(true);
//
//                // Clear the LCD and exit
//                LCD.clear();
//                Delay.msDelay(500);
//                System.exit(0);
//            }
//
//            // Delay to avoid high CPU usage
//            Delay.msDelay(100);
//        }
//    }
//}
