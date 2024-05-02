package data;

public class Path {

    private static int Id;
    private static int targetIntensity;
    private static int LeftMotorSpeed_1;
    private static int RightMotorSpeed_1;
    private static int LeftMotorSpeed_2;
    private static int RightMotorSpeed_2;

    public static int getId() {
        return Id;
    }

    public static void setId(int id) {
        Id = id;
    }

    public static void setId(String id) {
        try {
            Id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static int getTargetIntensity() {
        return targetIntensity;
    }

    public static void setTargetIntensity(int targetIntensity) {
        Path.targetIntensity = targetIntensity;
    }

    public static void setTargetIntensity(String targetIntensity) {
        try {
            Path.targetIntensity = Integer.parseInt(targetIntensity);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static int getLeftMotorSpeed_1() {
        return LeftMotorSpeed_1;
    }

    public static void setLeftMotorSpeed_1(int leftMotorSpeed) {
        LeftMotorSpeed_1 = leftMotorSpeed;
    }

    public static void setLeftMotorSpeed_1(String leftMotorSpeed) {
        try {
            LeftMotorSpeed_1 = Integer.parseInt(leftMotorSpeed);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static int getRightMotorSpeed_1() {
        return RightMotorSpeed_1;
    }

    public static void setRightMotorSpeed_1(int rightMotorSpeed) {
        RightMotorSpeed_1 = rightMotorSpeed;
    }

    public static void setRightMotorSpeed_1(String rightMotorSpeed) {
        try {
            RightMotorSpeed_1 = Integer.parseInt(rightMotorSpeed);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    public static int getLeftMotorSpeed_2() {
        return LeftMotorSpeed_2;
    }

    public static void setLeftMotorSpeed_2(int leftMotorSpeed) {
        LeftMotorSpeed_2 = leftMotorSpeed;
    }

    public static void setLeftMotorSpeed_2(String leftMotorSpeed) {
        try {
            LeftMotorSpeed_2 = Integer.parseInt(leftMotorSpeed);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static int getRightMotorSpeed_2() {
        return RightMotorSpeed_2;
    }

    public static void setRightMotorSpeed_2(int rightMotorSpeed) {
        RightMotorSpeed_2 = rightMotorSpeed;
    }

    public static void setRightMotorSpeed_2(String rightMotorSpeed) {
        try {
            RightMotorSpeed_2 = Integer.parseInt(rightMotorSpeed);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Path [Id=" + Id + ", targetIntensity=" + targetIntensity + ", LeftMotorSpeed_1=" + LeftMotorSpeed_1
                + ", RightMotorSpeed_1=" + RightMotorSpeed_1 + ", LeftMotorSpeed_2=" + LeftMotorSpeed_2
                + ", RightMotorSpeed_2=" + RightMotorSpeed_2 + "]";
    }

}
