package vcreature.genotype;

import com.jme3.math.Vector3f;

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
   * Setup a new Genome for a creature. Blocks and neurons are add later.
   * @param rootSize The size of the root node.
   */
  public Genome(ImmutableVector rootSize)
  {
    ROOT_SIZE = rootSize;
    GENE_BLOCKS = Collections.synchronizedList(new ArrayList<GeneBlock>());
    GENE_NEURONS = Collections.synchronizedList(new ArrayList<GeneNeuron>());
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
   * Return an unmodifiable copy of our GeneBlocks.
   */
  public List<GeneBlock> getGENE_BLOCKS()
  {
    synchronized (GENE_BLOCKS) //Needed since it uses an iterator to create the copy.
    {
      return Collections.unmodifiableList(new ArrayList<>(GENE_BLOCKS));
    }
  }

  /**
   * Return an unmodifiable copy of our GeneBlocks.
   */
  public List<GeneNeuron> getGENE_NEURONS()
  {
    synchronized (GENE_NEURONS) //Needed since it uses an iterator to create the copy.
    {
      return Collections.unmodifiableList(new ArrayList<>(GENE_NEURONS));
    }
  }

  /**
   * @return The Vector3f form of the root size.
   */
  public Vector3f getRootSize()
  {
    return ROOT_SIZE.getVector3f();
  }

}
