package vcreature.mainSimulation;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;
import vcreature.mutator.hillclimbing.Adder;
import vcreature.mutator.hillclimbing.Randomizer;

import java.util.Random;


public class SpawnRandomCreatureGenoform
{
  private static final Random rand = MainSim.RANDOM;
  private static final float MIN = .501f;
  private static final float MAX = 3f;

  /**
   * This was terribly not thread safe. I fixed that, but am not sure if this is what we want to use or if I should be using adder?
   * @param numberOfBlocks
   * @return
   */
  public static Genome createCreature(int numberOfBlocks)
  {
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);
    float rootSizeX = rand.nextFloat() * (MAX - MIN) + MIN;
    float rootSizeY = rand.nextFloat() * (MAX - MIN) + MIN;
    float rootSizeZ = rand.nextFloat() * (MAX - MIN) + MIN;
    ImmutableVector rootSize = new ImmutableVector(rootSizeX, rootSizeY, rootSizeZ);
    Genome genome = new Genome(rootSize, new ImmutableVector(0f, 0f, 0f));
    //Axis legParentAxis = Axis.UNIT_Z;
    //Axis legAxis = Axis.UNIT_Z;
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

    for (int i = 1; i < numberOfBlocks; i++)
    {
      sizeX = rand.nextFloat() * (MAX - MIN) + MIN;
      sizeY = rand.nextFloat() * (MAX - MIN) + MIN;
      sizeZ = rand.nextFloat() * (MAX - MIN) + MIN;
      int randomFace = rand.nextInt(2);
      ImmutableVector randPivot = new ImmutableVector(1, -1, 0);
      int xSign = (rand.nextBoolean()) ? 1 : -1;
      int ySign = (rand.nextBoolean()) ? 1 : -1;
      int zSign = (rand.nextBoolean()) ? 1 : -1;
      if (randomFace == 0)
      {
        randPivot = new ImmutableVector(xSign, ySign, zSign * rand.nextFloat());
      }
      else if (randomFace == 1)
      {
        randPivot = new ImmutableVector(xSign * rand.nextFloat(), ySign, zSign);
      }
      else if (randomFace == 2)
      {
        randPivot = new ImmutableVector(xSign, ySign * rand.nextFloat(), zSign);
      }

      // System.out.println(randPivot.getVector3f().toString());
      ImmutableVector pivotA = new ImmutableVector(-randPivot.X, -randPivot.Y, -randPivot.Z);//new ImmutableVector(1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
      //ImmutableVector pivotA =new ImmutableVector(1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector pivotB = randPivot;// new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
      //ImmutableVector pivotB =new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector leg1Size = new ImmutableVector(sizeX, sizeY, sizeZ);

//      if(numberOfBlocks!=0) numberOfBlocks=rand.nextInt(numberOfBlocks);
//      int randBlockIndex=genome.getGENE_BLOCKS().get(numberOfBlocks).PARENT_OFFSET;
//      if(randBlockIndex<1) randBlockIndex=1;
      int numOfBlocks = genome.getGENE_BLOCKS().size();

      int randBlockIndex = 1;
      //System.out.println(numOfBlocks);
      //System.out.println(genome.getGENE_BLOCKS().toString());
      if (numOfBlocks > 1)
      {
        randBlockIndex = genome.getGENE_BLOCKS().get(rand.nextInt(numOfBlocks)).hashCode();
      }
      int offset = -rand.nextInt(i);
      //System.out.println(offset);
      ImmutableVector randAngle = new ImmutableVector(0, 0, 0);//new ImmutableVector(rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2);
      ImmutableVector axis = new ImmutableVector(0, 0, 0);
      if (rand.nextBoolean())
      {
        axis = new ImmutableVector(0, 0, 1);//new ImmutableVector(0,0,randAngle.X*randAngle.Y);//=new ImmutableVector(0,randAngle.Y*randAngle.Z,0);
      }
      else
      {
        axis = new ImmutableVector(1, 0, 0);//new ImmutableVector(0,0,randAngle.X*randAngle.Y);//=new ImmutableVector(0,randAngle.Y*randAngle.Z,0);
      }
      GeneBlock leg = new GeneBlock(offset, pivotA, pivotB, leg1Size, axis, axis, randAngle);

      genome.addGeneBlock(leg); //Leg1 is in position 0 in the list.
//     GeneNeuron leg1Neuron1= new GeneNeuron(
//              i, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
//              EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//              0, 0, 5, -Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
//              EnumOperator.ADD, //Binary operator for merging A and B
//              EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//              EnumOperator.ADD, //Binary operator for merging D and E
//              EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome = Randomizer.randomizeNeuron(genome, i - 1);
//      genome.addGeneNeuron(leg1Neuron1);
    }
    //Leg1 stuff

    //Leg2 stuff

    return genome;
  }


  /**
   * Create a random creature using the Adder mutator.
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


