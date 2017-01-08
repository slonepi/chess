package util;

/**
 * Created by yann on 30/12/16.
 */

import model.Color;
import model.Movement;
import model.RockMovement;
import model.SimpleMovement;

import java.security.InvalidParameterException;

/**
 * Convert chess movement notations into numeric values
 */
public class InputConversionUtil {

  /**
   *
   * @param from the origin coordinates, format [A-Z][1-8] (ie:E4)
   * @param to the movement coordinates, format [A-Z][1-8] (ie:E6)
   * @return
   */
  public static Movement toNumericValue(String from, String to) {
    if (from == null || to == null || from.length() != 2 || to.length() != 2) {
      throw new InvalidParameterException("Coordinates do not match the [A-Z][1-8] pattern");
    }

    int jfrom = (int) from.charAt(0) - 65;
    int ifrom = Integer.parseInt(from.substring(1,2)) - 1;
    int jto = (int) to.charAt(0) - 65;
    int ito = Integer.parseInt(to.substring(1,2)) - 1;

    if (!MovementUtil.validateCoordonates(ifrom,jfrom) ||

            !MovementUtil.validateCoordonates(ito,jto)) {
      throw  new InvalidParameterException("Coordinates do not fit on the board:"+ifrom+", "+jfrom+"-"+ito+", "+jto);
    }

    return new SimpleMovement(ifrom, jfrom,ito,jto);
  }
}
