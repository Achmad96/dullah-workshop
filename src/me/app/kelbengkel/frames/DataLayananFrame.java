package me.app.kelbengkel.frames;

import static me.app.kelbengkel.utils.ValidationUtil.isNumber;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
import me.app.kelbengkel.helpers.DataLayananHelper;
import me.app.kelbengkel.utils.MessageUtil;

public class DataLayananFrame extends JFrame implements FrameBase, ActionListener, MouseListener {

  private DataLayananHelper dataLayananHelper;
  private ArrayList<Object[]> dataList;

  public DataLayananFrame() {
    dataLayananHelper = new DataLayananHelper();
    dataList = new ArrayList<>();
    initComponents();
    loadData();
  }

  private void initComponents() {
    jScrollPane1 = new JScrollPane();
    tblLayanan = new JTable();
    tfHarga = new JTextField();
    tfJenisLayanan = new JTextField();
    tfID = new JTextField();
    lblHarga = new JLabel();
    lblJenis = new JLabel();
    lblLayanan = new JLabel();
    riwayatoren = new JLabel();
    btnUpdate = new JButton();
    btnDelete = new JButton();
    btnInsert = new JButton();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    tblLayanan.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id layanan", "Jenis layanan", "Harga" }));
    tblLayanan.addMouseListener(this);
    jScrollPane1.setViewportView(tblLayanan);

    add(jScrollPane1, new AbsoluteConstraints(260, 440, 740, 250));
    add(tfHarga, new AbsoluteConstraints(390, 230, 610, -1));
    add(tfJenisLayanan, new AbsoluteConstraints(390, 190, 610, -1));
    add(tfID, new AbsoluteConstraints(390, 150, 610, -1));

    lblHarga.setFont(new Font("Montserrat", 0, 14));
    lblHarga.setText("Harga");
    lblHarga.setForeground(new Color(255, 255, 255));
    add(lblHarga, new AbsoluteConstraints(250, 230, -1, -1));

    lblJenis.setFont(new Font("Montserrat", 0, 14));
    lblJenis.setText("Jenis");
    lblJenis.setForeground(new Color(255, 255, 255));
    add(lblJenis, new AbsoluteConstraints(250, 190, -1, -1));

    lblLayanan.setFont(new Font("Montserrat", 0, 14));
    lblLayanan.setText("ID Layanan");
    lblLayanan.setForeground(new Color(255, 255, 255));
    add(lblLayanan, new AbsoluteConstraints(250, 150, -1, -1));

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48));
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("Data Layanan");
    add(riwayatoren, new AbsoluteConstraints(360, 70, -1, -1));

    btnUpdate.setBackground(new Color(255, 255, 255));
    btnUpdate.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnUpdate.setForeground(new Color(0, 0, 0));
    btnUpdate.setText("Update");
    btnUpdate.addActionListener(this);
    add(btnUpdate, new AbsoluteConstraints(730, 400, -1, -1));

    btnDelete.setBackground(new Color(255, 0, 0));
    btnDelete.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnDelete.setText("Delete");
    btnDelete.addActionListener(this);
    add(btnDelete, new AbsoluteConstraints(830, 400, -1, -1));

    btnInsert.setBackground(new Color(255, 163, 26));
    btnInsert.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnInsert.setText("Insert");
    btnInsert.addActionListener(this);
    add(btnInsert, new AbsoluteConstraints(640, 400, -1, -1));

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
  private JButton btnUpdate;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JScrollPane jScrollPane1;
  private JLabel lblHarga;
  private JLabel lblJenis;
  private JLabel lblLayanan;
  private JLabel riwayatoren;
  private JTable tblLayanan;
  private JTextField tfHarga;
  private JTextField tfID;
  private JTextField tfJenisLayanan;

  public HashMap<String, Object> processInput() {
    if (!isNumber(tfHarga.getText().trim())) {
      MessageUtil.showErrorMessageDialog(this, "Data harga harus berupa angka!");
      return null;
    }
    final String jenis = tfJenisLayanan.getText().trim();
    final int harga = Integer.parseInt(tfHarga.getText().trim());
    final HashMap<String, Object> parameters = new HashMap<>();
    parameters.put("jenis_layanan", jenis);
    parameters.put("biaya_layanan", harga);
    return parameters;
  }

  private void btnInsertActionPerformed(ActionEvent event) {
    final HashMap<String, Object> parameters = processInput();
    if (parameters == null) {
      return;
    }
    final String id = tfID.getText().trim();
    final boolean isSuccess = dataLayananHelper.insertData(id, parameters);
    clearInput();
    if (isSuccess) {
      MessageUtil.showSucessMessageDialog(this, "Berhasil menambahkan data layanan!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Gagal menambahkan data layanan!");
  }

  private void btnUpdateActionPerformed(ActionEvent event) {
    final HashMap<String, Object> parameters = processInput();
    if (parameters == null) {
      return;
    }
    final String id = tfID.getText().trim();
    final boolean isSuccess = dataLayananHelper.updateData(id, parameters);
    clearInput();
    if (isSuccess) {
      MessageUtil.showSucessMessageDialog(this, "Berhasil memperbarui data layanan!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Gagal memperbarui data layanan!");
  }

  private void btnDeleteActionPerformed(ActionEvent event) {
    final String id = tfID.getText().trim();
    clearInput();
    if (dataLayananHelper.deleteDataById(id)) {
      MessageUtil.showSucessMessageDialog(this, "Berhasil menghapus data layanan!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Gagal menghapus data layanan!");
  }

  public void clearInput() {
    tfID.setText(null);
    tfJenisLayanan.setText(null);
    tfHarga.setText(null);
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
    } else if (event.getSource() == tblLayanan) {
      final int row = tblLayanan.getSelectedRow();
      if (row == -1) {
        return;
      }
      final Object[] data = dataList.get(row);
      tfID.setText(data[0].toString());
      tfJenisLayanan.setText(data[1].toString());
      tfHarga.setText(data[2].toString());
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
    final ResultSet resultSet = dataLayananHelper.getAllData();
    try {
      while (resultSet.next()) {
        final Object[] data = new Object[3];
        data[0] = resultSet.getString("id_layanan");
        data[1] = resultSet.getString("jenis_layanan");
        data[2] = resultSet.getInt("biaya_layanan");
        dataList.add(data);
      }
      tblLayanan.setModel(getUpdatedModel(tblLayanan, dataList));
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}