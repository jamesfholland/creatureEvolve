package vcreature.mutator;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.FlappyBirdGenoform;

/**
 * This will manage finding mutated genomes.
 */
public class MutationManager
{

  private Genome parentGenome;
  private Genome testingGenome;

  /**
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
   * This returns the next mutant based on the current creature we are hill climbing from.
   * @param testedFitness the calculated fitness in meters.
   *                      If this is -1, this means we are just starting and need to test the seed.
   * @return the next genome to test.
   */
  public Genome getNextCreature(float testedFitness)
  {
    //Check if first run.
    if (testedFitness == -1)
    {
      return testingGenome;
    }

    if (testedFitness > parentGenome.getFitness())
    {
      System.out.println("Better Creature found, Fitness: "+ testedFitness);
      testingGenome.setFitness(testedFitness);
      parentGenome = testingGenome;
    }

    //testingGenome = Randomizer.randomize(parentGenome);
    //testingGenome = Scaler.scale(parentGenome, 1.05f);
    testingGenome = Subtracter.subtractBlocks(parentGenome);
    return testingGenome;
  }
}

