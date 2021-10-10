package vehicles;


/**
 * @author Shai Idan
 * @version 1
 */
public class Point implements Cloneable
{
	
	private int x;
	private int y;
	
	/**
	 * the constructor of class Point 
	 * @param x - the axis-x 
	 * @param y - the axis-y
	 */
	public Point(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString()
	{
		return "Point("+x+","+y+")";
	}
	
	@Override
	/**
	 * check if 2 point is equals
	 */
	public boolean equals(Object other)
	{
		 if(other instanceof Point) 
			 return this.x == ((Point)other).x && this.y == ((Point)other).y;
		 return false;
	}
	
	/**
	 * @return the axis-x
	 */
	public int getX()
	{
		return this.x;
	}
	

	/**
	 * @return the axis-y
	 */
	public int getY()
	{
		return this.y;
	}
	

	/**
	 * @param other - instance from class Point, the other point
	 * @return the distance between to point according to the Manhattan formula 
	 */
	public int distance(Point other)
	{
		return Math.abs(this.x - other.x)+Math.abs(this.y - other.y);
	}
	
	/**
	 * return copy from this object
	 */
	public Object clone() throws CloneNotSupportedException 
	{
		return new Point(this.getX(),this.getY());
	}
	
}// end of class Point
