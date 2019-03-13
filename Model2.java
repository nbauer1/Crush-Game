/**
 * @author: Nick Bauer
 * @version: Text Model
 * ITEC 220
 * Project1
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
 * 
 * The Model2 class is very similar to the original Model1 class but replaces images with 
 * characters to play a text version
 *
 */

public class Model2 
{
		private JFrame f = new JFrame("Arithmatic Crush!"); 
		private JPanel panel = new JPanel(new GridLayout(8,8));;
		private JButton[][] buttons = new JButton[8][8];
	
		private boolean deletedRow = false;
		private boolean deletedCol = false;
	
		private final int BUTTONSIZE = 75;
		private final int BUTTONFONTSIZE = 50;
		private final int BORDERSIZE = 10;

		/**
		 * 
		 * continues generating field of random buttons/actionListeners UNTIL there is a 
		 * possible move for the user to make 
		 * 
		 */
		public Model2(Controller c)
		{
	    	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			boolean reset = true;
			do {
				reset = false;
				genButtons();
				if(win()) {
					for(int row = 0; row < buttons.length; row++) {
						for(int col = 0; col < buttons[row].length; col ++){
				    		panel.remove(buttons[row][col]);
							buttons[row][col] = null;
						}
					}
					buttons = new JButton[8][8];
					reset = true;
				}
				}while(reset);
		    for(int row = 0; row < buttons.length; row++){
		    	for(int col = 0; col < buttons[row].length; col++)
		    	{   
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
			panel.setBackground(Color.gray);
			panel.setOpaque(true);
			Border red = BorderFactory.createLineBorder(Color.blue, BORDERSIZE);
			panel.setBorder(red);
		}
		
		/**
		 * genStr
		 * 
		 * generates a string of one random special character
		 * @return random special character
		 */
		public String genStr()
		{
	        String string = "+*=%@$&";
			Random rand = new Random();
			int num = rand.nextInt(string.length());
			char ch = string.charAt(num);
		    String str = Character.toString(ch);
		    return str;
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
		    		buttons[row][col] = new JButton(genStr());
		    		buttons[row][col].setPreferredSize(new Dimension(BUTTONSIZE,BUTTONSIZE));
		    		buttons[row][col].setFont(new Font("Arial", Font.BOLD, BUTTONFONTSIZE));
		    		buttons[row][col].setForeground(Color.BLUE);
		    		buttons[row][col].setBackground(Color.GRAY);
		    		buttons[row][col].setOpaque(true);
		    		panel.add(buttons[row][col]);	
		    	}
		    }
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
		    			View v = new View(2, null);
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
			if(buttons[row][col].getText().equals(buttons[row - 1][col].getText()) &&
				buttons[row][col].getText().equals(buttons[row + 1][col].getText()))
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
			if(buttons[row][col].getText().equals(buttons[row][col - 1].getText()) &&
			   buttons[row][col].getText().equals(buttons[row][col + 1].getText()))
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
					String str = buttons[i][col].getText();
					buttons[actualRow][col].setText(str);
					String str2 = buttons[i][col + 1].getText();
					buttons[actualRow][col + 1].setText(str2);
					String str3 = buttons[i][col - 1].getText();
					buttons[actualRow][col - 1].setText(str3);
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
						String str = buttons[i][col].getText();
						buttons[clickedRow + change][col].setText(str);
						buttons[i][col].setText(genStr());
						randColBounds(row,col);
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
			buttons[0][col].setText(genStr());
			buttons[0][col - 1].setText(genStr());
			buttons[0][col + 1].setText(genStr());
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
				String str = buttons[1][col].getText();
				buttons[row + 1][col].setText(str);
				String str1 = buttons[0][col].getText();
				buttons[row][col].setText(str1);
				for(int i = row - 1; i >= 0; i--)
				{
					buttons[i][col].setText(genStr());
				}
			}
			else if(row == 2)
			{
				String str = buttons[0][col].getText();
				buttons[row + 1][col].setText(str);
				for(int i = row; i >= 0; i--)
				{
						buttons[i][col].setText(genStr());
				}
			}
			else if(row == 1)
			{
				for(int i = row + 1; i >= 0; i--)
				{
					buttons[i][col].setText(genStr());
				}
			}
		}
		
		/**
		 * win
		 * 
		 * traverses through appropriate JButtons to confirm no more moves are possible
		 * @return true if user has won the game (no more possible moves)
		 */
		public boolean win()
		{
			boolean result = true;
			for(int row = 0; row < buttons.length; row++)
			{
				for(int col = 0; col < buttons[row].length && result; col++)
				{
					if(row > 0 && row < buttons.length - 1)
					{
						if(buttons[row][col].getText().equals(buttons[row - 1][col].getText()) &&
						buttons[row][col].getText().equals(buttons[row + 1][col].getText())){
							result = false;
							System.out.println("Answer: " + row + " col: " + col);
						}
					}
					if(col > 0 && col < buttons[row].length - 1){
							if(buttons[row][col].getText().equals(buttons[row][col - 1].getText()) &&
							buttons[row][col].getText().equals(buttons[row][col + 1].getText()))
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

