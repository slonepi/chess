package util;

import model.Color;
import model.Piece;
import model.Pieces.*;

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

    // Knight
    if (validateCoordonates(i-1,j-2) && board[i-1][j-2] instanceof Knight && board[i-1][j-2].getColor()!=color) {
      return true;
    }
    if (validateCoordonates(i-2,j-1) && board[i-2][j-1] instanceof Knight && board[i-2][j-1].getColor()!=color) {
      return true;
    }
    if (validateCoordonates(i-2,j+1) && board[i-2][j+1] instanceof Knight && board[i-2][j+1].getColor()!=color) {
      return true;
    }
    if (validateCoordonates(i-1,j+2) && board[i-1][j+2] instanceof Knight && board[i-1][j+2].getColor()!=color) {
      return true;
    }
    if (validateCoordonates(i+1,j-2) && board[i+1][j-2] instanceof Knight && board[i+1][j-2].getColor()!=color) {
      return true;
    }
    if (validateCoordonates(i+2,j-1) && board[i+2][j-1] instanceof Knight && board[i+2][j-1].getColor()!=color) {
      return true;
    }
    if (validateCoordonates(i+2,j-1) && board[i+2][j-1] instanceof Knight && board[i+2][j-1].getColor()!=color) {
      return true;
    }
    if (validateCoordonates(i+1,j+2) && board[i+1][j+2] instanceof Knight && board[i+1][j+2].getColor()!=color) {
      return true;
    }

    // Pawns
    int row = (color==Color.WHITE) ? 1 : 7;
    if (validateCoordonates(row,j-1) && board[row][j-1] instanceof Pawn && board[row][j-1].getColor() != color) {
      return true;
    }
    if (validateCoordonates(row,j+1) && board[row][j+1] instanceof Pawn && board[row][j+1].getColor() != color) {
      return true;
    }

    // Check linear threats
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

    pieceFound = false;
    m = i-1;
    n = j-1;
    while (!threatFound && validateCoordonates(m,n) && !pieceFound) {
      pieceFound = board[m][n] != null;
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Bishop);
      m--;
      n--;
    }

    pieceFound = false;
    m = i-1;
    n = j+1;
    while (!threatFound && validateCoordonates(m,n) && !pieceFound) {
      pieceFound = board[m][n] != null;
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Bishop);
      m--;
      n++;
    }

    pieceFound = false;
    m = i+1;
    n = j-1;
    while (!threatFound && validateCoordonates(m,n) && !pieceFound) {
      pieceFound = board[m][n] != null;
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Bishop);
      m++;
      n--;
    }

    pieceFound = false;
    m = i+1;
    n = j+1;
    while (!threatFound && validateCoordonates(m,n) && !pieceFound) {
      pieceFound = board[m][n] != null;
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Bishop);
      m++;
      n++;
    }

    return threatFound;
  }

  private static boolean enemyPieceFound(Piece[][] board, int i, int j, Color color) {
    return board[i][j] != null && board[i][j].getColor() != color;
  }
}
