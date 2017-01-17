package util;

import model.Color;
import model.Piece;
import model.Pieces.*;

import java.util.ArrayList;
import java.util.List;

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


    //TODO check opposite king threat
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

  public static List<Piece> findPinnedPieces(Piece[][] board, int i, int j, Color color) {
    List<Piece> pinned = new ArrayList<>();

    // Check linear threats
    boolean threatFound = false;
    int allyFound = 0;
    int allyX = 0, allyY = 0;
    int m = i+1;
    int n = j;

    while (allyFound < 2 && validateCoordonates(m,n) && !threatFound) {

      // Check for enemy piece
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Rock);

      // If an ally has been found, he his pinned
      if (threatFound && allyFound == 1) {
        pinned.add(board[allyX][allyY]);
      }

      // If ally found
      if (board[m][n] != null && !threatFound) {
        allyFound++;
        allyX = m;
        allyY = n;
      }
      m++;
    }

    threatFound = false;
    allyFound = 0;
    allyX = 0;
    allyY = 0;
    m = i-1;
    n = j;

    while (allyFound < 2 && validateCoordonates(m,n) && !threatFound) {

      // Check for enemy piece
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Rock);

      // If an ally has been found, he his pinned
      if (threatFound && allyFound == 1) {
        pinned.add(board[allyX][allyY]);
      }

      // If ally found
      if (board[m][n] != null && !threatFound) {
        allyFound++;
        allyX = m;
        allyY = n;
      }
      m--;
    }

    threatFound = false;
    allyFound = 0;
    allyX = 0;
    allyY = 0;
    m = i;
    n = j+1;

    while (allyFound < 2 && validateCoordonates(m,n) && !threatFound) {

      // Check for enemy piece
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Rock);

      // If an ally has been found, he his pinned
      if (threatFound && allyFound == 1) {
        pinned.add(board[allyX][allyY]);
      }

      // If ally found
      if (board[m][n] != null && !threatFound) {
        allyFound++;
        allyX = m;
        allyY = n;
      }
      n++;
    }

    threatFound = false;
    allyFound = 0;
    allyX = 0;
    allyY = 0;
    m = i;
    n = j-1;

    while (allyFound < 2 && validateCoordonates(m,n) && !threatFound) {

      // Check for enemy piece
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Rock);

      // If an ally has been found, he his pinned
      if (threatFound && allyFound == 1) {
        pinned.add(board[allyX][allyY]);
      }

      // If ally found
      if (board[m][n] != null && !threatFound) {
        allyFound++;
        allyX = m;
        allyY = n;
      }
      n--;
    }

    threatFound = false;
    allyFound = 0;
    allyX = 0;
    allyY = 0;
    m = i-1;
    n = j-1;

    while (allyFound < 2 && validateCoordonates(m,n) && !threatFound) {

      // Check for enemy piece
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Bishop);

      // If an ally has been found, he his pinned
      if (threatFound && allyFound == 1) {
        pinned.add(board[allyX][allyY]);
      }

      // If ally found
      if (board[m][n] != null && !threatFound) {
        allyFound++;
        allyX = m;
        allyY = n;
      }
      m--;
      n--;

    }

    threatFound = false;
    allyFound = 0;
    allyX = 0;
    allyY = 0;
    m = i-1;
    n = j+1;

    while (allyFound < 2 && validateCoordonates(m,n) && !threatFound) {

      // Check for enemy piece
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Bishop);

      // If an ally has been found, he his pinned
      if (threatFound && allyFound == 1) {
        pinned.add(board[allyX][allyY]);
      }

      // If ally found
      if (board[m][n] != null && !threatFound) {
        allyFound++;
        allyX = m;
        allyY = n;
      }
      m--;
      n++;
    }

    threatFound = false;
    allyFound = 0;
    allyX = 0;
    allyY = 0;
    m = i+1;
    n = j-1;

    while (allyFound < 2 && validateCoordonates(m,n) && !threatFound) {

      // Check for enemy piece
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Bishop);

      // If an ally has been found, he his pinned
      if (threatFound && allyFound == 1) {
        pinned.add(board[allyX][allyY]);
      }

      // If ally found
      if (board[m][n] != null && !threatFound) {
        allyFound++;
        allyX = m;
        allyY = n;
      }
      m++;
      n--;
    }

    threatFound = false;
    allyFound = 0;
    allyX = 0;
    allyY = 0;
    m = i+1;
    n = j+1;

    while (allyFound < 2 && validateCoordonates(m,n) && !threatFound) {

      // Check for enemy piece
      threatFound = enemyPieceFound(board,m,n,color) && (board[m][n] instanceof Queen || board[m][n] instanceof Bishop);

      // If an ally has been found, he his pinned
      if (threatFound && allyFound == 1) {
        pinned.add(board[allyX][allyY]);
      }

      // If ally found
      if (board[m][n] != null && !threatFound) {
        allyFound++;
        allyX = m;
        allyY = n;
      }
      m++;
      n++;
    }

    return pinned;
  }

}
