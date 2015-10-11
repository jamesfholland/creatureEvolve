package vcreature.mainSimulation;

import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Created by Dayloki on 10/8/2015.
 */
public class SpawnCreature extends Creature
{
  public SpawnCreature(PhysicsSpace physicsSpace, Node jMonkeyRootNode, String creatureName)
  {
    super(physicsSpace, jMonkeyRootNode);
  if(creatureName.equals("TableMonster2Legs")) makeTableMonster();
  else if(creatureName.equals("TableMonster4Legs")) makeFourLeggedTableMonster();
  else if(creatureName.equals("TableMonster4LegsFlat")) makeFourLeggedTableMonsterFlat();
  else if(creatureName.equals("Squid")) makeSquid();
  else if(creatureName.equals("FlappyBird")) makeFlappyBird();

  }
// 27.406
  private void makeFourLeggedTableMonsterFlat()
  {
    Vector3f torsoCenter = new Vector3f( 0.0f, .5f, 0.0f);     Vector3f torsoSize = new Vector3f(5.0f, .5f, 5f);
    Vector3f leg1Center  = new Vector3f( -7.5f, .5f, 0f);     Vector3f leg1Size  = new Vector3f( 2.250f, 0.5f, 0.5f);
    Vector3f leg2Center  = new Vector3f( 7.5f, .5f, 0f);     Vector3f leg2Size  = new Vector3f(2.250f, 0.5f, .5f);
    Vector3f leg3Center  = new Vector3f( -0f, .5f, -7.50f);     Vector3f leg3Size  = new Vector3f( .50f, 0.5f, 2.250f);
    Vector3f leg4Center  = new Vector3f( 0f, .5f, 7.50f);     Vector3f leg4Size  = new Vector3f(.50f, 0.5f, 2.250f);
    Block torso=addRoot(torsoCenter,torsoSize);

    Vector3f pivotA = new Vector3f( 5f, -.5f,  0f); //Center of hinge in the block's coordinates
    Vector3f pivotB = new Vector3f(-2.250f,  -0.5f,  .0f); //Center of hinge in the block's coordinates

    Block leg1  = addBlock(leg1Center, leg1Size,torso, pivotA,  pivotB, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    Vector3f pivotC = new Vector3f( -5f, -.5f,  0f); //Center of hinge in the block's coordinates
    Vector3f pivotD = new Vector3f(2.250f,  -0.5f,  .0f); //Center of hinge in the block's coordinates

    Block leg2  = addBlock(leg2Center, leg2Size,torso, pivotC,  pivotD, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    Vector3f pivotE = new Vector3f( 0f, -.5f,  -5f); //Center of hinge in the block's coordinates
    Vector3f pivotF = new Vector3f(0f,  -0.5f,2.250f); //Center of hinge in the block's coordinates

    Block leg3  = addBlock(leg3Center, leg3Size,torso, pivotE,  pivotF, Vector3f.UNIT_X, Vector3f.UNIT_X);

    Vector3f pivotG = new Vector3f( 0f, -.5f,  5f); //Center of hinge in the block's coordinates
    Vector3f pivotH = new Vector3f(0f,  -0.5f,-2.250f); //Center of hinge in the block's coordinates

    Block leg4  = addBlock(leg4Center, leg4Size,torso, pivotG,  pivotH, Vector3f.UNIT_X, Vector3f.UNIT_X);


    Neuron positveMax = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    positveMax.setInputValue(Neuron.C, 5);
    positveMax.setInputValue(Neuron.D,Float.MAX_VALUE);
    leg1.addNeuron(positveMax);
    leg3.addNeuron(positveMax);


    Neuron negativeMax = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    negativeMax.setInputValue(Neuron.C,5);
    negativeMax.setInputValue(Neuron.D,-Float.MAX_VALUE);
    leg2.addNeuron(negativeMax);
    leg4.addNeuron(negativeMax);

  }



  private void makeFourLeggedTableMonster()
  {
    Vector3f torsoCenter = new Vector3f( 0.0f, 1.5f, 0.0f);     Vector3f torsoSize = new Vector3f(5.0f, .5f, 5f);
    Vector3f leg1Center  = new Vector3f( -2.5f, .5f, -.50f);     Vector3f leg1Size  = new Vector3f( 2.250f, 0.5f, 0.5f);
    Vector3f leg2Center  = new Vector3f( 2.5f, .5f, .50f);     Vector3f leg2Size  = new Vector3f(2.250f, 0.5f, .5f);
    Vector3f leg3Center  = new Vector3f( -.50f, .5f, -2.50f);     Vector3f leg3Size  = new Vector3f( .50f, 0.5f, 2.250f);
    Vector3f leg4Center  = new Vector3f( 0.5f, .5f, 2.50f);     Vector3f leg4Size  = new Vector3f(.50f, 0.5f, 2.250f);
    Block torso=addRoot(torsoCenter,torsoSize);

    Vector3f pivotA = new Vector3f( 5f, -.5f,  0f); //Center of hinge in the block's coordinates
    Vector3f pivotB = new Vector3f(2.250f,  0.5f,  .0f); //Center of hinge in the block's coordinates

    Block leg1  = addBlock(leg1Center, leg1Size,torso, pivotA,  pivotB, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    Vector3f pivotC = new Vector3f( -5f, -.5f,  0f); //Center of hinge in the block's coordinates
    Vector3f pivotD = new Vector3f(-2.250f,  0.5f,  .0f); //Center of hinge in the block's coordinates

    Block leg2  = addBlock(leg2Center, leg2Size,torso, pivotC,  pivotD, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    Vector3f pivotE = new Vector3f( 0f, -.5f,  -5f); //Center of hinge in the block's coordinates
    Vector3f pivotF = new Vector3f(0f,  0.5f,-2.250f); //Center of hinge in the block's coordinates

    Block leg3  = addBlock(leg3Center, leg3Size,torso, pivotE,  pivotF, Vector3f.UNIT_X, Vector3f.UNIT_X);

    Vector3f pivotG = new Vector3f( 0f, -.5f,  5f); //Center of hinge in the block's coordinates
    Vector3f pivotH = new Vector3f(0f,  0.5f,2.250f); //Center of hinge in the block's coordinates

    Block leg4  = addBlock(leg4Center, leg4Size,torso, pivotG,  pivotH, Vector3f.UNIT_X, Vector3f.UNIT_X);


    Neuron positveMax = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    positveMax.setInputValue(Neuron.C, 5);
    positveMax.setInputValue(Neuron.D,Float.MAX_VALUE);
    leg2.addNeuron(positveMax);
    leg4.addNeuron(positveMax);


    Neuron negativeMax = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    negativeMax.setInputValue(Neuron.C,5);
    negativeMax.setInputValue(Neuron.D,-Float.MAX_VALUE);
    leg1.addNeuron(negativeMax);
    leg3.addNeuron(negativeMax);

  }
  private void makeTableMonster()
  {

    Vector3f torsoCenter = new Vector3f( 0.0f, 1.5f, 0.0f);     Vector3f torsoSize = new Vector3f(5.0f, .5f, 5f);
    Vector3f leg1Center  = new Vector3f( -0f, .5f, .50f);     Vector3f leg1Size  = new Vector3f( 3.0f, 0.5f, 0.5f);
    Vector3f leg2Center  = new Vector3f( 0f, .5f, -.50f);     Vector3f leg2Size  = new Vector3f(3.0f, 0.5f, 0.5f);
    //creature =new Creature(physicsSpace,rootNode,torsoCenter,torsoSize);
    //torso =creature.getBlockByID(0);
    Block torso=addRoot(torsoCenter,torsoSize);
    Vector3f pivotA = new Vector3f( 5f, -.5f,  0f); //Center of hinge in the block's coordinates
    Vector3f pivotB = new Vector3f(3.0f,  0.5f,  .5f); //Center of hinge in the block's coordinates

    Block leg1  = addBlock(leg1Center, leg1Size,torso, pivotA,  pivotB, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    Vector3f pivotC = new Vector3f( -5f, -.5f,  0f); //Center of hinge in the block's coordinates
    Vector3f pivotD = new Vector3f(-3.f,  .5f,  -0.5f); //Center of hinge in the block's coordinates

    Block leg2  =addBlock(leg2Center, leg2Size,torso, pivotC,  pivotD, Vector3f.UNIT_Z, Vector3f.UNIT_Z);


    BoundingBox box = (BoundingBox) torso.getGeometry().getWorldBound();
    // print("simpleInitApp(): torso.size=",box.getExtent(tmpVec3));

    Neuron leg1Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    leg1Neuron1.setInputValue(Neuron.C,6);
    leg1Neuron1.setInputValue(Neuron.D,Float.MAX_VALUE);

    Neuron leg1Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    leg1Neuron2.setInputValue(Neuron.C,5);
    leg1Neuron2.setInputValue(Neuron.D, -Float.MAX_VALUE);

    //leg1.addNeuron(leg1Neuron1);
    leg1.addNeuron(leg1Neuron2);

    Neuron leg2Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    leg2Neuron1.setInputValue(Neuron.C,6);
    leg2Neuron1.setInputValue(Neuron.D,-Float.MAX_VALUE);

    Neuron leg2Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    leg2Neuron2.setInputValue(Neuron.C,5);
    leg2Neuron2.setInputValue(Neuron.D, Float.MAX_VALUE);

    //leg2.addNeuron(leg2Neuron1);
    leg2.addNeuron(leg2Neuron2);
  }



  public void makeSquid()
  {
    Vector3f torsoCenter = new Vector3f( 0.0f, 1.0f, 0.0f);     Vector3f torsoSize = new Vector3f( 1.5f, .5f, 1.5f);
    Vector3f leg1Center  = new Vector3f( 4.0f, 0.5f, 0.0f);     Vector3f leg1Size  = new Vector3f( 3.5f, 0.5f, 1.5f);
    Vector3f leg2Center  = new Vector3f(-4.0f, 0.5f, 0.0f);     Vector3f leg2Size  = new Vector3f( 3.5f, 0.5f, 1.5f);

    Vector3f leg1CenterCon  = new Vector3f( 7.0f, 4.5f, 0.0f);     Vector3f leg1SizeCon  = new Vector3f( .5f, 3.5f, 1.5f);
    Vector3f leg2CenterCon  = new Vector3f(-7.0f, 4.5f, 0.0f);     Vector3f leg2SizeCon  = new Vector3f( .5f, 3.5f, 1.5f);


    Block torso = addRoot(torsoCenter, torsoSize);

    //addBlock(Vector3f center, Vector3f size, Block parent, Vector3f pivotA, Vector3f pivotB, Vector3f axisA, Vector3f axisB)

    Vector3f pivotA = new Vector3f( 1.5f, -.5f,  0.0f); //Center of hinge in the block's coordinates
    Vector3f pivotB = new Vector3f(-3.5f,  0.5f,  0.0f); //Center of hinge in the block's coordinates


    Block leg1  = addBlock(leg1Center, leg1Size, torso, pivotA, pivotB,
        Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    Vector3f pivotC = new Vector3f(-1.5f, -.5f,  0.0f); //Center of hinge in the block's coordinates
    Vector3f pivotD = new Vector3f( 3.5f,  0.5f,  0.0f); //Center of hinge in the block's coordinates

    Block leg2  = addBlock(leg2Center, leg2Size,torso, pivotC,  pivotD, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    Vector3f pivotE = new Vector3f(3.5f, 0.5f,  0.0f);
    Vector3f pivotF = new Vector3f(-.5f, -3.5f,  0.0f);

    Block leg3 = addBlock(leg1CenterCon, leg1SizeCon, leg1, pivotE, pivotF, Vector3f.UNIT_Z, Vector3f.UNIT_Z);


    Vector3f pivotG = new Vector3f(-3.5f, 0.5f,  0.0f);
    Vector3f pivotH = new Vector3f(.5f, -3.5f,  0.0f);

    Block leg4 = addBlock(leg2CenterCon, leg2SizeCon, leg2, pivotG, pivotH, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    torso.setMaterial(Block.MATERIAL_GREEN);
    leg1.setMaterial(Block.MATERIAL_RED);
    leg2.setMaterial(Block.MATERIAL_BLUE);

    BoundingBox box = (BoundingBox) torso.getGeometry().getWorldBound();



    Neuron leg1Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg1Neuron1.setInputValue(Neuron.C,11);
    leg1Neuron1.setInputValue(Neuron.D,-Float.MAX_VALUE);

    Neuron leg1Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg1Neuron2.setInputValue(Neuron.C, 10);
    leg1Neuron2.setInputValue(Neuron.D, Float.MAX_VALUE);

    leg1.addNeuron(leg1Neuron1);
    leg1.addNeuron(leg1Neuron2);


    Neuron leg2Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg2Neuron1.setInputValue(Neuron.C,11);
    leg2Neuron1.setInputValue(Neuron.D,Float.MAX_VALUE);

    Neuron leg2Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg2Neuron2.setInputValue(Neuron.C, 10);
    leg2Neuron2.setInputValue(Neuron.D, -Float.MAX_VALUE);

    leg2.addNeuron(leg2Neuron1);
    leg2.addNeuron(leg2Neuron2);


    Neuron leg3Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg3Neuron1.setInputValue(Neuron.C,13);
    leg3Neuron1.setInputValue(Neuron.D,-Float.MAX_VALUE);

    Neuron leg3Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg3Neuron2.setInputValue(Neuron.C,10);
    leg3Neuron2.setInputValue(Neuron.D, Float.MAX_VALUE);

    leg3.addNeuron(leg3Neuron1);
    leg3.addNeuron(leg3Neuron2);

    Neuron leg4Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg4Neuron1.setInputValue(Neuron.C,13);
    leg4Neuron1.setInputValue(Neuron.D,Float.MAX_VALUE);

    Neuron leg4Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
        EnumNeuronInput.CONSTANT, null);

    leg4Neuron2.setInputValue(Neuron.C,10);
    leg4Neuron2.setInputValue(Neuron.D, -Float.MAX_VALUE);

    leg4.addNeuron(leg4Neuron1);
    leg4.addNeuron(leg2Neuron2);
  }


  public void makeFlappyBird()
  {
    Vector3f torsoCenter = new Vector3f( 0.0f, 2.5f, 0.0f);     Vector3f torsoSize = new Vector3f( 2.0f, 1.5f, 1.5f);
    Vector3f leg1Center  = new Vector3f( 5.0f, 0.5f, 0.0f);     Vector3f leg1Size  = new Vector3f( 3.0f, 0.5f, 1.0f);
    Vector3f leg2Center  = new Vector3f(-5.0f, 0.5f, 0.0f);     Vector3f leg2Size  = new Vector3f( 3.0f, 0.5f, 1.0f);

    Block torso = addRoot(torsoCenter, torsoSize);

    //addBlock(Vector3f center, Vector3f size, Block parent, Vector3f pivotA, Vector3f pivotB, Vector3f axisA, Vector3f axisB)

    Vector3f pivotA = new Vector3f( 2.0f, -1.5f,  0.0f); //Center of hinge in the block's coordinates
    Vector3f pivotB = new Vector3f(-3.0f,  0.5f,  0.0f); //Center of hinge in the block's coordinates


    Block leg1  = addBlock(leg1Center, leg1Size,torso, pivotA,  pivotB, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    Vector3f pivotC = new Vector3f(-2.0f, -1.5f,  0.0f); //Center of hinge in the block's coordinates
    Vector3f pivotD = new Vector3f( 3.0f,  0.5f,  0.0f); //Center of hinge in the block's coordinates

    Block leg2  = addBlock(leg2Center, leg2Size,torso, pivotC,  pivotD, Vector3f.UNIT_Z, Vector3f.UNIT_Z);

    torso.setMaterial(Block.MATERIAL_GREEN);
    leg1.setMaterial(Block.MATERIAL_RED);
    leg2.setMaterial(Block.MATERIAL_BLUE);

    BoundingBox box = (BoundingBox) torso.getGeometry().getWorldBound();

    Neuron leg1Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    leg1Neuron1.setInputValue(Neuron.C,11);
    leg1Neuron1.setInputValue(Neuron.D,-Float.MAX_VALUE);

    Neuron leg1Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    leg1Neuron2.setInputValue(Neuron.C,10);
    leg1Neuron2.setInputValue(Neuron.D,Float.MAX_VALUE);

    leg1.addNeuron(leg1Neuron1);
    leg1.addNeuron(leg1Neuron2);


    Neuron leg2Neuron1 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    leg2Neuron1.setInputValue(Neuron.C,11);
    leg2Neuron1.setInputValue(Neuron.D,Float.MAX_VALUE);

    Neuron leg2Neuron2 = new Neuron(EnumNeuronInput.TIME, null, EnumNeuronInput.CONSTANT,
            EnumNeuronInput.CONSTANT, null);

    leg2Neuron2.setInputValue(Neuron.C,10);
    leg2Neuron2.setInputValue(Neuron.D,-Float.MAX_VALUE);

    leg2.addNeuron(leg2Neuron1);
    leg2.addNeuron(leg2Neuron2);
  }
}
