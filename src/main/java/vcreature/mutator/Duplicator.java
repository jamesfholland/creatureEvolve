package vcreature.mutator;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;

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
  private static ImmutableVector currentPivot;
  private static ImmutableVector rotatedPivotX;
  private static ImmutableVector rotatedPivotZ;


  private static Genome newGenome;


  protected static Genome duplicateLimb(Genome genome)
  {
    newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();
    for (GeneBlock block : geneBlocks) newGenome.addGeneBlock(block);
    int randLimbIndex = rand.nextInt(geneBlocks.size() - 1);
    block = geneBlocks.get(randLimbIndex);
    currentPivot = block.PIVOT;
    rotatedPivotX = new ImmutableVector(-(currentPivot.getX()), currentPivot.getY(), currentPivot.getZ());
    rotatedPivotZ= new ImmutableVector(currentPivot.getX(), currentPivot.getY(), -(currentPivot.getZ()));
    boolean spotAvailableX = true;
    boolean spotAvailableZ = true;
    for (GeneBlock block : geneBlocks)
    {
      if (block.PIVOT == rotatedPivotX)
      {
        spotAvailableX = false;
      }
      else
      {
        if (block.PIVOT == rotatedPivotZ)
        {
          spotAvailableZ = false;
        }
      }
    }
    if(spotAvailableX)
    {
      duplicateBlock = new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, rotatedPivotX, block.SIZE,
            block.PARENT_HINGE_AXIS, block.HINGE_AXIS, block.EULER_ANGLES);
    }
    else if(spotAvailableZ)
    {
      duplicateBlock = new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, rotatedPivotZ, block.SIZE,
          block.PARENT_HINGE_AXIS, block.HINGE_AXIS, block.EULER_ANGLES);
    }
    else return genome;
    newGenome.addGeneBlock(duplicateBlock);

    for (int i = 0; i < geneBlocks.size(); i++)
    {
      for (GeneNeuron geneNeuron : geneNeurons)
      {
        if (geneNeuron.BLOCK_INDEX == i) newGenome.addGeneNeuron(geneNeuron);
      }
    }
    return newGenome;
  }
}
