package me.app.kelbengkel.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import me.app.kelbengkel.App;
import me.app.kelbengkel.helpers.AuthHelper;
import me.app.kelbengkel.utils.MessageUtil;

import static me.app.kelbengkel.utils.ValidationUtil.isValidEmail;
import static me.app.kelbengkel.utils.ValidationUtil.isValidPassword;

public class SignupPanel extends JPanel implements ActionListener {

  private AuthHelper authHelper;

  public SignupPanel() {
    initComponents();
    authHelper = new AuthHelper();
  }

  private void initComponents() {
    tfNama = new JTextField();
    tfEmail = new JTextField();
    tfPassword = new JPasswordField();
    tfNotelp = new JTextField();
    tfAlamat = new JTextField();
    btnRegister = new JButton();
    btnLogin = new JButton();
    jLabel2 = new JLabel();

    setPreferredSize(new Dimension(936, 666));

    tfNama.setBackground(new Color(255, 255, 255));
    tfNama.setForeground(new Color(0, 0, 0));
    tfNama.setBorder(null);

    tfEmail.setBackground(new Color(255, 255, 255));
    tfEmail.setForeground(new Color(0, 0, 0));
    tfEmail.setBorder(null);

    tfPassword.setBackground(new Color(255, 255, 255));
    tfPassword.setForeground(new Color(0, 0, 0));
    tfPassword.setBorder(null);

    tfNotelp.setBackground(new Color(255, 255, 255));
    tfNotelp.setForeground(new Color(0, 0, 0));
    tfNotelp.setBorder(null);

    tfAlamat.setBackground(new Color(255, 255, 255));
    tfAlamat.setForeground(new Color(0, 0, 0));
    tfAlamat.setHorizontalAlignment(JTextField.LEFT);
    tfAlamat.setBorder(null);

    btnRegister.setBorder(null);
    btnRegister.setContentAreaFilled(false);
    btnRegister.setDefaultCapable(false);
    btnRegister.addActionListener(this);

    btnLogin.setBorder(null);
    btnLogin.setContentAreaFilled(false);
    btnLogin.setDefaultCapable(false);
    btnLogin.addActionListener(this);

    jLabel2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Register.png")));
    jLabel2.setText("jLabel2");

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addGap(340, 340, 340).addComponent(tfNama, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(340, 340, 340).addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(340, 340, 340).addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(340, 340, 340).addComponent(tfNotelp, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(340, 340, 340).addComponent(tfAlamat, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(510, 510, 510).addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup().addGap(520, 520, 520).addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)).addComponent(jLabel2));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addGap(210, 210, 210).addComponent(tfNama, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addGap(30, 30, 30)
            .addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addGap(40, 40, 40).addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
            .addGap(40, 40, 40).addComponent(tfNotelp, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addGap(40, 40, 40)
            .addComponent(tfAlamat, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(30, 30, 30).addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
            .addGap(25, 25, 25).addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
        .addComponent(jLabel2));
  }

  private JButton btnRegister;
  private JButton btnLogin;
  private JLabel jLabel2;
  private JTextField tfAlamat;
  private JTextField tfEmail;
  private JTextField tfNama;
  private JTextField tfNotelp;
  private JPasswordField tfPassword;
  private JPanel loginPanel;

  private void buttonSignupActionPerformed(ActionEvent event) {
    final ArrayList<String> parameters = new ArrayList<>();
    final String email = tfEmail.getText().trim();
    final String password = String.valueOf(tfPassword.getPassword());
    final String name = tfNama.getText().trim();
    final String alamat = tfAlamat.getText().trim();
    final String notelp = tfNotelp.getText().trim();
    parameters.add(password);
    parameters.add(name);
    parameters.add(alamat);
    parameters.add(notelp);
    if (isDataValid() instanceof Integer) {
      MessageUtil.showSucessMessageDialog(this, getValidationError((Integer) isDataValid()));
      return;
    }
    authHelper.insertData(email, parameters);
    MessageUtil.showSucessMessageDialog(this, "Berhasil membuat akun!");
  }

  private void buttonLoginActionPerformed(ActionEvent event) {
    if (loginPanel == null) {
      loginPanel = new LoginPanel();
    }
    App.getPanelSwitcher().switchPanelTo(loginPanel);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == btnLogin) {
      buttonLoginActionPerformed(event);
    } else if (event.getSource() == btnRegister) {
      buttonSignupActionPerformed(event);
    }
  }

  private String getValidationError(int index) {
    switch (index) {
    case 0:
      return "Nama harus terdiri dari 5 karakter";
    case 1:
      return "Email tidak valid";
    case 2:
      return "Password harus terdiri dari 8 karakter dengan kombinasi huruf dan angka";
    case 3:
      return "Alamat harus lebih dari 10 karakter";
    case 4:
      return "Nomor telepon harus terdiri dari 10-12 angka";
    }
    return "";
  }

  private Object isDataValid() {
    Object result = true;
    final String email = tfEmail.getText().trim();
    final String name = tfNama.getText().trim();
    if (name.length() < 5) {
      result = 0;
      return result;
    }
    if (!isValidEmail(email)) {
      result = 1;
      return result;
    }
    final String password = String.valueOf(tfPassword.getPassword());
    if (!isValidPassword(password)) {
      result = 2;
      return result;
    }
    final String alamat = tfAlamat.getText().trim();
    if (alamat.length() < 10) {
      result = 3;
      return result;
    }
    final String notelp = tfNotelp.getText().trim();
    if (notelp.length() < 10 || notelp.length() > 12) {
      result = 4;
      return result;
    }
    return result;
  }
}
