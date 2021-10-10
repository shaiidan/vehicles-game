package vehicles;
/**
 * @author Shai Idan
 * @version 1
 */
public class SolarEngine extends Engine {
	
	private static final int FUEL_CONSUMPTION  = 1;
	private static final String VEHICLE_NAME = "Solar";
	/**
	 * constructor of the class Engine
	 * @param fuelTank - the volume of fuel tank
	 * the fuel consumption is final and equal 6.
	 */
	public SolarEngine(int fuelTank)
	{
		super(FUEL_CONSUMPTION ,fuelTank);
	}
	
	@Override
	public String toString()
	{
		return "Solar "+ super.toString(); 
	}
	
	/**
	 * @return the name of this engine, Solar
	 */
	public String getEngineName()
	{
		return VEHICLE_NAME;
	}

	/**
	 * @return new copy of this object 
	 */
	public Object clone() throws CloneNotSupportedException 
	{
		return new SolarEngine(this.getVolumeFuelTank());
	}
}// end of class SolarEngine
