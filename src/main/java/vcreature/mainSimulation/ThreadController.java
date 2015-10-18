package vcreature.mainSimulation;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import vcreature.genotype.GenomeCreature;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.PhysicsConstants;

/**
 * ThreadController contains everything for running a physics sim and evolution sim. A copy exists for each thread.
 */
public class ThreadController
{
  private BulletAppState bulletAppState;
  private PhysicsSpace physicsSpace;
  public Creature myCreature;
  private float elapsedSimulationTime = 0.0f;

  ThreadController()
  {
    /**
     * Set up Physics
     */
    bulletAppState = new BulletAppState(new Vector3f(-1000,-1000,-1000), new Vector3f(1000,1000,1000));
    //stateManager.attach(bulletAppState);
    bulletAppState.startPhysics();
    physicsSpace = bulletAppState.getPhysicsSpace();
    //bulletAppState.setDebugEnabled(true);

    physicsSpace.setGravity(PhysicsConstants.GRAVITY);
    physicsSpace.setAccuracy(PhysicsConstants.PHYSICS_UPDATE_RATE);
    physicsSpace.setMaxSubSteps(4);




    //Set up inmovable floor
    Box floor = new Box(50f, 0.1f, 50f);

    /* Make the floor physical with mass 0.0f */
    RigidBodyControl floor_phy = new RigidBodyControl(0.0f);
    Geometry floor_geo = new Geometry("Floor", floor);
    floor_geo.setLocalTranslation(0, -0.11f, 0);
    floor_geo.addControl(floor_phy);
    physicsSpace.add(floor_phy);
    floor_phy.setFriction(PhysicsConstants.GROUND_SLIDING_FRICTION);
    floor_phy.setRestitution(PhysicsConstants.GROUND_BOUNCINESS);
    floor_phy.setDamping(PhysicsConstants.GROUND_LINEAR_DAMPINING,
                         PhysicsConstants.GROUND_ANGULAR_DAMPINING);

    FlappyBirdGenoform flappy = new FlappyBirdGenoform();
    Node root = new Node("Root Node");
    myCreature = new GenomeCreature(physicsSpace, root, flappy.getGenome());

    //physicsSpace.addAll(root);

    System.out.format("Creature Fitness (Maximium height of lowest point) = %.3f meters]\n", myCreature.getFitness());

    while(true)
    {
      try
      {
        Thread.sleep(11l);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      update(11l);
      if(myCreature.getFitness() != 0.0f)
        System.out.format("Creature Fitness (Maximium height of lowest point) = %.3f meters]\n", myCreature.getFitness());
    }

  }

  private void update(long deltaSeconds)
  {
    elapsedSimulationTime += deltaSeconds;
    bulletAppState.update(elapsedSimulationTime);
    //physicsSpace.update(elapsedSimulationTime);

    myCreature.updateBrain(elapsedSimulationTime*1000f);
  }
}
