package vcreature.mutator.hillclimbing;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.MainSim;

import java.util.ArrayList;
import java.util.Random;

/**
 * Removes genes from a genome, either random or seeded.
 */
public class Subtracter
{

  /**
   * Takes a genome, then takes a block and subtracts it from the creature.
   *
   * @param genome a genome of a current creature that you want to change
   * @return a new genome with a block that has been subtracted
   */
  public static Genome subtractBlock(Genome genome)
  {
    Random rand = new Random();
    if (genome.getGENE_BLOCKS().size() <= 0)
    {
      return Adder.addBlock(genome);
    }
    int index = MainSim.RANDOM.nextInt(genome.getGENE_BLOCKS().size());
    Genome newGenome = subtractBlock(genome, index);
    return newGenome;
  }

  public static Genome subtractBlock(Genome genome, int index)
  {
    if (genome.getGENE_BLOCKS().size() < 2)
    {
      return genome;
    }
    ArrayList<GeneBlock> geneBlocks = genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons = genome.getGENE_NEURONS();
    geneBlocks.remove(index);


    Genome newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    for (int i = 0; i < geneBlocks.size(); i++)
    {
      if (i != index)
      {
        newGenome.addGeneBlock(geneBlocks.get(i));
      }
      for (int j = 0; j < geneNeurons.size() - 1; j++)
      {
        newGenome.addGeneNeuron(geneNeurons.get(j));
      }
    }
    return newGenome;
  }

//  public static Genome subtractNeurons(Genome genome)
//  {
//    Random rand=new Random();
//    Genome newGenome=subtractNeurons(genome,rand.nextInt(genome.getGENE_BLOCKS().size()));
//    return newGenome;
//  }
//  public static Genome subtractNeurons(Genome genome,int index)
//  {
//    ArrayList<GeneBlock> geneBlocks=genome.getGENE_BLOCKS();
//    ArrayList<GeneNeuron> geneNeurons=genome.getGENE_NEURONS();
//   geneNeurons.remove(index);
//
//    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
//    for (int i = 0; i <geneBlocks.size(); i++)
//    {
//
//        newGenome.addGeneBlock(geneBlocks.get(i));
//      if(i!=index)
//      {
//        for (int j = 0; j < geneNeurons.size(); j++)
//        {
//          if (geneNeurons.get(j).BLOCK_INDEX == i) newGenome.addGeneNeuron(geneNeurons.get(i));
//        }
//      }
//    }
//    return newGenome;
//  }
}
