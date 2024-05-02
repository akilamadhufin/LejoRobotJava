package data;

public class Robot {

	private static int speed;
	private static int direction;
	private static int run;

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Robot.speed = speed;
	}

	public static void setSpeed(String speed) {
		try {
			Robot.speed = Integer.parseInt(speed);
		} catch (Exception e) {

		}

	}

	public static int getDirection() {
		return direction;
	}

	public static void setDirection(int direction) {
		Robot.direction = direction;
	}

	public static void setDirection(String direction) {
		try {
			Robot.direction = Integer.parseInt(direction);
		}

		catch (Exception e) {

		}

	}

	public static int getRun() {
		return run;
	}

	public static void setRun(int run) {
		Robot.run = run;
	}
	
	public static void setRun(String run) {
		try {
			Robot.run = Integer.parseInt(run);
		}catch  (Exception e) {

		}
	}

}