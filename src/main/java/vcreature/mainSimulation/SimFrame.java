package vcreature.mainSimulation;

import com.jme3.system.JmeCanvasContext;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import vcreature.mutator.Manager;

/**
 * Created by Tess Daughton on 10/18/2015
 * JFrame containing SimpleApp
 * **/

public class SimFrame extends JFrame implements ActionListener, MouseListener
{
  private SimAnimation animation;
  private JmeCanvasContext ctx;
  private JPanel hillClimbPane;

  private JPanel appPane;
  private static final int TOP_SPEED = 25;
  private static final int LOW_SPEED = 0;
  private static final int TOP_ZOOM = 250;
  private static final int LOW_ZOOM = 25;
  private JSlider speed = new JSlider(JSlider.HORIZONTAL, LOW_SPEED, TOP_SPEED, 4);
  private JSlider zoom = new JSlider(JSlider.HORIZONTAL, LOW_ZOOM, TOP_ZOOM, LOW_ZOOM);
  private JLabel zoomLabel;
  private JLabel speedLabel;
  private JLabel fitnessPerMin;
  private JLabel currentBestFitness;
  private JLabel threshold;
  private JTextField userThreshold = new JTextField();
  private JButton modeChange = new JButton("Switch to Genetic Algorithm");
  private JScrollPane creatureSelector = new JScrollPane();
  private Timer fitnessTracker;
  private DecimalFormat df = new DecimalFormat("#0.00");
  private JButton chooseFile = new JButton("Load Creature From File");

  /**
   * Class Constructor:
   * Sets up the JFrame to contain the SimpleApp canvas
   */
  public SimFrame()
  {
    super();
    animation = new SimAnimation();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(1200, 1200));
    setSize(new Dimension(1200, 1200));
    this.addHillClimbPane();
    this.addAppPane();
    pack();
    setVisible(true);
    fitnessTracker = new Timer(0, this);
    fitnessTracker.start();

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
    ctx.getCanvas().setPreferredSize(new Dimension(1000, 700));
    appPane.setPreferredSize(new Dimension(1200, 800));
    appPane.setSize(new Dimension(1200, 800));
    appPane.setBackground(Color.BLACK);
    modeChange.setFont(new Font("Serif", Font.BOLD, 40));
    modeChange.setOpaque(true);
    modeChange.setBackground(Color.lightGray);
    modeChange.addMouseListener(this);
    modeChange.setPreferredSize(new Dimension(1200, 50));
    modeChange.setSize(new Dimension(50, 800));
    creatureSelector.setPreferredSize(new Dimension(10, 700));
    creatureSelector.setSize(new Dimension(50, 800));
//    appPane.add(creatureSelector);
    appPane.add(modeChange);
//
    appPane.add(ctx.getCanvas(),BorderLayout.CENTER);
    add(appPane, BorderLayout.CENTER);
  }

  /**
   * Creates a Jpanel that contains user controls
   * JButton showApp to allow the user to hide/show the creature animation
   * JComboBox threadSelector allows the user to view the creatures running on each thread
   */
  private void addHillClimbPane()
  {
    hillClimbPane = new JPanel();
    speed.setMajorTickSpacing(4);
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
    zoom.setMajorTickSpacing(2);
    zoom.setPaintTicks(true);
    zoom.addChangeListener(new ChangeListener()
    {
      @Override
      public void stateChanged(ChangeEvent e)
      {
        JSlider source = (JSlider) e.getSource();
        if (source.getValueIsAdjusting())
        {
          int fps = source.getValue();
          animation.setZoom(fps);
        }
      }
    });
    chooseFile.addItemListener(new ItemListener()
    {
      @Override
      public void itemStateChanged(ItemEvent e)
      {

      }
    });
    userThreshold.setEditable(true);
    userThreshold.setText("15.00");
    userThreshold.addActionListener(this);
    chooseFile.addActionListener(new OpenFile(animation));
    threshold = new JLabel("Fitness Threshold: ");
    zoomLabel = new JLabel("Zoom: ");
    speedLabel = new JLabel("Speed: ");
    fitnessPerMin = new JLabel(("Fitness/Min: " + df.format(animation.getCurrentFitness())));
    currentBestFitness = new JLabel("Current Top Fitness: " + df.format(animation.getCurrentFitness()));
    hillClimbPane.add(threshold);
    hillClimbPane.add(userThreshold);
    hillClimbPane.add(zoomLabel);
    hillClimbPane.add(zoom);
    hillClimbPane.add(speedLabel);
    hillClimbPane.add(speed);
    hillClimbPane.add(fitnessPerMin);
    hillClimbPane.add(currentBestFitness);
    hillClimbPane.add(chooseFile);
    add(hillClimbPane, BorderLayout.PAGE_END);
  }


  @Override
  public void actionPerformed(ActionEvent e)
  {
    fitnessPerMin.setText("Fitness/Min: " + df.format(animation.getCurrentFitness()));
    //currentBestFitness.setText("Current Top Fitness: " + df.format(GenePool.getBest().toString()));

    if (e.getSource() instanceof JTextField)
    {
      JTextField tf = (JTextField) e.getSource();
      String userValue = tf.getText();
      tf.setText(userValue);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(modeChange.getText().equals("Switch to Genetic Algorithm"))
    {
      MainSim.MUTATION_TESTER.setCurrentMutationType(Manager.MutationType.GENETIC);
      modeChange.setText("Switch to Hill Climbing");
      animation.restart();

    }
    else
    {
      modeChange.setText("Switch to Genetic Algorithm");
      MainSim.MUTATION_TESTER.setCurrentMutationType(Manager.MutationType.HILL);
      animation.restart();
    }
  }

  @Override
  public void mousePressed(MouseEvent e)
  {}

  @Override
  public void mouseReleased(MouseEvent e)
  {}

  @Override
  public void mouseEntered(MouseEvent e)
  {}

  @Override
  public void mouseExited(MouseEvent e)
  {}
}

