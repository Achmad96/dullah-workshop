package me.app.kelbengkel.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import me.app.kelbengkel.App;

public class RiwayatServiceFrame extends JFrame implements ActionListener, MouseListener {
  public RiwayatServiceFrame() {
    initComponents();
  }

  private void initComponents() {
    jScrollPane1 = new JScrollPane();
    tblRiwayatService = new JTable();
    riwayatoren = new JLabel();
    btnHapus = new JButton();
    btnCetak = new JButton();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    tblRiwayatService
        .setModel(new DefaultTableModel(new Object[][] {}, new String[] { "id transaksi", "id kendaraan", "id mekanik", "id kasir", "tanggal", "total biaya", "odo kendaraan", "metode pembayaran" }));
    jScrollPane1.setViewportView(tblRiwayatService);

    add(jScrollPane1, new AbsoluteConstraints(260, 350, 740, 250));

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48));
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("RIWAYAT SERVICE");
    add(riwayatoren, new AbsoluteConstraints(330, 210, -1, -1));

    btnHapus.setBackground(new Color(255, 0, 0));
    btnHapus.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnHapus.setText("Hapus");
    btnHapus.addActionListener(this);
    add(btnHapus, new AbsoluteConstraints(920, 310, -1, -1));

    btnCetak.setBackground(new Color(255, 163, 26));
    btnCetak.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnCetak.setText("Cetak");
    add(btnCetak, new AbsoluteConstraints(830, 310, -1, -1));

    jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/MekanikTiktok.png")));
    jLabel3.setText("jLabel3");
    jLabel3.addMouseListener(this);
    add(jLabel3, new AbsoluteConstraints(-30, 70, 200, -1));

    jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Riwayat_service.png")));
    jLabel1.setText("jLabel1");
    add(jLabel1, new AbsoluteConstraints(0, 0, 1250, -1));
  }

  private JButton btnCetak;
  private JButton btnHapus;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JScrollPane jScrollPane1;
  private JLabel riwayatoren;
  private JTable tblRiwayatService;

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
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
  }
}