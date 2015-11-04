package vcreature.mutator;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;
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
      finalize(lastFitness);
    }
    if (testQueue.isEmpty())
    {
      mergeType = mergeType.next(); //Cycle through mergeTypes.
      buildQueue(GenePool.getRandom());
    }
    currentTestee = testQueue.poll();
    return currentTestee.GENOME;
  }

  private void buildQueue(Genome parent)
  {
    testQueue = new LinkedList<>();
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
  }

  public float getFitnessBar()
  {
    return Math.min(currentTestee.PARENT1.getFitness(), currentTestee.PARENT2.getFitness());
  }

  public void finalize(float lastFitness)
  {
    currentTestee.GENOME.setFitness(lastFitness);
    if (lastFitness > currentTestee.PARENT1.getFitness() &&
        lastFitness > currentTestee.PARENT2.getFitness())
    {
      System.out.println("Better Child replacing parents parent1,2: "
                             + currentTestee.PARENT1.getFitness() + ","
                             + currentTestee.PARENT2.getFitness() + "fitness: " + lastFitness);
      GenePool.replace(currentTestee.GENOME, currentTestee.PARENT1,
                       currentTestee.PARENT2);

      GenePool.add(SpawnRandomCreatureGenoform.createRandomCreature(4));
      buildQueue(currentTestee.GENOME);
      GenoFile.writeGenome(currentTestee.GENOME);
    }
    //Parent1 is currently the best so not bothering checking.
    //else if(lastFitness > currentTestee.PARENT2.getFitness() && currentTestee.PARENT2.getFitness() != -1)
    //{
      //System.out.println("Better Child replacing weaker parent2: "
      //                       + currentTestee.PARENT2.getFitness() + "fitness: " + lastFitness);
      //GenePool.replace(currentTestee.GENOME, currentTestee.PARENT2);
    //}
  }


  class GenomeTracker
  {
    public final Genome PARENT1;
    public final Genome PARENT2;
    public final Genome GENOME;

    GenomeTracker(Genome child, Genome parent1, Genome parent2)
    {
      this.GENOME = child;
      this.PARENT1 = parent1;
      this.PARENT2 = parent2;
    }
  }
}
