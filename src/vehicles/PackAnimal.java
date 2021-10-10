package vehicles;

import graphics.*;

/**
 * @author Shai Idan
 * @version 2
 */
public class PackAnimal implements IAnimal,Cloneable
{
	private final int ENERGY_MAX = 1000;
	private int energy ;
	private Carriage carriage;
	
	
	/**
	 * the constructor of PackAnimal
	 * @param c the instance from Carriage class
	 */
	public PackAnimal(Carriage c)
	{
		this.energy = ENERGY_MAX;
		this.carriage = c;
	}
	
	@Override
	public String toString()
	{
		return "The animal's name = "+  this.getAnimalName() + ", the energy = " +this.energy;
	}
	/**
	 * @return new copy of this object 
	 */
	public Object clone() throws CloneNotSupportedException 
	{
		return new PackAnimal(this.carriage);
	}
	
	/**
	 * @return String type, the name of animal
	 */
	public String getAnimalName()
	{
		return "Pack Animal";
	}
	
	/**
	 * update the animal's energy to maximum 
	 * @return true if succeeded else false
	 */
	public boolean eat()
	{
		if(this.energy == ENERGY_MAX)
			return false;
		
		this.carriage.setFuelConsumption(this.carriage.getFuelConsumption()+ENERGY_MAX - this.energy);
		this.energy = ENERGY_MAX;
		return true;
	}
	
	/**
	 * 
	 * @return the energy of this animal now 
	 */
	public int getEnergy()
	{
		return this.energy;
	}
	
	/**
	 * @return the name of vehicle , this case is Carriage
	 */
	public String getVehicleName()
	{
		return carriage.getVehicleName();
	}
	
	/**
	 * @return the speed of this vehicle, this case is Carriage
	 */
	public int getSpeed()
	{
		return carriage.getSpeed();
	}
	
	/**
	 * @return the energy of this animal
	 */
	public int getFuelConsumption()
	{
		return this.energy;
	}
	
	/**
	 * @return the vehicle Carriage for this pack animal
	 */
	public Carriage getCarriage()
	{
		return this.carriage;
	}
	
	/**
	 * 
	 * @param c from class Carriage, vehicle for this pack animal
	 */
	public void setCarriage(Carriage c)
	{
		this.carriage = c;
	}
	
	/**
	 * change this energy
	 * @param e different energy
	 */
	public void setEnergy(int e)
	{
		this.energy = e;
	}
	/**
	 * move the carriage
	 */
	public boolean move(Point p)
	{
		return this.carriage.move(p);
	}
	/**
	 * @return Vehicle survival value
	 */
	public int getDurability()
	{
		return this.carriage.getDurability();
	}

}//end class PackAnimal
