package vcreature.mainSimulation;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;

/**
 * Created by Dayloki on 10/8/2015.
 */

/**
 * This class will make a random creature of n blocks
 */
public class SpawnRandomCreature extends Creature
{
  public SpawnRandomCreature(PhysicsSpace physicsSpace, Node jMonkeyRootNode,int numberOfblocks)
  {
    super(physicsSpace, jMonkeyRootNode);
    createCreature(numberOfblocks);

  }

  private void createCreature(int numberOfBlocks)
  {

    for (int i = 0; i <numberOfBlocks-1 ; i++)
    {


    }



  }


}
