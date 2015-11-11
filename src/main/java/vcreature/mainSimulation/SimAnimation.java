package vcreature.mainSimulation;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
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
import vcreature.genotype.Genome;
import vcreature.genotype.GenomeCreature;
import vcreature.mutator.Manager;
import vcreature.phenotype.Block;
import vcreature.phenotype.PhysicsConstants;

import java.util.logging.Level;

/**
 * Sim Animation simulates the animation inside of the GUI
 **/
public class SimAnimation extends SimpleApplication implements ActionListener
{
  private PhysicsSpace physicsSpace;
  private float cameraAngle = (float) (Math.PI / 2.0);


  //Temporary vectors used on each frame. They are here to avoid instantiating new vectors on each frame
  private Vector3f cameraLocation = new Vector3f();
  private boolean isCameraRotating = true;
  private GenomeCreature myCreature;
  private GenomeCreature tempCreature;
  private float elapsedSimulationTime;

  //  private Genome fileGenome = SpawnCreatureGenoform.creature();
  private Genome fileGenome;
  private final Manager MANAGER;
  private int zoom = 25;

  /**
   * Sets the manager that the GUI is using
   *
   * @param manager picking a manger to use
   */
  public SimAnimation(Manager manager)
  {
    this.MANAGER = manager;
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
    // GenoFile.writeGenome(fileGenome);
    java.util.logging.Logger.getLogger("").setLevel(Level.OFF);

    BulletAppState bulletAppState = new BulletAppState();
    stateManager.attach(bulletAppState);
    physicsSpace = bulletAppState.getPhysicsSpace();
    physicsSpace.setGravity(PhysicsConstants.GRAVITY);
    physicsSpace.setAccuracy(PhysicsConstants.PHYSICS_UPDATE_RATE);
    physicsSpace.setMaxSubSteps(4);
    this.speed = 4;
    AppSettings settings = new AppSettings(true);
    settings.setAudioRenderer(null);
    settings.setFrameRate(60);
    settings.setResolution(800, 600);
    setSettings(settings);
    this.setPauseOnLostFocus(false);
    //Set up immovable floor
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

    myCreature = new GenomeCreature(physicsSpace, rootNode, MANAGER.getNextCreature(-1));
    // myCreature =new GenomeCreature(physicsSpace,rootNode,SpawnCreatureGenoform.makeFlappyBird());
    //genePool.addCreatureToPopulation();
    initLighting();
    initKeys();
    flyCam.setDragToRotate(true);
  }

  /**
   * Creates lighting for SimpleApp
   */
  private void initLighting()
  {
    //  ust add a light to make the lit object visible!
    DirectionalLight sun = new DirectionalLight();
    sun.setDirection(new Vector3f(0, -10, -2).normalizeLocal());
    sun.setColor(ColorRGBA.White);
    rootNode.addLight(sun);

    //Without ambient light, the seen looks like outerspace with razer sharp black shadows.
    AmbientLight ambient = new AmbientLight();
    ambient.setColor(ColorRGBA.White.mult(0.3f));
    rootNode.addLight(ambient);

    // SHADOW
    // the second parameter is the resolution. Experiment with it! (Must be a power of 2)
    DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, 2048, 2);
    dlsr.setLight(sun);
    viewPort.addProcessor(dlsr);
  }

  /**
   * Basically the "actionPerformed" of SimpleApplication
   *
   * @param name         name of action being performed
   * @param isPressed    was the mouse pressed
   * @param timePerFrame seconds per frame unused.
   */
  public void onAction(String name, boolean isPressed, float timePerFrame)
  {
    if (isPressed && name.equals("Toggle Camera Rotation"))
    {
      isCameraRotating = !isCameraRotating;
    }
    else if (name.equals("Quit"))
    {
      System.out.format("Creature Fitness (Maximium height of lowest point) = %.3f meters]\n", myCreature.getFitness());

      System.exit(0);
    }
  }

  /**
   * Called when user sets speed in GUI slider
   *
   * @param speed the speed that the animation runs
   */
  public void setSpeed(int speed)
  {
    this.speed = speed;
    physicsSpace.setMaxSubSteps(speed);
    settings.setFrequency(speed * 60);
    this.restart();
  }

  /**
   * Sets useful keybindings for user
   */
  private void initKeys()
  {
    inputManager.addMapping("Quit", new KeyTrigger(KeyInput.KEY_Q));
    inputManager.addMapping("Toggle Camera Rotation", new KeyTrigger(KeyInput.KEY_P));

    // Add the names to the action listener.
    inputManager.addListener(this, "Quit");
    inputManager.addListener(this, "Toggle Camera Rotation");
  }

  /**
   * Called when user slides zoom toggle in GUI
   * Sets zoom value which is later used to either lengthen or diminish the camera perspective vector
   *
   * @param zoom how much we want to zoom in or out.
   */
  protected void setZoom(int zoom)
  {
    this.zoom = zoom;
  }

  /**
   * Use the main event loop to trigger repeating actions.
   *
   * @param deltaSeconds the change in seconds
   */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    myCreature.updateBrain(elapsedSimulationTime);
    elapsedSimulationTime += deltaSeconds;
    if (elapsedSimulationTime > 15)
    {
      myCreature.remove();
      elapsedSimulationTime = 0;
      if (fileGenome != null)
      {
        myCreature = new GenomeCreature(physicsSpace, rootNode, fileGenome);
      }
      else
      {

        myCreature = new GenomeCreature(physicsSpace, rootNode, MANAGER.getCurrentGenome());
      }
    }

    if (isCameraRotating)
    {
      //Move camera continuously in circle of radius 25 meters centered 10 meters
      //  above the origin.
      cameraAngle += deltaSeconds * 2.0 * Math.PI / 60.0; //rotate full circle every minute
      float x = (float) (zoom * Math.cos(cameraAngle));
      float z = (float) (zoom * Math.sin(cameraAngle));

      cameraLocation.set(x, 10.0f, z);
      cam.setLocation(cameraLocation);
      cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
    }
  }

  /**
   * Used for loading creatures from file / creature selection from gene pool
   * Sets fileGenome, which is only used inside of simpleUpdate if fileGenome is not null
   *
   * @param creature a creature that the genome is going to use.
   */
  public void setCurrentCreature(Genome creature)
  {
    tempCreature = myCreature;
    fileGenome = creature;
  }

  /**
   * Get the fitness of the current creature being displayed
   *
   * @return fitness of myCreature
   */
  public float getCurrentCreatureFitness()
  {
    if (myCreature == null)
    {
      return 0.00f;
    }
    return myCreature.getFitness();
  }

  public void switchToMyCreatureView()
  {
    myCreature = tempCreature;
  }
}

