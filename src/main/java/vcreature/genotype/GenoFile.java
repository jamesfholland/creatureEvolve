package vcreature.genotype;

import java.io.*;

/**
 * GenoFile handles all input and output of genome files.
 */
public class GenoFile
{
  private static final String SAVE_LOCATION = "creatures/";
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
   * @param filePath the file's name, not the path to file.
   * @return the parsed genome or null if an error occurred.
   */
  public static Genome readGenome(String filePath)
  {
    BufferedReader fileIn = null;
    try
    {
      fileIn = new BufferedReader(new FileReader(new File(GENE_POOL_LOCATION + filePath)));
      Genome genome = new Genome(fileIn);
      return genome;
    }
    catch (IOException e)
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
}
