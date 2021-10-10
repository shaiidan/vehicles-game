package vehicles;

import graphics.CityPanel;

/**
 * @author Shai Idan
 * @version 1
 */
public class VehicleFactory 
{
	/**
	 * 
	 * @param typeVehicle - type of vehicle you want to create
	 * @param pan - the instance of CityPanel
	 * @param col - the Color of the vehicle
	 * @param numberGear - for Bike type
	 * @return instance from vehicle you want 
	 */
	public Vehicle getVehicle(String typeVehicle,CityPanel pan, Color col, int numberGear)
	{
		Vehicle v = null;
		
		if(typeVehicle.equals("Bike"))
		{
			v = new Bike(pan,col,numberGear);
		}
		else if(typeVehicle.equals("Benzine Car"))
		{
			v = new Car(pan, col, new BenzineEngine(40));
		}
		else if(typeVehicle.equals("Solar Car"))
		{
			v = new Car(pan, col, new SolarEngine(40));
		}
		else if(typeVehicle.equals("Carriage"))
		{
			Carriage c = new Carriage(pan, col, new PackAnimal(null));
			c.getPackAnimal().setCarriage(c);
			v = c;
		}
		else 
		{
			System.out.println("ERROR!! the type vehicle incorrect!!");
			System.exit(0);
		}
		return v;
	}

}
