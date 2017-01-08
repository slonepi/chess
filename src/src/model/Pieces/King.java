package model.Pieces;

import model.Color;
import model.SimpleMovement;
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
  public List<SimpleMovement> giveAvailableMovement(Piece[][] board, int i, int j) {
    List<SimpleMovement> movements = new ArrayList<>();

    if (emptyOrForeignCase(i-1,j-1,board,color)) {
      movements.add(new SimpleMovement(i,j,i-1,j-1));
    }
    if (emptyOrForeignCase(i-1,j,board,color)) {
      movements.add(new SimpleMovement(i,j,i-1,j));
    }
    if (emptyOrForeignCase(i-1,j+1,board,color)) {
      movements.add(new SimpleMovement(i,j,i-1,j+1));
    }
    if (emptyOrForeignCase(i,j-1,board,color)) {
      movements.add(new SimpleMovement(i,j,i,j-1));
    }
    if (emptyOrForeignCase(i,j+1,board,color)) {
      movements.add(new SimpleMovement(i,j,i,j+1));
    }
    if (emptyOrForeignCase(i+1,j-1,board,color)) {
      movements.add(new SimpleMovement(i,j,i+1,j-1));
    }
    if (emptyOrForeignCase(i+1,j,board,color)) {
      movements.add(new SimpleMovement(i,j,i+1,j));
    }
    if (emptyOrForeignCase(i+1,j+1,board,color)) {
      movements.add(new SimpleMovement(i,j,i+1,j+1));
    }

    return movements;
  }
}
