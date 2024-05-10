package app;

import java.net.HttpURLConnection;
import java.net.URL;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import java.io.*;

public class SendRobotData implements Runnable {

    private volatile boolean isRunning = true;
    private EV3ColorSensor colorSensor;
    private LineFollower lfw;

    @Override
    public void run() 
    {
        while (isRunning) 
        {
        	System.out.println("First");
            try 
            {                	           	
                // Query the web service for line follower data
                URL url = new URL("http://192.168.1.124:8080/rest/legoservice/setrobotvalues");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST"); // Specifing the request method as POST
                conn.setDoOutput(true); // Allow output for sending data
                conn.setRequestProperty("Content-Type", "application/json"); // Set content type to JSON
                
                // Construct JSON data
                String jsonData = "{\n" +
                        "    \"currentIntensity\":"+ readColorIntensity()+ ",\n" +
                      "    \"currentSpeedLeftMotor\":" + Motor.C.getSpeed()+ ",\n" +
                    "    \"currentSpeedRightMotor\":" + Motor.B.getSpeed()+ ",\n" +
                  "    \"currentSpeedLeftMotor1\": " + Motor.C.getSpeed()+ ",\n" +
                "    \"currentSpeedRightMotor2\": " + Motor.B.getSpeed()+ "\n" +
                        "}";
                
                // Write data to the connection output stream
                try (OutputStream os = conn.getOutputStream()) 
                {
                    byte[] input = jsonData.getBytes("utf-8"); 
                    os.write(input, 0, input.length);
                }

                int responseCode = conn.getResponseCode();
                
                if (responseCode == HttpURLConnection.HTTP_OK) 
                {
                    System.out.println("Data sent successfully.");   
                } 
                
                else 
                {
                    System.out.println("Failed to send data. Response code: " + responseCode);
                }

                // Close resources
                conn.disconnect();
                Thread.sleep(100); // Adjust the delay as needed
            } 
            
            catch (Exception e) 
            {
            	try
            	{
            		Thread.sleep(2000);
					
				} 
            	catch (Exception e2) 
            	{
					// TODO: handle exception
				}
            	
                e.printStackTrace();
                System.out.println("Some problem occurred while sending data!");
                try 
              	{
            	   Thread.sleep(2000);
              	} 
                catch (Exception e2)
                {
                	// TODO: handle exception
                }
                
             
                // Adding a delay to avoid continuous retries in case of errors                
                try 
                {
                    Thread.sleep(5000); // Wait for 5 seconds before retrying
                } 
                
                catch (InterruptedException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void setLineFollower(LineFollower lfw) 
    {
    	this.lfw = lfw;
    }
    
    public void stopSending() 
    {
        isRunning = false;
    }

    private int readColorIntensity() 
    {  	
        	EV3ColorSensor colorSensor = this.lfw.getColorSensor().getColorSensor();  
            float[] sample = new float[1];
            colorSensor.getRedMode().fetchSample(sample, 0);
            return (int) (sample[0] * 100);	 
    }
}




