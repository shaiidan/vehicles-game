package vehicles;
import graphics.CityPanel;

/**
 * @author Shai Idan
 * @version 3 , 12/5/19
 */
public class Car extends HasEngine {
	
	private final int SPEED = 4; 
	private final int DURABILIT = 15;
	private final static int NUMBER_PASSENGERS = 5;
	private final static String VEHICLE_NAME = "Car";
	
	/**
	 * the constructor of the class Car
	 * @param id - the vehicle id , the id have to be 1,000 - 1,000,000
	 * @param c - the 'enum' Color
	 * @param l  - the instance of Location class
	 * @param km - the KM of the vehicle
	 * @param lights - boolean, true if the lights is on and else false
	 * @param engine - the instance of Engine class
	 * @param countFuel - count of fuel now
	 * @param limitAge - limit of age for drive in this vehicle
	 * @param numberPassengers - number of passengers the can sit on this vehicle
	 */
	public Car(int id, Color c,Location l, int km,
			boolean lights,Engine engine, int countFuel,int limitAge, int numberPassengers)
	{
		super(id,c,4,l,km,lights,engine,countFuel,limitAge);
		
		if(!engine.setVolumeFuelTank(40))
			System.out.print("the volum fuel tank is incorrect!");
	}
	
	/**
	 * the constructor for GUI
	 * @param panel object of CityPanel class, for paint  the vehicle on this panel
	 * @param c the color of this vehicle
	 * @param engine instance from class Engine, this is the engine of this car
	 */
	public Car(CityPanel panel,Color c,Engine engine)
	{
		super(panel,c,engine);
		this.wheels = 4;
		if(!engine.setVolumeFuelTank(40))
			System.out.print("the volum fuel tank is incorrect!");
		this.amountFuel = this.engine.getVolumeFuelTank();

	}
	
	/**
	 * @return new copy of this object 
	 */
	public Object clone() throws CloneNotSupportedException 
	{
		Car c = new Car(this.pan,this.getColor(),(Engine)this.engine.clone()); 
		c.setID(this.getID());
		return c;
	}
	
	/**
	 * @return the full name of vehicle also the engine name
	 */
	public String getVehicleName()
	{
		return engine.getEngineName() +" "+ VEHICLE_NAME;
	}
	
	/**
	 * @return the speed of this vehicle
	 */
	public int getSpeed()
	{
		return SPEED;
	}

	/**
	 * @return Vehicle survival value
	 */
	public int getDurability()
	{
		return this.DURABILIT;
	}
	@Override 
	public String toString()
	{
		return "Car[" +super.toString()+",\nnumber of passengers = "+NUMBER_PASSENGERS +"speed = "+SPEED+"]";
	}
	
}// end of class Car
