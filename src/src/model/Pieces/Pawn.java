package model.Pieces;

import model.Color;
import model.SimpleMovement;
import model.Piece;

import java.util.ArrayList;
import java.util.List;

import static util.MovementUtil.emptyCase;
import static util.MovementUtil.validateCoordonates;

/**
 * Created by yann on 23/12/16.
 */
public class Pawn extends Piece {

  public Pawn(Color color) {
    super(color,"P");
  }

  @Override
  public List<SimpleMovement> giveAvailableMovement(Piece[][] board, int i, int j) {
    List<SimpleMovement> movements = new ArrayList<>();

      if (color == Color.WHITE) {
        if (emptyCase(i + 1, j, board)) {
          movements.add(new SimpleMovement(i, j, i + 1, j));
        }
        if (i == 1 && emptyCase(i + 1, j, board) && emptyCase(i + 2, j, board)) {
          movements.add(new SimpleMovement(i, j, i + 2, j));
        }
        if (validateCoordonates(i+1,j+1) && board[i+1][j+1] != null && board[i+1][j+1].getColor() != color) {
          movements.add(new SimpleMovement(i,j,i+1, j+1));
        }
        if (validateCoordonates(i+1,j-1) && board[i+1][j-1] != null && board[i+1][j-1].getColor() != color) {
          movements.add(new SimpleMovement(i,j,i+1, j-1));
        }
      } else {
        if (emptyCase(i - 1, j, board)) {
          movements.add(new SimpleMovement(i, j, i - 1, j));
        }
        if (i == 6 && emptyCase(i - 1, j, board) && emptyCase(i - 2, j, board)) {
          movements.add(new SimpleMovement(i, j, i - 2, j));
        }
        if (validateCoordonates(i-1,j-1) && board[i-1][j-1] != null && board[i-1][j-1].getColor() != color) {
          movements.add(new SimpleMovement(i,j,i-1, j-1));
        }
        if (validateCoordonates(i-1,j+1) && board[i-1][j+1] != null && board[i-1][j+1].getColor() != color) {
          movements.add(new SimpleMovement(i,j,i-1, j+1));
        }
      }

    return movements;
  }
}
