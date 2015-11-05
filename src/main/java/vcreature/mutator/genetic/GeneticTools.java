package vcreature.mutator.genetic;

import vcreature.genotype.Gene;
import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;

import java.util.ArrayList;

/**
 * This class contains tools that the genetic algorithm will use
 */
public class GeneticTools
{
  /**
   * Puts 2 genomes into 2 lists.
   * This would have been better done as a 2 calls for each genome since the lists are distinct.
   *
   * @param parent1 first genome
   * @param parent2 second genome
   * @return ArrayList of lists containing the genes of both parents
   * index 1 is parent1 index 2 is parent 2.
   */
  public static ArrayList<ArrayList<Gene>> getParentsGenes(Genome parent1, Genome parent2)
  {
    ArrayList<GeneBlock> geneBlocks1 = parent1.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons1 = parent1.getGENE_NEURONS();
    ArrayList<GeneBlock> geneBlocks2 = parent2.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons2 = parent2.getGENE_NEURONS();
    ArrayList<Gene> genes1 = new ArrayList<>();
    ArrayList<Gene> genes2 = new ArrayList<>();

    for (int i = 0; i < geneBlocks1.size(); i++)
    {
      Gene gene = new Gene(geneBlocks1, geneNeurons1, i);
      genes1.add(gene);
    }
    for (int i = 0; i < geneBlocks2.size(); i++)
    {
      Gene gene = new Gene(geneBlocks2, geneNeurons2, i);
      genes2.add(gene);
    }
    ArrayList<ArrayList<Gene>> parentGenes = new ArrayList<>();
    parentGenes.add(genes1);
    parentGenes.add(genes2);
    return parentGenes;
  }
}
