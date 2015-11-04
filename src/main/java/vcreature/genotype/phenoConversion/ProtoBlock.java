package vcreature.genotype.phenoConversion;

import com.jme3.math.Vector3f;
import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;
import vcreature.mainSimulation.MainSim;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.Neuron;

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
  private Vector3f eulerAngles = new Vector3f(0, 0, 0);
  /**
   * Size in meters.
   */
  private Vector3f size;
  private ProtoBlock parent;
  /**
   * List of children blocks.
   */
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

  private Vector3f axis;

  /**
   * The parent hinge point offset relative to parent center.
   */
  private ImmutableVector pivotParentOffset;
  /**
   * The hinge point offset relative to the block's center.
   */
  private ImmutableVector pivotOffset;


  private LinkedList<Neuron> neurons;

  /**
   * Constructor to root node as a ProtoBlock
   *
   * @param size        in meters
   * @param eulerAngles how the block is rotated.
   */
  public ProtoBlock(ImmutableVector size, ImmutableVector eulerAngles)
  {
    this.parent = null; //Null parent because we are the root.
    this.center = new Vector3f(0, 0, 0); //We don't know the actual
    this.size = size.getVector3f();
    this.children = new LinkedList<>();
    this.neurons = new LinkedList<>();
    this.eulerAngles = eulerAngles.getVector3f();
  }

  /**
   * This constructor is for initializing an empty ProtoBlock list for
   * initial parsing.
   */
  public ProtoBlock()
  {
    this.children = new LinkedList<>();
    this.neurons = new LinkedList<>();
  }

  /**
   * Sets up parent ties and basic information for the block. Doesn't compute
   * location or geometries.
   *
   * @param size        the size in meters
   * @param parent      the ProtoBlock parent to be adopted by
   * @param pivotParent the hinge offset on parent block
   * @param pivot       the hinge offset on this block
   * @param axis        the hinge's axis
   */
  public void initializeBlock(ImmutableVector size, ProtoBlock parent, ImmutableVector pivotParent,
                              ImmutableVector pivot, ImmutableVector axis,
                              ImmutableVector eulerAngles)
  {
    this.size = size.getVector3f();
    this.parent = parent;
    this.parent.addChild(this);

    this.axis = axis.getVector3f();

    this.pivotParentOffset = pivotParent;
    this.pivotOffset = pivot;
    this.eulerAngles = eulerAngles.getVector3f();
  }

  /**
   * Get min corner coordinates of block in a vector form
   *
   * @return returns a vector of meter coordinates.
   */
  public Vector3f getMinVector()
  {
    return new Vector3f(center.x - size.x, center.y - size.y, center.z - size.z);
  }

  /**
   * get max corner on cube in a vector form vector
   *
   * @return returns a vector
   */
  public Vector3f getMaxVector()
  {
    return new Vector3f(center.x + size.x, center.y + size.y, center.z + size.z);
  }

  /**
   * Gets the size of the cube from corner to corner in vector form.
   * Hard to explain, if confused ask Tyler to show you the diagram.
   *
   * @return returns a vector
   */
  public Vector3f getDimensionVector()
  {
    return new Vector3f(getMaxVector().x - getMinVector().x, getMaxVector().y - getMinVector().y, getMaxVector().z -
        getMinVector().z);
  }

  /**
   * Calculate if two blocks intersect. It assumes we are axis aligned, no rotations.
   *
   * @param min  meter coordinates for the corner with lowest location
   * @param size The block's size in meters
   * @param box  block we are comparing to.
   * @return true if they intersect.
   */
  public static boolean blockIntersecting(Vector3f min, Vector3f size, ProtoBlock box)
  {
    return (min.x < box.getMinVector().x + box.getDimensionVector().x) &&
        (min.y < box.getMinVector().y + box.getDimensionVector().y) &&
        (min.z < box.getMinVector().z + box.getDimensionVector().z) &&
        (box.getMinVector().x < +min.x + size.x) &&
        (box.getMinVector().y < +min.y + size.y) &&
        (box.getMinVector().z < +min.z + size.z);
  }

  /**
   * Compute the geometry and location. If the block is valid it adds itself to the existing blocks list.
   *
   * @param existingBlocks list of blocks that are valid and will be in the phenome.
   */
  public void computeLocation(LinkedList<ProtoBlock> existingBlocks)
  {
    if (parent != null)
    {
      this.pivot = this.parent.getHingeFromCenterOffset(this.pivotParentOffset);
      this.pivotParentLocal = this.parent.getHingeLocalCoord(this.pivotParentOffset);
      setCenterFromHingeOffset();
      this.pivotLocal = getHingeLocalCoord(this.pivotOffset);
    }
    Vector3f min = getMinVector();
    Vector3f dimensionVector = getDimensionVector();
    for (ProtoBlock box : existingBlocks)
    //Check Block collision somehow.
    //If collision remove child from this.parent.
    {
      //checks to see if two blocks are intersecting
      if (blockIntersecting(min, dimensionVector, box))
      {
        return;
      }
    }

    existingBlocks.add(this);

    for (ProtoBlock child : children)
    {
      child.computeLocation(existingBlocks);
    }
  }

  /**
   * Add a child to this protoblock.
   *
   * @param child block that is the child.
   */
  public void addChild(ProtoBlock child)
  {
    this.children.add(child);
  }

  /**
   * Add a neuron to this block.
   * @param neuron Adds a ProtoNeuron to the block.
   */
  public void addNeuron(Neuron neuron)
  {
    this.neurons.add(neuron);
  }

  /**
   * Returns the hinge point relative to the root's center.
   *
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
   * Calculates the center point relative to the root's center via the known
   * pivot and pivotOffset.
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
   *
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

  /**
   * Gets the height of this block
   *
   * @return height of block in meters.
   */
  public float getHeight()
  {
    float height = -1 * center.y + size.y;
    float tempHeight;
    for (ProtoBlock child : children)
    {
      tempHeight = child.getHeight();
      if (tempHeight > height)
      {
        height = tempHeight;
      }
    }
    return height;
  }

  /**
   * Adds this ProtoBlock and children to a creature. If and only if this block is in the existingBlocks list.
   *
   * @param creature       the creature we are being added to.
   * @param blockParent    the Phenotype Block we are attaching to
   * @param existingBlocks The list of blocks that are permitted to be added
   */
  public void addBlocksToCreature(Creature creature, Block blockParent, LinkedList<ProtoBlock> existingBlocks)
  {
    Block current;
    float[] floats = {eulerAngles.x, eulerAngles.y, eulerAngles.z};
    if (blockParent == null)
    {
      current = creature.addRoot(new Vector3f(0, 1000, 0), size, floats);
      creature.getBlockByID(0).setMaterial(Block.MATERIAL_RED);
    }
    else
    {

      current = creature.addBlock(floats, size, blockParent, pivotParentLocal, pivotLocal, axis);
      switch (MainSim.RANDOM.nextInt(3))
      {
        case 0:
        {
          creature.getBlockByID(current.getID()).setMaterial(Block.MATERIAL_BLUE);
          break;
        }
        case 1:
        {
          creature.getBlockByID(current.getID()).setMaterial(Block.MATERIAL_GREEN);
          break;
        }
        case 2:
        {
          creature.getBlockByID(current.getID()).setMaterial(Block.MATERIAL_BROWN);
          break;
        }
      }
      for (Neuron neuron : neurons)
      {
        current.addNeuron(neuron);
      }
    }

    for (ProtoBlock child : children)
    {
      if (existingBlocks.contains(child))
      {
        child.addBlocksToCreature(creature, current, existingBlocks);
      }
    }
  }

  /**
   * Recreates a genome based upon protoblocks. Used for getting clean genomes of creatures.
   *
   * @param genome      Genome we are adding to
   * @param parentIndex Block index of parent.
   */
  private void addToGenome(Genome genome, int parentIndex)
  {
    if (this.parent == null)
    {
      return; //This must have been the root node.
    }
    int blockIndex = genome.getGENE_BLOCKS().size();
    int parentOffset = blockIndex - parentIndex;
    if (parentIndex == 0)
    {
      parentOffset = 0; //Catch root block parents.
    }

    GeneBlock block = new GeneBlock(parentOffset, this.pivotParentOffset, this.pivotOffset, new ImmutableVector(this
                                                                                                                    .size), new ImmutableVector(this.axis), new ImmutableVector(this.axis), new ImmutableVector(this.eulerAngles));
    genome.addGeneBlock(block);

    for (Neuron protoNeuron : neurons)
    {
      genome.addGeneNeuron(new GeneNeuron(blockIndex, ((ProtoNeuron) protoNeuron).geneNeuron));
    }
    for (ProtoBlock child : children)
    {
      child.addToGenome(genome, blockIndex);
    }
  }

  /**
   * Returns a clean genome without any blocks not shown in phenome.
   *
   * @return the clean genome.
   */
  public Genome createCleanGenomeFromRoot()
  {
    Genome genome = new Genome(new ImmutableVector(this.size), new ImmutableVector(this.eulerAngles));
    for (ProtoBlock child : children)
    {
      child.addToGenome(genome, 0);
    }
    return genome;
  }
}
