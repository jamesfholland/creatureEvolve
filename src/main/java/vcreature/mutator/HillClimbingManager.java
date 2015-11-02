package vcreature.mutator;


import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;

import vcreature.genotype.TessMonster;
import vcreature.mainSimulation.*;
import vcreature.mutator.hillclimbing.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * This will manage finding mutated genomes.
 */
public class HillClimbingManager
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
   *                      If this is -1, this means we are just starting and
   *                      need to test the seed.
   * @return the next genome to test.
   */
  public synchronized Genome getNextCreature(float lastFitness)
  {
    if(lastFitness == -1 )
    {
      buildQueue(GenePool.getWorst());
    }
    else
    {
      currentTestee.setFitness(lastFitness);
      if (lastFitness > parentGenome.getFitness())
      {
        System.out.println("Better Child replacing parents parent1: "
                               + parentGenome.getFitness()+"fitness: " + lastFitness);
        GenePool.replace(currentTestee, parentGenome);
        buildQueue(GenePool.getWorst());
        GenoFile.writeGenome(currentTestee);
      }
    }
    if(testQueue.isEmpty())
    {
      System.out.println("QueueEmpty Building another from worst Creature");
      buildQueue(GenePool.getWorst());
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
}

