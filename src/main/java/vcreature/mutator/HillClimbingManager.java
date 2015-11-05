package vcreature.mutator;


import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;
import vcreature.mainSimulation.MainSim;
import vcreature.mutator.hillclimbing.Mutators;

import java.util.LinkedList;

/**
 * This will manage finding mutated genomes.
 */
class HillClimbingManager
{
  private Genome parentGenome;
  private Genome currentTestee = null;

  private LinkedList<Genome> testQueue = new LinkedList<>();

  /**
   * Sets up the mutation manager. Currently always seeds with FlappyBird.
   * In the future will be handed a GenEpoOl, that it picks a creature from.
   */
  public HillClimbingManager()
  {
  }

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
      if(MainSim.RANDOM.nextBoolean())buildQueue(GenePool.getWorst());
      else buildQueue(GenePool.getOneOfTheBest());
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

  public float getFitnessBar()
  {
    return parentGenome.getFitness();
  }

  public void finalize(float lastFitness)
  {
    currentTestee.setFitness(lastFitness);
    if (lastFitness > parentGenome.getFitness())
    {
      System.out.println("Better Child replacing parent parent: "
                             + parentGenome.getFitness() + "fitness: " + lastFitness);
      GenePool.replace(currentTestee, parentGenome);
      buildQueue(currentTestee);
    }
  }
}

