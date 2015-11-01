package vcreature.mutator;


import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.genotype.ImmutableVector;
import vcreature.mainSimulation.GenePool;
import vcreature.mainSimulation.MutationTester;
import vcreature.mutator.hillclimbing.*;
import vcreature.mainSimulation.MainSim;

import java.util.Random;

/**
 * This will manage finding mutated genomes.
 */
public class HillClimbingManager
{
  Random rand = new Random();
  private Genome parentGenome;
  private Genome testingGenome;

  private boolean retesting = false;

  /**
   * Sets up the mutation manager. Currently always seeds with FlappyBird.
   * In the future will be handed a GenEpoOl, that it picks a creature from.
   */
  public HillClimbingManager()
  {
    //testingGenome=SpawnCreatureGenoform.makeTableMonster();
    testingGenome = GenePool.getRandom(); //GenoFile.readGenomeFromPool("7.20_Flappy.geno");


    //testingGenome = new TessMonster(rootSize, eulerAngles, jointSize, 4);
    //testingGenome = GenePool.getRandom(); //GenoFile.readGenomeFromPool("7.20_Flappy.geno");
    //testingGenome = SpawnCreatureGenoform.makeFlappyBird();
    //testingGenome= CutAndSplice.cutAndSplice(SpawnCreatureGenoform.makeFlappyBird(),SpawnCreatureGenoform.makeTableMonster()).get(1);
    testingGenome = GenePool.getRandom();
    parentGenome = testingGenome;
    Mutators.setCurrentMutator(Mutators.getRandomMutator());
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
  public synchronized Genome getNextCreature(float testedFitness)
  {
    //Check if first run.
    if (testedFitness == -1)
    {
      return testingGenome;
    }
    //this.chooseMutationMethod(testedFitness);

    if (retesting)
    { retesting = false;
      return testingGenome;
    }
    return MainSim.MUTATION_TESTER.getBestCreatureGenome();
  }

  private void chooseMutationMethod(float testedFitness)
  {
    if (testedFitness > parentGenome.getFitness())
    {
      if (retesting)
      {
        retesting = false; //Want to double check that it wasn't a flawed test.
        System.out.println("Better Creature found, Fitness: " + testedFitness);
        testingGenome.setFitness(testedFitness);
        parentGenome = testingGenome;
        GenoFile.writeGenome(parentGenome);
      }
      else
      {
        retesting = true;
      }
    }
    else Mutators.setCurrentMutator(Mutators.getRandomMutator());
  }
}

