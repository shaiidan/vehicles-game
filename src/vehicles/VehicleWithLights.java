package vehicles;

/**
 * @author Shai Idan
 * @version 1
 * lights property
 */
public class VehicleWithLights extends VehicleProperty 
{
	private boolean lights;
	
	/**
	 * 
	 * @param draw the another property if doesn't so this instance of the class vehicle 
	 * @param lights true if you want this property, false if you don't want
	 */
	public VehicleWithLights(VehicleDraw draw,boolean lights)
	{
		super(draw);
		this.lights = lights;
	}
	
	public String Draw()
	{
		return super.Draw() +"," +"lights=" +lights;
	}
	
}
