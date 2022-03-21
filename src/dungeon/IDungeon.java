package dungeon;

/**
 * This interface represents the dugngeon of the model. It contains all the operations including
 * generating the grid, getting start and end caves, playing the game and dumping the grid to the
 * screen.
 */
public interface IDungeon {

  /**
   * A method that dumps the dungeon to the screen.
   *
   * @return the string format of the dungeon.
   */
  public String displayDungeon();

  /**
   * A method that generates the final dungeon along with caves and treasures.
   */
  public void generateDungeon();

  /**
   * A method that displays the final dungeon after generating the grid.
   */
  public void displayFinalDungeon();

  /**
   * A method that returns the random cave that is chosen as the destination.
   *
   * @return the destination cave in every game.
   */
  public Cave getDestination();

  /**
   * A method that returns the random cave that is chosen as the start cave.
   *
   * @return the start cave in every game.
   */
  public Cave getStartCave();

  /**
   * A method that returns the final dungeon grid in 2D grid format.
   *
   * @return the 2D grid.
   */
  public Cave[][] getDungeonGrid();

  /**
   * A method that returns if the game is over or still in progress.
   *
   * @return flase if the game is over.
   */
  public boolean isGameOver();

  /**
   * A method that handles the move functionality provided by the user as a input.
   *
   * @param direction the directon in which the player wants to make a move.
   * @return the result of the game state after a move is made.
   */
  public String move(String direction);

  /**
   * A method that handles picking up arrow command from the player.
   *
   * @return the result of the game state after the arrow is picked up.
   */
  public String pickUpArrows();

  /**
   * A method that handles picking up treasure command from the player.
   *
   * @return the result of the game state after the treasure is picked up.
   */
  public String pickUpTreasure();

  /**
   * A method that handles shoot arrow command from the player.
   *
   * @param direction the direction in which the player wants to shoot.
   * @param distance  the distance which the player aims to hit.
   * @return the result of the game state after shoot is made.
   */
  public String shootArrow(String direction, String distance);

}
