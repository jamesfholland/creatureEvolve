package vcreature.mutator.geneticMerger;

import vcreature.genotype.Genome;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages genetic merger classes. Will choose which class to use in different situations
 */
public class GeneticManager
{
  private LinkedList<GenomeTracker> testQueue;
  GeneticManager()
  {
    testQueue = new LinkedList<>();
  }


class GenomeTracker
{
  private Genome parent1;
  private Genome parent2;
  private Genome genome;
}
}
