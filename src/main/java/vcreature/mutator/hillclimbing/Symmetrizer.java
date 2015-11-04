package vcreature.mutator.hillclimbing;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;

import java.util.ArrayList;

import static vcreature.genotype.GenoTools.invertXZ;

/**
 * Symmetrizer (real word) makes a creature or block symmetrical.
 */
public class Symmetrizer
{
  /**
   * Symmetrizes a genome. It will add lots of recessive genes doing this.
   *
   * @param genome parent genome
   * @return symmetrized child
   */
  public static Genome basicSymmetrize(Genome genome)
  {
    Genome newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    ArrayList<GeneBlock> geneBlocks = genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons = genome.getGENE_NEURONS();
    for (GeneBlock tempBlock : geneBlocks)
    {
      GeneBlock newBlock = new GeneBlock(tempBlock.PARENT_OFFSET, invertXZ(tempBlock.PARENT_PIVOT), invertXZ(tempBlock.PIVOT), tempBlock.SIZE, tempBlock.PARENT_HINGE_AXIS, tempBlock.HINGE_AXIS, tempBlock.EULER_ANGLES);
      newGenome.addGeneBlock(tempBlock);
      newGenome.addGeneBlock(newBlock);
    }
    for (GeneNeuron tempNeuron : geneNeurons)
    {
      GeneNeuron newNeuron = Inverter.flipNeuron(tempNeuron);
      newGenome.addGeneNeuron(tempNeuron);
      newGenome.addGeneNeuron(newNeuron);
    }
    return newGenome;
  }
}
