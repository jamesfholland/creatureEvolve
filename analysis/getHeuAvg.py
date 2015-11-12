#!/usr/bin/env python

import csv
from sys import argv

filename = argv[1]

print filename
with open(filename) as f:
  lines = f.readlines()

dictionary = {}

starttime = -1
seconds = 0
best = -1
totalUsage = {}
totalTimeHelped = {}
totalImprovement = {}

totalUsage["ADDER"] = 0
totalTimeHelped["ADDER"] = 0
totalImprovement["ADDER"] = 0
totalUsage["DUPLICATOR"] = 0
totalTimeHelped["DUPLICATOR"] = 0
totalImprovement["DUPLICATOR"] = 0
totalUsage["INVERTER"] = 0
totalTimeHelped["INVERTER"] = 0
totalImprovement["INVERTER"] = 0
totalUsage["MOVER"] = 0
totalTimeHelped["MOVER"] = 0
totalImprovement["MOVER"] = 0
totalUsage["RANDOMIZER"] = 0
totalTimeHelped["RANDOMIZER"] = 0
totalImprovement["RANDOMIZER"] = 0
totalUsage["SYMMETRIZER"] = 0
totalTimeHelped["SYMMETRIZER"] = 0
totalImprovement["SYMMETRIZER"] = 0
totalUsage["SCALER"] = 0
totalTimeHelped["SCALER"] = 0
totalImprovement["SCALER"] = 0
totalUsage["SCALEBLOCK"] = 0
totalTimeHelped["SCALEBLOCK"] = 0
totalImprovement["SCALEBLOCK"] = 0
totalUsage["SCALEROOT"] = 0
totalTimeHelped["SCALEROOT"] = 0
totalImprovement["SCALEROOT"] = 0
totalUsage["EXTENDER"] = 0
totalTimeHelped["EXTENDER"] = 0
totalImprovement["EXTENDER"] = 0
totalUsage["SUBTRACTOR"] = 0
totalTimeHelped["SUBTRACTOR"] = 0
totalImprovement["SUBTRACTOR"] = 0
totalUsage["SUBTRACTOR"] = 0
totalTimeHelped["SUBTRACTOR"] = 0
totalImprovement["SUBTRACTOR"] = 0
totalUsage["CHIMERA"] = 0
totalTimeHelped["CHIMERA"] = 0
totalImprovement["CHIMERA"] = 0
totalUsage["CUTANDSPLICE"] = 0
totalTimeHelped["CUTANDSPLICE"] = 0
totalImprovement["CUTANDSPLICE"] = 0
totalUsage["SINGLECROSSOVER"] = 0
totalTimeHelped["SINGLECROSSOVER"] = 0
totalImprovement["SINGLECROSSOVER"] = 0

mutationTypes = {"ADDER","DUPLICATOR","INVERTER","MOVER","RANDOMIZER","SYMMETRIZER","SCALER","SCALEBLOCK","SCALEROOT","EXTENDER","SUBTRACTOR","CHIMERA","CUTANDSPLICE","SINGLECROSSOVER"}

for lineString in lines:
  line = lineString.split(",")
  try:
    if(line[1] in mutationTypes):
      fitnessC = float(line[2])
      fitnessP = float(line[4])
      fitnessDelta = fitnessC - fitnessP
      if(line[1] == "ADDER"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["ADDER"] += 1
          totalImprovement["ADDER"] += fitnessDelta
        totalUsage["ADDER"] += 1
      if(line[1] == "DUPLICATOR"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["DUPLICATOR"] += 1
          totalImprovement["DUPLICATOR"] += fitnessDelta
        totalUsage["DUPLICATOR"] += 1
      if(line[1] == "INVERTER"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["INVERTER"] += 1
          totalImprovement["INVERTER"] += fitnessDelta
        totalUsage["INVERTER"] += 1
      if(line[1] == "MOVER"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["MOVER"] += 1
          totalImprovement["MOVER"] += fitnessDelta
        totalUsage["MOVER"] += 1
      if(line[1] == "RANDOMIZER"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["RANDOMIZER"] += 1
          totalImprovement["RANDOMIZER"] += fitnessDelta
        totalUsage["RANDOMIZER"] += 1
      if(line[1] == "SYMMETRIZER"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["SYMMETRIZER"] += 1
          totalImprovement["SYMMETRIZER"] += fitnessDelta
        totalUsage["SYMMETRIZER"] += 1
      if(line[1] == "SCALER"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["SCALER"] += 1
          totalImprovement["SCALER"] += fitnessDelta
        totalUsage["SCALER"] += 1
      if(line[1] == "SCALEBLOCK"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["SCALEBLOCK"] += 1
          totalImprovement["SCALEBLOCK"] += fitnessDelta
        totalUsage["SCALEBLOCK"] += 1
      if(line[1] == "SCALEROOT"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["SCALEROOT"] += 1
          totalImprovement["SCALEROOT"] += fitnessDelta
        totalUsage["SCALEROOT"] += 1
      if(line[1] == "EXTENDER"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["EXTENDER"] += 1
          totalImprovement["EXTENDER"] += fitnessDelta
        totalUsage["EXTENDER"] += 1
      if(line[1] == "SUBTRACTOR"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["SUBTRACTOR"] += 1
          totalImprovement["SUBTRACTOR"] += fitnessDelta
        totalUsage["SUBTRACTOR"] += 1

      if(line[1] == "CHIMERA"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["CHIMERA"] += 1
          totalImprovement["CHIMERA"] += fitnessDelta
        totalUsage["CHIMERA"] += 1
      if(line[1] == "CUTANDSPLICE"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["CUTANDSPLICE"] += 1
          totalImprovement["CUTANDSPLICE"] += fitnessDelta
        totalUsage["CUTANDSPLICE"] += 1
      if(line[1] == "SINGLECROSSOVER"):
        if(fitnessP != -1 and fitnessDelta > 0):
          totalTimeHelped["SINGLECROSSOVER"] += 1
          totalImprovement["SINGLECROSSOVER"] += fitnessDelta
        totalUsage["SINGLECROSSOVER"] += 1
  except (ValueError,IndexError)  as e:
    continue
print "TotalUsage"
print totalUsage
print "TotalTimeHelped"
print totalTimeHelped
print "Total Improvement"
print totalImprovement
