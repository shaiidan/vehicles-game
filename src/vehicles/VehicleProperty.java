package vehicles;

/**
 * @author Shai Idan (311324602) and Harel Jerbi (204223184) 
 * @version 1
 */
public abstract class VehicleProperty implements VehicleDraw
{
	private VehicleDraw draw;
	
	public VehicleProperty(VehicleDraw draw)
	{
		this.draw = draw;
	}
	
	@Override
	public String Draw()
	{
		return draw.Draw();
	}
}
