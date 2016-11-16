public class WaveFunction 
{
	private double [] points;
	private double [] rValues;
	private double [] iValues;
	private double [] rHalfValues;
	private double [] rHamiltionian;
	private double [] iHamiltonian;
	
	private final double dx = 1/((double) Params.N);
	
	public WaveFunction() 
	{
		points = new double [Params.N + 1];
		rValues = new double [Params.N + 1];
		rHalfValues = new double [Params.N + 1];
		iValues = new double [Params.N + 1];

		for (int k=0; k<Params.N + 1; k++)
		{
			points[k] = k*dx; //(27)
			rValues[k] = Math.sqrt(2)*
					Math.sin(Params.n*Math.PI*points[k]); //(29)
			iValues[k] = 0; //(29)
			rHamiltionian[k] = 0; 
			iHamiltonian[k] = 0;
		}
	}

	public void hamiltonian()
	{
		rHamiltionian[0] = 0;
		rHamiltionian[Params.N + 1] = 0;
		iHamiltonian[0] = 0;
		iHamiltonian[Params.N + 1] = 0; //this four lines are from (31) border conditions
		
		for (int k=1; k<Params.N; k++)
		{
			rHamiltionian[k] = -0.5*(rValues[k+1]+rValues[k-1]-2*rValues[k])/(dx*dx)
					+ Params.kappa*(points[k]-0.5)*rValues[k]*Math.sin(Params.tau*Params.omega); //(30)
			
			iHamiltonian[k] = -0.5*(iValues[k+1]+iValues[k-1]-2*iValues[k])/(dx*dx)
					+ Params.kappa*(points[k]-0.5)*iValues[k]*Math.sin(Params.tau*Params.omega); //(30)
		}
	}
	
	public void integrate()
	{
		for (int k=0; k<Params.N + 1; k++)
		{
			rHalfValues[k] = rValues[k] + iHamiltonian[k]*Params.dTau*0.5; //32
			
		}
	}

}
