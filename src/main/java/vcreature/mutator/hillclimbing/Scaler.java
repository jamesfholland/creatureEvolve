package vcreature.mutator.hillclimbing;
import vcreature.genotype.*;
import vcreature.phenotype.Block;

import java.util.ArrayList;
import java.util.Random;

/**
 * Scales a gene a set amount. Can maximize, randomize, or seed the scaling.
 */
public class Scaler
{
  public static Genome scale(Genome genome)
  {
    Random rand = new Random();
    float scaler;
    scaler = rand.nextFloat();
    scaler=scaler*2;
    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;
    GeneBlock scaledBlock;
    ImmutableVector scaledSize;
    Genome newGenome;


    ImmutableVector rootSize = genome.getRootSize();
    scaledSize = new ImmutableVector(rootSize.X * scaler, rootSize.Y * scaler, rootSize.Z * scaler);
    if(scaledSize.X<0.5f || scaledSize.Y<0.5f || scaledSize.Z< 0.5f) return genome;
    if ( (Block.max(scaledSize.getVector3f())) > (Block.min(scaledSize.getVector3f()) * 10)) return genome;


    newGenome = new Genome(scaledSize, genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    for(GeneBlock block: geneBlocks)
    {

      scaledSize = new ImmutableVector(block.SIZE.X * scaler, block.SIZE.Y * scaler, block.SIZE.Z * scaler);
      if(scaledSize.X<0.5f || scaledSize.Y<0.5f || scaledSize.Z< 0.5f) return genome;
      if ( (Block.max(scaledSize.getVector3f())) > (Block.min(scaledSize.getVector3f()) * 10)) return genome;
      scaledBlock = new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT, scaledSize, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
          block.EULER_ANGLES);
      if(scaledSize.X<0.5f || scaledSize.Y<0.5f || scaledSize.Z< 0.5f) scaledBlock = block;
      if ( (Block.max(scaledSize.getVector3f())) > (Block.min(scaledSize.getVector3f()) * 10)) scaledBlock = block;
      newGenome.addGeneBlock(scaledBlock);
    }
    for(int i=geneNeurons.size()-1; i>=0; i--) newGenome.addGeneNeuron(geneNeurons.get(i));
    return newGenome;
  }
}
