package model;

import java.util.List;

/**
 * Created by yann on 06/01/17.
 */
public class RockMovement extends Movement {

  private boolean isQueenSideRock;
  private Color color;

  public RockMovement(boolean queenSide, Color color) {
    isQueenSideRock = queenSide;
    this.color = color;
  }

  @Override
  public void executeMovement(Piece[][] board, List<Piece> eatenPieces) {
    int i = color == Color.BLACK ? 7 : 0;
    Piece king, rock;

    System.out.println("i="+i+","+(color==Color.BLACK));
    if (isQueenSideRock) {
      king = board[i][4];
      rock = board[i][0];

      board[i][2] = king;
      board[i][3] = rock;

      board[i][4] = null;
      board[i][0] = null;
    }
    else {
      king = board[i][4];
      rock = board[i][7];

      board[i][6] = king;
      board[i][5] = rock;

      board[i][4] = null;
      board[i][7] = null;

    }
    king.move();
    rock.move();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RockMovement that = (RockMovement) o;

    if (isQueenSideRock != that.isQueenSideRock) return false;
    return color == that.color;

  }

  @Override
  public int hashCode() {
    int result = (isQueenSideRock ? 1 : 0);
    result = 31 * result + color.hashCode();
    return result;
  }
}
