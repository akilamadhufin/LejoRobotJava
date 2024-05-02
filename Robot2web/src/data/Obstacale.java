package data;





public class Obstacale {
	
	private static int Id;
	private static int StepOneLeftMotorSpeed;
	private static int StepOneRightMotorSpeed;
	private static int StepOneTimeDelay;
	private static int StepTwoLeftMotorSpeed;
	private static int StepTwoRightMotorSpeed;
	private static int StepTwoTimeDelay;
	private static int StepThreeLeftMotorSpeed;
	private static int StepThreeRightMotorSpeed;
	private static int StepThreeTimeDelay;
	private static int StepFourLeftMotorSpeed;
	private static int StepFourRightMotorSpeed;
	private static int StepFourTimeDelay;
	private static int ObtacalCount;
	private static int ObtacaDistance;
	public static int getId() {
		return Id;
	}
	public static int getStepOneLeftMotorSpeed() {
		return StepOneLeftMotorSpeed;
	}
	public static int getStepOneRightMotorSpeed() {
		return StepOneRightMotorSpeed;
	}
	public static int getStepOneTimeDelay() {
		return StepOneTimeDelay;
	}
	public static int getStepTwoLeftMotorSpeed() {
		return StepTwoLeftMotorSpeed;
	}
	public static int getStepTwoRightMotorSpeed() {
		return StepTwoRightMotorSpeed;
	}
	public static int getStepTwoTimeDelay() {
		return StepTwoTimeDelay;
	}
	public static int getStepThreeLeftMotorSpeed() {
		return StepThreeLeftMotorSpeed;
	}
	public static int getStepThreeRightMotorSpeed() {
		return StepThreeRightMotorSpeed;
	}
	public static int getStepThreeTimeDelay() {
		return StepThreeTimeDelay;
	}
	public static int getStepFourLeftMotorSpeed() {
		return StepFourLeftMotorSpeed;
	}
	public static int getStepFourRightMotorSpeed() {
		return StepFourRightMotorSpeed;
	}
	public static int getStepFourTimeDelay() {
		return StepFourTimeDelay;
	}
	public static int getObtacalCount() {
		return ObtacalCount;
	}
	public static int getObtacaDistance() {
		return ObtacaDistance;
	}
	public static void setId(int id) {
		Id = id;
	}
	public static void setStepOneLeftMotorSpeed(String stepOneLeftMotorSpeed) {
		try {
			StepOneLeftMotorSpeed = Integer.parseInt(stepOneLeftMotorSpeed);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepOneRightMotorSpeed(String stepOneRightMotorSpeed) {
		try {
			StepOneRightMotorSpeed = Integer.parseInt(stepOneRightMotorSpeed);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepOneTimeDelay(String stepOneTimeDelay) {
		try {
			StepOneTimeDelay = Integer.parseInt(stepOneTimeDelay);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepTwoLeftMotorSpeed(String stepTwoLeftMotorSpeed) {
		try {
			StepTwoLeftMotorSpeed = Integer.parseInt(stepTwoLeftMotorSpeed);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepTwoRightMotorSpeed(String stepTwoRightMotorSpeed) {
		try {
			StepTwoRightMotorSpeed = Integer.parseInt(stepTwoRightMotorSpeed);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepTwoTimeDelay(String stepTwoTimeDelay) {
		try {
			StepTwoTimeDelay = Integer.parseInt(stepTwoTimeDelay);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepThreeLeftMotorSpeed(String stepThreeLeftMotorSpeed) {
		try {
			StepThreeLeftMotorSpeed = Integer.parseInt(stepThreeLeftMotorSpeed);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepThreeRightMotorSpeed(String stepThreeRightMotorSpeed) {
		try {
			StepThreeRightMotorSpeed = Integer.parseInt(stepThreeRightMotorSpeed);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepThreeTimeDelay(String stepThreeTimeDelay) {
		try {
			StepThreeTimeDelay = Integer.parseInt(stepThreeTimeDelay);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepFourLeftMotorSpeed(String stepFourLeftMotorSpeed) {
		try {
			StepFourLeftMotorSpeed = Integer.parseInt(stepFourLeftMotorSpeed);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepFourRightMotorSpeed(String stepFourRightMotorSpeed) {
		try {
			StepFourRightMotorSpeed = Integer.parseInt(stepFourRightMotorSpeed)
					;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setStepFourTimeDelay(String stepFourTimeDelay) {
		try {
			StepFourTimeDelay = Integer.parseInt(stepFourTimeDelay)
					;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setObtacalCount(String obtacalCount) {
		try {
			ObtacalCount = Integer.parseInt(obtacalCount);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setObtacaDistance(String obtacaDistance) {
		try {
			ObtacaDistance = Integer.parseInt(obtacaDistance);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}