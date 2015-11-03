package vcreature.mutator.genetic.MergeTypes;

/**
 * Created by Dayloki on 10/28/2015.
 */

import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.MainSim;
import vcreature.mutator.genetic.GeneticTools;

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

  Random rand=new Random();

  ArrayList<Gene> genes1=new ArrayList<>();
  ArrayList<Gene> genes2=new ArrayList<>();

genes1= GeneticTools.getParentsGenes(parent1, parent2)[0];
genes2=GeneticTools.getParentsGenes(parent1,parent2)[1];
  int smallestGeneSize=genes1.size()>genes2.size() ? genes1.size() : genes2.size();
  int crossoverPoint=rand.nextInt(smallestGeneSize+1)-1;
  Genome child1=new Genome(parent1.getRootSize(),parent1.getRootEulerAngles());
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

  Genome child2=new Genome(parent2.getRootSize(),parent2.getRootEulerAngles());
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
