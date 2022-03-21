package dungeoncontroller;

import dungeon.IDungeon;

/**
 * Represents the controller for the Dungeon model: handles the player inputs by executing it using
 * the model and conveys move outcomes to the player.
 */
public interface IDungeonController {

  /**
   * Execute the dungeon game given a Dungeon Model. When the game is over, the playGame method
   * ends.
   *
   * @param model a non-null Dungeon Model
   */
  public void playGame(IDungeon model);
}
