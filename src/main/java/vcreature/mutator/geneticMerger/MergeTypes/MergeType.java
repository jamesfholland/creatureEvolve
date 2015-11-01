package vcreature.mutator.geneticMerger.MergeTypes;

import vcreature.genotype.Genome;

import java.util.ArrayList;

/**
 * Created by jholland on 10/31/15.
 */
public enum MergeType
{
  CUTANDSPLICE()
      {
        @Override
        public ArrayList<Genome> merge(Genome parent1, Genome parent2)
        {
          return CutAndSplice.cutAndSplice(parent1, parent2);
        }
      },
  SINGLECROSSOVER()
      {
        @Override
        public ArrayList<Genome> merge(Genome parent1, Genome parent2)
        {
          return SingleCrossover.singleCrossOver(parent1, parent2);
        }
      },
  SWAPBLOCKSANDNEURONS()
      {
        @Override
        public ArrayList<Genome> merge(Genome parent1, Genome parent2)
        {
          return null;
        }
      },
  SWAPLEGS()
      {
        @Override
        public ArrayList<Genome> merge(Genome parent1, Genome parent2)
        {
          return null;
        }
      };

  public abstract ArrayList<Genome> merge(Genome parent1, Genome parent2);
}
