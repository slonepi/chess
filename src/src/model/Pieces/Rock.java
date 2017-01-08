package model.Pieces;

import model.Color;
import model.SimpleMovement;
import model.Piece;

import java.util.List;

/**
 * Created by yann on 23/12/16.
 */
public class Rock extends Piece {

  private boolean hasMoved;

  public Rock(Color color) {
    super(color, "R");
  }

  @Override
  public List<SimpleMovement> giveAvailableMovement(Piece[][] board, int i, int j) {
    return DiagonalPiece.giveAvailableMovement(board, i, j, color);
  }

  @Override
  public void move() {
    hasMoved = true;
  }

  public boolean hasMoved () { return hasMoved;}
}
