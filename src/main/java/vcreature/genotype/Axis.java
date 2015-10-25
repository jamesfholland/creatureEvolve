package vcreature.genotype;

/**
 * This is enum contains axes that correspond to JMonkey's Vector3f axis.
 * This enum is to aid in making genes immutable and final.
 */
public enum Axis
{
  UNIT_X
      {
        public ImmutableVector getImmutableVector()
        {
          return new ImmutableVector(1, 0, 0);
        }
      },
  UNIT_Y
      {
        public ImmutableVector getImmutableVector()
        {
          return new ImmutableVector(0, 1, 0);
        }
      },
  UNIT_Z
      {
        public ImmutableVector getImmutableVector()
        {
          return new ImmutableVector(0, 0, 1);
        }
      };

  public abstract ImmutableVector getImmutableVector();

}
