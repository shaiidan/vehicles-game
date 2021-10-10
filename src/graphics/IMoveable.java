package graphics;

import vehicles.Point;

/**
 * @author Shai Idan
 * @version 2
 */
public interface IMoveable
{
    public String getVehicleName();
	public int getSpeed();
	public int getFuelConsumption();
	public boolean move(Point p);
	public int getDurability();
}
