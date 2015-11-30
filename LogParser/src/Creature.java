import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by jholland on 11/11/15.
 */
public class Creature
{
  public static HashMap<String, Creature> allCreatures = new HashMap<>();
  public static HashMap<String, Creature> gpCreatures = new HashMap<>();

  public static long startTime = 0;

  public Creature parent1;
  public Creature parent2; //If this is null, it was a mutation.

  double myDiversity = 0.0;

  private String hash;

  /**
   * Minutes
   */
  public long timeCreated = Long.MIN_VALUE;
  public long timeRemoved = Long.MIN_VALUE;

  public Creature(String hash)
  {
    this.hash = hash;
    myDiversity = 1.0;
    if(!allCreatures.containsKey(hash))
    {
      allCreatures.put(hash, this);
    }

  }

  public Creature(String hash, String parent)
  {
    this.hash = hash;
    this.myDiversity = 0.05;
    this.parent1 = allCreatures.get(parent);
    this.parent2 = null;
    if(!allCreatures.containsKey(hash))
    {
      allCreatures.put(hash, this);
    }
  }

  public Creature(String hash, String parent1, String parent2)
  {
    this.hash = hash;
    this.myDiversity = 0.0;
    this.parent1 = allCreatures.get(parent1);
    this.parent2 = allCreatures.get(parent2);
    if(!allCreatures.containsKey(hash))
    {
      allCreatures.put(hash, this);
    }
  }

  public void setTimeAdded(long time)
  {
    this.timeCreated = time;
    gpCreatures.put(this.hash, this);
  }

  public void setTimeRemoved(long time)
  {
    this.timeRemoved = time;
  }

  public void addAncestors(Generation generation, double percentOfChild)
  {
    if( percentOfChild < 0.009)
    {
      if(!generation.percentages.containsKey(this))
      {
        generation.percentages.put(this, percentOfChild);

      }
      else if (generation.percentages.get(this) < percentOfChild)
      {
        generation.percentages.put(this, percentOfChild);
      }
      return;
    }


    double myPercent = myDiversity * percentOfChild;
    if(parent1 == null && parent2 == null)
    {
      if(!generation.percentages.containsKey(this))
      {
        generation.percentages.put(this, myPercent);

      }
      else if (generation.percentages.get(this) < myPercent)
      {
        generation.percentages.put(this, myPercent);

      }
    }
    else if(parent1 != null && parent2 == null)
    {
      if(!generation.percentages.containsKey(this))
      {
        generation.percentages.put(this, myPercent);

      }
      else if (generation.percentages.get(this) < myPercent)
      {
        generation.percentages.put(this, myPercent);
      }
      parent1.addAncestors(generation, 0.95*percentOfChild);
    }
    else
    {
      if(!generation.percentages.containsKey(this))
      {
        generation.percentages.put(this, myPercent);

      }
      else if (generation.percentages.get(this) < myPercent)
      {
        generation.percentages.put(this, myPercent);
      }
      parent1.addAncestors(generation, 0.5*percentOfChild);
      parent1.addAncestors(generation, 0.5*percentOfChild);

    }
  }
}
