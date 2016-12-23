package model;

import model.Pieces.Pawn;

/**
 * Created by yann on 23/12/16.
 */
public class Board {
  Piece[][] board;

  public Board(){
    board = new Piece[8][8];
  }

  public void init(){
    for (int i = 0; i < 8; i++) {
      board[1][i] = new Pawn(Color.WHITE);
      board[6][i] = new Pawn(Color.BLACK);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 8; i ++) {
      for (int j = 0; j<8; j++) {
        sb.append(" -");
      }
      sb.append("\n|");
      for (int j = 0; j<8; j++) {
        sb.append(format(board[i][j],i,j));
        sb.append("|");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public static String format(Piece piece,int i, int j) {
    String background =  (i+j) % 2 == 0 ? "40" : "47";
    String pieceColor = "31";
    String value = " ";
    if (piece != null) {
      value = piece.toString();
      if (piece.color == Color.BLACK)
        pieceColor = "34";
    }

    return (char)27+ "["+pieceColor+";"+background+"m"+value+ (char)27 + "[0;0m";
  }
}
