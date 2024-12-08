package me.app.kelbengkel;

import javax.swing.JFrame;
import me.app.kelbengkel.panels.LoginPanel;
import me.app.kelbengkel.utils.DatabaseConnection;
import me.app.kelbengkel.utils.PanelSwitcher;

public class App {
  private static JFrame appFrame;
  private static LoginPanel loginPanel;
  private static PanelSwitcher panelSwitcher;

  public static void main(String[] args) {
    appFrame = new JFrame("Dullah Workshop");
    loginPanel = new LoginPanel();
    panelSwitcher = new PanelSwitcher(appFrame);
    DatabaseConnection.getConnection();
    App.run();
  }

  private static void run() {
    appFrame.setContentPane(loginPanel);
    appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    appFrame.setVisible(true);
    appFrame.pack();
    appFrame.setResizable(false);
    appFrame.setLocationRelativeTo(null);
  }

  public static PanelSwitcher getPanelSwitcher() {
    return panelSwitcher;
  }
}