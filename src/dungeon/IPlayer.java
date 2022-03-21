package dungeon;

import java.util.List;

/**
 * An interface that represents the player. It includes the name, location and treasures of the
 * player.
 */
public interface IPlayer {


  /**
   * A method to update the player's treasure during the course of the game.
   *
   * @param collectedTreasure the treasure list collected.
   */
  void setCollectedTreasure(Treasures collectedTreasure);

  /**
   * A method that returns the collected treasure to display the user.
   *
   * @return the treasure list.
   */
  List<Treasures> getCollectedTreasure();

  /**
   * A method that returns the location of the player.
   *
   * @return the location in terms of rows and columns.
   */
  Location getLocation();

  /**
   * A method to update the player's location and when the player traverses through the grid.
   *
   * @param location the location that needs to updated.
   */
  void setLocation(Location location);

  /**
   * A method that returns the rows in location.
   *
   * @return rows in location
   */
  int getRows();

  /**
   * A method that returns the columns in location.
   *
   * @return columns in location
   */
  int getColumns();

  public int getPlayerArrows();

  public void setPlayerArrows(int playerArrows);

  public boolean isAlive();

  public void setAlive(boolean alive);
  public int getPlayerTreasure();
  public void setPlayerTreasure(int playerTreasure);

}
