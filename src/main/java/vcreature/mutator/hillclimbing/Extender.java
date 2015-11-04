package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;
import vcreature.mainSimulation.MainSim;

import java.util.ArrayList;

/**
 * Created by Tyler on 11/3/2015.
 */

/**
 * Takes a block and extends or shortens one of the following to a block:
 *  -Length
 *  -Width
 *  -Height
 */
public class Extender
{

  /**
   * This will take a genome and then choose a random block from the genome. It will
   * then adjust the height, width, or length of the random block that was choosen.
   * It has checks inside of it to make sure it stays within the bounds of the block.
   * @param genome a genome of a current creature that you want to change
   * @return a new genome with a slight change
   */
  protected static Genome extendLimbs(Genome genome)
  {
    if(genome.getGENE_BLOCKS().size() == 0) return genome;
    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    int picker = MainSim.RANDOM.nextInt(3);
    int randomLimb = MainSim.RANDOM.nextInt(geneBlocks.size());

    float recalculatedSizeX = geneBlocks.get(randomLimb).SIZE.getX() * MainSim.RANDOM.nextFloat() * 2;
    float recalculatedSizeY = geneBlocks.get(randomLimb).SIZE.getY() * MainSim.RANDOM.nextFloat() * 2;
    float recalculatedSizeZ = geneBlocks.get(randomLimb).SIZE.getZ() * MainSim.RANDOM.nextFloat() * 2;

    ImmutableVector newSizeX =new ImmutableVector(recalculatedSizeX,geneBlocks.get(randomLimb).SIZE.getY(),geneBlocks.get(randomLimb).SIZE.getZ());
    ImmutableVector newSizeY =new ImmutableVector(geneBlocks.get(randomLimb).SIZE.getX(),recalculatedSizeY,geneBlocks.get(randomLimb).SIZE.getZ());
    ImmutableVector newSizeZ =new ImmutableVector(geneBlocks.get(randomLimb).SIZE.getX(),geneBlocks.get(randomLimb).SIZE.getY(),recalculatedSizeZ);

    GeneBlock block = geneBlocks.get(randomLimb);
    if(picker == 0 )
    {
      block =
          new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
              newSizeX, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
              block.EULER_ANGLES);
    }
    else if(picker == 1)
    {
      block =
          new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
              newSizeY, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
              block.EULER_ANGLES);
    }
    else if(picker == 2)
    {
      block =
          new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
              newSizeZ, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
              block.EULER_ANGLES);
    }

    if(GenoTools.isNotValidBlockSize(block.SIZE))
    {
      return genome;
    }
    geneBlocks.remove(randomLimb);
    geneBlocks.add(randomLimb, block);

    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    for (GeneBlock geneBlock : geneBlocks)
    {
      newGenome.addGeneBlock(geneBlock);

    }
    for (GeneNeuron geneNeuron : geneNeurons)
    {
      newGenome.addGeneNeuron(geneNeuron);
    }
    return newGenome;
  }
}
