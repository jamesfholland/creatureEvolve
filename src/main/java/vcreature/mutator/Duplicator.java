package vcreature.mutator;

import vcreature.genotype.*;
import com.jme3.math.Vector3f;

import java.util.ArrayList;
import java.util.Random;

/**
 * Finds either random existing gene and duplicates or duplicates a seeded gene. Calls Adder to add the gene.
 */
public class Duplicator
{

  private static ArrayList<GeneBlock> geneBlocks;
  private static ArrayList<GeneNeuron> geneNeurons;
  private static Random rand = new Random();
  private static GeneBlock block;
  private static GeneBlock duplicateBlock;
  private static ImmutableVector randomPivot;
  private static ImmutableVector randomParentPivot;
  private static ImmutableVector placementPivot;
  private static boolean validSpot;
  private static Genome newGenome;
  private static float x;
  private static float y;
  private static float z;


  /**
   * Algorithm for intelligently (using this word liberally) adding limbs.
   * Sequence of steps:
   *  (1) Pick random block from given genome
   *  (2) Check the pivot directly across from that genome
   *  (3) If this pivot is available, duplicate the block and place at this pivot. EXIT ALGORITHM
   *  (4) Check adjoining edges to see if their pivots are available. Step 3 if they are.
   *  (5) If none of the following pivots are available for placement, algorithm terminates
   *      WITHOUT PLACING DUPLICATE BLOCK
   * @param genome
   * @return
   */
  protected static Genome duplicateLimb(Genome genome)
  {
    newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();
    block = geneBlocks.get(rand.nextInt(geneBlocks.size() - 1));
    randomPivot = block.PIVOT;
    randomParentPivot = block.PARENT_PIVOT;
    placementPivot = findAvailablePivot(randomPivot);
    if(placementPivot==null) return genome;
    else
    {
      ImmutableVector hingeAxis = findHingeAxis(randomPivot);
      duplicateBlock = new GeneBlock(block.PARENT_OFFSET, placementPivot, randomPivot, block.SIZE,
          hingeAxis, hingeAxis, block.EULER_ANGLES);

      for (int i = 0; i < geneBlocks.size(); i++)
      {
        newGenome.addGeneBlock(geneBlocks.get(i));
        for (GeneNeuron geneNeuron : geneNeurons)
        {
          if (geneNeuron.BLOCK_INDEX == i) newGenome.addGeneNeuron(geneNeuron);
        }
      }
      newGenome.addGeneBlock(duplicateBlock);
    }
    return newGenome;
  }

  private static boolean comparePivot(ImmutableVector pivot1)
  {
    ImmutableVector pivot2;
    for (GeneBlock geneBlock : geneBlocks)
    {
      pivot2 = geneBlock.PIVOT;
      if (pivot1.getX() == pivot2.getX())
        if (pivot1.getY() == pivot2.getY())
          if (pivot1.getZ() == pivot2.getZ()) return true;
    }
    return false;
  }

  private static ImmutableVector findHingeAxis(ImmutableVector pivot)
  {
    if(pivot.getX()== 1 || pivot.getX()==-1) return Axis.UNIT_Z.getImmutableVector();
    else if(pivot.getZ()==1 || pivot.getZ()==-1) return Axis.UNIT_X.getImmutableVector();
    else return Axis.UNIT_Y.getImmutableVector();

  }
  private static boolean verifyEdge(ImmutableVector edge)
  {
    x = edge.getX();
    y = edge.getY();
    z = edge.getZ();
    boolean isEdge = true;
    if (x==0 && y==0 || x==0 && z==0  || y==0 && z==0)
    {
      isEdge = false;
    }
    return isEdge;
  }

  private static ImmutableVector findAvailablePivot(ImmutableVector randomPivot)
  {
    x = randomPivot.getX();
    y = randomPivot.getY();
    z = randomPivot.getZ();
    ImmutableVector availablePivot = null;
    if (Math.abs(x) == 1 || Math.abs(y) == 1 || Math.abs(z) == 1)
    {
      if (Math.abs(x) == 1 && Math.abs(y) == 1 && Math.abs(z) == 1)
      {
        availablePivot = new ImmutableVector(-x, y, -z);
        if (comparePivot(availablePivot)) return availablePivot;
      }
      if (Math.abs(x) == 1 && Math.abs(y) == 1)
      {
        availablePivot = new ImmutableVector(-x, y, z);
        if (comparePivot(availablePivot)) return availablePivot;
      }
      if (Math.abs(y) == 1 && Math.abs(z) == 1)
      {
        availablePivot = new ImmutableVector(x, y, -z);
        if (comparePivot(availablePivot)) return availablePivot;
      }
      if (Math.abs(y) == 1 && Math.abs(z) == 1)
      {
        availablePivot = new ImmutableVector(z, y, x);
        if (comparePivot(availablePivot)) return availablePivot;
      }
      if (Math.abs(y) == 1 && Math.abs(z) == 1)
      {
        availablePivot = new ImmutableVector(-z, y, -x);
        if (comparePivot(availablePivot)) return availablePivot;
      }
    }
    return availablePivot;
  }
}
