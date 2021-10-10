package vehicles;

/**
 * @author Shai Idan (311324602) and Harel Jerbi (204223184) 
 * @version 1
 * Color property
 */
public class VehicleWithColor extends VehicleProperty
{
	private Color col;
	
	/**
	 * 
	 * @param draw the another property if doesn't so this instance of the class vehicle 
	 * @param col true if you want this property, false if you don't want
	 */
	public VehicleWithColor(VehicleDraw draw,Color col) 
	{
		super(draw);
		this.col = col;
	}
	
	public String Draw()
	{
		return super.Draw() +"," +"Color="+col.toString();
	}

}
