package vcreature.mainSimulation;

import vcreature.genotype.GenoFile;
import vcreature.genotype.Genome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Tess Daughton on 11/1/15.
 * Event Handler called on user click of Jbutton "Load Creature" inside SimAnimation instance
 * Used to allow the user to select a creature to load from file
 */
class OpenFile implements ActionListener
{
  private SimAnimation animation;
  private JFrame frame;

  /**
   * Class Constructor
   * @param animation the instance of SimAnimation the GUI is being running with
   */
  public OpenFile(SimAnimation animation, JFrame frame)
  {
    this.animation = animation;
    this.frame = frame;
  }

  /**
   * opens a JFileChooser
   * Converts user selected file into genome, which is then passed into the instance of
   * SimAnimation and loaded upon next internal call of simpleUpdate
   * @param e
   */
  public void actionPerformed(ActionEvent e)
  {
    JFileChooser fileChooser = new JFileChooser();
    int returnVal = fileChooser.showOpenDialog((Component) e.getSource());
    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
      //new LoadFrame(frame,2000);
      File file = fileChooser.getSelectedFile();
      Genome fileGenome = GenoFile.readGenome(file.getAbsolutePath());
      if(fileGenome != null) //Null means it was an invalid file.
      {
        animation.setCurrentCreature(fileGenome);
      }
    }
  }
}
