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
        while (true) 
        {       	
            float[] sample = new float[intensityProvider.sampleSize()];
            // Continue following the line
            intensityProvider.fetchSample(sample, 0);
            int currentIntensity = (int)(sample[0] * 100); // Corrected the calculation

            // Set the current intensity in the data exchange object
            DEObj.setLineIntensity(currentIntensity);

            try 
            {
                // Query the web service for line follower data
                URL url = new URL("http://192.168.75.248:8080/rest/legoservice/getfollow");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) 
                {
                    InputStream is = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    
                    while ((line = br.readLine()) != null) 
                    {
                        // Process the line follower data
                        String[] data = line.split("#");
                        if (data.length >= 7) 
                        { // Ensure correct data format
                            int targetIntensity = Integer.parseInt(data[6]);
                            
                        } 
                        else 
                        {
                            System.out.println("Invalid data format received: " + line);
                        }
                    }
                    
                    // Close resources
                    br.close();
                    isr.close();
                    is.close();
                } 
                else 
                {
                    System.out.println("Failed to retrieve data. Response code: " + responseCode);
                }
                
                conn.disconnect();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                System.out.println("Error occurred while fetching or processing data!");
            }
        }
    }
}
