package model;

import java.util.List;

/**
 * Created by yann on 23/12/16.
 */
public class SimpleMovement extends Movement {
  public int ifrom;
  public int jfrom;
  public int ito;
  public int jto;
  private String pieceSymbol;

  public SimpleMovement(int iFrom, int jFrom, int iTo, int jTo, String pieceSymbol) {
    this(iFrom,jFrom,iTo,jTo);
    this.pieceSymbol = pieceSymbol;
  }

  public SimpleMovement(int iFrom, int jFrom, int iTo, int jTo) {
    this.ifrom = iFrom;
    this.jfrom = jFrom;
    this.ito = iTo;
    this.jto = jTo;
  }

  public String getPieceSymbol() {
    return pieceSymbol;
  }

  public void setPieceSymbol(String pieceSymbol) {
    this.pieceSymbol = pieceSymbol;
  }

  @Override
  public String toString() {
    return "SimpleMovement{" +
            "pieceSymbol='" + pieceSymbol + '\'' +
            ", ifrom=" + ifrom +
            ", jfrom=" + jfrom +
            ", ito=" + ito +
            ", jto=" + jto +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SimpleMovement movement = (SimpleMovement) o;

    if (ifrom != movement.ifrom) return false;
    if (jfrom != movement.jfrom) return false;
    if (ito != movement.ito) return false;
    return jto == movement.jto;

  }

  @Override
  public int hashCode() {
    int result = ifrom;
    result = 31 * result + jfrom;
    result = 31 * result + ito;
    result = 31 * result + jto;
    return result;
  }

  @Override
  public void executeMovement(Piece[][] board, List<Piece> eatenPieces) {
    Piece movingPiece = board[ifrom][jfrom];
    board[ifrom][jfrom] = null;

    if (board[ito][jto] != null) {
      eatenPieces.add(board[ito][jto]);
    }
    board[ito][jto] = movingPiece;
  }
}
