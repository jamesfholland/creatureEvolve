package vcreature.mutator;

import vcreature.genotype.Genome;

/**
 * Cleaner cleans genomes by removing genes that are not present in the phenome AKA recessive genes.
 */
public class Cleaner
{
  public static Genome cleanGenome(Genome genome)
  {
    Genome cleanGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());




    return cleanGenome;
  }


}
