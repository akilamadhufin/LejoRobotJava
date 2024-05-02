package app;


public class DataExchange {
    // Obstacle detection flag
    private boolean obstacleDetected = false;

    // Robot commands: Follow Line, Stop, Turn Left
    private int CMD = 1;
    private volatile int lineIntensity;

    public DataExchange() 
    {
    	
    }

    /*
     * Getters & Setters
     */

    public void setObstacleDetected(boolean status) 
    {
        obstacleDetected = status;
        if (status) {
            // Obstacle detected, set command to turn left and move
            CMD = 2;
        } 
        else 
        {
            // No obstacle detected, set command to follow the line
            CMD = 1;
        }
    }

    public boolean getObstacleDetected() 
    {
        return obstacleDetected;
    }

    public void setCMD(int command) 
    {
        CMD = command;
    }

    public int getCMD() 
    {
        return CMD;
    }
    public int getLineIntensity() 
    {
        return lineIntensity;
    }

    public void setLineIntensity(int intensity) 
    {
        lineIntensity = intensity;
    }

}
