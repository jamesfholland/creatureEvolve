package vcreature.mutator;

import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class handles switching between Genetics and HillClimbing. If it meets
 * certain requirements such as not improving in a given amount of time,
 * or not meeting a certain threshold
 */
public class Manager
{
  private float switchingThreshhold = 1.0f;
  private long startTime;

  private Genome currentGenome;
  private Lock genomeLock = new ReentrantLock();
  private boolean repeated = false;
  private float firstTest;
  private float fitnessBar;

  private MutationType currentMutationType = MutationType.GENETICHILL;
  private HeuristicMode currentHeuristic = HeuristicMode.HILLCLIMB;

  private GeneticManager geneticManager;
  private HillClimbingManager hillClimbingManager;
  private float deltaFitness = 0.0f;

  /**
   *
   * @param type Either Gentic or Hill Climbing, or HillClimbing/Gentic
   */
  public Manager(MutationType type)
  {
    currentMutationType = type;
    initializeGenetics();
    initializeHillClimbing();
    currentGenome = GenePool.getWorst();
    startTime = System.currentTimeMillis();
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
    genomeLock.lock();
    try
    {
      return this.currentGenome;
    }
    finally
    {
      genomeLock.unlock();
    }
  }

  public Genome getNextCreature(float lastFitness)
  {
    genomeLock.lock();
    try
    {
      float minFitness = lastFitness;

      if (repeated)
      {
        minFitness = Math.min(lastFitness, firstTest);
        //Add our change in fitness, unless its parent was an untested genome.
        if (fitnessBar != -1)
        {
          deltaFitness += minFitness - fitnessBar;
        }
        repeated = false;
      }
      else if (lastFitness > fitnessBar)
      {
        //Don't get new Genome we will test again.
        firstTest = lastFitness;
        repeated = true;
        return currentGenome;
      }


      minFitness = determineSwitch(minFitness);


      switch (currentHeuristic)
      {
        case GENETICALGORITHM:
          currentGenome = geneticManager.getNextGenome(minFitness);
          fitnessBar = geneticManager.getFitnessBar();
          break;
        case HILLCLIMB:
          currentGenome = hillClimbingManager.getNextCreature(minFitness);
          fitnessBar = hillClimbingManager.getFitnessBar();
          break;
      }

      return currentGenome;
    }
    finally
    {
      genomeLock.unlock();
    }
  }

  private float determineSwitch(float fitness)
  {
    float returnFitness = fitness;
    switch (currentMutationType)
    {
      //Forced HillClimbing
      case HILL:
        switch (currentHeuristic)
        {
          case GENETICALGORITHM:
            switchToHILL(fitness);
            returnFitness = -1;
            break;
        }
        break;
      //Force Genetic Algorithm
      case GENETIC:
        switch (currentHeuristic)
        {
          case HILLCLIMB:
            switchToGENETICALG(fitness);
            returnFitness = -1;
        }
        break;
      //Switching permitted
      case GENETICHILL:
      {
        switch (currentHeuristic)
        {
          case GENETICALGORITHM:
            if ((System.currentTimeMillis() - startTime) > 15000 * 60)
            {
              switchToHILL(fitness);
              returnFitness = -1;
            }
            break;
          case HILLCLIMB:
            if (this.getFitnessPerMinute() <= this.switchingThreshhold
                && (System.currentTimeMillis() - startTime) > 2000 * 60)
            {
              switchToGENETICALG(fitness);
              returnFitness = -1;
            }
            break;
        }

      }
    }
    //We switched now reset time.
    if (returnFitness == -1)
    {
      System.out.println("Switched to " + currentHeuristic.name());
      deltaFitness = 0;
      startTime = System.currentTimeMillis();
    }

    return returnFitness;
  }

  public float getFitnessPerMinute()
  {
    return (deltaFitness) * 1000 / (System.currentTimeMillis() - startTime);
  }

  public void setCurrentMutationType(MutationType mutationType)
  {
      currentMutationType = mutationType;
  }

  private void switchToHILL(float fitness)
  {
    geneticManager.finalize(fitness);
    currentHeuristic = HeuristicMode.HILLCLIMB;
  }

  private void switchToGENETICALG(float fitness)
  {
    hillClimbingManager.finalize(fitness);
    currentHeuristic = HeuristicMode.GENETICALGORITHM;
  }

  public enum MutationType
  {
    HILL, GENETIC, GENETICHILL
  }

  private enum HeuristicMode
  {
    HILLCLIMB, GENETICALGORITHM
  }

}
