#########################
##     Chara Class     ##
#########################

# Imports
import random as r
import math
#TODO: import Item

# Class: Chara
class Chara:
    # __init__(self, name)
    #
    # name: String
    # > The character's name
    def __init__(self, name):
        self.name = name
        
        # Set Level to 1
        self.level = 1
        self.xp = 0
        
        # Create Base Stats
        self.maxHp = 5
        self.currentHp = 5
        
        self.baseAttack = 0
        self.baseDefense = 0
        self.baseMagic = 0
        self.baseResistance = 0
        self.baseCharisma = 0
        self.baseFortitude = 0
        
        self.totalAttack = 0
        self.totalDefense = 0
        self.totalMagic = 0
        self.totalResistance = 0
        self.totalCharisma = 0
        self.totalFortitude = 0
        
        # Create empty Equipment
        #
        # Slot 0 = Weapon
        # Slot 1 = Armor (Head)
        # Slot 2 = Armor (Chest)
        # Slot 3 = Armor (Legs)
        # Slot 4 = Charm 1
        # Slot 5 = Charm 2
        self.equipment = [None, None, None, None, None, None]
        
        # Generate Base Stats
        statPoints = 13
        for i  in range(0, statPoints):
            stat = r.randint(0, 6)
            if stat == 0: # maxHp
                self.maxHp += 1
            elif stat == 1: # baseAttack
                self.baseAttack += 1
            elif stat == 2: # baseDefense
                self.baseDefense += 1
            elif stat == 3: # baseMagic
                self.baseMagic += 1
            elif stat == 4: # baseResistance
                self.baseResistance += 1
            elif stat == 5: # baseCharisma
                self.baseCharisma += 1
            elif stat == 6: # baseFortitude
                self.baseFortitude += 1
        self.currentHp = self.maxHp
        self.update_stats()
        
    # update_stats(self)
    #
    # Updates total stats to account for equipment.
    def update_stats(self):
        # Set any negatives to 0
        self.baseAttack = (self.baseAttack if self.baseAttack >= 0 else 0)
        self.baseDefense = (self.baseDefense if self.baseDefense >= 0 else 0)
        self.baseMagic = (self.baseMagic if self.baseMagic >= 0 else 0)
        self.baseResistance = (self.baseResistance if self.baseResistance >= 0 else 0)
        self.baseCharisma = (self.baseCharisma if self.baseCharisma >= 0 else 0)
        self.baseFortitude = (self.baseFortitude if self.baseFortitude >= 0 else 0)
    
        # Set to base
        self.totalAttack = self.baseAttack
        self.totalDefense = self.baseDefense
        self.totalMagic = self.baseMagic
        self.totalResistance = self.baseResistance
        self.totalCharisma = self.baseCharisma
        self.totalFortitude = self.baseFortitude
        
        # Check for equipment
        if self.equipment[0] != None:
            if self.equipment[0].type == "Attack":
                self.totalAttack += self.equipment[0].modifier
            elif self.equipment[0].type == "Magic":
                self.totalMagic += self.equipment[0].modifier
        if self.equipment[1] != None:
            self.totalDefense += self.equipment[1].modDefense
            self.totalResistance += self.equipment[1].modResistance
        if self.equipment[2] != None:
            self.totalDefense += self.equipment[2].modDefense
            self.totalResistance += self.equipment[2].modResistance
        if self.equipment[3] != None:
            self.totalDefense += self.equipment[3].modDefense
            self.totalResistance += self.equipment[3].modResistance
        if self.equipment[4] != None:
            self.totalAttack += self.equipment[4].modAttack
            self.totalDefense += self.equipment[4].modDefense
            self.totalMagic += self.equipment[4].modMagic
            self.totalResistance += self.equipment[4].modResistance
            self.totalCharisma += self.equipment[4].modCharisma
            self.totalFortitude += self.equipment[4].modFortitude
        if self.equipment[5] != None:
            self.totalAttack += self.equipment[5].modAttack
            self.totalDefense += self.equipment[5].modDefense
            self.totalMagic += self.equipment[5].modMagic
            self.totalResistance += self.equipment[5].modResistance
            self.totalCharisma += self.equipment[5].modCharisma
            self.totalFortitude += self.equipment[5].modFortitude
            
        # TEMPORARY: Set total stat to base Stat
        
        # Test maxHp vs currentHp
        if self.currentHp > self.maxHp:
            self.currentHp = self.maxHp
        
    # details(self)
    #
    # Prints out character stats and equipment
    def details(self):
        # Print header - name, level, xp
        print("Name: {0}".format(self.name))
        print("Level: {0}".format(self.level))
        print("XP: {0}".format(self.xp))            
        
        # Print base stats
        print("\nBase Stats:")
        print(" > Max Hp: {0}".format(self.maxHp))
        print(" > Base Attack: {0}".format(self.baseAttack))
        print(" > Base Defense: {0}".format(self.baseDefense))
        print(" > Base Magic: {0}".format(self.baseMagic))
        print(" > Base Resistance: {0}".format(self.baseResistance))
        print(" > Base Charisma: {0}".format(self.baseCharisma))
        print(" > Base Fortitude: {0}".format(self.baseFortitude))
        
        # Print total stats
        print("\nTotal Stats:")
        print(" > Max Hp: {0}".format(self.maxHp))
        print(" > Total Attack: {0}".format(self.totalAttack))
        print(" > Total Defense: {0}".format(self.totalDefense))
        print(" > Total Magic: {0}".format(self.totalMagic))
        print(" > Total Resistance: {0}".format(self.totalResistance))
        print(" > Total Charisma: {0}".format(self.totalCharisma))
        print(" > Total Fortitude: {0}".format(self.totalFortitude))
    
        # Gear
        print("\nEquipped Gear:")
        for i in range(0, len(self.equipment)):
            if self.equipment[i] != None:
                item = self.equipment[i]
                print("{0} : {1} ({2})".format(i, item.name, item.type))
                item.details()
                print("")
            else:
                print("{0} : None".format(i))
        print("")
        
    # equip(item)
    #
    # Equips an item to one of the available slots.
    #
    # item: Item
    # > The item to equip.
    #
    # inventory: Array
    # > Removes the item from the inventory when equipped.
    #
    #
    # index: Integer
    # > The index of the item to remove
    #
    # return: Array
    # > Returns the inventory.
    def equip(self, item, inventory, index):
        if self.equipment[item.equipSlot] == None:
            self.equipment[item.equipSlot] = item
            inventory.pop(index)
        else:
            print("There is already an item equipped! You must unequip that first!")
        self.update_stats()
        return inventory
            
    # unequip(equipSlot)
    #
    # Unequips an already equipped item.
    #
    # equipSlot: Integer
    # > Used as an index for the equipment array.
    #
    # inventory: Array
    # > Adds the uneqipped item back into the inventory
    def unequip(self, equipSlot, inventory):
        if self.equipment[equipSlot] != None:
            item = self.equipment[equipSlot]
            self.equipment[equipSlot] = None
            inventory.append(item)
            self.update_stats()
        else:
            print("There is no gear equipped there.")
        return inventory
    
class NPC(Chara):
    # __init__(self, name, level)
    #
    # name: String
    # > The name of the NPC
    #
    # level: Integer
    # > used to determine stat allotment points
    def __init__(self, name, level):
        self.name = name
        self.level = level
        
        self.maxHp = 1
        self.currentHp = 0
        self.attack = 0
        self.defense = 0
        self.magic = 0
        self.resistance = 0
        
        # Generate Stats
        points = 5 + (0 if self.level == 0 else r.randint(1, self.level))
        for i in range(0, points):
            stat = r.randint(0,4)
            if stat == 0:
                self.maxHp += 1
            elif stat == 1:
                self.attack += 1
            elif stat == 2:
                self.defense += 1
            elif stat == 3:
                self.magic += 1
            elif stat == 4:
                self.resistance += 1
            else:
                self.maxHp += 1
        self.currentHp = self.maxHp
                
    def details(self):
        print("Name: {0}".format(self.name))
        print("\nStats:")
        print(" > Max Hp: {0}".format(self.maxHp))
        print(" > Attack: {0}".format(self.attack))
        print(" > Defense: {0}".format(self.defense))
        print(" > Magic: {0}".format(self.magic))
        print(" > Resistance: {0}".format(self.resistance))