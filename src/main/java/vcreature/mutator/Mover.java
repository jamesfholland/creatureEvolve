package vcreature.mutator;
import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;

import java.util.ArrayList;

/**
 * Moves genes around either randomly or smartly (eg pass a parent node and reattaches to it).
 * Tess implemented, 10/27
 * I implemented this to check limbs (non-parent blocks) and make them be on the same Hinge-Axis if
 * they aren't already. Should probably add some more logic to this.
 */
public class Mover
{
  static Genome newGenome;
  static GeneBlock limb;
  static GeneBlock nextLimb;
  static ArrayList<GeneBlock> geneBlocks;
  static ArrayList<GeneNeuron> geneNeurons;

  protected static Genome moveLimbs(Genome genome)
  {
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();
    newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());

    for (int i = 0; i < geneBlocks.size(); i++)
    {
      limb = geneBlocks.get(i);
      if(i+1<geneBlocks.size())
      {
        nextLimb = geneBlocks.get(i + 1);
        if (limb.HINGE_AXIS != nextLimb.HINGE_AXIS)
        {
          nextLimb = new GeneBlock(nextLimb.PARENT_OFFSET, nextLimb.PARENT_PIVOT, nextLimb.PIVOT,
              nextLimb.SIZE, nextLimb.PARENT_HINGE_AXIS, limb.HINGE_AXIS,
              nextLimb.EULER_ANGLES);
          geneBlocks.set(i + 1, nextLimb);
        }
      }
      geneBlocks.set(i, limb);
      newGenome.addGeneBlock(limb);
      for (GeneNeuron geneNeuron : geneNeurons)
      {
        if (geneNeuron.BLOCK_INDEX == i) newGenome.addGeneNeuron(geneNeuron);
      }
    }

    return newGenome;
  }
}
