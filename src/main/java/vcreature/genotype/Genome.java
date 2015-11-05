package vcreature.genotype;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
   *
   * @param rootSize        The size of the creature's root.
   * @param rootEulerAngles The orientation of the root.
   */
  public Genome(ImmutableVector rootSize, ImmutableVector rootEulerAngles)
  {
    ROOT_SIZE = rootSize;
    ROOT_EULER_ANGLES = rootEulerAngles;
    GENE_BLOCKS = Collections.synchronizedList(new ArrayList<GeneBlock>());
    GENE_NEURONS = Collections.synchronizedList(new ArrayList<GeneNeuron>());
  }

  /**
   * Constructs a full Genome from an input file.
   *
   * @param fileIn BufferedReader containing the Genome data.
   * @throws IOException this is handled in GenoFile or by whatever called this constructor.
   */
  public Genome(BufferedReader fileIn) throws IOException
  {
    GENE_BLOCKS = Collections.synchronizedList(new ArrayList<GeneBlock>());
    GENE_NEURONS = Collections.synchronizedList(new ArrayList<GeneNeuron>());

    //Read in file name (if we want to store the fitness we can parse it here.)
    this.fitness = Float.parseFloat(fileIn.readLine().split("_")[0]);
    fileIn.readLine(); //Read #ROOT
    ROOT_SIZE = new ImmutableVector(fileIn);
    ROOT_EULER_ANGLES = new ImmutableVector(fileIn);

    fileIn.readLine(); //Read #BLOCKS
    String header = fileIn.readLine();
    while (!Objects.equals(header, "") && header != null)
    {
      if (Objects.equals(header, "#Block"))
      {
        GENE_BLOCKS.add(new GeneBlock(fileIn));
      }
      else if (Objects.equals(header, "#Neuron"))
      {
        GENE_NEURONS.add(new GeneNeuron(fileIn));
      }
      header = fileIn.readLine();
    }
  }

  /**
   * Synchronized List, in case the list is read while inserting.
   *
   * @param geneBlock the new GeneBlock we are adding.
   */
  public void addGeneBlock(GeneBlock geneBlock)
  {
    GENE_BLOCKS.add(geneBlock); //Synchronized list takes care of thread safety.
  }

  /**
   * Synchronized List, in case the list is read while inserting.
   * @param geneNeuron  neuron to add into list
   */
  public void addGeneNeuron(GeneNeuron geneNeuron)
  {
    GENE_NEURONS.add(geneNeuron);  //Synchronized list takes care of thread safety.
  }

  /**
   * Return an copy of our GeneBlocks.
   * @return arraylist of geneblocks
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
   * @return arraylist of GeneNeurons
   */
  public ArrayList<GeneNeuron> getGENE_NEURONS()
  {
    synchronized (GENE_NEURONS) //Needed since it uses an iterator to create the copy.
    {
      return new ArrayList<>(GENE_NEURONS);
    }
  }

  /**
   *Gets root size of Genome
   * @return The Vector3f form of the root size.
   */
  public ImmutableVector getRootSize()
  {
    return ROOT_SIZE;
  }

  /**
   * Gets the euler angles of Genome
   * @return The Vector3f form of the root euler angles.
   */
  public ImmutableVector getRootEulerAngles()
  {
    return ROOT_EULER_ANGLES;
  }

  /**
   * Set the fitness as determined by the physics testing.
   *
   * @param fitness in meters
   */
  public void setFitness(float fitness)
  {
    this.fitness = fitness;
  }

  /**
   * Get the fitness in meters of the creature.
   * A fitness of -1 indicates the creature has not been tested.
   * @return float of fittness
   */
  public float getFitness()
  {
    return this.fitness;
  }

  /**
   * Generates a filename based on the fitness and hashcode of object.
   *
   * @return the file name formatted as [fitness]_[hashcodeInHex]
   */
  public String getFileName()
  {
    return String.format("%.2f_%04X.geno", this.fitness, this.hashCode());
  }

  /**
   * Writes the genome to the FileOutputStream.
   * It first writes the root block information,
   * then loops over GENE_BLOCKS, and lastly loops over GENE_NEURONS.
   *
   * @param fileOut the outputstream we are writing to.
   */
  public void toFile(BufferedWriter fileOut) throws IOException
  {
    fileOut.write(getFileName() + "\n");
    fileOut.write("#ROOT\n");
    ROOT_SIZE.toFile(fileOut);
    ROOT_EULER_ANGLES.toFile(fileOut);

    fileOut.write("#BLOCKS\n");
    synchronized (this.GENE_BLOCKS)
    {
      for (GeneBlock block : GENE_BLOCKS)
      {
        block.toFile(fileOut);
      }
    }

    fileOut.write("#NEURONS\n");
    synchronized (this.GENE_NEURONS)
    {
      for (GeneNeuron neuron : GENE_NEURONS)
      {
        neuron.toFile(fileOut);
      }
    }
  }

  /**
   * This is overridden to maintain stability in genome hashes between runs.
   *
   * @return an integer that is the hash.
   */
  @Override
  public int hashCode()
  {
    int result = ROOT_SIZE.hashCode();
    result = 31 * result + ROOT_EULER_ANGLES.hashCode();
    synchronized (GENE_BLOCKS)
    {
      for (GeneBlock block : GENE_BLOCKS)
      {
        result = 31 * result + block.hashCode();
      }
    }
    synchronized (GENE_NEURONS)
    {
      for (GeneNeuron neuron : GENE_NEURONS)
      {
        result = 31 * result + neuron.hashCode();
      }
    }
    result = 31 * result + (fitness != +0.0f ? Float.floatToIntBits(fitness) : 0);
    return result;
  }
}
