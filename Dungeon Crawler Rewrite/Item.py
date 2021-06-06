##################
##     Item     ##
##################

# Imports
import random as r


# Item Class Parent
class Item:
    # __init__(self, name)
    #
    # name: String
    # > The name of the item.
    def __init__(self, name):
        self.name = name
        
# Weapon Item
class Weapon(Item):
    # __init__(self, name, type)
    #
    # name: String
    # > The name of the item
    #
    # type: String
    # > Determines between Attack or Magic weapon
    #
    # level: Integer
    # > Directly influences damage modification.
    #
    # Generates a weapon item of a given name, type ("Attack" or "Magic"), and level,
    # then creates a damage modifier influenced by the level of the item.
    def __init__(self, name, type, level):
        self.name = name
        self.type = type
        self.level = level
        
        # Generate
        self.equipSlot = 0       
        self.modifier = r.randint(1, 3) + self.level
    
    
    # details(self)
    #
    # Prints details of Weapon item
    def details(self):
        print("Name: {0} ({1})".format(self.name, self.type))
        print(" > Damage: {0}".format(self.modifier))
        
# Armor Item
class Armor(Item):
    # __init__(self, name, type, level)
    #
    # name: String
    # > The name of the item.
    #
    # type: String
    # > Determines armor equip slot (Head, Chest, or Legs)
    #
    # level: Integer
    # > Directly influences modifier point allocation.
    def __init__(self, name, type, level):
        self.name = name
        self.type = type
        self.level = level
        
        # Generate
        if self.type == "Head":
            self.equipSlot = 1
        elif self.type == "Chest":
            self.equipSlot = 2
        else:
            self.equipSlot = 3
            
        points = r.randint(1, 3) + level
        self.modDefense = 0
        self.modResistance = 0
        for i in range(0, points):
            mod = r.randint(0,1)
            if mod == 0:
                self.modDefense += 1
            elif mod == 1:
                self.modResistance += 1
    
    # details(self)
    #
    # Prints details of Armor item
    def details(self):
        print("Name: {0} ({1})".format(self.name, self.type))
        print(" > Defense: {0}".format(self.modDefense))
        print(" > Resistance: {0}".format(self.modResistance))
        
# Charm Class
class Charm:
    # __init__(self, name, level)
    #
    # name: String
    # > The name of the item
    #
    # level: Integer
    # > Directly influences modifier point allocation.
    def __init__(self, name, level):
        self.name = name
        self.level = level
        
        # Set type
        # Determines equipSlot
        self.type = ("Charm 1" if r.randint(0, 1) == 0 else "Charm 2")
        self.equipSlot = (4 if self.type == "Charm 1" else 5)
        
        # Generate
        points = r.randint(0, 4) + 1 + (0 if self.level == 0 else r.randint(0, self.level) )
        
        self.modAttack = 0
        self.modDefense = 0
        self.modMagic = 0
        self.modResistance = 0
        self.modCharisma = 0
        self.modFortitude = 0
        
        for i in range(0, points):
            stat = r.randint(0,5)
            if stat == 0:
                self.modAttack += 1
            elif stat == 1:
                self.modDefense += 1
            elif stat == 2:
                self.modMagic += 1
            elif stat == 3:
                self.modResistance += 1
            elif stat == 4:
                self.modCharisma += 1
            elif stat == 5:
                self.modFortitude += 1
            else:
                # In case of bug, increase 'points' by 1
                points += 1
                
    # details(self)
    #
    # Print charm details, including name and modifiers
    def details(self):
        print("Name: {0} ({1})".format(self.name, self.type))
        print("Stats:")
        print(" > Attack: {0}".format(self.modAttack))
        print(" > Defense: {0}".format(self.modDefense))
        print(" > Magic: {0}".format(self.modMagic))
        print(" > Resistance: {0}".format(self.modResistance))
        print(" > Charisma: {0}".format(self.modCharisma))
        print(" > Fortitude: {0}".format(self.modFortitude))