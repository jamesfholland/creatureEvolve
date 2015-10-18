package vcreature.mainSimulation;

import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import vcreature.genotype.*;
import vcreature.phenotype.Block;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;
import vcreature.phenotype.Neuron;

import java.util.Random;

/**
 * Created by Dayloki on 10/15/2015.
 */
public class SpawnRandomCreatureGenoform
{
  private Random rand = new Random();
  private float min = 1f;
  private float max = 3f;
  private Genome genome;


  public SpawnRandomCreatureGenoform(int numberOfBlocks)
  {
    createCreature(numberOfBlocks);
  }

  public Genome getGenome()
  {
    return genome;
  }

  private void createCreature(int numberOfBlocks)
  {
    float rootSizeX = rand.nextFloat() * (max - min) + min;
    float rootSizeY = rand.nextFloat() * (max - min) + min;
    float rootSizeZ = rand.nextFloat() * (max - min) + min;
    ImmutableVector rootSize = new ImmutableVector(rootSizeX, rootSizeY, rootSizeZ);

    genome = new Genome(rootSize);
    //Block root = addRoot(rootCenter, rootSize);
    int count = 0;
    for (int i = 0; i < numberOfBlocks - 1; i++)
    {
      float sizeX = rand.nextFloat() * (max - min) + min;
      float sizeY = rand.nextFloat() * (max - min) + min;
      float sizeZ = rand.nextFloat() * (max - min) + min;
      int xSign = (rand.nextBoolean()) ? 1 : -1;
      int ySign = (rand.nextBoolean()) ? 1 : -1;
      int zSign = (rand.nextBoolean()) ? 1 : -1;
//      int xSign = 1;//(rand.nextBoolean())? 1:-1;
//      int ySign = 1;//(rand.nextBoolean())? 1:-1;
//      int zSign = 1;// (rand.nextBoolean())? 1:-1;
      //Block tempBlock = getBlockByID(rand.nextInt(getNumberOfBodyBlocks()));
      // Block tempBlock=getBlockByID(count++);//rand.nextInt(getNumberOfBodyBlocks()));
//      Geometry tempGeometry= tempBlock.getGeometry();
      // Vector3f tempCenter = tempBlock.getStartCenter();
      //System.out.println(tempBlock.getStartCenter().toString());
      Vector3f legSize = new Vector3f(sizeX, sizeY, sizeZ);
      // Vector3f legCenter=new Vector3f(tempCenter.getX()*xSign+sizeX,tempCenter.getX()+tempBlock.getSizeY()+sizeY+rootHeight,tempBlock.getSize()*zSign+sizeZ);

      // Vector3f pivotA = new Vector3f( -xSign*tempBlock.getSizeX(),-ySign*tempBlock.getSizeY(),-zSign*tempBlock.getSize());
      int randomFace = rand.nextInt(2);
      ImmutableVector randPivot = new ImmutableVector(1, 0, 0);
//      if(randomFace==0) randPivot=new ImmutableVector(xSign,ySign*rand.nextFloat(),zSign*rand.nextFloat());
//      else if(randomFace==1)randPivot=new ImmutableVector(xSign*rand.nextFloat(),ySign,zSign*rand.nextFloat());
//      else if(randomFace==2) randPivot=new ImmutableVector(xSign*rand.nextFloat(),ySign*rand.nextFloat(),zSign);
      if (randomFace == 0) randPivot = new ImmutableVector(1, rand.nextFloat(), rand.nextFloat());
      else if (randomFace == 1) randPivot = new ImmutableVector(rand.nextFloat(), 1, rand.nextFloat());
      else if (randomFace == 2) randPivot = new ImmutableVector(rand.nextFloat(), rand.nextFloat(), 1);


      System.out.println(randPivot.getVector3f().toString());
      //ImmutableVector pivotA =randPivot;
     // ImmutableVector pivotB = randPivot;
      ImmutableVector pivotA = new ImmutableVector(1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
       ImmutableVector pivotB = new ImmutableVector(-1.0f, 1.0f, 0.0f);
      ImmutableVector leg1Size = new ImmutableVector(sizeX, sizeY, sizeZ);
      Axis LegParentAxis = Axis.UNIT_Z;
      Axis LegAxis = Axis.UNIT_Z;
      //System.out.println(rand.nextInt(genome.getGENE_BLOCKS().size()));

      GeneBlock leg1 = new GeneBlock(i, pivotA, pivotB, leg1Size, LegParentAxis, LegAxis);
      System.out.println(genome.getGENE_BLOCKS().toString());
      genome.addGeneBlock(leg1);
      GeneNeuron leg1Neuron1 = new GeneNeuron(
              0, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
              EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
              0, 0, 11, -Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
              EnumOperator.ADD, //Binary operator for merging A and B
              EnumOperator.IDENTITY, //Unary operator for after A and B are merged
              EnumOperator.ADD, //Binary operator for merging D and E
              EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);

    }
  }
}

//  public boolean notIntersecting()
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

