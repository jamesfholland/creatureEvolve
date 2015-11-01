
package vcreature.mutator;
import vcreature.genotype.*;
import vcreature.mainSimulation.MainSim;
import vcreature.phenotype.Block;

import java.util.ArrayList;
import java.util.Random;

/**
 * Scales a single block in the Genome.
 */
public class ScaleSingleBlock
{

  /**
   * Scales a random block in the genome
   * If the Scale makes the creature invalid, we return the given genome.
   * @param genome genome we are mutating
   * @param scale the scale factor.
   * @return new Genome with scaled block.
   */
  protected static Genome scaleBlock(Genome genome, float scale)
  {
    ArrayList<GeneBlock> geneBlocks = genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons = genome.getGENE_NEURONS();
    GeneBlock scaledBlock;
    GeneBlock block;

    ImmutableVector scaledSize;
    Genome newGenome;

    newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());

    int pickRandom = MainSim.RANDOM.nextInt(geneBlocks.size());

    for (int i =0; i<geneBlocks.size();i++)
    {
      block = geneBlocks.get(i);
      if(i==pickRandom)
      {
        scaledSize = new ImmutableVector(block.SIZE.X * scale,
                                         block.SIZE.Y * scale, block.SIZE.Z * scale);
        if(scaledSize.X<0.5f || scaledSize.Y<0.5f || scaledSize.Z< 0.5f) return genome;
        if ( Block.max(scaledSize.getVector3f()) > (Block.min(scaledSize.getVector3f()) * 10)) return genome;

        scaledBlock =
            new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
                scaledSize, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
                block.EULER_ANGLES);
      }
      else
      {
        scaledBlock = block;
      }
      newGenome.addGeneBlock(scaledBlock);
    }
    for(GeneNeuron geneNeuron: geneNeurons)
    {
      newGenome.addGeneNeuron(geneNeuron);
    }
    return newGenome;
  }

  protected static Genome scaleRoot(Genome genome, float scale)
  {
    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;

    ImmutableVector scaledSize;
    Genome newGenome;

    ImmutableVector rootSize = genome.getRootSize();
    scaledSize = new ImmutableVector(rootSize.getX()*scale, rootSize.getY()*scale, rootSize.getZ()*scale);
    if(scaledSize.X<0.5f || scaledSize.Y<0.5f || scaledSize.Z< 0.5f) return genome;
    if ( Block.max(scaledSize.getVector3f()) > Block.min(scaledSize.getVector3f()) * 10) return genome;

    newGenome = new Genome(scaledSize, genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    for (int i =0; i<geneBlocks.size();i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));
    }
    for(GeneNeuron geneNeuron: geneNeurons)
    {
      newGenome.addGeneNeuron(geneNeuron);
    }
    return newGenome;
  }
}
