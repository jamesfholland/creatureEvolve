package vcreature.mainSimulation;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Tess Daughton on 10/18/2015
 * JFrame containing SimpleApp
 * **/

public class SimFrame extends JFrame implements ItemListener, ActionListener
{
  private SimAnimation animation;
  private JmeCanvasContext ctx;
  private JPanel threadPane;
  private JPanel appPane;
  private JToggleButton showApp;
  private JComboBox threadSelector;
  private String[] threads = {"Thread 1", "Thread 2", "Thread 3", "Thread 4",
      "Thread 5", "Thread 6", "Thread 7", "Thread 8",
      "Thread 9", "Thread 10", "Thread 11", "Thread 12",
      "Thread 13", "Thread 14", "Thread 15", "Thread 16"};

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
    ctx= (JmeCanvasContext) animation.getContext();
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
    showApp = new JToggleButton("Hide Animation");
    threadSelector = new JComboBox(threads);
    threadSelector.setSelectedIndex(0);
    threadSelector.addActionListener(this);
    showApp.addItemListener(this);
    threadPane.setPreferredSize(new Dimension(1000, 50));
    threadPane.setSize(new Dimension(1000, 50));
    threadPane.add(threadSelector, BorderLayout.CENTER);
    threadPane.add(showApp, BorderLayout.CENTER);
    add(threadPane, BorderLayout.PAGE_END);
  }

  /**
   * updates the JComboBox threadSelector to show that current thread user has selected
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    JComboBox cb = (JComboBox) e.getSource();
    String thread = (String) cb.getSelectedItem();
  }

  /**
   * Used to toggle the text on the showApp button depending on whether the user wants to
   * show the animation or hide the animation
   * @param e
   */
  @Override
  public void itemStateChanged(ItemEvent e)
  {

    if (showApp.getText().equals(("Show Animation")))
    {
      addAppPane();
      showApp.setText("Hide Animation");
    }
    else
    {
      showApp.setText("Show Animation");
    }
  }
}

