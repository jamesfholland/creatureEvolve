package vcreature.genotype;

import com.jme3.math.Vector3f;

/**
 * This is enum contains axes that correspond to JMonkey's Vector3f axis.
 * This enum is to aid in making genes immutable and final.
 */
public enum Axis
{
  UNIT_X
      {
        public Vector3f getVector3f()
        {
          return Vector3f.UNIT_X;
        }
      },
  UNIT_Y
      {
        public Vector3f getVector3f()
        {
          return Vector3f.UNIT_Y;
        }
      },
  UNIT_Z
      {
        public Vector3f getVector3f()
        {
          return Vector3f.UNIT_Z;
        }
      };

  public abstract Vector3f getVector3f();

}
