package vcreature.mainSimulation;

import vcreature.phenotype.Creature;

import java.util.ArrayList;

/**
 * Created by Tyler on 10/18/2015.
 */
public class Genepool
{
  private ArrayList<ArrayList<Creature>> population = new ArrayList<ArrayList<Creature>>();



 public float getFitness(int creatureNumber, int variationNumber)
 {
   return population.get(creatureNumber).get(variationNumber).getFitness();
 }


  public Creature getCreatureAtIndex(int creatureNumber, int variationNumber)
  {
    return population.get(creatureNumber).get(variationNumber);
  }
}
