package vcreature.mainSimulation;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;

/**
 * ThreadController contains everything for running a physics simulation. A copy exists for each thread.
 */
public class ThreadController
{
  private BulletAppState bulletAppState;
  private PhysicsSpace physicsSpace;
  ThreadController()
  {

  }
}
