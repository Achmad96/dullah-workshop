package me.app.kelbengkel.frames;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import me.app.kelbengkel.App;
import me.app.kelbengkel.helpers.DashboardHelper;
import me.app.kelbengkel.utils.MessageUtil;

public class Dashboard extends JFrame implements FrameBase, ActionListener {
  private DashboardHelper dashboardHelper;
  private ArrayList<Object[]> dataList;

  public Dashboard() {
    dashboardHelper = new DashboardHelper();
    dataList = new ArrayList<>();
    initComponents();
    loadData();
  }

  private void initComponents() {
    btnLogout = new JButton();
    btnPembayaran = new JButton();
    btnPerbaikan = new JButton();
    btnReservasi = new JButton();
    jScrollPane2 = new JScrollPane();
    table = new JTable();
    jLabel1 = new JLabel();
    jMenuBar1 = new JMenuBar();
    jMenu1 = new JMenu();
    jMenu2 = new JMenu();
    jMenuItem1 = new JMenuItem();
    jMenuItem2 = new JMenuItem();
    jMenu3 = new JMenu();
    jMenuItem3 = new JMenuItem();
    jMenuItem4 = new JMenuItem();
    jMenuItem5 = new JMenuItem();
    jMenuItem6 = new JMenuItem();
    jMenuItem7 = new JMenuItem();
    jMenuItem8 = new JMenuItem();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(new AbsoluteLayout());

    btnLogout.setBorder(null);
    btnLogout.setBorderPainted(false);
    btnLogout.setContentAreaFilled(false);
    getContentPane().add(btnLogout, new AbsoluteConstraints(1153, 45, 80, 30));

    btnPembayaran.setBackground(new Color(255, 153, 0));
    btnPembayaran.setFont(new Font("Helvetica", 1, 14));
    btnPembayaran.setForeground(new Color(0, 0, 0));
    btnPembayaran.setText("Pembayaran");
    btnPembayaran.setBorder(BorderFactory.createEtchedBorder());
    btnPembayaran.addActionListener(this);
    getContentPane().add(btnPembayaran, new AbsoluteConstraints(820, 110, 230, 150));

    btnPerbaikan.setBackground(new Color(255, 153, 0));
    btnPerbaikan.setFont(new Font("Helvetica", 1, 14));
    btnPerbaikan.setForeground(new Color(0, 0, 0));
    btnPerbaikan.setText("Perbaikan");
    btnPerbaikan.setBorder(BorderFactory.createEtchedBorder());
    btnPerbaikan.addActionListener(this);
    getContentPane().add(btnPerbaikan, new AbsoluteConstraints(540, 110, 230, 150));

    btnReservasi.setBackground(new Color(255, 153, 0));
    btnReservasi.setFont(new Font("Helvetica", 1, 14));
    btnReservasi.setForeground(new Color(0, 0, 0));
    btnReservasi.setText("Ambil nomor antrian");
    btnReservasi.setBorder(BorderFactory.createEtchedBorder());
    btnReservasi.addActionListener(this);
    getContentPane().add(btnReservasi, new AbsoluteConstraints(260, 110, 230, 150));

    table.setBorder(BorderFactory.createEtchedBorder());
    table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID_Reservasi", "ID_Mekanik", "ID_Kendaraan", "ID_Layanan" }));
    jScrollPane2.setViewportView(table);
    if (table.getColumnModel().getColumnCount() > 0) {
      table.getColumnModel().getColumn(0).setResizable(false);
    }

    getContentPane().add(jScrollPane2, new AbsoluteConstraints(260, 320, 790, 230));

    jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Dashboard.png")));
    jLabel1.setText("jLabel1");
    getContentPane().add(jLabel1, new AbsoluteConstraints(0, 0, 1246, -1));

    jMenuBar1.setBackground(new Color(217, 217, 217));
    jMenuBar1.setBorder(null);
    jMenuBar1.setForeground(new Color(217, 217, 217));
    jMenuBar1.setToolTipText("");

    jMenu1.setText("Dashboard");
    jMenuBar1.add(jMenu1);

    jMenu2.setText("Transaksi");

    jMenuItem1.setText("Riwayat Service");
    jMenuItem1.addActionListener(this);
    jMenu2.add(jMenuItem1);

    jMenuItem2.setText("Riwayat Penjualan");
    jMenuItem2.addActionListener(this);
    jMenu2.add(jMenuItem2);

    jMenuBar1.add(jMenu2);

    jMenu3.setText("Data & Konsultan");

    jMenuItem3.setText("Data Pelanggan");
    jMenuItem3.addActionListener(this);
    jMenu3.add(jMenuItem3);

    jMenuItem4.setText("Data Layanan");
    jMenuItem4.addActionListener(this);
    jMenu3.add(jMenuItem4);

    jMenuItem5.setText("Data Sparepart");
    jMenuItem5.addActionListener(this);
    jMenu3.add(jMenuItem5);

    jMenuItem6.setText("Data Mekanik");
    jMenuItem6.addActionListener(this);
    jMenu3.add(jMenuItem6);

    jMenuItem7.setText("Data Konsultan");
    jMenuItem7.addActionListener(this);
    jMenu3.add(jMenuItem7);

    jMenuItem8.setText("Data Kendaraan");
    jMenuItem8.addActionListener(this);
    jMenu3.add(jMenuItem8);

    jMenuBar1.add(jMenu3);

    setJMenuBar(jMenuBar1);

    setSize(new Dimension(1260, 739));
    setLocationRelativeTo(null);
  }

  private JButton btnLogout;
  private JButton btnPembayaran;
  private JButton btnPerbaikan;
  private JButton btnReservasi;
  private JLabel jLabel1;
  private JMenu jMenu1;
  private JMenu jMenu2;
  private JMenu jMenu3;
  private JMenuBar jMenuBar1;
  private JMenuItem jMenuItem1;
  private JMenuItem jMenuItem2;
  private JMenuItem jMenuItem3;
  private JMenuItem jMenuItem4;
  private JMenuItem jMenuItem5;
  private JMenuItem jMenuItem6;
  private JMenuItem jMenuItem7;
  private JMenuItem jMenuItem8;
  private JScrollPane jScrollPane2;
  private JTable table;

  private RiwayatServiceFrame riwayatServiceFrame;
  private RiwayatPenjualanFrame riwayatPenjualanFrame;
  private DataPelangganFrame dataPelangganFrame;
  private DataLayananFrame dataLayananFrame;
  private DataSparepartFrame dataSparepartFrame;
  private DataMekanikFrame dataMekanikFrame;
  private DataKonsultanFrame dataKonsultanFrame;
  private DataKendaraanFrame dataKendaraanFrame;
  private ReservasiFrame reservasiFrame;
  private PerbaikanFrame perbaikanFrame;
  private PembayaranFrame pembayaranFrame;

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == jMenu1) {
      this.setContentPane(this.getContentPane());
    } else if (event.getSource() == jMenuItem1) {
      if (riwayatServiceFrame == null) {
        riwayatServiceFrame = new RiwayatServiceFrame();
      }
      App.getPanelSwitcher().openNewFrame(riwayatServiceFrame);
    } else if (event.getSource() == jMenuItem2) {
      if (riwayatPenjualanFrame == null) {
        riwayatPenjualanFrame = new RiwayatPenjualanFrame();
      }
      App.getPanelSwitcher().openNewFrame(riwayatPenjualanFrame);
    } else if (event.getSource() == jMenuItem3) {
      if (dataPelangganFrame == null) {
        dataPelangganFrame = new DataPelangganFrame();
      }
      App.getPanelSwitcher().openNewFrame(dataPelangganFrame);
    } else if (event.getSource() == jMenuItem4) {
      if (dataLayananFrame == null) {
        dataLayananFrame = new DataLayananFrame();
      }
      App.getPanelSwitcher().openNewFrame(dataLayananFrame);
    } else if (event.getSource() == jMenuItem5) {
      if (dataSparepartFrame == null) {
        dataSparepartFrame = new DataSparepartFrame();
      }
      App.getPanelSwitcher().openNewFrame(dataSparepartFrame);
    } else if (event.getSource() == jMenuItem6) {
      if (dataMekanikFrame == null) {
        dataMekanikFrame = new DataMekanikFrame();
      }
      App.getPanelSwitcher().openNewFrame(dataMekanikFrame);
    } else if (event.getSource() == jMenuItem7) {
      if (dataKonsultanFrame == null) {
        dataKonsultanFrame = new DataKonsultanFrame();
      }
      App.getPanelSwitcher().openNewFrame(dataKonsultanFrame);
    } else if (event.getSource() == jMenuItem8) {
      if (dataKendaraanFrame == null) {
        dataKendaraanFrame = new DataKendaraanFrame();
      }
      App.getPanelSwitcher().openNewFrame(dataKendaraanFrame);
    } else if (event.getSource() == btnReservasi) {
      if (reservasiFrame == null) {
        reservasiFrame = new ReservasiFrame();
      }
      App.getPanelSwitcher().openNewFrame(reservasiFrame);
    } else if (event.getSource() == btnPerbaikan) {
      if (perbaikanFrame == null) {
        perbaikanFrame = new PerbaikanFrame();
      }
      App.getPanelSwitcher().openNewFrame(perbaikanFrame);
    } else if (event.getSource() == btnPembayaran) {
      if (pembayaranFrame == null) {
        pembayaranFrame = new PembayaranFrame();
      }
      App.getPanelSwitcher().openNewFrame(pembayaranFrame);
    }
  }

  @Override
  public void loadData() {
    dataList.clear();
    final ResultSet resultSet = dashboardHelper.getAllData();
    if (resultSet == null) {
      MessageUtil.showErrorMessageDialog(this, "Gagailhal mengambil data pelanggan!");
      return;
    }
    try {
      while (resultSet.next()) {
        final Object[] data = { resultSet.getString("id_reservasi"), resultSet.getString("id_mekanik"), resultSet.getString("id_kendaraan"), resultSet.getString("id_layanan") };
        dataList.add(data);
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    table.setModel(getUpdatedModel(table, dataList));
  }

  @Override
  public void clearInput() {
    return;
  }

  @Override
  public HashMap<String, Object> processInput() {
    return null;
  }
}