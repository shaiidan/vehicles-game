package vehicles;

/**
 * @author Shai Idan
 * @version 1
 */

public class BenzineEngine extends Engine
{
	private static final int FUEL_CONSUMPTION = 2;
	private static final String VEHICLE_NAME = "Benzine";
	/**
	 * constructor of the class Engine
	 * @param fuelTank - the volume of fuel tank
	 * the fuel consumption is final and equal 9.
	 */
	public BenzineEngine(int fuelTank)
	{
		super(FUEL_CONSUMPTION,fuelTank);
	}
	
	@Override
	public String toString()
	{
		return "Benzine "+ super.toString(); 
	}
	
	/**
	 * @return new copy of this object 
	 */
	public Object clone() throws CloneNotSupportedException 
	{
		return new BenzineEngine(this.getVolumeFuelTank());
	}
	
	/**
	 * @return the name of engine , Benzine 
	 */
	public String getEngineName()
	{
		return VEHICLE_NAME;
	}
}// end of class BenzineEngine
