package vcreature.genotype;

import com.jme3.math.Vector3f;

import java.util.ArrayList;

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
  private ImmutableVector rootSize;

  /**
   * This is a list of all geneBlocks in the creature. Individual blocks are immutable.
   */
  private ArrayList<GeneBlock> geneBlocks;

  /**
   * This is the list of all geneNeurons in the creatures. Individual neurons are immutable
   */
  private ArrayList<GeneNeuron> geneNeurons;

}
