package driver;

import dungeon.Dungeon;
import java.io.InputStreamReader;
import dungeoncontroller.DungeonController;
public class TextDriver {
  public void startGame() {
  /*Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to Dungeon Game!");
    System.out.println("Please enter your name");
    String name = scanner.next();
    System.out.println("Please specify the size of dungeon:");
    System.out.println("Rows:");
    int rows = scanner.nextInt();
    System.out.println("Columns:");
    int columns = scanner.nextInt();
    System.out.println("Want a wrapping/non-wrapping dungeon?");
    System.out.println("Enter True/False");
    boolean wrapped = scanner.nextBoolean();
    System.out.println("Enter a value for interconnectivity");
    int interconnectivity = scanner.nextInt();
    System.out.println("Enter percentage of caves to add treasure");
    int percent = scanner.nextInt();
    System.out.println("Enter number of extra monsters to add to dungeon");
    int monsterNumber = scanner.nextInt();*/



    String name = "Greeshma";
    int rows = 4;
    int columns = 6;
    boolean wrapped = true;
    int interconnectivity = 12;
    int percent = 50;
    int monsterNumber = 0;

    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    new DungeonController(input, output)
        .playGame(new Dungeon(rows, columns, wrapped, interconnectivity, percent, monsterNumber));
  }
}

