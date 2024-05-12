package app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ObstacleDetector implements Runnable {
    private DataExchange DEObj;
    private SoundWave soundwave; // Reference to SoundWave
    private EV3UltrasonicSensor ultrasonicSensor;
    private SampleProvider distanceProvider;
    private int obstacleCount = 0; // Counter to keep track of obstacles
    private int obstacleDistance = 0; // Initial value

    public ObstacleDetector(DataExchange DE, SoundWave soundwave) {
        DEObj = DE;
        this.soundwave = soundwave;
        Port ultrasonicPort = SensorPort.S1;
        ultrasonicSensor = new EV3UltrasonicSensor(ultrasonicPort);
        distanceProvider = ultrasonicSensor.getDistanceMode();
    }

    public void run() {
        float[] sample = new float[distanceProvider.sampleSize()];
        while (true) {
            try {
                // Query the web service for obstacle detection data
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
                        String[] data = line.split("#");
                        if (data.length >= 2) {
                            // Adjust the security distance based on received data
                            obstacleDistance = Integer.parseInt(data[2]);
                            
                            LCD.drawString("Distance: " + obstacleDistance, 0, 6);
                        }
                    }
                    // Close resources
                    br.close();
                    isr.close();
                    is.close();
                } else {
                    System.out.println("Failed to retrieve obstacle detection data. Response code: " + responseCode);
                }
                conn.disconnect();
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

            // Continue with obstacle detection logic
            distanceProvider.fetchSample(sample, 0);
            int distance = (int) sample[0];
            if (distance > obstacleDistance) {
                // No obstacle, continue following the line
                DEObj.setCMD(1); // Continue following the line
            } 
            else 
            {
                obstacleCount++;
                if (obstacleCount == 1) {
                    // First obstacle detected, avoid it
                    DEObj.setCMD(2); // Turn left and move outside the line
                    // LCD Output
                    // Play music
                    Thread musicThread = new Thread(soundwave);
                    musicThread.start();
                   // LCD.drawString("First obstacle found!", 0, 1);
                    LCD.refresh();

                    // Reset obstacle count after handling the first obstacle
                    obstacleCount = 0;
                } else if (obstacleCount > 1) {
                    // More than one obstacle encountered, stop
                    DEObj.setCMD(0); // Stop the robot
                    // LCD Output
                    LCD.clear(); // Clear the screen before displaying new message
                    Sound.beep();
                  //  LCD.drawString("Second obstacle found!", 0, 1);
                    LCD.refresh();
                }
            }
            Delay.msDelay(100); // Delay to avoid rapid command changes
        }
    }
}
