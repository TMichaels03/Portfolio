import math

def calcHitPoints(hitDieSize, hitDieNum, conMod):
    aveHitDie = (hitDieSize + 1) / 2
    hp = math.floor(aveHitDie * hitDieNum) + (conMod * hitDieNum)
    print("{0}d{1} + ({2} * {3}) = {4}".format(hitDieNum, hitDieSize, hitDieNum, conMod, hp))
    
    
    
# Get user inputs
print("Enter hit die size")
hitDieSize = int(input("> d"))

print ("Enter Constitution modifier")
conMod = int(input("> "))

#Output    
for i in range(1, 25):
    calcHitPoints(hitDieSize, i, conMod)