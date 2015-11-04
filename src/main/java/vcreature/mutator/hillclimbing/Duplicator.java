package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;
import vcreature.mainSimulation.MainSim;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Finds either random existing gene and duplicates or duplicates a seeded
 * gene. Calls Adder to add the gene.
 */
class Duplicator
{


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
    ArrayList<GeneBlock> geneBlocks = genome.getGENE_BLOCKS();
    for (GeneBlock geneBlock : geneBlocks)
    {
      newGenome.addGeneBlock(geneBlock);
    }
    ArrayList<GeneNeuron> geneNeurons = genome.getGENE_NEURONS();
    int randIndex = MainSim.RANDOM.nextInt(geneBlocks.size());
    GeneBlock block = geneBlocks.get(randIndex);
    if (geneNeurons.size() == 0)
    {
      randIndex = 0;
    }
    else
    {
      randIndex = MainSim.RANDOM.nextInt(geneNeurons.size());
    }
    GeneNeuron neuron = geneNeurons.get(randIndex);
    ImmutableVector[] pivotInfo = findAvailablePivot(block.PARENT_PIVOT, geneBlocks, block);
    ImmutableVector placementPivotParent = pivotInfo[0];
    ImmutableVector placementPivot = pivotInfo[1];
    ImmutableVector size = pivotInfo[2];
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
       n=Inverter.flipNeuron(n);
        {
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

  /**
   * Find if any blocks share the pivot parent_pivot
   * This doesn't care who the parent block is in the comparison. Is this a bug?
   *
   * @param pivot1     our pivot
   * @param geneBlocks list of blocks to compare pivots to.
   * @return true if any other blocks happen to have the same pivot location
   */
  private static boolean hasMatchingPivotInList(ImmutableVector pivot1, ArrayList<GeneBlock> geneBlocks)
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

  /**
   * Returns an array containing the pivot and the size.
   *
   * @param randomPivot location on parent
   * @param geneBlocks  list of geneBlocks to squeeze into
   * @param block       block we are duplicating.
   * @return array of pivot location and size {available pivot, placement pivot, size}
   */
  private static ImmutableVector[] findAvailablePivot(ImmutableVector randomPivot, ArrayList<GeneBlock> geneBlocks, GeneBlock block)
  {
    ImmutableVector[] returnVectors = new ImmutableVector[3];

    float x = Math.abs(randomPivot.getX());
    float y = Math.abs(randomPivot.getY());
    float z = Math.abs(randomPivot.getZ());
    float xp = block.PIVOT.getX();
    float yp = block.PIVOT.getY();
    float zp = block.PIVOT.getZ();
    ImmutableVector availablePivot = null;
    ImmutableVector size = null;
    ImmutableVector placementPivot = null;
    if (x == 1 && y == 1 || x == 1 && z == 1 || y == 1 && z == 1)
    {
      x = randomPivot.getX();
      y = randomPivot.getY();
      z = randomPivot.getZ();
      size = new ImmutableVector(block.SIZE.X, block.SIZE.Y, block.SIZE.Z);
      availablePivot = new ImmutableVector(x, y, -z);
      placementPivot = new ImmutableVector(0, yp, -zp);
      if (hasMatchingPivotInList(availablePivot, geneBlocks))
      {
        availablePivot = new ImmutableVector(-x, y, z);
        size = new ImmutableVector(block.SIZE.X, block.SIZE.Y, block.SIZE.Z);
        placementPivot = new ImmutableVector(-xp, yp, zp);
        if (hasMatchingPivotInList(availablePivot, geneBlocks))
        {
          availablePivot = new ImmutableVector(-z, y, -x);
          placementPivot = new ImmutableVector(zp, yp, -xp);
          size = new ImmutableVector(block.SIZE.Z, block.SIZE.Y, block.SIZE.X);
          if (hasMatchingPivotInList(availablePivot, geneBlocks))
          {
            availablePivot = new ImmutableVector(-z, y, x);
            size = new ImmutableVector(block.SIZE.Z, block.SIZE.Y, block.SIZE.X);
            placementPivot = new ImmutableVector(zp, yp, -xp);
            if (hasMatchingPivotInList(availablePivot, geneBlocks))
            {
              availablePivot = new ImmutableVector(z, y, -x);
              size = new ImmutableVector(block.SIZE.Z, block.SIZE.Y, block.SIZE.X);
              placementPivot = new ImmutableVector(zp, yp, xp);
              if (hasMatchingPivotInList(availablePivot, geneBlocks))
              {
                availablePivot = new ImmutableVector(-z, y, -x);
                size = new ImmutableVector(block.SIZE.Z, block.SIZE.Y, block.SIZE.X);
                placementPivot = new ImmutableVector(zp, yp, -xp);
              }
            }
          }
        }
      }
    }
    returnVectors[0] = availablePivot;
    returnVectors[1] = placementPivot;
    returnVectors[2] = size;
    return returnVectors;
  }
}
