package vcreature.genotype.phenoConversion;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import vcreature.genotype.Axis;
import vcreature.genotype.ImmutableVector;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This is a ProtoBlock for the genotype to phenotype conversion.
 */
public class ProtoBlock
{
  /**
   * Offset from the root's 0,0,0 center.
   */
  private Vector3f center;
  /**
   * Size in meters.
   */
  private Vector3f size;
  private ProtoBlock parent;
  private LinkedList<ProtoBlock> children;

  /**
   * The pivot point relative to the root.
   */
  private Vector3f pivot;

  /**
   * Pivot point relative to center of Parent block.
   */
  private Vector3f pivotParentLocal;
  /**
   * Pivot point relative to center of block.
   */
  private Vector3f pivotLocal;

  private Vector3f axisParent;
  private Vector3f axis;

  /**
   * The parent hinge point offset relative to parent center.
   */
  private ImmutableVector pivotParentOffset;
  /**
   * The hinge point offset relative to the block's center.
   */
  private ImmutableVector pivotOffset;


  /**
   * Used for collision detection.
   */
  private BoundingBox boundingBox;

  /**
   * This is used for the root vector
   * @param size The size in meters of the block
   */
  public ProtoBlock(ImmutableVector size)
  {
    this.parent = null; //Null parent because we are the root.
    this.center = new Vector3f(0,0,0); //We don't know the actual
    this.size = size.getVector3f();
    this.children = new LinkedList<>();
  }

  /**
   * This constructor is for initializing an empty ProtoBlock list for initial parsing.
   */
  public ProtoBlock()
  {
    this.children = new LinkedList<>();
  }

  /**
   * Sets up parent ties and basic information for the block. Doesn't compute location or geometries.
   * @param size the size in meters
   * @param parent the ProtoBlock parent to be adopted by
   * @param pivotParent the hinge offset on parent block
   * @param pivot the hinge offset on this block
   * @param axisParent the hinge's parent axis
   * @param axis the hinge's axis
   */
  public void initializeBlock(ImmutableVector size, ProtoBlock parent, ImmutableVector pivotParent, ImmutableVector pivot, Axis axisParent, Axis axis)
  {
    this.size = size.getVector3f();
    this.parent = parent;
    this.parent.addChild(this);

    this.axisParent = axisParent.getVector3f();
    this.axis = axis.getVector3f();

    this.pivotParentOffset = pivotParent;
    this.pivotOffset = pivot;

  }

  /**
   * Compute the geometry and location. If not valid removes itself from the parent.
   * @param existingBlocks
   */
  public void computeLocation(LinkedList<BoundingBox> existingBlocks)
  {
    if(parent != null)
    {
      this.pivot = this.parent.getHingeFromCenterOffset(this.pivotParentOffset);
      this.pivotParentLocal = this.parent.getHingeLocalCoord(this.pivotParentOffset);
      setCenterFromHingeOffset();
      this.pivotLocal = this.parent.getHingeLocalCoord(this.pivotOffset);
    }

      this.boundingBox = new BoundingBox(this.center, size.x, size.y, size.z);

      //Check collisions with existing blocks.
      for (BoundingBox box : existingBlocks)
      {
        //Check Block collision somehow.
        //If collision remove child from this.parent.
        if (false)
        {
          this.parent.removeChild(this);
          return;
        }
      }


    existingBlocks.add(this.boundingBox);

    for(ProtoBlock child: children)
    {
      child.computeLocation(existingBlocks);
    }

  }

  /**
   * Add a child to this protoblock.
   * @param child
   */
  public void addChild(ProtoBlock child)
  {
    this.children.add(child);
  }

  /**
   * Removes an unwanted or invalid child
   * @param child to be removed.
   */
  public void removeChild(ProtoBlock child)
  {
    this.children.remove(child);
  }

  /**
   * Returns the hinge point relative to the root's center.
   * @param hingeOffset offset on this block to use to find the hinge point.
   * @return the actual point relative to root.
   */
  public Vector3f getHingeFromCenterOffset(ImmutableVector hingeOffset)
  {
    float x = center.x + size.x * hingeOffset.X;
    float y = center.y + size.y * hingeOffset.Y;
    float z = center.z + size.z * hingeOffset.Z;
    return new Vector3f(x, y, z);
  }

  /**
   * Calculates the center point relative to the root's center via the known pivot and pivotOffset.
   */
  private void setCenterFromHingeOffset()
  {
    float x = pivot.x - size.x * pivotOffset.X;
    float y = pivot.y - size.y * pivotOffset.Y;
    float z = pivot.z - size.z * pivotOffset.Z;
    this.center = new Vector3f(x, y, z);
  }

  /**
   * Calculate the block coordinate of hinge from size and offset
   * @param hingeOffset the locale offset percentages
   * @return locale coordinates of the hinge
   */
  public Vector3f getHingeLocalCoord(ImmutableVector hingeOffset)
  {
    float x = size.x * hingeOffset.X;
    float y = size.y * hingeOffset.Y;
    float z = size.z * hingeOffset.Z;
    return new Vector3f(x, y, z);
  }

  public float getHeight()
  {
    float height = -1 * center.y + size.y;
    float tempHeight;
    for(ProtoBlock child : children)
    {
      tempHeight = child.getHeight();
      if(tempHeight > height) height = tempHeight;
    }

    return height;
  }

  public void addBlocksToCreature(Creature creature,  float heightOffset, Block blockParent)
  {
    Vector3f newCenter = new Vector3f(center.x, center.y + heightOffset, center.z);
    Block current;
    if(blockParent == null)
    {
      current = creature.addRoot(newCenter, size);
    }
    else
    {
      current = creature.addBlock(newCenter, size, blockParent, pivotParentLocal, pivotLocal,axisParent, axis);
    }

    for(ProtoBlock child : children)
    {
      child.addBlocksToCreature(creature, heightOffset, current);
    }

  }

}
