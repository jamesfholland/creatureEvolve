package vcreature.mutator.genetic;

import vcreature.genotype.Genome;
import vcreature.mutator.genetic.MergeTypes.*;

import java.util.ArrayList;

/**
 * This enum contains the types of genetic manipulations that can occur.
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
  CHIMERA()
      {
        @Override
        public ArrayList<Genome> merge(Genome parent1, Genome parent2)
        {
          return Chimera.makeChimera(parent1, parent2);
        }

        @Override
        public MergeType next()
        {
          return CUTANDSPLICE;
        }
      };

  /**
   * This performs the action associated with the Genetic merger type.
   * @param parent1 first genome
   * @param parent2 second genome
   * @return the merged genome performed according to the mutation type.
   */
  public abstract ArrayList<Genome> merge(Genome parent1, Genome parent2);

  /**
   * Allows cyclical mutation changes.
   * @return next mutation type needed.
   */
  public abstract MergeType next();
}
