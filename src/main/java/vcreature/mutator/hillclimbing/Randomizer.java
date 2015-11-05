package vcreature.mutator.hillclimbing;

import vcreature.genotype.*;
import vcreature.mainSimulation.MainSim;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class will randomize a block and genes within valid specifications.
 */
public class Randomizer
{
  /**
   * Randomizes a block in creature, but keeps sizes below 3 meters.
   * It will also try to make the block more flat than cube shape.
   *
   * @param genome a genome of a current creature that you want to change
   * @return a new genome with a slight change
   */
  public static Genome randomize(Genome genome)
  {
    ArrayList<GeneNeuron> geneNeurons;
    ArrayList<GeneBlock> geneBlocks;

    Random rand = new Random();
    float min = .501f;
    float max = 3f;


    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();
    if(geneBlocks.size() > 0) return Adder.addBlock(genome);
    int index = MainSim.RANDOM.nextInt(geneBlocks.size());
    GeneBlock block = geneBlocks.get(index);
    GeneBlock randBlock;
    float sizeX;
    float sizeY;
    float sizeZ;
    sizeX = rand.nextFloat() * (max - min) + min;
    sizeY = rand.nextFloat() * (max - min) + min;
    if (sizeX > 1f && sizeY > 1f)
    {
      sizeZ = rand.nextFloat() + min;
    } else
    {
      sizeZ = rand.nextFloat() * (max - min) + min;
    }
    ImmutableVector size = new ImmutableVector(sizeX, sizeY, sizeZ);
    ImmutableVector randAngle = new ImmutableVector(0, 0, 0);//new ImmutableVector(rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2,rand.nextFloat()*(float)Math.PI/2);
    int parentOffset = block.PARENT_OFFSET;
    int xSign = (rand.nextBoolean()) ? 1 : -1;
    int ySign = (rand.nextBoolean()) ? 1 : -1;
    int zSign = (rand.nextBoolean()) ? 1 : -1;
    ImmutableVector randPivot = new ImmutableVector(1, 0, 0);
    int randomFace = rand.nextInt(2);
    if (randomFace == 0)
    {
      randPivot = new ImmutableVector(xSign, ySign, zSign * rand.nextFloat());
    } else if (randomFace == 1)
    {
      randPivot = new ImmutableVector(xSign * rand.nextFloat(), ySign, zSign);
    } else if (randomFace == 2)
    {
      randPivot = new ImmutableVector(xSign, ySign * rand.nextFloat(), zSign);
    }
    ImmutableVector parentPivot = new ImmutableVector(-randPivot.X, -randPivot.Y, -randPivot.Z);
    randomFace = rand.nextInt(2);
    if (randomFace == 0)
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size,
              Axis.UNIT_Z.getImmutableVector(), Axis.UNIT_Z.getImmutableVector(),
              randAngle);
    } else
    {
      randBlock = new GeneBlock(parentOffset, randPivot, parentPivot, size, Axis.UNIT_X.getImmutableVector(), Axis.UNIT_X.getImmutableVector(), randAngle);
    }
    geneBlocks.remove(index);
    geneBlocks.add(index, randBlock);
    Genome newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    for (int i = 0; i < geneBlocks.size(); i++)
    {
      newGenome.addGeneBlock(geneBlocks.get(i));
    }
    for (GeneNeuron geneNeuron : geneNeurons)
    {
      newGenome.addGeneNeuron(geneNeuron);
    }
    return newGenome;
  }

  /**
   * Randomize a selected neuron.
   *
   * @param genome genome to modify
   * @param index  the index in the neuron list to randomize
   * @return mutated genome.
   */
  public static Genome randomizeNeuron(Genome genome, int index)
  {
    ArrayList<GeneBlock> geneBlocks;
    Random rand = MainSim.RANDOM;
    ArrayList<GeneNeuron> geneNeurons;

    Genome newGenome = new Genome(genome.getRootSize(), genome.getRootEulerAngles());
    geneBlocks = genome.getGENE_BLOCKS();
    geneNeurons = genome.getGENE_NEURONS();

    int sign = (rand.nextBoolean()) ? 1 : -1;
    EnumNeuronInput aInput = EnumNeuronInput.TIME;
    EnumNeuronInput bInput = EnumNeuronInput.CONSTANT;
    EnumNeuronInput cInput = EnumNeuronInput.CONSTANT;
    EnumNeuronInput dInput = EnumNeuronInput.CONSTANT;
    EnumNeuronInput eInput = EnumNeuronInput.CONSTANT;
    GeneNeuron randNeuron2 = new GeneNeuron(
            index, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
            aInput, bInput, cInput, dInput, eInput, //EnumNeuronInput types
            0, 0, 6, -1 * sign * Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
            EnumOperator.ADD, //Binary operator for merging A and B
            EnumOperator.IDENTITY, //Unary operator for after A and B are merged
            EnumOperator.ADD, //Binary operator for merging D and E
            EnumOperator.IDENTITY); //Unary operator for after D and E are merged
    GeneNeuron randNeuron = new GeneNeuron(
            index, //This is the list index of leg1 the corresponding block. As long as we generate lists in the same order this should work fine.
            aInput, bInput, cInput, dInput, eInput, //EnumNeuronInput types
            0, 0, 5, sign * Float.MAX_VALUE, 0, //are the float values that correspond to each type. If the type is not Constant, then it will be ignored.
            EnumOperator.ADD, //Binary operator for merging A and B
            EnumOperator.IDENTITY, //Unary operator for after A and B are merged
            EnumOperator.ADD, //Binary operator for merging D and E
            EnumOperator.IDENTITY); //Unary operator for after D and E are merged

    for (GeneBlock geneBlock : geneBlocks)
    {
      newGenome.addGeneBlock(geneBlock);
    }
    for (GeneNeuron geneNeuron : geneNeurons)
    {
      newGenome.addGeneNeuron(geneNeuron);
    }
    newGenome.addGeneNeuron(randNeuron2);
    newGenome.addGeneNeuron(randNeuron);

    return newGenome;
  }
}
