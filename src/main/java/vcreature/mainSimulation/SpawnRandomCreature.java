package vcreature.mainSimulation;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
//import javafx.geometry.BoundingBox;
import com.jme3.bounding.BoundingBox;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;

import java.util.Random;

/**
 * Created by Dayloki on 10/8/2015.
 */

/**
 * This class will make a random creature of n blocks
 */
public class SpawnRandomCreature extends Creature
{
  private Random rand = new Random();
  private float min = .5f;
  private float max = 1f;


  public SpawnRandomCreature(PhysicsSpace physicsSpace, Node jMonkeyRootNode, int numberOfBlocks)
  {
    super(physicsSpace, jMonkeyRootNode);
    createCreature(numberOfBlocks);

  }

  private void createCreature(int numberOfBlocks)
  {
    float rootSizeX = rand.nextFloat() * (max - min) + min;
    float rootSizeY = rand.nextFloat() * (max - min) + min;
    float rootSizeZ = rand.nextFloat() * (max - min) + min;
    Vector3f rootSize = new Vector3f(rootSizeX, rootSizeY, rootSizeZ);

    float rootHeight = rootSizeY;
    // float rootHeight=10;
    Vector3f rootCenter = new Vector3f(0, rootHeight, 0);
    Block root = addRoot(rootCenter, rootSize);
    int count = 0;
    for (int i = 0; i < numberOfBlocks - 1; i++)
    {
      float sizeX = rand.nextFloat() * (max - min) + min;
      float sizeY = rand.nextFloat() * (max - min) + min;
      float sizeZ = rand.nextFloat() * (max - min) + min;
//      int xSign= (rand.nextBoolean())? 1:-1;
//      int ySign= (rand.nextBoolean())? 1:-1;
//      int zSign= (rand.nextBoolean())? 1:-1;
      int xSign = 1;//(rand.nextBoolean())? 1:-1;
      int ySign = 1;//(rand.nextBoolean())? 1:-1;
      int zSign = 1;// (rand.nextBoolean())? 1:-1;
      Block tempBlock = getBlockByID(rand.nextInt(getNumberOfBodyBlocks()));
      // Block tempBlock=getBlockByID(count++);//rand.nextInt(getNumberOfBodyBlocks()));
//      Geometry tempGeometry= tempBlock.getGeometry();
      Vector3f tempCenter=new Vector3f(0,0,0);
       tempCenter = tempBlock.getCenter(tempCenter);
      //System.out.println(tempBlock.getStartCenter().toString());
      Vector3f legSize = new Vector3f(sizeX, sizeY, sizeZ);
      // Vector3f legCenter=new Vector3f(tempCenter.getX()*xSign+sizeX,tempCenter.getX()+tempBlock.getSizeY()+sizeY+rootHeight,tempBlock.getSize()*zSign+sizeZ);
      Vector3f legCenter = new Vector3f(xSign * tempCenter.getX() + tempBlock.getSizeX() + sizeX, ySign * tempCenter.getY() +
              tempBlock.getSizeY() + sizeY, zSign * tempCenter.getZ() + tempBlock.getSize() + sizeZ);
      // Vector3f pivotA = new Vector3f( -xSign*tempBlock.getSizeX(),-ySign*tempBlock.getSizeY(),-zSign*tempBlock.getSize());
      Vector3f pivotA = new Vector3f(tempBlock.getSizeX() / 2, tempBlock.getSizeY() / 2, tempBlock.getSize() / 2);
      Vector3f pivotB = new Vector3f(-xSign * sizeX, -ySign * sizeY, 0 - zSign * sizeZ);

      if (notIntersecting())
      {
        Block nextLeg = addBlock(legCenter, legSize, tempBlock, pivotA, pivotB, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

        switch (rand.nextInt(4))
        {
          case 0:
            nextLeg.setMaterial(Block.MATERIAL_BLUE);
            break;
          case 1:
            nextLeg.setMaterial(Block.MATERIAL_BROWN);
            break;
          case 2:
            nextLeg.setMaterial(Block.MATERIAL_GREEN);
            break;
          case 3:
            nextLeg.setMaterial(Block.MATERIAL_RED);
            break;
        }

        Neuron positveMax = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
                EnumNeuronInput.CONSTANT, null);
        positveMax.setInputValue(Neuron.C, 10);
        positveMax.setInputValue(Neuron.D, Float.MAX_VALUE);
        nextLeg.addNeuron(positveMax);
      }
    }
  }

  public boolean notIntersecting()
  {
    CollisionResults results = new CollisionResults();
    Vector3f tempVector =new Vector3f(0,0,0);
    for (int i = 0; i < getNumberOfBodyBlocks() - 1; i++)
    {
      Block tempBlock = getBlockByID(i);
      for (int j = 1; j < getNumberOfBodyBlocks(); j++)
      {
        Block tempBlock2 = getBlockByID(j);

        BoundingBox tempBlock2Volume = new BoundingBox(tempBlock2.getStartCenter(tempVector), tempBlock2.getSizeX(), tempBlock2.getSizeY(), tempBlock2.getSize());
        System.out.println(tempBlock2.getGeometry().getLocalTranslation().toString());
        if(tempBlock.getGeometry()==null) System.out.println("DKLS:JFDSA");
        tempBlock.getGeometry().collideWith(tempBlock2Volume, results);
        if (results.size() > 0)
        {
          System.out.println("intersects");
          return false;
        }
      }
    }
    return true;
  }


  private void translateUp(float unitsUp)
  {
    for (int i = 0; i < this.getNumberOfBodyBlocks(); i++)
    {

    }


  }

}
