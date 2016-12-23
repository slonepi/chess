package util;

import model.Color;
import model.Movement;
import model.Piece;
import model.Pieces.King;
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
        if (validateCoordonates(i+1,j+1) && board[i+1][j+1] != null && board[i+1][j+1].getColor() != piece.getColor()) {
          movements.add(new Movement(i,j,i+1, j+1));
        }
        if (validateCoordonates(i+1,j-1) && board[i+1][j-1] != null && board[i+1][j-1].getColor() != piece.getColor()) {
          movements.add(new Movement(i,j,i+1, j-1));
        }
      } else {
        if (emptyCase(i - 1, j, board)) {
          movements.add(new Movement(i, j, i - 1, j));
        }
        if (i == 6 && emptyCase(i - 1, j, board) && emptyCase(i - 2, j, board)) {
          movements.add(new Movement(i, j, i - 2, j));
        }
        if (validateCoordonates(i-1,j-1) && board[i-1][j-1] != null && board[i-1][j-1].getColor() != piece.getColor()) {
          movements.add(new Movement(i,j,i-1, j-1));
        }
        if (validateCoordonates(i-1,j+1) && board[i-1][j+1] != null && board[i-1][j+1].getColor() != piece.getColor()) {
          movements.add(new Movement(i,j,i-1, j+1));
        }
      }
    }

    // TODO: manage rock
    else if (piece instanceof King) {
      if (emptyOrForeignCase(i-1,j-1,board,piece.getColor())) {
        movements.add(new Movement(i,j,i-1,j-1));
      }
      if (emptyOrForeignCase(i-1,j,board,piece.getColor())) {
        movements.add(new Movement(i,j,i-1,j));
      }
      if (emptyOrForeignCase(i-1,j+1,board,piece.getColor())) {
        movements.add(new Movement(i,j,i-1,j+1));
      }
      if (emptyOrForeignCase(i,j-1,board,piece.getColor())) {
        movements.add(new Movement(i,j,i,j-1));
      }
      if (emptyOrForeignCase(i,j+1,board,piece.getColor())) {
        movements.add(new Movement(i,j,i,j+1));
      }
      if (emptyOrForeignCase(i+1,j-1,board,piece.getColor())) {
        movements.add(new Movement(i,j,i+1,j-1));
      }
      if (emptyOrForeignCase(i+1,j,board,piece.getColor())) {
        movements.add(new Movement(i,j,i+1,j));
      }
      if (emptyOrForeignCase(i+1,j+1,board,piece.getColor())) {
        movements.add(new Movement(i,j,i+1,j+1));
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

  public static boolean emptyOrForeignCase(int i, int j, Piece[][] board, Color myColor) {
    return validateCoordonates(i,j) && (board[i][j] == null || board[i][j].getColor() != myColor);
  }
}
