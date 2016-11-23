import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class SaveToFile 
{
	private PrintWriter writer;
	private String fileName;
	private static int counter = 0;
	
	public SaveToFile(String fileName) 
	{
		this.fileName = fileName;
		createFile();
	}
	
	public void createFile()
	{
		try
		{
			writer = new PrintWriter(fileName);
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeHeadline()
	{
		writer.println("index\tN\tx\tepsilon\t");
	}

	public void updateFile(double resultN, double resultX, double resultEpsilon)
	{
		writer.println(counter + "\t" + resultN + "\t" + resultX + "\t" + resultEpsilon);
		counter+=1;
	}
	
	public void closeFile()
	{
		writer.close();
	}
}