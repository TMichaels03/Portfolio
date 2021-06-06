###############################
##       Base Functions      ##
###############################

# Imports
import random as r
import Item
import Chara

# combat(pc, npc)
#
# pc: Chara
# > The Player Character
#
# npc: Chara
# > A non-player character enemy.
#
# isBoss: Boolean
# > Determines enemy AI
#
# return: Integer
# > 1 = Player Victory
# > -1 = Enemy Victory
# > 0 = End of code, error occured.
def combat(pc, npc, isBoss):
    # Encounter Text
    print("{0} has encountered {1} {2}!\n".format(pc.name, test_vowel(npc.name), npc.name))
    
    # Variables
    damage = 0
    attackStr = ""
    
    # Determine Turns
    turn = 0
    npcFirst = r.randint(1,20)
    pcFirst = r.randint(1,20)
    turnList = []
    if npcFirst > pcFirst:
        turnList.append(npc)
        turnList.append(pc)
    else:
        turnList.append(pc)
        turnList.append(npc)
          
    # Auto-Battle Script
    while pc.currentHp > 0 or npc.currentHp > 0:
        # PC Turn
        if turnList[turn] == pc:
            # Test for equipment first
            if pc.equipment[0] != None:
                # Get Equipment Type
                type = pc.equipment[0].type
                damage = (pc.totalMagic - npc.resistance if type == "Magic" else pc.totalAttack - npc.defense)
                attackStr = pc.equipment[0].name
            else:
                # No equipment: Choose highest damage
                dmgAttack = pc.totalAttack - npc.defense
                dmgMagic = pc.totalMagic - npc.resistance
                damage = (dmgAttack if dmgAttack >= dmgMagic else dmgMagic)
                attackStr = ("attack." if dmgAttack >= dmgMagic else "magic.")
            # Deal Damage
            damage = (damage if damage > 0 else 1)
            npc.currentHp -= damage
            print("{0} did {1} damage to the {2} with their {3}".format(pc.name, damage, npc.name, attackStr))
                
        # NPC Turn
        if turnList[turn] == npc:
            # Non-Boss AI (Choose highest attacking stat)
            if isBoss == False:
                damage = (npc.attack - pc.totalDefense if npc.attack >= npc.magic else npc.magic - pc.totalResistance)
            # Boss / Miniboss AI (Choose highest damage)
            elif isBoss == True:
                dmgAttack = npc.attack - pc.totalDefense
                dmgMagic = npc.magic - pc.totalResistance
                damage = (dmgAttack if dmgAttack >= dmgMagic else dmgMagic)
            # Deak Damage
            damage = (damage if damage > 0 else 1)
            pc.currentHp -= damage
            print("The {0} did {1} damage to {2}.\n".format(npc.name, damage, pc.name))
            
        # Test Death
        if npc.currentHp <= 0:
            print("{0} has defeated the {1}.\n".format(pc.name, npc.name))
            return 1
        elif pc.currentHp <= 0:
            #print("{0} has fallen.".format(pc.name))
            return -1
        
        # Remaining (Player) HP
        print("{0}\n - HP: {1} / {2}".format(pc.name, pc.currentHp, pc.maxHp))
        
        # Next Turn
        turn = (1 if turn == 0 else 0)      
        print("")
    return 0
    
# choice_ui(options)
#
# Allows the user to choose from a set of input options.
# Different functions may utilize different options. This function allows
# for that flexibility.
#
# options: Array (String)
# > A list of options to choose from.
#
# returnRaw: Boolean
# > If False, returns the string. If true, returns the integer.
#
# return: String / Integer
# > Returns either the string value or the integer value, denoted by returnRaw.
def choice_ui(options, returnRaw):
    while(True):
        for i in range(0, len(options)):
            print("{0}: {1}".format(i, options[i]))
        user_input = input("Select an option.\n> ")
        try:
            if returnRaw == False:
                toReturn = options[int(user_input)]
            else:
                toReturn = int(user_input)
            print("")
            return toReturn
        except:
            print("Please make a valid selection.\n")
            
# generate_item(level, attackNames, magicNames, charmNames)            
#
# Generates and returns a random item.
#
# level: Integer
# > The level of the item. Affects stats,
#
# attackNames: Array (String)
# > A list of attack weapon names.
#
# magicNames: Array (String)
# > A list of magic weapon names
#
# charmNames: Array (String)
# > A list of charm names.
def generate_item(level, attackNames, magicNames, charmNames):
    # 0 = Attack Weapon
    # 1 = Magic Weapon
    # 2 = Head Armor
    # 3 = Chest Armor
    # 4 = Legs Armor
    # 5 = Charm
    itemType = r.randint(0,5)
    item = None
    if itemType == 0:
        item = Item.Weapon(r.choice(attackNames), "Attack", level)
    elif itemType == 1:
        item = Item.Weapon(r.choice(magicNames), "Magic", level)
    elif itemType == 2:
        item = Item.Armor("Armor", "Head", level)
    elif itemType == 3:
        item = Item.Armor("Armor", "Chest", level)
    elif itemType == 4:
        item = Item.Armor("Armor", "Legs", level)
    elif itemType == 5:
        item = Item.Charm(r.choice(charmNames), level)
    return item
    
# test_vowel(test_string)
#
# test_string: String
#
# Tests the first character of the test_string to see if it is a vowel or not.
#
# return: String
# Returns the string "an" if the first letter is a vowel.
# Returns the string "a" if the first letter is not a vowel.
def test_vowel(test_string):
        vowels = ['A', 'E', 'I', 'O', 'U']
        if (test_string[0].upper()) in vowels:
            return "an"
        else:
            return "a"
            
# default_options()
#
# Used when there are no special options within a room.
# Options are "Gear", "Continue", "Exit"
#
# return: Integer
# > 0 = Exit
# > 1 = Continue
# > 2 = Gear
# > 3 = Stats
def default_options():
    options = ["Gear", "Stats", "Continue", "Exit"]
    while(True):
        option = choice_ui(options, False)
        if option == "Continue":
            return 1
        elif option == "Exit":
            return 0
        elif option == "Gear":
            return 2
        elif option == "Stats":
            return 3
            
# gear_ui(pc, inventory)
#
# Used when the "Gear" option is selected.
# Allows players to equip and unequip gear from their character.
#
# pc: Chara
# > The character whose gear is getting modified.
#
# inventory: Array
# Removes or adds the item equpped or unequipped from the character to the inventory.
def gear_ui(pc, inventory):
    # Print player stats
    pc.details()
    print("")
    options = ["Equip", "Unequip", "Exit"]
    
    while(True):
    # Options  
        option = choice_ui(options, False)
        if option == "Equip":
            equipOptions = []
        
            # Print inventory
            for i in range(1, len(inventory)):
                item = inventory[i]
                item.details()
                equipOptions.append("{0} ({1})".format(item.name, item.type))
                print("")
            # Options
            equipOption = choice_ui(equipOptions, True) + 1
            pc.equip(inventory[equipOption], inventory, equipOption)
        elif option == "Unequip":
            equipOptions = []
        
            # Print Equipment
            for i in range(0, len(pc.equipment)):
                if pc.equipment[i] != None:
                    item = pc.equipment[i]
                    item.details()
                    equipOptions.append("{0} ({1})".format(item.name, item.type))
                    print("")
                else:
                    equipOptions.append("None")
            # Options
            equipOption = choice_ui(equipOptions, True)
            pc.unequip(equipOption, inventory)
        elif option == "Exit":
            return 1