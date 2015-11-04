package vcreature.mainSimulation;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;
import vcreature.mutator.hillclimbing.Adder;
import vcreature.mutator.hillclimbing.Randomizer;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

import java.util.Random;

/**
 * Created by Dayloki on 10/15/2015.
 */

public class SpawnRandomCreatureGenoform
{
  private static Random rand = new Random();
  private static float min = .5f;
  private static float max = 3f;
  private static Genome genome;


  public SpawnRandomCreatureGenoform(int numberOfBlocks)
  {
    createCreature(numberOfBlocks);
  }

  public Genome getGenome()
  {
    return genome;
  }

  public static Genome createCreature(int numberOfBlocks)
  {
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);
    float rootSizeX = rand.nextFloat() * (max - min) + min;
    float rootSizeY = rand.nextFloat() * (max - min) + min;
    float rootSizeZ = rand.nextFloat() * (max - min) + min;
    ImmutableVector rootSize = new ImmutableVector(rootSizeX, rootSizeY, rootSizeZ);
    genome = new Genome(rootSize, new ImmutableVector(0f, 0f, 0f));
    //Axis legParentAxis = Axis.UNIT_Z;
    //Axis legAxis = Axis.UNIT_Z;
    ImmutableVector legParentAxis = new ImmutableVector(0, 0, 1);
    ImmutableVector legAxis = new ImmutableVector(0, 0, 1);
    //Axis legAxis=new Axis();

    float sizeX = rand.nextFloat() * (max - min) + min;
    float sizeY = rand.nextFloat() * (max - min) + min;
    float sizeZ = rand.nextFloat() * (max - min) + min;
    ImmutableVector pivotC = new ImmutableVector(-1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotD = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg2Size = new ImmutableVector(sizeX, sizeY, sizeZ);
    GeneBlock leg2 = new GeneBlock(0, pivotC, pivotD, leg2Size, legParentAxis, legAxis, zeroVector);
    genome.addGeneBlock(leg2);

    for (int i = 1; i < numberOfBlocks; i++)
    {
      sizeX = rand.nextFloat() * (max - min) + min;
      sizeY = rand.nextFloat() * (max - min) + min;
      sizeZ = rand.nextFloat() * (max - min) + min;
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

  private GeneNeuron randNeuron(int index)
  {
    GeneNeuron randNeuron = null;
    int randNeuronEnum = rand.nextInt(5);
    EnumNeuronInput aInput = randEnumNeuron();
    EnumNeuronInput bInput = randEnumNeuron();
    EnumNeuronInput cInput = randEnumNeuron();
    EnumNeuronInput dInput = randEnumNeuron();
    EnumNeuronInput eInput = randEnumNeuron();
    randNeuron = new GeneNeuron(
        index, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
        aInput, bInput, cInput, dInput, eInput, //EnumNeuronInput types
        rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
        EnumOperator.ADD, //Binary operator for merging A and B
        EnumOperator.IDENTITY, //Unary operator for after A and B are merged
        EnumOperator.ADD, //Binary operator for merging D and E
        EnumOperator.IDENTITY); //Unary operator for after D and E are merged
    return randNeuron;
  }

  private EnumNeuronInput randEnumNeuron()
  {
    int enumIndex = rand.nextInt(3);
    switch (enumIndex)
    {
      case 0:
        return EnumNeuronInput.CONSTANT;
      case 1:
        return EnumNeuronInput.HEIGHT;
      case 4:
        return EnumNeuronInput.JOINT;
      case 2:
        return EnumNeuronInput.TIME;
      case 3:
        return EnumNeuronInput.TOUCH;
    }
    return EnumNeuronInput.CONSTANT;
  }

  public static Genome createRandomCreature(int numOfBlocks)
  {
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);
    float rootSizeX = rand.nextFloat() * (max - min) + min;
    float rootSizeY = rand.nextFloat() * (max - min) + min;
    float rootSizeZ = rand.nextFloat() * (max - min) + min;
    ImmutableVector rootSize = new ImmutableVector(rootSizeX, rootSizeY, rootSizeZ);
    Genome genome = new Genome(rootSize, new ImmutableVector(0f, 0f, 0f));

    ImmutableVector legParentAxis = new ImmutableVector(0, 0, 1);
    ImmutableVector legAxis = new ImmutableVector(0, 0, 1);
    //Axis legAxis=new Axis();

    float sizeX = rand.nextFloat() * (max - min) + min;
    float sizeY = rand.nextFloat() * (max - min) + min;
    float sizeZ = rand.nextFloat() * (max - min) + min;
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
//  {
//    CollisionResults results = new CollisionResults();
//    for (int i = 0; i < getNumberOfBodyBlocks() - 1; i++)
//    {
//      Block tempBlock = getBlockByID(i);
//      for (int j = 1; j < getNumberOfBodyBlocks(); j++)
//      {
//        Block tempBlock2 = getBlockByID(j);
//
//        BoundingBox tempBlock2Volume = new BoundingBox(tempBlock2.getStartCenter(), tempBlock2.getSizeX(), tempBlock2.getSizeY(), tempBlock2.getSize());
//        System.out.println(tempBlock2.getGeometry().getLocalTranslation().toString());
//        if(tempBlock.getGeometry()==null) System.out.println("DKLS:JFDSA");
//        tempBlock.getGeometry().collideWith(tempBlock2Volume, results);
//        if (results.size() > 0)
//        {
//          System.out.println("intersects");
//          return false;
//        }
//      }
//    }
//    return true;
//  }

