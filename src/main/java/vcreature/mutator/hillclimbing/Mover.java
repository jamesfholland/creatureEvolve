package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Moves genes around either randomly or smartly (eg pass a parent node and reattaches to it).
 * Tess implemented, 10/27
 * I implemented this to check limbs (non-parent blocks) and make them be on the same Hinge-Axis if
 * they aren't already. Should probably add some more logic to this.
 */
class Mover
{
  /**
   * Takes a genome, then takes a block inside of the genome and moves it
   * randomly around. It can move the block anywhere on the creature, and
   * will keep the same firing of the neurons.
   *
   * @param genome a genome of a current creature that you want to change
   * @return a new genome with a slight change
   */
  static Genome moveLimbs(Genome genome)
  {
    if (genome.getGENE_BLOCKS().size() == 0)
    {
      return genome;
    }
    GeneBlock limb;
    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;
    Random rand = new Random();
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();


    int xSign = (rand.nextBoolean()) ? 1 : -1;
    int ySign = (rand.nextBoolean()) ? 1 : -1;
    int zSign = (rand.nextBoolean()) ? 1 : -1;

    ImmutableVector randPivot = new ImmutableVector(1, 0, 0);

    int randomFace = rand.nextInt(2);
    if (randomFace == 0)
    {
      randPivot = new ImmutableVector(xSign, ySign, zSign * rand.nextFloat());
    }
    else if (randomFace == 1)
    {
      randPivot = new ImmutableVector(xSign * rand.nextFloat(), ySign, zSign);
    }
    else if (randomFace == 2)
    {
      randPivot = new ImmutableVector(xSign, ySign * rand.nextFloat(), zSign);
    }


    int randomLimb = rand.nextInt(geneBlocks.size());

    ImmutableVector sizeCopy = new ImmutableVector(geneBlocks.get(randomLimb).SIZE.getX(), geneBlocks.get(randomLimb).SIZE.getY(), geneBlocks.get(randomLimb).SIZE.getZ());

    ImmutableVector parentPivot = new ImmutableVector(-randPivot.X, -randPivot.Y, -randPivot.Z);

    GeneBlock block = geneBlocks.get(randomLimb);
    int parentOffset = block.PARENT_OFFSET;
    ImmutableVector randAngle = new ImmutableVector(0, 0, 0);//new ImmutableVector(rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2);
    randomFace = rand.nextInt(2);
    if (randomFace == 0)
    {
      limb = new GeneBlock(parentOffset, randPivot, parentPivot, sizeCopy,
                           Axis.UNIT_Z.getImmutableVector(), Axis.UNIT_Z.getImmutableVector(),
                           randAngle);
    }
    else
    {
      limb = new GeneBlock(parentOffset, randPivot, parentPivot, sizeCopy, Axis.UNIT_X.getImmutableVector(), Axis.UNIT_X.getImmutableVector(), randAngle);
    }
    //limb=new GeneBlock(parentOffset, randPivot,parentPivot,sizeCopy, Axis.UNIT_Z.getImmutableVector(),Axis.UNIT_Z.getImmutableVector(),randAngle);


    geneBlocks.remove(randomLimb);
    geneBlocks.add(randomLimb, limb);


    Genome newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    for (int i = 0; i < geneBlocks.size(); i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));

    }
    for (int j = 0; j < geneNeurons.size(); j++)
    {
      newGenome.addGeneNeuron(geneNeurons.get(j));
    }
    return newGenome;
  }

}
