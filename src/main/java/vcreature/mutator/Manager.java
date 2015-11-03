package vcreature.mutator;

import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;

/**
 * This class handles switching between Genetics and HillClimbing.
 */
public class Manager
{
  private float switchingThreshhold = 0.001f;

  private long timeStart;
  private float startFitness;

  private Genome currentGenome;
  private boolean repeated = false;
  private float firstTest;
  private float fitnessBar;

  private MutationType currentMutationType = MutationType.GENETIC;

  private GeneticManager geneticManager;
  private HillClimbingManager hillClimbingManager;
  private float bestFitnessFound = 0.0f;

  public Manager()
  {
    initializeGenetics();
    initializeHillClimbing();
    currentGenome = GenePool.getWorst();

  }

  private void initializeHillClimbing()
  {
    hillClimbingManager = new HillClimbingManager();
  }

  private void initializeGenetics()
  {
    geneticManager = new GeneticManager();
  }

  public void setSwitchingThreshhold(float threshhold)
  {
    this.switchingThreshhold = threshhold;
  }

  public Genome getCurrentGenome()
  {
    synchronized (this.currentGenome)
    {
      return this.currentGenome;
    }
  }

  public Genome getNextCreature(float lastFitness)
  {
    synchronized (this.currentGenome)
    {
      float minFitness = lastFitness;

      if (repeated)
      {
        minFitness = Math.min(lastFitness, firstTest);
        if (minFitness > bestFitnessFound)
        {
          bestFitnessFound = minFitness;
        }
      }
      else if (lastFitness > fitnessBar && fitnessBar != -1)
      {
        //Don't get new Genome we will test again.
        firstTest = lastFitness;
        repeated = true;
        return currentGenome;
      }

      switch (currentMutationType)
      {
        case GENETIC:
          currentGenome = geneticManager.getNextGenome(minFitness);
          fitnessBar = geneticManager.getFitnessBar();
          break;
        case HILL:
          currentGenome = hillClimbingManager.getNextCreature(minFitness);
          fitnessBar = hillClimbingManager.getFitnessBar();
          break;
      }

      return currentGenome;
    }
  }
  public void setCurrentMutationType(MutationType mutationType)
  {
    currentMutationType = mutationType;
  }

  public enum MutationType
  {
    HILL, GENETIC
  }


}
