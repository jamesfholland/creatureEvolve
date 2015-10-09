package vcreature.mainSimulation;

import vcreature.genotype.*;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

/**
 * This an example of the FlappyBird creature converted to our genotype instead of Joel's Phenotype.
 * This has yet to be tested as the conversion back to phenotype is not completed yet.
 */
public class FlappyBirdGenoform
{
  private Genome genome;

  FlappyBirdGenoform()
  {
    //Sets the root node's size in Genome constructor
    genome = new Genome(new ImmutableVector(2.0f, 1.5f, 1.5f));

    //Leg1 stuff
    ImmutableVector pivotA = new ImmutableVector(2.0f, -1.5f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotB = new ImmutableVector(-3.0f, 0.5f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg1Size = new ImmutableVector(3.0f, 0.5f, 1.0f);
    Axis LegParentAxis = Axis.UNIT_Z;
    Axis LegAxis = Axis.UNIT_Z;

    GeneBlock leg1 = new GeneBlock(0, pivotA, pivotB, leg1Size, LegParentAxis, LegAxis);

    //Leg2 stuff
    ImmutableVector pivotC = new ImmutableVector(-2.0f, -1.5f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotD = new ImmutableVector(3.0f, 0.5f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg2Size = new ImmutableVector(3.0f, 0.5f, 1.0f);

    GeneBlock leg2 = new GeneBlock(0, pivotC, pivotD, leg2Size, LegParentAxis, LegAxis);

    genome.addGeneBlock(leg1); //Leg1 is in position 0 in the list.
    genome.addGeneBlock(leg2);

    /*
     * This is the most complicated constructor. I will attempt to explain the parameters.
     * Remember inputs are in the order A, B, C, D, E
     * Thus input types are typeA, typeB, ... etc.
     * And values are valueA, valueB, ... etc.
     */
    GeneNeuron leg1Neuron1 = new GeneNeuron(
        0, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
        0, 0, 11, -Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
        EnumOperator.ADD, //Binary operator for merging A and B
        EnumOperator.IDENTITY, //Unary operator for after A and B are merged
        EnumOperator.ADD, //Binary operator for merging D and E
        EnumOperator.IDENTITY); //Unary operator for after D and E are merged


    GeneNeuron leg1Neuron2 = new GeneNeuron(
        0,
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null,
        0, 0, 10, Float.MAX_VALUE, 0,
        EnumOperator.ADD,
        EnumOperator.IDENTITY,
        EnumOperator.ADD,
        EnumOperator.IDENTITY);
    genome.addGeneNeuron(leg1Neuron1);
    genome.addGeneNeuron(leg1Neuron2);


    GeneNeuron leg2Neuron1 = new GeneNeuron(
        1, //This is the list index of leg2 the corresponding block. As long as we generate lists in the same order this should work fine.
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
        0, 0, 11, Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
        EnumOperator.ADD, //Binary operator for merging A and B
        EnumOperator.IDENTITY, //Unary operator for after A and B are merged
        EnumOperator.ADD, //Binary operator for merging D and E
        EnumOperator.IDENTITY); //Unary operator for after D and E are merged


    GeneNeuron leg2Neuron2 = new GeneNeuron(
        1,
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null,
        0, 0, 10, -Float.MAX_VALUE, 0,
        EnumOperator.ADD,
        EnumOperator.IDENTITY,
        EnumOperator.ADD,
        EnumOperator.IDENTITY);

    genome.addGeneNeuron(leg2Neuron1);
    genome.addGeneNeuron(leg2Neuron2);

  }

}
