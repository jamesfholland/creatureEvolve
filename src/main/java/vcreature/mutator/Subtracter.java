package vcreature.mutator;

import vcreature.genotype.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Removes genes from a genome, either random or seeded.
 */
public class Subtracter
{

  static Genome genome;
  static ArrayList<GeneBlock> geneBlocks;
  static ArrayList<GeneNeuron> geneNeurons;
  static Random rand = new Random();
  static private float min = .5f;
  static private float max = 5f;

//  /**
//   * This will Randomize the genome at a random index
//   * @param genome
//   *//*
//  public Randomizer(Genome genome)
//  {
//    this.genome=genome;
//    this.geneBlocks=genome.getGENE_BLOCKS();
//    this.geneNeurons=genome.getGENE_NEURONS();
//    int index=rand.nextInt(geneBlocks.size());
//    randomize(index);
//  }
//
//  *//**
//   * Randomize genome at specific point
//   * @param genome
//   * @param index index of leg
//   *//*
//  public Randomizer(Genome genome,int index)
//  {
//    this.genome=genome;
//    this.geneBlocks=genome.getGENE_BLOCKS();
//    this.geneNeurons=genome.getGENE_NEURONS();
//    randomize(index);
//  }*/

  /**
   * This is where the magic happens
   */
  protected static Genome subtractBlock(Genome genome)
  {

    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();
    int index = rand.nextInt(geneBlocks.size());


    GeneBlock block = geneBlocks.get(index);
    geneBlocks.remove(index);
    Genome newGenome =
        new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    for (int i = 0; i < geneBlocks.size(); i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));
    }
    if (checkForIntersections(newGenome))
    {
      genome = newGenome;
    }
    else
    {
      subtractBlock(genome);
    }
    return newGenome;
  }

  //Checks if the creature is valid after mutation
  private static boolean checkForIntersections(Genome genome)
  {
    for (int i = 0; i < geneBlocks.size(); i++)
    {

    }
    return true;
  }

  public Genome getGenome()
  {
    return genome;
  }
}



