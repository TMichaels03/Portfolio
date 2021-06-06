package main;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.io.*;

@SuppressWarnings({"serial", "unused"})
public class EncounterGUI extends JFrame implements ActionListener, InputMethodListener
{
		// Buttons
		JButton buttonLoadFile, buttonLoadEnc, buttonDel, buttonSave, buttonAdd, buttonEdit;
		JButton buttonLoadEncounter;
		JButton buttonRefresh;
			
		// Combo Box
		JComboBox<String> boxMList;
		JComboBox<String> boxEditList;
		
		// TextBox
		JTextField textQuantity;
		JTextField searchBar;
		
		// Label
		JLabel labelQuantity;
		
		// Text Area
		JTextArea textEncList, textStats;
		JTextArea textStatBlock;
		
		// JFrame
		JFrame t;
		
		// Font
		Font f = new Font("ARIAL", Font.PLAIN, 15);
		
		// Lists
		DList<Creature> monsterList;
		DList<Creature> encounterList;
		
		
		// Listeners
		KeyListener keySearch = new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e)
			{}

			@Override
			public void keyReleased(KeyEvent e)
			{
				String toSearch = searchBar.getText().trim();
				//System.out.printf("Searching: %s%n", toSearch);
				
				if(toSearch.equals(""))
				{
					boxMList.removeAllItems();
					for(int i = 0; i < monsterList.size(); i++)
					{
						boxMList.addItem(monsterList.get(i).getName().trim());
					}
				}
				else
				{
					boxMList.removeAllItems();
					for(int i = 0; i < monsterList.size(); i++)
					{
						if(monsterList.get(i).getName().trim().toUpperCase().contains(toSearch.toUpperCase().trim()))
						{
							boxMList.addItem(monsterList.get(i).getName());
						}
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e)
			{}
		};
		
		ActionListener actionMList = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					Creature c = null;
					for(int i = 0; i < monsterList.size(); i++)
					{
						if(boxMList.getSelectedItem().toString().trim().equals(monsterList.get(i).getName().trim()))
						{
							c = monsterList.get(i);
							break;
						}
					}
					textStats.setText("Name: " + c.getName() + "\nInitiative Mod: " + c.getInitMod() + "\nMax Hit Points: " + c.getMaxHitPoints());
				}
				catch(Exception e)
				{}
			}		
		};
		
		ActionListener actionEditList = new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							String cr = boxEditList.getSelectedItem().toString().trim();
							
							
							Creature c = null;
							for(int i = 0; i < encounterList.size(); i++)
							{
								if(encounterList.get(i).getName().trim().equals(cr))
								{
									c = encounterList.get(i);
									textStatBlock.setText("");
									textStatBlock.setText("Name: " + c.getName() + "\nInitiative Mod: " + c.getInitMod() + "\nMax Hit Points: " + c.getMaxHitPoints()); 
									break;
								}
							}
						}
						catch(Exception ee)
						{
							textStatBlock.setText("");
						}
					}		
				};
		
		
		public EncounterGUI()
		{
			monsterList = new DList<Creature>();
			encounterList = new DList<Creature>();
			
			t = new JFrame("Encounter Builder");
			t.setLayout(null);
			t.setSize(600, 605);
			
			// Buttons
			buttonLoadFile = new JButton("Load Monster File");
			buttonLoadFile.setFont(f);
			buttonLoadFile.addActionListener(this);
			buttonLoadFile.setActionCommand("loadMFile");
			buttonLoadFile.setBounds(25, 25, 150, 50);
			
			buttonAdd = new JButton("Add");
			buttonAdd.setFont(f);
			buttonAdd.addActionListener(this);
			buttonAdd.setActionCommand("add");
			buttonAdd.setBounds(45, 390, 100, 50);
			
			buttonEdit = new JButton("Edit");
			buttonEdit.setFont(f);
			buttonEdit.addActionListener(this);
			buttonEdit.setActionCommand("edit");
			buttonEdit.setBounds(445, 260, 100, 50);
			
			buttonDel = new JButton("Delete");
			buttonDel.setFont(f);
			buttonDel.addActionListener(this);
			buttonDel.setActionCommand("delete");
			buttonDel.setBounds(445, 360, 100, 50);
			
			buttonSave = new JButton("Save Encounter");
			buttonSave.setFont(f);
			buttonSave.addActionListener(this);
			buttonSave.setActionCommand("save");
			buttonSave.setBounds(140, 500, 150, 50);
			
			buttonLoadEncounter = new JButton("Load Encounter");
			buttonLoadEncounter.setFont(f);
			buttonLoadEncounter.addActionListener(this);
			buttonLoadEncounter.setActionCommand("loadEnc");
			buttonLoadEncounter.setBounds(315, 500, 150, 50);
			
			buttonRefresh = new JButton("Refresh List");
			buttonRefresh.setFont(f);
			buttonRefresh.addActionListener(this);
			buttonRefresh.setActionCommand("refresh");
			buttonRefresh.setBounds(200, 410, 200, 50);
			
			// Label
			labelQuantity = new JLabel("Quantity:");
			labelQuantity.setFont(f);
			labelQuantity.setBounds(65, 270, 100, 100);
			
			// Text Boxes
			textQuantity = new JTextField();
			textQuantity.setFont(f);
			textQuantity.setHorizontalAlignment(JTextField.CENTER);
			textQuantity.setBounds(55, 330, 80, 35);
			
			
			searchBar = new JTextField();
			searchBar.setFont(f);
			searchBar.addKeyListener(keySearch);
			searchBar.setBounds(25, 90, 150, 30);
			
			// Text Areas
			textStats = new JTextArea();
			textStats.setFont(f);
			textStats.setEditable(false);
			textStats.setBounds(20, 175, 160, 125);
			
			textEncList = new JTextArea();
			textEncList.setFont(f);
			textEncList.setEditable(false);
			textEncList.setBounds(200, 30, 200, 375);
			
			textStatBlock = new JTextArea();
			textStatBlock.setFont(f);
			textStatBlock.setEditable(false);
			textStatBlock.setBounds(410, 80, 165, 150);
					
			// Combo Boxes
			boxMList = new JComboBox<String>();
			boxMList.setFont(f);
			boxMList.addActionListener(actionMList);
			boxMList.setBounds(25, 130, 150, 35);
			
			boxEditList = new JComboBox<String>();
			boxEditList.setFont(f);
			boxEditList.addActionListener(actionEditList);
			boxEditList.setBounds(425, 30, 150, 35);
			
			// Add
			t.add(buttonLoadFile);
			t.add(buttonAdd);
			t.add(buttonEdit);
			t.add(buttonDel);
			t.add(buttonSave);
			t.add(buttonLoadEncounter);
			t.add(buttonRefresh);
			
			t.add(labelQuantity);
			
			t.add(textQuantity);
			t.add(searchBar);
			
			t.add(textStats);
			t.add(textEncList);
			t.add(textStatBlock);
			
			t.add(boxMList);
			t.add(boxEditList);
			
			t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			t.setVisible(true);
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String ae = arg0.getActionCommand();
		
		// Load Monster List
		if(ae.equals(buttonLoadFile.getActionCommand()))
		{
			File file = null;
			
			String path = System.getProperty("user.dir") + "\\Monster Lists\\";
			
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
					monsterList.clear();
					while((line = in.readLine()) != null)
					{
						String[] fields = line.split(":");
						String name = fields[0].trim();
						int initMod = Integer.parseInt(fields[1].trim());
						int maxHp = Integer.parseInt(fields[2].trim());
						Creature c = new Creature(name, maxHp, initMod);
						monsterList.add(c);
						monsterList.sort(Creature.BY_NAME);
					}
					in.close();
				}				
			}
			catch(Exception e)
			{}
			
			boxMList.removeAllItems();
			for(int i = 0; i < monsterList.size(); i++)
			{
				boxMList.addItem(monsterList.get(i).getName().trim());
			}
		}
		// Add To Encounter List
		else if(ae.equals(buttonAdd.getActionCommand()))
		{
			int quantity = 1;
			try
			{
				quantity = Integer.parseInt(textQuantity.getText().trim());
				textQuantity.setText("");
			}
			catch(Exception e)
			{}
			
			Creature c = null;
			for(int i = 0; i < monsterList.size(); i++)
			{
				if(boxMList.getSelectedItem().toString().trim().equals(monsterList.get(i).getName().trim()))
				{
					c = monsterList.get(i);
					break;
				}
			}
			
			int n = 1;
			String genName = c.getName() + " " + Integer.toString(n);
			
			for(int i = 0; i < quantity; i++)
			{
				if(encounterList.size() == 0)
				{
					this.addCreature(new Creature(genName, c.getMaxHitPoints(), c.getInitMod()));
				}
				else
				{
					this.addCreature(new Creature(this.leveledName(n, c), c.getMaxHitPoints(), c.getInitMod()));
				}
			}
		}
		// Edit
		else if(ae.equals(buttonEdit.getActionCommand()))
		{
			Creature c = null;
			
			String toEdit = boxEditList.getSelectedItem().toString().trim();
			for(int i = 0; i < encounterList.size(); i++)
			{
				if(toEdit.equals(encounterList.get(i).getName()))
				{
					c = encounterList.get(i);
					encounterList.remove(i);
					textEncList.setText("");
					boxEditList.removeAllItems();
					for(int p = 0; p < encounterList.size(); p++)
					{
						textEncList.append(encounterList.get(p).getName() + "\n");
						boxEditList.addItem(encounterList.get(p).getName() + "\n");
					}
					break;
				}
			}
			new EditGUI(c, this);
		}
		// Delete
		else if(ae.equals(buttonDel.getActionCommand()))
		{
			String test = boxEditList.getSelectedItem().toString().trim();
			for(int i = 0; i < encounterList.size(); i++)
			{
				if(test.equals(encounterList.get(i).getName().trim()))
				{
					encounterList.remove(i);
					textEncList.setText("");
					boxEditList.removeAllItems();
					for(int p = 0; p < encounterList.size(); p++)
					{
						textEncList.append(encounterList.get(p).getName() + "\n");
						boxEditList.addItem(encounterList.get(p).getName() + "\n");
					}
					break;
				}
			}
		}
		// Save Encounter
		else if(ae.equals(buttonSave.getActionCommand()))
		{
			this.refresh();
			
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
				{}
			}
			
			JFileChooser fc = new JFileChooser(path);
			
			
			
			int option = fc.showSaveDialog(this);
			fc.setDialogTitle("Save Encounter File");
			
			if(option == JFileChooser.APPROVE_OPTION)
			{
				file = fc.getSelectedFile();
				
				// Set extension
				if(!this.getFileExtension(file).equals(".en"))
				{
					file = new File(fc.getSelectedFile() + ".en");
				}
				
				System.out.printf("Saving file: %s%n", file.getAbsolutePath());
				
				try
				{
					file.createNewFile();
					BufferedWriter out = new BufferedWriter(new FileWriter(file));
					
					String toWrite = "";
					for(int i = 0; i < encounterList.size(); i++)
					{
						Creature c = encounterList.get(i);
						toWrite += c.getName() + ":" + c.getInitMod() + ":" + c.getMaxHitPoints() + "\n";
					}
					out.write(toWrite);
					out.close();
				}
				catch(Exception e)
				{
					System.out.printf("Error: Could not write to file.%n");
				}
			}
		}
		// Load Encounter
		else if(ae.equals(buttonLoadEncounter.getActionCommand()))
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
					encounterList.clear();
					
					while((line = in.readLine()) != null)
					{
						String[] fields = line.split(":");
						String name = fields[0].trim();
						int initMod = Integer.parseInt(fields[1].trim());
						int maxHp = Integer.parseInt(fields[2].trim());
						Creature c = new Creature(name, maxHp, initMod);
						this.addCreature(c);
					}
					in.close();
					this.refresh();
				}				
			}
			catch(Exception e)
			{}
		}
		// Refresh List
		else if(ae.equals(buttonRefresh.getActionCommand()))
		{
			this.refresh();
		}
	}
	
	public void addCreature(Creature c)
	{
		encounterList.add(c);
		encounterList.sort(Creature.BY_NAME);
		
		textEncList.setText("");
		boxEditList.removeAllItems();
		for(int i = 0; i < encounterList.size(); i++)
		{
			textEncList.append(encounterList.get(i).getName() + "\n");
			boxEditList.addItem(encounterList.get(i).getName());
		}
	}
	
	private int numberWithoutName(int pos)
	{
		String test = encounterList.get(pos).getName().trim();
		String num = "";
		for(int i = test.length() - 1; i > 0 ;i--)
		{
			try
			{
				int nn = Integer.parseInt(Character.toString(test.charAt(i)));
				num += Character.toString(test.charAt(i));
			}
			catch(Exception e)
			{
				num = new StringBuffer(num).reverse().toString();
			}
		}
		try
		{
			return Integer.parseInt(num);
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	private String nameWithoutNumber(int pos)
	{
		String test = encounterList.get(pos).getName().trim();
		String name = "";
		for(int i = 0; i < test.length(); i++)
		{
			try
			{
				int nn = Integer.parseInt(Character.toString(test.charAt(i)));
			}
			catch(Exception e)
			{
				name += Character.toString(test.charAt(i));
			}
		}
		return name.trim();
	}
	
	private String nameWithoutNumber(String s)
	{	
		String name = "";
		for(int i = 0; i < s.length(); i++)
		{
			try
			{
				int nn = Integer.parseInt(Character.toString(s.charAt(i)));
			}
			catch(Exception e)
			{
				name += Character.toString(s.charAt(i));
			}
		}
		return name.trim();
	}
	
	private String leveledName(int n, Creature c)
	{
		String genName = c.getName() + " " + Integer.toString(n);
		
		for(int p = 0; p < encounterList.size(); p++)
		{
			String getName = encounterList.get(p).getName().trim();
			if(genName.equals(getName))
			{
				n++;
				genName = this.leveledName(n, c);
			}
		}
		return genName;
	}
	
	private String getFileExtension(File file)
	{
		try
		{
		    String name = file.getName();
		    int lastIndexOf = name.lastIndexOf(".");
		    if (lastIndexOf == -1) {
		        return ""; // empty extension
		    }
		    return name.substring(lastIndexOf);
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	private void refresh()
	{
		DList<Creature> temp = new DList<Creature>();
		for(int i = 0; i < encounterList.size(); i++)
		{
			Creature c = encounterList.get(i);
			temp.add(new Creature(c.getName(), c.getMaxHitPoints(), c.getInitMod()));
		}
		encounterList.clear();
		
		String newName = "";
		for(int i = 0; i < temp.size(); i++)
		{
			newName = temp.get(i).getName().trim();
			temp.get(i).setName(this.nameWithoutNumber(newName));
			//System.out.printf("%s%n", temp.get(i).getName());
		}
		
		int n = 1;
		String genName = "";
		
		for(int i = 0; i < temp.size(); i++)
		{
			Creature cc = temp.get(i);
			genName = temp.get(i).getName() + " " + Integer.toString(n);
			
			if(encounterList.size() == 0)
			{
				this.addCreature(new Creature(genName, cc.getMaxHitPoints(), cc.getInitMod()));
			}
			else
			{
				this.addCreature(new Creature(this.leveledName(n, cc), cc.getMaxHitPoints(), cc.getInitMod()));
			}
		}
		temp.clear();
	}
	
	
	@Override
	public void caretPositionChanged(InputMethodEvent arg0)
	{}

	@Override
	public void inputMethodTextChanged(InputMethodEvent arg0)
	{}
}
