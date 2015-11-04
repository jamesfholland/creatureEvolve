package vcreature.mainSimulation;

import com.jme3.system.JmeCanvasContext;
import vcreature.genotype.Genome;
import vcreature.mutator.Manager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Tess Daughton on 10/18/2015
 * JFrame containing all components of GUI including:
 * User Control Panel
 * Animation for application
 * Creature selection panel to choose creatures from Gene Pool
 **/

public class SimFrame extends JFrame implements ActionListener
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
  private String[] modes = {"Genetic/Hill Climbing", "Hill Climbing", "Genetic Algorithm"};
  private JComboBox modeChange = new JComboBox<String>(modes);
  private JScrollPane creatureSelector = new JScrollPane();
  private Timer fitnessTracker;
  private DecimalFormat df = new DecimalFormat("#0.00");
  private JButton chooseFile = new JButton("Load Creature From File");
  private JPanel creatureSelectionPanel;
  private ArrayList<JButton> creatures = new ArrayList<>();
  private JButton creature;
  private JLabel creatureSelectorTitle;
  private Manager manager;


  /**
   * Class Constructor:
   * Sets up the JFrame to contain the SimpleApp canvas
   * Adds a creature selection panel to select creatures from genome
   * Adds a control panel with user controls
   * Adds the canvas from simple application
   * Starts the class timer for the ActionListener
   */
  public SimFrame(Manager manager)
  {
    super();
    this.manager = manager;
    animation = new SimAnimation(manager);
    this.addCreatureSelectionPane();
    this.addControlPane();
    this.addAppPane();
    pack();
    setVisible(true);
    fitnessTracker = new Timer(0, this);
    fitnessTracker.start();

  }

  /**
   * Creates a JScrollPane with the current list of creatures in the gene pool
   * Utilizes a CreatureSelectionHandler object for listening to button clicks and loading the relevant creature
   */
  protected void addCreatureSelectionPane()
  {
    creatureSelectionPanel = new JPanel();
    creatureSelectorTitle = new JLabel("Gene Pool Creatures");
    creatureSelectorTitle.setFont(new Font("Serif", Font.BOLD, 20));
    creatureSelectionPanel.add(creatureSelectorTitle);
    for (Genome genome : GenePool.getCopy())
    {
      creature = new JButton(genome.getFileName());
      creatures.add(creature);
      creatureSelectionPanel.add(creature);
    }
    new CreatureSelectionHandler(creatures, animation, this);
    creatureSelector = new JScrollPane(creatureSelectionPanel);
    creatureSelector.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    creatureSelector.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    setPreferredSize(new Dimension(1400, 1200));
    setSize(new Dimension(1400, 1200));
    creatureSelector.setSize(new Dimension(200, 865));
    creatureSelectionPanel.setPreferredSize(new Dimension(200, 865));
    creatureSelectionPanel.setSize(new Dimension(200, 865));
    this.add(creatureSelectionPanel);
  }

  /**
   * Creates a JPanel that contains the SimpleAppliation
   * Creates and starts the SimpleApplication Canvas (which is what actually gets embedded in panel)
   * Gets the context of the simple application
   * Takes care of the "ModeChange" selector, which toggles the Manager between different Heuristic Modes
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
    modeChange.setFont(new Font("Serif", Font.BOLD, 20));
    modeChange.setOpaque(true);
    modeChange.setBackground(Color.BLACK);
    modeChange.setForeground(Color.DARK_GRAY);
    modeChange.addActionListener(this);
    modeChange.setPreferredSize(new Dimension(350, 100));
    modeChange.setSize(new Dimension(350, 100));
    appPane.add(title);
    appPane.add(modeChange);
    appPane.add(ctx.getCanvas(), BorderLayout.CENTER);
    add(appPane, BorderLayout.CENTER);
  }

  /**
   * Creates a Jpanel that contains user controls
   * JSliders speed and zoom to allow the user to modify the app speed and the camera zoom level
   * userThreshold is the user specified threshold for hill climbing / genetic
   * chooseFile button allows user to open a JFileOpener and select a creature to load from file
   * Displays current best fitness and fitness/minute labels
   */
  private void addControlPane()
  {
    controlPane = new JPanel();
    speed.setMajorTickSpacing(4);
    speed.setPaintTicks(true);
    speed.setPaintLabels(true);
    speed.addChangeListener(new ChangeListener() //speed event handling, toggles speed inside GUI
    {
      @Override
      public void stateChanged(ChangeEvent e)
      {
        JSlider source = (JSlider) e.getSource();
        if (source.getValueIsAdjusting())
        {
          int fps = source.getValue();
          animation.setSpeed(fps);
        }
      }
    });
    zoom.setMajorTickSpacing(2);
    zoom.setPaintTicks(true);
    zoom.addChangeListener(new ChangeListener() //zoom event handling, toggles zoom inside GUI
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

    userThreshold.setEditable(true);
    userThreshold.setText("15.00");
    userThreshold.addActionListener(this);
    chooseFile.addActionListener(new OpenFile(animation,this)); //event handling for User selected file creature
    threshold = new JLabel("Fitness Threshold: ");
    zoomLabel = new JLabel("Zoom: ");
    speedLabel = new JLabel("Speed: ");
    fitnessPerMin = new JLabel(("Fitness/Min: " + df.format((manager.getFitnessPerMinute()))));
    currentBestFitness = new JLabel("Current Top Fitness: " + df.format(GenePool.getBest().getFitness()));
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
  /**
   * Updates the fitness per minute and the current best fitness labels
   * Both the userThreshold textfield and the jcombobox for mode change rely on this action listener,
   * so it checks to see which of the two has triggered the actionEvent
   * If it was the userThreshold, this value is updated
   * If it was the mode change, it switches the current selected item in the JComboBox and sets the relevant
   * MutationType inside of Manager
   */
  public void actionPerformed(ActionEvent e)
  {
    Object source = e.getSource();
    fitnessPerMin.setText("Fitness/Min: " + df.format(manager.getFitnessPerMinute()));
    currentBestFitness.setText("Current Top Fitness: " + df.format(GenePool.getBest().getFitness()));

    if (source instanceof JTextField)
    {
      JTextField tf = (JTextField) e.getSource();
      String userValue = tf.getText();
      tf.setText(userValue);
      new LoadFrame(this,1000).setVisible(true);

    }
    if (source instanceof JComboBox)
    {
      System.out.println("JComboBox");
      JComboBox cb = (JComboBox) source;
      String thread = (String) cb.getSelectedItem();
      new LoadFrame(this,2000).setVisible(true);

      if (thread.equals("Hill Climbing"))
      {
        manager.setCurrentMutationType(Manager.MutationType.HILL);
      }
      else if (thread.equals("Genetic Algorithm"))
      {
        manager.setCurrentMutationType(Manager.MutationType.GENETIC);
      }
      else if (thread.equals("Genetic Algorithm/Hill Climbing"))
      {
        manager.setCurrentMutationType(Manager.MutationType.GENETICHILL);
      }
      animation.restart();
    }
  }
}
