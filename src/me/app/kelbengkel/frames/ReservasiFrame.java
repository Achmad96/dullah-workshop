package me.app.kelbengkel.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import me.app.kelbengkel.App;
import me.app.kelbengkel.helpers.ReservasiHelper;
import me.app.kelbengkel.utils.MessageUtil;

public class ReservasiFrame extends JFrame implements FrameBase, ActionListener, MouseListener {

  private ArrayList<Object[]> dataList;
  private ArrayList<Object[]> dataMechanics;
  private ReservasiHelper reservasiPerbaikanHelper;

  public ReservasiFrame() {
    dataMechanics = new ArrayList<>();
    dataList = new ArrayList<>();
    reservasiPerbaikanHelper = new ReservasiHelper();
    initComponents();
    initMekanik();
    loadData();
  }

  private void initMekanik() {
    try {
      final ResultSet resultSet = reservasiPerbaikanHelper.getMekanik();
      while (resultSet.next()) {
        Object[] data = { resultSet.getString("id_mekanik"), resultSet.getString("nama_mekanik") };
        dataMechanics.add(data);
        cbMekanik.addItem(data[0].toString());
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  private void initComponents() {
    riwayatoren = new JLabel();
    jScrollPane2 = new JScrollPane();
    tblReservasi = new JTable();
    tfKendaraan = new JTextField();
    cbMekanik = new JComboBox<>();
    Jenis_service = new JLabel();
    lblMekanik = new JLabel();
    lblKendaraan = new JLabel();
    btnReservasi = new JButton();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48)); // NOI18N
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("Reservasi");
    add(riwayatoren, new AbsoluteConstraints(390, 40, -1, -1));

    tblReservasi.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "No_antrian", "ID_kendaraan", "ID_mekanik" }));
    jScrollPane2.setViewportView(tblReservasi);

    add(jScrollPane2, new AbsoluteConstraints(700, 170, 490, 480));
    add(tfKendaraan, new AbsoluteConstraints(320, 220, 180, -1));

    cbMekanik.setModel(new DefaultComboBoxModel<>(new String[] {}));
    add(cbMekanik, new AbsoluteConstraints(320, 260, -1, -1));

    Jenis_service.setFont(new Font("Dialog", 0, 14)); // NOI18N
    Jenis_service.setText("Antrian");
    add(Jenis_service, new AbsoluteConstraints(700, 140, -1, -1));

    lblMekanik.setFont(new Font("Dialog", 0, 14)); // NOI18N
    lblMekanik.setText("ID Mekanik");
    lblMekanik.setForeground(new Color(255, 255, 255));
    add(lblMekanik, new AbsoluteConstraints(190, 260, -1, -1));

    lblKendaraan.setFont(new Font("Dialog", 0, 14)); // NOI18N
    lblKendaraan.setText("ID Kendaraan");
    lblKendaraan.setForeground(new Color(255, 255, 255));
    add(lblKendaraan, new AbsoluteConstraints(190, 220, -1, -1));

    btnReservasi.setText("Buat Reservasi");
    btnReservasi.addActionListener(this);
    add(btnReservasi, new AbsoluteConstraints(180, 450, 380, -1));

    jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/MekanikTiktok.png"))); // NOI18N
    jLabel3.setText("jLabel3");
    jLabel3.addMouseListener(this);
    add(jLabel3, new AbsoluteConstraints(-30, 70, 200, -1));

    jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Riwayat_service.png"))); // NOI18N
    jLabel1.setText("jLabel1");
    add(jLabel1, new AbsoluteConstraints(0, 0, 1250, -1));
  }

  private JLabel Jenis_service;
  private JButton btnReservasi;
  private JComboBox<String> cbMekanik;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JScrollPane jScrollPane2;
  private JLabel lblKendaraan;
  private JLabel lblMekanik;
  private JLabel riwayatoren;
  private JTable tblReservasi;
  private JTextField tfKendaraan;

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == btnReservasi) {
      btnReservasiActionPerformed(event);
    }
  }

  private void btnReservasiActionPerformed(ActionEvent event) {
    final HashMap<String, Object> parameters = processInput();
    if (parameters == null) {
      return;
    }
    if (reservasiPerbaikanHelper.insertData(null, parameters)) {
      MessageUtil.showSucessMessageDialog(this, "Reservasi berhasil dibuat!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Reservasi gagal dibuat!");
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    if (event.getSource() == jLabel3) {
      App.getPanelSwitcher().back();
    }
  }

  @Override
  public void mousePressed(MouseEvent event) {}

  @Override
  public void mouseReleased(MouseEvent event) {}

  @Override
  public void mouseEntered(MouseEvent event) {}

  @Override
  public void mouseExited(MouseEvent event) {}

  @Override
  public void loadData() {
    dataList.clear();
    try {
      final ResultSet resultSet = reservasiPerbaikanHelper.getAllData();
      while (resultSet.next()) {
        final Object[] data = new Object[3];
        data[0] = resultSet.getString("id_reservasi");
        data[1] = resultSet.getString("id_kendaraan");
        data[2] = resultSet.getString("id_mekanik");
        dataList.add(data);
      }
      tblReservasi.setModel(getUpdatedModel(tblReservasi, dataList));
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void clearInput() {}

  @Override
  public HashMap<String, Object> processInput() {
    final String idKendaraan = tfKendaraan.getText().trim();
    final String idMekanik = dataMechanics.get(cbMekanik.getSelectedIndex())[0].toString();
    final HashMap<String, Object> parameters = new HashMap<>();
    parameters.put("id_kendaraan", idKendaraan);
    parameters.put("id_mekanik", idMekanik);
    return parameters;
  }
}
