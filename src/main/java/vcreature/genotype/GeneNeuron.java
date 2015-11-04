package vcreature.genotype;

import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * This class contains data required for building a neuron.
 * This is immutable all members are final.
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
   *
   * @param blockIndex The index of the corresponding block found in the GeneBlock list. If index is out of range this neuron is ignored, but still kept for diversity.
   * @param aType      A's input type
   * @param bType      B's input type
   * @param cType      C's input type. This is the standard AB are being compared to, a constant is typical.
   * @param dType      D's input type
   * @param eType      E's input type
   * @param aValue     A's value
   * @param bValue     B's value
   * @param cValue     C's value
   * @param dValue     D's value
   * @param eValue     E's value
   * @param binaryAB   binary operator to merge A and B
   * @param unaryAB    unary operator applied after A and B are merged and before comparison to C
   * @param binaryDE   binary operator to merge D and E
   * @param unaryDE    unary operator applied before the merged D and E are sent to the hinge.
   */
  public GeneNeuron(int blockIndex, EnumNeuronInput aType, EnumNeuronInput bType, EnumNeuronInput cType,
                    EnumNeuronInput dType, EnumNeuronInput eType, float aValue, float bValue, float cValue, float dValue, float eValue,
                    EnumOperator binaryAB, EnumOperator unaryAB, EnumOperator binaryDE, EnumOperator unaryDE)
  {
    BLOCK_INDEX = blockIndex;
    if (aType == null)
    {
      A_TYPE = EnumNeuronInput.CONSTANT;
    }
    else
    {
      A_TYPE = aType;
    }
    if (bType == null)
    {
      B_TYPE = EnumNeuronInput.CONSTANT;
    }
    else
    {
      B_TYPE = bType;
    }
    if (cType == null)
    {
      C_TYPE = EnumNeuronInput.CONSTANT;
    }
    else
    {
      C_TYPE = cType;
    }
    if (dType == null)
    {
      D_TYPE = EnumNeuronInput.CONSTANT;
    }
    else
    {
      D_TYPE = dType;
    }
    if (eType == null)
    {
      E_TYPE = EnumNeuronInput.CONSTANT;
    }
    else
    {
      E_TYPE = eType;
    }

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

  /**
   * Creates a copy except for the block index.
   *
   * @param blockIndex new block index we want.
   * @param copy       GeneNeuron to copy.
   */
  public GeneNeuron(int blockIndex, GeneNeuron copy)
  {
    BLOCK_INDEX = blockIndex;
    A_TYPE = copy.A_TYPE;
    B_TYPE = copy.B_TYPE;
    C_TYPE = copy.C_TYPE;
    D_TYPE = copy.D_TYPE;
    E_TYPE = copy.E_TYPE;

    A_VALUE = copy.A_VALUE;
    B_VALUE = copy.B_VALUE;
    C_VALUE = copy.C_VALUE;
    D_VALUE = copy.D_VALUE;
    E_VALUE = copy.E_VALUE;
    BINARY_AB = copy.BINARY_AB;
    UNARY_AB = copy.UNARY_AB;
    BINARY_DE = copy.BINARY_DE;
    UNARY_DE = copy.UNARY_DE;
  }

  /**
   * Constructs a GeneNeuron from file input.
   *
   * @param fileIn the stream we are reading from
   * @throws IOException handled in GenoFile
   */
  public GeneNeuron(BufferedReader fileIn) throws IOException
  {
    BLOCK_INDEX = Integer.parseInt(fileIn.readLine());
    A_TYPE = Enum.valueOf(EnumNeuronInput.class, fileIn.readLine());
    B_TYPE = Enum.valueOf(EnumNeuronInput.class, fileIn.readLine());
    C_TYPE = Enum.valueOf(EnumNeuronInput.class, fileIn.readLine());
    D_TYPE = Enum.valueOf(EnumNeuronInput.class, fileIn.readLine());
    E_TYPE = Enum.valueOf(EnumNeuronInput.class, fileIn.readLine());

    A_VALUE = Float.parseFloat(fileIn.readLine());
    B_VALUE = Float.parseFloat(fileIn.readLine());
    C_VALUE = Float.parseFloat(fileIn.readLine());
    D_VALUE = Float.parseFloat(fileIn.readLine());
    E_VALUE = Float.parseFloat(fileIn.readLine());
    BINARY_AB = Enum.valueOf(EnumOperator.class, fileIn.readLine());
    UNARY_AB = Enum.valueOf(EnumOperator.class, fileIn.readLine());
    BINARY_DE = Enum.valueOf(EnumOperator.class, fileIn.readLine());
    UNARY_DE = Enum.valueOf(EnumOperator.class, fileIn.readLine());
  }

  /**
   * Writes a GeneNeuron to a file output stream
   *
   * @param fileOut our output stream
   * @throws IOException handled in GenoFile
   */
  public void toFile(BufferedWriter fileOut) throws IOException
  {
    fileOut.write("#Neuron\n");
    fileOut.write(String.format("%d\n", BLOCK_INDEX));
    fileOut.write(A_TYPE.name() + "\n");
    fileOut.write(B_TYPE.name() + "\n");
    fileOut.write(C_TYPE.name() + "\n");
    fileOut.write(D_TYPE.name() + "\n");
    fileOut.write(E_TYPE.name() + "\n");
    fileOut.write(String.format("%f\n", A_VALUE));
    fileOut.write(String.format("%f\n", B_VALUE));
    fileOut.write(String.format("%f\n", C_VALUE));
    fileOut.write(String.format("%f\n", D_VALUE));
    fileOut.write(String.format("%f\n", E_VALUE));
    fileOut.write(BINARY_AB.name() + "\n");
    fileOut.write(UNARY_AB.name() + "\n");
    fileOut.write(BINARY_DE.name() + "\n");
    fileOut.write(UNARY_DE.name() + "\n");
  }

  /**
   * This is overridden to maintain stability in genome hashes between runs.
   *
   * @return an integer that is the hash.
   */
  @Override
  public int hashCode()
  {
    return Objects.hash(BLOCK_INDEX, A_TYPE, B_TYPE, C_TYPE, D_TYPE, E_TYPE, A_VALUE, B_VALUE, C_VALUE, D_VALUE, E_VALUE, BINARY_AB, UNARY_AB, BINARY_DE, UNARY_DE);
  }
}
