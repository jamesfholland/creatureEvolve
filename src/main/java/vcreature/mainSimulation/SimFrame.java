package vcreature.mainSimulation;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Tess Daughton on 10/18/2015
 * JFrame containing SimpleApp
 * **/

public class SimFrame extends JFrame
{
  private SimAnimation animation;
  private JmeCanvasContext ctx;
  private JPanel threadPane;
  private JPanel appPane;
  private static final int TOP_SPEED = 10;
  private static final int LOW_SPEED = 0;
  private JSlider speed = new JSlider(JSlider.HORIZONTAL, LOW_SPEED, TOP_SPEED, 3);

  /**
   * Class Constructor:
   * Sets up the JFrame to contain the SimpleApp canvas
   */
  public SimFrame()
  {
    super();
    animation = new SimAnimation();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(1000, 800));
    setSize(new Dimension(1000, 800));
    this.addThreadPane();
    this.addAppPane();
    pack();
    setVisible(true);
  }

  /**
   * Creates a JPanel that contains the SimpleAppliation
   * Creates and starts the SimpleApplication Canvas (which is what actually gets embedded in panel)
   * Gets the context of the simple application
   */
  protected void addAppPane()
  {
    appPane = new JPanel();
    animation.createCanvas();
    animation.startCanvas();
    ctx = (JmeCanvasContext) animation.getContext();
    ctx.setSystemListener(animation);
    Dimension dim = new Dimension(1000, 725);
    ctx.getCanvas().setPreferredSize(dim);
    appPane.add(ctx.getCanvas());
    add(appPane, BorderLayout.CENTER);
  }

  /**
   * Creates a Jpanel that contains user controls
   * JButton showApp to allow the user to hide/show the creature animation
   * JComboBox threadSelector allows the user to view the creatures running on each thread
   */
  protected void addThreadPane()
  {
    threadPane = new JPanel();
    speed.setMajorTickSpacing(2);
    speed.setPaintTicks(true);
    speed.setPaintLabels(true);
    speed.addChangeListener(new ChangeListener()
    {
      @Override
      public void stateChanged(ChangeEvent e)
      {
        JSlider source = (JSlider) e.getSource();
        if (source.getValueIsAdjusting())
        {
          int fps = (int) source.getValue();
          animation.setSpeed(fps);
        }

      }
    });
    JLabel speedLabel = new JLabel(("Speed: "));
    threadPane.add(speedLabel);
    threadPane.add(speed);
    add(threadPane, BorderLayout.PAGE_END);
  }
}

