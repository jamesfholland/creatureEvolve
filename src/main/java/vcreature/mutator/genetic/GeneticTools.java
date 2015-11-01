package vcreature.mutator.genetic;

/**
 * Created by Dayloki on 10/29/2015.
 */

import vcreature.genotype.Gene;
import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;

import java.util.ArrayList;

/**
 * This class will just have tools that the genetic algorithm will use
 */
public class GeneticTools
{
  public static ArrayList<Gene>[] getParentsGenes(Genome parent1,Genome parent2)
  {
    ArrayList<Genome> children=new ArrayList<>();
    ArrayList<GeneBlock> geneBlocks1=parent1.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons1=parent1.getGENE_NEURONS();
    ArrayList<GeneBlock> geneBlocks2=parent2.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons2=parent2.getGENE_NEURONS();
    ArrayList<Gene> genes1=new ArrayList<>();
    ArrayList<Gene> genes2=new ArrayList<>();
//    GeneBlock rootBlock=new GeneBlock()
//    Gene rootGene=new Gene(parent1.getR)
    for (int i = 0; i < geneBlocks1.size(); i++)
    {
      Gene gene=new Gene(geneBlocks1,geneNeurons1,i);
      genes1.add(gene);
    }
    for (int i = 0; i < geneBlocks2.size(); i++)
    {
      Gene gene=new Gene(geneBlocks2,geneNeurons2,i);
      genes2.add(gene);
    }
  ArrayList<Gene>[] parentGenes=new ArrayList[]{genes1,genes2};
  return parentGenes;
  }
}
