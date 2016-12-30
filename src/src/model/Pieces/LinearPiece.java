package model.Pieces;

import model.Color;
import model.Movement;
import model.Piece;

import java.util.ArrayList;
import java.util.List;

import static util.MovementUtil.emptyCase;
import static util.MovementUtil.emptyOrForeignCase;
import static util.MovementUtil.validateCoordonates;

/**
 * Created by yann on 30/12/16.
 */
public class LinearPiece {

  public static List<Movement> giveAvailableMovement(Piece[][] board, int i, int j, Color color) {
      List<Movement> movements = new ArrayList<>();

      int m = i+1;
      int n = j+1;
      while (validateCoordonates(m,n) && emptyCase(m,n,board)) {
        movements.add(new Movement(i,j,m,n));
        m++;
        n++;
      }
      if (emptyOrForeignCase(m,n,board, color)) {
        movements.add(new Movement(i,j,m,n));
      }

      m = i-1;
      n = j-1;
      while (validateCoordonates(m,n) && emptyCase(m,n,board)) {
        movements.add(new Movement(i,j,m,n));
        m--;
        n--;
      }
      if (emptyOrForeignCase(m,n,board, color)) {
        movements.add(new Movement(i,j,m,n));
      }

      m = i-1;
      n = j+1;
      while (validateCoordonates(m,n) && emptyCase(m,n,board)) {
        movements.add(new Movement(i,j,m,n));
        m--;
        n++;
      }
      if (emptyOrForeignCase(m,n,board, color)) {
        movements.add(new Movement(i,j,m,n));
      }

      m = i-1;
      n = j-1;
      while (validateCoordonates(m,n) && emptyCase(m,n,board)) {
        movements.add(new Movement(i,j,m,n));
        m--;
        n--;
      }
      if (emptyOrForeignCase(m,n,board, color)) {
        movements.add(new Movement(i,j,m,n));
      }
      return movements;
    }
}
