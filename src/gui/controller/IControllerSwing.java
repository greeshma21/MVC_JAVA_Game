package gui.controller;

public interface IControllerSwing {

  public void start();

  public void constructDungeon(int rows, int columns, boolean wrapped, int interconnectivity,
      int percent, int monsterNumber);

  public void constructDungeon1();
}
