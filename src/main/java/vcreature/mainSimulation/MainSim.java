package vcreature.mainSimulation;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import vcreature.mutator.Manager;

import java.util.Random;

/**
 * Entry point to the program
 * Creates a JFrame, which takes care of embedding SimpleApplication inside of itself
 * Creates a headless SimpleApp used for testing creatures
 */
public class MainSim
{
  /**
   * A shared Random object across all classes that use random.
   */
  public static final Random RANDOM = new Random(System.currentTimeMillis());

  /**
   * This starts the entire application. If GUI mode, it spawns two applications for viewing the
   * @param args hill starts headless hill-climbing. both starts headless genetic/hill climbing
   */
  public static void main(final String[] args)
  {
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
        mutationTester.start(JmeContext.Type.Headless);
        if (args.length == 0) // if the user enters ANY command line argument, the GUI will NOT run
        {
          //GUI version
          new SimFrame(manager);
        }
      }
    });
  }
}
