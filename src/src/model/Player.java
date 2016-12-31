package model;

/**
 * Created by yann on 23/12/16.
 */
public class Player {
  private String name;
  private Color color;

  public Player(String name,Color color) {
    this.name = name;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public Color getColor() {
    return color;
  }

  public void setName(String name) {
    this.name = name;
  }
}
