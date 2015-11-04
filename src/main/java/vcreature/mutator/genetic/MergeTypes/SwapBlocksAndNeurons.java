package vcreature.mutator.genetic.MergeTypes;

import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.mutator.genetic.GeneticTools;

import java.util.ArrayList;

/**
 * Takes blocks from parents one and add parents two's neurons
 */
public class SwapBlocksAndNeurons
{
  /**
   * Will produce two children, One with parent1's blocks and parent1's neurons.
   * The second with parent1's neurons and parent2's blocks
   * @param parent1
   * @param parent2
   * @return An arraylist with each of the children
   */
  public static ArrayList<Genome> swapBlocksAndNeurons(Genome parent1, Genome parent2)
  {
    ArrayList<Genome> children = new ArrayList<>();
    Genome child1;
    Genome child2;
    ArrayList<Gene> genes1;
    ArrayList<Gene> genes2;

    genes1 = GeneticTools.getParentsGenes(parent1, parent2).get(0);
    genes2 = GeneticTools.getParentsGenes(parent1, parent2).get(1);

    child1 = new Genome(parent1.getRootSize(), parent1.getRootEulerAngles());
    child2 = new Genome(parent2.getRootSize(), parent2.getRootEulerAngles());
    //Parent1's body and Parent2's brain
    for (int i = 0; i < genes1.size(); i++)
    {
      Gene gene1 = genes1.get(i);
      Gene gene2 = genes2.get(i);
      child1.addGeneBlock(gene1.geneBlock);
      for (int j = 0; j < gene2.geneNeurons.size(); j++)
      {
        child1.addGeneNeuron(gene2.geneNeurons.get(j));
      }
    }

    //Parent2's body and Parent1's brain
    for (int i = 0; i < genes2.size(); i++)
    {
      Gene gene1 = genes2.get(i);
      Gene gene2 = genes1.get(i);
      child1.addGeneBlock(gene1.geneBlock);
      for (int j = 0; j < gene2.geneNeurons.size(); j++)
      {
        child1.addGeneNeuron(gene2.geneNeurons.get(j));
      }
    }
    children.add(child1);
    children.add(child2);
    return children;
  }
}
