package vcreature.mainSimulation;

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

      System.out.println(creature.getBlockByID(i).getSizeX());

      System.out.println(creature.getBlockByID(i).getSizeY());

      System.out.println(creature.getBlockByID(i).getSizeZ());

    }
    return true;
  }
}
