import Chara
import Item
import random as r
import Base
import Location_Crypt

pc = Chara.Chara("Test PC")
pc.details()

inventory = [0]

Location_Crypt.controller(pc, 1, 1, inventory)