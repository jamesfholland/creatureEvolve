package vcreature.genotype;

import com.jme3.math.Vector3f;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

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

  public void toFile(BufferedWriter fileOut) throws IOException
  {
    fileOut.write(String.format("%f,%f,%f\n", this.X, this.Y, this.Z));
  }

  public static ImmutableVector parseImmutableVector(String line)
  {
    return null;
  }

  /**
   * This is overridden to maintain stability in genome hashes between runs.
   * @return an integer that is the hash.
   */
  @Override
  public int hashCode()
  {
    return Objects.hash(X, Y, Z);
  }
}
