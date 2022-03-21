package gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class ButtonListener implements ActionListener {

  private Map<String, Runnable> buttonToObj;


  protected void setButtonListenerMap(Map<String, Runnable> buttonToObj) {
    this.buttonToObj = buttonToObj;

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonToObj.containsKey(e.getActionCommand())) {
      buttonToObj.get(e.getActionCommand()).run();
    }


  }

}
