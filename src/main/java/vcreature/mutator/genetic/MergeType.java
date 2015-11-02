package vcreature.mutator.genetic;

import vcreature.genotype.Genome;
import vcreature.mutator.genetic.MergeTypes.CutAndSplice;
import vcreature.mutator.genetic.MergeTypes.SingleCrossover;

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

        @Override
        public MergeType next()
        {
          return SINGLECROSSOVER;
        }
      },
  SINGLECROSSOVER()
      {
        @Override
        public ArrayList<Genome> merge(Genome parent1, Genome parent2)
        {
          return SingleCrossover.singleCrossOver(parent1, parent2);
        }

        @Override
        public MergeType next()
        {
          return CUTANDSPLICE;
        }
      },
  SWAPBLOCKSANDNEURONS()
      {
        @Override
        public ArrayList<Genome> merge(Genome parent1, Genome parent2)
        {
          return null;
        }

        @Override
        public MergeType next()
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

        @Override
        public MergeType next()
        {
          return null;
        }
      };

  public abstract ArrayList<Genome> merge(Genome parent1, Genome parent2);
  public abstract MergeType next();
}
