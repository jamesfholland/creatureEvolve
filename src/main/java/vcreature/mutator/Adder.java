package vcreature.mutator;

import vcreature.genotype.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adds genes to a genome, either random or seeded genes
 */
public class Adder
{
  Genome genome;

  /**
   * This is where the magic happens
   */
  protected static Genome addBlock(Genome genome)
  {
    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;
    Random rand=new Random();
    float min = .5f;
    float max = 3f;

    geneBlocks=genome.getGENE_BLOCKS();
    geneNeurons=genome.getGENE_NEURONS();
    int index = rand.nextInt(geneBlocks.size());
    int signChooser = (rand.nextBoolean()) ? -1 : 1;



   // GeneBlock block=geneBlocks.get(index);
    GeneBlock randBlock;
    float sizeX = rand.nextFloat() * (max - min) + min;
    float sizeY = rand.nextFloat() * (max - min) + min;
    float sizeZ = rand.nextFloat() * (max - min) + min;
    ImmutableVector size=new ImmutableVector(sizeX,sizeY,sizeZ);
    ImmutableVector randAngle=new ImmutableVector(0,0,0);//new ImmutableVector(rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2);
    int parentOffset= -1 * index;
    int xSign = (rand.nextBoolean()) ? 1 : -1;
    int ySign =(rand.nextBoolean()) ? 1 : -1;
    int zSign = (rand.nextBoolean()) ? 1 : -1;
    ImmutableVector randPivot= new ImmutableVector(0, 0, 0);
    int randomFace=rand.nextInt(2);

    if(randomFace==0)      randPivot=new ImmutableVector(xSign*rand.nextFloat(),ySign,zSign);
    else if(randomFace==1) randPivot=new ImmutableVector(xSign,ySign*rand.nextFloat(),zSign);
    else if(randomFace==2) randPivot=new ImmutableVector(xSign,ySign,zSign*rand.nextFloat());

    ImmutableVector parentPivot=new ImmutableVector(-randPivot.X,-randPivot.Y,-randPivot.Z);

    if(rand.nextBoolean())
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
          Axis.UNIT_Z.getImmutableVector(), Axis.UNIT_Z.getImmutableVector(),
          randAngle);
    }
    else
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
          Axis.UNIT_X.getImmutableVector(), Axis.UNIT_X.getImmutableVector(),
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
