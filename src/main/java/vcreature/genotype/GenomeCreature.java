package vcreature.genotype;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;
import vcreature.genotype.phenoConversion.ProtoBlock;
import vcreature.genotype.phenoConversion.ProtoNeuron;
import vcreature.phenotype.Creature;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class creates a creature based on a genome.
 * Unfinished.
 */
public class GenomeCreature extends Creature
{
  private Genome genome;
  private ProtoBlock root;

  public GenomeCreature(PhysicsSpace physicsSpace, Node jMonkeyRootNode, Genome genome)
  {
    super(physicsSpace, jMonkeyRootNode);
    this.genome = genome;
    this.root = new ProtoBlock(genome.getRootSize(), genome.getRootEulerAngles());

    ArrayList<GeneBlock> geneBlocks = genome.getGENE_BLOCKS();
    ArrayList<GeneNeuron> geneNeurons = genome.getGENE_NEURONS();

    ArrayList<ProtoBlock> protoBlocks = new ArrayList<>(geneBlocks.size());

    //Initialize blank protoblock for each geneblock
    for (int i = 0; i < geneBlocks.size(); i++)
    {
      protoBlocks.add(new ProtoBlock());
    }
    //Add neurons
    for (GeneNeuron geneNeuron : geneNeurons)
    {
      if (geneNeuron.BLOCK_INDEX >= 0 && geneNeuron.BLOCK_INDEX < protoBlocks.size())
      {
        protoBlocks.get(geneNeuron.BLOCK_INDEX).addNeuron(new ProtoNeuron(geneNeuron));
      }
    }

    for (int i = 0; i < geneBlocks.size(); i++)
    {
      GeneBlock geneBlock = geneBlocks.get(i);
      int parentIndex = i + geneBlock.PARENT_OFFSET;

      //Check if parent is valid
      if (parentIndex >= geneBlocks.size() || parentIndex < 0)
      {
        continue;
      }
      //We have a valid parent add to block tree.
      ProtoBlock parent;
      if (geneBlock.PARENT_OFFSET == 0)
      {
        parent = root;
      }
      else
      {
        parent = protoBlocks.get(parentIndex);
      }

      //if(geneBlock.EULER_ANGLES==null) protoBlocks.get(i).initializeBlock(geneBlock.SIZE, parent, geneBlock.PARENT_PIVOT, geneBlock.PIVOT, geneBlock.PARENT_HINGE_AXIS, geneBlock.HINGE_AXIS);
      protoBlocks.get(i).initializeBlock(geneBlock.SIZE, parent, geneBlock.PARENT_PIVOT, geneBlock.PIVOT, geneBlock.HINGE_AXIS, geneBlock.EULER_ANGLES);
    }

    //Add Neurons

    LinkedList<ProtoBlock> treeBlocks = new LinkedList<>();
    root.computeLocation(treeBlocks);
    root.addBlocksToCreature(this, null, treeBlocks);
    this.placeOnGround();
  }

  /**
   * Removes genes that are not in the phenome.
   *
   * @return a clean genome from the root
   */
  public Genome getCleanGenome()
  {
    return root.createCleanGenomeFromRoot();
  }

  /**
   * returns a genome
   * @return a genome
   */
  public Genome getGenome()
  {
    return genome;
  }
}
