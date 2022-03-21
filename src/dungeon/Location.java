package dungeon;

/**
 * A class that defines the location of the cave and player in the grid in terms of rows and
 * columns.
 */
public class Location {

  private int row;
  private int col;

  /**
   * Constructs the location object in terms of rows and columns.
   *
   * @param row the row number.
   * @param col the column number.
   */
  public Location(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  @Override
  public String toString() {
    return
        "<" + row
            + ","
            + col
            + ">";
  }
}
