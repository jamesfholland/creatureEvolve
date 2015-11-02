package vcreature.genotype;


import java.util.Comparator;

/**
 * GenomeComparator compares Genomes based on their fitness.
 */
public class GenomeComparator implements Comparator<Genome>
{
  @Override
  public int compare(Genome other1, Genome other2)
  {
    return Float.compare(other1.getFitness(), other2.getFitness());
  }
}
