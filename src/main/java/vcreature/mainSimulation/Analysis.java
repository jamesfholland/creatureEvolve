package vcreature.mainSimulation;

import com.jme3.math.Vector3f;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import vcreature.genotype.GenomeCreature;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;

/**
 * Created by Dayloki on 10/11/2015.
 */
public class Analysis
{
//  private final Vector3f[] cornerVectors = new Vector3f[8];
//
//  cornerVectors[0] = new Vector3f(center.x-size.x, center.y+size.y,center.z-size.z); // top,front,left
//  cornerVectors[1] = new Vector3f(center.x+size.x, center.y+size.y,center.z-size.z); // top,front,right
//  cornerVectors[2] = new Vector3f(center.x-size.x, center.y-size.y,center.z-size.z); // bottom,front,left
//  cornerVectors[3] = new Vector3f(center.x+size.x, center.y-size.y,center.z-size.z); // bottom,front,right
//  cornerVectors[4] = new Vector3f(center.x-size.x, center.y+size.y,center.z+size.z); // top,back,left
//  cornerVectors[5] = new Vector3f(center.x+size.x, center.y+size.y,center.z+size.z); // top,back,right
//  cornerVectors[6] = new Vector3f(center.x-size.x, center.y-size.y,center.z+size.z); // bottom,back,left
//  cornerVectors[7] = new Vector3f(center.x+size.x, center.y-size.y,center.z+size.z); // bottom,back,right
//
//
//  public Vector3f getCorner1() {return cornerVectors[0];}
//  public Vector3f getCorner2() {return cornerVectors[1];}
//  public Vector3f getCorner3() {return cornerVectors[2];}
//  public Vector3f getCorner4() {return cornerVectors[3];}
//  public Vector3f getCorner5() {return cornerVectors[4];}
//  public Vector3f getCorner6() {return cornerVectors[5];}
//  public Vector3f getCorner7() {return cornerVectors[6];}
//  public Vector3f getCorner8() {return cornerVectors[7];}

// cannot use numbers greater than seven or less than zero.
//  public Vector3f getCornerVector(int cornerNumber) {return cornerVectors[cornerNumber];}


  public Analysis()
  {

  }

  /**
   * Gets the bottom face coordinate of the Block
   * @return
   */
  private float getBottomYOfCube(Block inputBlock) { return inputBlock.getCenter().y-(inputBlock.getSizeY()/2);}
  /**
   * Gets the top face coordinate of the Block
   * @return
   */
  private float getTopYOfCube(Block inputBlock) { return inputBlock.getCenter().y+(inputBlock.getSizeY()/2);}
  /**
   * Gets the right face coordinate of the Block
   * @return
   */
  private float getRightXOfCube(Block inputBlock) { return inputBlock.getCenter().x-(inputBlock.getSizeX()/2);}
  /**
   * Gets the left face coordinate of the Block
   * @return
   */
  private float getLeftXOfCube(Block inputBlock) { return inputBlock.getCenter().x+(inputBlock.getSizeX()/2);}
  /**
   * Gets the front face coordinate of the Block
   * @return
   */
  private float getFrontZOfCube(Block inputBlock) { return inputBlock.getCenter().z-(inputBlock.getSize()/2);}
  /**
   * Gets the back face coordinate of the Block
   * @return
   */
  private float getBackZOfCube(Block inputBlock) { return inputBlock.getCenter().z+(inputBlock.getSize()/2);}


  private float getSurfaceArea(Block inputBlock) {
    return  2*inputBlock.getSizeX()*inputBlock.getSizeY() + 2*inputBlock.getSizeX()*inputBlock.getSize() + 2*inputBlock.getSizeY()*inputBlock.getSize();
  }


  public Boolean CheckValidlyOfCreature(Creature creature)
  {
    System.out.println(creature.getBlockByID(0).toString());
    for(int cornerNumber=0; cornerNumber<8;cornerNumber++)
    {
   //   System.out.println("Corner Number: " +(cornerNumber+1)+ " - Vector: " + creature.getBlockByID(0).getCornerVector(cornerNumber));
    }

    System.out.println(
        "Surface Area: " + getSurfaceArea(creature.getBlockByID(0)));
    System.out.println("center: "+creature.getBlockByID(0).getCenter());
    System.out.println(
        "Bottom Y: " + getBottomYOfCube(creature.getBlockByID(0)));
    System.out.println("Top Y: " + getTopYOfCube(creature.getBlockByID(0)));
    System.out.println("Right X: " + getRightXOfCube(creature.getBlockByID(0)));
    System.out.println("Left X: " + getLeftXOfCube(creature.getBlockByID(0)));
    System.out.println("Front Z: " + getFrontZOfCube(creature.getBlockByID(0)));
    System.out.println("Back Z: " + getBackZOfCube(creature.getBlockByID(0)));


    for (int i = 1; i < creature.getNumberOfBodyBlocks(); i++)
    {
      System.out.println(creature.getBlockByID(i).toString());
      for(int cornerNumber=0; cornerNumber<8;cornerNumber++)
      {
  //      System.out.println("Corner Number: " +(cornerNumber+1)+ " - Vector (x,y,z): " + creature.getBlockByID(i).getCornerVector(cornerNumber));
      }
      System.out.println();
      System.out.println("Surface Area: " + getSurfaceArea(creature.getBlockByID(i)));
      System.out.println("center: "+creature.getBlockByID(i).getCenter());
      System.out.println(
          "Bottom Y: " + getBottomYOfCube(creature.getBlockByID(i)));
      System.out.println("Top Y: " + getTopYOfCube(creature.getBlockByID(i)));
      System.out.println(
          "Right X: " + getRightXOfCube(creature.getBlockByID(i)));
      System.out.println("Left X: " + getLeftXOfCube(creature.getBlockByID(i)));
      System.out.println(
          "Front Z: " + getFrontZOfCube(creature.getBlockByID(i)));
      System.out.println("Back Z: " + getBackZOfCube(creature.getBlockByID(i)));

      if(getLeftXOfCube(creature.getBlockByID(i)) == getRightXOfCube(creature.getBlockByID(i)))
      {
        System.out.println("Connected on Left");
      }
      if(getRightXOfCube(creature.getBlockByID(i)) == getLeftXOfCube(creature.getBlockByID(i).getParent()))
      {
        System.out.println("connected on Right");
      }

      if(getTopYOfCube(creature.getBlockByID(i)) == getBottomYOfCube(creature.getBlockByID(i).getParent()))
      {
        System.out.println("Connected on Top");
      }
      if(getBottomYOfCube(creature.getBlockByID(i)) == getTopYOfCube(creature.getBlockByID(i).getParent()))
      {
        System.out.println("Connected on Bottom");
      }
      if(getFrontZOfCube(creature.getBlockByID(i)) == getBackZOfCube(creature.getBlockByID(i).getParent()))
      {
        System.out.println("Connected on Front");
      }
      if(getBackZOfCube(creature.getBlockByID(i)) == getFrontZOfCube(creature.getBlockByID(i).getParent()))
      {
        System.out.println("Connected on Back");
      }

      System.out.println();
    }
    return true;
  }
}
