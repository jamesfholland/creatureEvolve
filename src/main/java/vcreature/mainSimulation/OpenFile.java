package vcreature.mainSimulation;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by L301126 on 11/1/15.
 * Author: http://stackoverflow.com/questions/16351875/jfilechooser-on-a-button-click
 */
public class OpenFile implements ActionListener
{
  private Genome fileGenome;
  private SimAnimation animation;

  public OpenFile(SimAnimation animation)
  {
    this.animation = animation;
  }

  public void actionPerformed(ActionEvent e)
  {
    JFileChooser fileChooser = new JFileChooser();
    int returnVal = fileChooser.showOpenDialog((Component) e.getSource());
    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
      File file = fileChooser.getSelectedFile();
      fileGenome = GenoFile.readGenome(file.getAbsolutePath());
      animation.setCurrentCreature(fileGenome);
    }
  }
}
