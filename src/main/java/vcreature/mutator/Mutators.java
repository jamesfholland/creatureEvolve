package vcreature.mutator;

/**
 * Created by Tess Daughton on 10/27/15
 */
public enum Mutators
{
  ADDER, DUPLICATOR, INVERTER, MOVER, RANDOMIZER, ROTATOR, SCALER, SUBSTRACTOR, SYMMETRIZER;

  private static Mutators currentMutator = null;

  protected static void setCurrentMutator(Mutators mutator)
  {
    currentMutator = mutator;
  }

  protected static Mutators getCurrentMutator()
  {
    return currentMutator;
  }
}
