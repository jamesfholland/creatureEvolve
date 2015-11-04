package vcreature.mainSimulation;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.genotype.GenomeComparator;

import java.util.LinkedList;
import java.util.Random;

/**
 * GenePool manages the list of creatures that exist.
 * Upon initialization it reads all genomes in GenePool folder
 */
public class GenePool
{
  /**
   * Don't change from private, this is synchronized.
   * Get a copy or add a synchronized method to do what you need.
   */
  private static final LinkedList<Genome> GENOMES;
  private static final int MINIMUM_POOL_SIZE = 1000;
  static
  {
    GENOMES = GenoFile.loadGenePool();
    while (GENOMES.size() < MINIMUM_POOL_SIZE)
    {
      synchronized (GENOMES)
      {
        GENOMES.add(SpawnRandomCreatureGenoform.createCreature(MainSim.RANDOM.nextInt(2)+2));
      }
    }
  }

  /**
   * Return a random Genome from the pool.
   * @return a Genome
   */
  public static Genome getRandom()
  {
    synchronized (GENOMES)
    {
      int index = Math.abs(MainSim.RANDOM.nextInt()) % GENOMES.size();
      return GENOMES.get(index);
    }
  }

  /**
   * Replace 2 parents with a child.
   * @param newGenome Genome to store
   * @param parent1 Genome to remove
   * @param parent2 Genome to remove
   */
  public static void replace(Genome newGenome, Genome parent1, Genome parent2)
  {
    synchronized (GENOMES)
    {
      GENOMES.remove(parent1);
      GENOMES.remove(parent2);
      GENOMES.add(newGenome);
    }
  }

  /**
   * Replace a single parent with a child Genome
   * @param newGenome Genome to store
   * @param parent Genome to replace
   */
  public static void replace(Genome newGenome, Genome parent)
  {
    synchronized (GENOMES)
    {
      GENOMES.remove(parent);
      GENOMES.add(newGenome);
    }
  }

  /**
   * Adds a Genome to the pool
   * @param newGenome Genome to add
   */
  public static void add(Genome newGenome)
  {
    synchronized (GENOMES)
    {
      GENOMES.add(newGenome);
    }
  }

  /**
   * Sorts the GenePool.
   * Returns the best creature in the pool.
   * @return a genome.
   */
  public static Genome getBest()
  {
    synchronized (GENOMES)
    {
      GENOMES.sort(new GenomeComparator());
      return GENOMES.getLast();
    }
  }

  /**
   * Returns a copy of the GenePool.
   * @return The GenePool as a LinkedList.
   */
  public static LinkedList<Genome> getCopy()
  {
    synchronized (GENOMES)
    {
      return new LinkedList<Genome>(GENOMES);
    }
  }

  /**
   * Returns the current size of the pool
   * @return number of elements in the pool
   */
  public static int getPoolSize()
  {
    synchronized (GENOMES)
    {
      return GENOMES.size();
    }
  }

  /**
   * Get the worst Genome in the pool
   * @return worst Genome
   */
  public static Genome getWorst()
  {
    synchronized (GENOMES)
    {
      GENOMES.sort(new GenomeComparator());
      return GENOMES.getFirst();
    }
  }
}
