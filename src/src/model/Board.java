package model;

import model.Pieces.*;
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

    // Pawns
    for (int i = 0; i < 8; i++) {
      board[1][i] = new Pawn(Color.WHITE);
      board[6][i] = new Pawn(Color.BLACK);
    }

   // Knights
    board[0][1] = new Knight(Color.WHITE);
    board[0][6] = new Knight(Color.WHITE);
    board[7][1] = new Knight(Color.BLACK);
    board[7][6] = new Knight(Color.BLACK);

    // Knights
    board[0][2] = new Bishop(Color.WHITE);
    board[0][5] = new Bishop(Color.WHITE);
    board[7][2] = new Bishop(Color.BLACK);
    board[7][5] = new Bishop(Color.BLACK);

    // Kings & Queens
    board[0][4] = new King(Color.WHITE);
    board[7][4] = new King(Color.BLACK);
    board[0][3] = new Queen(Color.WHITE);
    board[7][3] = new Queen(Color.BLACK);

    // Rocks
    board[0][0] = new Rock(Color.WHITE);
    board[0][7] = new Rock(Color.WHITE);
    board[7][0] = new Rock(Color.BLACK);
    board[7][7] = new Rock(Color.BLACK);
  }

  public List<Movement> getAvailableMovements(Color color) {
    List<Movement> availableMovements = new ArrayList<>();
    Piece piece;
    for (int i = 0; i < 8 ; i++) {
      for (int j = 0; j < 8; j++) {
        piece = board[i][j];
        if (piece != null && piece.getColor() == color) {
          availableMovements.addAll(piece.giveAvailableMovement(board,i,j));
        }
      }
    }
    return availableMovements;
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
    for (int i = 7; i >= 0; i --) {
      for (int j = 0; j<8; j++) {
        sb.append(" -");
      }
      sb.append("\n");
      sb.append(i+1);
      sb.append("|");
      for (int j = 0; j<8; j++) {
        sb.append(format(board[i][j],i,j));
        sb.append("|");
      }
      sb.append("\n");
    }
    sb.append("  ");
    for (int i = 0; i <8; i++) {
      sb.append(" ")
              .append(Character.toString((char) (i+65)))
              .append("  ");

    }
    return sb.toString();
  }


  public static String format(Piece piece,int i, int j) {
    String background =  (i+j) % 2 == 0 ? "47" : "40";
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
