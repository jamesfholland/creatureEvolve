package vcreature.mutator.genetic.MergeTypes;

/**
 * Created by Dayloki on 10/28/2015.
 */

import vcreature.genotype.Gene;
import vcreature.genotype.GenoTools;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.MainSim;
import vcreature.mutator.genetic.GeneticTools;

import java.util.ArrayList;

/**
 * Will crossover children from the root node and all of their children (Appendages)
 * Will do cut and splice and single crossover but only switch around whole legs
 */
public class SwapLegs
{
  public static ArrayList<Genome> swapThoseLegs(Genome parent1, Genome parent2)
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

    ArrayList<Gene> parent1Legs = new ArrayList<>();
    for (int i = 0; i < genes1.size(); i++)
    {
      Gene gene = genes1.get(i);
      //Checks if it is a attached to parent block and if it is adds that block and all of it's children to parent1Legs
      if (gene.geneBlock.PARENT_OFFSET == 0)
      {
        parent1Legs.add(GenoTools.getAppendage(parent1, i));
      }
    }
    ArrayList<Gene> parent2Legs = new ArrayList<>();
    for (int i = 0; i < genes2.size(); i++)
    {
      Gene gene = genes2.get(i);
      //Checks if it is a attached to parent block and if it is adds that block and all of it's children to parent1Legs
      if (gene.geneBlock.PARENT_OFFSET == 0)
      {
        parent2Legs.add(GenoTools.getAppendage(parent2, i));
      }
    }
    return singleCrossoverLegs(parent1, parent2, genes1, genes2);
  }

  public static ArrayList<Genome> singleCrossoverLegs(Genome parent1, Genome parent2, ArrayList<Gene> genes1, ArrayList<Gene> genes2)
  {
    int smallestGeneSize = genes1.size() > genes2.size() ? genes1.size() : genes2.size();
    genes1 = GeneticTools.getParentsGenes(parent1, parent2).get(0);
    genes2 = GeneticTools.getParentsGenes(parent1, parent2).get(1);
    int crossoverPoint = MainSim.RANDOM.nextInt(smallestGeneSize + 1) - 1;
    ArrayList<Genome> children = new ArrayList<>();
    Genome child1 = new Genome(parent1.getRootSize(), parent1.getRootEulerAngles());
    //Child 1 will be made here
    for (int i = 0; i < genes2.size(); i++)
    {
      if (i < crossoverPoint)
      {
        Gene gene = genes1.get(i);

        child1.addGeneBlock(gene.geneBlock);
        for (int j = 0; j < gene.children.size(); j++)
        {
          child1.addGeneBlock(gene.children.get(j).geneBlock);
          for (int k = 0; k < gene.children.get(j).geneNeurons.size(); k++)
          {
            child1.addGeneNeuron(gene.children.get(j).geneNeurons.get(k));
          }
        }
        for (int j = 0; j < gene.geneNeurons.size(); j++)
        {
          child1.addGeneNeuron(gene.geneNeurons.get(j));
        }
      }
      else
      {
        Gene gene = genes2.get(i);

        child1.addGeneBlock(gene.geneBlock);
        for (int j = 0; j < gene.children.size(); j++)
        {
          child1.addGeneBlock(gene.children.get(j).geneBlock);
          for (int k = 0; k < gene.children.get(j).geneNeurons.size(); k++)
          {
            child1.addGeneNeuron(gene.children.get(j).geneNeurons.get(k));
          }
        }
        for (int j = 0; j < gene.geneNeurons.size(); j++)
        {
          child1.addGeneNeuron(gene.geneNeurons.get(j));
        }
      }
    }

    Genome child2 = new Genome(parent2.getRootSize(), parent2.getRootEulerAngles());
    for (int i = 0; i < genes1.size(); i++)
    {
      if (i < crossoverPoint)
      {
        Gene gene = genes2.get(i);

        child2.addGeneBlock(gene.geneBlock);

        for (int j = 0; j < gene.children.size(); j++)
        {
          child2.addGeneBlock(gene.children.get(j).geneBlock);
          for (int k = 0; k < gene.children.get(j).geneNeurons.size(); k++)
          {
            child2.addGeneNeuron(gene.children.get(j).geneNeurons.get(k));
          }
        }
        for (int j = 0; j < gene.geneNeurons.size(); j++)
        {
          child2.addGeneNeuron(gene.geneNeurons.get(j));
        }
      }
      else
      {
        Gene gene = genes1.get(i);

        child2.addGeneBlock(gene.geneBlock);
        for (int j = 0; j < gene.children.size(); j++)
        {
          child2.addGeneBlock(gene.children.get(j).geneBlock);
          for (int k = 0; k < gene.children.get(j).geneNeurons.size(); k++)
          {
            child2.addGeneNeuron(gene.children.get(j).geneNeurons.get(k));
          }
        }
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
