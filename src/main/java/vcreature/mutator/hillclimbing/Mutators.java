package vcreature.mutator.hillclimbing;

import vcreature.mainSimulation.MainSim;
import vcreature.genotype.Genome;


/**
 * This is a caller method made with enums. This allowed us to easily
 * add methods for hill climbing relitivly easy.
 * We have the methods for hill climbing
 *  -Adder: Adds a block
 *  -Duplicator: Duplicates a block
 *  -Inverter: Inverts creature
 *  -Mover: Moves blocks
 *  -Randomizer: takes a block and Randomizes it on the creature
 *  -Scaler: Takes a creature and scales it
 *  -Extender: Takes a block and adjusts the legnth, height, or width
 *  -Scale_Block: This will take a block and make it larger, or smaller
 *  -Scale_Root: This will take the root and make it larger, or smaller
 *  -Subtractor: Takes a block and deletes it from a creature
 *  -Symmitrizer: A leg from a creature and tries to duplicate on an similar spot
 *      somewhere else on the creature
 *  -Neuron_FLipper: Flips the neurons on a creature, to make them fire oppisite
 */
public enum Mutators
{
  ADDER()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          return Adder.addBlock(parent);
        }
      },
  DUPLICATOR()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          return Duplicator.duplicateLimb(parent);
        }
      },
  INVERTER()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          return Inverter.basicInverter(parent);
        }
      },
  MOVER()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          return Mover.moveLimbs(parent);
        }
      },
  RANDOMIZER()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          return Randomizer.randomize(parent);
        }
      },
  SCALER()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          //scale value is hard-coded for now
          return Scaler.scale(parent);
        }
      },
  EXTENDER()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          return Extender.extendLimbs(parent);
        }
      },

  SCALE_BLOCK()
  {
    @Override
    public Genome mutate (Genome parent)
    {//scale value is hard-coded for now
      return ScaleSingleBlock.scaleBlock(parent);
    }
  }

  ,

  SCALE_ROOT()
  {
    @Override
    public Genome mutate (Genome parent)
    {
      return ScaleSingleBlock.scaleRoot(parent);
    }
  }

  ,

  SUBTRACTOR()
  {
    @Override
    public Genome mutate (Genome parent)
    {
      return Subtracter.subtractBlock(parent);
    }
  }

  ,

  SYMMETRIZER()
  {
    @Override
    public Genome mutate (Genome parent)
    {
      return Symmetrizer.basicSymmetrize(parent);
    }
  }

  ,

  NEURON_FLIPPER()
  {
    @Override
    public Genome mutate (Genome parent)
    {
      return Inverter.flipNeuron(parent);
    }
  }
  ;

  public abstract Genome mutate(Genome parent);
  }
