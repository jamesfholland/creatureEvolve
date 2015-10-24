
package vcreature.mainSimulation;

import com.jme3.math.Vector3f;

public class MainSim
{
  private void print(String msg, float x)
  {
    String className = this.getClass().getSimpleName();
    System.out.format("%s.%s %.3f\n", className, msg, x);
  }

  private void print(String msg, Vector3f vector)
  {
    String className = this.getClass().getSimpleName();
    System.out.format("%s.%s [%.3f, %.3f, %.3f]\n", className, msg, vector.x, vector.y, vector.z);
  }

  public static void main(String[] args)
  {
    System.out.println("Starting Simulation");

    java.awt.EventQueue.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
       // if (args.length== 0)
        {
          System.out.println("Starting App");
          new SimFrame();
        }
      }
    });
  }
}