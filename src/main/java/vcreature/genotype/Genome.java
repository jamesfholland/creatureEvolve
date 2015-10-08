package vcreature.genotype;

/**
 * This Class contains the genome of the creature.
 * This consists of the root block and all children blocks as well as mating logic.
 */
public class Genome
{

  /*
  Root
    Size
  Block Array/List
    Parent Block (0=root  +/- number is offset in array to parent.)
    PivotParent (Percentage from center in each dimension)
    PivotSelf (Percentage from center in each dimension)
    Center Offset (Percentage of size to move to get center point. Something like offset*size/2 = center)
    Size (3d tuple)

    Parent Axis
    Self Axis

    Types (Neuron trigger [height, touch, time, constant, joint angle])
    Input values (inputs for each type if constant)

    Binary Operation AB -> X
    Unary Operation X

    Binary Operation DE -> Y
    Unary Operation Y



   */
}
