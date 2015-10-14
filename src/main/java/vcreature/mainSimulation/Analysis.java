package vcreature.mainSimulation;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import vcreature.genotype.GenomeCreature;
import vcreature.phenotype.Creature;

/**
 * Created by Dayloki on 10/11/2015.
 */
public class Analysis
{
  public Analysis()
  {

  }

  public Boolean CheckValidlyOfCreature(Creature creature)
  {
    System.out.println(creature.getBlockByID(0).toString());
    for(int cornerNumber=0; cornerNumber<8;cornerNumber++)
    {
      System.out.println("Corner Number: " +(cornerNumber+1)+ " - Vector: " + creature.getBlockByID(0).getCornerVector(cornerNumber));
    }

    System.out.println(
        "Surface Area: " + creature.getBlockByID(0).getSurfaceArea());
    System.out.println("center: "+creature.getBlockByID(0).getCenter());
    System.out.println(
        "Bottom Y: " + creature.getBlockByID(0).getBottomYOfCube());
    System.out.println("Top Y: "+ creature.getBlockByID(0).getTopYOfCube());
    System.out.println("Right X: " + creature.getBlockByID(0).getRightXOfCube());
    System.out.println("Left X: "+ creature.getBlockByID(0).getLeftXOfCube());
    System.out.println("Front Z: " + creature.getBlockByID(0).getFrontZOfCube());
    System.out.println("Back Z: "+ creature.getBlockByID(0).getBackZOfCube());


    for (int i = 1; i < creature.getNumberOfBodyBlocks(); i++)
    {
      System.out.println(creature.getBlockByID(i).toString());
      for(int cornerNumber=0; cornerNumber<8;cornerNumber++)
      {
        System.out.println("Corner Number: " +(cornerNumber+1)+ " - Vector (x,y,z): " + creature.getBlockByID(i).getCornerVector(cornerNumber));
      }
      System.out.println();
      System.out.println("Surface Area: " +creature.getBlockByID(i).getSurfaceArea());
      System.out.println("center: "+creature.getBlockByID(i).getCenter());
      System.out.println(
          "Bottom Y: " + creature.getBlockByID(i).getBottomYOfCube());
      System.out.println("Top Y: "+ creature.getBlockByID(i).getTopYOfCube());
      System.out.println(
          "Right X: " + creature.getBlockByID(i).getRightXOfCube());
      System.out.println("Left X: " + creature.getBlockByID(i).getLeftXOfCube
          ());
      System.out.println(
          "Front Z: " + creature.getBlockByID(i).getFrontZOfCube());
      System.out.println("Back Z: " + creature.getBlockByID(i).getBackZOfCube());

      if(creature.getBlockByID(i).getLeftXOfCube() == creature.getBlockByID(i).getParent().getRightXOfCube())
      {
        System.out.println("Connected on Left");
      }
      if(creature.getBlockByID(i).getRightXOfCube() == creature.getBlockByID(i).getParent().getLeftXOfCube())
      {
        System.out.println("connected on Right");
      }

      if(creature.getBlockByID(i).getTopYOfCube() == creature.getBlockByID(i).getParent().getBottomYOfCube())
      {
        System.out.println("Connected on Top");
      }
      if(creature.getBlockByID(i).getBottomYOfCube() == creature.getBlockByID(i).getParent().getTopYOfCube())
      {
        System.out.println("Connected on Bottom");
      }
      if(creature.getBlockByID(i).getFrontZOfCube() == creature.getBlockByID(i).getParent().getBackZOfCube())
      {
        System.out.println("Connected on Front");
      }
      if(creature.getBlockByID(i).getBackZOfCube() == creature.getBlockByID(i).getParent().getFrontZOfCube())
      {
        System.out.println("Connected on Back");
      }

      System.out.println();
    }
    return true;
  }
}
