package model.Pieces;

import model.Color;
import model.Movement;
import model.Piece;

import java.util.ArrayList;
import java.util.List;

import static util.MovementUtil.emptyOrForeignCase;

/**
 * Created by yann on 23/12/16.
 */
public class Knight extends Piece {
  public Knight(Color color) {
    super(color,"N");
  }

  @Override
  public List<Movement> giveAvailableMovement(Piece[][] board, int i, int j) {
    List<Movement> movements = new ArrayList<>();

    if (emptyOrForeignCase(i - 1, j - 2, board, color)) {
      movements.add(new Movement(i, j, i - 1, j - 2));
    }
    if (emptyOrForeignCase(i - 2, j - 1, board, color)) {
      movements.add(new Movement(i, j, i - 2, j - 1));
    }
    if (emptyOrForeignCase(i - 2, j + 1, board, color)) {
      movements.add(new Movement(i, j, i - 2, j + 1));
    }
    if (emptyOrForeignCase(i - 1, j + 2, board, color)) {
      movements.add(new Movement(i, j, i - 1, j + 2));
    }
    if (emptyOrForeignCase(i + 1, j - 2, board, color)) {
      movements.add(new Movement(i, j, i + 1, j - 2));
    }
    if (emptyOrForeignCase(i + 2, j - 1, board, color)) {
      movements.add(new Movement(i, j, i + 2, j - 1));
    }
    if (emptyOrForeignCase(i + 2, j + 1, board, color)) {
      movements.add(new Movement(i, j, i + 2, j + 1));
    }
    if (emptyOrForeignCase(i + 1, j + 2, board, color)) {
      movements.add(new Movement(i, j, i + 1, j + 2));
    }

    return movements;
  }
}
