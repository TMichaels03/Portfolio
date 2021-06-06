import math

def calcHitPoints(hitDieSize, hitDieNum, conMod):
    aveHitDie = (hitDieSize + 1) / 2
    initHp = hitDieSize + conMod
    hitDieNum -= 1
    hp = math.floor(aveHitDie * hitDieNum) + (conMod * hitDieNum) + initHp
    print("{0}d{1} + ({2} * {3}) + {4} = {5}".format(hitDieNum, hitDieSize, hitDieNum, conMod, initHp, hp))
    
    
    
# Get user inputs
print("Enter hit die size")
hitDieSize = int(input("> d"))

print ("Enter Constitution modifier")
conMod = int(input("> "))

#Output    
for i in range(2, 25):
    calcHitPoints(hitDieSize, i, conMod)