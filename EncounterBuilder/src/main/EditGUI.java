package main;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class EditGUI extends JFrame implements ActionListener, WindowListener
{
	private Creature c;

	// Buttons
	JButton buttonEdit;
	
	// Labels
	JLabel labelName, labelInitMod, labelHp;
	
	// Text Boxes
	JTextField textName, textInitMod, textHp;
	
	// Text Area
	JTextArea textStats;
	
	// JFrame
	JFrame t;
	
	// Font
	Font f = new Font("ARIAL", Font.PLAIN, 15);
	
	// EncounterGUI
	EncounterGUI en;
	
	public EditGUI(Creature c, EncounterGUI en)
	{	
		this.en = en;
		this.c = c;
		t = new JFrame("Encounter Builder: Creature Edit");
		t.addWindowListener(this);
		t.setLayout(null);
		t.setSize(650, 300);
		
		// Text Area
		textStats = new JTextArea();
		textStats.setEditable(false);
		textStats.setFont(f);
		textStats.setBounds(25, 25, 250, 200);
		
		// Labels
		labelName = new JLabel("Name:");
		labelName.setFont(f);
		labelName.setBounds(300, 25, 100, 25);
		
		labelInitMod = new JLabel("Initiative Modifier:");
		labelInitMod.setFont(f);
		labelInitMod.setBounds(300, 100, 125, 25);
		
		labelHp = new JLabel("Max Hit Points:");
		labelHp.setFont(f);
		labelHp.setBounds(300, 175, 150, 25);
		
		
		// Text Boxes
		textName = new JTextField();
		textName.setFont(f);
		textName.setBounds(300, 50, 100, 25);
		
		textInitMod = new JTextField();
		textInitMod.setFont(f);
		textInitMod.setBounds(300, 125, 100, 25);
		
		textHp = new JTextField();
		textHp.setFont(f);
		textHp.setBounds(300, 200, 100, 25);
		
		// Button
		buttonEdit = new JButton("Confirm Edit");
		buttonEdit.setFont(f);
		buttonEdit.addActionListener(this);
		buttonEdit.setActionCommand("edit");
		buttonEdit.setBounds(440, 105, 170, 50);
		
		// Add
		t.add(textStats);
		
		t.add(labelName);
		t.add(labelInitMod);
		t.add(labelHp);
		
		t.add(textName);
		t.add(textInitMod);
		t.add(textHp);
		
		t.add(buttonEdit);
		
		//
		
		// Add input creature text to stat block
		textStats.setText("Name: " + c.getName() + "\nInitiative Mod: " + c.getInitMod() + "\nMax Hit Points: " + c.getMaxHitPoints());
		
		//
		
		t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		t.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String ae = arg0.getActionCommand();
		
		if(ae.equals("edit"))
		{
			String name = "";
			int initMod = 0;
			int maxHp = 0;
			
			if(textName.getText().equals(""))
			{
				name = c.getName();
			}
			else
			{
				name = textName.getText().trim();
			}
			
			if(textInitMod.getText().equals(""))
			{
				initMod = c.getInitMod();
			}
			else
			{
				initMod = Integer.parseInt(textInitMod.getText().trim());
			}
			
			if(textHp.getText().equals(""))
			{
				maxHp = c.getMaxHitPoints();
			}
			else
			{
				maxHp = Integer.parseInt(textHp.getText().trim());
			}
			
			c = new Creature(name, maxHp, initMod);
			
			textStats.setText("Name: " + c.getName() + "\nInitiative Mod: " + c.getInitMod() + "\nMax Hit Points: " + c.getMaxHitPoints());
			
			textName.setText("");
			textInitMod.setText("");
			textHp.setText("");
			

			t.setVisible(false);
			t.dispose();
		}
	}
	
	private void sendEdit()
	{
			en.addCreature(c);
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		this.sendEdit();
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{}

	@Override
	public void windowIconified(WindowEvent arg0)
	{}

	@Override
	public void windowOpened(WindowEvent arg0)
	{}
	
}
