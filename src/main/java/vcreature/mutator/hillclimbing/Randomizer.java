package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class will randomize genomes or genes within valid specifications.
 */
public class Randomizer
{
  /**
   * Randomizes a creature, but keeps sizes below 5 meters.
   * @param genome genome to mutate
   * @return new genome
   */
  public static Genome randomize(Genome genome)
  {
    ArrayList<GeneNeuron> geneNeurons;
    ArrayList<GeneBlock> geneBlocks;

    Random rand=new Random();
    float min = .5f;
    float max = 3f;


    geneBlocks=genome.getGENE_BLOCKS();
    geneNeurons=genome.getGENE_NEURONS();
    int index=rand.nextInt(geneBlocks.size());

    GeneBlock block=geneBlocks.get(index);
    GeneBlock randBlock;
    float sizeX;
    float sizeY;
    float sizeZ;
    sizeX = rand.nextFloat() * (max - min) + min;
    sizeY = rand.nextFloat() * (max - min) + min;
    if(sizeX > 1f && sizeY > 1f)
    {
      sizeZ= rand.nextFloat()+min;
    }
    else
    {
      sizeZ = rand.nextFloat() * (max - min) + min;
    }
    ImmutableVector size=new ImmutableVector(sizeX,sizeY,sizeZ);
    ImmutableVector randAngle=new ImmutableVector(0,0,0);//new ImmutableVector(rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2);
    int parentOffset=block.PARENT_OFFSET;
    int xSign = (rand.nextBoolean()) ? 1 : -1;
    int ySign =(rand.nextBoolean()) ? 1 : -1;
    int zSign = (rand.nextBoolean()) ? 1 : -1;
    ImmutableVector randPivot= new ImmutableVector(1, 0, 0);;
    int randomFace=rand.nextInt(2);
    if(randomFace==0) randPivot=new ImmutableVector(xSign,ySign,zSign*rand.nextFloat());
    else if(randomFace==1)randPivot=new ImmutableVector(xSign*rand.nextFloat(),ySign,zSign);
    else if(randomFace==2) randPivot=new ImmutableVector(xSign,ySign*rand.nextFloat(),zSign);
    ImmutableVector parentPivot=new ImmutableVector(-randPivot.X,-randPivot.Y,-randPivot.Z);


    randomFace=rand.nextInt(2);
    if(randomFace==0)
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
          Axis.UNIT_Z.getImmutableVector(), Axis.UNIT_Z.getImmutableVector(),
          randAngle);
    }
    else if(randomFace==1)
    {
      randBlock=new GeneBlock(parentOffset, randPivot,parentPivot,size,Axis.UNIT_X.getImmutableVector(),Axis.UNIT_X.getImmutableVector(),randAngle);
    }
    else
    {
      randBlock=new GeneBlock(parentOffset, randPivot,parentPivot,size,Axis.UNIT_Y.getImmutableVector(),Axis.UNIT_Y.getImmutableVector(),randAngle);
    }
    geneBlocks.remove(index);
    geneBlocks.add(index,randBlock);
    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    for (int i = 0; i <geneBlocks.size() ; i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));
      for (int j = 0; j <geneNeurons.size() ; j++)
      {
        if(geneNeurons.get(j).BLOCK_INDEX==i)newGenome.addGeneNeuron(geneNeurons.get(j));
      }
    }
    if(checkForIntersections(newGenome)) genome=newGenome;
    else randomize(genome);
    return newGenome;
  }

  public static Genome randomizeNeuron(Genome genome,int index)
  {
    ArrayList<GeneBlock> geneBlocks;
    Random rand = new Random();
    ArrayList<GeneNeuron> geneNeurons;

    Genome newGenome =new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    geneBlocks=genome.getGENE_BLOCKS();
    geneNeurons=genome.getGENE_NEURONS();

    int sign = (rand.nextBoolean()) ? 1 : -1;
    ImmutableVector pivot=geneBlocks.get(index).PIVOT;
   // if(Math.abs(pivot.X)==1) sign=(int)pivot.X;
    //else if(Math.abs(pivot.Z)==1) sign=(int)pivot.Z;
    EnumNeuronInput aInput=EnumNeuronInput.TIME;
    EnumNeuronInput bInput=EnumNeuronInput.CONSTANT;
    EnumNeuronInput cInput=EnumNeuronInput.CONSTANT;
    EnumNeuronInput dInput=EnumNeuronInput.CONSTANT;
    EnumNeuronInput eInput=EnumNeuronInput.CONSTANT;
    GeneNeuron randNeuron2 = new GeneNeuron(
            index, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
            aInput, bInput, cInput, dInput, eInput, //EnumNeuronInput types
            0, 0,6, -1*sign*Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
            EnumOperator.ADD, //Binary operator for merging A and B
            EnumOperator.IDENTITY, //Unary operator for after A and B are merged
            EnumOperator.ADD, //Binary operator for merging D and E
            EnumOperator.IDENTITY); //Unary operator for after D and E are merged
    GeneNeuron randNeuron = new GeneNeuron(
            index, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
            aInput, bInput, cInput, dInput, eInput, //EnumNeuronInput types
            0, 0,5, sign*Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
            EnumOperator.ADD, //Binary operator for merging A and B
            EnumOperator.IDENTITY, //Unary operator for after A and B are merged
            EnumOperator.ADD, //Binary operator for merging D and E
            EnumOperator.IDENTITY); //Unary operator for after D and E are merged

    for (int i = 0; i <geneBlocks.size() ; i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));
    }
    for (int j = 0; j <geneNeurons.size() ; j++)
    {
      newGenome.addGeneNeuron(geneNeurons.get(j));
    }
    newGenome.addGeneNeuron(randNeuron2);
    newGenome.addGeneNeuron(randNeuron);

    return newGenome;
  }
  //Checks if the creature is valid after mutation
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
