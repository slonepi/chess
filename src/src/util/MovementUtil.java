package util;

import model.Color;
import model.Piece;
import model.Pieces.Queen;
import model.Pieces.Rock;

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

  public static boolean checkIfCaseIsThreatened(Piece[][] board, int i, int j, Color color) {
    // Check lines
    boolean threatFound = false;
    int m = i+1;
    int n = j;
    boolean pieceFound = false;

    while (!threatFound && validateCoordonates(m,n) && !pieceFound) {
      pieceFound = board[m][n] != null;
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Rock);
      m++;
    }

    pieceFound = false;
    m = i-1;
    n = j;
    while (!threatFound && validateCoordonates(m,n) && !pieceFound) {
      pieceFound = board[m][n] != null;
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Rock);
      m--;
    }

    pieceFound = false;
    m = i;
    n = j+1;
    while (!threatFound && validateCoordonates(m,n) && !pieceFound) {
      pieceFound = board[m][n] != null;
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Rock);
      n++;
    }

    pieceFound = false;
    m = i;
    n = j-1;
    while (!threatFound && validateCoordonates(m,n) && !pieceFound) {
      pieceFound = board[m][n] != null;
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Rock);
      n--;
    }

    return threatFound;
  }

  private static boolean enemyPieceFound(Piece[][] board, int i, int j, Color color) {
    return board[i][j] != null && board[i][j].getColor() != color;
  }
}
