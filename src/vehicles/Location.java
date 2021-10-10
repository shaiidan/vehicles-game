package vehicles;


/**
 * @author Shai Idan (311324602) and Harel Jerbi (204223184) 
 * @version 3 , 12/5/19
 */
public class Location implements Cloneable{
	
	private Point p;
	private Orientation direct;
	
	/**
	 * the constructor of class Location 
	 * @param point - the instance from class Point
	 * @param d - the enum DirectionalOreintation
	 */
	public Location(Point point,Orientation d ){
		
		this.p = point;
		direct = d;
	}
	
	@Override
	public String toString()
	{
		return "Location ["+ p.toString() +", diectional = "+direct+"]";
	}
	
	/**
	 * 
	 * @return the point of this vehicle
	 */
	public Point getPoint()
	{
		return p;
	}
	
	/**
	 * change the point 
	 * @param p - the new point
	 */
	public void setPoint(Point p)
	{
		this.p = p; // Point is immutable class
	}
	
	/**
	 * 
	 * @return the orientation has now 
	 */
	public Orientation getOrientation()
	{
		return this.direct;
	}
	
	/**
	 * change the orientation
	 * @param o different orientation 
	 */
	public void setOrientation(Orientation o)
	{
		this.direct = o;
	}
	
	/**
	 * check if equals the point and orientation!
	 */
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Location)
		{
			return this.getPoint().equals(((Location)other).getPoint()) && 
					this.getOrientation() == ((Location)other).getOrientation();
		}
		return false;
	}
	
	/**
	 * @return new copy of this object 
	 */
	public Object clone() throws CloneNotSupportedException 
	{
		return new Location(this.getPoint(),this.getOrientation());
	}

}// end of class Location
