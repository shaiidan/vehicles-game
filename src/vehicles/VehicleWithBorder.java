package vehicles;

/**
 * @author Shai Idan 
 * @version 1
 * border property 
 */
public class VehicleWithBorder extends VehicleProperty
{
	private boolean border;
	
	/**
	 * 
	 * @param draw the another property if doesn't so this instance of the class vehicle 
	 * @param border true if you want this property, false if you don't want
	 */
	public VehicleWithBorder(VehicleDraw draw,boolean border) 
	{
		super(draw);
		this.border = border;
	}
	
	public String Draw()
	{
		return super.Draw() +"," +"border="+border;
	}
}
