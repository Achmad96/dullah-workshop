package me.app.kelbengkel.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class PembayaranFrame extends JFrame implements ActionListener, MouseListener {
  public PembayaranFrame() {
    initComponents();
  }

  private void initComponents() {
    riwayatoren = new JLabel();
    lblMetodePembayaran = new JLabel();
    lblTotalBiaya = new JLabel();
    jScrollPane2 = new JScrollPane();
    tblPembayaran = new JTable();
    tfKendaraan = new JTextField();
    lblDetailPembayaran = new JLabel();
    lblPaketPerbaikan = new JLabel();
    lblKendaraan = new JLabel();
    cbMetodePembayaran = new JComboBox<>();
    btnKonfirmasi = new JButton();
    btnCetak = new JButton();
    lblTotalBiaya1 = new JLabel();
    lblPelanggan1 = new JLabel();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48)); // NOI18N
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("Pembayaran");
    add(riwayatoren, new AbsoluteConstraints(400, 40, -1, -1));

    lblMetodePembayaran.setFont(new Font("Dialog", 0, 14)); // NOI18N
    lblMetodePembayaran.setText("Metode Pembayaran");
    add(lblMetodePembayaran, new AbsoluteConstraints(190, 300, -1, -1));

    lblTotalBiaya.setFont(new Font("Dialog", 0, 14)); // NOI18N
    lblTotalBiaya.setText("Total Biaya");
    add(lblTotalBiaya, new AbsoluteConstraints(190, 260, -1, -1));

    tblPembayaran.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
    jScrollPane2.setViewportView(tblPembayaran);

    add(jScrollPane2, new AbsoluteConstraints(700, 170, 490, 340));
    add(tfKendaraan, new AbsoluteConstraints(350, 180, 180, -1));

    lblDetailPembayaran.setFont(new Font("Dialog", 0, 14)); // NOI18N
    lblDetailPembayaran.setText("Detail Pembayaran");
    add(lblDetailPembayaran, new AbsoluteConstraints(700, 140, -1, -1));

    lblPaketPerbaikan.setFont(new Font("Dialog", 0, 14)); // NOI18N
    lblPaketPerbaikan.setText("ID Pelanggan");
    add(lblPaketPerbaikan, new AbsoluteConstraints(190, 220, -1, -1));

    lblKendaraan.setFont(new Font("Dialog", 0, 14)); // NOI18N
    lblKendaraan.setText("ID Kendaraan");
    add(lblKendaraan, new AbsoluteConstraints(190, 180, -1, -1));

    cbMetodePembayaran.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    add(cbMetodePembayaran, new AbsoluteConstraints(350, 300, -1, -1));

    btnKonfirmasi.setText("Konfirmasi Pembayaran");
    btnKonfirmasi.addActionListener(this);
    add(btnKonfirmasi, new AbsoluteConstraints(190, 350, 380, -1));

    btnCetak.setBackground(new Color(255, 163, 26));
    btnCetak.setFont(new Font("Montserrat SemiBold", 0, 14)); // NOI18N
    btnCetak.setText("Cetak");
    btnCetak.addActionListener(this);
    add(btnCetak, new AbsoluteConstraints(1110, 580, -1, -1));

    lblTotalBiaya1.setFont(new Font("Montserrat", 0, 14)); // NOI18N
    lblTotalBiaya1.setText("-");
    add(lblTotalBiaya1, new AbsoluteConstraints(350, 260, 10, -1));

    lblPelanggan1.setFont(new Font("Montserrat", 0, 14)); // NOI18N
    lblPelanggan1.setText("-");
    lblPelanggan1.setForeground(new Color(255, 255, 255));
    add(lblPelanggan1, new AbsoluteConstraints(350, 220, 10, -1));

    jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/MekanikTiktok.png"))); // NOI18N
    jLabel3.setText("jLabel3");
    jLabel3.addMouseListener(this);
    add(jLabel3, new AbsoluteConstraints(-30, 70, 200, -1));

    jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Riwayat_service.png"))); // NOI18N
    jLabel1.setText("jLabel1");
    add(jLabel1, new AbsoluteConstraints(0, 0, 1250, -1));
  }

  private JButton btnCetak;
  private JButton btnKonfirmasi;
  private JComboBox<String> cbMetodePembayaran;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JScrollPane jScrollPane2;
  private JLabel lblDetailPembayaran;
  private JLabel lblKendaraan;
  private JLabel lblMetodePembayaran;
  private JLabel lblPaketPerbaikan;
  private JLabel lblPelanggan1;
  private JLabel lblTotalBiaya;
  private JLabel lblTotalBiaya1;
  private JLabel riwayatoren;
  private JTable tblPembayaran;
  private JTextField tfKendaraan;

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
