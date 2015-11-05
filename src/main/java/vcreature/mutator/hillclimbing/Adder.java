package vcreature.mutator.hillclimbing;

import vcreature.genotype.Axis;
import vcreature.genotype.GeneBlock;
import vcreature.genotype.GeneNeuron;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;
import vcreature.mainSimulation.MainSim;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adds genes to a genome, either random or seeded genes
 */
public class Adder
{
  /**
   * This will randomly add a block to a creature for hill climbing.
   * It has methods so it tries to not make cubes, but more flat blocks that are wide.
   *
   * @param genome a genome of a current creature that you want to change
   * @return a new genome with a slight change
   */
  public static Genome addBlock(Genome genome)
  {
    ArrayList<GeneBlock> geneBlocks;
    ArrayList<GeneNeuron> geneNeurons;
    Random rand = MainSim.RANDOM;
    float min = .501f;
    float max = 3f;

    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();
    if(geneBlocks.size() <= 0) return Adder.addBlock(genome);
    int index = MainSim.RANDOM.nextInt(geneBlocks.size());

    // GeneBlock block=geneBlocks.get(index);
    GeneBlock randBlock;
    float sizeX;
    float sizeY;
    float sizeZ;

    sizeX = rand.nextFloat() * (max - min) + min;
    sizeY = rand.nextFloat() * (max - min) + min;
    if (sizeX > 1f && sizeY > 1f)
    {
      sizeZ = rand.nextFloat() + min;
    }
    else
    {
      sizeZ = rand.nextFloat() * (max - min) + min;
    }
    ImmutableVector size = new ImmutableVector(sizeX, sizeY, sizeZ);
    ImmutableVector randAngle = new ImmutableVector(0, 0, 0);//new ImmutableVector(rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2);
    int parentOffset = -1 * index;
    int xSign = (rand.nextBoolean()) ? 1 : -1;
    int ySign = (rand.nextBoolean()) ? 1 : -1;
    int zSign = (rand.nextBoolean()) ? 1 : -1;
    ImmutableVector randPivot;
    int randomFace = rand.nextInt(3);

    if (randomFace == 0)
    {
      randPivot = new ImmutableVector(xSign * rand.nextFloat(), ySign, zSign);
    }
    else if (randomFace == 1)
    {
      randPivot = new ImmutableVector(xSign, ySign * rand.nextFloat(), zSign);
    }
    else
    {
      randPivot = new ImmutableVector(xSign, ySign, zSign * rand.nextFloat());
    }

    ImmutableVector parentPivot = new ImmutableVector(-randPivot.X, -randPivot.Y, -randPivot.Z);

    if (randomFace==0)
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
                                Axis.UNIT_X.getImmutableVector(), Axis.UNIT_X.getImmutableVector(),
                                randAngle);
    }
    else if(randomFace == 1)
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
                                Axis.UNIT_Y.getImmutableVector(), Axis.UNIT_Y.getImmutableVector(),
                                randAngle);
    }
    else
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
          Axis.UNIT_Z.getImmutableVector(), Axis.UNIT_Z.getImmutableVector(),
          randAngle);
    }

    geneBlocks.add(randBlock);
    Genome newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    for (GeneBlock geneBlock : geneBlocks)
    {
      newGenome.addGeneBlock(geneBlock);

    }
    for (GeneNeuron geneNeuron : geneNeurons)
    {
      newGenome.addGeneNeuron(geneNeuron);
    }
    newGenome = Randomizer.randomizeNeuron(newGenome, geneBlocks.size() - 1);
    return newGenome;
  }
}
