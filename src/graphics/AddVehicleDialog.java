package graphics;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vehicles.*;

/**
 * @author Shai Idan (311324602) and Harel Jerbi (204223184) 
 * @version 2
 */
public class AddVehicleDialog extends JDialog implements ActionListener, ChangeListener
{
	private static final long serialVersionUID = 1L;
	private JPanel pan1,pan2,panChooseVehicle,panChooseColor,panForBike,pancalcs;
	private JButton calc1,calc2;
	private final int NUM_OF_CHOICE = 4; // the number of vehicles type  
	private JRadioButton[] radioButtonsVehicle,radioButtonsColor;   
	private final ButtonGroup group1,group2; 
	private CityPanel city;
	private static final int FPS_MIN =0, FPS_MAX=10, FPS_INIT=5;
	private static AddVehicleDialog single_instance = null;
	JSlider framesPerSecond;
	
	private AddVehicleDialog(CityPanel city)
	{
		this.setTitle("add a vehicle to city");
		this.city = city;
		this.setSize(800,400);
		//this.setBounds(500, 300, 800, 400);

		pan1 = new JPanel();
		pan1.setLayout(new GridLayout(1,2));
		
		Border border = BorderFactory.createTitledBorder("Choose a vehicle to the city");
		radioButtonsVehicle = new JRadioButton[NUM_OF_CHOICE];
		group1 =  new ButtonGroup(); 
		panChooseVehicle = new JPanel();
		panChooseVehicle.setLayout(new GridLayout(2,2));
		panChooseVehicle.setBorder(border);
		radioButtonsVehicle[0] = new JRadioButton("Bike");
		radioButtonsVehicle[0].setFont(new Font("Arial",Font.BOLD,20));
		radioButtonsVehicle[0].addActionListener(this);
		radioButtonsVehicle[0].setSelected(true);
				
		radioButtonsVehicle[1] = new JRadioButton("Carriage");
		radioButtonsVehicle[1].setFont(new Font("Arial",Font.BOLD,20));
		radioButtonsVehicle[1].addActionListener(this);
		
		
		radioButtonsVehicle[2] = new JRadioButton("Benzine Car");
		radioButtonsVehicle[2].setFont(new Font("Arial",Font.BOLD,20));
		radioButtonsVehicle[2].addActionListener(this);
		
		radioButtonsVehicle[3] = new JRadioButton("Solar Car");
		radioButtonsVehicle[3].setFont(new Font("Arial",Font.BOLD,20));		
		radioButtonsVehicle[3].addActionListener(this);
		
		for(int i=0;i<NUM_OF_CHOICE;i++)
		{
			group1.add(radioButtonsVehicle[i]);
			panChooseVehicle.add(radioButtonsVehicle[i]);
		}
		
		Border border1 = BorderFactory.createTitledBorder("Choose a color");
		radioButtonsColor = new JRadioButton[NUM_OF_CHOICE];
		group2 =  new ButtonGroup(); 
		panChooseColor = new JPanel(new GridLayout(1,4));
		panChooseColor.setBorder(border1);
		radioButtonsColor[0] = new JRadioButton("Red");
		radioButtonsColor[0].setFont(new Font("Arial",Font.BOLD,20));
		radioButtonsColor[0].addActionListener(this);
		radioButtonsColor[0].setSelected(true);
				
		radioButtonsColor[1] = new JRadioButton("Green");
		radioButtonsColor[1].setFont(new Font("Arial",Font.BOLD,20));
		radioButtonsVehicle[1].addActionListener(this);
		
		
		radioButtonsColor[2] = new JRadioButton("Silver");
		radioButtonsColor[2].setFont(new Font("Arial",Font.BOLD,20));
		radioButtonsColor[2].addActionListener(this);
		
		radioButtonsColor[3] = new JRadioButton("White");
		radioButtonsColor[3].setFont(new Font("Arial",Font.BOLD,20));		
		radioButtonsColor[3].addActionListener(this);
		
		for(int i=0;i<NUM_OF_CHOICE;i++)
		{
			group2.add(radioButtonsColor[i]);
			panChooseColor.add(radioButtonsColor[i]);
		}
		pan1.add(panChooseVehicle);
		pan1.add(panChooseColor);
		
		pan2 = new JPanel(new GridLayout(2,1));
		panForBike = new JPanel(new BorderLayout());
		framesPerSecond = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
		framesPerSecond.addChangeListener(this);

		//Turn on labels at major tick marks.
		framesPerSecond.setMajorTickSpacing(10);
		framesPerSecond.setMinorTickSpacing(2);
	
		//Create the label table
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("0"));
		labelTable.put( new Integer(2), new JLabel("2") );
		labelTable.put( new Integer(4), new JLabel("4") );
		labelTable.put( new Integer(6), new JLabel("6") );
		labelTable.put( new Integer(8), new JLabel("8") );
		labelTable.put( new Integer(10), new JLabel("10") );
		labelTable.get(0).setFont(new Font("Arial",Font.BOLD,20));
		labelTable.get(2).setFont(new Font("Arial",Font.BOLD,20));
		labelTable.get(4).setFont(new Font("Arial",Font.BOLD,20));
		labelTable.get(6).setFont(new Font("Arial",Font.BOLD,20));
		labelTable.get(8).setFont(new Font("Arial",Font.BOLD,20));
		labelTable.get(10).setFont(new Font("Arial",Font.BOLD,20));
		framesPerSecond.setLabelTable(labelTable);
		framesPerSecond.setPaintTicks(true);
		framesPerSecond.setPaintLabels(true);
		
		Border border2 = BorderFactory.createTitledBorder("Choose bike gears");
		framesPerSecond.setBorder(border2);
		panForBike.add(framesPerSecond,BorderLayout.SOUTH);
		pan2.add(pan1);
		pan2.add(panForBike);
		
		setLayout(new BorderLayout());
		add(pan2,BorderLayout.NORTH);
		
		
		// Button panel start
		pancalcs = new JPanel();
		calc1 = new JButton("OK");
		calc2 = new JButton("Cancel");
		calc1.setFont(new Font("Arial",Font.BOLD,20));
		calc2.setFont(new Font("Arial",Font.BOLD,20));
		pancalcs.setLayout(new GridLayout(1,2));
		pancalcs.add(calc1);
		pancalcs.add(calc2);
		add(pancalcs,BorderLayout.SOUTH);
		
		calc1.addActionListener(this);
		calc2.addActionListener(this);
		
	}
	
	/**
	 * for singleton DP - get one instance for this class
	 * @param pan the instance of CityPanel
	 * @return the instance of this class
	 */
	public static AddVehicleDialog getInstance(CityPanel pan)
	{
		if(single_instance == null)
			single_instance = new AddVehicleDialog(pan);
		return single_instance;
	}
	
	/**
	 * the click events
	 */
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		VehicleFactory createVehicle = new VehicleFactory();
		if(ev.getSource().equals(calc1)) // click the button OK
		{
			Color col;
			int numberGear = 0;
			String type = "";
			if(radioButtonsColor[0].isSelected())
				col = Color.red;
			else if(radioButtonsColor[1].isSelected())
				col = Color.green;
			else if(radioButtonsColor[2].isSelected())
				col = Color.silver;
			else
				col = Color.white;
			
			// select a Bike
			if(radioButtonsVehicle[0].isSelected())
			{
				type = radioButtonsVehicle[0].getText();
				numberGear = framesPerSecond.getValue();
			}
			// select a Carriage
			else if(radioButtonsVehicle[1].isSelected())
			{
				type = radioButtonsVehicle[1].getText();
			}
			// select a Car
			else if(radioButtonsVehicle[2].isSelected())
			{	
				type = radioButtonsVehicle[2].getText();
			}
			else if(radioButtonsVehicle[3].isSelected())
			{
				type = radioButtonsVehicle[3].getText();
			}
			
			this.setVisible(false);
			city.AddVehicleListener(createVehicle.getVehicle(type,city,col,numberGear));
		}
		
		//click Bike
		else if(ev.getSource().equals(radioButtonsVehicle[0]))
			panForBike.setVisible(true);
		
		//click other vehicle 
		else if(ev.getSource().equals(radioButtonsVehicle[1])||
				ev.getSource().equals(radioButtonsVehicle[2])||ev.getSource().equals(radioButtonsVehicle[3]))
			panForBike.setVisible(false);
		
		else if(ev.getSource().equals(calc2)) // click the button Cancel
			this.setVisible(false);
	}
	

	@Override
	public void stateChanged(ChangeEvent arg0) 
	{
	}
	
	
}// end class AddVehicleDialog
