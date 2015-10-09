package vcreature.genotype;

/**
 * This Class contains the basic information for creating a block and hinge pair.
 * <p>
 * All members are finalized to prevent corruption in a multithreaded environment and allow simplified replication of
 * genes across creatures.
 * <p>
 * Parent Block (0=root  +/- number is offset in array to parent.)
 * PivotParent (Percentage from center in each dimension)
 * PivotSelf (Percentage from center in each dimension)
 * (I think this is computable from PivotSelf/PivotParent and Size) Center Offset (Percentage of size to move to get
 * center point. Something like offset*size/2 = center)
 * Size (3d tuple)
 * <p>
 * Parent Axis
 * Self Axis
 * <p>
 * Types (Neuron trigger [height, touch, time, constant, joint angle])
 * Input values (inputs for each type if constant)
 * <p>
 * Binary Operation AB -> X
 * Unary Operation X
 * <p>
 * Binary Operation DE -> Y
 * Unary Operation Y
 */
public final class GeneBlock
{

  /**
   * The index offset to the parent block.
   * i is the PARENT_OFFSET
   * i = 0 means parent is the root block.
   * i > 0 means parent is located i places after the current index.
   * i < 0 means parent is located i places before the current index.
   */
  public final int PARENT_OFFSET;

  /**
   * The parent block's hinge location in percentage from center.
   */
  public final ImmutableVector PARENT_PIVOT;

  /**
   * The block's hinge location in percentage from center.
   */
  public final ImmutableVector PIVOT;

  /**
   * The blocks size in distance
   */
  public final ImmutableVector SIZE;

  /**
   * The axis on the parent to rotate upon.
   */
  public final Axis PARENT_HINGE_AXIS;

  /**
   * The axis on to rotate upon.
   */
  public final Axis HINGE_AXIS;

  public GeneBlock(int parentOffset, ImmutableVector parentPivot, ImmutableVector pivot, ImmutableVector size,
                   Axis parentHingeAxis, Axis hingeAxis)
  {
    PARENT_OFFSET = parentOffset;
    PARENT_PIVOT = parentPivot;
    PIVOT = pivot;
    SIZE = size;
    PARENT_HINGE_AXIS = parentHingeAxis;
    HINGE_AXIS = hingeAxis;
  }

}
