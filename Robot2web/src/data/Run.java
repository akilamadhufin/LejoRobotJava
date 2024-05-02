package data;

public class Run {

	private static int run;
	
	
	

	public static int getRun() {
		return run;
	}

	public static void setRun(int run) {
		Run.run = run;
	}
	
	public static void setRun(String run) {
		try {
			Run.run = Integer.parseInt(run);
		}catch  (Exception e) {

		}
	}
	
	@Override
	public String toString() {
		return "Run []";
	}
}
