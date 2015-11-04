package vcreature.mutator.genetic.MergeTypes;

import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.MainSim;
import vcreature.mutator.genetic.GeneticTools;

import java.util.ArrayList;

/**
 * This will pick a random location in the DNA of each of the parents, split the parent DNA and produce two children
 */
public class CutAndSplice
{
  /**
   * Picks a unique location on each parent and cuts both parents there.
   * The first half of parent 1 and the second half of parent 2 will be put together and the
   * second half of parent 1 and first half of parent 2 will make the second child
   * @param parent1 first parent
   * @param parent2 second parent
   * @return List of child Genomes, always two.
   */
  public static ArrayList<Genome> cutAndSplice(Genome parent1, Genome parent2)
  {
    ArrayList<Genome> children = new ArrayList<>();
    Genome child1;
    Genome child2;

    ArrayList<Gene> genes1 = GeneticTools.getParentsGenes(parent1, parent2).get(0);
    ArrayList<Gene> genes2 = GeneticTools.getParentsGenes(parent1, parent2).get(1);
    int crossoverPoint1 = MainSim.RANDOM.nextInt(genes1.size());
    int crossoverPoint2 = MainSim.RANDOM.nextInt(genes2.size());

    child1 = new Genome(parent1.getRootSize(), parent1.getRootEulerAngles());
    child2 = new Genome(parent2.getRootSize(), parent2.getRootEulerAngles());
    //Gene 1 slice and dice for child 1 and 2
    for (int i = 0; i < genes1.size(); i++)
    {
      if (i < crossoverPoint1)
      {
        Gene gene = genes1.get(i);
        child1.addGeneBlock(gene.geneBlock);
        for (int j = 0; j < gene.geneNeurons.size(); j++)
        {
          child1.addGeneNeuron(gene.geneNeurons.get(j));
        }
      }
      else
      {
        Gene gene = genes1.get(i);
        child2.addGeneBlock(gene.geneBlock);
        for (int j = 0; j < gene.geneNeurons.size(); j++)
        {
          child2.addGeneNeuron(gene.geneNeurons.get(j));
        }
      }
    }
    //Gene 2 slice and dice for child 1 and 2
    for (int i = 0; i < genes2.size(); i++)
    {
      if (i < crossoverPoint2)
      {
        Gene gene = genes2.get(i);
        child1.addGeneBlock(gene.geneBlock);
        for (int j = 0; j < gene.geneNeurons.size(); j++)
        {
          child1.addGeneNeuron(gene.geneNeurons.get(j));
        }
      }
      else
      {
        Gene gene = genes2.get(i);
        child2.addGeneBlock(gene.geneBlock);
        for (int j = 0; j < gene.geneNeurons.size(); j++)
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