package vcreature.mutator;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;
import vcreature.genotype.Genome;
import vcreature.genotype.GenomeCreature;

/**
 * Cleaner cleans genomes by removing genes that are not present in the phenome AKA recessive genes.
 */
public class Cleaner
{
  /**
   * Removes genes that are not in the phenome
   *
   * @param genome a genome
   * @return a clean genome
   */
  public static Genome cleanGenome(Genome genome)
  {
    PhysicsSpace physicsSpace = new PhysicsSpace();
    Node node = new Node();
    GenomeCreature creature = new GenomeCreature(physicsSpace, node, genome);
    return creature.getCleanGenome();
  }
}
