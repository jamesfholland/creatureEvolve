package vcreature.mainSimulation;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;

import javax.vecmath.Vector3d;
import java.awt.*;
import java.util.Random;

/**
 * Created by Dayloki on 10/8/2015.
 */

/**
 * This class will make a random creature of n blocks
 */
public class SpawnRandomCreature extends Creature
{
  private Random rand=new Random();
  private float min=.5f;
  private float max=1f;


  public SpawnRandomCreature(PhysicsSpace physicsSpace, Node jMonkeyRootNode,int numberOfblocks)
  {
    super(physicsSpace, jMonkeyRootNode);
    createCreature(numberOfblocks);

  }

  private void createCreature(int numberOfBlocks)
  {

    float rootSizeX=rand.nextFloat()*(max-min)+min;
    float rootSizeY=rand.nextFloat()*(max-min)+min;
    float rootSizeZ=rand.nextFloat()*(max-min)+min;
    Vector3f rootSize=new Vector3f(rootSizeX,rootSizeY,rootSizeZ);

    float rootHeight=rootSizeY;
    Vector3f rootCenter=new Vector3f(0,rootHeight,0);
    Block root=addRoot(rootCenter,rootSize);
    System.out.println(root.toString());
    System.out.println(rootCenter.toString());
    int count=0;
    for (int i = 0; i <numberOfBlocks-1 ; i++)
    {
      float sizeX=rand.nextFloat()*(max-min)+min;
      float sizeY=rand.nextFloat()*(max-min)+min;
      float sizeZ=rand.nextFloat()*(max-min)+min;
      int xSign= (rand.nextBoolean())? 1:-1;
      int ySign= (rand.nextBoolean())? 1:-1;
      int zSign= (rand.nextBoolean())? 1:-1;
//      int xSign= 1;//(rand.nextBoolean())? 1:-1;
//      int ySign= 1;//(rand.nextBoolean())? 1:-1;
//      int zSign=1;// (rand.nextBoolean())? 1:-1;
      count++;
      Block tempBlock=getBlockByID(rand.nextInt(getNumberOfBodyBlocks()));
//      Geometry tempGeometry= tempBlock.getGeometry();
//      Vector3f tempCenter= tempGeometry.center().getWorldTranslation();
      Vector3f legSize=new Vector3f(sizeX,sizeY,sizeZ);
     // Vector3f legCenter=new Vector3f(tempCenter.getX()*xSign+sizeX,tempCenter.getX()+tempBlock.getSizeY()+sizeY+rootHeight,tempBlock.getSize()*zSign+sizeZ);
      Vector3f legCenter=new Vector3f(rootCenter.getX()+tempBlock.getSizeX()+sizeX,rootCenter.getY()+
              tempBlock.getSizeY()+sizeY,0);
     // Vector3f pivotA = new Vector3f( -xSign*tempBlock.getSizeX(),-ySign*tempBlock.getSizeY(),-zSign*tempBlock.getSize());
      Vector3f pivotA = new Vector3f(tempBlock.getSizeX()/2,tempBlock.getSizeY()/2,tempBlock.getSize()/2);
      Vector3f pivotB = new Vector3f(-sizeX,  -sizeY,  0);

      Block nextLeg=addBlock(legCenter,legSize,tempBlock,pivotA,pivotB,Vector3f.UNIT_Z,Vector3f.UNIT_Z);


      System.out.println(rootCenter.toString()+" center of Parent");
//      System.out.println(nextLeg.toString());
//      System.out.println(legCenter.toString());
//      System.out.println(sizeX + "," + sizeY + "," + sizeZ);
//      System.out.println(tempBlock.getSizeX() + "," + tempBlock.getSizeY() + "," + tempBlock.getSize() + " tempBlock size");
      switch (rand.nextInt(4)){
        case 0: nextLeg.setMaterial(nextLeg.MATERIAL_BLUE);
          break;
        case 1: nextLeg.setMaterial(nextLeg.MATERIAL_BROWN);
          break;
        case 2: nextLeg.setMaterial(nextLeg.MATERIAL_GREEN);
          break;
        case 3: nextLeg.setMaterial(nextLeg.MATERIAL_RED);
          break;
      }

      Neuron positveMax = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
              EnumNeuronInput.CONSTANT, null);
      positveMax.setInputValue(Neuron.C, 10);
      positveMax.setInputValue(Neuron.D,Float.MAX_VALUE);
      nextLeg.addNeuron(positveMax);
    }
  }


}
