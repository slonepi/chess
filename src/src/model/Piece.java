package model;

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

  public Color getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return " "+symbol+" ";
  }
}
