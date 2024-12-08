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
    btnPembayaran = new JButton();
    btnReservasiPerbaikan = new JButton();
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

    btnPembayaran.setBackground(new Color(255, 153, 0));
    btnPembayaran.setFont(new Font("Helvetica", 1, 14));
    btnPembayaran.setForeground(new Color(0, 0, 0));
    btnPembayaran.setText("Pembayaran");
    btnPembayaran.setBorder(BorderFactory.createEtchedBorder());
    getContentPane().add(btnPembayaran, new AbsoluteConstraints(710, 110, 340, 150));

    btnReservasiPerbaikan.setBackground(new Color(255, 153, 0));
    btnReservasiPerbaikan.setFont(new Font("Helvetica", 1, 14));
    btnReservasiPerbaikan.setForeground(new Color(0, 0, 0));
    btnReservasiPerbaikan.setText("Reservasi Perbaikan");
    btnReservasiPerbaikan.setBorder(BorderFactory.createEtchedBorder());
    getContentPane().add(btnReservasiPerbaikan, new AbsoluteConstraints(260, 110, 340, 150));

    table.setBorder(BorderFactory.createEtchedBorder());
    table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id Kendaraan", "Nama Kendaraan", "Id Pelanggan", "Status Perbaikan" }));
    jScrollPane2.setViewportView(table);
    getContentPane().add(jScrollPane2, new AbsoluteConstraints(260, 290, 790, 230));

    jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Dashboard.png")));
    jLabel1.setText("jlabel1");
    getContentPane().add(jLabel1, new AbsoluteConstraints(0, 0, 1246, -1));

    jMenuBar1.setBackground(new Color(217, 217, 217));
    jMenuBar1.setBorder(null);
    jMenuBar1.setForeground(new Color(217, 217, 217));
    jMenuBar1.setToolTipText("");

    jMenu1.setText("Dashboard");
    jMenu1.addActionListener(this);
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

  private JButton btnPembayaran;
  private JButton btnReservasiPerbaikan;
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
    }
  }

  @Override
  public void loadData() {
    dataList.clear();
    final ResultSet resultSet = dashboardHelper.getAllData();
    if (resultSet == null) {
      MessageUtil.showErrorMessageDialog(this, "Gagal mengambil data pelanggan!");
      return;
    }
    try {
      while (resultSet.next()) {
        final Object[] data = { resultSet.getString("id_kendaraan"), resultSet.getString("nama_kendaraan"), resultSet.getString("id_pelanggan"), resultSet.getString("nama_pelanggan") };
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