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
  private Mutators mutator;

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
    mutator = mutator.getCurrentMutator();
    switch (mutator)
    {
      case ADDER:
        testingGenome = Adder.addBlock(parentGenome);
        mutator.setCurrentMutator(Mutators.ADDER);
        break;
      case DUPLICATOR:
        testingGenome = Duplicator.duplicateLimb(parentGenome);
        mutator.setCurrentMutator(Mutators.DUPLICATOR);
        break;
      case INVERTER:
        // implement
        mutator.setCurrentMutator(Mutators.INVERTER);
        break;
      case MOVER:
        //implement
        mutator.setCurrentMutator(Mutators.INVERTER);
        break;
      case RANDOMIZER:
        testingGenome = Randomizer.randomize(parentGenome);
        mutator.setCurrentMutator(Mutators.RANDOMIZER);
        break;
      case ROTATOR:
        //implement
        mutator.setCurrentMutator(Mutators.ROTATOR);
        break;
      case SCALER:
        testingGenome = Scaler.scale(parentGenome, 1.1f);
        mutator.setCurrentMutator(Mutators.SCALER);
        break;
      case SUBSTRACTOR:
        //implement
        mutator.setCurrentMutator(Mutators.SUBSTRACTOR);
      case SYMMETRIZER:
        //implement
        mutator.setCurrentMutator(Mutators.SYMMETRIZER);
        break;
      default:
        //implement
        mutator.setCurrentMutator(Mutators.RANDOMIZER);
      float scaler;
      scaler = rand.nextFloat() + 1;
      testingGenome = ScaleSingleBlock.scaleBlock(parentGenome, scaler);

//    testingGenome = Randomizer.randomize(parentGenome);
//    testingGenome = Mover.moveLimbs(testingGenome);
   // testingGenome = Duplicator.duplicateLimb(parentGenome);

    //testingGenome = Scaler.scale(parentGenome, 1.1f);
    //testingGenome = Subtracter.subtractBlocks(parentGenome);
    return testingGenome;
  }
}