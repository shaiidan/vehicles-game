package vehicles;

import java.util.HashMap;
import java.util.Map;

public class VehicleMemento 
{
	private Map<Integer, Vehicle> vehiclesInfo = new HashMap<Integer, Vehicle>();
	private Map<Integer, Vehicle> vehiclesActive = new HashMap<Integer, Vehicle>();
	
	/**
	 * 
	 * @param vehiclesInfo for infoDialog save
	 * @param vehiclesActive for active vehicles save
	 */
	public VehicleMemento(Map<Integer, Vehicle> vehiclesInfo, Map<Integer, Vehicle> vehiclesActive)
	{
		vehiclesInfo.forEach((id,vehicle)->{
			Vehicle v = null;
			try {
				v = (Vehicle)vehicle.clone();
				v.setLights(vehicle.getLights());
				v.setBorder(vehicle.getBorder());
				v.setID(id);
				v.setLocation((Location)vehicle.getLocation().clone());
			}
			catch(CloneNotSupportedException e) {System.out.println(e.getMessage());}
			
			if(v != null)
				this.vehiclesInfo.put(v.getID(),v);
		});
		
		vehiclesActive.forEach((id,vehicle)->{
			
			Vehicle v = null;
			try {
				v = (Vehicle)vehicle.clone();
				v.setLights(vehicle.getLights());
				v.setBorder(vehicle.getBorder());
				v.setID(id);
				v.setLocation((Location)vehicle.getLocation().clone());
			}
			catch(CloneNotSupportedException e) {System.out.println(e.getMessage());}
			
			if(v != null)
				this.vehiclesActive.put(v.getID(),v);
		});
	}
	
	/**
	 * 
	 * @return  the map vehicles for infoDialog
	 */
	public Map<Integer, Vehicle> getVehiclesInfo()
	{
		return this.vehiclesInfo;
	}
	/**
	 * 
	 * @return the map vehicles for active screen
	 */
	public Map<Integer, Vehicle> getVehiclesActive()
	{
		return this.vehiclesActive;
	}
}// end class VehicleMemento
