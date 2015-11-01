
package vcreature.mutator;
import vcreature.genotype.*;
import vcreature.phenotype.Block;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tyler on 10/28/2015.
 */
public class ScaleSingleBlock
{

  protected static Genome scaleBlock(Genome genome, float scale)
  {
    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;
    GeneBlock scaledBlock;
    GeneBlock block;

    ImmutableVector scaledSize;
    Genome newGenome;

    Random rand = new Random();
    ImmutableVector rootSize = genome.getRootSize();
    scaledSize = new ImmutableVector(rootSize.getX(), rootSize.getY(), rootSize.getZ());
    newGenome = new Genome(scaledSize, genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    int pickRandom = rand.nextInt(geneBlocks.size()-1);

    for (int i =0; i<geneBlocks.size();i++)
    {
      block = geneBlocks.get(i);
      if(i==pickRandom)
      {

        scaledSize = new ImmutableVector(block.SIZE.getX() * scale,
            block.SIZE.getY() * scale, block.SIZE.getZ() * scale);
        if(scaledSize.X<0.5f || scaledSize.Y <0.5f || scaledSize.Z < 0.5f) return genome;
        if ( (Block.max(scaledSize.getVector3f())) > (Block.min(scaledSize.getVector3f()) * 10)) return genome;
        scaledBlock =
            new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
                scaledSize, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
                block.EULER_ANGLES);
      }
      else
      {
        scaledSize = new ImmutableVector(block.SIZE.getX(),
            block.SIZE.getY(), block.SIZE.getZ());
        scaledBlock =
            new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
                scaledSize, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
                block.EULER_ANGLES);
      }
      geneBlocks.set(i, scaledBlock);
      newGenome.addGeneBlock(scaledBlock);
      for(GeneNeuron geneNeuron: geneNeurons)
      {
        if(geneNeuron.BLOCK_INDEX==i) newGenome.addGeneNeuron(geneNeuron);
      }

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
    if ( (Block.max(scaledSize.getVector3f())) > (Block.min(scaledSize.getVector3f()) * 10)) return genome;

    newGenome = new Genome(scaledSize, genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    for (int i =0; i<geneBlocks.size();i++)
    {
      geneBlocks.set(i, geneBlocks.get(i));
      newGenome.addGeneBlock(geneBlocks.get(i));
      for(GeneNeuron geneNeuron: geneNeurons)
      {
        if(geneNeuron.BLOCK_INDEX==i) newGenome.addGeneNeuron(geneNeuron);
      }
    }
    return newGenome;
  }
}
