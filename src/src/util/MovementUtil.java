package util;

import model.Color;
import model.Piece;

/**
 * Created by yann on 23/12/16.
 */
public class MovementUtil {

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
