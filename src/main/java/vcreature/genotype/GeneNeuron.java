package vcreature.genotype;

import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

/**
 * This class contains data required for building a neuron.
 * This is immutable all members are final.
 *
 * Types (Neuron trigger [height, touch, time, constant, joint angle])
 * Input values (inputs for each type if constant)
 * <p>
 * Binary Operation AB -> X
 * Unary Operation X
 * <p>
 * Binary Operation DE -> Y
 * Unary Operation Y
 */
public final class GeneNeuron
{

  /**
   * The location in the GeneBlock list of this neurons corresponding block
   */
  public final int BLOCK_INDEX;

  /**
   * A's input type.
   */
  public final EnumNeuronInput A_TYPE;
  /**
   * B's input type.
   */
  public final EnumNeuronInput B_TYPE;
  /**
   * C's input type.
   */
  public final EnumNeuronInput C_TYPE;
  /**
   * D's input type.
   */
  public final EnumNeuronInput D_TYPE;
  /**
   * E's input type.
   */
  public final EnumNeuronInput E_TYPE;

  /**
   * A's value Only used if a constant type.
   */
  public final float A_VALUE;
  /**
   * B's input type.
   */
  public final float B_VALUE;
  /**
   * C's input type.
   */
  public final float C_VALUE;
  /**
   * D's input type.
   */
  public final float D_VALUE;
  /**
   * E's input type.
   */
  public final float E_VALUE;

  /**
   * Binary Operator to merge A and B
   */
  public final EnumOperator BINARY_AB;
  /**
   * Unary Operator to apply to the merged AB
   */
  public final EnumOperator UNARY_AB;
  /**
   * Binary Operator to merge D and E
   */
  public final EnumOperator BINARY_DE;
  /**
   * Unary Operator to apply to the merged DE
   */
  public final EnumOperator UNARY_DE;

  /**
   * Create a new GeneNeuron nothing is modifiable after creation. This object is immutable.
   * @param blockIndex The index of the corresponding block found in the GeneBlock list. If index is out of range this neuron is ignored, but still kept for diversity.
   * @param aType A's input type
   * @param bType B's input type
   * @param cType C's input type. This is the standard AB are being compared to, a constant is typical.
   * @param dType D's input type
   * @param eType E's input type
   * @param aValue A's value
   * @param bValue B's value
   * @param cValue C's value
   * @param dValue D's value
   * @param eValue E's value
   * @param binaryAB binary operator to merge A and B
   * @param unaryAB unary operator applied after A and B are merged and before comparison to C
   * @param binaryDE binary operator to merge D and E
   * @param unaryDE unary operator applied before the merged D and E are sent to the hinge.
   */
  public GeneNeuron(int blockIndex, EnumNeuronInput aType, EnumNeuronInput bType, EnumNeuronInput cType,
                    EnumNeuronInput dType, EnumNeuronInput eType, float aValue, float bValue, float cValue, float dValue, float eValue,
                    EnumOperator binaryAB, EnumOperator unaryAB, EnumOperator binaryDE, EnumOperator unaryDE)
  {
    BLOCK_INDEX = blockIndex;
    A_TYPE = aType;
    B_TYPE = bType;
    C_TYPE = cType;
    D_TYPE = dType;
    E_TYPE = eType;
    A_VALUE = aValue;
    B_VALUE = bValue;
    C_VALUE = cValue;
    D_VALUE = dValue;
    E_VALUE = eValue;
    BINARY_AB = binaryAB;
    UNARY_AB = unaryAB;
    BINARY_DE = binaryDE;
    UNARY_DE = unaryDE;
  }
}
