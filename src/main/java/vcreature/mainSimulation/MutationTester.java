package vcreature.mainSimulation;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.scene.Geometry;
import com.jme3.system.AppSettings;
import vcreature.genotype.GenomeCreature;
import vcreature.mutator.Manager;
import vcreature.phenotype.PhysicsConstants;

/**
 * MutationTester is the background application for mutations and Genetic merging.
 * It contains the physics space for actual testing.
 */
public class MutationTester extends SimpleApplication implements ActionListener
{
  private PhysicsSpace physicsSpace;

  private boolean speedSet = false;
  private final int SPEEDSETTING = 20;

  //Temporary vectors used on each frame. They here to avoid instanciating new vectors on each frame
  private GenomeCreature currentCreature;
  private float elapsedSimulationTime;
  private Manager manager;


  public MutationTester(Manager manager)
  {
    this.manager = manager;
  }

  /**
   * Initalizes a BulletAppState and a Physics Space
   * Sets relevant Physics constants
   * Creates AppSettings for this SimpleApp
   * Gets the materials for the graphics
   * Creates a new Creature
   * Sets lighting and camera rotation
   */
  @Override
  public void simpleInitApp()
  {
    BulletAppState bulletAppState = new BulletAppState();
    stateManager.attach(bulletAppState);
    physicsSpace = bulletAppState.getPhysicsSpace();

    physicsSpace.setGravity(PhysicsConstants.GRAVITY);
    physicsSpace.setAccuracy(PhysicsConstants.PHYSICS_UPDATE_RATE);


    AppSettings settings = new AppSettings(true);

    setSettings(settings);
    settings.setResolution(1024, 768);
    this.setPauseOnLostFocus(false);
    //Set up inmovable floor
    com.jme3.scene.shape.Box floor = new com.jme3.scene.shape.Box(50f, 0.1f, 50f);
    Geometry floor_geo = new Geometry("Floor", floor);
    floor_geo.setLocalTranslation(0, -0.11f, 0);
    rootNode.attachChild(floor_geo);

    RigidBodyControl floor_phy = new RigidBodyControl(0.0f);
    floor_geo.addControl(floor_phy);
    physicsSpace.add(floor_phy);
    floor_phy.setFriction(PhysicsConstants.GROUND_SLIDING_FRICTION);
    floor_phy.setRestitution(PhysicsConstants.GROUND_BOUNCINESS);
    floor_phy.setDamping(PhysicsConstants.GROUND_LINEAR_DAMPINING,
                         PhysicsConstants.GROUND_ANGULAR_DAMPINING);

    //Block.initStaticMaterials(assetManager);

    currentCreature = new GenomeCreature(physicsSpace, rootNode, manager.getNextCreature(-1));
  }


  /**
   * Basically the "actionPerformed" of SimpleApplication
   *
   * @param name the type of action being performed
   * @param isPressed was the mouse pressed
   * @param timePerFrame unused seconds per frame.
   */
  public void onAction(String name, boolean isPressed, float timePerFrame)
  {
    if (name.equals("Quit"))
    {
      System.out.format("Creature Fitness (Maximium height of lowest point) = %.3f meters]\n", currentCreature
          .getFitness());
      System.exit(0);
    }
  }

  /**
   * Use the main event loop to trigger repeating actions.
   * @param deltaSeconds change in simulation time seconds.
   */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    if (!this.speedSet)
    {
      this.setSpeed(SPEEDSETTING);
      this.speedSet = true;
    }
    //System.out.println(deltaSeconds);
    float currentFitness = currentCreature.updateBrain(elapsedSimulationTime);
    elapsedSimulationTime += deltaSeconds;

    if (elapsedSimulationTime < 1 && currentFitness > 0.01)
    {
      currentCreature.remove();
      elapsedSimulationTime = 0;
      currentCreature = new GenomeCreature(physicsSpace, rootNode, manager.getNextCreature(0.0f));
      return;
    }
    if (elapsedSimulationTime > 15)
    {
      currentCreature.remove();
      elapsedSimulationTime = 0;

      try
      {
        currentCreature = new GenomeCreature(physicsSpace, rootNode, manager.getNextCreature(currentFitness));
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

    }
  }

  private void setSpeed(int speed)
  {
    this.speed = speed;
    physicsSpace.setMaxSubSteps(4 * speed);
    settings.setFrequency(speed * 60);
    this.restart();
  }
}

