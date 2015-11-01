package vcreature.mutator.hillclimbing;

import vcreature.mainSimulation.MainSim;

/**
 * Created by Tess Daughton on 10/27/15
 */
public enum Mutators
{
  ADDER, DUPLICATOR, INVERTER, MOVER, RANDOMIZER, ROTATOR, SCALER, SUBTRACTOR, SYMMETRIZER, SCALE_ROOT, SCALE_BLOCK;

  private static Mutators currentMutator = null;
  private static Mutators [] mutatorsList = Mutators.values();

  public static void setCurrentMutator(Mutators mutator)
  {
    currentMutator = mutator;
  }

  public static Mutators getCurrentMutator()
  {
    return currentMutator;
  }
  public static Mutators getRandomMutator()
  {
    return mutatorsList[MainSim.RANDOM.nextInt(mutatorsList.length)];
  }
}
