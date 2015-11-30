import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by jholland on 11/11/15.
 */
public class Generation
{
  public HashMap<Creature, Double> percentages = new HashMap<>();

  public LinkedList<Creature> creatures = new LinkedList<>();

  public double getDiversity()
  {
    for(Creature creature : creatures)
    {
      creature.addAncestors(this, 1.0);
    }

    Double diversity = 0.0;
    for(Double percent : percentages.values())
    {
      diversity += percent;
    }

    return diversity;
  }

}
