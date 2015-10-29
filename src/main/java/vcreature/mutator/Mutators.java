package vcreature.mutator;
import java.util.Random;
/**
 * Created by Tess Daughton on 10/27/15
 */
public enum Mutators
{
  ADDER, DUPLICATOR, INVERTER, MOVER, RANDOMIZER, ROTATOR, SCALER, SUBTRACTOR, SYMMETRIZER, SCALE_ROOT, SCALE_BLOCK;

  private static Mutators currentMutator = null;
  private static Mutators [] mutatorsList = Mutators.values();
  private static Random rand = new Random();

  protected static void setCurrentMutator(Mutators mutator)
  {
    currentMutator = mutator;
  }

  protected static Mutators getCurrentMutator()
  {
    return currentMutator;
  }
  protected static Mutators getRandomMutator()
  {
    return mutatorsList[rand.nextInt(mutatorsList.length)];
  }
}
