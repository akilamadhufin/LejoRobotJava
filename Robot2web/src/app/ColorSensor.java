//package app;
//
//import lejos.hardware.ev3.LocalEV3;
//import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.robotics.SampleProvider;
//
///**
// * <b>Date :</b> 31.03.2024
// * 
// * @author 
// * <ul>
// * <li>Gimhani</li>
// * </ul>
// * 
// * @version  1.0
// * <br>
// * <br>
// * <b>Functionality: </b> <br>
// * The ColorSensor.java class is necessary to interface with the color sensor hardware, 
// * read intensity values from it, and provide this data to other components, such as the line-following algorithm, 
// * enabling the robot to perceive and respond to the environment based on color information. 
//
// */
//
//public class ColorSensor implements Runnable {
//    private EV3ColorSensor ColorSensor;
//    private SampleProvider intensityProvider;
//  //  private final float targetIntensity = 15;
//    private DataExchange DEObj;
//
//    public ColorSensor(DataExchange DE) {
//        this.DEObj = DE;
//        this.ColorSensor = new EV3ColorSensor(LocalEV3.get().getPort("S3"));
//        this.intensityProvider = ColorSensor.getRedMode();
//    }
//
//    /**
//     * Method to follow the line and Set the intensity difference in the data exchange object
//     */
//    @Override
//    public void run() {
//        float[] sample = new float[intensityProvider.sampleSize()];
//        while (true) {
//            if (DEObj.getCMD() == 1) {
//                // Continue following the line
//                intensityProvider.fetchSample(sample, 0);
//                float currentIntensity = sample[0] * 100;
//
//              //  float difference = currentIntensity - targetIntensity;
//                
//                // Set the intensity difference in the data exchange object
//              //  DEObj.setLineIntensity(difference);
//            }
//            
//        }
//    }
//
//	public static int getCurrentIntensity() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//}


package app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor implements Runnable {
    private EV3ColorSensor colorSensor;
    private SampleProvider intensityProvider;
    private DataExchange DEObj;

    public ColorSensor(DataExchange DE) {
        this.DEObj = DE;
        this.colorSensor = new EV3ColorSensor(LocalEV3.get().getPort("S3"));
        this.intensityProvider = colorSensor.getRedMode();
    }
    
    ///Yashodha
    public EV3ColorSensor getColorSensor() {
		return colorSensor;
	}

    @Override
    public void run() {     
        while (true) {       	
            float[] sample = new float[intensityProvider.sampleSize()];
            // Continue following the line
            intensityProvider.fetchSample(sample, 0);
            int currentIntensity = (int)(sample[0] * 100); // Corrected the calculation

            // Set the current intensity in the data exchange object
            DEObj.setLineIntensity(currentIntensity);

            try {
                // Query the web service for line follower data
                URL url = new URL("http://192.168.75.248:8080/rest/legoservice/getfollow");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET"); // Specify the request method explicitly
//                conn.setConnectTimeout(5000); // Set a timeout for connection (5 seconds)
//                conn.setReadTimeout(5000); // Set a timeout for reading data (5 seconds)

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        // Process the line follower data
                        String[] data = line.split("#");
                        if (data.length >= 7) { // Ensure correct data format
                            int targetIntensity = Integer.parseInt(data[6]);
                            // No need to calculate intensity gap here
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
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while fetching or processing data!");
            }
        }
    }
}
