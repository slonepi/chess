package inputs;

import model.Color;
import model.Movement;

/**
 * Created by yann on 08/01/17.
 */
public interface InputHandler {

  public Movement receiveInput(Color currentPlayerColor);
}
