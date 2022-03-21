package dungeoncontroller;

import dungeon.IDungeon;
import java.io.IOException;
import java.util.Scanner;

/**
 * Dungeon controller that handles the player inputs and provides the outcomes of every input of the
 * player.
 */
public class DungeonController implements IDungeonController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in the source to read from.
   * @param out the target to print to
   */
  public DungeonController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(IDungeon model) {
    if (model == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
    try {
//      out.append(model.displayDungeon());
//      model.displayDungeon();
//      model.generateDungeon();
//      model.displayFinalDungeon();
      while (!model.isGameOver()) {
        out.append("\n");
        out.append("Move, Pickup, or Shoot (M-P-S)?");

        String input = scan.next();
        if (input.equalsIgnoreCase("M")) {
          out.append("Where to?");
          String direction = scan.next();
          if (direction.equalsIgnoreCase("Q")) {
            out.append("Game Quit");
            return;
          }
          out.append("\n");
          out.append(model.move(direction));
        } else if (input.equalsIgnoreCase("P")) {
          out.append("What?");
          String pick = scan.next();
          if (pick.equalsIgnoreCase("arrow")) {
            out.append(model.pickUpArrows());
          } else if (pick.equalsIgnoreCase("treasure")) {
            out.append(model.pickUpTreasure());
          } else if (pick.equalsIgnoreCase("Q")) {
            out.append("Game Quit");
            return;
          } else {
            out.append("Invalid input");
          }
        } else if (input.equalsIgnoreCase("S")) {
          out.append("No. of caves (1-5)?");
          String noOfCaves = scan.next();
          out.append("Where to?");
          String dir = scan.next();
          if (dir.equalsIgnoreCase("N") || dir.equalsIgnoreCase("S") || dir
              .equalsIgnoreCase("E") || dir.equalsIgnoreCase("W")) {
            out.append(model.shootArrow(dir, noOfCaves));
          } else if (dir.equalsIgnoreCase("Q")) {
            out.append("Game Quit");
            return;
          } else {
            out.append("Invalid input");
          }
        } else if (input.equalsIgnoreCase("Q")) {
          out.append("Game Quit");
          return;
        }
        else {
          out.append("Invalid input");
          continue;
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
    try {
      out.append("Game Over");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}
