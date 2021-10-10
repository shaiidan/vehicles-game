package vehicles;
import graphics.IMoveable;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import graphics.CityFrame;
import graphics.CityPanel;
import graphics.IDrawable;

/**
 * @author Shai Idan
 * @version 4 , 16/5/19
 */
public abstract class Vehicle extends Observable  implements IMoveable, IDrawable, Cloneable,Runnable,VehicleDraw
{
	protected int size = 65;
	protected int fuelConsumption =0;
	protected CityPanel pan;
	protected BufferedImage img1 = null, img2 = null, img3 = null, img4 = null;
	protected int id;
	protected int wheels;
	protected Location loc;
	protected int KM =0;
	protected final int limitAge = 18;
	private static int VEHICLE_ID = 1000;	
	private static Map<Integer, Vehicle> activeVehicles = null;
	private int collision = -1;
	private final int DISTANCE_COLLISION = size + 2;
	private boolean isAlive;
	protected VehicleDraw property;
	
	/**
	 * the constructor of the class Vehicle
	 * @param id - the vehicle id , the id have to be 1,000 - 1,000,000
	 * @param c - the 'enum' Color
	 * @param location  - the instance of Location class
	 * @param km - the KM of the vehicle
	 * @param numberWheel - number of wheel
	 * @param lights - boolean, true if the lights is on and else false
	 */
	public Vehicle(int id,Color c,int numberWheel,Location location,int km,boolean lights)
	{
		this.id = id;
		this.wheels = numberWheel;
		try{this.loc = (Location)location.clone();}
		catch(CloneNotSupportedException e) {System.out.println(e.toString());}
		this.KM = km;
	}
	
	/**
	 * the constructor for GUI
	 * @param panel object from CityPanel class, for paint this vehicle on this panel
	 * @param c the color of this vehicle
	 */
	public Vehicle(CityPanel panel,Color c)
	{
		this.pan = panel;
		this.id = VEHICLE_ID;
		this.wheels = 2;
		this.KM = 0;
		this.loc = new Location(new Point(0,0),Orientation.EAST);
		this.property = new VehicleWithColor(new VehicleWithLights(new VehicleWithBorder(this,false),false),c);
		VEHICLE_ID++;
	}
	@Override
	public String toString()
	{
		return "Vehicle [id ="+id+",number of wheel = "+ wheels
				+" ,\n"+ loc.toString() + ", km = "+ KM + ","+property.Draw();
	}
	
	/**
	 * 
	 * update the vehicle location, total mileage passed.
	 * @param point - the new point for move
	 * @return false if the vehicle doesn't move and else return true
	 */
	public boolean drive(Point point) {
		
		if (!point.equals(loc.getPoint()))
		{
			this.KM += point.distance(loc.getPoint());
			this.loc.setPoint(point);
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * @return the KM of this vehicle
	 */
	public int getKM()
	{
		return this.KM;
	}
	/**
	 * @return the Vehicle ID
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * for clone object 
	 * @param id the id before the clone
	 */
	protected void setID(int id)
	{
		this.id = id;
	}
	
	/**
	 * @return the location of this vehicle 
	 */
	public Location getLocation()
	{
		return this.loc;
	}
	
	/**
	 * 
	 * @param location the new location
	 * @return true if location is not null else return false
	 */
	public boolean setLocation(Location location)
	{
		if(location == null)
			return false;
		else
		{
			this.loc = location;
			return true;
		}
	}
	
	/**
	 * 
	 * @return true if the lights is on else return false
	 */
	public boolean getLights()
	{
		String pro = property.Draw();
		if(pro.contains("lights=true"))
				return true;
		return false;
	}
	
	/**
	 * 
	 * @param light true if lights is on else false
	 * @return the param
	 */
	public boolean setLights(boolean light)
	{
		this.property = new VehicleWithColor(new VehicleWithLights(new VehicleWithBorder(this,this.getBorder()),light),this.getColor());
		return light;
	}
	
	/**
	 * change lights
	 * @return true if lights is on before the change else return false
	 */
	public boolean changeLights()
	{
		if(getLights())
		{
			this.property = new VehicleWithColor(new VehicleWithLights(new VehicleWithBorder(this,this.getBorder()),false),this.getColor());
			return true;
		}
		else
		{

			this.property = new VehicleWithColor(new VehicleWithLights(new VehicleWithBorder(this,this.getBorder()),true),this.getColor());
			return false;
			
		}
	}
	
	/**
	 * @return true if draw the border else return false 
	 */
	public boolean getBorder()
	{
		String pro = property.Draw();
		if(pro.contains("border=true"))
				return true;
		return false;
	}
	
	/**
	 * 
	 * @param bord true if has border else false
	 * @return the bord parameter
	 */
	public boolean setBorder(boolean bord)
	{
		this.property = new VehicleWithColor(new VehicleWithLights(new VehicleWithBorder(this,bord),this.getLights()),this.getColor());
		return bord;
	}
	
	/**
	 * change the border if the border before the change was true now is false
	 * @return the value before the change
	 */
	public boolean changeBorder()
	{
		if(getBorder())
		{
			this.property = new VehicleWithColor(new VehicleWithLights(new VehicleWithBorder(this,false),this.getLights()),this.getColor());
			return true;
		}
		else
		{
			this.property = new VehicleWithColor(new VehicleWithLights(new VehicleWithBorder(this,true),this.getLights()),this.getColor());
			return false;	
		}
	}
	
	/**
	 * @return the color in string 
	 */
	public Color getColor()
	{
		String pro = property.Draw();
		if(pro.contains("green"))
			return Color.green;
		else if(pro.contains("red"))
			return Color.red;
		else if(pro.contains("white"))
			return Color.white;
		else
			return Color.silver;
	}
	
	/**
	 * paint the vehicle on the screen
	 */
	public void drawObject (Graphics g)
	{  
		if(loc.getOrientation() == Orientation.NORTH) // vehicle drives to the north side
			g.drawImage(img1,loc.getPoint().getX(),loc.getPoint().getY(),size,size,pan);
		else if(loc.getOrientation() == Orientation.SOUTH) // vehicle drives to the south side
			g.drawImage(img2,loc.getPoint().getX(),loc.getPoint().getY(),size,size,pan);
		else if(loc.getOrientation() == Orientation.EAST) // vehicle drives to the east side
			g.drawImage(img3,loc.getPoint().getX(),loc.getPoint().getY(),size,size,pan);
		else if(loc.getOrientation() == Orientation.WEST) // vehicle drives to the west side
			g.drawImage(img4,loc.getPoint().getX(),loc.getPoint().getY(),size,size,pan);
		
		// paint the border 
		if(getBorder())
		{
			g.setColor(java.awt.Color.BLUE);
			g.drawRect(loc.getPoint().getX(), loc.getPoint().getY(),size, size);
		}
	}
	
	/**
	 * loading images 
	 */
	public void loadImages()
	{
		String vehicleName = getVehicleName();
		if(vehicleName.compareTo("Benzine Car")==0 || vehicleName.compareTo("Solar Car")==0)
			vehicleName = "Car";
		// loading north side
		try {img1 = ImageIO.read(new File(PICTURE_PATH +getColor().toString()+vehicleName+"North.png"));}
		catch(IOException e) {
        	JOptionPane.showMessageDialog(new JDialog(),"Could not open north image");
        	System.out.println("Cannot load image1 "+getVehicleName());}
		// loading south side
		try {img2 = ImageIO.read(new File(PICTURE_PATH +getColor().toString()+vehicleName+"South.png"));}
		catch(IOException e) {
        	JOptionPane.showMessageDialog(new JDialog(),"Could not open south image");
        	System.out.println("Cannot load image2 "+getVehicleName());}
		// loading east side
		try {img3 = ImageIO.read(new File(PICTURE_PATH +getColor().toString()+vehicleName+"East.png"));}
		catch(IOException e) {
        	JOptionPane.showMessageDialog(new JDialog(),"Could not open east image");
        	System.out.println("Cannot load image3 "+getVehicleName());}
		// loading west side
		try {img4 = ImageIO.read(new File(PICTURE_PATH +getColor().toString()+vehicleName+"West.png"));}
		catch(IOException e) {
        	JOptionPane.showMessageDialog(new JDialog(),"Could not open west image");
        	System.out.println("Cannot load image4 "+getVehicleName());}	
	}
	
	/**
	 * move the vehicle on the screen
	 * @return true if the vehicle move else return false
	 */
	public boolean move(Point p)
	{
		if(this.canMove())
		{
			try {
	             //thread to sleep for 100 milliseconds
	            Thread.sleep(100/this.getSpeed());
	         } 
			catch (Exception e) {
				System.out.println(e);
	         }
			this.drive(p);
			return true;
		}
		else
		{
			setChanged();
			notifyObservers(messageObserver.fuelNeeds); // change color of the button of fuel
			// the vehicle can't move so he waits
			synchronized(this)
			{
				try{
					this.wait();
				}
				catch(InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
			return false;
		}	
	}
	
	/**
	 *  where the vehicle needs to move
	 * @return object from Point class
	 */
	public Point nextLocation()
	{
		int x = this.getLocation().getPoint().getX(),y = this.getLocation().getPoint().getY();
		Orientation orien = this.getLocation().getOrientation();

		// top corner right
		if(x == CityFrame.MAX_WIDTH && y == CityFrame.MIN_HEIGHT)
		{
			if(orien == Orientation.EAST)
				this.getLocation().setOrientation(Orientation.SOUTH);
			else if(orien == Orientation.NORTH)
				this.getLocation().setOrientation(Orientation.WEST);
		}
		
		// down corner right
		else if(x == CityFrame.MAX_WIDTH && y == CityFrame.MAX_HEIGHT)
		{
			if(orien == Orientation.SOUTH)
				this.getLocation().setOrientation(Orientation.WEST);
			else if(orien == Orientation.EAST)
				this.getLocation().setOrientation(Orientation.NORTH);
		}
		// top corner left
		else if(x == CityFrame.MIN_WIDTH && y == CityFrame.MIN_HEIGHT)
		{
			if(orien == Orientation.NORTH)
				this.getLocation().setOrientation(Orientation.EAST);
			else if(orien == Orientation.WEST)
				this.getLocation().setOrientation(Orientation.SOUTH);
		}
		// down corner left
		else if(x == CityFrame.MIN_WIDTH && y == CityFrame.MAX_HEIGHT)
		{
			if(orien == Orientation.WEST)
				this.getLocation().setOrientation(Orientation.NORTH);
			else if(orien == Orientation.SOUTH)	
				this.getLocation().setOrientation(Orientation.EAST);
			
		}
		//center right
		else if(x == CityFrame.MAX_WIDTH && y == CityFrame.MIDDLE_HEIGHT)
		{
			Random rand = new Random();
			boolean lottery = rand.nextBoolean();
			
			if(orien == Orientation.EAST)
			{
				if(lottery)
					this.getLocation().setOrientation(Orientation.NORTH);
				else
					this.getLocation().setOrientation(Orientation.SOUTH);
			}
			else if((orien == Orientation.SOUTH || orien == Orientation.NORTH) &&lottery)
				this.getLocation().setOrientation(Orientation.WEST);
		}
		//center left
		else if(x == CityFrame.MIN_WIDTH && y == CityFrame.MIDDLE_HEIGHT)
		{
			Random rand = new Random();
			boolean lottery = rand.nextBoolean();
			
			if(orien == Orientation.WEST)
			{
				if(lottery)
					this.getLocation().setOrientation(Orientation.NORTH);
				else
					this.getLocation().setOrientation(Orientation.SOUTH);
			}
			else if((orien == Orientation.SOUTH || orien == Orientation.NORTH) && lottery)
				this.getLocation().setOrientation(Orientation.EAST);
		}
		
		// move the vehicle
		if(this.getLocation().getOrientation() == Orientation.NORTH)
			return new Point(x,y-this.getSpeed());
		else if(this.getLocation().getOrientation() == Orientation.SOUTH)
			return new Point(x,y+this.getSpeed());
		else if(this.getLocation().getOrientation() == Orientation.WEST)
			return new Point(x-this.getSpeed(),y);
		else if(this.getLocation().getOrientation() == Orientation.EAST)
			return new Point(x+this.getSpeed(),y);				
		return this.getLocation().getPoint();

		
	}
	
	/**
	 * abstract method , return vehicle name
	 */
	public abstract String getVehicleName();
	
	/**
	 * @return the fuel consumption of this vehicle
	 */
	public int getFuelConsumption()
	{
		return fuelConsumption;
	}
	
	/**
	 * 
	 * @return if this vehicle can move 
	 */
	public boolean canMove()
	{		
		return this.getAmountFuel() > 0 || this.getVehicleName().equals("Bike"); 
	}

	
	/**
	 * 
	 * @return the number of vehicle's wheels 
	 */
	public int getWheels()
	{
		return this.wheels;
	}
	
	/**
	 * 
	 * @param fuel the new fuel consumption
	 */
	public void setFuelConsumption(int fuel)
	{
		this.fuelConsumption = fuel;
	}
	
	/**
	 * abstract method
	 * @return the amount of fuel 
	 */
	public abstract int getAmountFuel();
	

	/**
	 * abstract method
	 * @return Vehicle survival value
	 */
	public abstract int getDurability();
	
	/**
	 * @return new copy of this object 
	 */
	public abstract Object clone() throws CloneNotSupportedException;
	
	public static void setActiveVehicles(Map<Integer, Vehicle> v)
	{
		activeVehicles = v;
	}
	
	/*
	 * @return -1 if there was no collision, if was collision return the id of other vehicle
	 */
	public int getCollision()
	{
		return this.collision;
	}
	
	/**
	 * was collision so update the id of the other vehicle
	 * @param i vehicle ID
	 */
	public void setCollision(int i)
	{
		this.collision = i;
	}
	/**
	 * check if has collision
	 * @return the vehicle ID that collided with him, if there is no collision return -1
	 */
	public int hasCollision()
	{
		if(activeVehicles != null)
		{
			Vehicle[] v;
			synchronized(activeVehicles)
			{
				v = activeVehicles.values().toArray(new Vehicle[0]).clone();
			}
			
			for(int i=0; i<v.length;i++)
			{
				if(v[i].getID() != this.getID()) // not this vehicle
				{
					int thisX = this.getLocation().getPoint().getX(), otherX = v[i].getLocation().getPoint().getX(),
							thisY = this.getLocation().getPoint().getY(), otherY = v[i].getLocation().getPoint().getY();
					
					// check axis-x
					if(this.getLocation().getOrientation() == Orientation.EAST &&
							(v[i].getLocation().getOrientation() == Orientation.EAST || 
							v[i].getLocation().getOrientation() == Orientation.WEST))
					{
						if(thisX <= otherX && thisY == otherY)
						{
							if(thisX + DISTANCE_COLLISION >= otherX)
								return v[i].getID();
						}
					}
					else if(this.getLocation().getOrientation() == Orientation.WEST &&
							(v[i].getLocation().getOrientation() == Orientation.WEST || 
							v[i].getLocation().getOrientation() == Orientation.EAST))
					{
						if(thisX >= otherX && thisY == otherY)
						{
							if(thisX - DISTANCE_COLLISION <= otherX)
								return v[i].getID();
						}
					}
					//check axis-y
					else if(this.getLocation().getOrientation() == Orientation.NORTH &&
							(v[i].getLocation().getOrientation() == Orientation.NORTH || 
							v[i].getLocation().getOrientation() == Orientation.SOUTH))
					{
						if(thisY >= otherY && thisX == otherX)
						{
							if(thisY- DISTANCE_COLLISION <= otherY)
								return v[i].getID();
						}
					}
					else if(this.getLocation().getOrientation() == Orientation.SOUTH &&
							(v[i].getLocation().getOrientation() == Orientation.SOUTH || 
							v[i].getLocation().getOrientation() == Orientation.NORTH))
					{
						if(thisY <= otherY && thisX == otherX)
						{
							if(thisY + DISTANCE_COLLISION >= otherY)
								return v[i].getID();
						}
					}
				}
			}
		}
		return -1;
	}
	
	/**
	 * remove vehicles because collision and update collision to be other vehicle id
	 * @param v other vehicle have been collision
	 */
	public void removeVehiclesCollision(Vehicle v)
	{
		//check if the vehicle has collision, because only 2 vehicles were allowed to collide
		if(v.getCollision() == -1 && this.getCollision() == -1)
		{
			if(v.getDurability() == this.getDurability()) // remove both
			{
				synchronized(activeVehicles)
				{
					this.setCollision(v.getID());
					v.setCollision(this.getID());
					activeVehicles.remove(v.getID());
					activeVehicles.remove(this.getID());
					this.setIsAlive(false);
					v.setIsAlive(false);
					this.deleteObservers();
					v.deleteObservers();
					CityPanel.countVehicles-= 2;
				}
			}
			else if(v.getDurability()> this.getDurability())// remove this
			{
				synchronized(activeVehicles)
				{
					this.setCollision(v.getID());
					activeVehicles.remove(this.getID());
					this.setIsAlive(false);
					this.deleteObservers();
					CityPanel.countVehicles--;
				}
			}
			else // remove the other
			{
				synchronized(activeVehicles)
				{
					v.setCollision(this.getID());
					activeVehicles.remove(v.getID());
					v.setIsAlive(false);
					v.deleteObservers();
					CityPanel.countVehicles--;
				}
			}
		}
	}
			
	/*
	 * check if has vehicle in start point
	 */
	private boolean IsVehicleCloseFromBeginning()
	{
		Vehicle [] vehicles;
		synchronized(activeVehicles)
		{
			vehicles = activeVehicles.values().toArray(new Vehicle[0]).clone();
		}
		
		for(int i=0;i<vehicles.length; i++)
		{
			if(this.getID() != vehicles[i].getID())
			{
				if(vehicles[i].getLocation().getOrientation() == Orientation.EAST &&
						vehicles[i].getLocation().getPoint().getX() <= DISTANCE_COLLISION)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return if the vehicle on the screen so the thread is alive and move, if is not alive example remove or do to thread interrupt
	 */
	public boolean getIsAlive()
	{
		return this.isAlive;
	}
	
	/**
	 * 
	 * @param live true if the thread is alive and vehicle on the screen else false
	 */
	public void setIsAlive(boolean live)
	{
		this.isAlive = live;
	}
	
	
	/**
	 * thread method, check if there is collision if not the vehicle can move and move
	 * if yes check how from the vehicles remove
	 */
	public void run()
	{
		this.setIsAlive(true); // the vehicle is active so vehicle is alive
		
		//Wait at first so there will not be an immediate collision
		if(IsVehicleCloseFromBeginning())
		{
			try {
	            Thread.sleep(1000);
	         } 
			catch (Exception e) {
				System.out.println(e);
	         }
		}
		this.loadImages();
		while(this.getIsAlive())
		{
			Vehicle other_vehicle = activeVehicles.get(hasCollision());
			if(other_vehicle == null)
			{
				if(this.move(this.nextLocation())) // move the vehicle
				{
					setChanged();
					notifyObservers(messageObserver.infoDialog);
				}
			}
			else
				removeVehiclesCollision(other_vehicle);	
			pan.repaint();
		}
	}
	
	/**
	 * close the thread vehicle if the thread still on the screen
	 */
	public void CloseFromCityVehicle()
	{
		setIsAlive(false);
		System.out.println("Interrupt, the vehicle close!");
		setChanged();
		notifyObservers(messageObserver.exitFromCity);
		this.deleteObservers();
	}
	
	@Override
	public String Draw()
	{
		return "Vehicle property= ";
	}
	
}// end of class Vehicle
