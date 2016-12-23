import model.Board;
import model.Player;

/**
 * Created by yann on 23/12/16.
 */
public class Game {
  private Player p1;
  private Player p2;
  private Player current;

  private Board board;

  public void init() {
    this.p1 = new Player("Alice");
    this.p2 = new Player("Bob");
    this.current = p1;

    board = new Board();
    board.init();
   }
  public static void main(String[] args) {

    Game game = new Game();
    game.init();

    System.out.println(game.toString());
  }

  @Override
  public String toString() {

    return board.toString();
  }
}
