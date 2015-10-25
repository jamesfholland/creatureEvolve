package vcreature.genotype;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This Class contains the genome of the creature.
 * This consists of the root block and all children blocks
 */
public class Genome
{

  /**
   * The size of the root block. This is the head of the creature's body tree.
   * All blocks must be attached to the root or not be included in the creature.
   */
  private final ImmutableVector ROOT_SIZE;

  /**
   * The euler angles of the root block. This is the head of the creature's body tree.
   * All blocks must be attached to the root or not be included in the creature.
   */
  private final ImmutableVector ROOT_EULER_ANGLES;

  /**
   * This is a list of all geneBlocks in the creature. Individual blocks are immutable.
   * This list is Synchronized.
   */
  private final List<GeneBlock> GENE_BLOCKS;

  /**
   * This is the list of all geneNeurons in the creatures. Individual neurons are immutable
   * This list is Synchronized.
   */
  private final List<GeneNeuron> GENE_NEURONS;

  /**
   * The determined fitness of the genome.
   */
  private float fitness = -1;

  /**
   * Creates a genome based on a root and its angles.
   * @param rootSize The size of the creature's root.
   * @param rootEulerAngles The orientation of the root.
   */
  public Genome(ImmutableVector rootSize,ImmutableVector rootEulerAngles)
  {
    ROOT_SIZE = rootSize;
    GENE_BLOCKS = Collections.synchronizedList(new ArrayList<GeneBlock>());
    GENE_NEURONS = Collections.synchronizedList(new ArrayList<GeneNeuron>());
    ROOT_EULER_ANGLES=rootEulerAngles;
  }

  /**
   * Synchronized List, in case the list is read while inserting.
   * @param geneBlock the new GeneBlock we are adding.
   */
  public void addGeneBlock(GeneBlock geneBlock)
  {
      GENE_BLOCKS.add(geneBlock); //Synchronized list takes care of thread safety.
  }

  /**
   * Synchronized List, in case the list is read while inserting.
   */
  public void addGeneNeuron(GeneNeuron geneNeuron)
  {
      GENE_NEURONS.add(geneNeuron);  //Synchronized list takes care of thread safety.
  }

  /**
   * Return an copy of our GeneBlocks.
   */
  public ArrayList<GeneBlock> getGENE_BLOCKS()
  {
    synchronized (GENE_BLOCKS) //Needed since it uses an iterator to create the copy.
    {
      return new ArrayList<>(GENE_BLOCKS);
    }
  }

  /**
   * Return an copy of our GeneBlocks.
   */
  public ArrayList<GeneNeuron> getGENE_NEURONS()
  {
    synchronized (GENE_NEURONS) //Needed since it uses an iterator to create the copy.
    {
      return new ArrayList<>(GENE_NEURONS);
    }
  }

  /**
   * @return The Vector3f form of the root size.
   */
  public ImmutableVector getRootSize()
  {
    return ROOT_SIZE;
  }

  /**
   * @return The Vector3f form of the root euler angles.
   */
  public ImmutableVector getRootEulerAngles()
  {
    return ROOT_EULER_ANGLES;
  }

  /**
   * Set the fitness as determined by the physics testing.
   * @param fitness in meters
   */
  public void setFitness(float fitness)
  {
    this.fitness = fitness;
  }

  /**
   * Get the fitness in meters of the creature.
   * A fitness of -1 indicates the creature has not been tested.
   */
  public float getFitness()
  {
    return this.fitness;
  }


}
