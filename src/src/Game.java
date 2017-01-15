import inputs.FileInput;
import inputs.InputHandler;
import model.*;
import inputs.CommandLineInput;

import java.util.List;

/**
 * Created by yann on 23/12/16.
 */
public class Game {
  private Player p1;
  private Player p2;
  private Player current;
  private InputHandler inputHandler;

  private Board board;

  public void init() {
    this.p1 = new Player("Alice", Color.WHITE);
    this.p2 = new Player("Bob",Color.BLACK);
    this.current = p1;

    board = new Board();
    board.init();
    inputHandler = new FileInput();
    //inputHandler = new CommandLineInput();
   }

  public static void main(String[] args) {

    Game game = new Game();
    game.init();
    Movement movementAsked;
    while(true) {
      System.out.println(game.toString());
      List<Movement> availableMovements = game.board.getAvailableMovements(game.current.getColor());
      System.out.println(availableMovements.size()+" movements available");

      // Get player movement
      movementAsked = game.inputHandler.receiveInput(game.current.getColor());
      Movement movement = validateInputs(availableMovements, movementAsked);

      // If the movement is not available
      while (movement == null) {
        //TODO deal with the error in the inputHandler
        System.err.println("Movement forbidden!!!");
        movementAsked = game.inputHandler.receiveInput(game.current.getColor());
        movement = validateInputs(availableMovements,movementAsked);

      }

      // Move piece
      System.out.println("Movement asked... i = "+movement);
      game.board.doMove(movement);

      // Switch player
      game.current = game.current == game.p1 ? game.p2 : game.p1;
    }
  }

  public static Movement validateInputs(List<Movement> movements, Movement movementAsked) {
      for (Movement movement : movements) {
        if(movement.equals(movementAsked)) {
          return movement;
        }
      }
    return null;
  }

  public void setPlayersNames(String name1, String name2) {
    this.p1.setName(name1);
    this.p2.setName(name2);
  }

  @Override
  public String toString() {
    return board.toString();
  }
}
