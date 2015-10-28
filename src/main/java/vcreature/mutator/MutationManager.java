package vcreature.mutator;

import com.sun.org.apache.bcel.internal.generic.DUP;
import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.FlappyBirdGenoform;
import vcreature.mainSimulation.GenePool;
import vcreature.mainSimulation.SpawnCreatureGenoform;

import java.util.Random;

/**
 * This will manage finding mutated genomes.
 */
public class MutationManager
{
  Random rand = new Random();
  private Genome parentGenome;
  private Genome testingGenome;

  private boolean retesting = false;

  /**
   * Sets up the mutation manager. Currently always seeds with FlappyBird.
   * In the future will be handed a GenEpoOl, that it picks a creature from.
   */
  public MutationManager()
  {
    //testingGenome=SpawnCreatureGenoform.makeTableMonster();
    testingGenome = GenePool.getRandom(); //GenoFile.readGenome("7.20_Flappy.geno");
    //testingGenome = SpawnCreatureGenoform.makeFlappyBird();
    parentGenome = testingGenome;
     //GenoFile.writeGenome(testingGenome);
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
    int numberOfMutationMethods = 4;
    int randomMethodPicker;
    randomMethodPicker = rand.nextInt(numberOfMutationMethods);
    //Check if first run.
    if (testedFitness == -1)
    {
      retesting = true; //We want to verify this one well.
      return testingGenome;
    }

    if (testedFitness > parentGenome.getFitness())
    {
      if(retesting)
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
        return testingGenome;
      }
    }

    boolean overRide =false;
    if(overRide)
    {
      float scaler;
      scaler = rand.nextFloat() + 1;
      testingGenome = ScaleSingleBlock.scaleBlock(parentGenome, scaler);
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

        float scaler;
        scaler = rand.nextFloat()+1;
        testingGenome = Scaler.scale(parentGenome, scaler);

      }
      else if (randomMethodPicker == 3)
      {
        float scaler;
        scaler = rand.nextFloat() + 1;
        testingGenome = ScaleSingleBlock.scaleBlock(parentGenome, scaler);
      }
      else if(randomMethodPicker == 4)
      {
    //    testingGenome = Mover.moveLimbs(parentGenome);
      }
      else if(randomMethodPicker == 5)
      {
    //    testingGenome = Duplicator.duplicateLimb(parentGenome);
      }
      else if(randomMethodPicker == 6)
      {
        testingGenome = Subtracter.subtractBlock(parentGenome);
      }
      else if(randomMethodPicker==7)
      {
        testingGenome=Inverter.basicInverter(parentGenome);
      }
    }
    return testingGenome;
  }
}

