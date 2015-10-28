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
    int index = rand.nextInt(1);

    GeneBlock block=geneBlocks.get(index);
    GeneBlock randBlock;
    float sizeX = rand.nextFloat() * (max - min) + min;
    float sizeY = rand.nextFloat() * (max - min) + min;
    float sizeZ = rand.nextFloat() * (max - min) + min;
    ImmutableVector size=new ImmutableVector(sizeX,sizeY,sizeZ);
    ImmutableVector randAngle=new ImmutableVector(0,0,0);//new ImmutableVector(rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2);
    int parentOffset=1;
    int xSign = (rand.nextBoolean()) ? 1 : -1;
    int ySign =(rand.nextBoolean()) ? 1 : -1;
    int zSign = (rand.nextBoolean()) ? 1 : -1;
    ImmutableVector randPivot= new ImmutableVector(0, 0, 0);
    int randomFace=rand.nextInt(2);
    if(randomFace==0) randPivot=new ImmutableVector(xSign,ySign*rand.nextFloat(),zSign*rand.nextFloat());
    else if(randomFace==1)randPivot=new ImmutableVector(xSign*rand.nextFloat(),ySign,zSign*rand.nextFloat());
    else if(randomFace==2) randPivot=new ImmutableVector(xSign*rand.nextFloat(),ySign*rand.nextFloat(),zSign);
    ImmutableVector parentPivot=new ImmutableVector(-randPivot.X,-randPivot.Y,-randPivot.Z);
    randBlock=new GeneBlock(parentOffset, randPivot,parentPivot,size, Axis.UNIT_Z.getImmutableVector(),Axis.UNIT_Z.getImmutableVector(),randAngle);
    //geneBlocks.remove(index);
    geneBlocks.add(index,randBlock);
    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    for (int i = 0; i <geneBlocks.size() ; i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));
    }
    if(checkForIntersections(newGenome)) genome=newGenome;
    else addBlock(genome);
    return newGenome;
  }
  //Checks if the creature is valid after mutation
  private static  boolean checkForIntersections(Genome genome)
  {
    for (int i = 0; i <geneBlocks.size() ; i++)
    {

    }
    return true;
  }
  public Genome getGenome(){return genome;}



}
