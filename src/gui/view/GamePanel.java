package gui.view;

import dungeon.Dungeon;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class GamePanel extends JPanel implements KeyListener {

  private JLabel test;
  private Dungeon dungeon;
  //  BufferedImage cave;
  private JPanel optionPanel;
  private JButton moveButton;
  private JButton exitButton;
  Container container;
  JFrame frame;
  JLabel picLabel;

  public GamePanel(Dungeon dungeon) {
    this.dungeon = dungeon;
    this.test = new JLabel("Welcome to the Dungeon Game!");
    GridLayout layout = new GridLayout(dungeon.getRows(), dungeon.getColumns(), 0, 0);
    setLayout(layout);
    this.addKeyListener(this);

  }

  private void addComponents() {
    //add(test);
    //add(picLabel);
    //add(container);
    add(optionPanel, BorderLayout.LINE_END);
    optionPanel.add(moveButton);
    optionPanel.add(exitButton);
  }

  private void display() {
    //System.out.println(dungeon.getStartCave());
  }

  public void setImagesOnPanel() {
    //System.out.println(dungeon.getRows());
    int caveNum = dungeon.getStartCave().getId();
    try {
      for (int i = 0; i < dungeon.getRows(); i++) {
        for (int j = 0; j < dungeon.getColumns(); j++) {
          BufferedImage updatedCave = null;
          BufferedImage caveAdd = null;
          BufferedImage cave = null;
          BufferedImage playerImg = null;
          String cavePicName = "";
          int count = 0;
          if (dungeon.getDungeonGrid()[i][j].getNorth() != null) {
            cavePicName = cavePicName + "N";
          }
          if (dungeon.getDungeonGrid()[i][j].getSouth() != null) {
            cavePicName = cavePicName + "S";
          }
          if (dungeon.getDungeonGrid()[i][j].getEast() != null) {
            cavePicName = cavePicName + "E";
          }
          if (dungeon.getDungeonGrid()[i][j].getWest() != null) {
            cavePicName = cavePicName + "W";
          }
          cavePicName = cavePicName + ".png";
          cavePicName = "res/" + cavePicName;

//          picLabel = new JLabel(new ImageIcon(cave));
//          cave = ImageIO.read(this.getClass().getResource("res/black.png"));
          cave = ImageIO.read(this.getClass().getResource(cavePicName));
          updatedCave = cave;

          if (dungeon.getDungeonGrid()[i][j].isMonsterPresent()) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/otyugh.png"));
            caveAdd = resize(caveAdd, 0.5);
            updatedCave = overlayImg(cave, caveAdd, "otyugh");
          }

          if (dungeon.getDungeonGrid()[i][j].getTreasureList().size() > 0) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/diamond.png"));
            caveAdd = resize(caveAdd, 0.5);
            updatedCave = overlayImg(cave, caveAdd, "diamond");
          }

          if (dungeon.getDungeonGrid()[i][j].getArrows() > 0) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/arrow-white.png"));
            caveAdd = resize(caveAdd, 0.5);
            updatedCave = overlayImg(cave, caveAdd, "arrow-white");
          }

          if (dungeon.getDungeonGrid()[i][j].isThiefPresent()) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/thief.png"));
            caveAdd = resize(caveAdd, 0.1);
            updatedCave = overlayImg(cave, caveAdd, "thief");
          }

          if (count == 0 && dungeon.getDungeonGrid()[i][j].getId() == caveNum) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/player.png"));
            caveAdd = resize(caveAdd, 0.05);
            updatedCave = overlayImg(cave, caveAdd, "player");
            count++;
          }

          picLabel = new JLabel(new ImageIcon(updatedCave));
          add(picLabel);

        }
      }
      //cave = ImageIO.read(this.getClass().getResource("res/black.png"));
    } catch (IOException e) {
      return;
    }

  }

  private BufferedImage overlayImg(BufferedImage background, BufferedImage foreground,
      String images) {
    Graphics2D g = background.createGraphics();
    //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawImage(background, 0, 0, null);
    if (images.equals("otyugh")) {
      g.drawImage(foreground, 15, 20, null);
    }
    if (images.equals("diamond")) {
      g.drawImage(foreground, 40, 25, null);
    }
    if (images.equals("arrow-white")) {
      g.drawImage(foreground, 35, 30, null);
    }
    if (images.equals("player")) {
      g.drawImage(foreground, 20, 30, null);
    }
    if (images.equals("thief")) {
      g.drawImage(foreground, 15, 20, null);
    }
    g.dispose();
    return background;
  }

  private BufferedImage resize(BufferedImage img, double percent) {
    int scaledWidth = (int) (img.getWidth() * percent);
    int scaledHeight = (int) (img.getHeight() * percent);
    BufferedImage finalImg = new BufferedImage(scaledWidth, scaledHeight, img.getType());
    Graphics2D g2d = finalImg.createGraphics();
    g2d.drawImage(img, 0, 0, scaledWidth, scaledHeight, null);
    g2d.dispose();
    return finalImg;
  }


  @Override
  public void keyTyped(KeyEvent e) {
    //System.out.println("KeyPressed");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("KeyPressed");
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  public void refresh() {
    //System.out.println("refresh");
    removeAll();
    revalidate();
    repaint();
    try {
      for (int i = 0; i < dungeon.getRows(); i++) {
        for (int j = 0; j < dungeon.getColumns(); j++) {
          BufferedImage updatedCave = null;
          BufferedImage caveAdd = null;
          BufferedImage cave = null;
          BufferedImage playerImg = null;
          String cavePicName = "";
          int count = 0;
          if (dungeon.getDungeonGrid()[i][j].getNorth() != null) {
            cavePicName = cavePicName + "N";
          }
          if (dungeon.getDungeonGrid()[i][j].getSouth() != null) {
            cavePicName = cavePicName + "S";
          }
          if (dungeon.getDungeonGrid()[i][j].getEast() != null) {
            cavePicName = cavePicName + "E";
          }
          if (dungeon.getDungeonGrid()[i][j].getWest() != null) {
            cavePicName = cavePicName + "W";
          }
          cavePicName = cavePicName + ".png";
          cavePicName = "res/" + cavePicName;

//          picLabel = new JLabel(new ImageIcon(cave));
//          cave = ImageIO.read(this.getClass().getResource("res/black.png"));
          cave = ImageIO.read(this.getClass().getResource(cavePicName));
          updatedCave = cave;

          if (dungeon.getDungeonGrid()[i][j].isMonsterPresent()) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/otyugh.png"));
            caveAdd = resize(caveAdd, 0.5);
            updatedCave = overlayImg(cave, caveAdd, "otyugh");
          }

          if (dungeon.getDungeonGrid()[i][j].getTreasureList().size() > 0) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/diamond.png"));
            caveAdd = resize(caveAdd, 0.5);
            updatedCave = overlayImg(cave, caveAdd, "diamond");
          }

          if (dungeon.getDungeonGrid()[i][j].getArrows() > 0) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/arrow-white.png"));
            caveAdd = resize(caveAdd, 0.5);
            updatedCave = overlayImg(cave, caveAdd, "arrow-white");
          }

          if (dungeon.getDungeonGrid()[i][j].isThiefPresent()) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/thief.png"));
            caveAdd = resize(caveAdd, 0.1);
            updatedCave = overlayImg(cave, caveAdd, "thief");
          }

          int tempRow = dungeon.getDungeonGrid()[i][j].getRow();
          int tempCol = dungeon.getDungeonGrid()[i][j].getColumn();
          int tempPlayerRow = dungeon.player.getLocation().getRow();
          int tempPlayerCol = dungeon.player.getLocation().getCol();

          if (tempRow == tempPlayerRow && tempCol == tempPlayerCol) {
            caveAdd = ImageIO.read(this.getClass().getResource("res/player.png"));
            caveAdd = resize(caveAdd, 0.05);
            updatedCave = overlayImg(cave, caveAdd, "player");
          }

          picLabel = new JLabel(new ImageIcon(updatedCave));
          add(picLabel);

        }
      }
    } catch (IOException e) {
      return;
    }
  }

  public String openPopUp() {
    JFrame f = new JFrame();
    ImageIcon icon = new ImageIcon();
    Object[] possibilities = {"1", "2", "3", "4", "5"};
    String s = (String) JOptionPane.showInputDialog(
        frame, "Enter the number of caves to shoot", "Shoot Number", JOptionPane.PLAIN_MESSAGE,
        icon, possibilities, "1");
    return s;
  }


}
