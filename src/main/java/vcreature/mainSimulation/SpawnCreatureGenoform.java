package vcreature.mainSimulation;

import vcreature.genotype.*;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

/**
 * Created by Dayloki on 10/15/2015.
 */
public class SpawnCreatureGenoform
{
  Genome genome;
  public SpawnCreatureGenoform(String creatureName)
  {
    //superTableMonster2();
    makeFlappyBird();
    //if(creatureName.equals("FlappyBird")) makeFlappyBird();
    //else if(creatureName.equals("superTableMonster")) superTableMonster();
//    if(creatureName.equals("TableMonster2Legs")) makeTableMonster();
//    else if(creatureName.equals("TableMonster4Legs")) makeFourLeggedTableMonster();
//    else if(creatureName.equals("TableMonster4LegsFlat")) makeFourLeggedTableMonsterFlat();
//    else if(creatureName.equals("Squid")) makeSquid();
//    else if(creatureName.equals("FlappyBird")) makeFlappyBird();
  }
  public Genome getGenome()
  {
    return genome;
  }

  public static Genome makeFlappyBird()
  {
    Genome genome = new Genome(new ImmutableVector(2.0f, 1.5f, 1.5f), new ImmutableVector(0f,0f,0f));
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
  public void superTableMonster(){
    genome=new Genome(new ImmutableVector(5f,.5f,5f), new ImmutableVector(0f,0f,0f));
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);

    for (int i = 1; i <10; i++)
    {
      ImmutableVector pivotA = new ImmutableVector(-1.0f, 0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector pivotB = new ImmutableVector(1.0f,.0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector leg1Size = new ImmutableVector(.51f, 5f, .51f);

      Axis LegParentAxis = Axis.UNIT_Z;
      Axis LegAxis = Axis.UNIT_Z;
      GeneBlock leg1 = new GeneBlock(i-1, pivotA, pivotB, leg1Size, LegParentAxis.getImmutableVector(), LegAxis.getImmutableVector(), zeroVector);
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
  }


  public static Genome makeTylerMonster()
  {
    ImmutableVector zeroVector = new ImmutableVector(0f,0f,0f);
    Genome genome = new Genome(new ImmutableVector(5f,.5f,5f), zeroVector);

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



    ImmutableVector pivotAOneBlockDown = new ImmutableVector(-1.0f,1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotBOneBlockDown = new ImmutableVector(1.0f,1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotCOneBlockDown = new ImmutableVector(0.0f,1.0f, -1.0f);
    ImmutableVector pivotDOneBlockDown = new ImmutableVector(0.0f,1.0f, 1.0f);


    ImmutableVector legSize1 = new ImmutableVector(5f, .75f, .75f);
    ImmutableVector legSize2 = new ImmutableVector(.75f,.75f,5f);

    Axis LegParentAxis1 = Axis.UNIT_Z;
    Axis LegAxis1 = Axis.UNIT_Z;

    Axis LegParentAxis2 = Axis.UNIT_X;
    Axis LegAxis2 = Axis.UNIT_X;

    GeneBlock leg[] = new GeneBlock[16];

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

    for(int i=0; i<16; i++)
    {
      genome.addGeneBlock(leg[i]);
    }

    for(int i=0; i<5;i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 3, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);
    }

    for(int i=5; i<10;i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 3, -Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);
    }


    for(int i=10; i<13;i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long
          // as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 3, -Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is
          // not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);
    }


    for(int i=13; i<16;i++)
    {
      GeneNeuron leg1Neuron1 = new GeneNeuron(
          i,
          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
          0, 0, 3, Float.MAX_VALUE, 0,
          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
          EnumOperator.ADD, //Binary operator for merging A and B
          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
          EnumOperator.ADD, //Binary operator for merging D and E
          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);
    }


//
//    for(int i=0; i<5;i++)
//    {
//      GeneNeuron leg1Neuron1 = new GeneNeuron(
//          i-1,
//          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
//          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
//          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//          0, 0, 4, -Float.MAX_VALUE, 0,
//          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
//          EnumOperator.ADD, //Binary operator for merging A and B
//          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//          EnumOperator.ADD, //Binary operator for merging D and E
//          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
//      genome.addGeneNeuron(leg1Neuron1);
//    }
//
//    for(int i=5; i<10;i++)
//    {
//      GeneNeuron leg1Neuron1 = new GeneNeuron(
//          i-1,
//          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
//          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
//          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//          0, 0, 4, Float.MAX_VALUE, 0,
//          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
//          EnumOperator.ADD, //Binary operator for merging A and B
//          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//          EnumOperator.ADD, //Binary operator for merging D and E
//          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
//      genome.addGeneNeuron(leg1Neuron1);
//    }
//
//
//    for(int i=10; i<13;i++)
//    {
//      GeneNeuron leg1Neuron1 = new GeneNeuron(
//          i-1,
//          //This is the list index of leg1 the corresponding block. As long
//          // as we generate lists in the same order this should work fine.
//          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
//          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//          0, 0, 4, Float.MAX_VALUE, 0,
//          //are the float values that correspond to each type. If the type is
//          // not Constant, then it will be ignored.
//          EnumOperator.ADD, //Binary operator for merging A and B
//          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//          EnumOperator.ADD, //Binary operator for merging D and E
//          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
//      genome.addGeneNeuron(leg1Neuron1);
//    }
//
//
//    for(int i=13; i<16;i++)
//    {
//      GeneNeuron leg1Neuron1 = new GeneNeuron(
//          i-1,
//          //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
//          EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
//          EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
//          0, 0, 4, -Float.MAX_VALUE, 0,
//          //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
//          EnumOperator.ADD, //Binary operator for merging A and B
//          EnumOperator.IDENTITY, //Unary operator for after A and B are merged
//          EnumOperator.ADD, //Binary operator for merging D and E
//          EnumOperator.IDENTITY); //Unary operator for after D and E are merged
//      genome.addGeneNeuron(leg1Neuron1);
//    }
//
//



    //for

    return genome;

  }

  static public Genome makeTableMonster()
  {
    Genome genome=new Genome(new ImmutableVector(5f,.75f,5f), new ImmutableVector(0f,0f,0f));
    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);

    {
      ImmutableVector pivotA = new ImmutableVector(1.0f, -1f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector pivotB = new ImmutableVector(-1.0f,1.0f, 0.0f); //Center of hinge in the block's coordinates
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
