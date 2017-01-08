package model;

import model.Board;

import java.util.List;

/**
 * Created by yann on 06/01/17.
 */
public abstract class Movement {

  public abstract void executeMovement(Piece[][] board, List<Piece> eatenPieces);
}
