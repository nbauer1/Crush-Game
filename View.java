/**
 * @author: Nick Bauer
 * @version: View
 * ITEC 220
 * Project1
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;


public class View
{
	private JFrame fWin = new JFrame("Congratulations!!"); 
	private JButton jb = new JButton("Quit");
    private JButton jb2 = new JButton("Play Again");
    private JButton jb3 = new JButton("<html><div style = 'text-align: center;'>Play Text<br/>Edition<html>");
    private JButton jb4 = new JButton("<html><div style = 'text-align: center;'>Play Radford<br/>Edition<html>");
    
    private JLabel label = new JLabel("<html>Good Job,<br/> You Won!!<html>");
    private JPanel panel = new JPanel();
    
    private int gameType;
    private final int XFRAMESIZE = 275;
    private final int YFRAMESIZE = 300;
    private final int BUTTONFONTSIZE = 25;
    private final int LABELFONTSIZE = 50;
    private final int BORDERSIZE = 10;
    
    /**
     * View
     * 
     * takes in the current game type and displays corresponding menu
     * @param game type (1 = Radford, 2 = Text)
     * @param Controller c
     */
	public View(int type, Controller c)
	{
		gameType = type;
	}
	
	/**
	 * winPanel
	 * 
	 * creating win panel with "quit"/"play again"/"play text edition" options
	 */
	public void winPanel()
	{
		fWin.setPreferredSize(new Dimension(XFRAMESIZE,YFRAMESIZE));
		panel.setBackground(Color.BLUE);
		panel.setOpaque(true);
		Border red = BorderFactory.createLineBorder(Color.red, BORDERSIZE);
		panel.setBorder(red);
        label.setFont(new Font("Impact", Font.BOLD, LABELFONTSIZE));
        label.setForeground(Color.RED);
        addPanelElements();
		formatButtons();
		fWin.add(panel);
		fWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fWin.pack();
		fWin.setVisible(true);
		setWinAction();
		setNewGameType();
	}
	
	/**
	 * addPanelElements
	 * 
	 * adding all appropriate elements to the JPanel
	 */
	public void addPanelElements()
	{
		panel.add(label);
		panel.add(jb);
		panel.add(jb2);
		if(gameType == 1)
		{
			panel.add(jb3);
		}
		else if(gameType == 2)
		{
			panel.add(jb4);
		}
	}
	
	/**
	 * formatButtons
	 * 
	 * changes font size and color of text on buttons
	 */
	public void formatButtons()
	{
		jb.setFont(new Font("Impact", Font.PLAIN, BUTTONFONTSIZE));
		jb.setForeground(Color.BLUE);
		jb2.setFont(new Font("Impact", Font.PLAIN, BUTTONFONTSIZE));
		jb2.setForeground(Color.BLUE);
		jb3.setFont(new Font("Impact", Font.PLAIN, BUTTONFONTSIZE));
		jb3.setForeground(Color.BLUE);
		jb4.setFont(new Font("Impact", Font.PLAIN, BUTTONFONTSIZE));
		jb4.setForeground(Color.BLUE);
	}
	
	/**
	 * setWinAction
	 * 
	 * executes user's JButton input on the win panel
	 */
	public void setWinAction()
	{
		jb.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		fWin.dispose();
	    		System.exit(0);
	    	}
	    });
    	jb3.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		fWin.dispose();
	    		new Model2(null);
	    	}
	    });
    	jb4.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		fWin.dispose();
	    		new Model(null);
	    	}
	    });
	}
	
	/**
	 * setNewGameType
	 * 
	 * if the Radford Version was just played by user, win panel will show 
	 * "play text version" JButton and vice versa
	 */
	public void setNewGameType()
	{
		jb2.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		if(gameType == 1)
	    		{
	    			fWin.dispose();
	    			new Model(null);
	    		}
	    		else if(gameType == 2)
	    		{
	    			fWin.dispose();
	    			new Model2(null);
	    		}
	    	}
	    });
	}
}



