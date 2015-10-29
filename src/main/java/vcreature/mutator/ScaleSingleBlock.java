
package vcreature.mutator;
import vcreature.genotype.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tyler on 10/28/2015.
 */
public class ScaleSingleBlock
{

  private static ArrayList<GeneBlock> geneBlocks;
  private static ArrayList<GeneNeuron> geneNeurons;

  private static GeneBlock scaledBlock;
  private static GeneBlock block;
  private static GeneBlock choosenBlock;
  private static ImmutableVector scaledSize;
  private static Genome newGenome;


  protected static Genome scaleBlock(Genome genome, float scale)
  {
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
        if(block.SIZE.getX()*scale<.5f || block.SIZE.getX()>10*block.SIZE.getY() ||block.SIZE.getX()>10*block.SIZE.getZ() ||
            block.SIZE.getY()*scale<.5f || block.SIZE.getY() >10*block.SIZE.getX() ||block.SIZE.getY()>10*block.SIZE.getZ() ||
            block.SIZE.getZ()*scale<.5f || block.SIZE.getZ() >10*block.SIZE.getX() ||block.SIZE.getZ()>10*block.SIZE.getY())
        {
          return genome;
        }
          scaledSize = new ImmutableVector(block.SIZE.getX() * scale,
            block.SIZE.getY() * scale, block.SIZE.getZ() * scale);
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

    Random rand = new Random();
    ImmutableVector rootSize = genome.getRootSize();
    if(rootSize.getX()*scale<0.5f || rootSize.getX()* scale>10*rootSize.getY()*scale || rootSize.getX()* scale>10*rootSize.getZ()*scale ||
        rootSize.getY()*scale<0.5f ||  rootSize.getY()* scale>10*rootSize.getX()*scale || rootSize.getY()* scale>10*rootSize.getZ()*scale ||
        rootSize.getZ()*scale<0.5f || rootSize.getZ()* scale>10*rootSize.getY()*scale || rootSize.getZ()* scale>10*rootSize.getX()*scale)
    {
      return genome;
    }
    scaledSize = new ImmutableVector(rootSize.getX()*scale, rootSize.getY()*scale, rootSize.getZ()*scale);

    newGenome = new Genome(scaledSize, genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    for (int i =0; i<geneBlocks.size();i++)
    {
      block = geneBlocks.get(i);
        scaledSize = new ImmutableVector(block.SIZE.getX(),
            block.SIZE.getY(), block.SIZE.getZ());
        scaledBlock =
            new GeneBlock(block.PARENT_OFFSET, block.PARENT_PIVOT, block.PIVOT,
                scaledSize, block.PARENT_HINGE_AXIS, block.HINGE_AXIS,
                block.EULER_ANGLES);
      geneBlocks.set(i, scaledBlock);
      newGenome.addGeneBlock(scaledBlock);
      for(GeneNeuron geneNeuron: geneNeurons)
      {
        if(geneNeuron.BLOCK_INDEX==i) newGenome.addGeneNeuron(geneNeuron);
      }

    }
    return newGenome;
  }
}
