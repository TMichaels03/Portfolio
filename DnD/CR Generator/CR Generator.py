import random as r
import math
import sys

# A 1:1 table with recommended hit points based on CR
# Source: Dungeon Master's Guide
def getHitPointTable(rating):
    hitPointTable = {
        1: "71-85",
        2: "86-100",
        3: "101-115",
        4: "116-130",
        5: "131-145",
        6: "146-160",
        7: "161-175",
        8: "176-190",
        9: "191-205",
        10: "206-220",
        11: "221-235",
        12: "236-250",
        13: "251-265",
        14: "266-180",
        15: "281-295",
        16: "296-310",
        17: "311-325",
        18: "326-340",
        19: "341-355",
        20: "356-400",
        21: "401-445",
        22: "446-490",
        23: "491-535",
        24: "536-580",
        25: "581-625",
        26: "626-670",
        27: "671-715",
        28: "716-760",
        29: "761-805",
        30: "806-850"
    }
    return hitPointTable.get(rating)

# A 1:1 table with recommended Armor Class based on CR
# Source: Dungeon Master's Guide        
def getArmorClassTable(rating):
    armorClassTable = {
        1: "13",
        2: "13",
        3: "13",
        4: "14",
        5: "15",
        6: "15",
        7: "15",
        8: "16",
        9: "16",
        10: "17",
        11: "17",
        12: "17",
        13: "18",
        14: "18",
        15: "18",
        16: "18",
        17: "19",
        18: "19",
        19: "19",
        20: "19",
        21: "19",
        22: "19",
        23: "19",
        24: "19",
        25: "19",
        26: "19",
        27: "19",
        28: "19",
        29: "19",
        30: "19"
    }
    return armorClassTable.get(rating)
    
# A 1:1 table with recommended attack bonus based on CR
# Source: Dungeon Master's Guide
def getAttackBonusTable(rating):
    attackBonusTable = {
        1: "+3",
        2: "+3",
        3: "+4",
        4: "+5",
        5: "+6",
        6: "+6",
        7: "+6",
        8: "+7",
        9: "+7",
        10: "+7",
        11: "+8",
        12: "+8",
        13: "+8",
        14: "+8",
        15: "+8",
        16: "+9",
        17: "+10",
        18: "+10",
        19: "+10",
        20: "+10",
        21: "+11",
        22: "+11",
        23: "+11",
        24: "+12",
        25: "+12",
        26: "+12",
        27: "+13",
        28: "+13",
        29: "+13",
        30: "+14"
    }
    return attackBonusTable.get(rating)

# A 1:1 table with recommended damage per round based on CR
# Source: Dungeon Master's Guide
def getDamageTable(rating):
    damageTable = {
        1: "9-14",
        2: "15-20",
        3: "21-26",
        4: "27-32",
        5: "33-38",
        6: "39-44",
        7: "45-50",
        8: "51-56",
        9: "57-62",
        10: "63-68",
        11: "69-74",
        12: "75-80",
        13: "81-86",
        14: "87-92",
        15: "93-98",
        16: "99-104",
        17: "105-110",
        18: "111-116",
        19: "117-122",
        20: "123-140",
        21: "141-158",
        22: "159-176",
        23: "177-194",
        24: "195-212",
        25: "213-230",
        26: "231-248",
        27: "249-266",
        28: "267-284",
        29: "285-302",
        30: "303-320"
    }
    return damageTable.get(rating)
    
# A 1:1 table with recommended saving throw DC based on CR
# Source: Dungeon's Master's Guide
def getSaveTable(rating):
    saveTable = {
        1: "13",
        2: "13",
        3: "13",
        4: "14",
        5: "15",
        6: "15",
        7: "15",
        8: "16",
        9: "16",
        10: "16",
        11: "17",
        12: "17",
        13: "18",
        14: "18",
        15: "18",
        16: "18",
        17: "19",
        18: "19",
        19: "19",
        20: "19",
        21: "20",
        22: "20",
        23: "20",
        24: "21",
        25: "21",
        26: "21",
        27: "22",
        28: "22",
        29: "22",
        30: "23"
    }
    return saveTable.get(rating)

# Generates a random Challenge Rating between 1 and 30
def genCR():
    cr = r.randint(1,30)
    return cr

# Using an input value, generate two values that, when added, have the input value as their average (roudned down)
#
# int: baseRating
# > The average used to determine two other ratings.
#
# return: array
# > Contains the two ratings that have the baseRating as their average.
def genRatings(baseRating):
    # Create ratings array for return
    ratings = []

    # Determine Max value: (input rating * 2), (input rating * 2) + 1
    maxValue = (int(baseRating) * 2) + r.randint(0, 1)
    
    # Generate random value between 0 and maxValue
    # Assign that value to first rating and add to ratings array.
    
    baseLowValue = math.floor(maxValue / 2) + r.randint(-2, -1)
    baseHighValue = math.floor(maxValue / 2) + r.randint(1, 2)
    
    rating = r.randint(baseLowValue, baseHighValue)
    ratings.append(rating)
    
    # Determine second rating and add to ratings array
    ratings.append((maxValue - rating))
    
    # Return ratings
    return ratings    
       
# Uses the base challenge rating to randomly determine the offensive and defensive ratings.
# Prints the generated statistics of the creature.
#
# int: cr
# > The determined final challenge rating of the creature.
def generateStats(cr):
    # 1 : Determine Offensive and Defensive Ratings
    # 2 : Determine Damage per Round and Attack Bonus Ratings
    #     > When determing Attack Bonus rating, also output a relative save DC
    # 3 : Determine Hit Points and AC ratings
    # 4 : Print Stats determined by individual ratings
    
    # Determine Offensive and Defensive Ratings
    offDefRatings = genRatings(cr)
    offensiveRating = offDefRatings[0]
    defensiveRating = offDefRatings[1]
    
    # Determine Damage per Round and Attack Bonus / Save DC Ratings
    dprSaveRatings = genRatings(offensiveRating)
    dprRating = dprSaveRatings[0]
    atkBonusRating = dprSaveRatings[1]
    
    # Determine Hit Points and AC Ratings
    hpAcRatings = genRatings(defensiveRating)
    hpRating = hpAcRatings[0]
    acRating = hpAcRatings[1]
    
    # Output
    print("CR: {0}".format(cr))
    print("Offensive CR: {0}".format(offensiveRating))
    print(" > Attack Bonus: {0} (CR: {1})".format(getAttackBonusTable(atkBonusRating), atkBonusRating))
    print(" > Save DC: {0} (CR: {1})".format(getSaveTable(atkBonusRating), atkBonusRating))
    print(" > Damage Per Round: {0} (CR: {1})".format(getDamageTable(dprRating), dprRating))
    print("Defensive CR: {0}".format(defensiveRating))
    print(" > Armor Class: {0} (CR: {1})".format(getArmorClassTable(acRating), acRating))
    print(" > Hit Points: {0} (CR: {1})".format(getHitPointTable(hpRating), hpRating))
    
# Main    
cr = 0  
try:
    cr = sys.argv[1]
except:
    print("Error: Input not valid. Generating random CR.\n\n")
    cr = genCR()
        
for i in range(0, 10):
    generateStats(cr)
    print("")