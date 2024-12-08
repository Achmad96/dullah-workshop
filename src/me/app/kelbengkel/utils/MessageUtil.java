package me.app.kelbengkel.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * @author Achmad Raihan
 */
public class MessageUtil {

  public static void showSucessMessageDialog(Component parent, String message) {
    JOptionPane.showMessageDialog(parent, message, "MESSAGE", JOptionPane.PLAIN_MESSAGE);
  }

  public static void showErrorMessageDialog(Component parent, String message) {
    JOptionPane.showMessageDialog(parent, message, "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
  }

  public static int showConfirmDialog(Component parent, String message) {
    return JOptionPane.showConfirmDialog(parent, message, "Konfirmasi", JOptionPane.YES_NO_OPTION);
  }

  public static int showWarningDialog(Component parent, String message) {
    return JOptionPane.showConfirmDialog(parent, message, "PERINGATAN", JOptionPane.YES_NO_OPTION);
  }
}