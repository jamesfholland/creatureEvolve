package vcreature.mutator;

import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;

/**
 * This class handles switching between Genetics and HillClimbing.
 */
public class Manager
{
  private float switchingThreshhold = 0.001f;

  private long startTime;

  private Genome currentGenome;
  private boolean repeated = false;
  private float firstTest;
  private float fitnessBar;

  private MutationType currentMutationType = MutationType.GENETICHILL;
  private HeuristicMode currentHeuristic = HeuristicMode.HILLCLIMB;

  private GeneticManager geneticManager;
  private HillClimbingManager hillClimbingManager;
  private float deltaFitness = 0.0f;

  public Manager()
  {
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
        //Add our change in fitness, unless its parent was an untested genome.
        if(fitnessBar != -1)
        {
          deltaFitness += minFitness - fitnessBar;
        }
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
  }

  private float determineSwitch(float fitness)
  {
    float returnFitness = fitness;
    switch(currentMutationType)
    {
      //Forced HillClimbing
      case HILL:
        switch(currentHeuristic)
        {
          case GENETICALGORITHM:
            switchToHILL(fitness);
            returnFitness = -1;
            break;
        }
      //Force Genetic Algorithm
      case GENETIC:
        switch(currentHeuristic)
        {
          case HILLCLIMB:
            switchToGENETICALG(fitness);
            returnFitness = -1;
        }
      //Switching permitted
      case GENETICHILL:
      {
        switch(currentHeuristic)
        {
          case GENETICALGORITHM:
            if((System.currentTimeMillis() - startTime) > 2000*60)
            {
              switchToHILL(fitness);
              returnFitness = -1;
            }
            break;
          case HILLCLIMB:
            if(this.getFitnessPerMinute() <= this.switchingThreshhold
                && (System.currentTimeMillis() - startTime) > 2000*60)
            {
              switchToGENETICALG(fitness);
              returnFitness = -1;
            }
            break;
        }

      }
    }
    //We switched now reset time.
    if(returnFitness == -1)
    {
      System.out.println("Switched to " + currentHeuristic.name());
      deltaFitness = 0;
      startTime = System.currentTimeMillis();
    }

    return returnFitness;
  }

  public float getFitnessPerMinute()
  {
    return (deltaFitness)*1000/(System.currentTimeMillis() - startTime);
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
