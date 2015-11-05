package vcreature.mainSimulation;

import vcreature.genotype.*;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

/**
 * This class is full of seeds for creatures, each class should return a genome
 */
public class SpawnCreatureGenoform
{
  /**
   * A basic creature template unused except for making seeds.
   *
   * @return the genome of our creature.
   */
  public static Genome creature()
  {
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);
    Genome genome = new Genome(new ImmutableVector(5f, .5f, 5f), zeroVector);

    ImmutableVector pivotARoot = new ImmutableVector(1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotBRoot = new ImmutableVector(-1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates

    ImmutableVector pivotAOneBlockDown = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotBOneBlockDown = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates


    ImmutableVector legSize1 = new ImmutableVector(.75f, 5f, .75f);
    ImmutableVector legSize2 = new ImmutableVector(.75f, 1.5f, .75f);

    Axis LegParentAxis1 = Axis.UNIT_Z;
    Axis LegAxis1 = Axis.UNIT_Z;

    Axis LegParentAxis2 = Axis.UNIT_X;
    Axis LegAxis2 = Axis.UNIT_X;

    GeneBlock leg[] = new GeneBlock[32];

    leg[0] = new GeneBlock(0, pivotARoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[1] = new GeneBlock(-1, pivotBRoot, pivotARoot, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[2] = new GeneBlock(0, pivotBRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[3] = new GeneBlock(-1, pivotARoot, pivotBRoot, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
//    leg[4] = new GeneBlock(0, pivotERoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);

    // leg[26] = new GeneBlock(-16, pivotKRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[27] = new GeneBlock(-16, pivotLRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[28] = new GeneBlock(-16, pivotMRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//
//    leg[29] = new GeneBlock(-16, pivotNRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[30] = new GeneBlock(-16, pivotORoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[31] = new GeneBlock(-16, pivotPRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);

    for (int i = 0; i < 4; i++)
    {
      genome.addGeneBlock(leg[i]);
    }

    for (int i = 0; i < 1; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, -Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);

      GeneNeuron leg1Neuron2 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 4, -Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron2);
    }

    for (int i = 1; i < 2; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, -Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);


      GeneNeuron leg1Neuron2 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 4, -Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron2);


    }

    for (int i = 2; i < 3; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long
          // as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is
          // not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);

      GeneNeuron leg1Neuron2 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 4, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron2);
    }
    for (int i = 3; i < 4; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long
          // as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is
          // not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);


      GeneNeuron leg1Neuron2 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 4, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron2);
    }
    return genome;
  }
}
