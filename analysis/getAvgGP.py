#!/usr/bin/env python

from sys import argv

filename = argv[1]

print filename
with open(filename) as f:
  lines = f.readlines()

starttime = -1
seconds = 0
best = -1
totalCreatures = 0
totalFitness = 0

for lineString in lines:
  line = lineString.split(",")
  time = int(line[0])
  if(starttime < time):
    if(totalCreatures != 0):
      seconds += 1
      print str(seconds)+ ","+ str(best) + "," + str(totalFitness/totalCreatures)
#      print str(totalCreatures) + "," + str(totalFitness)
    starttime = time + 1000 #add 1000 to last time

    
  if(line[1] == "add"):
    fitness = float(line[2])
    totalCreatures += 1
    if(fitness > best):
      best = fitness
    if(fitness != -1):
      totalFitness = totalFitness + fitness
  if(line[1] == "remove"):
    fitness = float(line[2])
    totalCreatures -= 1
    if(fitness != -1):
      totalFitness = totalFitness - fitness

