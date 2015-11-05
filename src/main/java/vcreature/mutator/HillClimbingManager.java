package vcreature.mutator;


import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;
import vcreature.mainSimulation.MainSim;
import vcreature.mutator.hillclimbing.Mutators;

import java.util.LinkedList;

/**
 * This will manage finding mutated genomes, from hill climbing.
 */
class HillClimbingManager
{
  private Genome parentGenome;
  private Genome currentTestee = null;

  private LinkedList<Genome> testQueue = new LinkedList<>();


  /**
   * This returns the next mutant based on the current creature we are hill
   * climbing from.
   *
   * @param lastFitness the calculated fitness in meters.
   *                    If this is -1, this means we are just starting and
   *                    need to test the seed.
   * @return the next genome to test.
   */
  public synchronized Genome getNextCreature(float lastFitness)
  {
    if (lastFitness == -1 || currentTestee == null)
    {
      buildQueue(GenePool.getWorst());
    }
    else
    {
      finalize(lastFitness);
    }
    if (testQueue.isEmpty())
    {
      if (MainSim.RANDOM.nextBoolean())
      {
        buildQueue(GenePool.getWorst());
      }
      else
      {
        buildQueue(GenePool.getOneOfTheBest());
      }
    }
    currentTestee = testQueue.poll();

    return currentTestee;
  }

  private void buildQueue(Genome genome)
  {
    parentGenome = genome;
    testQueue = new LinkedList<>();
    for (Mutators mutator : Mutators.values())
    {
      testQueue.add(mutator.mutate(genome));
    }
  }

  /**
   * Get fitness of what a creature has to meet
   *
   * @return getting the bar of what are fitness has to meet
   */
  public float getFitnessBar()
  {
    return parentGenome.getFitness();
  }

  /**
   * Determins what happens what to the creature when it is being tested,
   * by finalizing the results. If creature is better it will replace parents, or
   * if parent two is weaker it will replace it with a random creature
   *
   * @param lastFitness the last fitness of a genome
   */
  public void finalize(float lastFitness)
  {
    currentTestee.setFitness(lastFitness);
    if (lastFitness > parentGenome.getFitness())
    {
      GenePool.replace(currentTestee, parentGenome);
      buildQueue(currentTestee);
    }
  }
}

