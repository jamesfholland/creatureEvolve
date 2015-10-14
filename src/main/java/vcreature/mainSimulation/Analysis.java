package vcreature.mainSimulation;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
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
    for (int i = 0; i < creature.getNumberOfBodyBlocks(); i++)
    {
      System.out.println(creature.getBlockByID(i).toString());
      System.out.println(creature.getBlockByID(i).getSurfaceArea());
      System.out.println("center: "+creature.getBlockByID(i).getCenter());
      System.out.println("Bottom Y: "+ creature.getBlockByID(i).getBottomYOfCube());
      System.out.println("Top Y: "+ creature.getBlockByID(i).getTopYOfCube());
      System.out.println("Right X: "+ creature.getBlockByID(i).getRightXOfCube());
      System.out.println("Left X: "+ creature.getBlockByID(i).getLeftXOfCube());
      System.out.println("Front Z: "+ creature.getBlockByID(i).getFrontZOfCube());
      System.out.println("Back Z: "+ creature.getBlockByID(i).getBackZOfCube());
    }
    return true;
  }
}
