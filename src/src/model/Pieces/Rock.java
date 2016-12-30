package model.Pieces;

import model.Color;
import model.Movement;
import model.Piece;

import java.util.ArrayList;
import java.util.List;

import static util.MovementUtil.emptyCase;
import static util.MovementUtil.emptyOrForeignCase;
import static util.MovementUtil.validateCoordonates;

/**
 * Created by yann on 23/12/16.
 */
public class Rock extends Piece {

  public Rock(Color color) {
    super(color, "R");
  }

  @Override
  public List<Movement> giveAvailableMovement(Piece[][] board, int i, int j) {
    return DiagonalPiece.giveAvailableMovement(board, i, j, color);
  }
  }
