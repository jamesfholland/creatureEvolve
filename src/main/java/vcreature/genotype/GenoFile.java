package vcreature.genotype;

import java.io.*;
import java.util.LinkedList;

/**
 * GenoFile handles all input and output of genome files.
 */
public class GenoFile
{
  private static final String SAVE_LOCATION = "creatures/";
  /**
   * The location of the GenePool relative to the execution location.
   */
  private static final String GENE_POOL_LOCATION = "GenePool/";

  /**
   * Simply pass a Genome and it will write it to the creatures folder.
   * It will overwrite if a name collision occurs, but this should be extremely rare
   * as the name is based on a 32 bit hash of the creature and the fitness jumped.
   *
   * @param genome a complete genome.
   */
  public static void writeGenome(Genome genome)
  {
    BufferedWriter fileOut = null;
    try
    {
      fileOut = new BufferedWriter(new FileWriter(SAVE_LOCATION + genome.getFileName()));
      genome.toFile(fileOut);
      fileOut.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (fileOut != null)
      {
        try
        {
          fileOut.close();
        }
        catch (IOException ignored)
        {
        }
      }
    }
  }

  /**
   * Reads a genome from file located in the GenePool folder.
   *
   * @param fileName the file's name, not the path to file.
   * @return the parsed genome or null if an error occurred.
   */
  private static Genome readGenomeFromPool(String fileName)
  {
    return readGenome(GENE_POOL_LOCATION + fileName);
  }

  /**
   * Returns a Genome from an arbitrary file path
   *
   * @param filePath The path to the file.
   * @return genome found in the file.
   */
  public static Genome readGenome(String filePath)
  {
    BufferedReader fileIn = null;
    try
    {
      fileIn = new BufferedReader(new FileReader(new File(filePath)));
      Genome genome = new Genome(fileIn);
      return genome;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
    finally
    {
      try
      {
        if (fileIn != null)
        {
          fileIn.close();
        }
      }
      catch (IOException ignored)
      {
      }
    }
  }

  /**
   * Loads all genomes into a LinkedList.
   *
   * @return List of genomes in starter GenePool.
   */
  public static LinkedList<Genome> loadGenePool()
  {
    LinkedList<Genome> genomes = new LinkedList<>();
    File folder = new File(GenoFile.GENE_POOL_LOCATION);

    File[] files = folder.listFiles();

    if (files != null)
    {
      for (int i = 0; i < files.length; i++)
      {
        if (files[i].isFile() && files[i].getName().endsWith(".geno"))
        {
          Genome genome = GenoFile.readGenomeFromPool(files[i].getName());
          if (genome != null)
          {
            genomes.add(genome);
          }
        }
      }
    }
    return genomes;
  }
}
