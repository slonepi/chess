package model.Pieces;

import model.Color;
import model.SimpleMovement;
import model.Piece;

import java.util.List;

/**
 * Created by yann on 30/12/16.
 */
public class Bishop extends Piece {

  public Bishop(Color color) {
    super(color,"B");
  }

  @Override
  public List<SimpleMovement> giveAvailableMovement(Piece[][] board, int i, int j) {
    return LinearPiece.giveAvailableMovement(board, i, j,color);
  }
}
