package gui.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class View1 extends JFrame {
  private InitialInputPanel initialInputPanel;
  private GamePanel gamePanel;

  public View1() {
    initialInputPanel = new InitialInputPanel();
    this.add(initialInputPanel);
    this.setSize(500, 500);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
}
  public void test() {
    System.out.println("Hello");
  }
}
