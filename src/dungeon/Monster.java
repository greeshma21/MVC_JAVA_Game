package dungeon;

/**
 * A class that represents the Otyugh monster in the game.
 */
public class Monster {

  private int health;
  private Location location;
  private int rows;
  private int columns;
  private boolean isAlive;

  /**
   * Monster constructor that has health, location and if the monster is alive as its attributes.
   */
  public Monster() {
    this.health = 100;
    this.rows = 0;
    this.columns = 0;
    this.location = new Location(rows, columns);
    this.isAlive = true;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public int getRows() {
    return rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public int getColumns() {
    return columns;
  }

  public void setColumns(int columns) {
    this.columns = columns;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  @Override
  public String toString() {
    /*String k = "";
    k += location.getRow() + "," + location.getCol();*/
    return "Monster{"
        + "health=" + health
        + ", isAlive=" + isAlive
        + '}';
  }
}
