package inputs;

import model.Color;
import model.Movement;
import model.RockMovement;
import model.SimpleMovement;
import util.InputConversionUtil;

import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * Created by yann on 30/12/16.
 */
public class CommandLineInput {

  //TODO init method for players names

  public static Movement receiveInput(Color currentPlayerColor) {
    Movement movement = null;
    Scanner scanner = new Scanner(System.in);
    while(movement==null) {
      try {
        System.out.println("From...");
        String from = scanner.next();

        if (from.contains("O-O-O")) {
          return new RockMovement(true, currentPlayerColor);
        }
        else if (from.contains("O-O")) {
          return new RockMovement(false, currentPlayerColor);
        }

        System.out.println("To...");
        String to = scanner.next();
        movement = InputConversionUtil.toNumericValue(from,to);
        System.out.println(movement);
      }
      catch (InvalidParameterException e){
        System.err.println(e.toString());
      }
    }
    return movement;
  }

}
