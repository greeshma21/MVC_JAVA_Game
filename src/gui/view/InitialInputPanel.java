package gui.view;

import dungeon.Dungeon;
import gui.controller.ControllerSwing;
import gui.controller.IControllerSwing;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;


public class InitialInputPanel extends JPanel implements ActionListener {

  private JTextField rows;
  private JTextField columns;
  private JTextField interconnectivity;
  private JTextField cavePercent;
  private JTextField monsterNumber;
  private JLabel test;
  private JComboBox wrappingBar;
  private JLabel wrapText;
  private JButton startGameButton;
  private JMenuBar menuBar;
  IControllerSwing controller;
  IDungeonView view;

  public InitialInputPanel() {
    this.test = new JLabel("Welcome to the Dungeon Game!");
    this.rows = new JTextField("Enter number of columns for the dungeon");
    this.columns = new JTextField("Enter number of columns for the dungeon");
    this.interconnectivity = new JTextField("Enter a value for interconnectivity");
    this.cavePercent = new JTextField("Enter percentage of caves to add treasure");
    this.monsterNumber = new JTextField("Enter number of extra monsters to add to dungeon");
    this.wrapText = new JLabel("Want a wrapping dungeon?");
    String[] s = {"true", "false"};
    this.wrappingBar = new JComboBox(s);
    this.startGameButton = new JButton("Start Game");
    startGameButton.addActionListener(this);
    //this.view=new DungeonView();
    this.controller= new ControllerSwing(view);
    GridLayout layout = new GridLayout(10, 1);
    setLayout(layout);
    addComponents();
  }

  private void addComponents() {
    add(test);
    add(rows);
    add(columns);
    add(interconnectivity);
    add(cavePercent);
    add(monsterNumber);
    add(wrapText);
    add(wrappingBar);
    add(startGameButton);
  }

  public int getRowsFromPanel() {
    return Integer.parseInt(rows.getText());
  }

  public int getColumnsFromPanel() {
    return Integer.parseInt(columns.getText());
  }

  public int getInterconnectivityFromPanel() {
    return Integer.parseInt(interconnectivity.getText());
  }

  public int getCavePercentFromPanel() {
    return Integer.parseInt(cavePercent.getText());
  }

  public int getMonsterNumberFromPanel() {
    return Integer.parseInt(monsterNumber.getText());
  }

  public boolean getWrappingType() {
    String s = (String) wrappingBar.getSelectedItem();
    return s.equals("true");
  }

 /* public void addActionListener(IControllerSwing listener) {
//    startGameButton.addActionListener(listener);


    startGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startGameButton) {
          System.out.println(rows.getText());
        }
      }
    });


  }*/

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startGameButton) {
      int r = getRowsFromPanel();
      int c = getColumnsFromPanel();
      int i = getInterconnectivityFromPanel();
      int caveP = getCavePercentFromPanel();
      int monsterP = getMonsterNumberFromPanel();
      boolean b = getWrappingType();

      /*controller.constructDungeon(getRowsFromPanel(), getColumnsFromPanel(), getWrappingType(),
          getInterconnectivityFromPanel(), getCavePercentFromPanel(), getMonsterNumberFromPanel());*/
      //this.setVisible(false);
      //GamePanel gamePanel = new GamePanel();
      if(validateUserInput()) {
        GameView gameView = new GameView(
            new Dungeon(getRowsFromPanel(), getColumnsFromPanel(), getWrappingType(),
                getInterconnectivityFromPanel(), getCavePercentFromPanel(),
                getMonsterNumberFromPanel()));
      }
      //controller.constructDungeon1();

      /*controller.constructDungeon(r, c, b,
          i, caveP, monsterP);*/
      //controller.constructDungeon1();

    }
  }
  private boolean validateUserInput() {
    try {
      if (getRowsFromPanel() < 3 || getRowsFromPanel() > 10) {
        showErrorPopUp("Enter a valid row");
        return false;
      } else if (getColumnsFromPanel() < 3 || getColumnsFromPanel() > 10) {
        showErrorPopUp("Enter a valid column");
        return false;
      } else if (getInterconnectivityFromPanel() > 40) {
        showErrorPopUp("Enter a valid interconnectivity");
        return false;
      } else if (getCavePercentFromPanel() > 100 || getCavePercentFromPanel() < 0) {
        showErrorPopUp("Enter a valid cave percent");
        return false;
      } else if (getMonsterNumberFromPanel() > 3 || getMonsterNumberFromPanel() < 0) {
        showErrorPopUp("Enter a valid monster number");
        return false;
      } else {
        return true;
      }
    }
    catch (IllegalArgumentException e) {
      showErrorPopUp("Please enter correct details");
      return false;
    }
  }

  private void showErrorPopUp(String errorMsg) {
    JOptionPane optionPane = new JOptionPane(errorMsg, JOptionPane.ERROR_MESSAGE);
    JDialog dialog = optionPane.createDialog("Input error");
    dialog.setAlwaysOnTop(true);
    dialog.setVisible(true);
  }
}
