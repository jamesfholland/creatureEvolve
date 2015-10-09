package vcreature.genotype;

import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

/**
 * This class contains data required for building a neuron.
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

  public final EnumNeuronInput A_TYPE;
  public final EnumNeuronInput B_TYPE;
  public final EnumNeuronInput C_TYPE;
  public final EnumNeuronInput D_TYPE;
  public final EnumNeuronInput E_TYPE;

  public final float A_VALUE;
  public final float B_VALUE;
  public final float C_VALUE;
  public final float D_VALUE;
  public final float E_VALUE;

  public final EnumOperator BINARY_AB;
  public final EnumOperator UNARY_AB;
  public final EnumOperator BINARY_DE;
  public final EnumOperator UNARY_DE;

  public GeneNeuron(EnumNeuronInput a_type, EnumNeuronInput b_type, EnumNeuronInput c_type, EnumNeuronInput d_type,
                    EnumNeuronInput e_type, float a_value, float b_value, float c_value, float d_value, float e_value,
                    EnumOperator binary_ab, EnumOperator unary_ab, EnumOperator binary_de, EnumOperator unary_de)
  {
    A_TYPE = a_type;
    B_TYPE = b_type;
    C_TYPE = c_type;
    D_TYPE = d_type;
    E_TYPE = e_type;
    A_VALUE = a_value;
    B_VALUE = b_value;
    C_VALUE = c_value;
    D_VALUE = d_value;
    E_VALUE = e_value;
    BINARY_AB = binary_ab;
    UNARY_AB = unary_ab;
    BINARY_DE = binary_de;
    UNARY_DE = unary_de;
  }
}
