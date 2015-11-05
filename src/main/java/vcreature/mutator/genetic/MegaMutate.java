package vcreature.mutator.genetic;

import vcreature.genotype.Genome;
import vcreature.mainSimulation.MainSim;
import vcreature.mutator.hillclimbing.Mutators;

/**
 * Created by Dayloki on 11/4/2015.
 */

/**
 * Mutates genome 10 times
 */
public class MegaMutate
{
  /**
   * This method will mutate a single genome 10 times.
   * @param genome a genome to be mutated
   * @return the genome mutated 10 times
   */
  public static Genome megaMutate(Genome genome)
  {
    Genome mutatedCreature=genome;
    Mutators[] allMutators=Mutators.values();
    for (int i = 0; i <10 ; i++)
    {
      Mutators randMutator=allMutators[MainSim.RANDOM.nextInt(allMutators.length)];
      mutatedCreature=randMutator.mutate(mutatedCreature);
    }
    return mutatedCreature;
  }
}
