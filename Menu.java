/**
 * @author: Nick Bauer
 * @version: Menu
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

/**
 * 
 * The Menu Class Creates a "Main Menu" JFrame that allows user input via JButtons
 * 
 */
public class Menu
{
	private JFrame menu = new JFrame("Welcome to the Crush Game!");
	
	private JButton jb = new JButton("<html><div style = 'text-align: center;'>Play Text<br/>Edition<html>");
	private JButton jb2 = new JButton("<html><div style = 'text-align: center;'>Play Radford<br/>Edition<html>");
	private JButton jb3 = new JButton("Quit");
	private JPanel p = new JPanel();
	private JLabel crush = new JLabel("<html><div style = 'text-align: center;'>CRUSH GAME<br/>");
	private JLabel label = new JLabel("<html><div style = 'text-align: center;'><br/>Instructions:<br/> Find any three matching buttons<br/>in a row (horizontal or vertical).<br/>Click the center button to delete<br/>those buttons causing the<br>other buttons to fall.<br/><br/>How to Win:<br/>When there are no more moves<br/>possible, you have won the game!<br/><br/><html>");
	private JLabel author = new JLabel("<html><div style = 'text-align: right;'>Developer: Nick Bauer<html>");
	
	private final int XLENGTH = 500;
	private final int YLENGTH = 550;
	private final int TITLESIZE = 40;
	private final int BUTTONTEXTSIZE = 25;
	private final int TEXTSIZE = 20;
	private final int AUTHORSIZE = 12;
	private final int BORDERSIZE = 7;
	private final int TITLEBORDERSIZE = 10;

	public Menu()
	{
		menu.setPreferredSize(new Dimension(XLENGTH,YLENGTH));
		p.setBackground(Color.BLACK);
		p.setOpaque(true);
		formatLabels();
        formatButtons();
		addPanelElements();
		Border white = BorderFactory.createLineBorder(Color.white, BORDERSIZE);
		Border blue = BorderFactory.createLineBorder(Color.blue, TITLEBORDERSIZE);
		p.setBorder(white);
		crush.setBorder(blue);
		menu.add(p);
		setMenuAction();
		menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		menu.pack();
		menu.setVisible(true);
	}
	
	/**
	 * formatLabels
	 * 
	 * adds font style to JLabels
	 */
	public void formatLabels()
	{
		crush.setFont(new Font("Verdana", Font.BOLD, TITLESIZE));
        crush.setForeground(Color.RED);
        label.setFont(new Font("Verdana", Font.BOLD, TEXTSIZE));
        label.setForeground(Color.WHITE);
        author.setFont(new Font("Verdana", Font.PLAIN, AUTHORSIZE));
		author.setForeground(Color.WHITE);
	}
	
	/**
	 * addPanelElements
	 * 
	 * adding all appropriate elements to the JPanel
	 */
	public void addPanelElements()
	{
		p.add(crush);
        p.add(label);
        p.add(jb);
		p.add(jb2);
		p.add(jb3);
		p.add(author);
	}
	
	/**
	 * setButtonFont
	 * 
	 * setting font size and text style
	 */
	public void formatButtons()
	{
		jb.setFont(new Font("Impact", Font.BOLD, BUTTONTEXTSIZE));
		jb.setForeground(Color.BLUE);
		jb2.setFont(new Font("Impact", Font.BOLD, BUTTONTEXTSIZE));
		jb2.setForeground(Color.RED);
		jb3.setFont(new Font("Impact", Font.BOLD, BUTTONTEXTSIZE));
	}
	
	/**
	 * setWinAction
	 * 
	 * assigning actionListeners to Menu's JButtons to perform desired tasks
	 */
	public void setMenuAction()
	{
		jb.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		menu.dispose();
	    		new Model2(null);
	    	}
	    });
    	jb2.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		menu.dispose();
	    		new Model(null);
	    	}
	    });
    	jb3.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		menu.dispose();
	    	}
	    });
	}
}
