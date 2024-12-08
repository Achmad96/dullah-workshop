package me.app.kelbengkel.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.netbeans.lib.awtextra.AbsoluteLayout;

import me.app.kelbengkel.App;

import org.netbeans.lib.awtextra.AbsoluteConstraints;

public class RiwayatPenjualanFrame extends JFrame implements ActionListener, MouseListener {
  public RiwayatPenjualanFrame() {
    initComponents();
  }

  private void initComponents() {
    jScrollPane1 = new JScrollPane();
    tblRiwayatService = new JTable();
    riwayatoren = new JLabel();
    jButton2 = new JButton();
    jButton1 = new JButton();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    tblRiwayatService.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "id sparepart", "jenis sparepart", "jumlah", "total harga" }));
    jScrollPane1.setViewportView(tblRiwayatService);

    add(jScrollPane1, new AbsoluteConstraints(260, 350, 740, 250));

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48)); // NOI18N
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("RIWAYAT Penjualan");
    add(riwayatoren, new AbsoluteConstraints(260, 210, -1, -1));

    jButton2.setBackground(new Color(255, 0, 0));
    jButton2.setFont(new Font("Montserrat SemiBold", 0, 14)); // NOI18N
    jButton2.setText("Hapus");
    jButton2.addActionListener(this);
    add(jButton2, new AbsoluteConstraints(920, 310, -1, -1));

    jButton1.setBackground(new Color(255, 163, 26));
    jButton1.setFont(new Font("Montserrat SemiBold", 0, 14)); // NOI18N
    jButton1.setText("Cetak");
    jButton1.addActionListener(this);
    add(jButton1, new AbsoluteConstraints(830, 310, -1, -1));

    jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/MekanikTiktok.png"))); // NOI18N
    jLabel3.setText("jLabel3");
    jLabel3.addMouseListener(this);
    add(jLabel3, new AbsoluteConstraints(-30, 70, 200, -1));

    jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Riwayat_service.png"))); // NOI18N
    jLabel1.setText("jLabel1");
    add(jLabel1, new AbsoluteConstraints(0, 0, 1250, -1));
  }

  private JButton jButton1;
  private JButton jButton2;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JScrollPane jScrollPane1;
  private JLabel riwayatoren;
  private JTable tblRiwayatService;

  @Override
  public void actionPerformed(ActionEvent e) {}

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getSource() == jLabel3) {
      App.getPanelSwitcher().back();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
