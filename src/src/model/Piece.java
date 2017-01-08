package model;

import java.util.List;

/**
 * Created by yann on 23/12/16.
 */
public abstract class Piece {
  protected Color color;
  protected String symbol;

  protected Piece(Color color, String symbol) {
    this.color = color;
    this.symbol = symbol;
  }

  public abstract List<SimpleMovement> giveAvailableMovement(Piece[][] board, int i, int j);

  public Color getColor() {
    return this.color;
  }

  // Default behaviour, different for some piece (rock, pawn and king)
  public void move() {}

  public boolean hasMoved() { return false;}

  @Override
  public String toString() {
    return " "+symbol+" ";
  }

}
