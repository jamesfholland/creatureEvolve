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
   * Creates classic flappy bird but in a genome
   * @return genome of flappy bird
   */
  public static Genome makeFlappyBird()
  {
    Genome genome = new Genome(new ImmutableVector(2.0f, 1.5f, 1.5f), new ImmutableVector(0f, 0f, 0f));
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);

    //Leg1 stuff
    ImmutableVector pivotA = new ImmutableVector(1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotB = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg1Size = new ImmutableVector(3.0f, 0.5f, 1.0f);
    Axis LegParentAxis = Axis.UNIT_Z;
    Axis LegAxis = Axis.UNIT_Z;

    GeneBlock leg1 = new GeneBlock(0, pivotA, pivotB, leg1Size, LegParentAxis.getImmutableVector(), LegAxis.getImmutableVector(), zeroVector);

    //Leg2 stuff
    ImmutableVector pivotC = new ImmutableVector(-1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotD = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg2Size = new ImmutableVector(3.0f, 0.5f, 1.0f);

    GeneBlock leg2 = new GeneBlock(0, pivotC, pivotD, leg2Size, LegParentAxis.getImmutableVector(), LegAxis.getImmutableVector(), zeroVector);

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
    return genome;
  }

  public static Genome superTableMonster()
  {
    Genome genome = new Genome(new ImmutableVector(5f, .5f, 5f), new ImmutableVector(0f, 0f, 0f));
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);

    for (int i = 1; i < 10; i++)
    {
      ImmutableVector pivotA = new ImmutableVector(-1.0f, 0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector pivotB = new ImmutableVector(1.0f, .0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector leg1Size = new ImmutableVector(.51f, 5f, .51f);

      Axis LegParentAxis = Axis.UNIT_Z;
      Axis LegAxis = Axis.UNIT_Z;
      GeneBlock leg1 = new GeneBlock(i - 1, pivotA, pivotB, leg1Size, LegParentAxis.getImmutableVector(), LegAxis.getImmutableVector(), zeroVector);
      genome.addGeneBlock(leg1);
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);
    }
//    ImmutableVector pivotA = new ImmutableVector(1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
//    ImmutableVector pivotB = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
//    ImmutableVector leg1Size = new ImmutableVector(5.0f, 0.5f, .5f);
//
//    Axis LegParentAxis = Axis.UNIT_Z;
//    Axis LegAxis = Axis.UNIT_Z;
//    GeneBlock leg1 = new GeneBlock(0, pivotA, pivotB, leg1Size, LegParentAxis, LegAxis);

//Leg2
    ImmutableVector pivotC = new ImmutableVector(1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotD = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg2Size = new ImmutableVector(.51f, .51f, .51f);

    //Axis LegParentAxis = Axis.UNIT_Z;
    Axis Leg2Axis = Axis.UNIT_Z;
    GeneBlock leg2 = new GeneBlock(0, pivotC, pivotD, leg2Size, Axis.UNIT_Z.getImmutableVector(), Leg2Axis.getImmutableVector(), zeroVector);
    System.out.println(leg2.PARENT_OFFSET);
    GeneNeuron leg1Neuron1 = new GeneNeuron(
        leg2.PARENT_OFFSET, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
        0, 0, 5, Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
        EnumOperator.ADD, //Binary operator for merging A and B
        EnumOperator.IDENTITY, //Unary operator for after A and B are merged
        EnumOperator.ADD, //Binary operator for merging D and E
        EnumOperator.IDENTITY); //Unary operator for after D and E are merged
    genome.addGeneNeuron(leg1Neuron1);

    GeneNeuron leg2Neuron1 = new GeneNeuron(
        1, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
        0, 0, 5, -Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
        EnumOperator.ADD, //Binary operator for merging A and B
        EnumOperator.IDENTITY, //Unary operator for after A and B are merged
        EnumOperator.ADD, //Binary operator for merging D and E
        EnumOperator.IDENTITY); //Unary operator for after D and E are merged
    // genome.addGeneNeuron(leg2Neuron1);

    //genome.addGeneBlock(leg1);
    genome.addGeneBlock(leg2);

    return genome;
  }


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

  public static Genome makeTylerMonster()
  {
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);
    Genome genome = new Genome(new ImmutableVector(40f, 400f, 40f), zeroVector);

    ImmutableVector pivotARoot = new ImmutableVector(1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotBRoot = new ImmutableVector(1.0f, -1f, .5f); //Center of hinge in the block's coordinates
    ImmutableVector pivotCRoot = new ImmutableVector(1.0f, -1f, -.5f); //Center of hinge in the block's coordinates
    ImmutableVector pivotDRoot = new ImmutableVector(1.0f, -1f, 1.f); //Center of hinge in the block's coordinates
    ImmutableVector pivotERoot = new ImmutableVector(1.0f, -1f, -1.0f); //Center of hinge in the block's coordinates

    ImmutableVector pivotFRoot = new ImmutableVector(-1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotGRoot = new ImmutableVector(-1.0f, -1f, .5f); //Center of hinge in the block's coordinates
    ImmutableVector pivotHRoot = new ImmutableVector(-1.0f, -1f, -.5f); //Center of hinge in the block's coordinates
    ImmutableVector pivotIRoot = new ImmutableVector(-1.0f, -1f, 1.f); //Center of hinge in the block's coordinates
    ImmutableVector pivotJRoot = new ImmutableVector(-1.0f, -1f, -1.0f); //Center of hinge in the block's coordinates


    ImmutableVector pivotKRoot = new ImmutableVector(0.0f, -1f, 1.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotLRoot = new ImmutableVector(.5f, -1f, 1.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotMRoot = new ImmutableVector(-.5f, -1f, 1.0f); //Center of hinge in the block's coordinates

    ImmutableVector pivotNRoot = new ImmutableVector(0.0f, -1f, -1.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotORoot = new ImmutableVector(.5f, -1f, -1.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotPRoot = new ImmutableVector(-.5f, -1f, -1.0f); //Center of hinge in the block's coordinates


    ImmutableVector pivotAOneBlockDown = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotBOneBlockDown = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotCOneBlockDown = new ImmutableVector(0.0f, 1.0f, -1.0f);
    ImmutableVector pivotDOneBlockDown = new ImmutableVector(0.0f, 1.0f, 1.0f);
    ImmutableVector pivotEOneBlockDown = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotFOneBlockDown = new ImmutableVector(-1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates


    ImmutableVector legSize1 = new ImmutableVector(5f, 50f, 5f);
    ImmutableVector legSize2 = new ImmutableVector(.75f, .75f, 1.5f);

    Axis LegParentAxis1 = Axis.UNIT_Z;
    Axis LegAxis1 = Axis.UNIT_Z;

    Axis LegParentAxis2 = Axis.UNIT_X;
    Axis LegAxis2 = Axis.UNIT_X;

    GeneBlock leg[] = new GeneBlock[32];

    leg[0] = new GeneBlock(0, pivotARoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[1] = new GeneBlock(0, pivotFRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[2] = new GeneBlock(0, pivotCRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[3] = new GeneBlock(0, pivotDRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[4] = new GeneBlock(0, pivotERoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);

    leg[5] = new GeneBlock(0, pivotFRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[6] = new GeneBlock(0, pivotGRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[7] = new GeneBlock(0, pivotHRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[8] = new GeneBlock(0, pivotIRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[9] = new GeneBlock(0, pivotJRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);

    leg[10] = new GeneBlock(0, pivotKRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
    leg[11] = new GeneBlock(0, pivotLRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
    leg[12] = new GeneBlock(0, pivotMRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);

    leg[13] = new GeneBlock(0, pivotNRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
    leg[14] = new GeneBlock(0, pivotORoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
    leg[15] = new GeneBlock(0, pivotPRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);


    leg[16] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[17] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[18] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[19] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[20] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);

    leg[21] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[22] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[23] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[24] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[25] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);

    // leg[26] = new GeneBlock(-16, pivotKRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[27] = new GeneBlock(-16, pivotLRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[28] = new GeneBlock(-16, pivotMRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//
//    leg[29] = new GeneBlock(-16, pivotNRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[30] = new GeneBlock(-16, pivotORoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[31] = new GeneBlock(-16, pivotPRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);


    for (int i = 0; i < 26; i++)
    {
      genome.addGeneBlock(leg[i]);
    }

    for (int i = 0; i < 5; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, Float.MAX_VALUE, 0,
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

    for (int i = 5; i < 10; i++)
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
          0, 0, 4, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron2);


    }


    for (int i = 10; i < 13; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long
          // as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, -Float.MAX_VALUE, 0,
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


    for (int i = 13; i < 16; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, Float.MAX_VALUE, 0,
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


    for (int i = 16; i < 21; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long
          // as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, -Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is
          // not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);
    }

    for (int i = 21; i < 26; i++)
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
    }
    return genome;

  }


  static public Genome gaint()
  {

    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);
    Genome genome = new Genome(new ImmutableVector(5f, 5f, 5f), zeroVector);

    ImmutableVector pivotParentA = new ImmutableVector(1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotParentB = new ImmutableVector(-1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates

    ImmutableVector pivotChildA = new ImmutableVector(1.0f, 1f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotChildB = new ImmutableVector(-1.0f, 1f, 0.0f); //Center of hinge in the block's coordinates


    ImmutableVector legSize = new ImmutableVector(1.0f, 1.0f, 1.0f);

    int totaNumber = 400;
    GeneBlock leg;
    Axis LegParentAxis = Axis.UNIT_Z;
    Axis LegAxis = Axis.UNIT_Z;


    leg = new GeneBlock(0, pivotParentA, pivotChildA, legSize, LegParentAxis.getImmutableVector(), LegAxis.getImmutableVector(), zeroVector);
    genome.addGeneBlock(leg);
    for (int i = 0; i < totaNumber / 2; i++)
    {
      leg = new GeneBlock(-1, pivotParentA, pivotChildA, legSize, LegParentAxis.getImmutableVector(), LegAxis.getImmutableVector(), zeroVector);
      genome.addGeneBlock(leg);
    }

    leg = new GeneBlock(0, pivotParentB, pivotChildB, legSize, LegParentAxis.getImmutableVector(), LegAxis.getImmutableVector(), zeroVector);
    genome.addGeneBlock(leg);
    for (int i = totaNumber / 2; i < totaNumber; i++)
    {
      leg = new GeneBlock(-1, pivotParentB, pivotChildB, legSize, LegParentAxis.getImmutableVector(), LegAxis.getImmutableVector(), zeroVector);
      genome.addGeneBlock(leg);
    }

    for (int i = 0; i < totaNumber; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);
    }

    return genome;
  }

  static public Genome fallingCreature()
  {

    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);
    Genome genome = new Genome(new ImmutableVector(5f, .5f, 5f), zeroVector);

    ImmutableVector pivotARoot = new ImmutableVector(1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotBRoot = new ImmutableVector(1.0f, -1f, .5f); //Center of hinge in the block's coordinates
    ImmutableVector pivotCRoot = new ImmutableVector(1.0f, -1f, -.5f); //Center of hinge in the block's coordinates
    ImmutableVector pivotDRoot = new ImmutableVector(1.0f, -1f, 1.f); //Center of hinge in the block's coordinates
    ImmutableVector pivotERoot = new ImmutableVector(1.0f, -1f, -1.0f); //Center of hinge in the block's coordinates

    ImmutableVector pivotFRoot = new ImmutableVector(-1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotGRoot = new ImmutableVector(-1.0f, -1f, .5f); //Center of hinge in the block's coordinates
    ImmutableVector pivotHRoot = new ImmutableVector(-1.0f, -1f, -.5f); //Center of hinge in the block's coordinates
    ImmutableVector pivotIRoot = new ImmutableVector(-1.0f, -1f, 1.f); //Center of hinge in the block's coordinates
    ImmutableVector pivotJRoot = new ImmutableVector(-1.0f, -1f, -1.0f); //Center of hinge in the block's coordinates


    ImmutableVector pivotKRoot = new ImmutableVector(0.0f, -1f, 1.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotLRoot = new ImmutableVector(.5f, -1f, 1.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotMRoot = new ImmutableVector(-.5f, -1f, 1.0f); //Center of hinge in the block's coordinates

    ImmutableVector pivotNRoot = new ImmutableVector(0.0f, -1f, -1.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotORoot = new ImmutableVector(.5f, -1f, -1.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotPRoot = new ImmutableVector(-.5f, -1f, -1.0f); //Center of hinge in the block's coordinates


    ImmutableVector pivotAOneBlockDown = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotBOneBlockDown = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotCOneBlockDown = new ImmutableVector(0.0f, 1.0f, -1.0f);
    ImmutableVector pivotDOneBlockDown = new ImmutableVector(0.0f, 1.0f, 1.0f);
    ImmutableVector pivotEOneBlockDown = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotFOneBlockDown = new ImmutableVector(-1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates


    ImmutableVector legSize1 = new ImmutableVector(1.5f, .75f, .75f);
    ImmutableVector legSize2 = new ImmutableVector(.75f, .75f, 1.5f);

    Axis LegParentAxis1 = Axis.UNIT_Z;
    Axis LegAxis1 = Axis.UNIT_Z;

    Axis LegParentAxis2 = Axis.UNIT_X;
    Axis LegAxis2 = Axis.UNIT_X;

    GeneBlock leg[] = new GeneBlock[32];

    leg[0] = new GeneBlock(0, pivotARoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[1] = new GeneBlock(0, pivotBRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[2] = new GeneBlock(0, pivotCRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[3] = new GeneBlock(0, pivotDRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[4] = new GeneBlock(0, pivotERoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);

    leg[5] = new GeneBlock(0, pivotFRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[6] = new GeneBlock(0, pivotGRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[7] = new GeneBlock(0, pivotHRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[8] = new GeneBlock(0, pivotIRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[9] = new GeneBlock(0, pivotJRoot, pivotBOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);

    leg[10] = new GeneBlock(0, pivotKRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
    leg[11] = new GeneBlock(0, pivotLRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
    leg[12] = new GeneBlock(0, pivotMRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);

    leg[13] = new GeneBlock(0, pivotNRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
    leg[14] = new GeneBlock(0, pivotORoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
    leg[15] = new GeneBlock(0, pivotPRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);


    leg[16] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[17] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[18] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[19] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[20] = new GeneBlock(-16, pivotARoot, pivotEOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);

    leg[21] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[22] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[23] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[24] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);
    leg[25] = new GeneBlock(-16, pivotFRoot, pivotAOneBlockDown, legSize1, LegParentAxis1.getImmutableVector(), LegAxis1.getImmutableVector(), zeroVector);

    // leg[26] = new GeneBlock(-16, pivotKRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[27] = new GeneBlock(-16, pivotLRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[28] = new GeneBlock(-16, pivotMRoot, pivotCOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//
//    leg[29] = new GeneBlock(-16, pivotNRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[30] = new GeneBlock(-16, pivotORoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);
//    leg[31] = new GeneBlock(-16, pivotPRoot, pivotDOneBlockDown, legSize2, LegParentAxis2.getImmutableVector(), LegAxis2.getImmutableVector(), zeroVector);


    for (int i = 0; i < 26; i++)
    {
      genome.addGeneBlock(leg[i]);
    }

    for (int i = 0; i < 5; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);


//      GeneNeuron leg1Neuron2 = new GeneNeuron(
//          i,
//          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
//          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
//          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//          0, 0, 2, -Float.MAX_VALUE, 0,
//          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
//          EnumOperator.ADD, //Binary operator for merging A and B
//          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//          EnumOperator.ADD, //Binary operator for merging D and E
//          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
//      genome.addGeneNeuron(leg1Neuron2);
//
    }

    for (int i = 5; i < 10; i++)
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


//      GeneNeuron leg1Neuron2 = new GeneNeuron(
//          i,
//          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
//          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
//          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//          0, 0, 2, Float.MAX_VALUE, 0,
//          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
//          EnumOperator.ADD, //Binary operator for merging A and B
//          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//          EnumOperator.ADD, //Binary operator for merging D and E
//          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
//      genome.addGeneNeuron(leg1Neuron2);


    }


    for (int i = 10; i < 13; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long
          // as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, -Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is
          // not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);


//      GeneNeuron leg1Neuron2 = new GeneNeuron(
//          i,
//          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
//          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
//          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//          0, 0, 2, Float.MAX_VALUE, 0,
//          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
//          EnumOperator.ADD, //Binary operator for merging A and B
//          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//          EnumOperator.ADD, //Binary operator for merging D and E
//          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
//      genome.addGeneNeuron(leg1Neuron2);
//

    }


    for (int i = 13; i < 16; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);

//      GeneNeuron leg1Neuron2 = new GeneNeuron(
//          i,
//          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
//          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
//          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//          0, 0, 2, -Float.MAX_VALUE, 0,
//          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
//          EnumOperator.ADD, //Binary operator for merging A and B
//          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//          EnumOperator.ADD, //Binary operator for merging D and E
//          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
//      genome.addGeneNeuron(leg1Neuron2);
    }


    for (int i = 16; i < 21; i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long
          // as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 5, -Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is
          // not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);
    }

    for (int i = 21; i < 26; i++)
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
    }
    return genome;
  }

  /**
   * Makes everyone's favorite creature, TableMonster!
   * @return tableMonster genome
   */
  static public Genome makeTableMonster()
  {
    Genome genome = new Genome(new ImmutableVector(5f, .75f, 5f), new ImmutableVector(0f, 0f, 0f));
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);

    {
      ImmutableVector pivotA = new ImmutableVector(1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector pivotB = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector leg1Size = new ImmutableVector(5f, .75f, .51f);

      Axis LegParentAxis = Axis.UNIT_Z;
      Axis LegAxis = Axis.UNIT_Z;
      GeneBlock leg1 = new GeneBlock(0, pivotA, pivotB, leg1Size, LegParentAxis.getImmutableVector(), LegAxis.getImmutableVector(), zeroVector);
      genome.addGeneBlock(leg1);

//      GeneNeuron leg1Neuron1 = new GeneNeuron(
//              0, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
//              EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//              0, 0, 5, -Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
//              EnumOperator.ADD, //Binary operator for merging A and B
//              EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//              EnumOperator.ADD, //Binary operator for merging D and E
//              EnumOperator.IDENTITY); //Unary operator for after D and E are merged
//      genome.addGeneNeuron(leg1Neuron1);
    }
//    ImmutableVector pivotA = new ImmutableVector(1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
//    ImmutableVector pivotB = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
//    ImmutableVector leg1Size = new ImmutableVector(5.0f, 0.5f, .5f);
//
//    Axis LegParentAxis = Axis.UNIT_Z;
//    Axis LegAxis = Axis.UNIT_Z;
//    GeneBlock leg1 = new GeneBlock(0, pivotA, pivotB, leg1Size, LegParentAxis, LegAxis);

//Leg2
    ImmutableVector pivotC = new ImmutableVector(-1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotD = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg2Size = new ImmutableVector(5f, .75f, .75f);

    Axis Leg2Axis = Axis.UNIT_Z;
    GeneBlock leg2 = new GeneBlock(0, pivotC, pivotD, leg2Size, Axis.UNIT_Z.getImmutableVector(), Leg2Axis.getImmutableVector(), zeroVector);
    GeneNeuron leg1Neuron2 = new GeneNeuron(
        leg2.PARENT_OFFSET, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
        0, 0, 12, -Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
        EnumOperator.ADD, //Binary operator for merging A and B
        EnumOperator.IDENTITY, //Unary operator for after A and B are merged
        EnumOperator.ADD, //Binary operator for merging D and E
        EnumOperator.IDENTITY); //Unary operator for after D and E are merged
    genome.addGeneNeuron(leg1Neuron2);


    //Axis LegParentAxis = Axis.UNIT_Z;


    GeneNeuron leg1Neuron1 = new GeneNeuron(
        leg2.PARENT_OFFSET, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
        0, 0, 10, Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
        EnumOperator.ADD, //Binary operator for merging A and B
        EnumOperator.IDENTITY, //Unary operator for after A and B are merged
        EnumOperator.ADD, //Binary operator for merging D and E
        EnumOperator.IDENTITY); //Unary operator for after D and E are merged
    genome.addGeneNeuron(leg1Neuron1);


    GeneNeuron leg2Neuron2 = new GeneNeuron(
        1, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
        0, 0, 12, Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
        EnumOperator.ADD, //Binary operator for merging A and B
        EnumOperator.IDENTITY, //Unary operator for after A and B are merged
        EnumOperator.ADD, //Binary operator for merging D and E
        EnumOperator.IDENTITY); //Unary operator for after D and E are merged
    genome.addGeneNeuron(leg2Neuron2);

    GeneNeuron leg2Neuron1 = new GeneNeuron(
        1, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
        EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
        0, 0, 10, -Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
        EnumOperator.ADD, //Binary operator for merging A and B
        EnumOperator.IDENTITY, //Unary operator for after A and B are merged
        EnumOperator.ADD, //Binary operator for merging D and E
        EnumOperator.IDENTITY); //Unary operator for after D and E are merged
    genome.addGeneNeuron(leg2Neuron1);

    //genome.addGeneBlock(leg1);
    genome.addGeneBlock(leg2);
    return genome;
  }
}
