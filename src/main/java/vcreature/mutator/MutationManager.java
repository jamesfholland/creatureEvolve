package vcreature.mutator;


import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;
import vcreature.mainSimulation.GenePool;
import vcreature.mainSimulation.SpawnCreature;
import vcreature.mainSimulation.SpawnCreatureGenoform;
import vcreature.mutator.geneticMerger.CutAndSplice;

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
    //testingGenome = GenePool.getRandom(); //GenoFile.readGenome("7.20_Flappy.geno");
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

    boolean override =false;
    if(override)
    {
      testingGenome = Cleaner.cleanGenome(parentGenome);
    }
    else
    {
      float scaler;
      switch (Mutators.getCurrentMutator())
      {
        case ADDER:
          testingGenome = Adder.addBlock(parentGenome);
          testingGenome = Symmetrizer.basicSymmetrize(testingGenome);
//        testingGenome = Adder.addBlock(testingGenome);

          Mutators.setCurrentMutator(Mutators.ADDER);
          break;
        case DUPLICATOR:
          testingGenome = Duplicator.duplicateLimb(parentGenome);
          Mutators.setCurrentMutator(Mutators.DUPLICATOR);
          break;
        case INVERTER:
          // implement
          testingGenome = Inverter.basicInverter(parentGenome);
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
          testingGenome = Symmetrizer.basicSymmetrize(testingGenome);

          break;
        case SCALER:
          scaler = rand.nextFloat() * 2;
          testingGenome = Scaler.scale(parentGenome, scaler);
          Mutators.setCurrentMutator(Mutators.SCALER);
          break;
        case SCALE_ROOT:
          scaler = rand.nextFloat() * 3;
          testingGenome = ScaleSingleBlock.scaleRoot(parentGenome, scaler);
          Mutators.setCurrentMutator(Mutators.SCALE_ROOT);
          break;
        case SCALE_BLOCK:
          scaler = rand.nextFloat() * 3;
          testingGenome = ScaleSingleBlock.scaleBlock(parentGenome, scaler);
          Mutators.setCurrentMutator(Mutators.SCALE_ROOT);
          testingGenome = Symmetrizer.basicSymmetrize(testingGenome);

          break;
        case SUBTRACTOR:
          //implement
          Mutators.setCurrentMutator(Mutators.SUBTRACTOR);
          break;
        case SYMMETRIZER:
          //implement
          Mutators.setCurrentMutator(Mutators.SYMMETRIZER);
          break;
        default:
          //implement
          testingGenome = Randomizer.randomize(parentGenome);
          testingGenome = Symmetrizer.basicSymmetrize(testingGenome);
          Mutators.setCurrentMutator(Mutators.RANDOMIZER);
          break;
      }
    }
    return testingGenome;
  }
}
