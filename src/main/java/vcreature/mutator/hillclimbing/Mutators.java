package vcreature.mutator.hillclimbing;

import vcreature.mainSimulation.MainSim;
import vcreature.genotype.Genome;


/**
 * Created by Tess Daughton on 10/27/15
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
  SCALE_BLOCK()
      {
        @Override
        public Genome mutate(Genome parent)
        {//scale value is hard-coded for now
          float scaler;
          return ScaleSingleBlock.scaleBlock(parent);
        }
      },
  SCALE_ROOT()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          return ScaleSingleBlock.scaleRoot(parent);
        }
      },
  SUBTRACTOR()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          return Subtracter.subtractBlock(parent);
        }
      },
  SYMMETRIZER()
      {
        @Override
        public Genome mutate(Genome parent)
        {
          return Symmetrizer.basicSymmetrize(parent);
        }
      },
    NEURON_FLIPPER()
          {
            @Override
            public Genome mutate(Genome parent)
            {
              return Inverter.flipNeuron(parent);
            }
          };

  private static Mutators currentMutator = Mutators.RANDOMIZER;

  public static void setCurrentMutator(Mutators mutator)
  {
    currentMutator = mutator;
  }

  public static Mutators getCurrentMutator()
  {
    return currentMutator;
  }
  public static Mutators getRandomMutator()
  {
    return Mutators.values()[MainSim.RANDOM.nextInt(Mutators.values().length)];
  }
  public abstract Genome mutate(Genome parent);
  }
