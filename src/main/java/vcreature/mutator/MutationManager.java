package vcreature.mutator;

import vcreature.genotype.Genome;

import java.util.LinkedList;
import java.util.Random;

/**
 * This will manage finding mutated genomes.
 */
public class MutationManager
{

  public static LinkedList<Genome> getMutants(Genome seed)
  {
    LinkedList<Genome> mutants = new LinkedList<>();

    //Do a bunch of mutations and store in mutants.

    return mutants;
  }

  public Genome mutate(Genome currentCreature)
  {
    Genome randomGenome = Randomizer.randomize(currentCreature);
    return randomGenome;
  }
}
