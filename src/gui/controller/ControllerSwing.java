package gui.controller;

import dungeon.Dungeon;
import dungeon.IDungeon;
import gui.view.DungeonView;
import gui.view.GameView;
import gui.view.IDungeonView;
import gui.view.InitialInputPanel;
import gui.view.View1;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.View;

public class ControllerSwing implements IControllerSwing {

  private IDungeonView view;
  private IDungeon model;
  private GameView gameView;

  public ControllerSwing(IDungeonView v) {
    this.view = v;
    this.model = null;
    this.gameView=null;
  }

  @Override
  public void start() {
    view.display();
  }

  @Override
  public void constructDungeon(int rows, int columns, boolean wrapped, int interconnectivity,
      int percent,
      int monsterNumber) {
    this.model = new Dungeon(rows, columns, wrapped, interconnectivity, percent, monsterNumber);
    System.out.println("Entered");
    System.out.println(model.getStartCave());
    //view.removeInitialPane();
  }

  /*public void test(int r) {
    this.model = new Dungeon(r, 20,true,12,20,2);
    System.out.println("Entered");
  }*/

  public void constructDungeon1() {
    System.out.println("Initialized");
    /*view.test();
    view.getInitialInputPanel();*/
    //System.out.println(view.getInitialInputPanel().getRowsFromPanel());
    this.model = new Dungeon(view.getInitialInputPanel().getRowsFromPanel(),
        view.getInitialInputPanel().getColumnsFromPanel(),
        view.getInitialInputPanel().getWrappingType(),
        view.getInitialInputPanel().getInterconnectivityFromPanel(),
        view.getInitialInputPanel().getCavePercentFromPanel(),
        view.getInitialInputPanel().getMonsterNumberFromPanel());
   // System.out.println("Initialized");
  }
  //}


}
