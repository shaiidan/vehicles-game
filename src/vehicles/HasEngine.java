package vehicles;

import graphics.CityPanel;

/**
 * @author Shai Idan
 * @version 2
 */
public abstract class HasEngine extends Vehicle
{
	
	protected Engine engine;
	protected int amountFuel;
	
	/**
	 * the constructor of class HasEngine 
	 * @param id - the vehicle id , the id have to be 1,000 - 1,000,000
	 * @param c - the 'enum' Color
	 * @param numberWheel - number of wheel 
	 * @param l  - the instance of Location class
	 * @param km - the KM of the vehicle
	 * @param lights - boolean, true if the lights is on and else false
	 * @param engine - instance of class Engine
	 * @param countFuel - count of fuel noew of this vehicle
	 * @param limitAge - limit of age for drive in this vehicle
	 */
	public HasEngine(int id, Color c,int numberWheel,Location l, int km,boolean lights,
			Engine engine, int countFuel,int limitAge)
	{
		super(id,c,numberWheel,l,km,lights);
		try{this.engine = (Engine)engine.clone();}
		catch(CloneNotSupportedException e) {System.out.println(e.toString());}
		this.amountFuel = countFuel;
	}
	
	/**
	 * the constructor for GUI
	 * @param panel object of CityPanel class, for paint  the vehicle on this panel
	 * @param c the color of this vehicle
	 * @param engine instance from class Engine, this is the engine of this car
	 */
	public HasEngine(CityPanel panel,Color c,Engine engine)
	{
		super(panel,c);
		try{this.engine = (Engine)engine.clone();}
		catch(CloneNotSupportedException e) {System.out.println(e.toString());}
	}
	
	/**
	 * update the count of fuel to maximum for this vehicle 
	 * @return false if The fuel tank is already full and else return true
	 */
	public boolean Refuel()
	{
		if(amountFuel != engine.getVolumeFuelTank())
		{
			this.fuelConsumption += engine.getVolumeFuelTank() - amountFuel;
			amountFuel = engine.getVolumeFuelTank();
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return super.toString()+",\nHas Engine ["+engine.toString() + 
				", count of fuel = " + amountFuel + ", limit of age = " + limitAge+"]";
	}
	
	/**
	 * @return the amount of fuel 
	 */
	public int getAmountFuel()
	{
		return this.amountFuel;
	}
	@Override
	/**
	 * Updating the amount of fuel according to the fuel consumption,
	 * if it does not have enough filling it to the maximum and update the amount of fuel
	 */
	public boolean drive(Point point) 
	{
		int oldKM = super.getKM(), newKM;
		if(super.drive(point))
		{
			newKM = super.getKM();
			amountFuel = amountFuel - this.engine.getFuelConsumption() *(newKM - oldKM);
			if(amountFuel<0)
				this.amountFuel = 0;
			return true;
		}
		return false;
	}
	
}// end of class HasEngine
