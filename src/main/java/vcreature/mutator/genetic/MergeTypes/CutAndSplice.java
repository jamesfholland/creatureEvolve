package vcreature.mutator.genetic.MergeTypes;

/**
 * Created by Dayloki on 10/28/2015.
 */

import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.mutator.genetic.GeneticTools;

import java.util.ArrayList;
import java.util.Random;

/**
 * This will pick a random location in the DNA of each of the parents, split the parent DNA and produce two children
 *
 */
public class CutAndSplice
{
  public static ArrayList<Genome> cutAndSplice(Genome parent1, Genome parent2)
  {
    ArrayList<Genome> children = new ArrayList<>();
    Genome child1;
    Genome child2;
    Random rand = new Random();

    ArrayList<Gene> genes1 = new ArrayList<>();
    ArrayList<Gene> genes2 = new ArrayList<>();

    int smallestGeneSize = genes1.size() > genes2.size() ? genes1.size() : genes2.size();
    genes1 = GeneticTools.getParentsGenes(parent1, parent2).get(0);
    genes2 = GeneticTools.getParentsGenes(parent1, parent2).get(1);
    int crossoverPoint1 = rand.nextInt(genes1.size());
    int crossoverPoint2 = rand.nextInt(genes2.size());

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
      } else
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
      } else
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
