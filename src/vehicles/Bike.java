package vehicles;

import graphics.CityPanel;

/**
 * @author Shai Idan (311324602) and Harel Jerbi (204223184) 
 * @version 3 , 12/5/19
 */
public class Bike extends Vehicle
{
	
	private final int SPEED = 2;
	private int numberGear;
	private final int DURABILIT = 5;
	private static final String VEHICLE_NAME = "Bike";
	
	/**
	 * the constructor of class Vehicle
	 * @param id - the vehicle ID, the id have to be 1,000 - 1,000,000
	 * @param c - the enum Color
	 * @param numberWheel - the wheel's number for this vehicle 
	 * @param l - instance of class Location
	 * @param km - the KM of this vehicle 
	 * @param lights - true if the lights is on else false
	 * @param numberGear - the number of gear 
	 */
	public Bike(int id, Color c,int numberWheel,Location l, int km,boolean lights,int numberGear)
	{
		super(id,c,numberWheel,l,km,lights);
		this.numberGear = numberGear;
		
	}
	/**
	 * the constructor for GUI
	 * @param panel object of CityPanel class, for paint  the vehicle on this panel
	 * @param c the color of this vehicle
	 * @param numberGear the number of gears
	 */
	public Bike(CityPanel panel,Color c,int numberGear)
	{
		super(panel,c);
		this.wheels = 2;
		this.numberGear= numberGear;
	}
	
	@Override
	public String toString()
	{
		return "Bike["+super.toString() + "[number of gear = "+ numberGear + ", speed = "+ SPEED+"]]";
	}
	
	
	/**
	 * @return the name of vehicle
	 */
	public String getVehicleName()
	{
		return VEHICLE_NAME;
	}
	
	/**
	 * @return number of speed of this vehicle
	 */
	public int getSpeed()
	{
		return SPEED;
	}
	
	/**
	 * for bike doesn't have amount of fuel so return -1!
	 */
	public int getAmountFuel()
	{
		return -1;
	}
	

	/**
	 * @return Vehicle survival value
	 */
	public int getDurability()
	{
		return this.DURABILIT;
	}
	
	/**
	 * @return new copy of this object 
	 */
	public Object clone() throws CloneNotSupportedException 
	{
		Bike b = new Bike(this.pan,this.getColor(),this.numberGear);
		b.setID(this.getID());
		return b;
	}
	
}// end of class Bike
