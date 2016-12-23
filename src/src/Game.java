import model.Board;
import model.Color;
import model.Movement;
import model.Player;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yann on 23/12/16.
 */
public class Game {
  private Player p1;
  private Player p2;
  private Player current;

  private Board board;

  public void init() {
    this.p1 = new Player("Alice", Color.WHITE);
    this.p2 = new Player("Bob",Color.BLACK);
    this.current = p1;

    board = new Board();
    board.init();
   }

  public static void main(String[] args) {

    Game game = new Game();
    game.init();

    while(true) {
      System.out.println(game.toString());
      List<Movement> avaibleMovements = game.board.getAvailableMouvements(game.current.getColor());
      Movement movement = null;
      int i, j;
      do {
        Scanner scanner = new Scanner(System.in);
        //TODO read optional piece if ambiguous
        System.out.println("Enter i...");
        i = scanner.nextInt();
        System.out.println("Enter j...");
        j = scanner.nextInt();
        movement = validateInputs(avaibleMovements,i,j);

        if (movement == null) {
          System.out.println("!!! Invalid inputs !!!");
        }
      } while (movement == null);
      System.out.println("Movement asked... i = "+i+", i = "+j);
    }
  }

  public static Movement validateInputs(List<Movement> movements, int i, int j) {
    if (i<0 || i>7 || j<0 || j>7)
      return null;
    else {
      boolean foundMatch = false;
      for (Movement movement : movements) {
        if(movement.ito == i && movement.jto == j) {
          return movement;
        }
      }
      return null;
    }
  }

  @Override
  public String toString() {

    return board.toString();
  }
}
