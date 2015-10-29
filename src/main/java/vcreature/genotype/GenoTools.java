package vcreature.genotype;

import java.util.ArrayList;

/**
 * GenoTools has utility methods for dealing genomes.
 */
public class GenoTools
{
  /**
   * Returns a leg (appendage) starting at an index.
   * @param genome Genome we are parsing the leg from.
   * @param index Start of the leg.
   * @return Gene with recursive children containing the leg
   */
  public static Gene getAppendage(Genome genome, int index)
  {
    ArrayList<GeneBlock> blocks = genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> neurons = genome.getGENE_NEURONS();

    Gene parent = new Gene(blocks, neurons, index);

    for(int i = 0; i < blocks.size(); i++)
    {
      if(i + blocks.get(i).PARENT_OFFSET == index)
      {
        parent.children.add(getAppendage(genome, i));
      }
    }



    return parent;
  }

}
