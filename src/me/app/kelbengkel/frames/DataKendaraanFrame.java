package me.app.kelbengkel.frames;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import me.app.kelbengkel.App;
import me.app.kelbengkel.helpers.DataKendaraanHelper;
import me.app.kelbengkel.utils.MessageUtil;

import static me.app.kelbengkel.utils.ValidationUtil.isNumber;

public class DataKendaraanFrame extends JFrame implements FrameBase, ActionListener, MouseListener {
  private DataKendaraanHelper dataKendaraanHelper;
  private ArrayList<Object[]> dataList;

  public DataKendaraanFrame() {
    dataList = new ArrayList<>();
    dataKendaraanHelper = new DataKendaraanHelper();
    initComponents();
    loadData();
  }

  private void initComponents() {
    jScrollPane1 = new JScrollPane();
    tblDataKendaraan = new JTable();
    tfNorang = new JTextField();
    tfNosin = new JTextField();
    tfKilometer = new JTextField();
    tfTahun = new JTextField();
    tfModel = new JTextField();
    tfMerk = new JTextField();
    tfNopol = new JTextField();
    tfPelanggan = new JTextField();
    tfKendaraan = new JTextField();
    lblNorang = new JLabel();
    lblNosin = new JLabel();
    lblKilometer = new JLabel();
    lblTahun = new JLabel();
    lblModel = new JLabel();
    lblMerk = new JLabel();
    lblNopol = new JLabel();
    lblPelanggan = new JLabel();
    lblKendaraan = new JLabel();
    riwayatoren = new JLabel();
    btnClear = new JButton();
    btnUpdate = new JButton();
    btnDelete = new JButton();
    btnInsert = new JButton();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    tblDataKendaraan.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id kendaraan", "Id pelanggan", "Plat nomor", "Merk", "Model" }));
    jScrollPane1.setViewportView(tblDataKendaraan);

    add(jScrollPane1, new AbsoluteConstraints(650, 140, 430, 490));
    add(tfNorang, new AbsoluteConstraints(390, 470, 200, -1));
    add(tfNosin, new AbsoluteConstraints(390, 430, 200, -1));
    add(tfKilometer, new AbsoluteConstraints(390, 390, 200, -1));
    add(tfTahun, new AbsoluteConstraints(390, 350, 200, -1));
    add(tfModel, new AbsoluteConstraints(390, 310, 200, -1));
    add(tfMerk, new AbsoluteConstraints(390, 270, 200, -1));
    add(tfNopol, new AbsoluteConstraints(390, 230, 200, -1));
    add(tfPelanggan, new AbsoluteConstraints(390, 190, 200, -1));
    add(tfKendaraan, new AbsoluteConstraints(390, 150, 200, -1));

    lblNorang.setFont(new Font("Montserrat", 0, 14));
    lblNorang.setText("No. Rangka");
    lblNorang.setForeground(new Color(255, 255, 255));
    add(lblNorang, new AbsoluteConstraints(250, 470, -1, -1));

    lblNosin.setFont(new Font("Montserrat", 0, 14));
    lblNosin.setText("No. Mesin");
    lblNosin.setForeground(new Color(255, 255, 255));
    add(lblNosin, new AbsoluteConstraints(250, 430, -1, -1));

    lblKilometer.setFont(new Font("Montserrat", 0, 14));
    lblKilometer.setText("Kilometer");
    lblKilometer.setForeground(new Color(255, 255, 255));
    add(lblKilometer, new AbsoluteConstraints(250, 390, -1, -1));

    lblTahun.setFont(new Font("Montserrat", 0, 14));
    lblTahun.setText("Tahun");
    lblTahun.setForeground(new Color(255, 255, 255));
    add(lblTahun, new AbsoluteConstraints(250, 350, -1, -1));

    lblModel.setFont(new Font("Montserrat", 0, 14));
    lblModel.setText("Model");
    lblModel.setForeground(new Color(255, 255, 255));
    add(lblModel, new AbsoluteConstraints(250, 310, -1, -1));

    lblMerk.setFont(new Font("Montserrat", 0, 14));
    lblMerk.setText("Merk");
    lblMerk.setForeground(new Color(255, 255, 255));
    add(lblMerk, new AbsoluteConstraints(250, 270, -1, -1));

    lblNopol.setFont(new Font("Montserrat", 0, 14));
    lblNopol.setText("Plat Nomor");
    lblNopol.setForeground(new Color(255, 255, 255));
    add(lblNopol, new AbsoluteConstraints(250, 230, -1, -1));

    lblPelanggan.setFont(new Font("Montserrat", 0, 14));
    lblPelanggan.setText("ID Pelanggan");
    lblPelanggan.setForeground(new Color(255, 255, 255));
    add(lblPelanggan, new AbsoluteConstraints(250, 190, -1, -1));

    lblKendaraan.setFont(new Font("Montserrat", 0, 14));
    lblKendaraan.setText("ID Kendaraan");
    lblKendaraan.setForeground(new Color(255, 255, 255));
    add(lblKendaraan, new AbsoluteConstraints(250, 150, -1, -1));

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48));
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("Data Kendaraan");
    add(riwayatoren, new AbsoluteConstraints(330, 70, -1, -1));

    btnClear.setBackground(new Color(255, 255, 255));
    btnClear.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnClear.setForeground(new Color(0, 0, 0));
    btnClear.setText("Clear");
    btnClear.addActionListener(this);
    add(btnClear, new AbsoluteConstraints(490, 630, -1, -1));

    btnInsert.setBackground(new Color(255, 163, 26));
    btnInsert.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnInsert.setText("Insert");
    btnInsert.addActionListener(this);
    add(btnInsert, new AbsoluteConstraints(200, 630, -1, -1));

    btnUpdate.setBackground(new Color(255, 255, 255));
    btnUpdate.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnUpdate.setForeground(new Color(0, 0, 0));
    btnUpdate.setText("Update");
    btnUpdate.addActionListener(this);
    add(btnUpdate, new AbsoluteConstraints(290, 630, -1, -1));

    btnDelete.setBackground(new Color(255, 0, 0));
    btnDelete.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnDelete.setText("Delete");
    btnDelete.addActionListener(this);
    add(btnDelete, new AbsoluteConstraints(390, 630, -1, -1));

    jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/MekanikTiktok.png")));
    jLabel3.setText("jLabel3");
    jLabel3.addMouseListener(this);
    add(jLabel3, new AbsoluteConstraints(-30, 70, 200, -1));

    jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Riwayat_service.png")));
    jLabel1.setText("jLabel1");
    add(jLabel1, new AbsoluteConstraints(0, 0, 1250, -1));
  }

  private JButton btnClear;
  private JButton btnDelete;
  private JButton btnInsert;
  private JButton btnUpdate;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JScrollPane jScrollPane1;
  private JLabel lblKendaraan;
  private JLabel lblKilometer;
  private JLabel lblMerk;
  private JLabel lblModel;
  private JLabel lblNopol;
  private JLabel lblNorang;
  private JLabel lblNosin;
  private JLabel lblPelanggan;
  private JLabel lblTahun;
  private JLabel riwayatoren;
  private JTable tblDataKendaraan;
  private JTextField tfKendaraan;
  private JTextField tfKilometer;
  private JTextField tfMerk;
  private JTextField tfModel;
  private JTextField tfNopol;
  private JTextField tfNorang;
  private JTextField tfNosin;
  private JTextField tfPelanggan;
  private JTextField tfTahun;

  private void btnInsertActionPerformed(ActionEvent event) {
    final String idKendaraan = tfKendaraan.getText().trim();
    final HashMap<String, Object> parameters = processInput();
    if (parameters == null) {
      return;
    }
    final boolean isSuccess = dataKendaraanHelper.insertData(idKendaraan, parameters);
    clearInput();
    if (isSuccess) {
      MessageUtil.showSucessMessageDialog(this, "Berhasil menambahkan data kendaraan!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Gagal menambahkan data kendaraan!");
  }

  private void btnUpdateActionPerformed(ActionEvent event) {
    final String idKendaraan = tfKendaraan.getText().trim();
    final HashMap<String, Object> parameters = processInput();
    if (parameters == null) {
      return;
    }
    final boolean isSuccess = dataKendaraanHelper.updateData(idKendaraan, parameters);
    clearInput();
    if (isSuccess) {
      MessageUtil.showSucessMessageDialog(this, "Berhasil memperbarui data kendaraan!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Gagal memperbarui data kendaraan!");
  }

  private void btnDeleteActionPerformed(ActionEvent event) {
    final String idKendaraan = tfKendaraan.getText().trim();
    final boolean isSuccess = dataKendaraanHelper.deleteDataById(idKendaraan);
    clearInput();
    if (isSuccess) {
      MessageUtil.showSucessMessageDialog(this, "Berhasil menghapus data kendaraan!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Gagal menghapus data kendaraan!");
  }

  public void clearInput() {
    tfKendaraan.setText(null);
    tfPelanggan.setText(null);
    tfNopol.setText(null);
    tfMerk.setText(null);
    tfModel.setText(null);
    tfTahun.setText(null);
    tfKilometer.setText(null);
    tfNosin.setText(null);
    tfNorang.setText(null);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == btnInsert) {
      btnInsertActionPerformed(event);
    } else if (event.getSource() == btnUpdate) {
      btnUpdateActionPerformed(event);
    } else if (event.getSource() == btnDelete) {
      btnDeleteActionPerformed(event);
    }
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    if (event.getSource() == jLabel3) {
      App.getPanelSwitcher().back();
    } else if (event.getSource() == tblDataKendaraan) {
      final int row = tblDataKendaraan.getSelectedRow();
      if (row == -1) {
        return;
      }
      final Object[] data = dataList.get(row);
      tfKendaraan.setText(data[0].toString());
      tfPelanggan.setText(data[1].toString());
      tfNopol.setText(data[2].toString());
      tfMerk.setText(data[3].toString());
      tfModel.setText(data[4].toString());
      tfTahun.setText(data[5].toString());
      tfKilometer.setText(data[6].toString());
      tfNosin.setText(data[7].toString());
      tfNosin.setText(data[8].toString());
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
    final ResultSet resultSet = dataKendaraanHelper.getAllData();
    try {
      while (resultSet.next()) {
        final Object[] data = new Object[9];
        data[0] = resultSet.getString("id_kendaraan");
        data[1] = resultSet.getString("id_pelanggan");
        data[2] = resultSet.getString("no_plat");
        data[3] = resultSet.getString("merk");
        data[4] = resultSet.getString("model");
        data[5] = resultSet.getString("tahun");
        data[6] = resultSet.getString("kilometer");
        data[7] = resultSet.getString("no_mesin");
        data[8] = resultSet.getString("no_rangka");
        dataList.add(data);
      }
      tblDataKendaraan.setModel(getUpdatedModel(tblDataKendaraan, dataList));
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public HashMap<String, Object> processInput() {
    if (!isNumber(tfTahun.getText().trim())) {
      MessageUtil.showErrorMessageDialog(this, "Data tahun berupa angka!");
      return null;
    }
    if (!isNumber(tfKilometer.getText().trim())) {
      MessageUtil.showErrorMessageDialog(this, "Data kilometer berupa angka!");
      return null;
    }
    final String idPelanggan = tfPelanggan.getText().trim();
    final String nopol = tfNopol.getText().trim();
    final String merk = tfMerk.getText().trim();
    final String model = tfModel.getText().trim();
    final int tahun = Integer.parseInt(tfTahun.getText().trim());
    final int kilometer = Integer.parseInt(tfKilometer.getText().trim());
    final String nosin = tfNosin.getText().trim();
    final String norang = tfNorang.getText().trim();

    final HashMap<String, Object> parameters = new HashMap<>();
    parameters.put("id_pelanggan", idPelanggan);
    parameters.put("no_plat", nopol);
    parameters.put("merk", merk);
    parameters.put("model", model);
    parameters.put("tahun", tahun);
    parameters.put("kilometer", kilometer);
    parameters.put("no_mesin", nosin);
    parameters.put("no_rangka", norang);
    return parameters;
  }
}