package vehicles;

/**
 * @author Shai Idan
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
