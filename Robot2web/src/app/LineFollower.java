//package app;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import data.Path;
//import lejos.hardware.lcd.LCD;
//import lejos.hardware.motor.Motor;
//import lejos.hardware.port.SensorPort;
//import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.robotics.Color;
//import lejos.utility.Delay;
//
//public class LineFollower implements Runnable {
//
//    private volatile boolean isRunning = true;
//    private EV3ColorSensor colorSensor;
//
//    public LineFollower() {
//        colorSensor = new EV3ColorSensor(SensorPort.S3);
//    }
//
//    @Override
//    public void run() {
//        while (isRunning) {
//            try {
//                // Query the web service for line follower data
//                URL url = new URL("http://192.168.250.248:8080/rest/legoservice/getfollow");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET"); // Specify the request method explicitly
//                conn.setConnectTimeout(5000); // Set a timeout for connection (5 seconds)
//                conn.setReadTimeout(5000); // Set a timeout for reading data (5 seconds)
//
//                int responseCode = conn.getResponseCode();
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    InputStream is = conn.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(is);
//                    BufferedReader br = new BufferedReader(isr);
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        // Process the line follower data
//                        String[] data = line.split("#");
//                        if (data.length >= 6) {
//                            int leftMotorSpeed_1 = Integer.parseInt(data[2]);
//                            int rightMotorSpeed_1 = Integer.parseInt(data[3]);
//                            int leftMotorSpeed_2 = Integer.parseInt(data[4]);
//                            int rightMotorSpeed_2 = Integer.parseInt(data[5]);
//                            int targetIntensity = Integer.parseInt(data[6]);
//
//                            // Display target intensity and motor speeds on LCD
//                            LCD.clear();
//                            LCD.drawString("Intensity: " + targetIntensity, 0, 0);
//                            LCD.drawString("Left 1: " + leftMotorSpeed_1, 0, 1);
//                            LCD.drawString("Right 1: " + rightMotorSpeed_1, 0, 2);
//                            LCD.drawString("Left 2: " + leftMotorSpeed_2, 0, 3);
//                            LCD.drawString("Right 2: " + rightMotorSpeed_2, 0, 4);
//
//                            int currentIntensity = readColorIntensity();
//                            int intensityGap = targetIntensity - currentIntensity;
//                            if (Math.abs(intensityGap) >= 10) {
//                                // Turn right
//                                Motor.C.setSpeed(rightMotorSpeed_1);
//                                Motor.B.setSpeed(leftMotorSpeed_1);
//                            } else {
//                                // Turn left
//                                Motor.C.setSpeed(rightMotorSpeed_2);
//                                Motor.B.setSpeed(leftMotorSpeed_2);
//                            }
//                                                      
//                            Motor.B.forward();
//                            Motor.C.forward();
//                        } else {
//                            System.out.println("Invalid data format received: " + line);
//                        }
//                    }
//                    // Close resources
//                    br.close();
//                    isr.close();
//                    is.close();
//                } else {
//                    System.out.println("Failed to retrieve data. Response code: " + responseCode);
//                }
//                conn.disconnect();
//                Thread.sleep(100); // Adjust delay as needed
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Some problem occurred while fetching or processing data!");
//                // Add a delay to avoid continuous retries in case of errors
//                try {
//                    Thread.sleep(5000); // Wait for 5 seconds before retrying
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }
//
//
//    public void stop() {
//        isRunning = false;
//    }
//
//    private int readColorIntensity() {
//        float[] sample = new float[1];
//        colorSensor.getRedMode().fetchSample(sample, 0);
//        return (int) (sample[0] * 100);
//    }
//}



////Working Code
//package app;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import lejos.hardware.lcd.LCD;
//import lejos.hardware.motor.Motor;
//
//public class LineFollower implements Runnable {
//
//    private volatile boolean isRunning = true;
//    private DataExchange DEObj; // Added DataExchange object
//    private ColorSensor colorSensor; // Added ColorSensor object
//
//    public LineFollower(DataExchange deObj) {
//        this.DEObj = deObj; // Initialize DataExchange object
//        this.colorSensor = new ColorSensor(DEObj); // Initialize ColorSensor object
//    }
//
//    @Override
//    public void run() {
//        // Start the color sensor thread
//        Thread colorSensorThread = new Thread(colorSensor);
//        colorSensorThread.start();
//
//        while (isRunning) {
//            try {
//                // Query the web service for line follower data
//                URL url = new URL("http://192.168.75.248:8080/rest/legoservice/getfollow");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET"); // Specify the request method explicitly
//                conn.setConnectTimeout(5000); // Set a timeout for connection (5 seconds)
//                conn.setReadTimeout(5000); // Set a timeout for reading data (5 seconds)
//
//                int responseCode = conn.getResponseCode();
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    InputStream is = conn.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(is);
//                    BufferedReader br = new BufferedReader(isr);
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        // Process the line follower data
//                        String[] data = line.split("#");
//                        if (data.length >= 6) {
//                            int leftMotorSpeed_1 = Integer.parseInt(data[2]);
//                            int rightMotorSpeed_1 = Integer.parseInt(data[3]);
//                            int leftMotorSpeed_2 = Integer.parseInt(data[4]);
//                            int rightMotorSpeed_2 = Integer.parseInt(data[5]);
//                            int targetIntensity = Integer.parseInt(data[6]);
//
//                            // Display target intensity and motor speeds on LCD
//                            LCD.clear();
//                            LCD.drawString("Intensity: " + targetIntensity, 0, 0);
//                            LCD.drawString("Left 1: " + leftMotorSpeed_1, 0, 1);
//                            LCD.drawString("Right 1: " + rightMotorSpeed_1, 0, 2);
//                            LCD.drawString("Left 2: " + leftMotorSpeed_2, 0, 3);
//                            LCD.drawString("Right 2: " + rightMotorSpeed_2, 0, 4);
//
//                            int currentIntensity = DEObj.getLineIntensity();
//                            int intensityGap = targetIntensity - currentIntensity;
//                            
//                            // Display current intensity on LCD
//                            LCD.drawString("CurrentInte: " + intensityGap, 0, 5);
//
//                            if (DEObj.getCMD() == 1) {
//                                if (Math.abs(intensityGap) >= 13) {
//                                    // Turn right
//                                    Motor.C.setSpeed(rightMotorSpeed_1);
//                                    Motor.B.setSpeed(leftMotorSpeed_1);
//                                } else {
//                                    // Turn left
//                                    Motor.C.setSpeed(rightMotorSpeed_2);
//                                    Motor.B.setSpeed(leftMotorSpeed_2);
//                                }
//                                // Start the motors
//                                Motor.B.forward();
//                                Motor.C.forward();
//                            } else if (DEObj.getCMD() == 2) {
//                                // Obstacle avoidance maneuver
//                                // Implement obstacle avoidance logic here
//                            } else if (DEObj.getCMD() == 0) {
//                                // Stop the motors
//                                Motor.C.stop(true);
//                                Motor.B.stop(true);
//                            } else {
//                                // Rotate the robot to scan for the line
//                                // Implement rotation logic here
//                            }
//                        } else {
//                            System.out.println("Invalid data format received: " + line);
//                        }
//                    }
//                    // Close resources
//                    br.close();
//                    isr.close();
//                    is.close();
//                } else {
//                    System.out.println("Failed to retrieve data. Response code: " + responseCode);
//                }
//                conn.disconnect();
//                Thread.sleep(100); // Adjust delay as needed
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Some problem occurred while fetching or processing data!");
//                // Add a delay to avoid continuous retries in case of errors
//                try {
//                    Thread.sleep(5000); // Wait for 5 seconds before retrying
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void stop() {
//        isRunning = false;
//    }
//}
//
//

package app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class LineFollower implements Runnable {

    private volatile boolean isRunning = true;
    private DataExchange DEObj; // Added DataExchange object
    private ColorSensor colorSensor; // Added ColorSensor object

    public LineFollower(DataExchange deObj) {
        this.DEObj = deObj; // Initialize DataExchange object
        this.colorSensor = new ColorSensor(DEObj); // Initialize ColorSensor object
    }

    @Override
    public void run() {
        // Start the color sensor thread
        Thread colorSensorThread = new Thread(colorSensor);
        colorSensorThread.start();

        while (isRunning) {
            try {
                // Query the web service for line follower data
                URL url = new URL("http://192.168.75.248:8080/rest/legoservice/getfollow");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET"); // Specify the request method explicitly
                conn.setConnectTimeout(5000); // Set a timeout for connection (5 seconds)
                conn.setReadTimeout(5000); // Set a timeout for reading data (5 seconds)

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        // Process the line follower data
                        String[] data = line.split("#");
                        if (data.length >= 6) {
                            int leftMotorSpeed_1 = Integer.parseInt(data[2]);
                            int rightMotorSpeed_1 = Integer.parseInt(data[3]);
                            int leftMotorSpeed_2 = Integer.parseInt(data[4]);
                            int rightMotorSpeed_2 = Integer.parseInt(data[5]);
                            int targetIntensity = Integer.parseInt(data[6]);

                            // Display target intensity and motor speeds on LCD
                            LCD.clear();
                            LCD.drawString("Intensity: " + targetIntensity, 0, 0);
                            LCD.drawString("Left 1: " + leftMotorSpeed_1, 0, 1);
                            LCD.drawString("Right 1: " + rightMotorSpeed_1, 0, 2);
                            LCD.drawString("Left 2: " + leftMotorSpeed_2, 0, 3);
                            LCD.drawString("Right 2: " + rightMotorSpeed_2, 0, 4);

                            int currentIntensity = DEObj.getLineIntensity();
                            int intensityGap = targetIntensity - currentIntensity;
                            
                            // Display current intensity on LCD
                            LCD.drawString("CurrentInte: " + intensityGap, 0, 5);

                            if (DEObj.getCMD() == 1) {
                                if (Math.abs(intensityGap) >= 13) {
                                    // Turn right
                                    Motor.C.setSpeed(rightMotorSpeed_1);
                                    Motor.B.setSpeed(leftMotorSpeed_1);
                                } else {
                                    // Turn left
                                    Motor.C.setSpeed(rightMotorSpeed_2);
                                    Motor.B.setSpeed(leftMotorSpeed_2);
                                }
                                // Start the motors
                                Motor.B.forward();
                                Motor.C.forward();
                            } else if (DEObj.getCMD() == 2) {
                            	// Turn left
                                Motor.C.setSpeed(200); 
                                Motor.B.setSpeed(200);
                                Motor.B.backward();  
                                Motor.C.forward();  

                                // Delay for a specific duration to achieve a 90-degree turn                
                                Delay.msDelay(500); 

                                // Stop turning left
                                Motor.C.stop(true);
                                Motor.B.stop(true);

                                // Move forward
                                Motor.C.setSpeed(200);
                                Motor.B.setSpeed(200);  
                                Motor.C.forward();  
                                Motor.B.forward(); 
                                
                                // Move forward for 5 seconds
                                Delay.msDelay(2000);

                                // Stop moving forward
                                Motor.C.stop(true);
                                Motor.B.stop(true);

                                // Turn right 90 degrees
                                Motor.C.setSpeed(200); 
                                Motor.B.setSpeed(200);  
                                Motor.C.backward();  
                                Motor.B.forward();  
                             
                                // Delay for a specific duration to achieve a 90-degree turn
                                Delay.msDelay(500); 

                                // Stop turning left
                                Motor.C.stop(true);
                                Motor.B.stop(true);
                                
                             // Move forward
                                Motor.C.setSpeed(200);
                                Motor.B.setSpeed(200);
                                Motor.C.forward();  
                                Motor.B.forward(); 
                                
                                // Move forward for 3 seconds
                                Delay.msDelay(3000);  
                                
                             // Stop turning left
                                Motor.C.stop(true);
                                Motor.B.stop(true);
                                
                                // Turn right 90 degrees
                                Motor.C.setSpeed(200);
                                Motor.B.setSpeed(200);
                                Motor.C.backward(); 
                                Motor.B.forward(); 
                             
                                // Delay for a specific duration to achieve a 90-degree turn
                                Delay.msDelay(500);

                                // Stop turning left
                                Motor.C.stop(true);
                                Motor.B.stop(true);
                                
                             // Move forward
                                Motor.C.setSpeed(200);
                                Motor.B.setSpeed(200);
                                Motor.C.forward();    
                                Motor.B.forward();  
                                
                             // Move forward for 1.5 seconds
                                Delay.msDelay(1500); 
                                
                            } 
                        
                        else if (DEObj.getCMD() == 0) 
                        {
                            // Stop the motors
                            Motor.C.stop(true);
                            Motor.B.stop(true);
                        
                        } 
                            
                            else 
                            {
                                // Stop the motors
                                Motor.C.stop(true);
                                Motor.B.stop(true);
                                
                                // Rotate the robot to scan for the line
                                Motor.C.setSpeed(200); 
                                Motor.B.setSpeed(200); 
                                Motor.B.forward(); 
                                Motor.C.backward(); 
                                
                                // Delay for a specific duration to rotate the robot                
                                Delay.msDelay(300); 

                                // Stop rotating
                                Motor.C.stop(true);
                                Motor.B.stop(true);
                                
                                // Resume following the line
                                Motor.B.setSpeed(200); 
                                Motor.C.setSpeed(100);  
                                Motor.B.forward(); 
                                Motor.C.forward();
                                
                            } 
                        } else {
                            System.out.println("Invalid data format received: " + line);
                        }
                    }
                    // Close resources
                    br.close();
                    isr.close();
                    is.close();
                } else {
                    System.out.println("Failed to retrieve data. Response code: " + responseCode);
                }
                conn.disconnect();
                Thread.sleep(100); // Adjust delay as needed
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Some problem occurred while fetching or processing data!");
                // Add a delay to avoid continuous retries in case of errors
                try {
                    Thread.sleep(5000); // Wait for 5 seconds before retrying
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
