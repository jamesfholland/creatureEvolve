package vcreature.mainSimulation;

import com.jme3.system.JmeCanvasContext;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import vcreature.genotype.Genome;
import vcreature.mutator.Manager;

/**
 * Created by Tess Daughton on 10/18/2015
 * JFrame containing SimpleApp
 * **/

public class SimFrame extends JFrame implements ActionListener, MouseListener
{
  private SimAnimation animation;
  private JmeCanvasContext ctx;
  private JPanel controlPane;

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
  private JPanel creatureSelectionPanel;
  private ArrayList<JButton> creatures = new ArrayList<>();
  private JButton creature;

  private JLabel creatureSelectorTitle;



  /**
   * Class Constructor:
   * Sets up the JFrame to contain the SimpleApp canvas
   */
  public SimFrame()
  {
    super();
    animation = new SimAnimation();

    this.addCreatureSelectionPane();
    this.addControlPane();
    this.addAppPane();
    pack();
    setVisible(true);
    fitnessTracker = new Timer(0, this);
    fitnessTracker.start();

  }

  protected void addCreatureSelectionPane()
  {
    creatureSelectionPanel = new JPanel();
    creatureSelectorTitle = new JLabel("Gene Pool Creatures");
    creatureSelectorTitle.setFont(new Font("Serif", Font.BOLD, 20));
    creatureSelectionPanel.add(creatureSelectorTitle);
    for (Genome genome : GenePool.GENOMES)
    {
      creature = new JButton(genome.getFileName());
      creatures.add(creature);
      creatureSelectionPanel.add(creature);
    }
    new CreatureSelectionHandler(creatures, animation);
    creatureSelector = new JScrollPane(creatureSelectionPanel);
    creatureSelector.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    creatureSelector.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(1400, 1200));
    setSize(new Dimension(1400, 1200));
    creatureSelectionPanel.setPreferredSize(new Dimension(200, 865));
    creatureSelectionPanel.setSize(new Dimension(200, 865));
    this.add(creatureSelectionPanel);
  }

  /**
   * Creates a JPanel that contains the SimpleAppliation
   * Creates and starts the SimpleApplication Canvas (which is what actually gets embedded in panel)
   * Gets the context of the simple application
   */
  protected void addAppPane()
  {
    appPane = new JPanel();
    JLabel title = new JLabel("Creature Evolve");
    title.setFont(new Font("Serif", Font.BOLD, 90));
    title.setForeground(Color.white);
    title.setBackground(Color.black);
    animation.createCanvas();
    animation.startCanvas();
    ctx = (JmeCanvasContext) animation.getContext();
    ctx.setSystemListener(animation);
    ctx.getCanvas().setPreferredSize(new Dimension(1000, 735));
    appPane.setPreferredSize(new Dimension(1200, 800));
    appPane.setSize(new Dimension(1200, 800));
    appPane.setBackground(Color.BLACK);
    modeChange.setFont(new Font("Serif", Font.BOLD, 25));
    modeChange.setOpaque(true);
    modeChange.setBackground(Color.BLACK);
    modeChange.setForeground(Color.DARK_GRAY);
    modeChange.addMouseListener(this);
    modeChange.setPreferredSize(new Dimension(350, 50));
    modeChange.setSize(new Dimension(350, 50));
    appPane.add(title);
    appPane.add(modeChange);
    appPane.add(ctx.getCanvas(),BorderLayout.CENTER);
    add(appPane, BorderLayout.CENTER);
  }

  /**
   * Creates a Jpanel that contains user controls
   * JButton showApp to allow the user to hide/show the creature animation
   * JComboBox threadSelector allows the user to view the creatures running on each thread
   */
  private void addControlPane()
  {
    controlPane = new JPanel();
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
    controlPane.add(threshold);
    controlPane.add(userThreshold);
    controlPane.add(zoomLabel);
    controlPane.add(zoom);
    controlPane.add(speedLabel);
    controlPane.add(speed);
    controlPane.add(fitnessPerMin);
    controlPane.add(currentBestFitness);
    controlPane.add(chooseFile);
    add(controlPane, BorderLayout.PAGE_END);
  }


  @Override
  public void actionPerformed(ActionEvent e)
  {
    fitnessPerMin.setText("Fitness/Min: " + df.format(animation.getCurrentFitness()));
    currentBestFitness.setText("Current Top Fitness: " + df.format(GenePool.getBest().getFitness()));

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

