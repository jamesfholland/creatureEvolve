package vcreature.mainSimulation;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;
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
    superTableMonster2();
    //makeFlappyBird();
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

  public void makeFlappyBird()
  {

    genome = new Genome(new ImmutableVector(2.0f, 1.5f, 1.5f));

    //Leg1 stuff

    ImmutableVector pivotA = new ImmutableVector(1f, 1.f, 0.5f); //Center of hinge in the block's coordinates
    ImmutableVector pivotB = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg1Size = new ImmutableVector(3.0f, 0.5f, 1.0f);
    Axis LegParentAxis = Axis.UNIT_Z;
    Axis LegAxis = Axis.UNIT_Z;

    GeneBlock leg1 = new GeneBlock(0, pivotA, pivotB, leg1Size, LegParentAxis, LegAxis);

    //Leg2 stuff
    ImmutableVector pivotC = new ImmutableVector(-1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotD = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
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
  public void superTableMonster2(){
    genome = new Genome(new ImmutableVector(5.0f, .5f, 5f));
    Axis LegParentAxis = Axis.UNIT_Z;
    Axis LegAxis = Axis.UNIT_Z;
    for (int i = 1; i <7 ; i++)
    {
      ImmutableVector pivotA = new ImmutableVector(1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector pivotB = new ImmutableVector(-1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector leg1Size = new ImmutableVector(04.9f, 0.75f, .750f);
      GeneBlock leg1 = new GeneBlock(1, pivotA, pivotB, leg1Size, LegParentAxis, LegAxis);
      genome.addGeneBlock(leg1); //Leg1 is in position 0 in the list.
      GeneNeuron leg1Neuron1 = new GeneNeuron(
              i, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
              EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null, //EnumNeuronInput types
              0, 0, 11, -Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
              EnumOperator.ADD, //Binary operator for merging A and B
              EnumOperator.IDENTITY, //Unary operator for after A and B are merged
              EnumOperator.ADD, //Binary operator for merging D and E
              EnumOperator.IDENTITY); //Unary operator for after D and E are merged
      genome.addGeneNeuron(leg1Neuron1);
    }
    //Leg1 stuff

    //Leg2 stuff
    ImmutableVector pivotC = new ImmutableVector(-1.0f, -1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotD = new ImmutableVector(1.0f, 1.0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg2Size = new ImmutableVector(4.5f, 0.75f, .75f);
    GeneBlock leg2 = new GeneBlock(0, pivotC, pivotD, leg2Size, LegParentAxis, LegAxis);
    genome.addGeneBlock(leg2);
    GeneNeuron leg1Neuron2 = new GeneNeuron(
            0,
            EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT, null,
            0, 0, 10, Float.MAX_VALUE, 0,
            EnumOperator.ADD,
            EnumOperator.IDENTITY,
            EnumOperator.ADD,
            EnumOperator.IDENTITY);
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
  public void superTableMonster(){
    genome=new Genome(new ImmutableVector(5f,.5f,5f));


    for (int i = 1; i <10; i++)
    {
      ImmutableVector pivotA = new ImmutableVector(-1.0f, 0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector pivotB = new ImmutableVector(1.0f,.0f, 0.0f); //Center of hinge in the block's coordinates
      ImmutableVector leg1Size = new ImmutableVector(.50f, 5f, .5f);

      Axis LegParentAxis = Axis.UNIT_Z;
      Axis LegAxis = Axis.UNIT_Z;
      GeneBlock leg1 = new GeneBlock(i-1, pivotA, pivotB, leg1Size, LegParentAxis, LegAxis);
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
    ImmutableVector pivotC = new ImmutableVector(1.0f, .0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector pivotD = new ImmutableVector(-1.0f, .0f, 0.0f); //Center of hinge in the block's coordinates
    ImmutableVector leg2Size = new ImmutableVector(0.50f, 0.5f, .5f);

    //Axis LegParentAxis = Axis.UNIT_Z;
    Axis Leg2Axis = Axis.UNIT_Z;
    GeneBlock leg2 = new GeneBlock(0, pivotC, pivotD, leg2Size, Axis.UNIT_Z, Leg2Axis);
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
}
