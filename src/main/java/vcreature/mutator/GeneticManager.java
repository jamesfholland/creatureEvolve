package vcreature.mutator;

import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;
import vcreature.mutator.genetic.MergeType;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Manages genetic merger classes. Will choose which class to use in different situations
 */
public class GeneticManager
{
  private GenomeTracker currentTestee = null;
  private LinkedList<GenomeTracker> testQueue;
  private MergeType mergeType;

  GeneticManager()
  {
    testQueue = new LinkedList<>();
    mergeType = MergeType.CUTANDSPLICE;
  }

  public Genome getNextGenome(float lastFitness)
  {
    if(lastFitness == -1 || testQueue.isEmpty())
    {
      buildQueue(GenePool.getBest());
    }
    else
    {
      currentTestee.genome.setFitness(lastFitness);
      if (lastFitness > currentTestee.parent1.getFitness() && lastFitness > currentTestee.parent2.getFitness())
      {
        GenePool.replace(currentTestee.genome, currentTestee.parent1, currentTestee.parent2);
        buildQueue(currentTestee.genome);
      }
    }
    currentTestee = testQueue.poll();

    return currentTestee.genome;
  }

  private void buildQueue(Genome parent)
  {

    mergeType = mergeType.next(); //Cycle through mergeTypes.
    LinkedList<Genome> pool = GenePool.getCopy();
    for(Genome mate : pool)
    {
      ArrayList<Genome> children = mergeType.merge(parent, mate);
      for(Genome child : children)
      {
        testQueue.offer(new GenomeTracker(child, parent, mate));
      }
    }
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
