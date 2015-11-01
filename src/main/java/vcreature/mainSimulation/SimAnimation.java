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
import vcreature.mutator.HillClimbingManager;
import vcreature.mutator.Manager;
import vcreature.phenotype.Block;
import vcreature.phenotype.PhysicsConstants;

/**
 * Created by Tess Daughton on 10/14/15.
 * Joel's code with minor changes
 **/
public class SimAnimation extends SimpleApplication implements ActionListener
{
//  public static GenePool genePool = new GenePool();
  private BulletAppState bulletAppState;
  private PhysicsSpace physicsSpace;
  private float cameraAngle = (float) (Math.PI / 2.0);
  private boolean gamePause;


  //Temporary vectors used on each frame. They here to avoid instanciating new vectors on each frame
  private Vector3f tmpVec3; //
  private boolean isCameraRotating = true;
  private GenomeCreature myCreature;
  private float elapsedSimulationTime;

  private Manager manager = new Manager();
  private float fitnessUpdater = 0;
  private float currentFitness = 0;
  private float previousFitness = 0;
  private int zoom = 25;


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
    this.speed = 4;
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

    myCreature = new GenomeCreature(physicsSpace, rootNode, manager.getNextCreature(-1));
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
   * @param name
   * @param isPressed
   * @param timePerFrame
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

  public void setSpeed(int speed)
  {
    this.speed=speed;
    physicsSpace.setMaxSubSteps(4*speed);
    settings.setFrequency(speed*60);
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

  private void setCurrentFitness()
  {
    currentFitness = currentFitness-previousFitness;
  }
  protected void setZoom(int zoom)
  {
    this.zoom = zoom;
  }
  protected float getCurrentFitness()
  {
    return currentFitness;
  }



  /**
   * Use the main event loop to trigger repeating actions.
   */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    this.currentFitness = myCreature.updateBrain(elapsedSimulationTime);
    elapsedSimulationTime += deltaSeconds;
    fitnessUpdater+=deltaSeconds;

    if(elapsedSimulationTime<1 && this.currentFitness>0.01)
    {
      myCreature.remove();
      myCreature = new GenomeCreature(physicsSpace, rootNode, manager.getNextCreature(0));
      return;
    }
    if (elapsedSimulationTime > 15)
    {
      myCreature.remove();
      elapsedSimulationTime = 0;

      myCreature = new GenomeCreature(physicsSpace, rootNode, manager.getNextCreature(this.currentFitness));
    }

    //This is the timer for updating the fitness per minute in the GUI.
    if(fitnessUpdater==60)
    {
      setCurrentFitness();
      fitnessUpdater=0;
    }

    if (isCameraRotating)
    {
      //Move camera continously in circle of radius 25 meters centered 10 meters
      //  above the origin.
      cameraAngle += deltaSeconds * 2.0 * Math.PI / 60.0; //rotate full circle every minute
      float x = (float) (zoom * Math.cos(cameraAngle));
      float z = (float) (zoom * Math.sin(cameraAngle));

      tmpVec3 = new Vector3f(x, 10.0f, z);
      cam.setLocation(tmpVec3);
      cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
    }
  }
}

