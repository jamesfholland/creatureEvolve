package vcreature.mutator.genetic.MergeTypes;


import vcreature.genotype.Gene;
import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.mutator.genetic.GeneticTools;

import java.util.ArrayList;

/**
 * This merge type will take two parents and merge them together with all blocks from both parents
 */
public class Chimera
{
  /**
   * Merges two parents into one parent by extracting limbs from one and sticking onto the other.
   * Does this both ways and creates 2 children.
   *
   * @param parent1 first parent genome
   * @param parent2 second parent genome
   * @return list of both children's genomes
   */
  public static ArrayList<Genome> makeChimera(Genome parent1, Genome parent2)
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
//child1
    for (Gene aGenes1 : genes1)
    {
      child1.addGeneBlock(aGenes1.geneBlock);
    }
    for (int i = 0; i < parent1.getGENE_NEURONS().size(); i++)
    {
      child1.addGeneNeuron(parent1.getGENE_NEURONS().get(i));
    }
    for (Gene aGenes2 : genes2)
    {
      GeneBlock block = aGenes2.geneBlock;
      if (block.PARENT_OFFSET != 0)
      {
        child1.addGeneBlock(block);
        for (int j = 0; j < aGenes2.geneNeurons.size(); j++)
        {
          child1.addGeneNeuron(new GeneNeuron(child1.getGENE_BLOCKS().size(), aGenes2.geneNeurons.get(j)));
        }
      }
    }

    children.add(child1);

    //child 2
    for (Gene aGenes2 : genes2)
    {
      child2.addGeneBlock(aGenes2.geneBlock);
    }
    for (int i = 0; i < parent2.getGENE_NEURONS().size(); i++)
    {
      child2.addGeneNeuron(parent2.getGENE_NEURONS().get(i));
    }
    for (Gene aGenes1 : genes1)
    {
      GeneBlock block = aGenes1.geneBlock;
      if (block.PARENT_OFFSET != 0)
      {
        child2.addGeneBlock(block);
        for (int j = 0; j < aGenes1.geneNeurons.size(); j++)
        {
          child1.addGeneNeuron(new GeneNeuron(child1.getGENE_BLOCKS().size(), aGenes1.geneNeurons.get(j)));
        }
      }
    }
    children.add(child2);
    return children;
  }
}
