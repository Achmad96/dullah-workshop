package me.app.kelbengkel.frames;

import static me.app.kelbengkel.utils.ValidationUtil.isNumber;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
import me.app.kelbengkel.helpers.PerbaikanHelper;
import me.app.kelbengkel.utils.IDGenerator;
import me.app.kelbengkel.utils.MessageUtil;

public class PerbaikanFrame extends JFrame implements FrameBase, MouseListener, ActionListener, KeyListener {

  private PerbaikanHelper perbaikanHelper;
  private String idKendaraan;
  private String idMekanik;
  private ArrayList<Object[]> spareparts;
  private ArrayList<Object[]> services;
  private ArrayList<Object[]> sparepartsData;
  private String idPerbaikan;

  public PerbaikanFrame() {
    services = new ArrayList<>();
    spareparts = new ArrayList<>();
    sparepartsData = new ArrayList<>();
    perbaikanHelper = new PerbaikanHelper();

    initComponents();
    initSpareparts();
    initServices();
    loadData();
  }

  private void initComponents() {
    riwayatoren = new JLabel();
    jScrollPane3 = new JScrollPane();
    tblSparepart = new JTable();
    cbPilihSparepart = new JComboBox<>();
    cbServices = new JComboBox<>();
    tfJumlahSparepart = new JTextField();
    lblJumlahSparepart = new JLabel();
    lblStock = new JLabel();
    lblJenisService = new JLabel();
    lblSparepart = new JLabel();
    lblReservasi = new JLabel();
    btnDelete = new JButton();
    btnInsert = new JButton();
    btnPerbaikan = new JButton();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();
    tfReservasi = new JTextField();

    setLayout(new AbsoluteLayout());

    lblReservasi.setFont(new Font("Dialog", 0, 14));
    lblReservasi.setText("ID Reservasi");
    lblReservasi.setForeground(new Color(255, 255, 255));
    add(lblReservasi, new AbsoluteConstraints(340, 180, -1, -1));
    add(tfReservasi, new AbsoluteConstraints(470, 180, 490, -1));

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48));
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("Perbaikan");
    add(riwayatoren, new AbsoluteConstraints(460, 40, -1, -1));

    tblSparepart.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID_Sparepart", "Nama_Sparepart", "Jumlah_Sparepart" }) {
      boolean[] canEdit = new boolean[] { true, true, false };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });
    jScrollPane3.setViewportView(tblSparepart);

    add(jScrollPane3, new AbsoluteConstraints(340, 450, 620, 120));

    cbPilihSparepart.setModel(new DefaultComboBoxModel<>(new String[] { "--Pilih Sparepart--" }));
    cbPilihSparepart.addActionListener(this);
    add(cbPilihSparepart, new AbsoluteConstraints(470, 340, -1, -1));

    cbServices.setModel(new DefaultComboBoxModel<>(new String[] { "--Pilih Jenis Layanan--" }));
    cbServices.addActionListener(this);
    tfJumlahSparepart.setText("0");
    tfJumlahSparepart.addKeyListener(this);
    add(cbServices, new AbsoluteConstraints(470, 300, -1, -1));
    add(tfJumlahSparepart, new AbsoluteConstraints(470, 380, 490, -1));

    lblJumlahSparepart.setFont(new Font("Dialog", 0, 14));
    lblJumlahSparepart.setText("Jumlah Sparepart");
    lblJumlahSparepart.setForeground(new Color(255, 255, 255));
    add(lblJumlahSparepart, new AbsoluteConstraints(340, 380, 110, -1));

    lblStock.setFont(new Font("Dialog", 0, 14));
    lblStock.setText("Stock: -");
    lblStock.setForeground(new Color(255, 255, 255));
    add(lblStock, new AbsoluteConstraints(610, 340, 100, -1));

    lblJenisService.setFont(new Font("Dialog", 0, 14));
    lblJenisService.setText("Jenis Service");
    lblJenisService.setForeground(new Color(255, 255, 255));
    add(lblJenisService, new AbsoluteConstraints(340, 300, -1, -1));

    lblSparepart.setFont(new Font("Dialog", 0, 14));
    lblSparepart.setText("Pilih Sparepart");
    lblSparepart.setForeground(new Color(255, 255, 255));
    add(lblSparepart, new AbsoluteConstraints(340, 340, 100, -1));

    btnDelete.setText("Delete");
    btnDelete.addActionListener(this);
    add(btnDelete, new AbsoluteConstraints(890, 410, -1, -1));

    btnInsert.setText("Insert");
    btnInsert.addActionListener(this);
    add(btnInsert, new AbsoluteConstraints(800, 410, -1, -1));

    btnPerbaikan.setText("Perbaikan");
    btnPerbaikan.addActionListener(this);
    add(btnPerbaikan, new AbsoluteConstraints(810, 590, 150, -1));

    jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/MekanikTiktok.png")));
    jLabel3.setText("jLabel3");
    jLabel3.addMouseListener(this);
    add(jLabel3, new AbsoluteConstraints(-30, 70, 200, -1));

    jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Riwayat_service.png")));
    jLabel1.setText("jLabel1");
    add(jLabel1, new AbsoluteConstraints(0, 0, 1250, -1));
  }

  private JButton btnDelete;
  private JButton btnInsert;
  private JButton btnPerbaikan;
  private JComboBox<String> cbPilihSparepart;
  private JComboBox<String> cbServices;

  private JLabel jLabel1;
  private JLabel jLabel3;
  private JScrollPane jScrollPane3;
  private JLabel lblJumlahSparepart;
  private JLabel lblJenisService;
  private JLabel lblSparepart;
  private JLabel lblStock;
  private JLabel lblReservasi;
  private JLabel riwayatoren;

  private JTable tblSparepart;
  private JTextField tfJumlahSparepart;
  private JTextField tfReservasi;

  private void generateID() {
    idPerbaikan = IDGenerator.generateID("PB", 10);
  }

  private void initServices() {
    try {
      final ResultSet resultSet = perbaikanHelper.getServices();
      while (resultSet.next()) {
        Object[] data = new Object[3];
        data[0] = resultSet.getString("id_layanan");
        data[1] = resultSet.getString("jenis_layanan");
        data[2] = resultSet.getInt("biaya_layanan");
        services.add(data);
        cbServices.addItem(data[1].toString());

      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  private void initSpareparts() {
    try {
      final ResultSet resultSet = perbaikanHelper.getSpareparts();
      while (resultSet.next()) {
        Object[] data = new Object[4];
        data[0] = resultSet.getString("id_sparepart");
        data[1] = resultSet.getString("jenis_sparepart");
        data[2] = resultSet.getInt("harga_sparepart");
        data[3] = resultSet.getInt("stock_sparepart");
        spareparts.add(data);
        cbPilihSparepart.addItem(data[1].toString());
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  private void btnInsertActionPerformed(ActionEvent event) {
    try {
      final int idReservasi = Integer.parseInt(tfReservasi.getText().trim());
      if (!perbaikanHelper.isReservasiExist(idReservasi)) {
        MessageUtil.showErrorMessageDialog(this, "Reservasi tidak ditemukan!");
        return;
      }
      this.generateID();
      final ResultSet resultSet = perbaikanHelper.getReservasiById(idReservasi);
      idMekanik = resultSet.getString("id_mekanik");
      idKendaraan = resultSet.getString("id_kendaraan");
      final HashMap<String, Object> parameters = processInput();
      perbaikanHelper.insertData(idPerbaikan, parameters);
      for (int i = 0; i < sparepartsData.size(); i++) {
        final String idSparepart = sparepartsData.get(i)[0].toString();
        final int jumlah = (int) sparepartsData.get(i)[2];
        perbaikanHelper.insertDetailPerbaikan(idPerbaikan, idSparepart, jumlah);
        perbaikanHelper.updateSparepartById(idSparepart, jumlah);
      }
      perbaikanHelper.insertPerbaikan(idPerbaikan, idReservasi);
      clearInput();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == btnPerbaikan) {
      btnInsertActionPerformed(event);
    } else if (event.getSource() == btnInsert) {
      if (!isNumber(tfJumlahSparepart.getText().trim())) {
        MessageUtil.showErrorMessageDialog(this, "Jumlah sparepart harus angka!");
        return;
      }
      if (cbPilihSparepart.getSelectedIndex() == 0) {
        MessageUtil.showErrorMessageDialog(this, "Pilih sparepart terlebih dahulu!");
        return;
      }
      final String idSparepart = spareparts.get(cbPilihSparepart.getSelectedIndex() - 1)[0].toString();
      final String jenisSparepart = spareparts.get(cbPilihSparepart.getSelectedIndex() - 1)[1].toString();
      final int jumlahSparepart = Integer.parseInt(tfJumlahSparepart.getText().trim());
      final Object[] data = { idSparepart, jenisSparepart, jumlahSparepart };
      sparepartsData.add(data);
      loadData();
    } else if (event.getSource() == btnDelete) {
      sparepartsData.remove(tblSparepart.getSelectedRow());
      loadData();
    } else if (event.getSource() == cbPilihSparepart) {
      if (cbPilihSparepart.getSelectedIndex() == 0) {
        return;
      }
      stock = (int) spareparts.get(cbPilihSparepart.getSelectedIndex() - 1)[3];
      if (!isNumber(tfJumlahSparepart.getText().trim())) {
        return;
      }
      amount = Integer.parseInt(tfJumlahSparepart.getText().trim());
      tfJumlahSparepart.setText("0");
      lblStock.setText("Stock: " + String.valueOf(stock - amount));
    }
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
    tblSparepart.setModel(getUpdatedModel(tblSparepart, sparepartsData));
  }

  @Override
  public void clearInput() {
    tfReservasi.setText(null);
    tfJumlahSparepart.setText(null);
    cbServices.setSelectedIndex(0);
    cbPilihSparepart.setSelectedIndex(0);
    sparepartsData.clear();
    loadData();
  }

  @Override
  public HashMap<String, Object> processInput() {
    if (cbServices.getSelectedIndex() == 0) {
      return null;
    }
    final String idLayanan = services.get(cbServices.getSelectedIndex() - 1)[0].toString();
    final int kilometer = perbaikanHelper.getKilometerById(idKendaraan);
    final HashMap<String, Object> parameters = new HashMap<>();
    parameters.put("id_kendaraan", idKendaraan);
    parameters.put("id_mekanik", idMekanik);
    parameters.put("id_layanan", idLayanan);
    parameters.put("kilometer_pertama", kilometer);
    return parameters;
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  private int amount = 0;
  private int stock = 0;

  @Override
  public void keyPressed(KeyEvent event) {}

  @Override
  public void keyReleased(KeyEvent event) {
    if (event.getSource() == tfJumlahSparepart) {
      if (cbPilihSparepart.getSelectedIndex() == 0) {
        tfJumlahSparepart.setText(String.valueOf(amount));
        lblStock.setText("Stock: " + String.valueOf(stock - amount));
        return;
      }
      if (!isNumber(tfJumlahSparepart.getText().trim())) {
        tfJumlahSparepart.setText(String.valueOf(amount));
        lblStock.setText("Stock: " + String.valueOf(stock - amount));
        return;
      }
      final int currentValue = Integer.parseInt(tfJumlahSparepart.getText().trim());
      final int updatedStock = stock - currentValue;
      if (updatedStock < 0) {
        tfJumlahSparepart.setText(String.valueOf(amount));
        lblStock.setText("Stock: " + String.valueOf(stock - amount));
        return;
      }
      lblStock.setText("Stock: " + String.valueOf(updatedStock));
      amount = currentValue;
    }
  }
}
