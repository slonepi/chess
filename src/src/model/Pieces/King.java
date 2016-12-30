package model.Pieces;

import model.Color;
import model.Movement;
import model.Piece;

import java.util.ArrayList;
import java.util.List;

import static util.MovementUtil.*;

/**
 * Created by yann on 23/12/16.
 */
public class King extends Piece {

  public King(Color color) {
    super(color, "K");
  }

  @Override
  public List<Movement> giveAvailableMovement(Piece[][] board, int i, int j) {
    List<Movement> movements = new ArrayList<>();

    if (emptyOrForeignCase(i-1,j-1,board,this.color)) {
      movements.add(new Movement(i,j,i-1,j-1));
    }
    if (emptyOrForeignCase(i-1,j,board,this.color)) {
      movements.add(new Movement(i,j,i-1,j));
    }
    if (emptyOrForeignCase(i-1,j+1,board,this.color)) {
      movements.add(new Movement(i,j,i-1,j+1));
    }
    if (emptyOrForeignCase(i,j-1,board,this.color)) {
      movements.add(new Movement(i,j,i,j-1));
    }
    if (emptyOrForeignCase(i,j+1,board,this.color)) {
      movements.add(new Movement(i,j,i,j+1));
    }
    if (emptyOrForeignCase(i+1,j-1,board,this.color)) {
      movements.add(new Movement(i,j,i+1,j-1));
    }
    if (emptyOrForeignCase(i+1,j,board,this.color)) {
      movements.add(new Movement(i,j,i+1,j));
    }
    if (emptyOrForeignCase(i+1,j+1,board,this.color)) {
      movements.add(new Movement(i,j,i+1,j+1));
    }

    return movements;
  }
}
