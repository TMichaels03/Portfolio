##########################
#    Location: Crypt     #
##########################

#Imports
import random as r
import math
import Chara
import Item
import Base
import sys

# Strength Weapon Types
#
# A list of strings, of weapon names, to be used when generating random strength weapon items.
strength_weapons = ["Mace", "Maul", "Dagger", "Warhammer", "Quarterstaff", "Flail", "Morning Star", "Crossbow"]

# Magic Weapon Types
#
# A list of strings, of weapon names, to be used when generating random magic weapon items.
magic_weapons = ["Staff", "Crystal", "Amulet", "Orb"]

# Charm item types
#
# A list of Charm item names, used when generating Charm items.
charms = ["Holy Symbol", "Necklace", "Ring", "Talisman"]

# Enemy Types
#
# basicEnemyTypes: A list of strings containing basic enemy type names.
# miniBossEnemyTypes: A list of strings containing mini boss enemy type names.
# bossEnemyTypes: A list of strings containing boss enemy type names.
#
# Enemy names are used within combat encounter functions to generate a random enemy.
basicEnemyTypes = ["Skeleton", "Giant Rat", "Giant Spider", "Zombie", "Ghost"]
miniBossEnemyTypes = ["Vampire Spawn", "Dark Mage", "Wight"]
bossEnemyTypes = ["Vampire", "Necromancer"]

# gen_encounter()
# return: String
#
# Create a predefined 1:1 int to str dictionary, and return a random string element with default return of "Error".
# The returned string determines the next called 'encounter' function.
def gen_encounter():
    encounterList = {
        0: "Combat",
        1: "Dart Trap",
        2: "Coffin",
        3: "Ritual",
        4: "Protected Room"
    }
    listLen = len(encounterList) - 1
    return encounterList.get(r.randint(0, listLen), "Error")

# controller(pc, level)
#
# The base controller of this locale. Determines which encounters are generated.
#
# pc: Chara
# > The character exploring this locale.
#
# level: Integer
# > The overall level of the locale.
def controller(pc, level, room, inventory):
        # Intro
        print("TODO: Intro\n")
        
        # Generate Encounter
        while(True):
            print("Room: {0}".format(room))
            print("{0}'s HP: {1} / {2}\n".format(pc.name, pc.currentHp, pc.maxHp))
            result = None
            encounter = gen_encounter()
            if room == 30 or room == 20 or room == 10:
                result = encounter_combat(pc, room, level, False, inventory)
            elif encounter == "Combat":
                result = encounter_combat(pc, room, level, False, inventory)
            elif encounter == "Dart Trap":
                result = encounter_dart_trap(pc, level, inventory)
            elif encounter == "Coffin":
                result = encounter_coffin(pc, inventory, room, level)
            elif encounter == "Ritual":
                result = encounter_ritual(pc, level, inventory)
            elif encounter == "Protected Room":
                result = encounter_protected_room(pc, inventory)
            elif encounter == "Error":
                print("Error in selecting encounter. Reducing Room by 1.\n")
                room -= 1
                
            if result == 0:
                # Exit
                #print("TODO: controller - Exit")
                sys.exit()
            elif result == -1:
                print("{0} has perished within the crypt, only to join the numbers of the living dead...".format(pc.name))
                #TODO: PC Death
                sys.exit()
            else:
                room += 1
    
# test_death(pc)
#
# pc: Character
#
# Tests a Character object's currentHp variable to see if it is equal to or less than zero.
# If less than or equal to zero, end the run. Else return int "1" as default.
def test_death(pc):
    if pc.currentHp <= 0:
        return -1
    else:
        return 1
        
# encounter_combat(pc)
#
# pc: Chara
# > The character to do combat.
#
# room: Integer
# > The current room number. Used to determine enemy type
#
# level: Integer
# > The level of the locale
#
# bypass: Boolean
# > Used by certain encounters to continue within the room, but not move on.
#
# return: Integer
# > 0 = Exit
# > 1 = Continue
def encounter_combat(pc, room, level, bypass, inventory):
    # Test current room for miniboss or boss type.
    if room == 30: # Boss
        npc = Chara.NPC(r.choice(bossEnemyTypes), level + 5)
        combatResult = Base.combat(pc, npc, True)
    elif room == 10 or room == 20: # Miniboss
        npc = Chara.NPC(r.choice(miniBossEnemyTypes), level + 2)
        combatResult = Base.combat(pc, npc, True)
    else:
        npc = Chara.NPC(r.choice(basicEnemyTypes), level)
        combatResult = Base.combat(pc, npc, False)
    if combatResult == 0:
        print("TODO: Combat Encounter Error")
    pcDeath = test_death(pc)
    if pcDeath == -1:
        return pcDeath
    elif bypass == True:
        return 1
    while(True):
        result =  Base.default_options()
        if result == 2:
            gear_ui(pc, inventory)
        elif result == 3:
            pc.details()
        else:
            return result
    
# encounter_coffin(pc, inventory)
#
# An encounter where the player comes across a closed coffin.
# They can open it to receive an item, but there is a 50% of an enemy
# inside, initiating combat.
#
# pc: Chara
# > The player character
#
# inventory: Array
# > The list of items the character has found.
#
# room: Integer
# > Used for combat encounters.
#
# level: Integer
# > The level of the locale
#
# return: Integer
# > 0 = Exit
# > 1 = Continue
# > -1 = PC Death
def encounter_coffin(pc, inventory, room, level):
    print("A simple room with an unopened coffin against the wall. What could be in it?\n")
    testStr = ""
    isOpen = False
    options = None
    while(True):
        options = ["Gear", "Stats", "Continue", "Exit"]
        if isOpen == False:
            options.append("Investigate Coffin")
        option = Base.choice_ui(options, False)
        if option == "Investigate Coffin":
            # TODO: Generate Item and combat
            isOpen = True
            
            # Determine Combat (60% Chance)
            combatChance = r.randint(1,100)
            #print("Combat Chance Gen: {0}".format(combatChance))
            if combatChance <= 60:
                # Combat
                combatResult = encounter_combat(pc, room, level, True, inventory)
                
            # Generate Item
            item = Base.generate_item(r.randint(0, pc.level), strength_weapons, magic_weapons, charms)
            inventory.append(item)
            print("{0} has found {1} {2} ({3}) in the coffin.".format(pc.name, Base.test_vowel(item.name), item.name, item.type))
            item.details()
        elif option == "Gear":
            Base.gear_ui(pc, inventory)
        elif option == "Continue":
            return 1
        elif option == "Exit":
            return 0
        elif option == "Stats":
            pc.details()
        print("")
        
# encounter_dart_trap(pc)
#
# Deals a random amount of damage to the character.
#
# pc: Chara
# > The player character taking damage.
#
# level: Integer
# > The level of the locale
#
# return: Integer
# > 0 = Exit
# > 1 = Continue
def encounter_dart_trap(pc, level, inventory):
    print("A seemingly simple room, it is too late when the trap springs.")
    damage = r.randint(1, 3 + level)
    pc.currentHp -= damage
    print("{0} has taken {1} damage from a hidden dart trap.\n".format(pc.name, damage))
    if test_death(pc) == -1:
        return -1
    while(True):
        result =  Base.default_options()
        if result == 2:
            gear_ui(pc, inventory)
        elif result == 3:
            pc.details()
        else:
            return result
    
# encounter_ritual(pc)
#
# The player can attempt to finish a ritual.
# If successful, they gain a point in a base stat of their choice.
# If failed, they lose a point in all base stats.
#
# pc: Chara
# > The character.
#
# level: Integer
# > The level of the locale
#
# return: Integer
# > 0 = Exit
# > 1 = Continue
def encounter_ritual(pc, level, inventory):
    print("A ritualistic circle drawn on the ground. A force compels you to finish it.\n")
    isAttempted = False
    testStr = ""
    options = None
    while(True):
        options = ["Gear", "Stats", "Continue", "Exit"]
        if isAttempted == False:
            options.append("Attempt Ritual")
        option = Base.choice_ui(options, False)
        if option == "Attempt Ritual":
            isAttempted = True           
            # Set Threshold
            threshold = r.randint(10, 20) + r.randint(0, level)           
            # Test Fortitude
            fort = r.randint(1, 20) + pc.totalFortitude           
            if fort >= threshold:
                # Success
                stats = ["Max HP", "Attack", "Defense", "Magic", "Resistance", "Charisma", "Fortitude"]
                print("{0} has completed the ritual. The magic bolsters their abilities!".format(pc.name))
                print("Choose 2 stats to increase by 1 each:")
                n = 0
                while(True):
                    statChoice = Base.choice_ui(stats, False)                   
                    if statChoice in stats:
                        if statChoice == "Max HP":
                            pc.maxHp += 1
                            pc.currentHp += 1
                            stats.remove("Max HP")
                        elif statChoice == "Attack":
                            pc.baseAttack += 1
                            stats.remove("Attack")
                        elif statChoice == "Defense":
                            pc.baseDefense += 1
                            stats.remove("Defense")
                        elif statChoice == "Magic":
                            pc.baseMagic += 1
                            stats.remove("Magic")
                        elif statChoice == "Resistance":
                            pc.baseResistance += 1
                            stats.remove("Resistance")
                        elif statChoice == "Charisma":
                            pc.baseCharisma += 1
                            stats.remove("Charisma")
                        elif statChoice == "Fortitude":
                            pc.baseFortitude += 1
                            stats.remove("Fortitude")
                        n += 1                        
                    if n >= 2:
                        break                           
            else:
                # Fail
                print("{0} has failed to complete the ritual. A surge of wild magic drained their abilities!".format(pc.name))
                pc.maxHp -= 1
                pc.baseAttack -= 1
                pc.baseDefense -= 1
                pc.baseMagic -= 1
                pc.baseResistance -= 1
                pc.baseCharisma -= 1
                pc.baseFortitude -= 1
                pc.update_stats()
        elif option == "Continue":
            return 1
        elif option == "Exit":
            return 0
        elif option == "Gear":
            gear_ui(pc, inventory)
        elif option == "Stats":
            pc.details()
            
# encounter_protected_room(pc)
#
# A helpful room encounter that heals a player for half of their max hp.
#
# pc: Chara
# > The character to be healed.
#
# return: Integer
# > 0 = Exit
# > 1 = Continue
def encounter_protected_room(pc, inventory):
    print("Unlike the rest of the crypt, this room feels welcoming. A warm light bathes over {0}, recovering some of their wounds.".format(pc.name))
    toHeal = int(math.floor(pc.maxHp / 2))
    print("They heal {0} hit points.\n".format(toHeal))
    pc.currentHp += toHeal
    pc.update_stats()
    while(True):
        result =  Base.default_options()
        if result == 2:
            gear_ui(pc, inventory)            
        elif result == 3:
            pc.details()
        else:
            return result    