package vcreature.genotype;

import vcreature.phenotype.Block;

import java.util.ArrayList;

/**
 * GenoTools has utility methods for dealing genomes.
 */
public class GenoTools
{
  /**
   * Returns a leg (appendage) starting at an index.
   *
   * @param genome Genome we are parsing the leg from.
   * @param index  Start of the leg.
   * @return Gene with recursive children containing the leg
   */
  public static Gene getAppendage(Genome genome, int index)
  {
    ArrayList<GeneBlock> blocks = genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> neurons = genome.getGENE_NEURONS();

    Gene parent = new Gene(blocks, neurons, index);
    ArrayList<GeneNeuron> childNeurons = new ArrayList<>();
    for (int i = 0; i < blocks.size(); i++)
    {

      if (i + blocks.get(i).PARENT_OFFSET == index)
      {

        for (int j = 0; j < neurons.size(); j++)
        {
          if (neurons.get(j).BLOCK_INDEX == i)
          {
            childNeurons.add(neurons.get(j));
          }
        }
        Gene gene = new Gene(blocks, childNeurons, i);
        parent.children.add(gene);
        //parent.children.add(getAppendage(genome, i));
      }
    }
    return parent;
  }

  public static boolean isNotValidBlockSize(ImmutableVector size)
  {
    if (size.X < 0.501f || size.Y < 0.501f || size.Z < 0.501f)
    {
      return true;
    }
    return (Block.max(size.getVector3f())) > (Block.min(size.getVector3f()) * 9.99);

  }

  /**
   * Inverts X and Z components of a ImmutableVector
   * @param immutableVector vector we are inverting
   * @return new vector with inversions completed.
   */
  public static ImmutableVector invertXZ(ImmutableVector immutableVector)
  {
    return new ImmutableVector(-1 * immutableVector.X, immutableVector.Y, -1 * immutableVector.Z);
  }
}
