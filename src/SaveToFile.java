import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class SaveToFile 
{
	private PrintWriter writer;
	private String fileName;
	
	public SaveToFile(String fileName) 
	{
		this.fileName = fileName;
		createFile();
	}
	
	private void createFile()
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
		writer.println("N\tx\tepsilon\t");
	}

	public void updateFile()
	
	public void closeFile()
	{
		writer.close();
	}
}