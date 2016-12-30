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
public class Bishop extends Piece {

  public Bishop(Color color) {
    super(color,"B");
  }

  @Override
  public List<Movement> giveAvailableMovement(Piece[][] board, int i, int j) {
    return LinearPiece.giveAvailableMovement(board, i, j,color);
  }
}
