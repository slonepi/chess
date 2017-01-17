package util;

/**
 * Created by yann on 17/01/17.
 */
public class Logger {

  public static void logInformation(String message) {
    System.out.println("#i: "+message);
  }

  public static void logError(String message) {
    System.err.println("#e: "+message);
  }
}
