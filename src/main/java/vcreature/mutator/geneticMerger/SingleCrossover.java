package vcreature.mutator.geneticMerger;

/**
 * Created by Dayloki on 10/28/2015.
 */

import vcreature.genotype.Gene;
import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;

import java.util.ArrayList;
import java.util.Random;

/**
 *Starting from one end of one parent genome, copy
 gene by gene until some random crossover point is
 reached. At that crossover point, start copying from the second
 parent and continue until the end of the genome.
 */
public class SingleCrossover
{
public static ArrayList<Genome> singleCrossOver(Genome parent1,Genome parent2)
{

  ArrayList<Genome> children=new ArrayList<>();
  Genome child1;
  Genome child2;
  Random rand=new Random();
//  ArrayList<GeneBlock> geneBlocks1=parent1.getGENE_BLOCKS();
//  ArrayList<GeneNeuron> geneNeurons1=parent1.getGENE_NEURONS();
//  ArrayList<GeneBlock> geneBlocks2=parent2.getGENE_BLOCKS();
//  ArrayList<GeneNeuron> geneNeurons2=parent2.getGENE_NEURONS();
  ArrayList<Gene> genes1=new ArrayList<>();
  ArrayList<Gene> genes2=new ArrayList<>();
//  for (int i = 0; i < geneBlocks1.size(); i++)
//  {
//    Gene gene=new Gene(geneBlocks1,geneNeurons1,i);
//    genes1.add(gene);
//  }
//  for (int i = 0; i < geneBlocks2.size(); i++)
//  {
//    Gene gene=new Gene(geneBlocks2,geneNeurons2,i);
//    genes2.add(gene);
//  }
  int smallestGeneSize=genes1.size()>genes2.size() ? genes1.size() : genes2.size();
genes1=GeneticTools.getParentsGenes(parent1,parent2)[0];
genes2=GeneticTools.getParentsGenes(parent1,parent2)[1];
  int crossoverPoint=rand.nextInt(smallestGeneSize+1)-1;

  child1=new Genome(parent1.getRootSize(),parent1.getRootEulerAngles());
  //Child 1 will be made here
  for (int i = 0; i <genes2.size(); i++)
  {
    if(i<crossoverPoint)
    {
      Gene gene=genes1.get(i);
      child1.addGeneBlock(gene.geneBlock);
      for (int j = 0; j <gene.geneNeurons.size() ; j++)
      {
        child1.addGeneNeuron(gene.geneNeurons.get(j));
      }
    }else
    {
      Gene gene=genes2.get(i);
      child1.addGeneBlock(gene.geneBlock);
      for (int j = 0; j <gene.geneNeurons.size() ; j++)
      {
        child1.addGeneNeuron(gene.geneNeurons.get(j));
      }
    }
  }

  child2=new Genome(parent2.getRootSize(),parent2.getRootEulerAngles());
  for (int i = 0; i <genes1.size(); i++)
  {
    if(i<crossoverPoint)
    {
      Gene gene=genes2.get(i);
      child2.addGeneBlock(gene.geneBlock);
      for (int j = 0; j <gene.geneNeurons.size() ; j++)
      {
        child2.addGeneNeuron(gene.geneNeurons.get(j));
      }
    }else
    {
      Gene gene=genes1.get(i);
      child2.addGeneBlock(gene.geneBlock);
      for (int j = 0; j <gene.geneNeurons.size() ; j++)
      {
        child2.addGeneNeuron(gene.geneNeurons.get(j));
      }
    }
  }
  children.add(child1);
  children.add(child2);

  return children;
}

}
