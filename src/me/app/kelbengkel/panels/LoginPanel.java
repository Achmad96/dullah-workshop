package me.app.kelbengkel.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import me.app.kelbengkel.frames.Dashboard;
import me.app.kelbengkel.helpers.AuthHelper;
import me.app.kelbengkel.App;
import me.app.kelbengkel.utils.MessageUtil;

public class LoginPanel extends JPanel implements ActionListener, KeyListener {

  private AuthHelper authHelper;
  private Dashboard dashboard;

  public LoginPanel() {
    initComponents();
    authHelper = new AuthHelper();
  }

  private void initComponents() {
    btnLogin = new JButton();
    tfEmail = new JTextField();
    tfPassword = new JPasswordField();
    btnRegister = new JButton();
    jLabel2 = new JLabel();

    btnLogin.setOpaque(false);
    btnLogin.setContentAreaFilled(false);
    btnLogin.setBorder(null);
    btnLogin.setBorderPainted(false);
    btnLogin.setContentAreaFilled(false);
    btnLogin.addActionListener(this);

    tfEmail.setBackground(new Color(255, 255, 255));
    tfEmail.setForeground(new Color(0, 0, 0));
    tfEmail.setBorder(null);
    tfEmail.addKeyListener(this);

    tfPassword.setBackground(new Color(255, 255, 255));
    tfPassword.setForeground(new Color(0, 0, 0));
    tfPassword.setBorder(null);
    tfPassword.addKeyListener(this);

    btnRegister.setToolTipText("");
    btnRegister.setBorder(null);
    btnRegister.setBorderPainted(false);
    btnRegister.setFocusPainted(false);
    btnRegister.setOpaque(false);
    btnRegister.setContentAreaFilled(false);
    btnRegister.addActionListener(this);

    jLabel2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Login.png")));
    jLabel2.setText("jLabel2");

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addGap(390, 390, 390).addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(590, 590, 590).addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(570, 570, 570).addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(390, 390, 390).addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 1080, GroupLayout.PREFERRED_SIZE));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addGap(400, 400, 400).addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addGap(100, 100, 100).addComponent(btnLogin,
            GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(590, 590, 590).addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(470, 470, 470).addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)).addComponent(jLabel2));
  }

  private void btnSignupActionPerformed(ActionEvent event) {
    if (signUPanel == null) {
      signUPanel = new SignupPanel();
    }
    App.getPanelSwitcher().switchPanelTo(signUPanel);
  }

  private void btnLoginActionPerformed(ActionEvent event) {
    try {
      final String email = tfEmail.getText().trim();
      final String password = String.valueOf(tfPassword.getPassword());
      final ResultSet resultSet = authHelper.getValidationData(email, password);
      final boolean isValid = resultSet != null && resultSet.next();
      if (!isValid) {
        MessageUtil.showErrorMessageDialog(this, "Email atau password salah!");
        return;
      }
      final String name = resultSet.getString("nama");
      if (dashboard == null) {
        dashboard = new Dashboard();
      }
      App.getPanelSwitcher().openNewFrame(dashboard);
      MessageUtil.showSucessMessageDialog(dashboard, "Selamat datang " + name + "!");
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  private JButton btnLogin;
  private JButton btnRegister;
  private JLabel jLabel2;
  private JTextField tfEmail;
  private JPasswordField tfPassword;
  private JPanel signUPanel;

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == btnRegister) {
      btnSignupActionPerformed(event);
    } else if (event.getSource() == btnLogin) {
      btnLoginActionPerformed(event);
    }
  }

  @Override
  public void keyTyped(KeyEvent event) {}

  @Override
  public void keyPressed(KeyEvent event) {
    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
      if (event.getSource() == tfEmail && tfPassword.getPassword().length == 0) {
        tfPassword.requestFocus();
        return;
      }
      if (event.getSource() == tfPassword && tfEmail.getText().isEmpty()) {
        tfEmail.requestFocus();
        return;
      }
      btnLoginActionPerformed(null);
    }
  }

  @Override
  public void keyReleased(KeyEvent event) {}
}
