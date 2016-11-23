
public class Test 
{

	public Test() 
	{
		
	}
	
	public static void print(double [] array)
	{
		System.out.println("Printing started...");
		for (double number : array)
			System.out.println(number);
		System.out.println("Printing finished.");
	}
	
	public static void printAndStop(double [] array)
	{
		System.out.println("Printing started...");
		for (double number : array)
			System.out.println(number);
		System.out.println("Printing finished.");
		System.exit(0);
	}
	

}
