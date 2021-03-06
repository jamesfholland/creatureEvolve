package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Scales a entire Genome random amount.
 */
public class Scaler
{
  /**
   * This will take a genome and scale it. The max that it can scaled is double itself.
   * it can also shrink it.
   *
   * @param genome a genome of a current creature that you want to change
   * @return a new genome that has been scaled
   */
  public static Genome scale(Genome genome)
  {
    Random rand = new Random();
    float scaler;
    scaler = rand.nextFloat();
    scaler = scaler * 2;
    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;
    GeneBlock scaledBlock;
    ImmutableVector scaledSize;
    Genome newGenome;


    ImmutableVector rootSize = genome.getRootSize();
    scaledSize = new ImmutableVector(rootSize.X * scaler, rootSize.Y * scaler, rootSize.Z * scaler);
    if (GenoTools.isNotValidBlockSize(scaledSize))
    {
      scaledSize = genome.getRootSize(); //Revert to old size, it should be valid.
    }


    newGenome = new Genome(scaledSize, genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    for (GeneBlock block : geneBlocks)
    {

      scaledSize = new ImmutableVector(block.SIZE.X * scaler, block.SIZE.Y * scaler, block.SIZE.Z * scaler);
      scaledBlock = new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT, scaledSize, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
                                  block.EULER_ANGLES);
      if (GenoTools.isNotValidBlockSize(scaledBlock.SIZE))
      {
        scaledBlock = block; //Keep old block if invalid
      }
      newGenome.addGeneBlock(scaledBlock);
    }
    for (int i = geneNeurons.size() - 1; i >= 0; i--)
    {
      newGenome.addGeneNeuron(geneNeurons.get(i));
    }
    return newGenome;
  }
}
