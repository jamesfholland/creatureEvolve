package vcreature.mainSimulation;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;
import vcreature.mutator.hillclimbing.Adder;

import java.util.Random;


public class SpawnRandomCreatureGenoform
{
  private static final Random rand = MainSim.RANDOM;
  private static final float MIN = .501f;
  private static final float MAX = 3f;

  /**
   * Create a random creature using the Adder mutator.
   *
   * @param numOfBlocks to form creature from.
   * @return the random genome
   */
  public static Genome createRandomCreature(int numOfBlocks)
  {
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);
    float rootSizeX = rand.nextFloat() * (MAX - MIN) + MIN;
    float rootSizeY = rand.nextFloat() * (MAX - MIN) + MIN;
    float rootSizeZ = rand.nextFloat() * (MAX - MIN) + MIN;
    ImmutableVector rootSize = new ImmutableVector(rootSizeX, rootSizeY, rootSizeZ);
    Genome genome = new Genome(rootSize, new ImmutableVector(0f, 0f, 0f));

    ImmutableVector legParentAxis = new ImmutableVector(0, 0, 1);
    ImmutableVector legAxis = new ImmutableVector(0, 0, 1);
    //Axis legAxis=new Axis();

    float sizeX = rand.nextFloat() * (MAX - MIN) + MIN;
    float sizeY = rand.nextFloat() * (MAX - MIN) + MIN;
    float sizeZ = rand.nextFloat() * (MAX - MIN) + MIN;
    ImmutableVector pivotC = new ImmutableVector(-1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotD = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg2Size = new ImmutableVector(sizeX, sizeY, sizeZ);
    GeneBlock leg2 = new GeneBlock(0, pivotC, pivotD, leg2Size, legParentAxis, legAxis, zeroVector);
    genome.addGeneBlock(leg2);
    for (int i = 0; i < numOfBlocks; i++)
    {
      genome = Adder.addBlock(genome);
    }
    return genome;
  }
}


