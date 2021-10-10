package vehicles;

import graphics.CityPanel;

/**
 * @author Shai Idan (311324602) and Harel Jerbi (204223184) 
 * @version 3 , 12/5/19
 */
public class Carriage extends Vehicle {
	
	private PackAnimal packAnimal;
	private final int speed = 1;
	private final int DURABILIT = 10;
	private static final String VEHICLE_NAME = "Carriage";
	
	/**
	 * the constructor of class Carriage 
	* @param id - the vehicle id , the id have to be 1,000 - 1,000,000
	 * @param c - the 'enum' Color
	 * @param l  - the instance of Location class
	 * @param km - the KM of the vehicle
	 * @param lights - boolean, true if the lights is on and else false
	 * @param animal - The type of animal pulling the carriage
	 */
	public Carriage(int id, Color c,Location l,int km,boolean lights,PackAnimal animal)
	{
		super(id,c,4,l,km,lights);
		
		this.packAnimal = animal;
	}
	
	/**
	 * the constructor for GUI
	 * @param panel object of CityPanel class, for paint  the vehicle on this panel
	 * @param c the color of this vehicle
	 * @param packAnimal object from class PackAnimal 
	 */
	public Carriage(CityPanel panel,Color c,PackAnimal packAnimal)
	{
		super(panel,c);
		this.wheels = 4;
		this.packAnimal = packAnimal;
	}

	@Override
	public String toString()
	{
		return "Carriage ["+super.toString() + ", "+packAnimal.toString() + ", speed = "+speed+"]]";
	}
	
	/**
	 * @return the name of this vehicle
	 */
	public String getVehicleName()
	{
		return VEHICLE_NAME;
	}
	
	/**
	 * @return the speed of this vehicle
	 */
	public int getSpeed()
	{
		return speed;
	}
	
	/**
	 * 
	 * @return the object from class PackAnimal
	 */
	public PackAnimal getPackAnimal()
	{
		return this.packAnimal;
	}
	
	/**
	 * move the car, change the location and update km and energy
	 */
	public boolean drive(Point p)
	{
		int oldKm = this.KM, newKm;
		if(super.drive(p))
		{
			newKm = this.KM - oldKm;
			this.packAnimal.setEnergy(this.packAnimal.getEnergy() -newKm*20);
			if(this.packAnimal.getEnergy() <0)
				this.packAnimal.setEnergy(0);
			return true;
		}
		return false;
	}
	
	/**
	 * @return the amount of energy
	 */
	public int getAmountFuel()
	{
		return this.packAnimal.getEnergy();
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
		Carriage c = new Carriage(this.pan,this.getColor(),this.packAnimal);
		c.setID(this.getID());
		return c;
	}
	
}// end of class Carriage
