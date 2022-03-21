package dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.Set;

/**
 * A class that implements and defines all the operations mandated by the IDungeon interface. It
 * also contains some helper methods to perform the operations defined in the interface.
 */
public class Dungeon implements IDungeon {

  private int rows;
  private int columns;
  private boolean wrapped;
  private int interconnectivity;
  private Cave[][] dungeonGrid;
  private ArrayList<ArrayList<Cave>> locSets;
  private List<Pair> edgelist;
  private List<Pair> remainingEdgelist;
  private int percent;
  private List<Pair> usedSecondEdges;
  private List<Pair> usedFirstEdges;
  private List<Treasures> treasureList;
  private Cave startCave;
  private int startId;
  private Cave endCave;
  private List<Integer> cavesOnly;
  private int monsterNumber;
  //private IPlayer player;
  public IPlayer player;

  RandomNumberGenerator random = new RandomNumberGenerator();

  /**
   * Constructs the dungeon based on the inputs specified by the user.
   *
   * @param rows              the number of rows of the grid.
   * @param columns           the number of columns of the grid.
   * @param wrapped           the boolean value if dungeon is wrapped or not.
   * @param interconnectivity the interconnectivity value entered by user.
   * @param percent           the percentage of caves to which treasures must be added.
   */
  public Dungeon(int rows, int columns, boolean wrapped, int interconnectivity, int percent,
      int monsterNumber) {
    /*if (rows == 0 || columns == 0 || percent == 0 || percent > 100 || rows < 3 || columns < 3
        || interconnectivity > (rows * columns)) {
      throw new IllegalArgumentException("Invalid input");
    }*/
    this.rows = rows;
    this.columns = columns;
    this.wrapped = wrapped;
    this.interconnectivity = interconnectivity;
    this.percent = percent;
    this.dungeonGrid = new Cave[rows][columns];
    this.locSets = new ArrayList<>();
    this.edgelist = new ArrayList<Pair>();
    this.remainingEdgelist = new ArrayList<Pair>();
    this.usedSecondEdges = new ArrayList<>();
    this.usedFirstEdges = new ArrayList<>();
    this.startCave = new Cave();
    this.treasureList = new ArrayList<>();
    this.player = new Player("Greeshma");
    this.endCave = new Cave();
    this.cavesOnly = new ArrayList<>();
    this.monsterNumber = monsterNumber;
    this.startId = 0;
    displayDungeon();
    generateDungeon();
    //displayFinalDungeon();
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public boolean isWrapped() {
    return wrapped;
  }

  public int getInterconnectivity() {
    return interconnectivity;
  }

  @Override
  public Cave[][] getDungeonGrid() {
    return this.dungeonGrid;
  }

  public void setDungeonGrid(Cave[][] dungeonGrid) {
    this.dungeonGrid = dungeonGrid;
  }

  public List<Pair> getEdgelist() {
    return edgelist;
  }

  public void setEdgelist(List<Pair> edgelist) {
    this.edgelist = edgelist;
  }

  public List<Pair> getRemainingEdgelist() {
    return remainingEdgelist;
  }

  public void setRemainingEdgelist(List<Pair> remainingEdgelist) {
    this.remainingEdgelist = remainingEdgelist;
  }

  public int getPercent() {
    return percent;
  }

  public void setPercent(int percent) {
    this.percent = percent;
  }

  public List<Pair> getUsedSecondEdges() {
    return usedSecondEdges;
  }

  public void setUsedSecondEdges(List<Pair> usedSecondEdges) {
    this.usedSecondEdges = usedSecondEdges;
  }

  public List<Pair> getUsedFirstEdges() {
    return usedFirstEdges;
  }

  public void setUsedFirstEdges(List<Pair> usedFirstEdges) {
    this.usedFirstEdges = usedFirstEdges;
  }

  public void setStartCave(Cave startCave) {
    this.startCave = startCave;
  }

  public Cave getEndCave() {
    return endCave;
  }

  public void setEndCave(Cave endCave) {
    this.endCave = endCave;
  }

  public List<Integer> getCavesOnly() {
    return cavesOnly;
  }

  public void setCavesOnly(List<Integer> cavesOnly) {
    this.cavesOnly = cavesOnly;
  }

  private Cave[][] createDungeonMatrix(int rows, int columns) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        this.dungeonGrid[i][j] = new Cave();
        this.dungeonGrid[i][j].setId();
        this.dungeonGrid[i][j].setRow(i);
        this.dungeonGrid[i][j].setColumn(j);
        this.dungeonGrid[i][j].setLocation(i, j);
      }
    }

    return this.dungeonGrid;
  }

  @Override
  public String displayDungeon() {
    Cave[][] d;
    String grid = "";
    d = createDungeonMatrix(rows, columns);

    for (int i = 0; i < this.rows; i++) {
      grid += "\n";
      for (int j = 0; j < this.columns; j++) {
        grid += d[i][j].getId();
        grid += "\t";
      }
    }
    getPotentialPaths(d, rows, columns, this.wrapped);
    return grid;
  }

  private List<Pair> getPotentialPaths(Cave[][] dungeon, int rows, int columns, boolean isWrapped) {
    Cave[][] d = dungeon;
    Pair<Cave, Cave> edgesRight;
    Pair<Cave, Cave> edgesDown;
    if (!isWrapped) {
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns - 1; j++) {
          edgesRight = new Pair<Cave, Cave>(d[i][j], d[i][j + 1]);
          this.edgelist.add(edgesRight);
        }
      }
      for (int i = 0; i < rows - 1; i++) {
        for (int j = 0; j < columns; j++) {
          edgesDown = new Pair<Cave, Cave>(d[i][j], d[i + 1][j]);
          this.edgelist.add(edgesDown);
        }
      }
    } else {
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns - 1; j++) {
          edgesRight = new Pair<Cave, Cave>(d[i][j], d[i][j + 1]);
          this.edgelist.add(edgesRight);
        }
      }
      for (int i = 0; i < rows; i++) {
        edgesRight = new Pair<Cave, Cave>(d[i][columns - 1], d[i][0]);
        this.edgelist.add(edgesRight);
      }
      for (int i = 0; i < rows - 1; i++) {
        for (int j = 0; j < columns; j++) {
          edgesDown = new Pair<Cave, Cave>(d[i][j], d[i + 1][j]);
          this.edgelist.add(edgesDown);
        }
      }
      for (int j = 0; j < columns; j++) {
        edgesDown = new Pair<Cave, Cave>(d[rows - 1][j], d[0][j]);
        this.edgelist.add(edgesDown);
      }
    }
    return this.edgelist;
  }

  private ArrayList<ArrayList<Cave>> getLocationSets(int rows, int columns) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        ArrayList<Cave> subSet = new ArrayList<>();
        subSet.add(this.dungeonGrid[i][j]);
        this.locSets.add(subSet);
      }
    }
    return locSets;
  }

  @Override
  public void generateDungeon() {
    getLocationSets(this.rows, this.columns);
    while (this.edgelist.size() != 0) {
      int randomEdge = random.getRandomNumberInRange(0, this.edgelist.size() - 1, 0);
      Pair<Cave, Cave> oneEdge = this.edgelist.remove(randomEdge);
      Cave node1 = oneEdge.first;
      Cave node2 = oneEdge.second;
      int node1loc = getNodeLocInLocSets1(node1);
      int node2loc = getNodeLocInLocSets1(node2);
      if (node1loc != node2loc) {
        this.usedFirstEdges.add(oneEdge);
        this.dungeonGrid[node1.getRow()][node1.getColumn()].addNeighbors(node2);
        this.dungeonGrid[node2.getRow()][node2.getColumn()].addNeighbors(node1);
        this.locSets.get(node1loc).addAll(this.locSets.get(node2loc));
        this.locSets.remove(this.locSets.get(node2loc));
      } else {
        this.remainingEdgelist.add(oneEdge);
      }

    }
    gridWithInterconnectivity();
    updateEastCell();
    updateSouthCell();
    updateNorthCell();
    updateWestCell();
    updateIsCave();
    treasureBag();
    allocateTreasures();
    getDestination();
    assignMonster();
    addSmell();
    assignArrows();
    assignThief();
  }

  private void gridWithInterconnectivity() {
    for (int i = 0; i < this.interconnectivity; i++) {
      int randomEdge = random.getRandomNumberInRange(0, this.remainingEdgelist.size() - 1, 0);
      Pair<Cave, Cave> oneEdge = this.remainingEdgelist.remove(randomEdge);
      Cave node1 = oneEdge.first;
      Cave node2 = oneEdge.second;
      int node1loc = getNodeLocInLocSets1(node1);
      int node2loc = getNodeLocInLocSets1(node2);
      this.dungeonGrid[node1.getRow()][node1.getColumn()].addNeighbors(node2);
      this.dungeonGrid[node2.getRow()][node2.getColumn()].addNeighbors(node1);
      this.usedSecondEdges.add(oneEdge);
    }
  }


  private void updateIsCave() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getNeighbors().size() == 2) {
          this.dungeonGrid[i][j].setCave(false);
        }
      }
    }
  }

  /**
   * A method to display the final dungeon after updating neighbor nodes and treasure list.
   */
  public void displayFinalDungeon() {
    for (int i = 0; i < this.rows; i++) {
      System.out.print("\n");
      for (int j = 0; j < this.columns; j++) {
        System.out.print(dungeonGrid[i][j].getId());
        /*System.out.print(dungeonGrid[i][j].getNeighbors());
        System.out.print("Arrow "+ dungeonGrid[i][j].getArrows());*/
        System.out.print(dungeonGrid[i][j].isCave());
        System.out.print(dungeonGrid[i][j].isMonsterPresent());
        /*System.out.print(dungeonGrid[i][j].getTreasureList().size());
        System.out.print(dungeonGrid[i][j].getPungent());
        System.out.print(dungeonGrid[i][j].getSmell());*/
        System.out.print("\t");
      }
    }
    System.out.print("\n");
    System.out.println("Start cave " + this.startCave.getId());
    System.out.println("End cave " + this.endCave.getId());
  }

  private int getNodeLocInLocSets1(Cave cave) {
    for (int i = 0; i < locSets.size(); i++) {
      if (locSets.get(i).contains(cave)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * A method that returns the number of nodes in the dungeon after connection.
   *
   * @return the number of caves.
   */
  public int getCaveCount() {
    int count = 0;
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].isCave()) {
          count++;
        }
      }
    }
    return count;
  }

  public List<Pair> getUsedEdges() {
    return this.usedFirstEdges;
  }

  public List<Pair> getUsedEdgesInInterconnectivity() {
    return this.usedSecondEdges;
  }

  private List<Treasures> treasureBag() {

    int val = random.getRandomNumberInRange(1, 4, 0);
    for (int i = 0; i < val; i++) {
      this.treasureList.add(Treasures.DIAMONDS);
    }
    val = random.getRandomNumberInRange(1, 4, 0);
    for (int i = 0; i < val; i++) {
      this.treasureList.add(Treasures.RUBIES);
    }
    val = random.getRandomNumberInRange(1, 4, 0);
    for (int i = 0; i < val; i++) {
      this.treasureList.add(Treasures.SAPPHIRES);
    }
    return this.treasureList;

  }

  private void allocateTreasures() {
    int totalCaves = getCaveCount();
    int caveNumberToAddTreasure = (int) (percent * totalCaves / 100);
    int count = 0;
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].isCave()) {
          if (count == caveNumberToAddTreasure) {
            break;
          }
          count++;
          this.dungeonGrid[i][j].setTreasureList(this.treasureList);
        }
      }
    }
  }

  private void updateEastCell() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns - 1; j++) {
        if (this.dungeonGrid[i][j].getNeighbors().contains(this.dungeonGrid[i][j + 1])) {
          this.dungeonGrid[i][j].setEast(this.dungeonGrid[i][j + 1]);
        }
      }
    }
    if (this.wrapped) {
      for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.columns; j++) {
          if (this.dungeonGrid[i][columns - 1].getNeighbors().contains(this.dungeonGrid[i][0])) {
            this.dungeonGrid[i][columns - 1].setEast(this.dungeonGrid[i][0]);
          }
        }
      }
    }
  }

  private void updateSouthCell() {
    for (int i = 0; i < this.rows - 1; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getNeighbors().contains(this.dungeonGrid[i + 1][j])) {
          this.dungeonGrid[i][j].setSouth(this.dungeonGrid[i + 1][j]);
        }
      }
    }
    if (this.wrapped) {
      for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.columns; j++) {
          if (this.dungeonGrid[i][j].getNeighbors().contains(this.dungeonGrid[0][j])) {
            this.dungeonGrid[i][j].setSouth(this.dungeonGrid[0][j]);
          }
        }
      }
    }
  }

  private void updateNorthCell() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (i > 0) {
          if (this.dungeonGrid[i][j].getNeighbors().contains(this.dungeonGrid[i - 1][j])) {
            this.dungeonGrid[i][j].setNorth(this.dungeonGrid[i - 1][j]);
          }
        }
      }
    }
    if (this.wrapped) {
      for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.columns; j++) {
          if (i == 0) {
            if (this.dungeonGrid[i][j].getNeighbors()
                .contains(this.dungeonGrid[this.rows - 1][j])) {
              this.dungeonGrid[i][j].setNorth(this.dungeonGrid[this.rows - 1][j]);
            }
          }
        }
      }
    }
  }

  private void updateWestCell() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (j > 0) {
          if (this.dungeonGrid[i][j].getNeighbors().contains(this.dungeonGrid[i][j - 1])) {
            this.dungeonGrid[i][j].setWest(this.dungeonGrid[i][j - 1]);
          }
        }
      }
    }
    if (this.wrapped) {
      for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.columns; j++) {
          if (j == 0) {
            if (this.dungeonGrid[i][j].getNeighbors()
                .contains(this.dungeonGrid[i][this.columns - 1])) {
              this.dungeonGrid[i][j].setWest(this.dungeonGrid[i][this.columns - 1]);
            }
          }
        }
      }
    }
  }

  public Cave[][] getFinalDungeon() {
    return this.dungeonGrid;
  }

  /*@Override
  public Cave getStartCave() {
    Cave cave = new Cave();
    int valRows = random.getRandomNumberInRange(1, this.rows - 1, 0);
    int valColumns = random.getRandomNumberInRange(1, this.columns - 1, 0);
    for (int i = 0; i <= this.rows; i++) {
      for (int j = 0; j <= this.columns; j++) {
        cave = dungeonGrid[valRows][valColumns];
      }
    }
    this.startCave = cave;
    return this.startCave;
  }*/

  private List<Cave> getCaveList() {
    List<Cave> caveList = new ArrayList<>();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].isCave()) {
          this.cavesOnly.add(this.dungeonGrid[i][j].getId());
          caveList.add(this.dungeonGrid[i][j]);
        }
      }
    }
    return caveList;
  }

  @Override
  public Cave getStartCave() {
    List<Cave> onlyCaves = getCaveList();
    Cave cave = new Cave();
    //int valRows = random.getRandomNumberInRange(1, this.onlyCaves.size(), 0)
    Collections.shuffle(onlyCaves);
    for (int i = 0; i < onlyCaves.size(); i++) {
      cave = onlyCaves.get(0);
    }
    this.startCave = cave;
    this.startId = cave.getId();
    return this.startCave;
  }

  @Override
  public Cave getDestination() {
    Cave dest = new Cave();
    dest = bfs(getStartCave());
    this.endCave = dest;
    return dest;
  }

  /**
   * A method that returns the destination cave at a distance 5 paths away from the entered source
   * cave.
   *
   * @param startcave the start cave.
   * @return the destination cave.
   */
  public Cave bfs(Cave startcave) {
    Queue<Cave> queue = new LinkedList<>();
    Set<Cave> visitedList = new HashSet<>();
    List<Cave> destCave = new ArrayList<>();
    queue.add(startcave);
    int count = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      count++;
      for (int i = 0; i < size; i++) {
        Cave temp = queue.poll();
        visitedList.add(temp);
        if (temp.getWest() != null && !visitedList.contains(temp.getWest())) {
          queue.add(temp.getWest());
          if (count >= 5 && temp.getWest().isCave()) {
            destCave.add(temp.getWest());
          }
        }
        if (temp.getEast() != null && !visitedList.contains(temp.getEast())) {
          queue.add(temp.getEast());
          if (count >= 5 && temp.getEast().isCave()) {
            destCave.add(temp.getEast());
          }
        }
        if (temp.getNorth() != null && !visitedList.contains(temp.getNorth())) {
          queue.add(temp.getNorth());
          if (count >= 5 && temp.getNorth().isCave()) {
            destCave.add(temp.getNorth());
          }
        }
        if (temp.getSouth() != null && !visitedList.contains(temp.getSouth())) {
          queue.add(temp.getSouth());
          if (count >= 5 && temp.getSouth().isCave()) {
            destCave.add(temp.getSouth());
          }
        }
      }
    }
    Cave dest = new Cave();
    //= destCave.get(0);
    /*int val = random.getRandomNumberInRange(0, destCave.size() - 1, 0);*/
    //return destCave.get(0);
    for (int i = 0; i < destCave.size() - 1; i++) {
      dest = destCave.get(0);
    }
    return dest;
  }

  private void assignMonster() {
    List<Integer> caves;
    int tmp = this.endCave.getId();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getId() == tmp) {
          this.dungeonGrid[i][j].setMonster(new Monster());
        }
      }
    }
    caves = this.cavesOnly;
    int val = this.startCave.getId();
    for (int i = 0; i < caves.size(); i++) {
      if (caves.get(i) == val) {
        caves.remove(i);
      }
    }
    Collections.shuffle(caves);
    int count = 1;

    //while(count<=this.monsterNumber) {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        for (int k = 0; k < this.monsterNumber; k++) {
          if (this.dungeonGrid[i][j].getId() == caves.get(k)) {
            this.dungeonGrid[i][j].setMonster(new Monster());
            count++;
          }
        }
      }
    }
    //}
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getMonster() != null) {
          this.dungeonGrid[i][j].setMonsterPresent(true);
        }
      }
    }

  }

  private String getPossibleMoves(Cave start) {
    String possibleMoves = "Doors lead to ";
    if (start.getNorth() != null) {
      possibleMoves += "North ";
    }
    if (start.getSouth() != null) {
      possibleMoves += "South ";
    }
    if (start.getEast() != null) {
      possibleMoves += "East ";
    }
    if (start.getWest() != null) {
      possibleMoves += "West ";
    }
    return possibleMoves;
  }

  /**
   * A method that returns a string of the player's collected treasure for display purpose.
   *
   * @param playerTreasure the player's treasure.
   * @return the string output.
   */
  public String collectedTreasureCount(List<Treasures> playerTreasure) {
    String str = "";
    List<Treasures> temp = playerTreasure;
    int rubyCount = 0;
    int diamondCount = 0;
    int sapphireCount = 0;
    str += "Collected Treasure Count" + "-" + temp.size();
    for (int i = 0; i < temp.size(); i++) {
      if (temp.get(i) == Treasures.RUBIES) {
        rubyCount++;
      }
      if (temp.get(i) == Treasures.DIAMONDS) {
        diamondCount++;
      }
      if (temp.get(i) == Treasures.SAPPHIRES) {
        sapphireCount++;
      }
    }
    str += "\n";
    str += "Ruby Count: " + rubyCount;
    str += "\n";
    str += "Diamond Count: " + diamondCount;
    str += "\n";
    str += "Sapphire Count: " + sapphireCount;
    return str;
  }

  private void removeTreasurefromCave(Cave cave) {
    int tmpId = cave.getId();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getId() == tmpId) {
          //this.dungeonGrid[i][j].getTreasureList().clear();
          for (int k = 0; k < this.treasureList.size(); k++) {
            this.dungeonGrid[i][j].getTreasureList().remove(0);
          }
        }
      }

    }
  }

  private int getRubyCount(List<Treasures> playerTreasure) {
    List<Treasures> temp = playerTreasure;
    int rubyCount = 0;
    for (int i = 0; i < temp.size(); i++) {
      if (temp.get(i) == Treasures.RUBIES) {
        rubyCount++;
      }
    }
    return rubyCount;
  }

  private int getDiamondCount(List<Treasures> playerTreasure) {
    List<Treasures> temp = playerTreasure;
    int diamondCount = 0;
    for (int i = 0; i < temp.size(); i++) {
      if (temp.get(i) == Treasures.DIAMONDS) {
        diamondCount++;
      }
    }
    return diamondCount;
  }

  private int getSapphireCount(List<Treasures> playerTreasure) {
    List<Treasures> temp = playerTreasure;
    int sapphireCount = 0;
    for (int i = 0; i < temp.size(); i++) {
      if (temp.get(i) == Treasures.SAPPHIRES) {
        sapphireCount++;
      }
    }
    return sapphireCount;
  }

  /*private String showStatus() {

  }*/

  @Override
  public boolean isGameOver() {
    //return this.player.getLocation() == this.endCave.getLocation();
    if (!this.player.isAlive()) {
      return true;
    }
    return this.player.isAlive() && this.player.getLocation() == this.endCave.getLocation();
    //return (this.player.isAlive() && this.player.getLocation() == this.endCave.getLocation());

  }

  @Override
  public String move(String direction) {
    StringBuilder res = new StringBuilder("");
    int chance = RandomNumberGenerator.getRandomNumberInRange(1, 100, 0);
    /*if (this.player.getLocation() == this.endCave.getLocation()) {
//      res.append("Game over");
//      throw new IllegalStateException("Game Over");
      return "Game Over!";
    }*/

    if (this.player.isAlive() && this.player.getLocation() != this.endCave.getLocation()) {
      if ((direction.equalsIgnoreCase("N")) && this.startCave.getNorth() != null) {
        player.setLocation(this.startCave.getNorth().getLocation());
        if (this.startCave.getNorth().getMonster() != null) {
          if (this.startCave.getNorth().getMonster().getHealth() == 100) {
            this.player.setAlive(false);
            res.append("Chomp, chomp, chomp, you are eaten by an Otyugh!");
          } else if (chance <= 50) {
            this.player.setAlive(false);
            res.append("Chomp, chomp, chomp, you are eaten by an Otyugh!");
          } else {
            this.player.setAlive(true);
            res.append("You escaped from the monster!");
          }
        } else {
          if (this.startCave.getNorth().getTreasureList().size() != 0) {
            res.append("You find treasure here" + "\n");
          }
          if (this.startCave.getNorth().getArrows() == 1) {
            res.append("You find an arrow here" + "\n");
          }
          if (this.startCave.getNorth().getPungent() == Smell.LESSPUNGENT) {
            res.append("You smell something terrible nearby" + "\n");
          }
          if (this.startCave.getNorth().getPungent() == Smell.MOREPUNGENT) {
            res.append("You hear a great howl in the distance" + "\n");
          }
        }
        this.startCave = this.startCave.getNorth();
      } else if ((direction.equalsIgnoreCase("S")) && this.startCave.getSouth() != null) {
        player.setLocation(this.startCave.getSouth().getLocation());
        if (this.startCave.getSouth().getMonster() != null) {
          if (this.startCave.getSouth().getMonster().getHealth() == 100) {
            this.player.setAlive(false);
            res.append("Chomp, chomp, chomp, you are eaten by an Otyugh!");
          } else if (chance <= 50) {
            this.player.setAlive(false);
            res.append("Chomp, chomp, chomp, you are eaten by an Otyugh!");
          } else {
            this.player.setAlive(true);
            res.append("You escaped from the monster!");
          }
        } else {
          if (this.startCave.getSouth().getTreasureList().size() != 0) {
            res.append("You find treasure here" + "\n");
          }
          if (this.startCave.getSouth().getArrows() == 1) {
            res.append("You find an arrow here" + "\n");
          }
          if (this.startCave.getSouth().getPungent() == Smell.LESSPUNGENT) {
            res.append("You smell something terrible nearby" + "\n");
          }
          if (this.startCave.getSouth().getPungent() == Smell.MOREPUNGENT) {
            res.append("You hear a great howl in the distance" + "\n");
          }
        }
        this.startCave = this.startCave.getSouth();
      } else if ((direction.equalsIgnoreCase("W")) && this.startCave.getWest() != null) {
        player.setLocation(this.startCave.getWest().getLocation());
        if (this.startCave.getWest().getMonster() != null) {
          if (this.startCave.getWest().getMonster().getHealth() == 100) {
            this.player.setAlive(false);
            res.append("Chomp, chomp, chomp, you are eaten by an Otyugh!");
          } else if (chance <= 50) {
            this.player.setAlive(false);
            res.append("Chomp, chomp, chomp, you are eaten by an Otyugh!");
          } else {
            this.player.setAlive(true);
            res.append("You escaped from the monster!");
          }
        } else {
          if (this.startCave.getWest().getTreasureList().size() != 0) {
            res.append("You find treasure here" + "\n");
          }
          if (this.startCave.getWest().getArrows() == 1) {
            res.append("You find an arrow here" + "\n");
          }
          if (this.startCave.getWest().getPungent() == Smell.LESSPUNGENT) {
            res.append("You smell something terrible nearby" + "\n");
          }
          if (this.startCave.getWest().getPungent() == Smell.MOREPUNGENT) {
            res.append("You hear a great howl in the distance" + "\n");
          }
        }
        this.startCave = this.startCave.getWest();
      } else if ((direction.equalsIgnoreCase("E")) && this.startCave.getEast() != null) {
        player.setLocation(this.startCave.getEast().getLocation());
        if (this.startCave.getEast().getMonster() != null) {
          if (this.startCave.getEast().getMonster().getHealth() == 100) {
            this.player.setAlive(false);
            res.append("Chomp, chomp, chomp, you are eaten by an Otyugh!");
          } else if (chance <= 50) {
            this.player.setAlive(false);
            res.append("Chomp, chomp, chomp, you are eaten by an Otyugh!");
          } else {
            this.player.setAlive(true);
            res.append("You escaped from the monster!");
          }
        } else {
          if (this.startCave.getEast().getTreasureList().size() != 0) {
            res.append("You find treasure here" + "\n");
          }
          if (this.startCave.getEast().getArrows() == 1) {
            res.append("You find an arrow here" + "\n");
          }
          if (this.startCave.getEast().getPungent() == Smell.LESSPUNGENT) {
            res.append("You smell something terrible nearby" + "\n");
          }
          if (this.startCave.getEast().getPungent() == Smell.MOREPUNGENT) {
            res.append("You hear a great howl in the distance" + "\n");
          }
        }
        this.startCave = this.startCave.getEast();
      }
      res.append(getPossibleMoves(this.startCave));
      res.append(this.player.getLocation());
    }
    return res.toString();
  }

  private void addSmell() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].isMonsterPresent()) {
          if (this.dungeonGrid[i][j].getNorth() != null) {
            this.dungeonGrid[i][j].getNorth().setPungent(Smell.MOREPUNGENT);
          }
          if (this.dungeonGrid[i][j].getSouth() != null) {
            this.dungeonGrid[i][j].getSouth().setPungent(Smell.MOREPUNGENT);
          }
          if (this.dungeonGrid[i][j].getEast() != null) {
            this.dungeonGrid[i][j].getEast().setPungent(Smell.MOREPUNGENT);
          }
          if (this.dungeonGrid[i][j].getWest() != null) {
            this.dungeonGrid[i][j].getWest().setPungent(Smell.MOREPUNGENT);
          }
        }
      }
    }
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getPungent() == Smell.MOREPUNGENT) {
          if (this.dungeonGrid[i][j].getNorth() != null
              && this.dungeonGrid[i][j].getNorth().getPungent() != Smell.MOREPUNGENT
              && !this.dungeonGrid[i][j].getNorth().isMonsterPresent()) {
            this.dungeonGrid[i][j].getNorth().setPungent(Smell.LESSPUNGENT);
          }
          if (this.dungeonGrid[i][j].getSouth() != null
              && this.dungeonGrid[i][j].getSouth().getPungent() != Smell.MOREPUNGENT
              && !this.dungeonGrid[i][j].getSouth().isMonsterPresent()) {
            this.dungeonGrid[i][j].getSouth().setPungent(Smell.LESSPUNGENT);
          }
          if (this.dungeonGrid[i][j].getEast() != null
              && this.dungeonGrid[i][j].getEast().getPungent() != Smell.MOREPUNGENT
              && !this.dungeonGrid[i][j].getEast().isMonsterPresent()) {
            this.dungeonGrid[i][j].getEast().setPungent(Smell.LESSPUNGENT);
          }
          if (this.dungeonGrid[i][j].getWest() != null
              && this.dungeonGrid[i][j].getWest().getPungent() != Smell.MOREPUNGENT
              && !this.dungeonGrid[i][j].getWest().isMonsterPresent()) {
            this.dungeonGrid[i][j].getWest().setPungent(Smell.LESSPUNGENT);
          }
        }
      }
    }
  }

  private boolean checkIfPlayerAlive() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getLocation() == this.player.getLocation()) {
          if (this.dungeonGrid[i][j].getMonster() != null) {
            if (this.dungeonGrid[i][j].getMonster().getHealth() > 0) {
              this.player.setAlive(false);
            }
          }
        }
      }
    }
    boolean health = this.player.isAlive();
    return health;
  }

  private List<Integer> getAllNodes() {
    List<Integer> allNodes = new ArrayList<>();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        allNodes.add(this.dungeonGrid[i][j].getId());
      }
    }
    //System.out.println(allNodes.size());
    return allNodes;
  }

  private void assignArrows() {
    List<Integer> allNodeList = getAllNodes();
    Collections.shuffle(allNodeList);
    int numberOfArrows = (int) (this.percent * allNodeList.size() / 100);

    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        for (int k = 0; k < numberOfArrows; k++) {
          if (this.dungeonGrid[i][j].getId() == allNodeList.get(k)) {
            this.dungeonGrid[i][j].setArrows(1);
          }
        }
      }
    }

  }

  @Override
  public String pickUpTreasure() {
    StringBuilder res = new StringBuilder("");
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getLocation() == this.player.getLocation()
            && this.dungeonGrid[i][j].getTreasureList().size() > 0) {
          for (int k = 0; k < this.dungeonGrid[i][j].getTreasureList().size(); k++) {
            this.player.setCollectedTreasure(this.dungeonGrid[i][j].getTreasureList().get(k));
          }
          res.append("You picked up treasure");
          //if (this.dungeonGrid[i][j].getTreasureList().size() > 0) {
//          this.dungeonGrid[i][j].getTreasureList()
//              .subList(0, this.dungeonGrid[i][j].getTreasureList().size()).clear();
//          for(int k = 0; k < this.dungeonGrid[i][j].getTreasureList().size();k++) {
          this.dungeonGrid[i][j].getTreasureList().clear();
//          }
          //}
        }
      }
    }

    //res.append("Player's current treasure"+this.player.getCollectedTreasure().size());
    return res.toString();
  }

  @Override
  public String pickUpArrows() {
    StringBuilder res = new StringBuilder("");
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getLocation() == this.player.getLocation()
            && this.dungeonGrid[i][j].getArrows() == 1) {
          this.dungeonGrid[i][j].setArrows(-1);
          this.player.setPlayerArrows(1);
          res.append("You picked up an arrow");
        }
      }
    }
    return res.toString();
  }

  @Override
  public String shootArrow(String direction, String distance) {
    int distNo = Integer.parseInt(distance);
    direction = direction.toUpperCase(Locale.ROOT);
    StringBuilder res = new StringBuilder("");
    String k = "";
    if (distNo < 1 || distNo > 5) {
      try {
        throw new IllegalArgumentException("Invalid number");
      } catch (IllegalArgumentException e) {
        res.append("Invalid number of caves");
      }
    } else if (this.player.getPlayerArrows() == 0) {
      res.append("You are out of arrows, explore to find more");
    } else {
      this.player.setPlayerArrows(-1);
      switch (direction) {
        case "N":
          for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
              if (player.getLocation() == this.dungeonGrid[i][j].getLocation()) {
                moveArrowNorth(this.dungeonGrid[i][j], distNo, 0);
              }
            }
          }
          break;
        case "S":
          for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
              if (player.getLocation() == this.dungeonGrid[i][j].getLocation()) {
                moveArrowSouth(this.dungeonGrid[i][j], distNo, 0);
              }
            }
          }
          break;
        case "E":
          for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
              if (player.getLocation() == this.dungeonGrid[i][j].getLocation()) {
                moveArrowEast(this.dungeonGrid[i][j], distNo, 0);
              }
            }
          }
          break;
        case "W":
          for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
              if (player.getLocation() == this.dungeonGrid[i][j].getLocation()) {
                moveArrowWest(this.dungeonGrid[i][j], distNo, 0);
              }
            }
          }
          break;
        default:
          res.append("Invalid direction");
          break;
      }
      int count = 0;
      for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.columns; j++) {
          if (this.dungeonGrid[i][j].getMonster() != null) {
            /*count++;
            if(count==0) {
              System.out.println("No monsters present");
            }*/
            //}
            if (this.dungeonGrid[i][j].getMonster().getHealth() == 50) {
              k = "You injured a monster!";
            }
            if (this.dungeonGrid[i][j].getMonster().getHealth() == 100) {
              k = "You shoot an arrow into the darkness!";
            }
            if (this.dungeonGrid[i][j].getMonster().getHealth() == 0) {
              k = "You killed a monster!";
            }

            /*if (this.dungeonGrid[i][j].getMonster().getHealth() == 50) {
              k = "--ref-killed some monster";
            } else if (this.dungeonGrid[i][j].getMonster().getHealth() == 100) {
              k = "You shoot an arrow into the darkness";
            }
          } else if(this.dungeonGrid[i][j].getMonster() == null) {
            k="You killed the monster";
          }*/
          }
        }
      }
    }
    res.append(k);
    return res.toString();
  }

  private void moveArrowNorth(Cave cave, int distance, int cavesCrossed) {
    while (cave.getNorth() != null) {
      cave = cave.getNorth();
      cavesCrossed = arrowTraveller(cave, distance, cavesCrossed);
      if (cavesCrossed == distance) {
        return;
      }
    }
    changeArrowDirectionToHorizontal(cave, distance, cavesCrossed);
  }

  private void moveArrowSouth(Cave cave, int distance, int cavesCrossed) {
    while (cave.getSouth() != null) {
      cave = cave.getSouth();
      cavesCrossed = arrowTraveller(cave, distance, cavesCrossed);
      if (cavesCrossed == distance) {
        return;
      }
    }
    changeArrowDirectionToHorizontal(cave, distance, cavesCrossed);
  }

  private void moveArrowEast(Cave cave, int distance, int cavesCrossed) {
    while (cave.getEast() != null) {
      cave = cave.getEast();
      cavesCrossed = arrowTraveller(cave, distance, cavesCrossed);
      if (cavesCrossed == distance) {
        return;
      }
    }
    changeArrowDirectionToVertical(cave, distance, cavesCrossed);
  }

  private void moveArrowWest(Cave cave, int distance, int cavesCrossed) {
    while (cave.getWest() != null) {
      cave = cave.getWest();
      cavesCrossed = arrowTraveller(cave, distance, cavesCrossed);
      if (cavesCrossed == distance) {
        return;
      }
    }
    changeArrowDirectionToVertical(cave, distance, cavesCrossed);
  }

  private int arrowTraveller(Cave cave, int distance, int cavesCrossed) {
    if (cave.isCave()) {
      cavesCrossed++;
    }
    if (cavesCrossed == distance) {
      if (cave.getMonster() != null) {
        int health = cave.getMonster().getHealth();
        cave.getMonster().setHealth(health == 100 ? 50 : 0);
        if (cave.getMonster().getHealth() == 0) {
          /*for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
              if(this.dungeonGrid[i][j].getId()==cave.getId()) {
                this.dungeonGrid[i][j].getMonster().setHealth(50);
              }
            }
            }*/
          System.out.println("You killed a monster!");
          int caveNum = cave.getId();
          cave.setMonster(null);
          cave.setMonsterPresent(false);
          removeSmell(cave);
        }
      }
    }
    return cavesCrossed;
  }

  private void changeArrowDirectionToVertical(Cave cave, int distance, int cavesCrossed) {
    if (!cave.isCave()) {
      if (cave.getNorth() != null) {
        moveArrowNorth(cave, distance, cavesCrossed);
      } else if (cave.getSouth() != null) {
        moveArrowSouth(cave, distance, cavesCrossed);
      }
    }
  }

  private void changeArrowDirectionToHorizontal(Cave cave, int distance, int cavesCrossed) {
    if (!cave.isCave()) {
      if (cave.getEast() != null) {
        moveArrowEast(cave, distance, cavesCrossed);
      } else if (cave.getWest() != null) {
        moveArrowWest(cave, distance, cavesCrossed);
      }
    }
  }

  private void removeSmell(Cave caveNo) {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getId() == caveNo.getId()) {
          if (this.dungeonGrid[i][j].getNorth() != null) {
            this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            if (this.dungeonGrid[i][j].getNorth().getNorth() != null
                && this.dungeonGrid[i][j].getNorth().getNorth().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getNorth().getSouth() != null
                && this.dungeonGrid[i][j].getNorth().getSouth().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getNorth().getWest() != null
                && this.dungeonGrid[i][j].getNorth().getWest().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getNorth().getWest() != null
                && this.dungeonGrid[i][j].getNorth().getWest().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
          }
          if (this.dungeonGrid[i][j].getSouth() != null) {
            this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            if (this.dungeonGrid[i][j].getSouth().getNorth() != null
                && this.dungeonGrid[i][j].getSouth().getNorth().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getSouth().getSouth() != null
                && this.dungeonGrid[i][j].getSouth().getSouth().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getSouth().getWest() != null
                && this.dungeonGrid[i][j].getSouth().getWest().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getSouth().getWest() != null
                && this.dungeonGrid[i][j].getSouth().getWest().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
          }

          if (this.dungeonGrid[i][j].getWest() != null) {
            this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            if (this.dungeonGrid[i][j].getWest().getNorth() != null
                && this.dungeonGrid[i][j].getWest().getNorth().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getWest().getSouth() != null
                && this.dungeonGrid[i][j].getWest().getSouth().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getWest().getEast() != null
                && this.dungeonGrid[i][j].getWest().getEast().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getWest().getWest() != null
                && this.dungeonGrid[i][j].getWest().getWest().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
          }
          if (this.dungeonGrid[i][j].getEast() != null) {
            this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            if (this.dungeonGrid[i][j].getEast().getNorth() != null
                && this.dungeonGrid[i][j].getEast().getNorth().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getEast().getSouth() != null
                && this.dungeonGrid[i][j].getEast().getSouth().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getEast().getWest() != null
                && this.dungeonGrid[i][j].getEast().getWest().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
            if (this.dungeonGrid[i][j].getEast().getEast() != null
                && this.dungeonGrid[i][j].getEast().getEast().getPungent() == Smell.LESSPUNGENT) {
              this.dungeonGrid[i][j].setPungent(Smell.NOPUNGENT);
            }
          }
        }
      }
    }
  }

  public void assignThief() {
    int count = 0;
    //int val = random.getRandomNumberInRange(0,this.rows*this.columns,0);
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        int val = random.getRandomNumberInRange(0,this.rows*this.columns,0);
        if(this.dungeonGrid[i][j].getId()==val) {
          if(!this.dungeonGrid[i][j].isMonsterPresent()) {
            this.dungeonGrid[i][j].setThiefPresent(true);
            count++;
          }
        }
      }
    }
  }

}







