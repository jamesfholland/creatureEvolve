package vcreature.mutator.genetic;

import vcreature.genotype.Genome;
import vcreature.mutator.genetic.MergeTypes.*;

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
          return CHIMERA;
        }
      },
  SWAPBLOCKSANDNEURONS()
      {
        @Override
        public ArrayList<Genome> merge(Genome parent1, Genome parent2)
        {
          return SwapBlocksAndNeurons.swapBlocksAndNeurons(parent1,parent2);
        }

        @Override
        public MergeType next()
        {
          return SWAPBLOCKSANDNEURONS;
        }
      },
  SWAPLEGS()
      {
        @Override
        public ArrayList<Genome> merge(Genome parent1, Genome parent2)
        {
          return SwapLegs.swapThoseLegs(parent1,parent2);
        }

        @Override
        public MergeType next()
        {
          return SWAPLEGS;
        }
      },
  CHIMERA()
          {
            @Override
            public ArrayList<Genome> merge(Genome parent1, Genome parent2)
            {
              return Chimera.Chimera(parent1, parent2);
            }

            @Override
            public MergeType next()
            {
              return CUTANDSPLICE;
            }
          };

  public abstract ArrayList<Genome> merge(Genome parent1, Genome parent2);
  public abstract MergeType next();
}
