package vcreature.genotype.phenoConversion;

import vcreature.genotype.GeneNeuron;
import vcreature.phenotype.Neuron;

/**
 *  This class handles creation of neurons to be added to a block. ProtoNeurons are stored in ProtoBlocks.
 *  Because of the similiarity between GeneNeurons and Neurons, this class is able to extend Neuron.
 */
public class ProtoNeuron extends Neuron
{

  public final GeneNeuron geneNeuron;

  /**
   * Each Neuron can have up to 5 inputs (a, b, c, d and e). Each of these inputs
   * can be specified in the constructor.
   * Any inputs specified as null will be given the default input
   * (EnumNeuronInput.CONSTANT with value =0)
   * This constructor also sets default operations of the neuron to both
   * binary operations being EnumOperator.ADD and both unary operations to
   * EnumOperator.IDENTITY.
   *
   * @param geneNeuron contains all neuron information.
   */
  public ProtoNeuron(GeneNeuron geneNeuron)
  {
    super(geneNeuron.A_TYPE, geneNeuron.B_TYPE, geneNeuron.C_TYPE, geneNeuron.D_TYPE, geneNeuron.E_TYPE);

    this.geneNeuron = geneNeuron;
    setInputValue(Neuron.A, geneNeuron.A_VALUE);
    setInputValue(Neuron.B, geneNeuron.B_VALUE);
    setInputValue(Neuron.C, geneNeuron.C_VALUE);
    setInputValue(Neuron.D, geneNeuron.D_VALUE);
    setInputValue(Neuron.E, geneNeuron.E_VALUE);

    setOp(geneNeuron.BINARY_AB, 0);
    setOp(geneNeuron.BINARY_DE, 2);

    setOp(geneNeuron.UNARY_AB, 1);
    setOp(geneNeuron.UNARY_DE, 3);
  }
}
