package vcreature.phenotype;



import com.jme3.bullet.PhysicsSpace;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.bullet.joints.HingeJoint;
import com.jme3.scene.Node;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import java.util.ArrayList;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.RigidBodyControl;

/**
 * The Class Block.
 */
public class Block
{
  public static final float BLOCK_DENSITY = 4f; //Killograms per cubic meter.
  public static Material MATERIAL_RED;
  public static Material MATERIAL_BLUE;
  public static Material MATERIAL_GREEN;
  public static Material MATERIAL_BROWN;
  public static Material MATERIAL_GRAY;
  
  private final float sizeX, sizeY, sizeZ; //meters
  private final Vector3f startCenter; //meters
  private final Vector3f[] cornerVectors = new Vector3f[8];
  private final int id; //Assigned when added to Creature. 0=root and +1 for each block added in order the blocks are added. This is used by DNA and logic curits
  
  private Block parent;
  private HingeJoint jointToParent;
  private ArrayList<Neuron> neuronTable = new ArrayList<Neuron>();
  private ArrayList<Block> childList    = new ArrayList<Block>();
  private Geometry geometry;
  private RigidBodyControl physicsControl;
  
  //Temporary vectors used on each frame. They here to avoid instanciating new vectors on each frame
  private Vector3f tmpVec3; //
  
  
  //Creates a box that has a center of 0,0,0 and extends in the out from 
    //the center by the given amount in each direction. 
    // So, for example, a box with extent of 0.5 would be the unit cube.
  public Block(PhysicsSpace physicsSpace, Node rootNode, int id, Vector3f center, Vector3f size) 
  { 
    if (size.x < 0.5f || size.y < 0.5f || size.z < 0.5f) 
    { throw new IllegalArgumentException("No dimension may be less than 0.5 from block's center: ("+vectorToStr(size));
    }
    
    if (max(size) > 10*min(size))
    {  throw new IllegalArgumentException("Largest dimension must be no more than 10x the smallest: ("+vectorToStr(size));
    }
    
    this.id = id;
    startCenter = new Vector3f(center);
    sizeX = size.x*2;
    sizeY = size.y*2;
    sizeZ = size.z*2;

    cornerVectors[0] = new Vector3f(center.x-size.x, center.y+size.y,center.z-size.z); // top,front,left
    cornerVectors[1] = new Vector3f(center.x+size.x, center.y+size.y,center.z-size.z); // top,front,right
    cornerVectors[2] = new Vector3f(center.x-size.x, center.y-size.y,center.z-size.z); // bottom,front,left
    cornerVectors[3] = new Vector3f(center.x+size.x, center.y-size.y,center.z-size.z); // bottom,front,right
    cornerVectors[4] = new Vector3f(center.x-size.x, center.y+size.y,center.z+size.z); // top,back,left
    cornerVectors[5] = new Vector3f(center.x+size.x, center.y+size.y,center.z+size.z); // top,back,right
    cornerVectors[6] = new Vector3f(center.x-size.x, center.y-size.y,center.z+size.z); // bottom,back,left
    cornerVectors[7] = new Vector3f(center.x+size.x, center.y-size.y,center.z+size.z); // bottom,back,right

    //Creates a box that has a center of 0,0,0 and extends in the out from 
    //the center by the given amount in each direction. 
    // So, for example, a box with extent of 0.5 would be the unit cube.
    Box box = new Box(size.x, size.y, size.z);
    geometry = new Geometry("Box", box);
    geometry.setMaterial(MATERIAL_GRAY);
    rootNode.attachChild(geometry);
    geometry.setShadowMode(ShadowMode.Cast);
    
    BoxCollisionShape collisionBox = new BoxCollisionShape(size.mult(0.95f));
    physicsControl = new RigidBodyControl(collisionBox, getMass());
    geometry.addControl(physicsControl);
    physicsControl.setPhysicsLocation(center);
    physicsSpace.add(physicsControl);
    physicsControl.setRestitution(PhysicsConstants.BLOCK_BOUNCINESS);
    physicsControl.setFriction(PhysicsConstants.SLIDING_FRICTION);
    physicsControl.setDamping(PhysicsConstants.LINEAR_DAMPINING, 
            PhysicsConstants.ANGULAR_DAMPINING);
  }
  
  private void addChild(Block child) {childList.add(child);}
  
  public void setMaterial(Material mat)
  {
    geometry.setMaterial(mat);
  }


  public void setJointToParent(Block parent, HingeJoint joint)
  {
     jointToParent = joint;
     parent.addChild(this);
     this.parent = parent;
  }

  public void addNeuron(Neuron neuron)
  {
     neuronTable.add(neuron);
  }
  
  public Geometry getGeometry() {return geometry;}
  public RigidBodyControl getPhysicsControl() {return physicsControl;}
  public HingeJoint getJoint(){ return jointToParent;}
  
  public int getID() {return id;}

  public Block getParent(){
   return parent;
  }

  public int getIdOfParent(){
    return parent.getID();}

  /**
   * gets center vector of the cube
   * @return
   */
  public Vector3f getCenter() {return startCenter;}

  public Vector3f getCorner1() {return cornerVectors[0];}
  public Vector3f getCorner2() {return cornerVectors[1];}
  public Vector3f getCorner3() {return cornerVectors[2];}
  public Vector3f getCorner4() {return cornerVectors[3];}
  public Vector3f getCorner5() {return cornerVectors[4];}
  public Vector3f getCorner6() {return cornerVectors[5];}
  public Vector3f getCorner7() {return cornerVectors[6];}
  public Vector3f getCorner8() {return cornerVectors[7];}

  // cannot use numbers greater than seven or less than zero.
  public Vector3f getCornerVector(int cornerNumber) {return cornerVectors[cornerNumber];}

  /**
   * Gets the bottom coordinate of the Block
   * @return
   */
  public float getBottomYOfCube() { return getCenter().y-(sizeY/2);}
  /**
   * Gets the top face coordinate of the Block
   * @return
   */
  public float getTopYOfCube() { return getCenter().y+(sizeY/2);}
  /**
   * Gets the right face coordinate of the Block
   * @return
   */
  public float getRightXOfCube() { return getCenter().x+(sizeX/2);}
  /**
   * Gets the left face coordinate of the Block
   * @return
   */
  public float getLeftXOfCube() { return getCenter().x-(sizeX/2);}
  /**
   * Gets the front face coordinate of the Block
   * @return
   */
  public float getFrontZOfCube() { return getCenter().z-(sizeZ/2);}
  /**
   * Gets the back face coordinate of the Block
   * @return
   */
  public float getBackZOfCube() { return getCenter().z+(sizeZ/2);}


  public float getSizeX() {return sizeX;}
  

  public float getSizeY() {return sizeY;}
  

  public float getSize() {return sizeZ;}

  public float getSurfaceArea() {return  2*sizeX*sizeY + 2*sizeX*sizeZ + 2*sizeY*sizeZ;}

  public Vector3f getStartCenter(){return startCenter;}

  public ArrayList<Neuron> getNeuronTable() { return neuronTable;}
  
  
  public static void initStaticMaterials(AssetManager assetManager)
  {
    MATERIAL_RED   = initStaticMaterial(assetManager, ColorRGBA.Red);
    MATERIAL_BLUE  = initStaticMaterial(assetManager, ColorRGBA.Blue);
    MATERIAL_GREEN = initStaticMaterial(assetManager, ColorRGBA.Green);
    MATERIAL_BROWN = initStaticMaterial(assetManager, ColorRGBA.Brown);
    MATERIAL_GRAY  = initStaticMaterial(assetManager, ColorRGBA.Gray);
  }
  
  private static Material initStaticMaterial(AssetManager assetManager, ColorRGBA color)
  {
    Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
    mat.setBoolean("UseMaterialColors", true);
    mat.setColor("Ambient", color);
    mat.setColor("Diffuse", color);
    mat.setColor("Specular", color);
    mat.setFloat("Shininess", 64f);  // [0,128]
    return mat;
  }
  
  
  public String toString()
  {
    String s = "Block["+id+"]: {size X, size Y, size Z} {" + sizeX + ", " + sizeY + ", " + sizeZ + "}\n";
    
    if (parent == null)
    { s += "     ROOT\n\n";
    }
    else
    {
      s += "     Parent: [ID=" + parent.getID() + "]: " + jointToParent + "\n"; 
    }
    return s;
  }
  
  
   //Return the maximium impulse that can be supplyed by a joint on the given parent.
  //The maximium impulse that can be applyed to a joint is proportional to the parent's surface area.
  //The maximium impulse is returned in Newton seconds.
public final float getJointMaxImpulse()
  {
    
    return parent.sizeX*parent.sizeY + parent.sizeY*parent.sizeZ + parent.sizeZ*parent.sizeX;
  }


public final float getMass()
  {
    return sizeX*sizeY*sizeZ*BLOCK_DENSITY;
  }

public static float min(Vector3f vec)
{
  if (vec.x <= vec.y && vec.x <= vec.z) return vec.x;
  if (vec.y <= vec.x && vec.y <= vec.z) return vec.y;
  return vec.z;
}

public static float max(Vector3f vec)
{
  if (vec.x >= vec.y && vec.x >= vec.z) return vec.x;
  if (vec.y >= vec.x && vec.y >= vec.z) return vec.y;
  return vec.z;
}

public static String vectorToStr(Vector3f vec)
{
  return "("+vec.x +", "+vec.y +", "+vec.z +")";
}

}