package util;

import model.Color;
import model.Movement;
import model.Piece;
import model.Pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yann on 23/12/16.
 */
public class MovementUtil {
  public static List<Movement> getPieceMovement(Piece piece, Piece[][] board, int i, int j) {
    List<Movement> movements = new ArrayList<>();

    //TODO: use polymorphism or switch to enum
    if (piece instanceof Pawn) {

      if (piece.getColor() == Color.WHITE) {
        if (emptyCase(i + 1, j, board)) {
          movements.add(new Movement(i, j, i + 1, j));
        }
        if (i == 1 && emptyCase(i + 1, j, board) && emptyCase(i + 2, j, board)) {
          movements.add(new Movement(i, j, i + 2, j));
        }
      } else {
        if (emptyCase(i - 1, j, board)) {
          movements.add(new Movement(i, j, i - 1, j));
        }
        if (i == 6 && emptyCase(i - 1, j, board) && emptyCase(i - 2, j, board)) {
          movements.add(new Movement(i, j, i - 2, j));
        }
      }
    }

    return movements;
  }

  public static boolean validateCoordonates(int i, int j) {
    return i>=0 && i <8 && j>=0 && j<8;
  }

  public static boolean emptyCase(int i, int j, Piece[][] board) {
    return validateCoordonates(i,j) && board[i][j] == null;
  }
}
