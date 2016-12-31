package inputs;

import model.Movement;
import util.FileUtil;
import util.InputConversionUtil;

import java.security.InvalidParameterException;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yann on 31/12/16.
 */
public class FileInput {

  private static List<String> lines;
  private static Scanner scanner = new Scanner(System.in);
  private static int readIndex = 0;

  //TODO init method for file location

  public static Movement receiveInput() {
    scanner.nextLine();
    if (lines == null) {
      lines = FileUtil.readFromFile("resources/history/Game1.txt");
    }

    Movement movement = null;

    if(readIndex> lines.size()) {
      throw new InvalidParameterException("File terminated");
    }

    String[] line = lines.get(readIndex).replace("-"," ").split(" ");
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
      System.out.println(line);
      movement = InputConversionUtil.toNumericValue(from,to);
      System.out.println(movement);
    }
    catch (InvalidParameterException e){
      System.err.println(e.toString());
    }

    readIndex++;
    System.out.println(movement);
    return movement;
  }
}
