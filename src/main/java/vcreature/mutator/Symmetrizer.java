package vcreature.mutator;

import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Symmetrizer (real word) makes a creature or block symmetrical.
 */
public class Symmetrizer
{
  private static ArrayList<GeneBlock> geneBlocks;
  private static ArrayList<GeneNeuron> geneNeurons;
  private static Random rand = new Random();
  public static Genome basicSymmetrize(Genome genome)
  {
    Genome newGenome=new Genome(genome.getRootSize(),genome.getRootEulerAngles());
    ArrayList<GeneBlock> geneBlocks=genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons=genome.getGENE_NEURONS();
    for (int i = 0; i <geneBlocks.size(); i++)
    {
      GeneBlock tempBlock=geneBlocks.get(i);
      GeneBlock newBlock=new GeneBlock(tempBlock.PARENT_OFFSET,invertXZ(tempBlock.PARENT_PIVOT),invertXZ(tempBlock.PIVOT),tempBlock.SIZE,tempBlock.PARENT_HINGE_AXIS,tempBlock.HINGE_AXIS,tempBlock.EULER_ANGLES);
      newGenome.addGeneBlock(tempBlock);
      newGenome.addGeneBlock(newBlock);
    }
    for (int i = 0; i <geneNeurons.size() ; i++)
    {
      GeneNeuron tempNeuron=geneNeurons.get(i);
      GeneNeuron newNeuron=new GeneNeuron(tempNeuron.BLOCK_INDEX,tempNeuron.A_TYPE,tempNeuron.B_TYPE,tempNeuron.C_TYPE,tempNeuron.D_TYPE,tempNeuron.E_TYPE,tempNeuron.A_VALUE,tempNeuron.B_VALUE,tempNeuron.C_VALUE,-1*tempNeuron.D_VALUE,tempNeuron.E_VALUE,tempNeuron.BINARY_AB,tempNeuron.UNARY_AB,tempNeuron.BINARY_DE,tempNeuron.UNARY_DE);
      newGenome.addGeneNeuron(tempNeuron);
      newGenome.addGeneNeuron(newNeuron);
    }
    return newGenome;
  }
  private static ImmutableVector invertXZ(ImmutableVector immutableVector)
  {
    return new ImmutableVector(-1*immutableVector.X,immutableVector.Y,-1*immutableVector.Z);
  }

  public static ArrayList<Genome> symmetrizedList(Genome genome)
  {
    ArrayList<Genome> symmetrizedCreatures=new ArrayList<>();
   ArrayList<GeneBlock> geneBlocks=genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons=genome.getGENE_NEURONS();
    ArrayList<GeneBlock> childrenOfRoot=new ArrayList<>();


    for (GeneBlock block:geneBlocks)
    {
      if(block.PARENT_OFFSET==0) childrenOfRoot.add(block);
    }
    return symmetrizedCreatures;
  }
}
