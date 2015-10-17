package vcreature.mainSimulation;

import com.jme3.app.state.AppState;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Tess Daughton on 10/14/15.
 * JFrame to embed BulletApp inside of
 * Add user controls
 **/
public class SimFrame extends JFrame implements ActionListener, ItemListener
{
  private MainSim animation;
  private JmeCanvasContext ctx;
  private JPanel threadPane;
  private JPanel appPane;
  private JToggleButton showApp;
  private JComboBox threadSelector;
  private String[] threads = {"Thread 1", "Thread 2", "Thread 3", "Thread 4",
                              "Thread 5", "Thread 6", "Thread 7", "Thread 8",
                              "Thread 9", "Thread 10", "Thread 11", "Thread 12",
                              "Thread 13", "Thread 14", "Thread 15", "Thread 16"};

  public SimFrame(MainSim animation)
  {
    super("Creature Simulator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(1000, 800));
    setSize(new Dimension(1000, 800));
    this.animation=animation;
    this.addThreadPane();
    this.addAppPane();
    super.pack();
    super.setVisible(true);
  }

  protected void addAppPane()
  {
    appPane=new JPanel();
    animation.createCanvas();
    animation.startCanvas();
    ctx = (JmeCanvasContext) animation.getContext();
    ctx.setSystemListener(animation);
    Dimension dim = new Dimension(1000,725);
    ctx.getCanvas().setPreferredSize(dim);
    appPane.add(ctx.getCanvas());
    super.add(appPane, BorderLayout.CENTER);
  }

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
    super.add(threadPane, BorderLayout.PAGE_END);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    JComboBox cb = (JComboBox) e.getSource();
    String thread = (String)cb.getSelectedItem();
    //updateLabel(thread);
  }

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
      animation.stop(true);
      showApp.setText("Show Animation");
    }
  }
}
