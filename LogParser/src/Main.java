import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
      BufferedReader fileIn = null;
      try
      {
        fileIn = new BufferedReader(new FileReader(new File(args[0])));

        String header = fileIn.readLine();
        while (!Objects.equals(header, "") && header != null)
        {
          parseLine(header);
          header = fileIn.readLine();
        }
      }
      catch (IOException ignored)
      {

      }


    }

  public static void parseLine(String lineString)
  {


    try
    {
      String[] line = lineString.split(",");
      MutationType type = Enum.valueOf(MutationType.class, line[1]);
      switch (type)
      {

        case add:
          if (Double.parseDouble(line[2]) == -1)
          {
            new Creature(line[3]).setTimeAdded(Long.parseLong(line[0]));
          }
          else
          {
            Creature.allCreatures.get(line[3]).setTimeAdded(Long.parseLong(line[0]));
          }
          Generation generation = new Generation();
          for(Creature creature : Creature.gpCreatures.values())
          {
              generation.creatures.add(creature);
          }
          System.out.println(generation.getDiversity()); // +"  "+ generation.creatures.size());
          break;
        case remove:
          if(Creature.gpCreatures.remove(line[3]) == null)
          {
            //System.out.println(line[3]);
           // System.out.println("HHHHHH");
          }

          break;
        case CUTANDSPLICE:
        case SINGLECROSSOVER:
        case CHIMERA:
          new Creature(line[3], line[5], line[7]);
          break;
        case ADDER:
        case DUPLICATOR:
        case INVERTER:
        case MOVER:
        case RANDOMIZER:
        case SCALER:
        case EXTENDER:
        case SCALEBLOCK:
        case SCALEROOT:
        case SUBTRACTOR:
        case SYMMETRIZER:
        case NEURONFLIPPER:
          new Creature(line[3], line[5]);
          break;
      }
    }
    catch(Exception e)
    {
      //System.err.print(e.getLocalizedMessage());
    }
  }
}
