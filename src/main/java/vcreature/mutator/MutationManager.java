package vcreature.mutator;


import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;

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
    Mutators.setCurrentMutator(Mutators.RANDOMIZER);
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
  public synchronized Genome getNextCreature(float testedFitness)
  {
    //Check if first run.
    if (testedFitness == -1)
    {
      return testingGenome;
    }
    this.chooseMutationMethod(testedFitness);

    if(retesting) return testingGenome;
    return this.mutateGenome();
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


  private Genome mutateGenome()
  {
    switch (Mutators.getCurrentMutator())
    {
      case ADDER:
        testingGenome = Adder.addBlock(parentGenome);
        testingGenome = Adder.addBlock(testingGenome);

        Mutators.setCurrentMutator(Mutators.ADDER);
        break;
      case DUPLICATOR:
        testingGenome = Duplicator.duplicateLimb(parentGenome);
        Mutators.setCurrentMutator(Mutators.DUPLICATOR);
        break;
      case INVERTER:
        // implement
        Mutators.setCurrentMutator(Mutators.INVERTER);
        break;
      case MOVER:
        //implement
        Mutators.setCurrentMutator(Mutators.INVERTER);
        break;
      case RANDOMIZER:
        testingGenome = Randomizer.randomize(parentGenome);
        Mutators.setCurrentMutator(Mutators.RANDOMIZER);
        break;
      case ROTATOR:
        //implement
        Mutators.setCurrentMutator(Mutators.ROTATOR);
        break;
      case SCALER:
        testingGenome = Scaler.scale(parentGenome, 1.1f);
        Mutators.setCurrentMutator(Mutators.SCALER);
        break;
      case SUBSTRACTOR:
        //implement
        Mutators.setCurrentMutator(Mutators.SUBSTRACTOR);
      case SYMMETRIZER:
        //implement
        Mutators.setCurrentMutator(Mutators.SYMMETRIZER);
        break;
      default:
        //implement
        testingGenome = Randomizer.randomize(parentGenome);
        Mutators.setCurrentMutator(Mutators.RANDOMIZER);
//        float scaler;
//        scaler = rand.nextFloat() + 1;
//        testingGenome = ScaleSingleBlock.scaleBlock(parentGenome, scaler);
    }
    return testingGenome;
  }
}
