package vcreature.genotype;

import vcreature.mainSimulation.FlappyBirdGenoform;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

/**
 * GenoFile handles all input and output of genomes.
 */
public class GenoFile
{
  private static final String SAVE_LOCATION = "creatures/";

  public static void writeGenome(Genome genome)
  {
    try
    {
      BufferedWriter fileOut = new BufferedWriter(new FileWriter(SAVE_LOCATION + genome.getFileName()));
      genome.toFile(fileOut);
      fileOut.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  public static Genome readGenome(URL filePath)
  {
    return FlappyBirdGenoform.getFlappyBirdGenoform();
  }
}
