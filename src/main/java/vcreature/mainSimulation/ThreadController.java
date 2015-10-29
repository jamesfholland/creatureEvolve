package vcreature.mainSimulation;

    import com.jme3.app.SimpleApplication;
    import com.jme3.bullet.BulletAppState;
    import com.jme3.bullet.PhysicsSpace;
    import com.jme3.bullet.control.RigidBodyControl;
    import com.jme3.input.KeyInput;
    import com.jme3.input.controls.KeyTrigger;
    import com.jme3.light.AmbientLight;
    import com.jme3.light.DirectionalLight;
    import com.jme3.material.Material;
    import com.jme3.math.ColorRGBA;
    import com.jme3.math.Vector2f;
    import com.jme3.math.Vector3f;
    import com.jme3.renderer.queue.RenderQueue;
    import com.jme3.scene.Geometry;
    import com.jme3.shadow.DirectionalLightShadowRenderer;
    import com.jme3.system.AppSettings;
    import com.jme3.texture.Texture;
    import com.jme3.input.controls.ActionListener;
    import vcreature.genotype.GenomeCreature;
    import vcreature.mutator.MutationManager;
    import vcreature.phenotype.Block;
    import vcreature.phenotype.Creature;
    import vcreature.phenotype.PhysicsConstants;
/**
 * Created by L301126 on 10/28/15.
 */
public class ThreadController extends SimpleApplication implements Runnable
{
  //  public static GenePool genePool = new GenePool();
  private BulletAppState bulletAppState;
  private PhysicsSpace physicsSpace;


  //Temporary vectors used on each frame. They here to avoid instanciating new vectors on each frame
  private Creature myCreature;
  private float elapsedSimulationTime;
  private MutationManager mutationManager = new MutationManager();
  private float currentFitness = 0;

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
    this.speed = 20;
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

    myCreature = new GenomeCreature(physicsSpace, rootNode, mutationManager.getNextCreature(-1));
    //genePool.addCreatureToPopulation();

  }

  /**
   * Use the main event loop to trigger repeating actions.
   */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    this.currentFitness = myCreature.updateBrain(elapsedSimulationTime);
    elapsedSimulationTime += deltaSeconds;
    if (elapsedSimulationTime > 15)
    {
      myCreature.remove();
      elapsedSimulationTime = 0;

      myCreature = new GenomeCreature(physicsSpace, rootNode, mutationManager.getNextCreature(this.currentFitness));
    }
  }

  @Override
  public void run()
  {

  }
}

