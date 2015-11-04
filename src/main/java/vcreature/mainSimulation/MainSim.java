package vcreature.mainSimulation;

import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import vcreature.mutator.Manager;

import java.util.Random;

/**
 * Entry point to the program
 * Creates a JFrame, which takes care of embedding SimpleApplication inside of itself
 */
public class MainSim
{
  public static final Random RANDOM = new Random(System.currentTimeMillis());

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

  public static void main(final String[] args)
  {
    System.out.println("Starting Simulation");

    java.awt.EventQueue.invokeLater(new Runnable()
    {
      @Override
      public void run()
      { //starting up headless Simple Application for testing creatures inside of
        Manager manager = new Manager();
        MutationTester mutationTester = new MutationTester(manager);
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1024, 768);
        settings.setSamples(4);
        settings.setVSync(true);
        settings.setFrequency(60);
        settings.setTitle("Creatures Evolution");
        mutationTester.setShowSettings(false);
        mutationTester.setSettings(settings);
        mutationTester.start(JmeContext.Type.Headless);
        if (args.length == 0) // if the user enters ANY command line argument, the GUI will NOT run
        {
          System.out.println("Starting App");
          new SimFrame(manager);
        }
      }
    });
  }
}
