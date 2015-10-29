package vcreature.genotype;

import java.util.ArrayList;

/**
 * Gene is a helper class for manipulating
 */
public class Gene
{
  public final GeneBlock geneBlock;
  public final ArrayList<GeneNeuron> geneNeurons = new ArrayList<>();
  public final ArrayList<Gene> children = new ArrayList<>();
  public Gene(ArrayList<GeneBlock> blocks, ArrayList<GeneNeuron> neurons, int index)
  {
    this.geneBlock = blocks.get(index);
    for(int i = 0; i < neurons.size(); i++)
    {
      if(neurons.get(i).BLOCK_INDEX == index)
      {
        this.geneNeurons.add(neurons.get(i));
      }
    }
  }
}
