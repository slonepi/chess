package util;

import model.Movement;

import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * Created by yann on 30/12/16.
 */
public class InputUtil {

  public static Movement receiveInput() {
    Movement movement = null;
    Scanner scanner = new Scanner(System.in);
    while(movement==null) {
      try {
        System.out.println("From...");
        String from = scanner.next();
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
