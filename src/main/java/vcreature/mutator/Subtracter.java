package vcreature.mutator;

import com.jme3.math.Vector3f;
import vcreature.genotype.*;
import vcreature.genotype.phenoConversion.ProtoBlock;
import vcreature.phenotype.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Removes genes from a genome, either random or seeded.
 */
public class Subtracter
{

  public static Genome subtractBlocks(Genome genome)
  {
    Random rand=new Random();
    System.out.println(Arrays.toString(genome.getGENE_BLOCKS().toArray()));
    Genome newGenome=subtractBlocks(genome,rand.nextInt(genome.getGENE_BLOCKS().size()));
    return newGenome;
  }
public static Genome subtractBlocks(Genome genome,int index)
{
  ArrayList<GeneBlock> geneBlocks=genome.getGENE_BLOCKS();
  ArrayList<GeneNeuron> geneNeurons=genome.getGENE_NEURONS();
  geneBlocks.remove(index);


  Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
  for (int i = 0; i <geneBlocks.size(); i++)
  {
    if(i!=index)
    {
      System.out.println("test");
      newGenome.addGeneBlock(geneBlocks.get(i));

      for (int j = 0; j < geneNeurons.size(); j++)
      {
        if (geneNeurons.get(j).BLOCK_INDEX == i) newGenome.addGeneNeuron(geneNeurons.get(i));
      }
    }
  }
  return newGenome;
}
}
