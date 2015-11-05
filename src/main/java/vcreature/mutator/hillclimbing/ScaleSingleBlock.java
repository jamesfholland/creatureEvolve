package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;
import vcreature.mainSimulation.MainSim;

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
   *
   * @param genome a genome of a current creature that you want to change
   * @return a new genome with a scaled block
   */
  public static Genome scaleBlock(Genome genome)
  {
    Random rand = new Random();
    float scaler;
    scaler = rand.nextFloat();
    scaler = scaler * 2;
    ArrayList<GeneBlock> geneBlocks = genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons = genome.getGENE_NEURONS();
    GeneBlock scaledBlock;
    GeneBlock block;

    ImmutableVector scaledSize;
    Genome newGenome;

    newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());

    if(geneBlocks.size() <= 0) return Adder.addBlock(genome);
    int pickRandom = MainSim.RANDOM.nextInt(geneBlocks.size());
    for (int i = 0; i < geneBlocks.size(); i++)
    {
      block = geneBlocks.get(i);
      if (i == pickRandom)
      {
        scaledSize = new ImmutableVector(block.SIZE.X * scaler,
                                         block.SIZE.Y * scaler, block.SIZE.Z * scaler);
        if (GenoTools.isNotValidBlockSize(scaledSize))
        {
          return genome;
        }

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
    for (GeneNeuron geneNeuron : geneNeurons)
    {
      newGenome.addGeneNeuron(geneNeuron);
    }
    return newGenome;
  }

  /**
   * Scales the root block in the genome
   * If the Scale makes the creature invalid, we return the given genome.
   *
   * @param genome a genome of a current creature that you want to change
   * @return a new genome with a scaled block
   */
  public static Genome scaleRoot(Genome genome)
  {
    Random rand = new Random();
    float scaler;
    scaler = rand.nextFloat();
    scaler = scaler * 2;

    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;

    ImmutableVector scaledSize;
    Genome newGenome;

    ImmutableVector rootSize = genome.getRootSize();
    scaledSize =
        new ImmutableVector(rootSize.getX() * scaler, rootSize.getY() * scaler,
                            rootSize.getZ() * scaler);
    if (GenoTools.isNotValidBlockSize(scaledSize))
    {
      return genome;
    }

    newGenome = new Genome(scaledSize, genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    for (GeneBlock geneBlock : geneBlocks)
    {
      newGenome.addGeneBlock(geneBlock);
    }
    for (GeneNeuron geneNeuron : geneNeurons)
    {
      newGenome.addGeneNeuron(geneNeuron);
    }
    return newGenome;
  }
}
