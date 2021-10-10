package graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Shai Idan (311324602) and Harel Jerbi (204223184) 
 * @version 2
 */
public class CityFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static CityFrame single_instance = null;
	private JMenuBar menuBar;
	private JMenu menu, submenu;
	private JMenuItem itemMenu,itemSubmenu;
	public static final int MIDDLE_HEIGHT = 220;
	public static final int MIN_HEIGHT = 0;
	public static final int MAX_HEIGHT = 444;
	public static final int MIN_WIDTH = 0;
	public static final int MAX_WIDTH = 728;
	
	
	private CityFrame()
	{
		super("City");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		//this.setBounds(550,100,800, 600);
		this.setResizable(false);
		
		// add panel city
		add(CityPanel.getInstance(this));
		
		// add menu
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		submenu = new JMenu("Help");
		menu.setFont(new Font("Arial", Font.PLAIN, 20));
		submenu.setFont(new Font("Arial", Font.PLAIN, 20));
		itemMenu = new JMenuItem("Exit");
		itemMenu.addActionListener(this);
		itemSubmenu = new JMenuItem("Help");
		itemSubmenu.addActionListener(this);
		itemMenu.setFont(new Font("Arial", Font.PLAIN, 20));
		itemSubmenu.setFont(new Font("Arial", Font.PLAIN, 20));
		menu.add(itemMenu);
		submenu.add(itemSubmenu);
		menuBar.add(menu);
		menuBar.add(submenu);
		
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * the events of menu
	 */
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource().equals(itemMenu))
		{
			System.exit(0);
		}
		else if(ev.getSource().equals(itemSubmenu))
		{
			JOptionPane.showMessageDialog(this,"Home Work 2\nGUI","Message",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * for singleton DP - get one instance for this class
	 * @return the instance of this class
	 */
	public static CityFrame getInstance()
	{
		if(single_instance == null)
			single_instance = new CityFrame();
		
		return single_instance;
	}
	
	public static void main(String [] args)
	{
		CityFrame city = CityFrame.getInstance();
		city.setVisible(true);
	}
	

}// end class VehicleFrame
