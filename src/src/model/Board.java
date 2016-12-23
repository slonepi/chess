package model;

import model.Pieces.Pawn;
import util.MovementUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yann on 23/12/16.
 */
public class Board {
  Piece[][] board;
  List<Piece> eatenPieces;

  public Board(){
    board = new Piece[8][8];
  }

  public void init(){
    eatenPieces = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      board[1][i] = new Pawn(Color.WHITE);
      board[6][i] = new Pawn(Color.BLACK);
    }
  }

  public List<Movement> getAvailableMouvements(Color color) {
    List<Movement> availableMouvements = new ArrayList<>();
    Piece piece;
    for (int i = 0; i < 8 ; i++) {
      for (int j = 0; j < 8; j++) {
        piece = board[i][j];
        if (piece != null && piece.getColor() == color) {
          availableMouvements.addAll(MovementUtil.getPieceMovement(piece,board,i,j));
        }
      }
    }
    return availableMouvements;
  }

  public void doMove(Movement movement) {
    Piece movingPiece = board[movement.ifrom][movement.jfrom];
    board[movement.ifrom][movement.jfrom] = null;

    if (board[movement.ito][movement.jto] != null) {
      eatenPieces.add(board[movement.ito][movement.jto]);
    }
    board[movement.ito][movement.jto] = movingPiece;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 8; i ++) {
      for (int j = 0; j<8; j++) {
        sb.append(" -");
      }
      sb.append("\n");
      sb.append(i);
      sb.append("|");
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
    String value = "   ";
    if (piece != null) {
      value = piece.toString();
      if (piece.color == Color.BLACK)
        pieceColor = "34";
    }

    return (char)27+ "["+pieceColor+";"+background+"m"+value+ (char)27 + "[0;0m";
  }
}
