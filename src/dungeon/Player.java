package dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the player and defines all the operations mandated by the IPlayer
 * interface.
 */
public class Player implements IPlayer {

  private int rows;
  private int columns;
  private String name;
  private Location location;
  private List<Treasures> collectedTreasure;
  private int playerArrows;
  private boolean isAlive;
  private int playerTreasure;

  /**
   * Constructs the player object in terms of name.
   *
   * @param name the player name.
   */
  public Player(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    this.name = name;
    this.rows = 0;
    this.columns = 0;
    this.collectedTreasure = new ArrayList<>();
    this.location = new Location(rows, columns);
    this.playerArrows = 3;
    this.isAlive = true;
    this.playerTreasure=0;
  }

  @Override
  public int getRows() {
    return rows;
  }

  public void updateRows(int rows) {
    this.rows = rows;
  }

  @Override
  public int getColumns() {
    return columns;
  }

  public void updateColumns(int columns) {
    this.columns = columns;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public List<Treasures> getCollectedTreasure() {
    return collectedTreasure;
  }

  /*public void updateCollectedTreasure(List<Treasures> collectedTreasure) {
    this.collectedTreasure = collectedTreasure;
  }*/

  @Override
  public Location getLocation() {
    return location;
  }


  @Override
  public void setCollectedTreasure(Treasures collectedTreasure) {
    this.collectedTreasure.add(collectedTreasure);
  }

  @Override
  public void setLocation(Location location) {
    this.location = location;
  }

  @Override
  public int getPlayerTreasure() {
    return playerTreasure;
  }

  @Override
  public void setPlayerTreasure(int playerTreasure) {
    this.playerTreasure = this.playerTreasure + playerTreasure;
  }

  public int getPlayerArrows() {
    return playerArrows;
  }

  public void setPlayerArrows(int playerArrows) {
    this.playerArrows = this.playerArrows + playerArrows;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  @Override
  public String toString() {
    String k = "";
    k += location.getRow() + "," + location.getCol();
    String a = "";
    for (int i = 0; i < collectedTreasure.size(); i++) {
      a += collectedTreasure.get(i) + " ";
    }
    return "Player{"
        + ", name='" + name + '\''
        + ", location=" + k
        + ", collectedTreasure=" + a
        + ", playerArrows=" + playerArrows
        + ", isAlive=" + isAlive
        + '}';
  }

  /*@Override
  public String toString() {
    return "Player{" +
        "rows=" + rows +
        ", columns=" + columns +
        ", name='" + name + '\'' +
        ", location=" + location +
        ", collectedTreasure=" + collectedTreasure +
        ", playerArrows=" + playerArrows +
        ", isAlive=" + isAlive +
        '}';
  }*/
}
