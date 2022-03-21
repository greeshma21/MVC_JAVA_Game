package gui.view;

import dungeon.Dungeon;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class GameView extends JFrame implements ActionListener, KeyListener {

  private Dungeon dungeon;
  private GamePanel gamePanel;
  private JScrollPane scrollPane;
  private JPanel optionPanel;
  private JButton playerButton;
  private JButton exitButton;
  private JPanel startPanel;

  public GameView(Dungeon dungeon) {
    super("GUI Dungeon");
    this.dungeon=dungeon;
    this.setSize(500, 500);
    //this.setLocation(100,100);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.startPanel = new JPanel(new FlowLayout());
    this.gamePanel=new GamePanel(dungeon);
    this.startPanel.add(this.gamePanel,BorderLayout.CENTER);
    scrollPane = new JScrollPane(this.startPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    add(scrollPane);
    //add(startPanel);
    this.optionPanel = new JPanel();
    this.playerButton = new JButton("Player Description");
    this.exitButton = new JButton("Exit");
    add(optionPanel,BorderLayout.PAGE_END);
    optionPanel.add(playerButton);
    optionPanel.add(exitButton);
    this.playerButton.addActionListener(this);
    this.exitButton.addActionListener(this);
    setFocusable(true);
    addKeyListener(this);
    display();
  }
  public void display() {
    //System.out.println("======"+this.dungeon.getStartCave());
    gamePanel.setImagesOnPanel();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
   if(e.getActionCommand().equals("Player Description")) {
     JFrame f = new JFrame();
     String z="You are currently at: ";
     z+=dungeon.player.getLocation();
     JOptionPane.showMessageDialog(f,z);
     this.gamePanel.refresh();
   }
   if(e.getActionCommand().equals("Exit")) {
     System.exit(0);
   }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    //System.out.println("==KeyPressed");
   //this.gamePanel.refresh();
    if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
      dungeon.move("E");
      this.gamePanel.refresh();
    }
    if(e.getKeyCode() == KeyEvent.VK_LEFT) {
      dungeon.move("W");
      this.gamePanel.refresh();
    }
    if(e.getKeyCode() == KeyEvent.VK_UP) {
      dungeon.move("N");
      this.gamePanel.refresh();
    }
    if(e.getKeyCode() == KeyEvent.VK_DOWN) {
      dungeon.move("S");
      this.gamePanel.refresh();
    }
    if(e.getKeyChar()=='A') {
      dungeon.pickUpArrows();
      this.gamePanel.refresh();
    }
    if(e.getKeyChar()=='T') {
      dungeon.pickUpTreasure();
      this.gamePanel.refresh();
    }
//    if(e.getKeyChar()=='K') {
     /* String k = this.gamePanel.openPopUp();
      //System.out.println(k);
      int caveNum = Integer.parseInt(k);*/
      StringBuilder s= new StringBuilder("");
      /*if(e.getKeyChar()=='W') {
        s.append("W");
      }
      if(e.getKeyChar()=='E') {
        s.append("E");
      }
      if(e.getKeyChar()=='N') {
        s.append("N");
      }
      if(e.getKeyChar()=='S') {
        s.append("S");
      }*/
    String k ="";
    int caveNum;
    String z="";
          if(e.isAltDown() && e.getKeyChar()=='W') {
            s.append("W");
            k = this.gamePanel.openPopUp();
            caveNum = Integer.parseInt(k);
            //System.out.println("CaveNum"+caveNum+"dir"+s);
            z=dungeon.shootArrow(s.toString(),k);
            //System.out.println(z);
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f,z);
            this.gamePanel.refresh();
          }
          if(e.isAltDown() && e.getKeyChar()=='E') {
            s.append("E");
            k = this.gamePanel.openPopUp();
            caveNum = Integer.parseInt(k);
            //System.out.println("CaveNum"+caveNum+"dir"+s);
            z=dungeon.shootArrow(s.toString(),k);
            //System.out.println(z);
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f,z);
            this.gamePanel.refresh();
          }
          if(e.isAltDown() && e.getKeyChar()=='N') {
            s.append("N");
            k = this.gamePanel.openPopUp();
            caveNum = Integer.parseInt(k);
            //System.out.println("CaveNum"+caveNum+"dir"+s);
            z=dungeon.shootArrow(s.toString(),k);
            //System.out.println(z);
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f,z);
            this.gamePanel.refresh();
          }
          if(e.isAltDown() && e.getKeyChar()=='S') {
            s.append("S");
            k = this.gamePanel.openPopUp();
            caveNum = Integer.parseInt(k);
            //System.out.println("CaveNum"+caveNum+"dir"+s);
            z=dungeon.shootArrow(s.toString(),k);
            //System.out.println(z);
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f,z);
            this.gamePanel.refresh();
          }
      //System.out.println(k);



      //System.out.println(k);


    }
  //}

  @Override
  public void keyReleased(KeyEvent e) {

  }


}
