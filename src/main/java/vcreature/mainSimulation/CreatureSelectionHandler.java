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
  private ArrayList<JButton>  creatures;
  private SimAnimation animation;

  public CreatureSelectionHandler(ArrayList <JButton> creatures, SimAnimation animation)
  {
    this.creatures = creatures;
    for(JButton button: creatures)
    {
      button.addActionListener(this);
    }
    this.animation = animation;

  }

  public void actionPerformed(ActionEvent e)
  {
    for (JButton button : creatures)
    {
      if (e.getSource() == button)
      {
        for (Genome gene : GenePool.GENOMES)
          if (button.getText().equals(gene.getFileName()))
          {
            animation.setCurrentCreature(gene);
          }
      }
    }
  }
}
