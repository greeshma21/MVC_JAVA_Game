package gui.view;

import dungeon.IDungeon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class DungeonView extends JFrame implements IDungeonView {

  public InitialInputPanel initialInputPanel;
  private GamePanel gamePanel;
  private JMenuBar menuBar;
  private JMenu options;
  private JMenuItem item1;
  private JMenuItem item2;

  public DungeonView() {
    super("GUI Dungeon");
    menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);
    options = new JMenu("Options");
    menuBar.add(options);
    item1 = new JMenuItem("Start");
    item2 = new JMenuItem("Exit");
    options.add(item1);
    options.add(item2);
    this.add(menuBar);
    item2.addActionListener(e -> System.exit(0));
    this.initialInputPanel = new InitialInputPanel();
    this.add(initialInputPanel);
    this.setSize(500, 500);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void removeInitialPane() {
    System.out.println("Entered remove");
    this.setVisible(false);
  }

  @Override
  public InitialInputPanel getInitialInputPanel() {
    return this.initialInputPanel;
  }

  @Override
  public void test() {
    System.out.println("Hello");
  }

  public void initialGamePanel() {

  }

}
