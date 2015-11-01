package vcreature.mainSimulation;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import vcreature.genotype.GenomeCreature;
import vcreature.mutator.HillClimbingManager;
import vcreature.mutator.hillclimbing.*;
import vcreature.phenotype.Block;
import vcreature.phenotype.PhysicsConstants;
import vcreature.genotype.Genome;
/**
 * Created by Tess Daughton on 10/28/15.
 */
public class MutationTester extends SimpleApplication
{
  private BulletAppState bulletAppState;
  private PhysicsSpace physicsSpace;


  //Temporary vectors used on each frame. They here to avoid instanciating new vectors on each frame
  private GenomeCreature currentCreature;
  private Genome bestCreatureGenome;
  private Genome testGenome;
  private Genome currentGenome;
  private float elapsedSimulationTime;
  private HillClimbingManager hillClimbingManager = new HillClimbingManager();
  private float bestFitness = 0;
  private float currentFitness = 0;
  private Mutators mutators[] = Mutators.values();
  private int indexOfCurrentMutator = 0;

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
    bulletAppState = new BulletAppState();
    stateManager.attach(bulletAppState);
    physicsSpace = bulletAppState.getPhysicsSpace();

    physicsSpace.setGravity(PhysicsConstants.GRAVITY);
    physicsSpace.setAccuracy(PhysicsConstants.PHYSICS_UPDATE_RATE);
    physicsSpace.setMaxSubSteps(4);
    this.speed = 100;
    AppSettings settings = new AppSettings(true);
    setSettings(settings);
    settings.setResolution(1024, 768);
    this.setPauseOnLostFocus(false);
    //Set up inmovable floor
    com.jme3.scene.shape.Box floor = new com.jme3.scene.shape.Box(50f, 0.1f, 50f);
    Material floor_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    Texture floorTexture = assetManager.loadTexture("Textures/FloorTile.png");
    floorTexture.setWrap(Texture.WrapMode.Repeat);
    floor_mat.setTexture("ColorMap", floorTexture);
    floor.scaleTextureCoordinates(new Vector2f(50, 50));
    Geometry floor_geo = new Geometry("Floor", floor);
    floor_geo.setMaterial(floor_mat);
    floor_geo.setShadowMode(RenderQueue.ShadowMode.Receive);
    floor_geo.setLocalTranslation(0, -0.11f, 0);
    rootNode.attachChild(floor_geo);

    RigidBodyControl floor_phy = new RigidBodyControl(0.0f);
    floor_geo.addControl(floor_phy);
    physicsSpace.add(floor_phy);
    floor_phy.setFriction(PhysicsConstants.GROUND_SLIDING_FRICTION);
    floor_phy.setRestitution(PhysicsConstants.GROUND_BOUNCINESS);
    floor_phy.setDamping(PhysicsConstants.GROUND_LINEAR_DAMPINING,
        PhysicsConstants.GROUND_ANGULAR_DAMPINING);

    Block.initStaticMaterials(assetManager);

    currentCreature = new GenomeCreature(physicsSpace, rootNode, hillClimbingManager.getNextCreature(-1));
    currentGenome = currentCreature.getGenome();
    bestCreatureGenome = currentGenome;
    testGenome = mutators[indexOfCurrentMutator].mutate(currentGenome);
    indexOfCurrentMutator++;
    //genePool.addCreatureToPopulation();

  }

  /**
   * Use the main event loop to trigger repeating actions.
   */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    if(elapsedSimulationTime==15)
    {
      testGenome = mutators[indexOfCurrentMutator].mutate(currentGenome);
      indexOfCurrentMutator++;
      if(indexOfCurrentMutator>=mutators.length-1)
      {
        indexOfCurrentMutator=0;
        hillClimbingManager.getNextCreature(bestCreatureGenome.getFitness());
      }
    }
    this.currentFitness = currentCreature.updateBrain(elapsedSimulationTime);
    elapsedSimulationTime += deltaSeconds;
    if (elapsedSimulationTime > 15)
    {
      currentCreature.remove();
      elapsedSimulationTime = 0;
      currentCreature = new GenomeCreature(physicsSpace, rootNode, testGenome);
    }
    if (currentCreature.getFitness() > bestFitness)
    {
      bestFitness = currentCreature.getFitness();
      bestCreatureGenome = testGenome;
    }
  }

  public Genome getBestCreatureGenome()
  {
    return bestCreatureGenome;
  }
}

