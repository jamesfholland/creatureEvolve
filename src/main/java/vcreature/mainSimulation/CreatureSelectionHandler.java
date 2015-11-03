package vcreature.mainSimulation;

import vcreature.genotype.Genome;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Tess Daughton on 11/2/15.
 */
public class CreatureSelectionHandler implements ActionListener
{
  private ArrayList<JButton> creatures;
  private SimAnimation animation;

  public CreatureSelectionHandler(ArrayList<JButton> creatures, SimAnimation animation)
  {
    this.creatures = creatures;
    for (JButton button : creatures)
    {
      button.addActionListener(this);
    }
    this.animation = animation;

  }

  public void actionPerformed(ActionEvent e)
  {
    for (int j = 0; j < creatures.size() - 1; j++)
    {
      String genomeName = GenePool.GENOMES.get(j).toString();
      creatures.get(j).setText(genomeName.substring(25, genomeName.length() - 1));
      if (e.getSource() == creatures.get(j))
      {
        animation.setCurrentCreature(GenePool.GENOMES.get(j));
        creatures.get(j).setText("Current Creature View");
      }
    }
  }
}
