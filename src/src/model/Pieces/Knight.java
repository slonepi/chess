package model.Pieces;

import model.Color;
import model.SimpleMovement;
import model.Piece;

import java.util.ArrayList;
import java.util.List;

import static util.MovementUtil.emptyOrForeignCase;

/**
 * Created by yann on 23/12/16.
 */
public class Knight extends Piece {

  private boolean hasMoved;

  public Knight(Color color) {
    super(color,"N");
  }

  @Override
  public List<SimpleMovement> giveAvailableMovement(Piece[][] board, int i, int j) {
    List<SimpleMovement> movements = new ArrayList<>();

    if (emptyOrForeignCase(i - 1, j - 2, board, color)) {
      movements.add(new SimpleMovement(i, j, i - 1, j - 2));
    }
    if (emptyOrForeignCase(i - 2, j - 1, board, color)) {
      movements.add(new SimpleMovement(i, j, i - 2, j - 1));
    }
    if (emptyOrForeignCase(i - 2, j + 1, board, color)) {
      movements.add(new SimpleMovement(i, j, i - 2, j + 1));
    }
    if (emptyOrForeignCase(i - 1, j + 2, board, color)) {
      movements.add(new SimpleMovement(i, j, i - 1, j + 2));
    }
    if (emptyOrForeignCase(i + 1, j - 2, board, color)) {
      movements.add(new SimpleMovement(i, j, i + 1, j - 2));
    }
    if (emptyOrForeignCase(i + 2, j - 1, board, color)) {
      movements.add(new SimpleMovement(i, j, i + 2, j - 1));
    }
    if (emptyOrForeignCase(i + 2, j + 1, board, color)) {
      movements.add(new SimpleMovement(i, j, i + 2, j + 1));
    }
    if (emptyOrForeignCase(i + 1, j + 2, board, color)) {
      movements.add(new SimpleMovement(i, j, i + 1, j + 2));
    }

    return movements;
  }

  @Override
  public void move() {
    super.move();
    this.hasMoved = true;
  }

  @Override
  public boolean hasMoved() {
    return hasMoved;
  }
}
