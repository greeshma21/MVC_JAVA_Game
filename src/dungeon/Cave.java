package dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class that defines the cave or every node of a 2D grid. This object forms the required dungeon
 * for the model.
 */
public class Cave {

  static HashMap<Integer, Cave> map = new HashMap<>();
  private List<Treasures> treasureList;
  private boolean isCave;
  private int id;
  private Location location;
  private List<Cave> neighbors;
  private int row;
  private int column;
  static int count = 0;
  private Cave north;
  private Cave south;
  private Cave west;
  private Cave east;
  private boolean isTreasureCollected;
  private boolean isMonsterPresent;
  private Monster monster;
  private int smell;
  private int arrows;
  private int treasure;
  protected Smell pungent;
  private boolean isThiefPresent;

  /**
   * A constructor without parameters and has required attributes like location, neighbors.
   */
  public Cave() {

    this.treasureList = new ArrayList<>();
    this.isCave = true;
    this.id = 0;
    this.location = new Location(row, column);
    this.row = 0;
    this.column = 0;
    this.neighbors = new ArrayList<>();
    this.north = null;
    this.south = null;
    this.west = null;
    this.east = null;
    this.isTreasureCollected = false;
    this.isMonsterPresent = false;
    this.monster = null;
    this.smell = 0;
    this.arrows = 0;
    this.pungent = Smell.NOPUNGENT;
    this.isThiefPresent = false;
  }

  public List<Cave> getNeighbors() {
    return neighbors;
  }

  public void setNeighbors(List<Cave> neighbors) {
    this.neighbors = neighbors;
  }

  public void addNeighbors(Cave cave) {
    this.neighbors.add(cave);
  }


  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public List<Treasures> getTreasureList() {
    return treasureList;
  }

  public void setTreasureList(List<Treasures> treasureList) {
    this.treasureList = treasureList;
  }

  public boolean isCave() {
    return this.isCave;
  }

  public void setCave(boolean cave) {
    isCave = cave;
  }

  public int getId() {
    return id;
  }

  public void setId() {
    this.id = count++;
    getObjFromId(this.id);
  }

  public Cave getNorth() {
    return north;
  }

  public void setNorth(Cave north) {
    this.north = north;
  }

  public Cave getSouth() {
    return south;
  }

  public void setSouth(Cave south) {
    this.south = south;
  }

  public Cave getWest() {
    return west;
  }

  public void setWest(Cave west) {
    this.west = west;
  }

  public Cave getEast() {
    return east;
  }

  public void setEast(Cave east) {
    this.east = east;
  }

  public void getObjFromId(int id) {
    map.put(id, this);
    //System.out.println("Map....."+map);
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(int rows, int columns) {
    this.location.setRow(row);
    this.location.setCol(columns);
  }

  public boolean isTreasureCollected() {
    return isTreasureCollected;
  }

  public void setTreasureCollected(boolean treasureCollected) {
    isTreasureCollected = treasureCollected;
  }

  public boolean isMonsterPresent() {
    return isMonsterPresent;
  }

  public void setMonsterPresent(boolean monsterPresent) {
    isMonsterPresent = monsterPresent;
  }

  public Monster getMonster() {
    return monster;
  }

  public void setMonster(Monster monster) {
    this.monster = monster;
  }

  public int getSmell() {
    return smell;
  }

  public void setSmell(int smell) {
    this.smell = this.smell + smell;
  }

  public int getArrows() {
    return arrows;
  }

  public void setArrows(int arrows) {
    this.arrows = arrows;
  }

  public int getTreasure() {
    return treasure;
  }

  public void setTreasure(int treasure) {
    this.treasure = treasure;
  }

  public Smell getPungent() {
    return pungent;
  }

  public void setPungent(Smell pungent) {
    this.pungent = pungent;
  }

  public boolean isThiefPresent() {
    return isThiefPresent;
  }

  public void setThiefPresent(boolean thiefPresent) {
    isThiefPresent = thiefPresent;
  }

  @Override
  public String toString() {
    String a = "";
    for (int i = 0; i < neighbors.size(); i++) {
      a += neighbors.get(i).getId() + " ";
    }
    String k = "";
    k += location.getRow() + "," + location.getCol();
    return "{"
        + "id=" + id
        + ", pungent=" + pungent
        /*+ "smell=" + smell
        + ", isCave=" + isCave +
        + ", monster=" + monster
        + "treasure=" + treasureList.size()*/
        + ", isMonsterPresent=" + isMonsterPresent
        //+ ", arrows=" + arrows
        + '}';
  }

}
