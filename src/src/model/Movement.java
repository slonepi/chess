package model;

/**
 * Created by yann on 23/12/16.
 */
public class Movement {
  public int ifrom;
  public int jfrom;
  public int ito;
  public int jto;

  public Movement(int iFrom, int jFrom, int iTo, int jTo) {
    this.ifrom = iFrom;
    this.jfrom = jFrom;
    this.ito = iTo;
    this.jto = jTo;
  }
}
