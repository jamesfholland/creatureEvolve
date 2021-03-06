package vcreature.mutator;

import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;
import vcreature.mainSimulation.SpawnRandomCreatureGenoform;
import vcreature.mutator.genetic.MegaMutate;
import vcreature.mutator.genetic.MergeType;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Manages genetic merger classes. Will choose which class to use in
 * different situations.
 */
class GeneticManager
{
  private GenomeTracker currentTestee = null;
  private LinkedList<GenomeTracker> testQueue;
  private Genome bestChild;

  GeneticManager()
  {
  }

  public Genome getNextGenome(float lastFitness)
  {
    if (lastFitness == -1 || currentTestee == null)
    {
      buildQueue(GenePool.getBest());
    }
    else
    {
      finalize(lastFitness);
    }
    if (testQueue.isEmpty())
    {
      buildQueue(GenePool.getOneOfTheBest());
    }
    currentTestee = testQueue.poll();
    return currentTestee.GENOME;
  }

  private void buildQueue(Genome parent)
  {
    if (bestChild != null && currentTestee.PARENT2.getFitness() < bestChild.getFitness())
    {
      GenePool.replace(bestChild, currentTestee.PARENT2);
    }

    bestChild = MegaMutate.megaMutate(GenePool.getOneOfTheBest());
    bestChild.mergeMutationType = "MegaMutate";

    testQueue = new LinkedList<>();
    Genome mate = GenePool.getOneOfTheWorst();
    for (MergeType mergeType : MergeType.values())
    {
      ArrayList<Genome> children = mergeType.merge(parent, mate);
      for (Genome child : children)
      {
        Genome cleanChild = Cleaner.cleanGenome(child);
        cleanChild.mergeMutationType = mergeType.name();
        testQueue.offer(new GenomeTracker(cleanChild, parent, mate));
      }
    }
  }

  /**
   * returns the minimum fitness between two parents
   *
   * @return minimum fitness between two parents.
   */
  public float getFitnessBar()
  {
    return Math.min(currentTestee.PARENT1.getFitness(), currentTestee.PARENT2.getFitness());
  }

  /**
   * Determins what happens what to the creature when it is being tested,
   * by finalizing the results. If creature is better it will replace parents, or
   * if parent two is weaker it will replace it with a random creature
   *
   * @param lastFitness the last fitness that a creature had
   */
  public void finalize(float lastFitness)
  {
    currentTestee.GENOME.setFitness(lastFitness);

  //For analysis
   /* System.out.println(System.currentTimeMillis()
                           + "," +currentTestee.GENOME.mergeMutationType+","
                           + currentTestee.GENOME.getFileName()+","+currentTestee.PARENT1.getFileName()
                           + "," + currentTestee.PARENT2.getFileName());
  */
    if (lastFitness > currentTestee.PARENT1.getFitness() &&
        lastFitness > currentTestee.PARENT2.getFitness())
    {
      GenePool.replace(currentTestee.GENOME, currentTestee.PARENT1,
                       currentTestee.PARENT2);

      GenePool.add(SpawnRandomCreatureGenoform.createRandomCreature(4));
      buildQueue(currentTestee.GENOME);
    }
    else if (lastFitness > currentTestee.PARENT2.getFitness())
    {
      if (bestChild.getFitness() < lastFitness)
      {
        bestChild = currentTestee.GENOME;
      }
    }
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
