package vcreature.mutator.hillclimbing;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;
import vcreature.mainSimulation.MainSim;
import vcreature.mutator.Cleaner;

import java.util.ArrayList;

/**
 * Inverts creatures and genes. Flips sign of neurons, flips entire genomes
 * intelligently (turns creature over) or stupidly (ie flips arraylist)
 */
class Inverter
{
  /**
   * Takes a genome and will flip it upside down.
   * First takes all the blocks and inverts the pivot points of them.
   * Then it takes the Neurons and aflips there impulse signs.
   * returns the "upside down creauture"
   *
   * @param genome a genome of a current creature that you want to change
   * @return a new genome with everything being turned upside down.
   */
  public static Genome basicInverter(Genome genome)
  {
    Genome newGenome =
        new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    ArrayList<GeneBlock> geneBlocks = genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons = genome.getGENE_NEURONS();

    for (int i = 0; i < geneBlocks.size(); i++)
    {
      GeneBlock tempBlock = geneBlocks.get(i);
      GeneBlock newBlock = new GeneBlock(tempBlock.PARENT_OFFSET,
                                         invertY(tempBlock.PARENT_PIVOT), invertY(tempBlock.PIVOT),
                                         tempBlock.SIZE, tempBlock.PARENT_HINGE_AXIS, tempBlock.HINGE_AXIS,
                                         tempBlock.EULER_ANGLES);
      newGenome.addGeneBlock(newBlock);
    }
    for (int i = 0; i < geneNeurons.size(); i++)
    {
      GeneNeuron tempNeuron = geneNeurons.get(i);
      GeneNeuron newNeuron =
          new GeneNeuron(tempNeuron.BLOCK_INDEX, tempNeuron.A_TYPE,
                         tempNeuron.B_TYPE, tempNeuron.C_TYPE, tempNeuron.D_TYPE,
                         tempNeuron.E_TYPE, tempNeuron.A_VALUE, tempNeuron.B_VALUE,
                         tempNeuron.C_VALUE, -1 * tempNeuron.D_VALUE, tempNeuron.E_VALUE,
                         tempNeuron.BINARY_AB, tempNeuron.UNARY_AB, tempNeuron.BINARY_DE,
                         tempNeuron.UNARY_DE);
      newGenome.addGeneNeuron(newNeuron);
    }

    return newGenome;
  }

  private static ImmutableVector invertY(ImmutableVector immutableVector)
  {
    return new ImmutableVector(immutableVector.X, -1 * immutableVector.Y,
                               immutableVector.Z);
  }

  public static Genome flipNeuron(Genome genome)
  {
    genome = Cleaner.cleanGenome(genome);
    Genome newGenome =
        new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    ArrayList<GeneBlock> geneBlocks = genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons = genome.getGENE_NEURONS();
    int index = MainSim.RANDOM.nextInt(geneNeurons.size());
    for (int i = 0; i < geneBlocks.size(); i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));
    }
    for (int i = 0; i < geneNeurons.size(); i++)
    {
      if (i == index)
      {
        newGenome.addGeneNeuron(flipNeuron(geneNeurons.get(index)));
      }
      else
      {
        newGenome.addGeneNeuron(geneNeurons.get(i));
      }
    }
    return newGenome;
  }

  /**
   * This takes a neuron and flips the sign, that tell it what direction
   * that it fires in.
   *
   * @param neuron a neuron to be fired with a hinge.
   * @return a neuron that fires in the oppisite direction of the original.
   */
  public static GeneNeuron flipNeuron(GeneNeuron neuron)
  {
    return new GeneNeuron(neuron.BLOCK_INDEX, neuron.A_TYPE, neuron.B_TYPE,
                          neuron.C_TYPE, neuron.D_TYPE, neuron.E_TYPE, neuron.A_VALUE,
                          neuron.B_VALUE, neuron.C_VALUE, -1 * neuron.D_VALUE, neuron.E_VALUE,
                          neuron.BINARY_AB, neuron.UNARY_AB, neuron.BINARY_DE, neuron.UNARY_DE);
  }
}
