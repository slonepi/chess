package model;

import model.Pieces.*;
import util.MovementUtil;

import java.util.ArrayList;
import java.util.List;
import util.Logger;

import static util.MovementUtil.checkIfCaseIsThreatened;

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
    /*for (int i = 0; i < 8; i++) {
      board[1][i] = new Pawn(Color.WHITE);
      board[6][i] = new Pawn(Color.BLACK);
    }

   // Knights
    board[0][1] = new Knight(Color.WHITE);
    board[0][6] = new Knight(Color.WHITE);
    board[7][1] = new Knight(Color.BLACK);
    board[7][6] = new Knight(Color.BLACK);

    // Bishops
    board[0][2] = new Bishop(Color.WHITE);
    board[0][5] = new Bishop(Color.WHITE);
    board[7][2] = new Bishop(Color.BLACK);
    board[7][5] = new Bishop(Color.BLACK);
*/
    // Kings & Queens
    board[0][4] = new King(Color.WHITE);
    board[7][4] = new King(Color.BLACK);
    board[0][3] = new Queen(Color.WHITE);
    board[7][3] = new Queen(Color.BLACK);

    // Rocks
    board[1][0] = new Pawn(Color.WHITE);
    board[1][7] = new Pawn(Color.WHITE);
    board[1][4] = new Pawn(Color.WHITE);
    board[0][0] = new Rock(Color.WHITE);
    board[0][7] = new Rock(Color.WHITE);
    board[7][0] = new Rock(Color.BLACK);
    board[7][7] = new Rock(Color.BLACK);
  }

  public List<Movement> getAvailableMovements(Color color) {
    List<Movement> availableMovements = new ArrayList<>();
    Piece piece;

    //TODO check if one square is threatened
    // Rocks movements
    int row = color == Color.BLACK ? 7 : 0;
    if (board[row][4] instanceof King && !board[row][4].hasMoved()) {
      Piece rockLeft = board[row][0];
      Piece rockRight = board[row][7];

      // Check if King can rockLeft
      if (rockLeft instanceof Rock && (rockLeft.getColor() == color)
              && board[row][1] == null && board[row][2] == null && board[row][3] == null
              && !rockLeft.hasMoved()
              && !checkIfCaseIsThreatened(board,row,0,color)
              && !checkIfCaseIsThreatened(board,row,1,color)
              && !checkIfCaseIsThreatened(board,row,2,color)
              && !checkIfCaseIsThreatened(board,row,3,color)
              && !checkIfCaseIsThreatened(board,row,4,color)) {
        availableMovements.add(new RockMovement(true, color));
        Logger.logInformation("Rock left possible");
      }

      if (rockRight instanceof Rock && (rockRight.getColor() == color)
              && board[row][5] == null && board[row][6] == null
              && !rockRight.hasMoved()
              && !checkIfCaseIsThreatened(board,row,7,color)
              && !checkIfCaseIsThreatened(board,row,6,color)
              && !checkIfCaseIsThreatened(board,row,5,color)
              && !checkIfCaseIsThreatened(board,row,4,color)) {
        availableMovements.add(new RockMovement(false, color));
        Logger.logInformation("Rock right possible");
      }
    }

    // Find sexier way to find the king
    int kingX = -1;
    int kingY = -1;

    int i =0, j=0;
    while (kingX == -1){
      j=0;
      while (kingX == -1 && j<8) {
        if (board[i][j] instanceof King && board[i][j].getColor() == color) {
          kingX = i;
          kingY = j;
        }
        j++;
      }
      i++;
    }
    List<Piece> pinnedPieces = MovementUtil.findPinnedPieces(board, kingX, kingY, color);
    Logger.logInformation("Number of pinned pieces:"+pinnedPieces.size());

    for (i = 0; i < 8 ; i++) {
      for (j = 0; j < 8; j++) {
        piece = board[i][j];
        if (piece != null && piece.getColor() == color && !pinnedPieces.contains(piece)) {
          availableMovements.addAll(piece.giveAvailableMovement(board,i,j));
        }
      }
    }

    Logger.logInformation("Movements available:"+availableMovements.size());
    return availableMovements;
  }

  public void doMove(Movement movement) {
    movement.executeMovement(board, eatenPieces);
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
