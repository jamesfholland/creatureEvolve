package vcreature.mainSimulation;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;

import java.util.LinkedList;

/**
 * GenePool manages the list of creatures that exist.
 * Upon initialization it reads all genomes in GenePool folder
 */
public class GenePool
{
  private static LinkedList<Genome> genomes;
  static
  {
    genomes = GenoFile.loadGenePool();
  }

  public static Genome getRandom()
  {
    synchronized (genomes)
    {
      return genomes.getFirst();
    }
  }


}
