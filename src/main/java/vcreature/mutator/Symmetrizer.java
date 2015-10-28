package vcreature.mutator;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.Genome;

/**
 * Symmetrizer (real word) makes a creature or block symmetrical.
 */
public class Symmetrizer
{
  public static Genome Sysmmetrize(Genome genome)
  {
    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    return newGenome;
  }

}
