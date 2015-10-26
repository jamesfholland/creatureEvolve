package vcreature.mainSimulation;

import vcreature.genotype.Genome;
import vcreature.phenotype.Creature;

import java.util.ArrayList;

/**
 * Created by Tyler on 10/18/2015.
 */

/**
 * I am thinking that we can take this and store different types of creatures
 * in here
 * Then also store many of their mutated children.
 */
public class Genepool
{
  private ArrayList<ArrayList<Genome>> population =
      new ArrayList<ArrayList<Genome>>();


  /**
   * if(compareMultipleCreatures(creatureFirst, creatureSecond) == 1)
   * {
   *     population.add(creatureFisrt);
   *     population.removeCreatureFromPopualtion(creatureSecond));
   * }
   * else if(compareMultipleCreatures(creatureFirst, creatureSecond)<= 0)
   * {
   *
   * }
   */


  public void removeGenome(int creatureNumber)
  {
    population.remove(creatureNumber);
  }


  public Genome getGenome(int creatureNumber, int variationNumber)
  {
    return population.get(creatureNumber).get(variationNumber);
  }
  /**
   * Gets the fitness of a creature
   *
   * @param creatureNumber  Given number of the creature
   * @param variationNumber how many times has it been recreated
   * @return
   */

  /**
  public float getFitnessFromPopulation(int creatureNumber, int variationNumber)
  {
    return population.get(creatureNumber).get(variationNumber).getFitness();
  }

  public Creature getCreatureAtIndex(int creatureNumber, int variationNumber)
  {
    return population.get(creatureNumber).get(variationNumber);
  }

  public void addCreatureToPopulation(Creature creature)
  {
//    population.add(creature);
  }
*/
  public void removeCreatureFromPopulation(Creature creature)
  {
    population.remove(creature);
  }

  public int compareMultipleCreatures(Creature creatureFirst,
                                      Creature creatureSecond)
  {
    if (creatureFirst.getFitness() > creatureSecond.getFitness())
    {
      return 1;
    }
    else if (creatureFirst.getFitness() < creatureSecond.getFitness())
    {
      return -1;
    }
    else
    {
      return 0;
    }
  }
}
