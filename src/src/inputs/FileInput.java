package inputs;

import model.Color;
import model.Movement;
import model.RockMovement;
import model.SimpleMovement;
import util.FileUtil;
import util.InputConversionUtil;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yann on 31/12/16.
 */
public class FileInput implements InputHandler{

  private List<String> lines;
  private Scanner scanner = new Scanner(System.in);
  private int readIndex = 0;

  //TODO init method for file location

  public Movement receiveInput(Color currentPlayerColor) {
    scanner.nextLine();
    if (lines == null) {
      //lines = FileUtil.readFromFile("resources/history/Game1.txt");
      lines = FileUtil.readFromFile("resources/history/RockHistory.txt");

    }

    Movement movement = null;

    if (readIndex> lines.size()) {
      throw new InvalidParameterException("File terminated");
    }

    String lineRead = lines.get(readIndex++).replace("-"," ");

    if (lineRead.contains("O O O")) {
      return new RockMovement(true, currentPlayerColor);
    }
    else if (lineRead.contains("O O")) {
      return new RockMovement(false, currentPlayerColor);
    }

    String[] line = lineRead.split(" ");
    String from;
    String to;

    switch (line.length) {
      case 2: from = line[0];
              to = line[1];
              break;
      case 3:
              from = line[1];
              to = line[2];
              break;
      default:
        throw new InvalidParameterException("Illegal line input:"+line);
    }

    try {
      movement = InputConversionUtil.toNumericValue(from,to);
    }
    catch (InvalidParameterException e){
      System.err.println(e.toString());
    }

    System.out.println(movement);
    return movement;
  }
}
