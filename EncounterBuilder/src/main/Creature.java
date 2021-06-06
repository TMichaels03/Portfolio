package main;

import java.util.Comparator;

public class Creature
{
	private String name;
	private int init;
	private int maxHitPoints;
	private int currentHitPoints;
	private String type;
	private int initMod;
	
	public Creature(String name, int init, String type)
	{
		this.name = name;
		this.init = init;
		this.type = type;
	}

	public Creature(String name, int init, int maxHitPoints, String type)
	{
		this.name = name;
		this.init = init;
		this.maxHitPoints = maxHitPoints;
		this.currentHitPoints = maxHitPoints;
		this.type = type;
	}
	
	public Creature(String name, int maxHitPoints, int initMod)
	{
		this.name = name;
		this.maxHitPoints = maxHitPoints;
		this.currentHitPoints = maxHitPoints;
		this.initMod = initMod;
		this.type = "NPC";
		this.init = 0;
	}

	public int getCurrentHitPoints()
	{
		return currentHitPoints;
	}

	public void setCurrentHitPoints(int currentHitPoints)
	{
		this.currentHitPoints = currentHitPoints;
	}
	

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public int getInit()
	{
		return init;
	}

	public int getMaxHitPoints()
	{
		return maxHitPoints;
	}
	
	public void setMaxHitPoints(int maxHitPoints)
	{
		this.maxHitPoints = maxHitPoints;
	}

	public String getType()
	{
		return type;
	}
	
	public void setInit(int init)
	{
		this.init = init;
	}
	
	public int getInitMod()
	{
		return initMod;
	}
	
	public static final Comparator<Creature> BY_INIT = new Comparator<Creature>()
		{
			@Override
			public int compare(Creature c1, Creature c2)
			{
				return (c1.getInit() > c2.getInit() ? -1 : 1);
			}
		};
		
	public static final Comparator<Creature> BY_NAME = new Comparator<Creature>()
			{
				@Override
				public int compare(Creature c1, Creature c2)
				{
					if(c1.getName().charAt(0) == c2.getName().charAt(0))
					{
						try
						{
							int len = (c1.getName().length() > c2.getName().length() ? c1.getName().length() : c2.getName().length());
							for(int i = 0; i < len; i++)
							{
								if(c1.getName().charAt(i) != c2.getName().charAt(i))
								{
									return (c1.getName().charAt(i) > c2.getName().charAt(i) ? -1 : 1);
								}
							}
						}
						catch(Exception e)
						{
							return 1;
						}
						return 1;
					}
					else
					{
						return (c1.getName().charAt(0) > c2.getName().charAt(0) ? -1 : 1);
					}
				}
			};
}
