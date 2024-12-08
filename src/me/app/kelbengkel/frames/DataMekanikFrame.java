package me.app.kelbengkel.frames;

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
import me.app.kelbengkel.helpers.DataMekanikHelper;
import me.app.kelbengkel.utils.MessageUtil;

public class DataMekanikFrame extends JFrame implements FrameBase, ActionListener, MouseListener {
  private ArrayList<Object[]> dataList;
  private DataMekanikHelper dataMekanikHelper;

  public DataMekanikFrame() {
    dataMekanikHelper = new DataMekanikHelper();
    dataList = new ArrayList<>();
    initComponents();
    loadData();
  }

  private void initComponents() {
    jScrollPane1 = new JScrollPane();
    tblDataMekanik = new JTable();
    tfTelepon = new JTextField();
    tfSpesialis = new JTextField();
    tfNama = new JTextField();
    tfID = new JTextField();
    lblTelepon = new JLabel();
    lblSpesialis = new JLabel();
    lblNama = new JLabel();
    lblMekanik = new JLabel();
    riwayatoren = new JLabel();
    btnUpdate = new JButton();
    btnDelete = new JButton();
    btnInsert = new JButton();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    tblDataMekanik.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id mekanik", "Nama Mekanik", "Spesialis", "Nomor telpon" }));
    jScrollPane1.setViewportView(tblDataMekanik);

    add(jScrollPane1, new AbsoluteConstraints(260, 440, 740, 250));
    add(tfTelepon, new AbsoluteConstraints(390, 270, 610, -1));
    add(tfSpesialis, new AbsoluteConstraints(390, 230, 610, -1));
    add(tfNama, new AbsoluteConstraints(390, 190, 610, -1));
    add(tfID, new AbsoluteConstraints(390, 150, 610, -1));

    lblTelepon.setFont(new Font("Montserrat", 0, 14));
    lblTelepon.setText("No. Telp");
    lblTelepon.setForeground(new Color(255, 255, 255));
    add(lblTelepon, new AbsoluteConstraints(250, 270, -1, -1));

    lblSpesialis.setFont(new Font("Montserrat", 0, 14));
    lblSpesialis.setText("Spesialis");
    lblSpesialis.setForeground(new Color(255, 255, 255));
    add(lblSpesialis, new AbsoluteConstraints(250, 230, -1, -1));

    lblNama.setFont(new Font("Montserrat", 0, 14));
    lblNama.setText("Nama");
    lblNama.setForeground(new Color(255, 255, 255));
    add(lblNama, new AbsoluteConstraints(250, 190, -1, -1));

    lblMekanik.setFont(new Font("Montserrat", 0, 14));
    lblMekanik.setText("ID Mekanik");
    lblMekanik.setForeground(new Color(255, 255, 255));
    add(lblMekanik, new AbsoluteConstraints(250, 150, -1, -1));

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48));
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("Data Pelanggan");
    add(riwayatoren, new AbsoluteConstraints(330, 70, -1, -1));

    btnUpdate.setBackground(new Color(255, 255, 255));
    btnUpdate.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnUpdate.setForeground(new Color(0, 0, 0));
    btnUpdate.setText("Update");
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
  private JLabel lblMekanik;
  private JLabel lblNama;
  private JLabel lblSpesialis;
  private JLabel lblTelepon;
  private JLabel riwayatoren;
  private JTable tblDataMekanik;
  private JTextField tfID;
  private JTextField tfNama;
  private JTextField tfSpesialis;
  private JTextField tfTelepon;

  private void btnInsertActionPerformed(ActionEvent event) {
    final String id = tfID.getText().trim();
    final HashMap<String, Object> parameters = processInput();
    if (parameters == null) {
      return;
    }
    clearInput();
    if (dataMekanikHelper.insertData(id, parameters)) {
      MessageUtil.showSucessMessageDialog(this, "Berhasil menambahkan data mekanik!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Gagal menambahkan data mekanik!");
  }

  private void btnUpdateActionPerformed(ActionEvent event) {
    final String id = tfID.getText().trim();
    final HashMap<String, Object> parameters = processInput();
    if (parameters == null) {
      return;
    }
    clearInput();
    if (dataMekanikHelper.updateData(id, parameters)) {
      MessageUtil.showSucessMessageDialog(this, "Berhasil memperbarui data mekanik!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Gagal memperbarui data mekanik!");
  }

  private void btnDeleteActionPerformed(ActionEvent event) {
    final String id = tfID.getText().trim();
    clearInput();
    if (dataMekanikHelper.deleteDataById(id)) {
      MessageUtil.showSucessMessageDialog(this, "Berhasil menghapus data mekanik!");
      loadData();
      return;
    }
    MessageUtil.showErrorMessageDialog(this, "Gagal menghapus data mekanik!");
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
    final ResultSet resultSet = dataMekanikHelper.getAllData();
    try {
      while (resultSet.next()) {
        final Object[] data = new Object[4];
        data[0] = resultSet.getString("id_mekanik");
        data[1] = resultSet.getString("nama_mekanik");
        data[2] = resultSet.getString("telpon_mekanik");
        data[3] = resultSet.getString("spesialis_mekanik");
        dataList.add(data);
      }
      tblDataMekanik.setModel(getUpdatedModel(tblDataMekanik, dataList));
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void clearInput() {
    tfID.setText(null);
    tfNama.setText(null);
    tfTelepon.setText(null);
    tfSpesialis.setText(null);
  }

  @Override
  public HashMap<String, Object> processInput() {
    final String nama = tfNama.getText().trim();
    final String telepon = tfTelepon.getText().trim();
    final String spesialis = tfSpesialis.getText().trim();
    final HashMap<String, Object> parameters = new HashMap<>();
    parameters.put("nama_mekanik", nama);
    parameters.put("telpon_mekanik", telepon);
    parameters.put("spesialis_mekanik", spesialis);
    return parameters;
  }
}