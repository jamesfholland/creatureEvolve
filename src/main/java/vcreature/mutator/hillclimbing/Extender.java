package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tyler on 11/3/2015.
 */
public class Extender
{
  protected static Genome extendLimbs(Genome genome)
  {

    float min = .5f;
    float max = 3f;

    if(genome.getGENE_BLOCKS().size() == 0) return genome;
    GeneBlock limb;
    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;
    Random rand = new Random();
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();


    int xSign = (rand.nextBoolean()) ? 1 : -1;
    int ySign =(rand.nextBoolean()) ? 1 : -1;
    int zSign = (rand.nextBoolean()) ? 1 : -1;

//    ImmutableVector randPivot= new ImmutableVector(1, 0, 0);;
//
//    int randomFace=rand.nextInt(2);
//    if(randomFace==0) randPivot=new ImmutableVector(xSign,ySign,zSign*rand.nextFloat());
//    else if(randomFace==1)randPivot=new ImmutableVector(xSign*rand.nextFloat(),ySign,zSign);
//    else if(randomFace==2) randPivot=new ImmutableVector(xSign,ySign*rand.nextFloat(),zSign);

    int picker = rand.nextInt(3);
    int randomLimb = rand.nextInt(geneBlocks.size());
    float newSize;

    float recalculatedSizeX = geneBlocks.get(randomLimb).SIZE.getX() * rand.nextFloat() * 2;
    float recalculatedSizeY = geneBlocks.get(randomLimb).SIZE.getY() * rand.nextFloat() * 2;
    float recalculatedSizeZ = geneBlocks.get(randomLimb).SIZE.getZ() * rand.nextFloat() * 2;

//    ImmutableVector parentPivot=new ImmutableVector(-randPivot.X,-randPivot.Y,-randPivot.Z);
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
    int parentOffset = block.PARENT_OFFSET;
    ImmutableVector randAngle=new ImmutableVector(0,0,0);//new ImmutableVector(rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2);
//    randomFace = rand.nextInt(2);
//    if(randomFace==0)
//    {
//      limb = new GeneBlock(parentOffset, randPivot, parentPivot, sizeCopy,
//          Axis.UNIT_Z.getImmutableVector(), Axis.UNIT_Z.getImmutableVector(),
//          randAngle);
//    }
//    else
//    {
//      limb=new GeneBlock(parentOffset, randPivot,parentPivot,sizeCopy,Axis.UNIT_X.getImmutableVector(),Axis.UNIT_X.getImmutableVector(),randAngle);
//    }
    //limb=new GeneBlock(parentOffset, randPivot,parentPivot,sizeCopy, Axis.UNIT_Z.getImmutableVector(),Axis.UNIT_Z.getImmutableVector(),randAngle);


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
