public class WaveFunction 
{
	private double [] points;
	private double [] rValues;
	private double [] iValues;
	private double [] rHalfValues;
	private double [] rHamiltonian;
	private double [] iHamiltonian;
	private double [] rHalfHamiltonian;
	
	private double resultN;
	private double resultX;
	private double resultEpsilon;
	private double tau;
	private SaveToFile saveResult;
	
	
	private final double dx = 1/((double) Params.N);
	
	public WaveFunction() 
	{
		points = new double [Params.N + 1];
		rValues = new double [Params.N + 1];
		iValues = new double [Params.N + 1];
		rHalfValues = new double [Params.N + 1];
		rHamiltonian = new double [Params.N + 1];
		iHamiltonian = new double [Params.N + 1];
		rHalfHamiltonian = new double [Params.N + 1];
	
		saveResult = new SaveToFile("results.txt");
		saveResult.createFile();
		saveResult.writeHeadline();
		
		for (int k=0; k<Params.N+1; k++)
		{
			points[k] = k*dx; //(27)
			rValues[k] = Math.sqrt(2)*
					Math.sin(Params.n*Math.PI*points[k]); //(29)
			
			iValues[k] = 0; //(29)
			rHamiltonian[k] = 0; 
			iHamiltonian[k] = 0;
			tau = 0;
		}
	}
	
	public void hamiltonian()
	{
		rHamiltonian[0] = 0;
		rHamiltonian[Params.N] = 0;
		iHamiltonian[0] = 0;
		iHamiltonian[Params.N] = 0; 
		rHalfHamiltonian[0] = 0;
		rHalfHamiltonian[Params.N] = 0; //this six lines are from (31) border conditions
		
		for (int k=1; k<Params.N; k++)
		{
			rHamiltonian[k] = -0.5*(rValues[k+1]+rValues[k-1]-2*rValues[k])/(dx*dx)
					+ Params.kappa*(points[k]-0.5)*rValues[k]*Math.sin(tau*Params.omega); //(30)
			
			iHamiltonian[k] = -0.5*(iValues[k+1]+iValues[k-1]-2*iValues[k])/(dx*dx)
					+ Params.kappa*(points[k]-0.5)*iValues[k]*Math.sin(tau*Params.omega); //(30)
			
			rHalfHamiltonian[k] = -0.5*(rHalfValues[k+1]+rHalfValues[k-1]-2*rHalfValues[k])/(dx*dx)
					+ Params.kappa*(points[k]-0.5)*rHalfValues[k]*Math.sin(tau*Params.omega+Params.dTau*0.5); //(30)
		}
	}
	
	private void countHalfHamiltonian()
	{
		rHalfHamiltonian[0] = 0;
		rHalfHamiltonian[Params.N] = 0;
		
		for (int k=1; k<Params.N; k++)
		{			
			rHalfHamiltonian[k] = -0.5*(rHalfValues[k+1]+rHalfValues[k-1]-2*rHalfValues[k])/(dx*dx)
					+ Params.kappa*(points[k]-0.5)*rHalfValues[k]*Math.sin(tau*Params.omega+Params.dTau*0.5); //(30)
		}
	}
	
	public void integrate()
	{
		for (int k=1; k<Params.N; k++)
		{
			rHalfValues[k] = rValues[k] + iHamiltonian[k]*Params.dTau*0.5; //(32)
			countHalfHamiltonian();
			iValues[k] = iValues[k] - rHalfHamiltonian[k]*Params.dTau; // (33)
			rValues[k] = rHalfValues[k] + iValues[k]*Params.dTau*0.5; //(34)
		}
	}
	

	
	private void countResultN() 
	{
		resultN = 0;
		for (int i=0; i<rValues.length; i++)
			resultN += rValues[i]*rValues[i] + iValues[i]*iValues[i];
		
		resultN *= dx;
	}
	
	private void countResultX()
	{
		resultX = 0;
		for (int i=0; i<rValues.length; i++)
			resultX += points[i]*(rValues[i]*rValues[i] + iValues[i]*iValues[i]);
	
		resultX *=dx;
	}

	private void countResultEpsilon() 
	{
		resultEpsilon = 0;
		for (int i=0; i<rValues.length; i++)
			resultEpsilon += rValues[i]*rHamiltonian[i] + iValues[i]*iHamiltonian[i];
		
		resultEpsilon *= dx;
	}
	
	public void updateResults()
	{
		countResultN();
		countResultX();
		countResultEpsilon();
		saveResult.updateFile(resultN, resultX, resultEpsilon);
	}
	
	public void simulate()
	{
		for (int i=0; i<Params.S; i++)
		{
			tau = i*Params.dTau;
			hamiltonian();
			integrate();
			updateResults();	
		}
		saveResult.closeFile();
	}
}
