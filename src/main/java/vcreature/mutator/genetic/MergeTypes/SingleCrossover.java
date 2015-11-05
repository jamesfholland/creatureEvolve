package vcreature.mutator.genetic.MergeTypes;

import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.MainSim;
import vcreature.mutator.genetic.GeneticTools;

import java.util.ArrayList;

/**
 * This class handles cutting and splicing at one point in a pair of genomes.
 */
public class SingleCrossover
{
  /**
   * Starting from one end of one parent genome, copy
   * gene by gene until some random crossover point is
   * reached. At that crossover point, start copying from the second
   * parent and continue until the end of the genome.
   *
   * @param parent1 first genome
   * @param parent2 second genome
   * @return list of children genomes
   */
  public static ArrayList<Genome> singleCrossOver(Genome parent1, Genome parent2)
  {

    ArrayList<Genome> children = new ArrayList<>();

    ArrayList<Gene> genes1 = GeneticTools.getParentsGenes(parent1, parent2).get(0);
    ArrayList<Gene> genes2 = GeneticTools.getParentsGenes(parent1, parent2).get(1);
    int smallestGeneSize = genes1.size() < genes2.size() ? genes1.size() : genes2.size();
    int crossoverPoint = smallestGeneSize > 0 ? MainSim.RANDOM.nextInt(smallestGeneSize) : 0;
    Genome child1 = new Genome(parent1.getRootSize(), parent1.getRootEulerAngles());
    //Child 1 will be made here
    for (int i = 0; i < crossoverPoint; i++)
    {
      Gene gene = genes1.get(i);
      child1.addGeneBlock(gene.geneBlock);
      for (int j = 0; j < gene.geneNeurons.size(); j++)
      {
        child1.addGeneNeuron(gene.geneNeurons.get(j));
      }
    }
    for (int i = crossoverPoint; i < genes2.size(); i++)
    {
      Gene gene = genes2.get(i);
      child1.addGeneBlock(gene.geneBlock);
      for (int j = 0; j < gene.geneNeurons.size(); j++)
      {
        child1.addGeneNeuron(gene.geneNeurons.get(j));
      }
    }


    Genome child2 = new Genome(parent2.getRootSize(), parent2.getRootEulerAngles());
    for (int i = 0; i < crossoverPoint; i++)
    {
      Gene gene = genes2.get(i);
      child2.addGeneBlock(gene.geneBlock);
      for (int j = 0; j < gene.geneNeurons.size(); j++)
      {
        child2.addGeneNeuron(gene.geneNeurons.get(j));
      }

    }
    for (int i = crossoverPoint; i < genes1.size(); i++)
    {
      Gene gene = genes1.get(i);
      child2.addGeneBlock(gene.geneBlock);
      for (int j = 0; j < gene.geneNeurons.size(); j++)
      {
        child2.addGeneNeuron(gene.geneNeurons.get(j));
      }
    }
    children.add(child1);
    children.add(child2);

    return children;
  }
}