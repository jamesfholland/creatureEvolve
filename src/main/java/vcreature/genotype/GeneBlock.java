package vcreature.genotype;

import com.jme3.math.Vector3f;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

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
  public final ImmutableVector PARENT_HINGE_AXIS;

  /**
   * The axis on to rotate upon.
   */
  public final ImmutableVector HINGE_AXIS;
  /**
   *
   */
  public final ImmutableVector EULER_ANGLES;

  /**
   * Create a new GeneBlock, this object is immutable.
   *
   * @param parentOffset    The index offset to the parent block.
   *                        i is the PARENT_OFFSET
   *                        i = 0 means parent is the root block.
   *                        i > 0 means parent is located i places after the current index.
   *                        i < 0 means parent is located i places before the current index.
   * @param parentPivot     The parent block's hinge location in percentage from center.
   * @param pivot           The block's hinge location in percentage from center.
   * @param size            The blocks size in distance
   * @param parentHingeAxis The axis on the parent to rotate upon.
   * @param hingeAxis       The axis on to rotate upon.
   * @param eulerAngles
   */
  public GeneBlock(int parentOffset, ImmutableVector parentPivot, ImmutableVector pivot, ImmutableVector size,
                   ImmutableVector parentHingeAxis, ImmutableVector hingeAxis,ImmutableVector eulerAngles)
  {
    PARENT_OFFSET = parentOffset;
    PARENT_PIVOT = parentPivot;
    PIVOT = pivot;
    SIZE = size;
    PARENT_HINGE_AXIS = parentHingeAxis;
    HINGE_AXIS = hingeAxis;
    EULER_ANGLES = eulerAngles;
  }

  public GeneBlock(BufferedReader fileIn) throws IOException
  {
    PARENT_OFFSET = Integer.parseInt(fileIn.readLine());
    PARENT_PIVOT = new ImmutableVector(fileIn);
    PIVOT = new ImmutableVector(fileIn);
    SIZE = new ImmutableVector(fileIn);
    PARENT_HINGE_AXIS = new ImmutableVector(fileIn);
    HINGE_AXIS = new ImmutableVector(fileIn);
    EULER_ANGLES= new ImmutableVector(fileIn);
  }


  public void toFile(BufferedWriter fileOut) throws IOException
  {
    fileOut.write("#Block\n");
    fileOut.write(String.format("%d\n", PARENT_OFFSET));
    PARENT_PIVOT.toFile(fileOut);
    PIVOT.toFile(fileOut);
    SIZE.toFile(fileOut);
    PARENT_HINGE_AXIS.toFile(fileOut);
    HINGE_AXIS.toFile(fileOut);
    EULER_ANGLES.toFile(fileOut);
  }

  /**
   * This is overridden to maintain stability in genome hashes between runs.
   * @return an integer that is the hash.
   */
  @Override
  public int hashCode()
  {
    return Objects.hash(PARENT_OFFSET, PARENT_PIVOT, PIVOT, SIZE, PARENT_HINGE_AXIS, HINGE_AXIS, EULER_ANGLES);
  }
}
