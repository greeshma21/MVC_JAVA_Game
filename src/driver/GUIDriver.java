package driver;

import dungeon.Dungeon;
import dungeon.IDungeon;
import gui.controller.ControllerSwing;
import gui.controller.IControllerSwing;
import gui.view.DungeonView;
import gui.view.IDungeonView;

public class GUIDriver {
  public void start() {
    /*String name = "Greeshma";
    int rows = 4;
    int columns = 6;
    boolean wrapped = true;
    int interconnectivity = 12;
    int percent = 50;
    int monsterNumber = 0;
    IDungeon model = new Dungeon(rows, columns, wrapped, interconnectivity, percent, monsterNumber);*/
    IDungeonView dungeonView = new DungeonView();
    IControllerSwing controller = new ControllerSwing(dungeonView);
    controller.start();
  }

}
