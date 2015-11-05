package vcreature.genotype;

import java.util.ArrayList;

/**
 * Gene is a helper class for manipulating
 */
public class Gene
{
  /**
   * The block corrospnding
   */
  public final GeneBlock geneBlock;
  /**
   * list of corrosping geneneu
   */
  public final ArrayList<GeneNeuron> geneNeurons = new ArrayList<>();
  /**
   * list of corrsponding children to the gene
   */
  public final ArrayList<Gene> children = new ArrayList<>();

  /**
   * Creates a new gene for us to use to make a creature
   *
   * @param blocks  an arraylist of blocks
   * @param neurons an arraylist of neurons
   * @param index   an index of a block
   */
  public Gene(ArrayList<GeneBlock> blocks, ArrayList<GeneNeuron> neurons, int index)
  {
    this.geneBlock = blocks.get(index);
    for (int i = 0; i < neurons.size(); i++)
    {
      if (neurons.get(i).BLOCK_INDEX == index)
      {
        this.geneNeurons.add(neurons.get(i));
      }
    }
  }
}
