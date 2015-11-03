package vcreature.mutator.hillclimbing;

import sun.applet.Main;
import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;
import vcreature.mainSimulation.MainSim;
import vcreature.mutator.Cleaner;
import vcreature.phenotype.Neuron;

import java.util.ArrayList;

/**
 * Inverts creatures and genes. Flips sign of neurons, flips entire genomes intelligently (turns creature over) or stupidly (ie flips arraylist)
 */
public class Inverter
{
public static Genome basicInverter(Genome genome)
{
  Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
  ArrayList<GeneBlock> geneBlocks=genome.getGENE_BLOCKS();
  ArrayList<GeneNeuron> geneNeurons=genome.getGENE_NEURONS();

  for (int i = 0; i < geneBlocks.size(); i++)
  {
    GeneBlock tempBlock=geneBlocks.get(i);
    GeneBlock newBlock=new GeneBlock(tempBlock.PARENT_OFFSET,invertY(tempBlock.PARENT_PIVOT),invertY(tempBlock.PIVOT),tempBlock.SIZE,tempBlock.PARENT_HINGE_AXIS,tempBlock.HINGE_AXIS,tempBlock.EULER_ANGLES);
    newGenome.addGeneBlock(newBlock);
  }
  for (int i = 0; i < geneNeurons.size(); i++)
  {
    GeneNeuron tempNeuron=geneNeurons.get(i);
    GeneNeuron newNeuron=new GeneNeuron(tempNeuron.BLOCK_INDEX,tempNeuron.A_TYPE,tempNeuron.B_TYPE,tempNeuron.C_TYPE,tempNeuron.D_TYPE,tempNeuron.E_TYPE,tempNeuron.A_VALUE,tempNeuron.B_VALUE,tempNeuron.C_VALUE,-1*tempNeuron.D_VALUE,tempNeuron.E_VALUE,tempNeuron.BINARY_AB,tempNeuron.UNARY_AB,tempNeuron.BINARY_DE,tempNeuron.UNARY_DE);
    newGenome.addGeneNeuron(newNeuron);
  }

  return newGenome;
}
  private static ImmutableVector invertY(ImmutableVector immutableVector)
  {
    return new ImmutableVector(immutableVector.X,-1*immutableVector.Y,immutableVector.Z);
  }
  public static Genome flipNeuron(Genome genome)
  {
    genome=Cleaner.cleanGenome(genome);
    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    ArrayList<GeneBlock> geneBlocks=genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons=genome.getGENE_NEURONS();
    int index=MainSim.RANDOM.nextInt(geneNeurons.size()-1);
    geneNeurons.remove(index);
    geneNeurons.add(index, flipNeuron(geneNeurons.get(index)));
    for (int i = 0; i <geneBlocks.size() ; i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));
    }
    for (int i = 0; i <geneNeurons.size() ; i++)
    {
      newGenome.addGeneNeuron(geneNeurons.get(i));
    }
    return newGenome;
  }
  public static GeneNeuron flipNeuron(GeneNeuron neuron)
  {
    return new GeneNeuron(neuron.BLOCK_INDEX,neuron.A_TYPE,neuron.B_TYPE,neuron.C_TYPE,neuron.D_TYPE,neuron.E_TYPE,neuron.A_VALUE,neuron.B_VALUE,neuron.C_VALUE,-1*neuron.D_VALUE,neuron.E_VALUE,neuron.BINARY_AB,neuron.UNARY_AB,neuron.BINARY_DE,neuron.UNARY_DE);
  }
}
