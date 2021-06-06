package main;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class AddCreatureGUI extends JFrame implements ActionListener
{
	// Frame
	
	// Buttons
	private JButton addPC, addNPC, clear;
	
	// Labels
	private JLabel labelName, labelInit, labelMaxHP;
	
	// Text Fields
	private JTextField textName, textInit, textMaxHP;
	
	// JFrame
	private JFrame c;
	
	
	private TrackerGUI t;
	
	// Font
	Font f = new Font("ARIAL", Font.PLAIN, 17);
	
	public AddCreatureGUI(TrackerGUI t)
	{
		this.t = t;
		
		c = new JFrame("Add Creature");
		c.setSize(500, 350);
		c.setLayout(null);
		
		
		// Buttons
		
		int bLen = 120;
		int bHeight = 50;
		int bX = 40;
		int bY = 250;
		
		addPC = new JButton("Add PC");
		addPC.setBounds(bX, bY, bLen, bHeight);
		addPC.setFont(f);
		addPC.addActionListener(this);
		addPC.setActionCommand("AddPC");
		
		addNPC = new JButton("Add NPC");
		addNPC.setBounds(bX + bLen + 5, bY, bLen, bHeight);
		addNPC.setFont(f);
		addNPC.addActionListener(this);
		addNPC.setActionCommand("AddNPC");
		
		clear = new JButton("Clear");
		clear.setBounds(bX + (bLen * 2) + 10, bY, bLen + 25, bHeight);
		clear.setFont(f);
		clear.addActionListener(this);
		clear.setActionCommand("clear");
		
		// Text Fields
		
		int tX = 180;
		int tY = 60;
		int tWidth = 220;
		int tHeight = 40;
		
		textName = new JTextField();
		textName.setFont(f);
		textName.setBounds(tX, tY, tWidth, tHeight);
		
		textInit = new JTextField();
		textInit.setFont(f);
		textInit.setBounds(tX, tY + tHeight + 10, tWidth, tHeight);
		
		textMaxHP = new JTextField();
		textMaxHP.setFont(f);
		textMaxHP.setBounds(tX, tY + (tHeight * 2) + 20, tWidth, tHeight);
		
		// Labels
		
		int lX = 95;
		int lY = 30;
		int lWidth = 100;
		int lHeight = 100;
		
		labelName = new JLabel("Name:");
		labelName.setFont(f);
		labelName.setBounds(lX + 15, lY, lWidth, lHeight);
		
		labelInit = new JLabel("Initiative:");
		labelInit.setFont(f);
		labelInit.setBounds(lX, lY + 50, lWidth, lHeight);
		
		labelMaxHP = new JLabel("Max HP:");
		labelMaxHP.setFont(f);
		labelMaxHP.setBounds(lX + 2, lY + 100, lWidth, lHeight);
		
		// Add
		
		c.add(addPC);
		c.add(addNPC);
		c.add(clear);
		
		c.add(textName);
		c.add(textInit);
		c.add(textMaxHP);
		
		c.add(labelName);
		c.add(labelInit);
		c.add(labelMaxHP);
		
		c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		c.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String ae = arg0.getActionCommand();
		
		if(ae.equals(addPC.getActionCommand()))
		{
			try
			{
				String name = textName.getText().trim();
				int init = Integer.parseInt(textInit.getText().trim());
				
				if(!(name.equals("") && textInit.getText().equals("")))
				{
					Creature PC = new Creature(name, init, "PC");
					t.addCreature(PC);
					
					textName.setText("");
					textInit.setText("");
					textMaxHP.setText("");
					
					System.out.printf("Added PC: [%d] %s%n", init, name);
				}
			}
			catch(Exception e)
			{}
		}
		else if(ae.equals(addNPC.getActionCommand()))
		{
			try
			{
				String name = textName.getText().trim();
				int init = Integer.parseInt(textInit.getText().trim());
				int maxHP = Integer.parseInt(textMaxHP.getText().trim());
				
				if(!(name.equals("") && textInit.getText().equals("")))
				{
					Creature NPC = new Creature(name, init, maxHP, "NPC");
					t.addCreature(NPC);
				
					textName.setText("");
					textInit.setText("");
					textMaxHP.setText("");
					
					System.out.printf("Added NPC: [%d] <%d / %d> %s%n", init, maxHP, maxHP, name);
				}
			}
			catch(Exception e)
			{}
		}
		else if(ae.equals(clear.getActionCommand()))
		{
			textName.setText("");
			textInit.setText("");
			textMaxHP.setText("");
		}
	}
	
}
