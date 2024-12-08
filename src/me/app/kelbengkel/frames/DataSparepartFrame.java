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
import me.app.kelbengkel.helpers.DataSparepartHelper;

public class DataSparepartFrame extends JFrame implements FrameBase, ActionListener, MouseListener {
  private final ArrayList<Object[]> dataList;
  private final DataSparepartHelper dataSparepartHelper;

  public DataSparepartFrame() {
    dataList = new ArrayList<>();
    dataSparepartHelper = new DataSparepartHelper();
    initComponents();
  }

  private void initComponents() {
    jScrollPane1 = new JScrollPane();
    tblRiwayatService = new JTable();
    tfTelepon = new JTextField();
    tfAlamat = new JTextField();
    tfNama = new JTextField();
    tfID = new JTextField();
    lblTelepon = new JLabel();
    lblAlamat = new JLabel();
    lblNama = new JLabel();
    lblPelanggan = new JLabel();
    riwayatoren = new JLabel();
    btnUpdate = new JButton();
    btnDelete = new JButton();
    btnInsert = new JButton();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    tblRiwayatService.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id sparepart", "Jenis sparepart", "Harga sparepart", "jumlah" }));
    jScrollPane1.setViewportView(tblRiwayatService);

    add(jScrollPane1, new AbsoluteConstraints(260, 440, 740, 250));
    add(tfTelepon, new AbsoluteConstraints(390, 270, 610, -1));
    add(tfAlamat, new AbsoluteConstraints(390, 230, 610, -1));
    add(tfNama, new AbsoluteConstraints(390, 190, 610, -1));
    add(tfID, new AbsoluteConstraints(390, 150, 610, -1));

    lblTelepon.setFont(new Font("Montserrat", 0, 14));
    lblTelepon.setText("No. Telp");
    lblTelepon.setForeground(new Color(255, 255, 255));
    add(lblTelepon, new AbsoluteConstraints(250, 270, -1, -1));

    lblAlamat.setFont(new Font("Montserrat", 0, 14));
    lblAlamat.setText("Alamat");
    lblAlamat.setForeground(new Color(255, 255, 255));
    add(lblAlamat, new AbsoluteConstraints(250, 230, -1, -1));

    lblNama.setFont(new Font("Montserrat", 0, 14));
    lblNama.setText("Nama");
    lblNama.setForeground(new Color(255, 255, 255));
    add(lblNama, new AbsoluteConstraints(250, 190, -1, -1));

    lblPelanggan.setFont(new Font("Montserrat", 0, 14));
    lblPelanggan.setText("ID Pelanggan");
    lblPelanggan.setForeground(new Color(255, 255, 255));
    add(lblPelanggan, new AbsoluteConstraints(250, 150, -1, -1));

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48));
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("Data Sparepart");
    add(riwayatoren, new AbsoluteConstraints(330, 70, -1, -1));

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
  private JLabel lblAlamat;
  private JLabel lblNama;
  private JLabel lblPelanggan;
  private JLabel lblTelepon;
  private JLabel riwayatoren;
  private JTable tblRiwayatService;
  private JTextField tfID;
  private JTextField tfNama;
  private JTextField tfTelepon;
  private JTextField tfAlamat;

  private void btnInsertActionPerformed(ActionEvent event) {}

  private void btnUpdateActionPerformed(ActionEvent event) {}

  private void btnDeleteActionPerformed(ActionEvent event) {}

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
    final ResultSet resultSet = dataSparepartHelper.getAllData();
    try {
      while (resultSet.next()) {
        final Object[] data = new Object[3];
        data[0] = resultSet.getString("id_pelanggan");
        data[1] = resultSet.getString("jenis_sparepart");
        data[2] = resultSet.getString("harga_sparepart");
        dataList.add(data);
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void clearInput() {
    tfID.setText(null);
    tfNama.setText(null);
    tfAlamat.setText(null);
    tfTelepon.setText(null);
  }

  @Override
  public HashMap<String, Object> processInput() {
    return null;
  }
}