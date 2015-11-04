package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;
import vcreature.phenotype.EnumNeuronInput;

import java.util.ArrayList;
import java.util.Random;

/**
 * Finds either random existing gene and duplicates or duplicates a seeded
 * gene. Calls Adder to add the gene.
 */
public class Duplicator
{

  private static ArrayList<GeneBlock> geneBlocks;
  private static Random rand = new Random();
  private static GeneBlock block;
  private static ImmutableVector placementPivot;
  private static ImmutableVector size;


  /**
   * Algorithm for intelligently (using this word liberally) adding limbs.
   * Sequence of steps:
   * (1) Pick random block from given genome
   * (2) Check the pivot directly across from that genome
   * (3) If this pivot is available, duplicate the block and place at this
   * pivot. EXIT ALGORITHM
   * (4) Check adjoining edges to see if their pivots are available. Step 3
   * if they are.
   * (5) If none of the following pivots are available for placement,
   * algorithm terminates
   * WITHOUT PLACING DUPLICATE BLOCK
   *
   * @param genome a genome of a current creature that you want to change
   * @return a new genome with a slight change
   */
  public static Genome duplicateLimb(Genome genome)
  {
    Genome newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    for (GeneBlock geneBlock : geneBlocks) newGenome.addGeneBlock(geneBlock);
    ArrayList<GeneNeuron> geneNeurons = genome.getGENE_NEURONS();
    int randIndex = rand.nextInt(geneBlocks.size());
    block = geneBlocks.get(randIndex);
    if (geneNeurons.size() == 0)
    {
      randIndex = 0;
    }
    else
    {
      randIndex = rand.nextInt(geneNeurons.size());
    }
    GeneNeuron neuron = geneNeurons.get(randIndex);
    ImmutableVector placementPivotParent = findAvailablePivot(block.PARENT_PIVOT);
    if (placementPivotParent == null)
    {
      return genome;
    }
    else
    {
      ImmutableVector hingeAxis = findHingeAxis(placementPivotParent);
      GeneBlock duplicateBlock = new GeneBlock(0, placementPivotParent, placementPivot, size,
                                               hingeAxis, hingeAxis, block.EULER_ANGLES);

      newGenome.addGeneBlock(duplicateBlock);
      for (GeneBlock geneBlock : geneBlocks)
      {
        newGenome.addGeneBlock(geneBlock);
      }


      for (int j = 0; j < geneNeurons.size(); j++)
      {

        GeneNeuron n = geneNeurons.get(j);
        n = new GeneNeuron(n.BLOCK_INDEX, n.A_TYPE, n.B_TYPE, n.C_TYPE,
            n.D_TYPE, n.E_TYPE, n.A_VALUE, n.B_VALUE, n.C_VALUE, n.D_VALUE * -1,
            n.E_VALUE, n.BINARY_AB, n.UNARY_AB, n.BINARY_DE, n.UNARY_DE);
        {
          EnumNeuronInput aInput = EnumNeuronInput.TIME;
          EnumNeuronInput bInput = EnumNeuronInput.CONSTANT;
          EnumNeuronInput cInput = EnumNeuronInput.CONSTANT;
          EnumNeuronInput dInput = EnumNeuronInput.CONSTANT;
          EnumNeuronInput eInput = EnumNeuronInput.CONSTANT;

//          int sign = (rand.nextBoolean()) ? 1 : -1;
//          neuron = new GeneNeuron(
//              j,
//              //This is the list index of leg1 the corresponding block. As
//              // long as we generate lists in the same order this should work
//              // fine.
//              aInput, bInput, cInput, dInput, eInput, //EnumNeuronInput types
//              0, 0, 5, -1 * sign * Float.MAX_VALUE, 0,
//              //are the float values that correspond to each type. If the
//              // type is not Constant, then it will be ignored.
//              EnumOperator.ADD, //Binary operator for merging A and B
//              EnumOperator.IDENTITY,
//              //Unary operator for after A and B are merged
//              EnumOperator.ADD, //Binary operator for merging D and E
//              EnumOperator.IDENTITY); //Unary operator for after D and E are
//          // merged);
          if (j == geneNeurons.size() - 1)
          {
            newGenome.addGeneNeuron(n);
          }
          else
          {
            newGenome.addGeneNeuron(neuron);
          }
        }
      }
    }
    return newGenome;
  }


  private static boolean comparePivot(ImmutableVector pivot1)
  {
    ImmutableVector pivot2;
    for (GeneBlock geneBlock : geneBlocks)
    {
      pivot2 = geneBlock.PARENT_PIVOT;
      if (pivot1.getX() == pivot2.getX())
      {
        if (pivot1.getY() == pivot2.getY())
        {
          if (pivot1.getZ() == pivot2.getZ())
          {
            return true;
          }
        }
      }
    }
    return false;
  }

  private static ImmutableVector findHingeAxis(ImmutableVector pivot)
  {
    if (Math.abs(pivot.getX()) == 1)
    {
      return Axis.UNIT_Z.getImmutableVector();
    }
    else if (Math.abs(pivot.getZ()) == 1)
    {
      return Axis.UNIT_X.getImmutableVector();
    }
    else
    {
      return Axis.UNIT_Y.getImmutableVector();
    }

  }

  private static ImmutableVector findAvailablePivot(ImmutableVector randomPivot)
  {
    float x = Math.abs(randomPivot.getX());
    float y = Math.abs(randomPivot.getY());
    float z = Math.abs(randomPivot.getZ());
    float xp = block.PIVOT.getX();
    float yp = block.PIVOT.getY();
    float zp = block.PIVOT.getZ();
    ImmutableVector availablePivot = null;
    if (x == 1 && y == 1 || x == 1 && z == 1 || y == 1 && z == 1)
    {
      x = randomPivot.getX();
      y = randomPivot.getY();
      z = randomPivot.getZ();
      size = new ImmutableVector(block.SIZE.X, block.SIZE.Y, block.SIZE.Z);
      availablePivot = new ImmutableVector(x, y, -z);
      placementPivot = new ImmutableVector(0, yp, -zp);
      if (!(comparePivot(availablePivot)))
      {
        return availablePivot;
      }
      else
      {
        size = new ImmutableVector(block.SIZE.X, block.SIZE.Y, block.SIZE.Z);
        availablePivot = new ImmutableVector(-x, y, z);
        if (!(comparePivot(availablePivot)))
        {
          size = new ImmutableVector(block.SIZE.X, block.SIZE.Y, block.SIZE.Z);
          placementPivot = new ImmutableVector(-xp, yp, zp);
          return availablePivot;
        }
        availablePivot = new ImmutableVector(-z, y, -x);
        if (!(comparePivot(availablePivot)))
        {
          placementPivot = new ImmutableVector(zp, yp, -xp);
          size = new ImmutableVector(block.SIZE.Z, block.SIZE.Y, block.SIZE.X);
          return availablePivot;
        }
        availablePivot = new ImmutableVector(-z, y, x);
        if (!(comparePivot(availablePivot)))
        {
          size = new ImmutableVector(block.SIZE.Z, block.SIZE.Y, block.SIZE.X);
          placementPivot = new ImmutableVector(zp, yp, -xp);
          return availablePivot;
        }
        availablePivot = new ImmutableVector(z, y, -x);
        if (!(comparePivot(availablePivot)))
        {
          size = new ImmutableVector(block.SIZE.Z, block.SIZE.Y, block.SIZE.X);
          placementPivot = new ImmutableVector(zp, yp, xp);
          return availablePivot;
        }
        availablePivot = new ImmutableVector(-z, y, -x);
        if (!(comparePivot(availablePivot)))
        {
          size = new ImmutableVector(block.SIZE.Z, block.SIZE.Y, block.SIZE.X);
          placementPivot = new ImmutableVector(zp, yp, -xp);
          return availablePivot;
        }
      }
    }
    return availablePivot;
  }
}
