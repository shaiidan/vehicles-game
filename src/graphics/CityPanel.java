package graphics;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vehicles.*;

/**
 * @author Shai Idan
 * @version 4 , 16/5/19
 */
public class CityPanel extends JPanel implements ActionListener,Observer
{
	private static final long serialVersionUID = 1L;
	private static CityPanel single_instance = null;
	private JButton addVehicle, clear,fuel_food,lights,info,exit, saveButton,backButton,borderButton;
	private BufferedImage img = null;
	private String pathImageBackground = ".\\src\\graphics_image\\cityBackground.png";
	private JPanel panButton;
	private final int ACTIVE_NUMBER_VEHICLES = 5;
	private final int MAX_NUMBER_VEHICLES = 10;
	private Map<Integer, Vehicle> vehicles = new HashMap<Integer, Vehicle>(); // for vehicles on the screen
	private static ExecutorService pool;
	private InfoVehicleDialog infoDialog;
	public static int countVehicles = 0 ;
	private VehicleMemento memento;
	private VehicleCaretaker caretaker = new VehicleCaretaker();
	
	
	private CityPanel(CityFrame frame)
	{
		pool = Executors.newFixedThreadPool(ACTIVE_NUMBER_VEHICLES);
		panButton = new JPanel();
		panButton.setLayout(new GridLayout(1,9));
		Vehicle.setActiveVehicles(vehicles);
		infoDialog = InfoVehicleDialog.getInstance();
		try {img = ImageIO.read(new File(pathImageBackground));}
		catch(IOException e) {
        	JOptionPane.showMessageDialog(new JDialog(),"Could not open background file");
        	System.out.println("Cannot load image");}
		
		addVehicle = new JButton("Add");
		clear = new JButton("Clear");
		fuel_food = new JButton("Fuel");
		lights = new JButton("Lights");
		info = new JButton("Info");
		saveButton = new JButton("Save");
		backButton = new JButton("Back");
		borderButton = new JButton("Border");
		exit = new JButton("Exit");
		// change the font
		addVehicle.setFont(new Font("Arial", Font.BOLD, 15));
		clear.setFont(new Font("Arial", Font.BOLD, 15));
		fuel_food.setFont(new Font("Arial", Font.BOLD, 15));
		lights.setFont(new Font("Arial", Font.BOLD, 15));
		info.setFont(new Font("Arial", Font.BOLD, 15));
		exit.setFont(new Font("Arial", Font.BOLD, 15));
		saveButton.setFont(new Font("Arial", Font.BOLD, 15));
		backButton.setFont(new Font("Arial", Font.BOLD, 15));
		borderButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		// add to panel button
		panButton.add(addVehicle);
		panButton.add(clear);
		panButton.add(fuel_food);
		panButton.add(lights);
		panButton.add(info);
		panButton.add(saveButton);
		panButton.add(backButton);
		panButton.add(borderButton);
		panButton.add(exit);
		// add to first panel
		setLayout(new BorderLayout());
		add(panButton,BorderLayout.SOUTH);
		// add to event
		addVehicle.addActionListener(this);
		clear.addActionListener(this);
		fuel_food.addActionListener(this);
		lights.addActionListener(this);
		info.addActionListener(this);
		exit.addActionListener(this);
		saveButton.addActionListener(this);
		backButton.addActionListener(this);
		borderButton.addActionListener(this);
		
		// exit 
		frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	// stop the all threads
    			vehicles.forEach((id,vehicle)->
    			{
    				vehicle.CloseFromCityVehicle();
    			});
    			pool.shutdown();
                System.exit(0);
            }
        });
	}
	
	public static CityPanel getInstance(CityFrame frame)
	{
		if(single_instance == null)
			single_instance = new CityPanel(frame);
		
		return single_instance;
	}
	
	public void AddVehicleListener(Vehicle v)
	{
		if(v != null)
		{
			if(!IsVehiclesFull())
			{
				vehicles.put(v.getID(), v);
				vehicles.get(v.getID()).addObserver(infoDialog);
				vehicles.get(v.getID()).addObserver(this);
				pool.execute(vehicles.get(v.getID()));
				System.out.println("The vehicle add to the city: ");
				System.out.println(v.toString());
				System.out.println("----------------------------------------");
				countVehicles++;
			}
			else
				JOptionPane.showMessageDialog(this,"Sorry!!\nThere are already 10 vehicles in the city","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean IsVehiclesFull()
	{
		return countVehicles >= MAX_NUMBER_VEHICLES ;
	}
	
	/**
	 * buttons events
	 */
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource().equals(addVehicle)) // click the button add vehicle
		{
			if(IsVehiclesFull()) // already have vehicle on the screen
				JOptionPane.showMessageDialog(this,"Sorry!!\nThere are already vehicles in the city","Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				AddVehicleDialog add = AddVehicleDialog.getInstance(this);
				add.setVisible(true);
			}
		}
		else if(ev.getSource().equals(clear))  // click the button clear
		{
			fuel_food.setBackground(null);
			// stop the all threads are active
			
			Vehicle[] activeVehicles = vehicles.values().toArray(new Vehicle[0]);
			
			for(int i=0;i<activeVehicles.length;i++)
			{
				if(activeVehicles[i].getIsAlive())
				{
					activeVehicles[i].deleteObservers();
					activeVehicles[i].setIsAlive(false);
					vehicles.remove(activeVehicles[i].getID());
					countVehicles--;
				}
				
			}
			repaint();
		}
		else if(ev.getSource().equals(fuel_food)) // click the button fuel/food
		{
			fuel_food.setBackground(null);
			vehicles.forEach((id,vehicle)->{
				if(vehicle.getVehicleName().equals("Carriage"))
				{
					Carriage c = (Carriage)vehicle;
					c.getPackAnimal().eat();
				}
				else if(vehicle.getVehicleName().endsWith("Car")) // for Benzine and Solar
				{
					Car c = (Car)vehicle;
					c.Refuel();
				}
				
				synchronized(vehicle)
				{
					vehicle.notify();
				}
			});
			
		}
		
		else if(ev.getSource().equals(lights)) // click the button lights
		{
			if(vehicles.size() != 0) // change the lights of this vehicles
			{
				vehicles.forEach((id,vehicle)->{
					vehicle.changeLights();
				});
			}
		}
		
		else if(ev.getSource().equals(info)) //  // click the button info
		{
			// open table the show information about this vehicle
			infoDialog.setVisible(true);
		}
		else if(ev.getSource().equals(saveButton)) //  // click the button save for memento
		{
			this.saveVehiclesCity();
		}
		else if(ev.getSource().equals(backButton)) //  // click the button back for memento
		{
			this.restoreCity();
		} 
		else if(ev.getSource().equals(borderButton)) //  // click the button border for property
		{
			if(vehicles.size() != 0) // change the lights of this vehicles
			{
				vehicles.forEach((id,vehicle)->{
					System.out.println(vehicle.getBorder());
					vehicle.changeBorder();
					System.out.println(vehicle.getBorder());
				});
			}
		} 
		
		else if(ev.getSource().equals(exit)) //  // click the button exit
		{
			// stop the all threads
			
			vehicles.forEach((id,vehicle)->{
				vehicle.CloseFromCityVehicle();
			});
			pool.shutdown();
			System.exit(0);
		} 
	}
	
	private void saveVehiclesCity()
	{
		//save for memento
		System.out.println("Save the city!!");
		memento = new VehicleMemento(this.infoDialog.getVehiclesInfo(),this.vehicles);
		caretaker.addMemento(memento);
	}
	
	
	private void restoreCity()
	{
		//back
		if(!caretaker.isCaretakerEmpty())
		{
			System.out.println("Back to last....");
			vehicles.forEach((id,vehicle)->{
				vehicle.setIsAlive(false);
			});
			memento = caretaker.getMemento();
			this.vehicles = memento.getVehiclesActive();
			Vehicle.setActiveVehicles(vehicles);
			
			vehicles.forEach((id,vehicle)->{
				vehicles.get(vehicle.getID()).addObserver(infoDialog);
				vehicles.get(vehicle.getID()).addObserver(this);
				pool.execute(vehicle);
			});
			countVehicles = vehicles.size();
			this.infoDialog.removeVehiclesInfo(memento.getVehiclesInfo());
			this.infoDialog.setVehiclesInfo(memento.getVehiclesInfo());
		}
		else
			JOptionPane.showMessageDialog(this,"Sorry!!\nYou need to save before you can back","Error",JOptionPane.ERROR_MESSAGE);

			
		
	}
	/**
	 * paint of screen
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight()-30,this);
		
		synchronized(vehicles)
		{
			vehicles.forEach((id,vehicle)->{
				vehicle.drawObject(g);
			});
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 == messageObserver.fuelNeeds)
		{
			fuel_food.setBackground(java.awt.Color.RED);
		}
		else if(arg1 == messageObserver.exitFromCity)
		{
			System.out.println("Bye Bye....");
		}
	}
	
}