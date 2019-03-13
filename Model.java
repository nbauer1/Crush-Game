/**
 * @author: Nick Bauer
 * @version: Image Model
 * ITEC 220
 * Project1
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
 * 
 * The Model Class uses ImageIcons in its methods to manipulate it's positioning in the 
 * 2D Array of buttons
 *
 */
public class Model extends JFrame
{
	private JFrame f = new JFrame("Radford Crush!"); 
	private JPanel panel = new JPanel(new GridLayout(8,8));;
	private JButton[][] buttons = new JButton[8][8];
	

	private ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/Source/icon1.jpg"), "icon1");
	private ImageIcon icon2 = new ImageIcon(this.getClass().getResource("/Source/icon2.jpg"), "icon2");
	private ImageIcon icon3 = new ImageIcon(this.getClass().getResource("/Source/icon3.png"), "icon3");
	private ImageIcon icon4 = new ImageIcon(this.getClass().getResource("/Source/icon4.jpg"), "icon4");
	private ImageIcon icon5 = new ImageIcon(this.getClass().getResource("/Source/icon5.jpg"), "icon5");
	private ImageIcon icon6 = new ImageIcon(this.getClass().getResource("/Source/icon6.gif"), "icon6");
	private ImageIcon icon7 = new ImageIcon(this.getClass().getResource("/Source/icon7.jpg"), "icon7");
	
	private boolean deletedRow = false;
	private boolean deletedCol = false;
	
	private final int BUTTONSIZE = 75;
	private final int BORDERSIZE = 10;
	
	
	/**
	 * 
	 * continues generating field of random buttons/actionListeners UNTIL there is a 
	 * possible move for the user to make 
	 * 
	 */
	public Model(Controller c)
	{
    	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		boolean reset = true;
		do { 
			reset = false;
			genButtons();
			if(win()) {
				for(int row = 0; row < buttons.length; row++) {
					for(int col = 0; col < buttons[row].length; col ++) {
			    		panel.remove(buttons[row][col]);
						buttons[row][col] = null;
					}
				}
				buttons = new JButton[8][8];
				reset = true;
			}
			} while(reset);
	    for(int row = 0; row < buttons.length; row++) {
	    	for(int col = 0; col < buttons[row].length; col++) {
	    		setAction(row,col);	    		
	    	}
	    }
	    setPanelStyle();
    	f.add(panel);
    	f.pack();
    	f.setVisible(true);
	}
	
	/**
	 * setPanelStyle
	 * 
	 * setting panel borders and colors
	 */
	public void setPanelStyle()
	{
		panel.setBackground(Color.BLACK);
		panel.setOpaque(true);
		Border red = BorderFactory.createLineBorder(Color.red, BORDERSIZE);
		panel.setBorder(red);
	}
	/**
	 * genButtons
	 * 
	 * generating buttons to fill 2D Button Array on a JPanel
	 */
	public void genButtons()
	{
		for(int row = 0; row < buttons.length; row++)
	    {
	    	for(int col = 0; col < buttons[row].length; col++)
	    	{   
	    		buttons[row][col] = new JButton(genRandImageIcon());
	    		buttons[row][col].setPreferredSize(new Dimension(BUTTONSIZE, BUTTONSIZE));
	    		panel.add(buttons[row][col]);
	    	}
	    }
	}
	
	/**
	 * genRandImageIcon
	 * 
	 * generating a random ImageIcon
	 * @return Random ImageIcon
	 */
	public ImageIcon genRandImageIcon()
	{
		String str = "1234567";
		Random rand = new Random();
		int num = rand.nextInt(5);
		char ch = str.charAt(num);
		ImageIcon ii = new ImageIcon();
		if(ch == '1')
			ii = icon1;
		else if(ch == '2')
			ii = icon2;
		else if(ch == '3')
			ii = icon3;
		else if(ch == '4')
			ii = icon4;
		else if(ch == '5')
			ii = icon5;
		else if(ch == '6')
			ii = icon6;
		else if(ch == '7')
			ii = icon7;
		return ii;
	}
	
	/**
	 * setImageIcon
	 * 
	 * setting correct ImageIcon to corresponding String Parameter
	 * @param ImageIcon's String Description
	 * @return Corresponding ImageIcon
	 */
	public ImageIcon setImageIcon(String str)
	{
		ImageIcon ii = new ImageIcon();
		if(str == "icon1")
			ii = icon1;
		else if(str == "icon2")
			ii = icon2;
		else if(str == "icon3")
			ii = icon3;
		else if(str == "icon4")
			ii = icon4;
		else if(str == "icon5")
			ii = icon5;
		else if(str == "icon6")
			ii = icon6;
		else if(str == "icon7")
			ii = icon7;
		return ii;
	}
	
	/**
	 * setAction
	 * 
	 * adding an ActionListener to read user's JButton input
	 * @param Current Clicked Row
	 * @param Current Clicked Column
	 */
	public void setAction(int row, int col)
	{
	    buttons[row][col].addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		confirmRow(row,col);
	    		confirmColumn(row,col);
	    		if(win())
	    	    {
	    	    	View v = new View(1, null);
	    	    	v.winPanel();
	    	    }
	    	}
	    });
	}
	
	/**
	 * confirmColumn
	 * 
	 * When a column of matching JButtons is confirmed, it causes JButtons above it to "fall" 
	 * @param row: Current Clicked Row
	 * @param col: Current Clicked Column
	 * @throws ArrayIndexOutOfBoundsException when Column Clicked is either the 
	 * far right or left Column
	 */
	public void confirmColumn(int row, int col)
	{
		try {
			if(getString(buttons[row][col]).equals(getString(buttons[row - 1][col])) &&
			getString(buttons[row][col]).equals(getString(buttons[row + 1][col])))
			{
				deletedCol = true;
				shiftCols(row,col);
			}
		} catch(ArrayIndexOutOfBoundsException e)
		{ 
			System.out.println("Exception Thrown");
		}
	}
	
	/**
	 * confirmRow
	 * 
	 * When a row of matching JButtons is confirmed, it causes JButtons above it to "fall" 
	 * @param row: Current Clicked Row
	 * @param col: Current Clicked Column
	 * @throws ArrayIndexOutOfBoundsException when Row Clicked is either the 
	 * top or bottom row
	 */
	public void confirmRow(int row, int col)
	{
		try {
			if(getString(buttons[row][col]).equals(getString(buttons[row][col - 1])) &&
			getString(buttons[row][col]).equals(getString(buttons[row][col + 1])))
			{
				deletedRow = true;
				shiftRows(row,col);
				if(row == 0)
				{
					randRow(col);
				}
			}
		} catch(ArrayIndexOutOfBoundsException e)
		{ 
			System.out.println("Exception Thrown");
		}
	}
	
	/**
	 * getString
	 * 
	 * @param jb: Current Clicked JButton
	 * @return String Description of IconImage
	 */
	public String getString(JButton jb)
	{
		String str = ((ImageIcon)jb.getIcon()).getDescription();
		return str;
	}
	
	/**
	 * shiftRows
	 * 
	 * shifting rows to "fall" when a row is deleted
	 * @param row: Current Clicked Row
	 * @param col: Current Clicked Column
	 */
	public void shiftRows(int row, int col)
	{
		if(deletedRow == true)
		{
			int actualRow = row;
			for(int i = row - 1; i >= 0 ; i--)
			{
				String str = getString(buttons[i][col]);
				buttons[actualRow][col].setIcon(setImageIcon(str));
				String str2 = getString(buttons[i][col + 1]);
				buttons[actualRow][col + 1].setIcon(setImageIcon(str2));
				String str3 = getString(buttons[i][col - 1]);
				buttons[actualRow][col - 1].setIcon(setImageIcon(str3));
				actualRow--;
				if(i == 0)
				{
					randRow(col);
				}
			}
		}
	}
	
	/**
	 * shiftCols
	 * 
	 * Causes Columns to "fall" when a valid Column is clicked
	 * @param Current Clicked Row
	 * @param Current Clicked Column
	 */
	public void shiftCols(int row, int col)
	{
		if(deletedCol == true)
		{
			int clickedRow = row;
			int change = 1;
			if(row > 3)
			{
				for(int i = row - 2; i >= 0; i--)
				{
					String str = getString(buttons[i][col]);
					buttons[clickedRow + change][col].setIcon(setImageIcon(str));
					buttons[i][col].setIcon(genRandImageIcon());
					change--;
				}
			}
			else
			{
				randColBounds(row,col);
			}
		}
	}
	
	/**
	 * randRow
	 * 
	 * setting 3 top row elements to random Image Icons
	 * @param Current Clicked Column
	 */
	public void randRow(int col)
	{
		buttons[0][col].setIcon(genRandImageIcon());
		buttons[0][col - 1].setIcon(genRandImageIcon());
		buttons[0][col + 1].setIcon(genRandImageIcon());
	}
	
	/**
	 * randColBounds
	 * 
	 * testing bounds for shifting columns to avoid ArrayOutOfBoundsException
	 * @param Current Clicked Row
	 * @param Current Clicked Column
	 */
	public void randColBounds(int row, int col)
	{	
		if(row == 3)
		{
			String str = getString(buttons[1][col]);
			buttons[row + 1][col].setIcon(setImageIcon(str));
			String str1 = getString(buttons[0][col]);
			buttons[row][col].setIcon(setImageIcon(str1));
			for(int i = row - 1; i >= 0; i--)
			{
				buttons[i][col].setIcon(genRandImageIcon());
			}
		}
		else if(row == 2)
		{
			String str = getString(buttons[0][col]);
			buttons[row + 1][col].setIcon(setImageIcon(str));
			for(int i = row; i >= 0; i--)
			{
					buttons[i][col].setIcon(genRandImageIcon());
			}
		}
		else if(row == 1)
		{
			for(int i = row + 1; i >= 0; i--)
			{
				buttons[i][col].setIcon(genRandImageIcon());
			}
		}
	}
	
	/**
	 * win
	 * 
	 * checking appropriate JButtons to confirm no more moves are possible
	 * @return true if user has won the game (no more possible moves)
	 */
	public boolean win()
	{
		boolean result = true;
		for(int row = 0; row < buttons.length; row++)
		{
			for(int col = 0; col < buttons[row].length && result; col++)
			{
				if(row > 0 && row < buttons.length - 1){
					if(getString(buttons[row][col]).equals(getString(buttons[row - 1][col])) &&
					getString(buttons[row][col]).equals(getString(buttons[row + 1][col])))
					{
						result = false;
						System.out.println("Answer: " + row + " col: " + col);
					}
				}
				if(col > 0 && col < buttons[row].length - 1){
					if(getString(buttons[row][col]).equals(getString(buttons[row][col - 1])) &&
					getString(buttons[row][col]).equals(getString(buttons[row][col + 1])))
					{
						result = false;
						System.out.println("Answer: " + row + " col: " + col);
					}
				}
			}
		}
		if(result == true)
			f.dispose();
		return result;
	}
}

