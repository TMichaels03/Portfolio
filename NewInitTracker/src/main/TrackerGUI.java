package main;

import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings({"serial", "unused"})
public class TrackerGUI extends JFrame implements ActionListener
{
	// Buttons
	private JButton bAdd, bDel, bClear;
	private JButton bEncNext;
	private JButton bAddHP, bSubHP, bSetHP;
	private JButton buttonLoadEnc;
	
	// Labels
	private JLabel labelList;
	private JLabel labelCurrentRound, labelTime, labelPTurn, labelCTurn, labelNTurn;
	private JLabel labelHP;
	
	// ComboBox
	private JComboBox<String> boxDelList;
	private JComboBox<String> boxNPCList;
	
	// Text Area
	JTextArea textList;
	
	// Text Field
	JTextField textHP;
	
	// Font
	Font f = new Font("ARIAL", Font.PLAIN, 20);
	Font text = new Font("ARIAL", Font.PLAIN, 17);
	Font fRound = new Font("ARIAL", Font.PLAIN, 23);
	Font fMain = f;
	Font fSmall = new Font("ARIAL", Font.PLAIN, 14);
	Font fCTurn = new Font("ARIAL", Font.PLAIN, 20);
	
	// JFrame
	JFrame t;
	
	// Initiative List
	DList<Creature> initList;
	
	// Misc Variables
	int turn;
	int time;
	int round;
	
	public TrackerGUI()
	{
		turn = 0;
		round = 0;
		time = 0;
		
		initList = new DList<Creature>();
		
		t = new JFrame("D&D Tracker");
		t.setSize(1300, 600);
		t.setLayout(null);
		
		////////
		
//		Initiative List
		
		////////
		
		// Labels
		
		labelList = new JLabel("Intiative List");
		labelList.setFont(text);
		labelList.setBounds(180+100, 65, 100, 50);
		
		// Text Area
		
		textList = new JTextArea();
		textList.setFont(text);
		textList.setEditable(false);
		textList.setBounds(175+50, 100, 200, 400);
		
		// Buttons
		
		bAdd = new JButton("Add");
		bAdd.setFont(f);
		bAdd.addActionListener(this);
		bAdd.setActionCommand("add");
		bAdd.setBounds(40, 110, 120+50, 60);
		
		bDel = new JButton("Delete");
		bDel.setFont(f);
		bDel.addActionListener(this);
		bDel.setActionCommand("del");
		bDel.setBounds(40, 275, 120+50, 60);
		
		bClear = new JButton("Clear / End");
		bClear.setFont(f);
		bClear.addActionListener(this);
		bClear.setActionCommand("clear");
		bClear.setBounds(40, 400, 120+50, 60);
		
		buttonLoadEnc = new JButton("Load Encounter");
		buttonLoadEnc.setFont(new Font("ARIAL", Font.PLAIN, 15));
		buttonLoadEnc.addActionListener(this);
		buttonLoadEnc.setActionCommand("loadEnc");
		buttonLoadEnc.setBounds(40, 190, 120+50, 60);
		
		// Combo Box
		
		boxDelList = new JComboBox<String>();
		boxDelList.setBounds(40, 340, 110+60, 35);
		boxDelList.setFont(text);
		
		// Add
		t.add(bAdd);
		t.add(bDel);
		t.add(bClear);
		t.add(buttonLoadEnc);
		
		t.add(boxDelList);
		
		t.add(labelList);
		t.add(textList);
		
		
		////////

//		Turn and Round Tracking
		
		///////
		
		// Labels
		
		labelCurrentRound = new JLabel("Current Round:");
		labelCurrentRound.setFont(fRound);
		labelCurrentRound.setBounds(500+60, 50, 200, 25);
		
		labelTime = new JLabel("Time Elapsed:");
		labelTime.setFont(fMain);
		labelTime.setBounds(490+60, 80+10, 200, 25);
		
		labelPTurn = new JLabel("Previous Turn: -----------");
		labelPTurn.setFont(fSmall);
		labelPTurn.setBounds(500+60, 200-15, 300, 25);
		
		labelCTurn = new JLabel("Current Turn: -----------");
		labelCTurn.setFont(fCTurn);
		labelCTurn.setBounds(500+60, 275-15, 300, 25);
		
		labelNTurn = new JLabel("Next Turn: -------------");
		labelNTurn.setFont(fSmall);
		labelNTurn.setBounds(515+60, 350-15, 300, 25);
		
		// Button
		
		bEncNext = new JButton("Start Encounter");
		bEncNext.setFont(f);
		bEncNext.addActionListener(this);
		bEncNext.setActionCommand("next");
		bEncNext.setBounds(510+55, 450-20, 200, 75);
		
		// Add
		t.add(labelCurrentRound);
		t.add(labelTime);
		
		t.add(labelPTurn);	
		t.add(labelCTurn);
		t.add(labelNTurn);
		
		t.add(bEncNext);
		
		////////
		
//		NPC HP Tracking
		
		////////
		
		// Combo Box Action Listener
		ActionListener NPCAction = new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						try
						{
							String test = "";
							String other = boxNPCList.getSelectedItem().toString().trim();
							int pos = -1;
							for(int i = 0; i < boxNPCList.getSelectedItem().toString().trim().length(); i++)
							{
								try
								{
									int nn = Integer.parseInt(other.substring(0, 1));
									test += other.substring(0, 1);
									other = other.substring(1);
								}
								catch(Exception e)
								{
									pos = Integer.parseInt(test);
									break;
								}
							}
							String maxHitPoints = Integer.toString(initList.get(pos).getMaxHitPoints());
							String minHitPoints = Integer.toString(initList.get(pos).getCurrentHitPoints());
							labelHP.setText(minHitPoints + " / " + maxHitPoints);
						}
						catch(NullPointerException e)
						{
							System.out.printf("Refreshing NPC list...%n");
						}
						catch(Exception e)
						{
							System.out.printf("Error within NPCAction%n");
						}
					}
				};
		
		// Combo Box
				
		boxNPCList = new JComboBox<String>();
		boxNPCList.setFont(f);
		boxNPCList.setBounds(920-5, 100, 150+25, 40);
		boxNPCList.addActionListener(NPCAction);
		
		// Label
		
		labelHP = new JLabel("--- / ---");
		labelHP.setFont(new Font("ARIAL", Font.PLAIN, 30));
		labelHP.setBounds(950, 225, 300, 25);
		
		// Text Field
		
		textHP = new JTextField();
		textHP.setFont(f);
		textHP.setBounds(925, 350, 150, 30);
		
		// Buttons
		
		bAddHP = new JButton("+");
		bAddHP.setFont(fRound);
		bAddHP.addActionListener(this);
		bAddHP.setActionCommand("addHP");
		bAddHP.setBounds(925, 385, 70, 30);
		
		bSubHP = new JButton("-");
		bSubHP.setFont(fRound);
		bSubHP.addActionListener(this);
		bSubHP.setActionCommand("subHP");
		bSubHP.setBounds(1005, 385, 70, 30);
		
		bSetHP = new JButton("Set Max HP");
		bSetHP.setFont(f);
		bSetHP.addActionListener(this);
		bSetHP.setActionCommand("setHP");
		bSetHP.setBounds(925, 425, 150, 30);
		
		// Add
		t.add(boxNPCList);
		
		t.add(labelHP);
		
		t.add(textHP);
		
		t.add(bAddHP);
		t.add(bSubHP);
		t.add(bSetHP);
		
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String ae = arg0.getActionCommand();
		
		if(ae.equals(bAdd.getActionCommand()))
		{
			new AddCreatureGUI(this);
		}
		else if(ae.equals(bDel.getActionCommand()))
		{
			try
			{
				int pos = getPos(boxDelList);
				
				initList.remove(pos);
				writeLists();
				
				// Update turn
				if(turn >= pos)
				{
					turn--;
				}
			}
			catch(Exception e)
			{
				System.out.printf("Error Origin: Delete%n");
			}
		}
		else if(ae.equals(bClear.getActionCommand()))
		{
			initList = new DList<Creature>();
			turn = 0;
			time = 0;
			round = 0;
			labelCurrentRound.setText("Current Round:");
			labelTime.setText("Time Elapsed:");
			labelPTurn.setText("Previous Turn: -----------");
			labelCTurn.setText("Current Turn: -----------");
			labelNTurn.setText("Next Turn: -------------");
			labelHP.setText("--- / ---");
			bEncNext.setText("Start Encounter");
			writeLists();
		}
		else if(ae.equals(bEncNext.getActionCommand()) && bEncNext.getText().trim().equals("Start Encounter"))
		{
			turn = 0;
			bEncNext.setText("Next Turn");
			labelCTurn.setText("Current Turn: " + initList.get(turn).getName());
			labelNTurn.setText("Next Turn: " + initList.get(turn + 1).getName());
			round = 1;
			labelCurrentRound.setText("Current Round: " + Integer.toString(round));
			labelTime.setText("Time Elapsed: " + Integer.toString(time) + "s");
		}
		else if(ae.equals(bEncNext.getActionCommand()) && bEncNext.getText().trim().equals("Next Turn"))
		{									
			if(turn == initList.size() - 1)
			{
				turn = 0;
				round++;
				time += 6;
			}
			else
			{
				turn++;
			}
			
			if(turn == 0)
			{
				labelPTurn.setText("Previous Turn: " + initList.get(initList.size() - 1).getName());
			}
			else
			{
				labelPTurn.setText("Previous Turn: " + initList.get(turn - 1).getName());
			}
			
			if(turn + 1 == initList.size())
			{
				labelNTurn.setText("Next Turn: " + initList.get(0).getName());
			}
			else
			{
				labelNTurn.setText("Next Turn: " + initList.get(turn + 1).getName());
			}
			
			labelCurrentRound.setText("Current Round: " + Integer.toString(round));
			labelCTurn.setText("Current Turn: " + initList.get(turn).getName());
			labelTime.setText("Time Elapsed: " + minutes(time));		
		}
		else if(ae.equals(bAddHP.getActionCommand()))
		{
			int pos = getPos(boxNPCList);
			
			Creature c = initList.get(pos);
			
			int add = 0;
			try
			{
				add = Integer.parseInt(textHP.getText().trim());
			}
			catch(Exception e)
			{
				System.out.printf("Error: HP to add not numerical value.%n");
			}
			
			int chp = c.getCurrentHitPoints();
			if(chp + add > c.getMaxHitPoints())
			{
				c.setCurrentHitPoints(c.getMaxHitPoints());
			}
			else
			{
				c.setCurrentHitPoints(c.getCurrentHitPoints() + add);
			}
			
			textHP.setText("");
			labelHP.setText(c.getCurrentHitPoints() + " / " + c.getMaxHitPoints());
		}
		else if(ae.equals(bSubHP.getActionCommand()))
		{
			int pos = getPos(boxNPCList);
			
			Creature c = initList.get(pos);
			
			int sub = 0;
			try
			{
				sub = Integer.parseInt(textHP.getText().trim());
			}
			catch(Exception e)
			{
				System.out.printf("Error: HP to subtract not numerical value.%n");
			}
			
			int chp = c.getCurrentHitPoints();
			if(chp - sub < 0)
			{
				c.setCurrentHitPoints(0);
			}
			else
			{
				c.setCurrentHitPoints(c.getCurrentHitPoints() - sub);
			}
			
			textHP.setText("");
			labelHP.setText(c.getCurrentHitPoints() + " / " + c.getMaxHitPoints());
		}
		else if(ae.equals(bSetHP.getActionCommand()))
		{
			int pos  = getPos(boxNPCList);
			Creature c = initList.get(pos);
			int max = c.getMaxHitPoints();
			
			try
			{
				max = Integer.parseInt(textHP.getText().trim());
			}
			catch(Exception e)
			{
				System.out.printf("Error: New Maximum Hit Points not a numerical value.%n");
			}
			
			c.setMaxHitPoints(max);
			
			if(c.getCurrentHitPoints() > c.getMaxHitPoints())
			{
				c.setCurrentHitPoints(c.getMaxHitPoints());
			}
			
			textHP.setText("");
			labelHP.setText(c.getCurrentHitPoints() + " / " + c.getMaxHitPoints());
		}
		// Load Encounter
		else if(ae.equals(buttonLoadEnc.getActionCommand()))
		{
			File file = null;
			
			String path = System.getProperty("user.dir") + "\\Encounters\\";
			
			File ff = new File(path);
			
			if(!ff.exists())
			{
				try
				{
					ff.mkdir();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}		
			
			JFileChooser fc = new JFileChooser(path);
			try
			{
				int retVal = fc.showOpenDialog(null);
				if(retVal == JFileChooser.APPROVE_OPTION)
				{
					file = fc.getSelectedFile();
					String line = "";
					BufferedReader in = new BufferedReader(new FileReader(file));
					Random rnd = new Random(System.currentTimeMillis());
					
					while((line = in.readLine()) != null)
					{
						String[] fields = line.split(":");
						String name = fields[0].trim();
						int initMod = Integer.parseInt(fields[1].trim());
						int maxHp = Integer.parseInt(fields[2].trim());
						Creature c = new Creature(name, maxHp, initMod);
						c.setInit((rnd.nextInt(19) + 1) + c.getInitMod());
						this.addCreature(c);
					}
					in.close();
					this.writeLists();
				}				
			}
			catch(Exception e)
			{}
		}
	}
	
	public void addCreature(Creature cr)
	{
		initList.add(cr);
		initList.sort(Creature.BY_INIT);
		
		textList.setText("");
		boxDelList.removeAllItems();
		
		writeLists();
	}
	
	private void writeLists()
	{
		boxDelList.removeAllItems();
		boxNPCList.removeAllItems();
		textList.setText("");
		String n = "";
		for(int i = 0; i < initList.size(); i++)
		{
			n = Integer.toString(i);
			textList.append(n + " : [" + Integer.toString(initList.get(i).getInit()) + "] " + initList.get(i).getName() + "\n");
			boxDelList.addItem(n + " : " + initList.get(i).getName());
			if(initList.get(i).getType().equals("NPC"))
			{
				boxNPCList.addItem(n + " : " + initList.get(i).getName());
			}
		}
	}
	
	private String minutes(int seconds)
	{
		if(seconds < 60)
		{
			return (Integer.toString(time) + "s");
		}
		else
		{
			int min = 0;
			while(seconds >= 60)
			{
				seconds -= 60;
				min++;
			}
			
			return (Integer.toString(min) + "m " + Integer.toString(seconds) + "s");
		}
	}
	
	private int getPos(JComboBox<String> cb)
	{
		String test = "";
		String other = cb.getSelectedItem().toString().trim();
		int pos = -1;
		for(int i = 0; i < cb.getSelectedItem().toString().trim().length(); i++)
		{
			try
			{
				int nn = Integer.parseInt(other.substring(0, 1));
				test += other.substring(0, 1);
				other = other.substring(1);
			}
			catch(Exception e)
			{
				pos = Integer.parseInt(test);
				break;
			}
		}
		return pos;
	}
}
