public class WaveFunction 
{
	private double [] points;
	private double [] realValues;
	private double [] imaginaryValues;
	
	private final double dx = 1/((double) Params.N);
	
	public WaveFunction() 
	{
		points = new double [Params.N + 1];
		realValues = new double [Params.N + 1];
		imaginaryValues = new double [Params.N + 1];

		for (int k=0; k<Params.N + 1; k++)
		{
			points[k] = k*dx; //(27)
			realValues[k] = Math.sqrt(2)*
					Math.sin(Params.n*Math.PI*points[k]);
			imaginaryValues[k] = 0;
		}
	}
	
	public void update()
	{
		
	}

}
