package vcreature.mainSimulation;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;

import java.util.LinkedList;
import java.util.Random;

/**
 * GenePool manages the list of creatures that exist.
 * Upon initialization it reads all genomes in GenePool folder
 */
public class GenePool
{
  private static LinkedList<Genome> genomes;
  private static Random rand=new Random();
  static
  {
    genomes = GenoFile.loadGenePool();
  }

  public static Genome getRandom()
  {
    synchronized (genomes)
    {
      int index = rand.nextInt() % genomes.size();
      return genomes.get(index);
    }
  }


}
