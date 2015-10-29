package vcreature.mutator;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;

import java.util.ArrayList;
import java.util.Random;

/**
 * Rotates the gene in where it attaches to its parent.
 */
public class Rotator
{
  public static Genome rotateBlock(Genome genome,int index)
  {
    Random rand=new Random();

    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    ArrayList<GeneBlock> geneBlocks=genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons=genome.getGENE_NEURONS();
    GeneBlock tempBlock=geneBlocks.get(index);
    GeneBlock rotatedBlock=geneBlocks.get(index);

    //rotatedBlock.EULER_ANGLES=new ImmutableVector(rand.nextFloat()*2*(float)Math.PI,rand.nextFloat()*2*(float)Math.PI,rand.nextFloat()*2*(float)Math.PI);

    for (int i = 0; i < geneBlocks.size(); i++)
    {

    }
  return newGenome;
  }
}
