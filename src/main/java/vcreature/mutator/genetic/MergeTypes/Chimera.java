package vcreature.mutator.genetic.MergeTypes;

/**
 * Created by Dayloki on 11/2/2015.
 */

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
  public static ArrayList<Genome> Chimera(Genome parent1,Genome parent2)
  {
    ArrayList<Genome> children = new ArrayList<>();
    Genome child1;
    Genome child2;
    ArrayList<Gene> genes1;
    ArrayList<Gene> genes2;

    genes1 = GeneticTools.getParentsGenes(parent1, parent2)[0];
    genes2 = GeneticTools.getParentsGenes(parent1, parent2)[1];
    child1 = new Genome(parent1.getRootSize(), parent1.getRootEulerAngles());
    child2 = new Genome(parent2.getRootSize(), parent2.getRootEulerAngles());
//child1
    for (int i = 0; i <genes1.size(); i++) {child1.addGeneBlock(genes1.get(i).geneBlock);}
    for (int i = 0; i <parent1.getGENE_NEURONS().size() ; i++) {child1.addGeneNeuron(parent1.getGENE_NEURONS().get(i));}
    for (int i = 0; i <genes2.size() ; i++)
    {
      GeneBlock block=genes2.get(i).geneBlock;
      System.out.println("it's not happening");
      if(block.PARENT_OFFSET!=0)
     {
       System.out.println("it's happening");
       child1.addGeneBlock(block);
       for (int j = 0; j< genes2.get(i).geneNeurons.size() ; j++)
       {
         child1.addGeneNeuron(new GeneNeuron(child1.getGENE_BLOCKS().size(),genes2.get(i).geneNeurons.get(j)));
       }
     }
    }

    //for (int i = 0; i <parent2.getGENE_NEURONS().size() ; i++) {child1.addGeneNeuron(parent2.getGENE_NEURONS().get(i));}
    children.add(child1);

    for (int i = 0; i <genes2.size(); i++) {child2.addGeneBlock(genes2.get(i).geneBlock);}
    for (int i = 0; i <parent2.getGENE_NEURONS().size() ; i++) {child2.addGeneNeuron(parent2.getGENE_NEURONS().get(i));}
    for (int i = 0; i <genes1.size() ; i++)
    {
      GeneBlock block=genes1.get(i).geneBlock;
      if(block.PARENT_OFFSET!=0)
      {
        child2.addGeneBlock(block);
        for (int j = 0; j< genes1.get(i).geneNeurons.size() ; j++)
        {
          child1.addGeneNeuron(new GeneNeuron(child1.getGENE_BLOCKS().size(),genes1.get(i).geneNeurons.get(j)));
        }
      }
    }
    children.add(child2);
    return children;
  }
}
