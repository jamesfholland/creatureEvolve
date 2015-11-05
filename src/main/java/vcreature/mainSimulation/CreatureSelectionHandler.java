package vcreature.mainSimulation;

import vcreature.genotype.Genome;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Event Handling for the Creature Selection Scroll Panel
 * Passes an action listener to each button (each one represents a creature in the Gene Pool)
 * Displays the selected creature on user selection
 */
class CreatureSelectionHandler implements ActionListener
{
  private ArrayList<JButton> creatures;
  private SimAnimation animation;
  private Timer time;
  private LinkedList<Genome> genepoolCopy;

  /**
   * Class Constructor takes the list of creatures in the Gene Pool
   * and the relevant instance of SimAnimation running the GUI
   * @param creatures   list of creatures in the Gene Pool
   * @param animation   instance of SimAnimation running GUI
   */
  public CreatureSelectionHandler(ArrayList<JButton> creatures, SimAnimation animation)
  {
    this.creatures = creatures;

    for (JButton button : creatures)
    {
      button.addActionListener(this);
    }
    this.animation = animation;
    time = new Timer(0,this);
    time.setDelay(5000);
    time.start();

  }

  /**
   * Selects a creature to display from the genepool.
   * @param e ActionEvent unused.
   */
  public void actionPerformed(ActionEvent e)
  {
    genepoolCopy = GenePool.getCopy();
    for (int j = 0; j < creatures.size() - 1; j++)
    {
      String genomeName = genepoolCopy.get(j).getFileName();
      creatures.get(j).setText(genomeName);
      if (e.getSource() == creatures.get(j))
      {
        animation.setCurrentCreature(genepoolCopy.get(j));
        creatures.get(j).setText("Current Creature View");
      }
    }
  }
}
