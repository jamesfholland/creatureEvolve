package vcreature.genotype;

import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;
import vcreature.genotype.phenoConversion.ProtoBlock;
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

    this.root = new ProtoBlock(genome.getRootSize());

    ArrayList<GeneBlock> geneBlocks = genome.getGENE_BLOCKS();

    ArrayList<ProtoBlock> protoBlocks = new ArrayList<>(geneBlocks.size());

    //Initialize blank protoblock for each geneblock
    for (int i = 0; i < geneBlocks.size(); i++)
    {
      protoBlocks.add(new ProtoBlock());
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

      protoBlocks.get(i).initializeBlock(geneBlock.SIZE, parent, geneBlock.PARENT_PIVOT, geneBlock.PIVOT, geneBlock.PARENT_HINGE_AXIS, geneBlock.HINGE_AXIS);

    }

    //Add Neurons

    LinkedList<BoundingBox> treeBlocks = new LinkedList<>();
    root.computeLocation(treeBlocks);
    root.addBlocksToCreature(this, root.getHeight(), null);







/*
    Vector3f torsoCenter = new Vector3f( 0.0f, 2.5f, 0.0f);     Vector3f torsoSize = new Vector3f( 2.0f, 1.5f, 1.5f);
    Vector3f leg1Center  = new Vector3f( 5.0f, 0.5f, 0.0f);     Vector3f leg1Size  = new Vector3f( 3.0f, 0.5f, 1.0f);
    Vector3f leg2Center  = new Vector3f(-5.0f, 0.5f, 0.0f);     Vector3f leg2Size  = new Vector3f( 3.0f, 0.5f, 1.0f);

    Block torso = addRoot(torsoCenter, torsoSize);

    //addBlock(Vector3f center, Vector3f size, Block parent, Vector3f pivotA, Vector3f pivotB, Vector3f axisA, Vector3f axisB)

    Vector3f pivotA = new Vector3f( 2.0f, -1.5f,  0.0f); //Center of hinge in the block's coordinates
    Vector3f pivotB = new Vector3f(-3.0f,  0.5f,  0.0f); //Center of hinge in the block's coordinates


    Block leg1  = addBlock(leg1Center, leg1Size,torso, pivotA,  pivotB, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    Vector3f pivotC = new Vector3f(-2.0f, -1.5f,  0.0f); //Center of hinge in the block's coordinates
    Vector3f pivotD = new Vector3f( 3.0f,  0.5f,  0.0f); //Center of hinge in the block's coordinates

    Block leg2  = addBlock(leg2Center, leg2Size,torso, pivotC,  pivotD, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    torso.setMaterial(Block.MATERIAL_GREEN);
    leg1.setMaterial(Block.MATERIAL_RED);
    leg2.setMaterial(Block.MATERIAL_BLUE);

    BoundingBox box = (BoundingBox) torso.getGeometry().getWorldBound();

    Neuron leg1Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg1Neuron1.setInputValue(Neuron.C,11);
    leg1Neuron1.setInputValue(Neuron.D,-Float.MAX_VALUE);

    Neuron leg1Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg1Neuron2.setInputValue(Neuron.C,10);
    leg1Neuron2.setInputValue(Neuron.D,Float.MAX_VALUE);

    leg1.addNeuron(leg1Neuron1);
    leg1.addNeuron(leg1Neuron2);


    Neuron leg2Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg2Neuron1.setInputValue(Neuron.C,11);
    leg2Neuron1.setInputValue(Neuron.D,Float.MAX_VALUE);

    Neuron leg2Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg2Neuron2.setInputValue(Neuron.C,10);
    leg2Neuron2.setInputValue(Neuron.D,-Float.MAX_VALUE);

    leg2.addNeuron(leg2Neuron1);
    leg2.addNeuron(leg2Neuron2);
*/
  }

}
