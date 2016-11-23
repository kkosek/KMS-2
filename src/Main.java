public class Main
{
	public static double getCalculationTime(long startTime)
	{
		return (System.currentTimeMillis()-startTime)/1000;
	}
	
	public static void main (String [] args)
	{
		long startTime = System.currentTimeMillis();
		System.out.println("Application started...");
		WaveFunction f = new WaveFunction();
		f.simulate();
		System.out.print("Finished. Time: " + getCalculationTime(startTime) + " s");
	}
}
