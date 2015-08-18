import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
	|===========================================================|
	|	  	Exercise #  :	 1									|
	|															|
	|   	File name   :	 Menu.java							|
	|		Date		:	 16/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov	(327011813)			|
	|		Author 3    :	 Rita Markovich	(304492291)			|
	|===========================================================|
*/

public class Menu extends JPanel 
{
	//===================================================================================
	//						Initial And Constant Values
	//===================================================================================
	private final static double G_EARTH = 9.8;
	private final static double G_MOON  = 1.6;
	private final static double G_MARSE = 3.8;
	private final static double G_OTHER = 5.0;
	
	private final static double INITIAL_G 			= G_EARTH;
	private final static double INITIAL_COR 		= 0.8;
	private final static int    INITIAL_HEIGHT 		= 10;
	private final static String INITIAL_ENVIRONMENT	= "Earth";
	
	private final String[] WORLDS = {"Earth", "Moon", "Mars", "Other"};
	
	
	//===================================================================================
	//							Variables 
	//===================================================================================
	private JPanel	menuPanel;
	private World 	worldPanel;
	
	private int 	_height;
	private double 	_cor;
	private double 	_gravity;
	private String 	_environment;
	
	private JComboBox<String> environmentCombo;
	
	private JSpinner gravitySpinner;
	private JSpinner heightSpinner;
	private JSpinner corSpinner;
	
	private JLabel environmentLBL;
	private JLabel gravityLBL;
	private JLabel heightLBL;
	private JLabel corLBL;
	
	private JButton cmdReset;
	private JButton cmdStart;
	
	
	//===================================================================================
	//							Constructor
	//===================================================================================
	public Menu()
	{
		//====== Initial ComboBox =============
		environmentCombo = getEnvironmentComboBox();
				
		//======= Initial Buttons ==============
		cmdReset = getResetButton();
		cmdStart = getStartButton();
		
		//======= Initial Spinners =============
		gravitySpinner 	= getGravitySpinner();
		corSpinner 		= getCorSpinner();
		heightSpinner	= getHeightSpinner();
		
		//======= Initial Labels ===============
		environmentLBL  = new JLabel("Environment:");
		gravityLBL 	 	= new JLabel("Gravity:");
		heightLBL 	 	= new JLabel("Height:");
		corLBL 		 	= new JLabel("Cor:");

		//======= Initial World Values =========
		_height 	 = INITIAL_HEIGHT;
		_cor 		 = INITIAL_COR;
		_gravity 	 = INITIAL_G;
		_environment = INITIAL_ENVIRONMENT;
		
		
	    //============= Panels =================
		menuPanel = new JPanel();
	    worldPanel = new World(INITIAL_G, INITIAL_COR, INITIAL_HEIGHT,INITIAL_ENVIRONMENT);

	    
	    //======= Add Menu Controllers =========
	    this.setLayout( new BorderLayout() );
	    menuPanel.add(environmentLBL);
	    menuPanel.add(environmentCombo);
	    
	    menuPanel.add(gravityLBL);
	    menuPanel.add(gravitySpinner);
	    
	    menuPanel.add(corLBL);
	    menuPanel.add(corSpinner);
	    
	    menuPanel.add(heightLBL);
	    menuPanel.add(heightSpinner);
   
	    menuPanel.add(cmdReset);
	    menuPanel.add(cmdStart);
	   
	    menuPanel.setBackground(Color.white);

	    
	    //=========  Add Panels  =============
	    this.add(menuPanel, 	BorderLayout.NORTH);
	    this.add(worldPanel, 	BorderLayout.CENTER);
	}
	
	
	
	//===================================================================================
	//							Environment ComboBox
	//===================================================================================
	private JComboBox<String> getEnvironmentComboBox()
	{
		JComboBox<String> combo = new JComboBox<>(WORLDS);
		
		combo.addActionListener (new ActionListener ()
		{
			public void actionPerformed(ActionEvent e)
			{
				_environment = (String)environmentCombo.getSelectedItem();
				
				switch(_environment)
				{
					case "Earth":	gravitySpinner.setValue(G_EARTH);	
									gravitySpinner.setEnabled(false);
									return;
					case "Moon" :	gravitySpinner.setValue(G_MOON) ;
									gravitySpinner.setEnabled(false);
									return;
					case "Mars" :	gravitySpinner.setValue(G_MARSE);
									gravitySpinner.setEnabled(false);
									return;
					case "Other":	gravitySpinner.setValue(G_OTHER);
									gravitySpinner.setEnabled(true);
									return;
				}
			}
		});
		
		return combo;
	}
	
	//===================================================================================
	//							Gravity Spinner
	//===================================================================================
	private JSpinner getGravitySpinner()
	{
		JSpinner spinner;
		
		spinner = new JSpinner(new SpinnerNumberModel(
				INITIAL_G,	//initial value
				0,			//min
				20,			//max
				0.1));		//step);

		spinner.setEnabled(false);
		
		spinner.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e) 
			{
				_gravity = (double) gravitySpinner.getValue();
			}
		});
		
		return spinner;
	}
	
	//===================================================================================
	//								Cor Spinner
	//===================================================================================
	private JSpinner getCorSpinner()
	{
		JSpinner spinner;
		
		spinner = new JSpinner(new SpinnerNumberModel(
				INITIAL_COR,	//initial value
				0.0,			//min
				1.0,			//max
				0.1));			//step);
		spinner.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e) 
			{
				//environmentCombo.setSelectedIndex(3);
				_cor = (double) corSpinner.getValue();
			}
		});
		
		return spinner;
	}
	
	
	//===================================================================================
	//							Height Spinner
	//===================================================================================
	private JSpinner getHeightSpinner()
	{
		JSpinner spinner;
		
		spinner = new JSpinner(new SpinnerNumberModel(
				INITIAL_HEIGHT,	//initial value
				0,				//min
				100,			//max
				1));			//step);
		spinner.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e) 
			{
				_height = (int) heightSpinner.getValue();
			}
		});
		
		return spinner;
	}
	
	
	//===================================================================================
	//							Start Button
	//===================================================================================
	private JButton getStartButton()
	{
		JButton button = new JButton("Start");
		
		button.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				worldPanel.stopTimer();
				remove(worldPanel);
				worldPanel = new World(_gravity, _cor, _height, _environment);
				add(worldPanel, BorderLayout.CENTER);
				repaint();
				
		        revalidate();
				
			}
		});
		
		return button;
	}
	
	
	//===================================================================================
	//							Reset Button
	//===================================================================================
	private JButton getResetButton()
	{
		JButton button = new JButton("Reset");
		
		button.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				gravitySpinner.setValue(G_EARTH); 		//9.8
				heightSpinner.setValue(10); 			///10
				corSpinner.setValue(0.8); 				//0.8
				environmentCombo.setSelectedIndex(0); 	//Earth
			}
		});
		
		
		return button;
	}
	
	
	
	
	
	
}
