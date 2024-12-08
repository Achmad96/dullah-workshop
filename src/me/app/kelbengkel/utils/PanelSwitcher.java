package me.app.kelbengkel.utils;

import java.util.Stack;

import javax.swing.JFrame;

import java.awt.Container;

public class PanelSwitcher {
  private final Stack<Container> panels;
  private final JFrame frame;

  public PanelSwitcher(JFrame frame) {
    this.frame = frame;
    panels = new Stack<>();
  }

  public void openNewFrame(JFrame frame) {
    panels.push(frame);
    frame.setVisible(true);
    frame.pack();
    frame.setLocationRelativeTo(null);
  }

  public void switchPanelTo(Container panel) {
    panels.push(panel);
    frame.setContentPane(panel);
    frame.pack();
    frame.setLocationRelativeTo(null);
  }

  public void back() {
    if (panels.lastElement() instanceof JFrame) {
      ((JFrame) panels.lastElement()).dispose();
      panels.pop();

      final JFrame lastFrame = (JFrame) panels.lastElement();
      lastFrame.setVisible(true);
      lastFrame.pack();
      lastFrame.setLocationRelativeTo(lastFrame.getOwner());
      return;
    }
    panels.pop();
    frame.setContentPane(panels.lastElement());
    frame.pack();
    frame.setLocationRelativeTo(null);
  }
}
