
package vcreature.mutator;
import vcreature.genotype.*;

import java.util.ArrayList;

/**
 * Created by Tyler on 10/28/2015.
 */
public class ScaleSingleBlock
{

  private static ArrayList<GeneBlock> geneBlocks;
  private static ArrayList<GeneNeuron> geneNeurons;

  private static GeneBlock scaledBlock;
  private static GeneBlock block;
  private static ImmutableVector scaledSize;
  private static Genome newGenome;

  protected static Genome scaleBlock(Genome genome, float scale)
  {
    ImmutableVector rootSize = genome.getRootSize();
    scaledSize = new ImmutableVector(rootSize.getX() * scale, rootSize.getY() * scale, rootSize.getZ() * scale);
    newGenome = new Genome(scaledSize, genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    for (int i = 0; i < geneBlocks.size(); i++)
    {
      System.out.println(i);
      block = geneBlocks.get(i);
      scaledSize = new ImmutableVector(block.SIZE.getX(), block.SIZE.getY(), block.SIZE.getZ());
      scaledBlock = new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT, scaledSize, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
          block.EULER_ANGLES);
      geneBlocks.set(i, scaledBlock);
      newGenome.addGeneBlock(scaledBlock);
      for(GeneNeuron geneNeuron: geneNeurons)
      {
        if(geneNeuron.BLOCK_INDEX==i) newGenome.addGeneNeuron(geneNeuron);
      }

    }


    return newGenome;
  }
}
