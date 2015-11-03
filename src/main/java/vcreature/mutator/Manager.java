package vcreature.mutator;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;

/**
 * This class handles switching between Genetics and HillClimbing.
 */
public class Manager
{

  private float switchingThreshhold = 0.001f;

  private long timeStart;
  private float startFitness;

  private Genome currentGenome;
  private boolean repeat = false;
  private float firstTest;

  private MutationType currentMutationType = MutationType.GENETIC;

  private GeneticManager geneticManager;
  private HillClimbingManager hillClimbingManager;

  public Manager()
  {
    initializeGenetics();
    initializeHillClimbing();

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
    return this.currentGenome;
  }

  public Genome getNextCreature(float lastFitness)
  {
    float worstTest;
    if(repeat)
    {
      firstTest = lastFitness;
      //Don't get new Genome we will test again.
    }
    else
    {
      worstTest = Math.min(lastFitness, firstTest);

      switch(currentMutationType)
      {
        case GENETIC:
          currentGenome = geneticManager.getNextGenome(worstTest);
          break;
        case HILL:
          currentGenome = hillClimbingManager.getNextCreature(worstTest);
          break;
      }

    }

    repeat = !repeat;
    return currentGenome;
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
