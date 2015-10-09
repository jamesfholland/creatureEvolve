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

  public ImmutableVector(Vector3f vector3f)
  {
    this.X = vector3f.x;
    this.Y = vector3f.y;
    this.Z = vector3f.z;
  }

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

  public ImmutableVector getChangedX(float x)
  {
    return new ImmutableVector(x, this.Y, this.Z);
  }

  public ImmutableVector getChangedY(float y)
  {
    return new ImmutableVector(this.X, y, this.Z);
  }

  public ImmutableVector getChangedZ(float z)
  {
    return new ImmutableVector(this.X, this.Y, z);
  }

}
