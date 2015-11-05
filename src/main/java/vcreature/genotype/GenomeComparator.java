package vcreature.genotype;


import java.util.Comparator;

/**
 * GenomeComparator compares Genomes based on their fitness.
 */
public class GenomeComparator implements Comparator<Genome>
{
  /**
   * A comparator method that compares multiple genomes
   *
   * @param other1 A genome
   * @param other2 A genome
   * @return which genome is better
   */
  @Override
  public int compare(Genome other1, Genome other2)
  {
    return Float.compare(other1.getFitness(), other2.getFitness());
  }
}
