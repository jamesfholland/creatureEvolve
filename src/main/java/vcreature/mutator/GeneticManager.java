package vcreature.mutator;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;
import vcreature.mainSimulation.SpawnCreatureGenoform;
import vcreature.mainSimulation.SpawnRandomCreatureGenoform;
import vcreature.mutator.genetic.MergeType;
import vcreature.mutator.hillclimbing.Symmetrizer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Manages genetic merger classes. Will choose which class to use in
 * different situations
 */
public class GeneticManager
{
  private GenomeTracker currentTestee = null;
  private LinkedList<GenomeTracker> testQueue;
  private MergeType mergeType;

  GeneticManager()
  {
    mergeType = MergeType.CUTANDSPLICE;
  }

  public Genome getNextGenome(float lastFitness)
  {

    if (lastFitness == -1)
    {
      buildQueue(GenePool.getBest());
    }
    else
    {
      currentTestee.genome.setFitness(lastFitness);
      if (lastFitness > currentTestee.parent1.getFitness() &&
          lastFitness > currentTestee.parent2.getFitness())
      {
        System.out.println("Better Child replacing parents parent1,2: "
            + currentTestee.parent1.getFitness() + ","
            + currentTestee.parent2.getFitness() + "fitness: " + lastFitness);
        GenePool.replace(currentTestee.genome, currentTestee.parent1,
            currentTestee.parent2);

        GenePool.add(SpawnRandomCreatureGenoform.createRandomCreature(4));
        buildQueue(currentTestee.genome);
        GenoFile.writeGenome(currentTestee.genome);
      }
      //Parent1 is currently the best so not bothering checking.
      else if(lastFitness > currentTestee.parent2.getFitness() && currentTestee.parent2.getFitness() != -1)
      {
        System.out.println("Better Child replacing weaker parent2: "
                               + currentTestee.parent2.getFitness() + "fitness: " + lastFitness);
        GenePool.replace(currentTestee.genome, currentTestee.parent2);
      }

    }
    if (testQueue.isEmpty())
    {
      mergeType = mergeType.next(); //Cycle through mergeTypes.
      System.out.println("QueueEmpty Building another from random");
      buildQueue(GenePool.getRandom());
    }
    currentTestee = testQueue.poll();
    System.out.println("parent1,2: "
                           + currentTestee.parent1.getFitness() + ","
                           + currentTestee.parent2.getFitness() + "fitness: " + lastFitness);
    return currentTestee.genome;
  }

  private void buildQueue(Genome parent)
  {
    testQueue = new LinkedList<>();
    System.out
        .println("Building Queue GenePool Size: " + GenePool.getPoolSize());
    LinkedList<Genome> pool = GenePool.getCopy();
    for (Genome mate : pool)
    {
      ArrayList<Genome> children = mergeType.merge(parent, mate);
      for (Genome child : children)
      {
        Genome symChild = Symmetrizer.basicSymmetrize(child);
        testQueue.offer(new GenomeTracker(symChild, parent, mate));
        testQueue.offer(new GenomeTracker(child, parent, mate));
      }
    }

    System.out.println("TestQueue size: " + testQueue.size());
  }

  public float getFitnessBar()
  {
    return Math.min(currentTestee.parent1.getFitness(), currentTestee.parent2.getFitness());
  }


  class GenomeTracker
  {
    public Genome parent1;
    public Genome parent2;
    public Genome genome;

    GenomeTracker(Genome child, Genome parent1, Genome parent2)
    {
      this.genome = child;
      this.parent1 = parent1;
      this.parent2 = parent2;
    }
  }
}
