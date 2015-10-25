package vcreature.genotype;

import com.jme3.math.Vector3f;

/**
 * An immutable wrapper for JMonkey's Vector3f.
 * This allows vectors to be shared across genomes without risk of corruption or thread safety.
 */
public class ImmutableVector
{

  public final float X;

  public final float Y;

  public final float Z;

  public ImmutableVector(float x, float y, float z)
  {
    this.X = x;
    this.Y = y;
    this.Z = z;
  }

  public Vector3f getVector3f()
  {
    return new Vector3f(this.X, this.Y, this.Z);
  }
}
