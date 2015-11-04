package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

import java.util.ArrayList;
import java.util.Random;

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
    Random rand = new Random();
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    int picker = rand.nextInt(3);
    int randomLimb = rand.nextInt(geneBlocks.size());

    float recalculatedSizeX = geneBlocks.get(randomLimb).SIZE.getX() * rand.nextFloat() * 2;
    float recalculatedSizeY = geneBlocks.get(randomLimb).SIZE.getY() * rand.nextFloat() * 2;
    float recalculatedSizeZ = geneBlocks.get(randomLimb).SIZE.getZ() * rand.nextFloat() * 2;

    ImmutableVector newSizeX =new ImmutableVector(recalculatedSizeX,geneBlocks.get(randomLimb).SIZE.getY(),geneBlocks.get(randomLimb).SIZE.getZ());
    ImmutableVector newSizeY =new ImmutableVector(geneBlocks.get(randomLimb).SIZE.getX(),recalculatedSizeY,geneBlocks.get(randomLimb).SIZE.getZ());
    ImmutableVector newSizeZ =new ImmutableVector(geneBlocks.get(randomLimb).SIZE.getX(),geneBlocks.get(randomLimb).SIZE.getY(),recalculatedSizeZ);

    GeneBlock block = geneBlocks.get(randomLimb);
    if(picker == 0 && (recalculatedSizeX > .5f && (recalculatedSizeX * 10 > geneBlocks.get(randomLimb).SIZE.getY() || recalculatedSizeX * 10 > geneBlocks.get(randomLimb).SIZE.getZ())))
    {
      block =
          new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
              newSizeX, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
              block.EULER_ANGLES);
    }
    else if(picker == 1 && geneBlocks.get(randomLimb).SIZE.getX()> .5f && (recalculatedSizeY * 10 > geneBlocks.get(randomLimb).SIZE.getX() || recalculatedSizeY * 10 > geneBlocks.get(randomLimb).SIZE.getZ()))
    {
      block =
          new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
              newSizeY, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
              block.EULER_ANGLES);
    }
    else if(picker == 2  && geneBlocks.get(randomLimb).SIZE.getX()> .5f && (recalculatedSizeZ * 10 > geneBlocks.get(randomLimb).SIZE.getY() || recalculatedSizeZ * 10 > geneBlocks.get(randomLimb).SIZE.getX()))
    {
      block =
          new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
              newSizeZ, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
              block.EULER_ANGLES);
    }
    else
    {
      return genome;
    }
    geneBlocks.remove(randomLimb);
    geneBlocks.add(randomLimb, block);


    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    for (int i = 0; i <geneBlocks.size() ; i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));

    }
    for (int j = 0; j <geneNeurons.size() ; j++)
    {
      newGenome.addGeneNeuron(geneNeurons.get(j));
    }
    if(checkForIntersections(newGenome)) genome=newGenome;
    else extendLimbs(genome);
    return newGenome;
  }

  private static  boolean checkForIntersections(Genome genome)
  {
    ArrayList<GeneBlock> geneBlocks;
    geneBlocks=genome.getGENE_BLOCKS();

    for (int i = 0; i < geneBlocks.size(); i++)
    {

    }
    return true;
  }
}
