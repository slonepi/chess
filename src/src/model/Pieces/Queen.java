package model.Pieces;

import model.Color;
import model.Movement;
import model.Piece;

import java.util.List;

/**
 * Created by yann on 30/12/16.
 */
public class Queen extends Piece {

  public Queen(Color color) {
    super(color,"Q");
  }


  @Override
  public List<Movement> giveAvailableMovement(Piece[][] board, int i, int j) {
    List<Movement> avaibleMovements = LinearPiece.giveAvailableMovement(board, i , j , color);
    avaibleMovements.addAll(DiagonalPiece.giveAvailableMovement(board, i, j, color));
    return avaibleMovements;
  }
}
