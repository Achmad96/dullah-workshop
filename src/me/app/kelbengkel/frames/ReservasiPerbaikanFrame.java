package me.app.kelbengkel.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class ReservasiPerbaikanFrame extends JFrame implements ActionListener, MouseListener {
  public ReservasiPerbaikanFrame() {
    initComponents();
  }

  private void initComponents() {
    riwayatoren = new JLabel();
    lblHargaPerbaikan = new JLabel();
    lblCustomPerbaikan = new JLabel();
    lblJumlahSparepart = new JLabel();
    lblPelanggan = new JLabel();
    lblPilihSparepart = new JLabel();
    jScrollPane2 = new JScrollPane();
    tblReservasiPerbaikan = new JTable();
    jScrollPane1 = new JScrollPane();
    tblStokSparepart = new JTable();
    cbJumlahSparepart = new JComboBox<>();
    tblHargaPerbaikan = new JTextField();
    tfCustomPerbaikan = new JTextField();
    tfPelanggan = new JTextField();
    tfKendaraan = new JTextField();
    cbPilihSparepart = new JComboBox<>();
    cbPaketPerbaikan = new JComboBox<>();
    lblAntrian = new JLabel();
    lblPaketPerbaikan = new JLabel();
    lblMekanik = new JLabel();
    tfMekanik = new JTextField();
    lblKendaraan = new JLabel();
    btnReservasi = new JButton();
    btnDelete = new JButton();
    btnInsert = new JButton();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48));
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("Reservasi Perbaikan");
    add(riwayatoren, new AbsoluteConstraints(270, 40, -1, -1));

    lblHargaPerbaikan.setFont(new Font("Dialog", 0, 14));
    lblHargaPerbaikan.setText("Harga Perbaikan");
    add(lblHargaPerbaikan, new AbsoluteConstraints(190, 580, -1, -1));

    lblCustomPerbaikan.setFont(new Font("Dialog", 0, 14));
    lblCustomPerbaikan.setText("Custom Perbaikan");
    add(lblCustomPerbaikan, new AbsoluteConstraints(190, 540, -1, -1));

    lblJumlahSparepart.setFont(new Font("Dialog", 0, 14));
    lblJumlahSparepart.setText("Jumlah Sparepart");
    add(lblJumlahSparepart, new AbsoluteConstraints(190, 280, -1, -1));

    lblPelanggan.setFont(new Font("Dialog", 0, 14));
    lblPelanggan.setText("Paket Perbaikan");
    add(lblPelanggan, new AbsoluteConstraints(190, 200, -1, -1));

    lblPilihSparepart.setFont(new Font("Dialog", 0, 14));
    lblPilihSparepart.setText("Pilih Sparepart");
    add(lblPilihSparepart, new AbsoluteConstraints(190, 240, -1, -1));

    tblReservasiPerbaikan.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id Kendaraan", "Id Pelanggan", "Id Mekanik", "Antrian" }));
    jScrollPane2.setViewportView(tblReservasiPerbaikan);

    add(jScrollPane2, new AbsoluteConstraints(700, 170, 490, 480));

    tblStokSparepart.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id Sparepart", "Nama Sparepart", "Stock" }));
    jScrollPane1.setViewportView(tblStokSparepart);

    add(jScrollPane1, new AbsoluteConstraints(270, 370, 300, 150));

    cbJumlahSparepart.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    add(cbJumlahSparepart, new AbsoluteConstraints(320, 280, -1, -1));
    add(tblHargaPerbaikan, new AbsoluteConstraints(320, 580, 180, -1));
    add(tfCustomPerbaikan, new AbsoluteConstraints(320, 540, 180, -1));
    add(tfPelanggan, new AbsoluteConstraints(320, 160, 180, -1));
    add(tfKendaraan, new AbsoluteConstraints(320, 120, 180, -1));

    cbPilihSparepart.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    add(cbPilihSparepart, new AbsoluteConstraints(320, 240, -1, -1));

    cbPaketPerbaikan.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    add(cbPaketPerbaikan, new AbsoluteConstraints(320, 200, -1, -1));

    lblAntrian.setFont(new Font("Dialog", 0, 14));
    lblAntrian.setText("Antrian");
    add(lblAntrian, new AbsoluteConstraints(700, 140, -1, -1));

    lblPaketPerbaikan.setFont(new Font("Dialog", 0, 14));
    lblPaketPerbaikan.setText("ID Pelanggan");
    add(lblPaketPerbaikan, new AbsoluteConstraints(190, 160, -1, -1));

    lblMekanik.setFont(new Font("Dialog", 0, 14));
    lblMekanik.setText("ID Mekanik");
    add(lblMekanik, new AbsoluteConstraints(190, 620, -1, -1));
    add(tfMekanik, new AbsoluteConstraints(320, 620, 180, -1));

    lblKendaraan.setFont(new Font("Dialog", 0, 14));
    lblKendaraan.setText("ID Kendaraan");
    add(lblKendaraan, new AbsoluteConstraints(190, 120, -1, -1));

    btnReservasi.setText("Buat Reservasi");
    btnReservasi.addActionListener(this);
    add(btnReservasi, new AbsoluteConstraints(190, 650, 380, -1));

    btnDelete.setBackground(new Color(255, 0, 0));
    btnDelete.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnDelete.setText("Hapus");
    add(btnDelete, new AbsoluteConstraints(490, 320, -1, -1));

    btnInsert.setBackground(new Color(255, 163, 26));
    btnInsert.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnInsert.setText("Insert");
    btnInsert.addActionListener(this);
    add(btnInsert, new AbsoluteConstraints(390, 320, -1, -1));

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
  private JButton btnReservasi;
  private JComboBox<String> cbJumlahSparepart;
  private JComboBox<String> cbPaketPerbaikan;
  private JComboBox<String> cbPilihSparepart;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JScrollPane jScrollPane1;
  private JScrollPane jScrollPane2;
  private JLabel lblAntrian;
  private JLabel lblCustomPerbaikan;
  private JLabel lblHargaPerbaikan;
  private JLabel lblJumlahSparepart;
  private JLabel lblKendaraan;
  private JLabel lblMekanik;
  private JLabel lblPaketPerbaikan;
  private JLabel lblPelanggan;
  private JLabel lblPilihSparepart;
  private JLabel riwayatoren;
  private JTextField tblHargaPerbaikan;
  private JTable tblReservasiPerbaikan;
  private JTable tblStokSparepart;
  private JTextField tfCustomPerbaikan;
  private JTextField tfKendaraan;
  private JTextField tfMekanik;
  private JTextField tfPelanggan;

  @Override
  public void actionPerformed(ActionEvent event) {}

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
}
