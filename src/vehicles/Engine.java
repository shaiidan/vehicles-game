package vehicles;


/**
 * @author Shai Idan (311324602) and Harel Jerbi (204223184) 
 * @version 2 
 */
public abstract class Engine implements Cloneable
{
	private int fuelConsumption;
	private int volumeFuelTank;
	
	/**
	 * the constructor of class Engine
	 * @param fuelConsumption -  the fuel consumption of this vehicle
	 * @param fuelTank - Volume of fuel tank of this vehicle 
	 */
	public Engine(int fuelConsumption, int fuelTank)
	{
		this.fuelConsumption = fuelConsumption;
		this.volumeFuelTank = fuelTank;
	}
	
	@Override
	public String toString()
	{
		return "Engine [fuel consumption = "+ fuelConsumption +", fuel tank = "+ volumeFuelTank+"]";
	}
	
	/**
	 * 
	 * @return the attribute volume of fuel tank
	 */
	public int getVolumeFuelTank()
	{
		return volumeFuelTank;
	}
	
	/**
	 * 
	 * @return the attribute fuel consumption
	 */
	public int getFuelConsumption()
	{
		return this.fuelConsumption;
	}
	/**
	 * change the attribute volume of fuel tank
	 * @param tank - the new volume of fuel tank
	 * @return true if param tank is good and the change is happened else return false
	 */
	public boolean setVolumeFuelTank(int tank)
	{
		if (tank>0)
		{
			this.volumeFuelTank = tank;
			return true;
		}
		return false;
	}
	
	/**
	 * abstract method
	 * @return the name of this engine
	 */
	public abstract String getEngineName();
	
	/**
	 * @return new copy of this object 
	 */
	public abstract Object clone() throws CloneNotSupportedException;
}
