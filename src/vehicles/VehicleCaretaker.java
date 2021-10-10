package vehicles;

import java.util.Stack;

public class VehicleCaretaker 
{
	private Stack<VehicleMemento> cityList = new Stack<VehicleMemento>();
	
	/**
	 * add new state memento
	 * @param m save this state
	 */
	public void addMemento(VehicleMemento m) { 
		cityList.push(m); 
	} 
	
	/**
	 * 
	 * @return the last save state
	 */
	public VehicleMemento getMemento()
	{ 
		return cityList.pop();
	} 
	/**
	 * 
	 * @return true if not save memento state else return false
	 */
	public boolean isCaretakerEmpty()
	{
		return cityList.isEmpty();
	}
} // end class VehicleCaretaker
