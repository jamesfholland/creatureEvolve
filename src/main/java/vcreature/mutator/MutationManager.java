package vcreature.mutator;

import com.sun.org.apache.bcel.internal.generic.DUP;
import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.FlappyBirdGenoform;

import java.util.Random;

/**
 * This will manage finding mutated genomes.
 */
public class MutationManager
{
  Random rand = new Random();
  private Genome parentGenome;
  private Genome testingGenome;

  static private float min = .5f;
  static private float max = 5f;  /**
   * Sets up the mutation manager. Currently always seeds with FlappyBird.
   * In the future will be handed a GenEpoOl, that it picks a creature from.
   */
  public MutationManager()
  {
    testingGenome = GenoFile.readGenome("-1.00_Flappy.geno");
    parentGenome = testingGenome;
    // GenoFile.writeGenome(testingGenome);
  }

  /**
   * This returns the next mutant based on the current creature we are hill
   * climbing from.
   *
   * @param testedFitness the calculated fitness in meters.
   *                      If this is -1, this means we are just starting and
   *                      need to test the seed.
   * @return the next genome to test.
   */
  public Genome getNextCreature(float testedFitness)
  {
    int numberOfMutationMethods = 6;
    int randomMethodPicker;
    randomMethodPicker = rand.nextInt(numberOfMutationMethods);
    //Check if first run.
    if (testedFitness == -1)
    {
      return testingGenome;
    }

    if (testedFitness > parentGenome.getFitness())
    {
      System.out.println("Better Creature found, Fitness: " + testedFitness);
      testingGenome.setFitness(testedFitness);
      parentGenome = testingGenome;
    }
    boolean override= true;

    if(override)
    {
      testingGenome = Adder.addBlock(parentGenome);
    }
    else
    {
      if (randomMethodPicker == 0)
      {
        testingGenome = Adder.addBlock(parentGenome);
      }
      else if (randomMethodPicker == 1)
      {
        testingGenome = Randomizer.randomize(parentGenome);
      }
      else if (randomMethodPicker == 2)
      {
        testingGenome = Subtracter.subtractBlock(parentGenome);
      }
      else if (randomMethodPicker == 3)
      {
        float scaler;
        scaler = rand.nextFloat() * 3;
        testingGenome = Scaler.scale(parentGenome, scaler);
      }
      else if (randomMethodPicker == 4)
      {
        testingGenome = Duplicator.duplicateLimb(parentGenome);
      }
      else if (randomMethodPicker == 5)
      {
        testingGenome = Mover.moveLimbs(testingGenome);
      }
    }


//    testingGenome = Randomizer.randomize(parentGenome);
//
    //testingGenome = Scaler.scale(parentGenome, 1.1f);
    //testingGenome = Subtracter.subtractBlocks(parentGenome);
    return testingGenome;
  }
}

