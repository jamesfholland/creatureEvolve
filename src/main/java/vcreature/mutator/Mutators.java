package vcreature.mutator;

/**
 * Created by Tess Daughton on 10/27/15
 */
public enum Mutators
{
  ADDER, DUPLICATOR, INVERTER, MOVER, RANDOMIZER, ROTATOR, SCALER, SUBSTRACTOR, SYMMETRIZER;

  private Mutators currentMutator = null;

  protected void setCurrentMutator(Mutators mutator)
  {
    currentMutator = mutator;
  }

  protected Mutators getCurrentMutator()
  {
    return this.currentMutator;
  }
}
