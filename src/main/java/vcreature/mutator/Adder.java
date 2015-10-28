package vcreature.mutator;

import vcreature.genotype.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adds genes to a genome, either random or seeded genes
 */
public class Adder
{

  static Genome genome;
  static ArrayList<GeneBlock> geneBlocks;
  static ArrayList<GeneNeuron> geneNeurons;
  static Random rand=new Random();
  static private float min = .5f;
  static private float max = 5f;

//  /**
//   * This will Randomize the genome at a random index
//   * @param genome
//   *//*
//  public Randomizer(Genome genome)
//  {
//    this.genome=genome;
//    this.geneBlocks=genome.getGENE_BLOCKS();
//    this.geneNeurons=genome.getGENE_NEURONS();
//    int index=rand.nextInt(geneBlocks.size());
//    randomize(index);
//  }
//
//  *//**
//   * Randomize genome at specific point
//   * @param genome
//   * @param index index of leg
//   *//*
//  public Randomizer(Genome genome,int index)
//  {
//    this.genome=genome;
//    this.geneBlocks=genome.getGENE_BLOCKS();
//    this.geneNeurons=genome.getGENE_NEURONS();
//    randomize(index);
//  }*/

  /**
   * This is where the magic happens
   */
  protected static Genome addBlock(Genome genome)
  {

    geneBlocks=genome.getGENE_BLOCKS();
    geneNeurons=genome.getGENE_NEURONS();
    int index = rand.nextInt(geneBlocks.size());
    int signChooser = (rand.nextBoolean()) ? 0 : 2;


    index = signChooser;
    GeneBlock block=geneBlocks.get(index);
    GeneBlock randBlock;
    float sizeX = rand.nextFloat() * (max - min) + min;
    float sizeY = rand.nextFloat() * (max - min) + min;
    float sizeZ = rand.nextFloat() * (max - min) + min;
    ImmutableVector size=new ImmutableVector(sizeX,sizeY,sizeZ);
    ImmutableVector randAngle=new ImmutableVector(0,0,0);//new ImmutableVector(rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2);
    int parentOffset=0;
    int xSign = (rand.nextBoolean()) ? 1 : -1;
    int ySign =(rand.nextBoolean()) ? 1 : -1;
    int zSign = (rand.nextBoolean()) ? 1 : -1;
    ImmutableVector randPivot= new ImmutableVector(0, 0, 0);
    int randomFace=rand.nextInt(8);
    if(randomFace==0) randPivot=new ImmutableVector(xSign,ySign,zSign);
    else if(randomFace==1)randPivot=new ImmutableVector(-xSign,ySign,zSign);
    else if(randomFace==2) randPivot=new ImmutableVector(xSign,-ySign,zSign);
    else if(randomFace==3) randPivot=new ImmutableVector(-xSign,-ySign,zSign);
    else if(randomFace==4) randPivot=new ImmutableVector(xSign,ySign,-zSign);
    else if(randomFace==5) randPivot=new ImmutableVector(-xSign,ySign,-zSign);
    else if(randomFace==6) randPivot=new ImmutableVector(xSign,-ySign,-zSign);
    else if(randomFace==7) randPivot=new ImmutableVector(-xSign,-ySign,-zSign);

    ImmutableVector parentPivot=new ImmutableVector(-randPivot.X,-randPivot.Y,-randPivot.Z);
    randomFace = rand.nextInt(2);
    if(randomFace==0)
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
          Axis.UNIT_Z.getImmutableVector(), Axis.UNIT_Z.getImmutableVector(),
          randAngle);
    }
    else if(randomFace==1)
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
          Axis.UNIT_X.getImmutableVector(), Axis.UNIT_X.getImmutableVector(),
          randAngle);
    }
    else
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
          Axis.UNIT_Y.getImmutableVector(), Axis.UNIT_Y.getImmutableVector(),
          randAngle);
    }
    //geneBlocks.remove(index);
    //geneBlocks.add(randBlock);

    geneBlocks.add(randBlock);
    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    for (int i = 0; i <geneBlocks.size() ; i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));
//      for (int j = 0; j <geneNeurons.size() ; j++)
//      {
//        if(geneNeurons.get(j).BLOCK_INDEX==i)newGenome.addGeneNeuron(geneNeurons.get(j));
//      }
    }
    for (int j = 0; j <geneNeurons.size(); j++)
    {
      newGenome.addGeneNeuron(geneNeurons.get(j));
    }
    newGenome=Randomizer.randomizeNeuron(newGenome,geneBlocks.size()-1);
    return newGenome;
  }
}
