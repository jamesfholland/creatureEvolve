package vcreature.genotype;

import com.jme3.math.Vector3f;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

  /**
   * Basic vector constructor
   *
   * @param x the x value, unit determined by user
   * @param y the y value, unit determined by user
   * @param z the z value, unit determined by user
   */
  public ImmutableVector(float x, float y, float z)
  {
    this.X = x;
    this.Y = y;
    this.Z = z;
  }

  /**
   * Basic vector constructor from Vector3f
   *
   * @param vector3f vector to base upon.
   */
  public ImmutableVector(Vector3f vector3f)
  {
    this.X = vector3f.x;
    this.Y = vector3f.y;
    this.Z = vector3f.z;
  }

  /**
   * Constructs a ImmutableVector from a BufferedReader. Used when parsing a genome file.
   *
   * @param fileIn The stream to parse a vector from.
   * @throws IOException handled in GenoFile
   */
  public ImmutableVector(BufferedReader fileIn) throws IOException
  {
    this.X = Float.parseFloat(fileIn.readLine());
    this.Y = Float.parseFloat(fileIn.readLine());
    this.Z = Float.parseFloat(fileIn.readLine());
  }

  /**
   * Converts the vector back to Vector3f
   *
   * @return a new Vector3f
   */
  public Vector3f getVector3f()
  {
    return new Vector3f(this.X, this.Y, this.Z);
  }

  /**
   * Stores the vector into an output stream.
   * The format of the output is
   * [X value]\n
   * [Y value]\n
   * [Z value]\n
   *
   * @param fileOut the BufferedWriter we will write into.
   * @throws IOException handled in GenoFile
   */
  public void toFile(BufferedWriter fileOut) throws IOException
  {
    fileOut.write(String.format("%f\n%f\n%f\n", this.X, this.Y, this.Z));
  }

  /**
   * This is overridden to maintain stability in genome hashes between runs.
   *
   * @return an integer that is the hash.
   */
  @Override
  public int hashCode()
  {
    return Objects.hash(X, Y, Z);
  }

  /**
   * gets the X value from a immutable vector
   *
   * @return a float for X value of the Immutable vector
   */
  public float getX()
  {
    return this.X;
  }

  /**
   * gets the Y value from a immutable vector
   *
   * @return a float for Y value of the Immutable vector
   */
  public float getY()
  {
    return this.Y;
  }

  /**
   * gets the Z value from a immutable vector
   *
   * @return a float for Z value of the Immutable vector
   */
  public float getZ()
  {
    return this.Z;
  }

}
