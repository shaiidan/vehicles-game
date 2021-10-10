package graphics;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import vehicles.Color;
import vehicles.Vehicle;
import vehicles.messageObserver;

/**
 * @author Shai Idan
 * @version 4 , 12/5/19
 */
public class InfoVehicleDialog extends JDialog implements Observer
{
	private JTable table;
	private static final long serialVersionUID = 1L;
	private JPanel pan1;
	private  Map<Integer, Vehicle> vehicles = new HashMap<Integer, Vehicle>();
	private static InfoVehicleDialog single_instance = null;
	private  DefaultTableModel model;

	private InfoVehicleDialog() {
		this.setTitle("information about vehicle");
		this.setSize(1000, 220);
		// this.setBounds(120, 700, 1700, 220);
		this.setResizable(false);
		String[] columnNames = { "Vehicle", "ID", "Color", "Wheels", "Speed", "fuelAmount", "Distance",
				"Fuel consumption", "Lights", "collision" };
	    model = new DefaultTableModel();
	    model.setColumnIdentifiers(columnNames);
	    table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 20));
		table.setRowHeight(50);
		// change font of first column
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 22));
		JScrollPane sp = new JScrollPane(table);
		pan1 = new JPanel();
		pan1.setLayout(new BorderLayout());
		pan1.add(sp, BorderLayout.CENTER);
		add(pan1);
	}

	/**
	 * for singleton DP - get one instance for this class
	 * 
	 * @return the instance of this class
	 */
	public static InfoVehicleDialog getInstance() 
	{
		if (single_instance == null)
			single_instance = new InfoVehicleDialog();
		return single_instance;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 == messageObserver.infoDialog)
		{
			Vehicle v = (Vehicle)arg0;
			if(vehicles.get(v.getID()) == null)
			{
				vehicles.put(v.getID(), v);
				String[] data = new String[10];
				
				data[0] = v.getVehicleName();
				data[1] = Integer.toString(v.getID());
					Color col = v.getColor();
				data[2] = col.toString();
				data[3] = Integer.toString(v.getWheels());
				data[4] = Integer.toString(v.getSpeed());
				int temp = v.getAmountFuel();
				if (temp == -1)
					data[5] = "Infinity";
				else
					data[5] = Integer.toString(temp);
				data[6] = Integer.toString(v.getKM());
				data[7] = Integer.toString(v.getFuelConsumption());
				data[8] = Boolean.toString(v.getLights());
				temp = v.getCollision();
				
				if (temp == -1)
					data[9] = " ";
				else
					data[9] = Integer.toString(temp);

				model.addRow(data);
				// add row
			}
			else
			{
				int i = getRowNumber(v.getID());
				model.setValueAt(v.getVehicleName(),i,0);
				model.setValueAt(Integer.toString(v.getID()),i,1);
				Color col = v.getColor();
				model.setValueAt(col.toString(),i,2);
				model.setValueAt(Integer.toString(v.getWheels()),i,3);
				model.setValueAt(Integer.toString(v.getSpeed()),i,4);
				int temp = v.getAmountFuel();
				if (temp == -1)
					model.setValueAt("Infinity",i,5);
				else
					model.setValueAt(Integer.toString(temp),i,5);
				
				model.setValueAt(Integer.toString(v.getKM()),i,6);
				model.setValueAt(Integer.toString(v.getFuelConsumption()),i,7);
				model.setValueAt(Boolean.toString(v.getLights()),i,8);
				temp = v.getCollision();
				
				if (temp == -1)
					model.setValueAt(" ",i,9);
				else
					model.setValueAt(Integer.toString(temp),i,9);
				//update row
			}
		}	
	}
	
	/**
	 * 
	 * @param id the vehicleID
	 * @return the number of the row that the vehicle is. 
	 */
	private int getRowNumber(int id)
	{
		for(int i = 0; i < table.getRowCount(); i++)
		{//For each row
	        for(int j = 0; j < table.getColumnCount(); j++)
	        {//For each column in that row
	            if(table.getModel().getValueAt(i, j).equals(Integer.toString(id)))
	            {//Search the model
	            	return i;
	            }
	        }//For loop inner
	    }//For loop outer
		return -1;
	}
	
	/**
	 * 
	 * @return the vehicles on the info dialog
	 */
	public  Map<Integer, Vehicle> getVehiclesInfo()
	{
		return this.vehicles;
	}
	
	/**
	 * 
	 * @param infoV new map of vehicles on info dialog
	 */
	public  void setVehiclesInfo(Map<Integer, Vehicle> infoV)
	{
		this.vehicles = infoV;
	}
	
	/**
	 * remove the vehicles that which were not when the user pressed a save button
	 * @param infoV old map of vehicles on info dialog
	 * @return false if infoV is null else return true
	 */
	public  boolean removeVehiclesInfo(Map<Integer, Vehicle> infoV)
	{
		if(infoV==null)
			return false;
		
		vehicles.forEach((id,vehicle)->{
			if(infoV.get(id) == null)
			{
				int i = getRowNumber(id);
				model.removeRow(i);
			}
		});
		return true;
	}
}
