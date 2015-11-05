package vcreature.mainSimulation;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.genotype.GenomeComparator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

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
  private static final int MINIMUM_POOL_SIZE = 100;
  private static final int BEST_CUTOFF = 10;
  private static final Thread GENEMANAGER;

  static
  {
    GENOMES = GenoFile.loadGenePool();
    synchronized (GENOMES)
    {
      while (GENOMES.size() < MINIMUM_POOL_SIZE)
      {
        GENOMES.add(SpawnRandomCreatureGenoform.createRandomCreature(MainSim.RANDOM.nextInt(2) + 2));
      }
      GENOMES.sort(new GenomeComparator());
    }

    GENEMANAGER = new Thread(new GeneManager());
    GENEMANAGER.start();
  }

  /**
   * Return a random Genome from the pool.
   *
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
   *
   * @param newGenome Genome to store
   * @param parent1   Genome to remove
   * @param parent2   Genome to remove
   */
  public static void replace(Genome newGenome, Genome parent1, Genome parent2)
  {
    synchronized (GENOMES)
    {
      GENOMES.remove(parent1);
      GENOMES.remove(parent2);
      GenePool.add(newGenome);
    }

  }

  /**
   * Replace a single parent with a child Genome
   *
   * @param newGenome Genome to store
   * @param parent    Genome to replace
   */
  public static void replace(Genome newGenome, Genome parent)
  {
    synchronized (GENOMES)
    {
      GENOMES.remove(parent);
      GenePool.add(newGenome);
    }
  }

  /**
   * Adds a Genome to the pool
   *
   * @param newGenome Genome to add
   */
  public static void add(Genome newGenome)
  {
    synchronized (GENOMES)
    {
      GENOMES.add(newGenome);
      GENOMES.sort(new GenomeComparator());
    }
  }

  /**
   * Sorts the GenePool.
   * Returns the best creature in the pool.
   *
   * @return a genome.
   */
  public static Genome getBest()
  {
    synchronized (GENOMES)
    {
      return GENOMES.getLast();
    }
  }

  /**
   * Returns a copy of the GenePool.
   *
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
   *
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
   *
   * @return worst Genome
   */
  public static Genome getWorst()
  {
    synchronized (GENOMES)
    {
      return GENOMES.getFirst();
    }
  }

  /**
   * Gets one of the top 10 creatures
   * @return random top 10 creaute
   */
  public static Genome getOneOfTheBest()
  {

    synchronized (GENOMES)
    {
      if (MainSim.RANDOM.nextBoolean() && MainSim.RANDOM.nextBoolean())
      {
        return GENOMES.getLast();
      }
      return GENOMES.get(GENOMES.size() - MainSim.RANDOM.nextInt(BEST_CUTOFF) - 1);
    }
  }

  /**
   * Get the bottom 90 creatures
   * @return random bottom 90
   */
  public static Genome getOneOfTheWorst()
  {
    synchronized (GENOMES)
    {
      return GENOMES.get(MainSim.RANDOM.nextInt(GENOMES.size() - BEST_CUTOFF));
    }
  }

  private static void hourlyUpdate()
  {
    GenoFile.writeGenome(GenePool.getBest());
  }

  private static void minutelyUpdate()
  {
    synchronized (GENOMES)
    {
      System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                             + ": The best creature: " + GenePool.getBest().getFitness());
    }
  }

  private static class GeneManager implements Runnable
  {
    private static int minutes = 0;
    private static boolean interrupted = false;

    @Override
    public void run()
    {
      while (!interrupted)
      {
        try
        {
          Thread.sleep(60000); //one minute
          minutes++;
          minutelyUpdate();
          if (minutes > 60)
          {
            hourlyUpdate();
            minutes = 0;
          }
        }
        catch (InterruptedException e)
        {
          interrupted = true;
        }
      }
    }
  }
}
