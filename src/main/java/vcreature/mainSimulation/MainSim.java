package vcreature.mainSimulation;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import vcreature.mutator.Manager;
import vcreature.mutator.hillclimbing.Symmetrizer;

import java.util.Random;

/**
 * Entry point to the program
 * Creates a JFrame, which takes care of embedding SimpleApplication inside of itself
 * Creates a headless SimpleApp used for testing creatures
 */
public class MainSim
{
  public static final Random RANDOM = new Random(System.currentTimeMillis());

  public static void main(final String[] args)
  {
    System.out.println("Starting Simulation");

    java.awt.EventQueue.invokeLater(new Runnable()
    {
      @Override
      public void run()
      { //starting up headless Simple Application for testing creatures inside of
        Manager manager;


        if (args.length > 0)
        {
          if (args[0].equalsIgnoreCase("hill"))
          {
            manager = new Manager(Manager.MutationType.HILL);
          }
          else
          {
            manager = new Manager(Manager.MutationType.GENETICHILL);
          }
        }
        else
        {
          manager = new Manager(Manager.MutationType.GENETICHILL);
        }
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
          System.out.println("Starting GUI");
          //GUI version
          new SimFrame(manager);
        }
      }
    });
  }
}
