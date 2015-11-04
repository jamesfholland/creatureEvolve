package vcreature.mainSimulation;

import javax.swing.*;
import java.awt.*;


/**
 * Modified by Tess Daughton on 11/4/15.
 * Adds splash screen for user verification when creature loading
 * Author: Tony Colston
 * http://www.javaworld.com/article/2077467/core-java/java-tip-104--make-a-splash-with-swing.html
 */
public class LoadFrame extends JWindow
{
  public LoadFrame(Frame frame, int waitTime)
  {
    super(frame);
    JLabel loading = new JLabel("Loading Creature... ");
    loading.setFont(new Font ("Serif", Font.BOLD, 100));
    loading.setBackground(Color.WHITE);
    loading.setForeground(Color.DARK_GRAY);
    getContentPane().add(loading,BorderLayout.CENTER);
    pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension labelSize = new Dimension(500,500);
    setLocation((screenSize.width/2 - (labelSize.width/2)-300),
                (screenSize.height/2 - (labelSize.height/2)));
    final int pause =  waitTime;
    final Runnable closerRunner = new Runnable()
    {
      @Override
      public void run()
      {
        setVisible(false);
        dispose();
      }
    };
    Runnable waitRunner = new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          Thread.sleep(pause);
          SwingUtilities.invokeAndWait(closerRunner);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    };
    setVisible(true);
    Thread splashThread = new Thread(waitRunner,"SplashThread");
    splashThread.start();

  }
}
