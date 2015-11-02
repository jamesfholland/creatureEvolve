package vcreature.genotype;

import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

import java.util.ArrayList;

/**
 * Created by Tyler on 11/1/2015.
 */
public class TylerMonster extends Genome
{
  public static final float PI = (float) (Math.PI);
  private ArrayList<GeneNeuron> tempGeneNeurons = new ArrayList<>();


  public TylerMonster(ImmutableVector rootSize, ImmutableVector rootEulerAngles, ImmutableVector jointSize, int numLegJoints)
  {
    super(rootSize, rootEulerAngles);
    makeTylerMonster(numLegJoints, rootSize, jointSize);

  }

  private void makeTylerMonster(int numLegJoints, ImmutableVector rootSize, ImmutableVector jointSize)
  {

    GeneBlock leg1;
    GeneBlock leg2;
    GeneNeuron legNeuron1;
    GeneNeuron legNeuron2;

    ImmutableVector zeroVector = new ImmutableVector(0f, 0f, 0f);

    Axis legParentAxis = Axis.UNIT_Z;
    Axis legAxis = Axis.UNIT_Z;
    ImmutableVector leg1Size = jointSize;
    ImmutableVector leg2Size = new ImmutableVector(jointSize.getZ(), jointSize.getY(), jointSize.getX());
    ImmutableVector tempSize;

    ImmutableVector parentSize = rootSize;
    ArrayList<ImmutableVector> parentPivots = new ArrayList <>();

    parentPivots.add(new ImmutableVector(-1, -1, 0)); //Center of hinge in the block's coordinates
    parentPivots.add(new ImmutableVector(1, -1, 0)); //Center of hinge in the block's coordinates
    parentPivots.add(new ImmutableVector(0, -1, -1)); //Center of hinge in the block's coordinates
    parentPivots.add(new ImmutableVector(0, -1, 1)); //Center of hinge in the block's coordinates

    ImmutableVector parentChildPivot;
    ImmutableVector parentChildPivot2;
    ImmutableVector childPivot1;
    ImmutableVector childPivot2;
    ImmutableVector intermediatePivot;


    for (ImmutableVector parentPivot : parentPivots)
    {
      childPivot1 = new ImmutableVector(-(parentPivot.X), -(parentPivot.Y), -(parentPivot.Z));
      childPivot2 = new ImmutableVector(-(childPivot1.X), -(childPivot1.Y), -(childPivot1.Z));
      if(Math.abs(parentPivot.X)==1)
      {
        legParentAxis = Axis.UNIT_Z;
        legAxis = Axis.UNIT_Z;
        tempSize = leg1Size;
      }
      else
      {
        legParentAxis = Axis.UNIT_X;
        legAxis = Axis.UNIT_X;
        tempSize = leg2Size;
      }
      int alternator = 1;
      for (int i = 0; i < numLegJoints; i++)
      {
        if (i == 0)
        {
          leg1 = new GeneBlock(i, parentPivot, childPivot1, tempSize, legParentAxis.getImmutableVector(), legAxis.getImmutableVector(), zeroVector);
        }
        else
        {
          leg1 = new GeneBlock(-1, childPivot1, childPivot2, tempSize, legParentAxis.getImmutableVector(), legAxis.getImmutableVector(), zeroVector);
        }
        legNeuron1 = new GeneNeuron(i, EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT, EnumNeuronInput.CONSTANT,
            null, 0, 0, 5, alternator * Float.MAX_VALUE, 0, EnumOperator.ADD, EnumOperator.IDENTITY, EnumOperator.ADD, EnumOperator.IDENTITY);
        alternator = -(alternator);
        tempGeneNeurons.add(legNeuron1);
        this.addGeneBlock(leg1);
        intermediatePivot = childPivot1;
        childPivot1=childPivot2;
        childPivot2 = intermediatePivot;

      }
      for (int i = tempGeneNeurons.size() - 1; i > 0; i--)
      {
        this.addGeneNeuron(tempGeneNeurons.get(i));
      }
    }
  }


}
