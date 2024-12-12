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
import me.app.kelbengkel.exception.TemplateException;
import me.app.kelbengkel.helpers.PembayaranHelper;
import me.app.kelbengkel.utils.IDGenerator;
import me.app.kelbengkel.utils.MessageUtil;
import me.app.kelbengkel.utils.TemplatePrinter;

public class PembayaranFrame extends JFrame implements FrameBase, ActionListener, MouseListener, KeyListener {
  private ArrayList<Object[]> dataList;
  private HashMap<String, Integer> dataSpareparts;
  private PembayaranHelper pembayaranHelper;

  public PembayaranFrame() {
    pembayaranHelper = new PembayaranHelper();
    dataSpareparts = new HashMap<>();
    dataList = new ArrayList<>();
    initComponents();
    initSpareparts();
  }

  private void initSpareparts() {
    final ResultSet resultSet = pembayaranHelper.getSpareparts();
    try {
      while (resultSet.next()) {
        Object[] data = new Object[2];
        data[0] = resultSet.getString("id_sparepart");
        data[1] = resultSet.getInt("harga_sparepart");
        dataSpareparts.put(data[0].toString(), ((int) data[1]));
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  private void initComponents() {
    riwayatoren = new JLabel();
    lblMetodePembayaran = new JLabel();
    lblNamaLayanan = new JLabel();
    lblTotalBiaya = new JLabel();
    jScrollPane2 = new JScrollPane();
    tblPembayaran = new JTable();
    tfKasir = new JTextField();
    tfPerbaikan = new JTextField();
    lblBiaya = new JLabel();
    lblDetailPembayaran = new JLabel();
    lblKasir = new JLabel();
    lblPerbaikan = new JLabel();
    lblHargaLayanan = new JLabel();
    lblVHargaLayanan = new JLabel();
    cbMetodePembayaran = new JComboBox<>();
    btnKonfirmasi = new JButton();
    btnCetak = new JButton();
    lblVNamaLayanan = new JLabel();
    jLabel3 = new JLabel();
    jLabel1 = new JLabel();

    setLayout(new AbsoluteLayout());

    riwayatoren.setFont(new Font("Akira Expanded", 0, 48));
    riwayatoren.setForeground(new Color(255, 163, 26));
    riwayatoren.setText("Pembayaran");
    add(riwayatoren, new AbsoluteConstraints(400, 40, -1, -1));

    lblMetodePembayaran.setFont(new Font("Dialog", 0, 14));
    lblMetodePembayaran.setText("Metode Pembayaran");
    lblMetodePembayaran.setForeground(new Color(255, 255, 255));
    add(lblMetodePembayaran, new AbsoluteConstraints(190, 300, -1, -1));

    lblNamaLayanan.setFont(new Font("Dialog", 0, 14));
    lblNamaLayanan.setText("Nama Layanan");
    lblNamaLayanan.setForeground(new Color(255, 255, 255));
    add(lblNamaLayanan, new AbsoluteConstraints(190, 210, -1, -1));

    lblTotalBiaya.setFont(new Font("Dialog", 0, 14));
    lblTotalBiaya.setText("Total Biaya");
    lblTotalBiaya.setForeground(new Color(255, 255, 255));
    add(lblTotalBiaya, new AbsoluteConstraints(190, 265, -1, -1));

    tblPembayaran.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id Sparepart", "Jenis Sparepart", "Jumlah", "Subtotal" }));
    jScrollPane2.setViewportView(tblPembayaran);

    tfPerbaikan.addKeyListener(this);

    add(jScrollPane2, new AbsoluteConstraints(700, 170, 490, 340));
    add(tfKasir, new AbsoluteConstraints(350, 170, 180, -1));
    add(tfPerbaikan, new AbsoluteConstraints(350, 130, 180, -1));

    lblBiaya.setFont(new Font("Montserrat", 0, 14));
    lblBiaya.setText("-");
    lblBiaya.setForeground(new Color(255, 255, 255));
    add(lblBiaya, new AbsoluteConstraints(350, 265, -1, -1));

    lblDetailPembayaran.setFont(new Font("Dialog", 0, 14));
    lblDetailPembayaran.setText("Detail Pembayaran");
    lblDetailPembayaran.setForeground(new Color(255, 255, 255));
    add(lblDetailPembayaran, new AbsoluteConstraints(700, 140, -1, -1));

    lblKasir.setFont(new Font("Dialog", 0, 14));
    lblKasir.setText("ID Kasir");
    lblKasir.setForeground(new Color(255, 255, 255));
    add(lblKasir, new AbsoluteConstraints(190, 170, -1, -1));

    lblPerbaikan.setFont(new Font("Dialog", 0, 14));
    lblPerbaikan.setText("ID Perbaikan");
    lblPerbaikan.setForeground(new Color(255, 255, 255));
    add(lblPerbaikan, new AbsoluteConstraints(190, 130, -1, -1));

    cbMetodePembayaran.setModel(new DefaultComboBoxModel<>(new String[] { "QRIS", "CASH", }));
    add(cbMetodePembayaran, new AbsoluteConstraints(350, 300, -1, -1));

    btnKonfirmasi.setText("Konfirmasi Pembayaran");
    btnKonfirmasi.addActionListener(this);
    add(btnKonfirmasi, new AbsoluteConstraints(190, 350, 380, -1));

    btnCetak.setBackground(new Color(255, 163, 26));
    btnCetak.setFont(new Font("Montserrat SemiBold", 0, 14));
    btnCetak.setText("Cetak");
    btnCetak.addActionListener(this);
    add(btnCetak, new AbsoluteConstraints(1110, 580, -1, -1));

    lblVNamaLayanan.setFont(new Font("Montserrat", 0, 14));
    lblVNamaLayanan.setText("-");
    lblVNamaLayanan.setForeground(new Color(255, 255, 255));
    add(lblVNamaLayanan, new AbsoluteConstraints(350, 210, -1, -1));

    lblHargaLayanan.setFont(new Font("Montserrat", 0, 14));
    lblHargaLayanan.setText("Harga Layanan");
    lblHargaLayanan.setForeground(new Color(255, 255, 255));
    add(lblHargaLayanan, new AbsoluteConstraints(190, 238, -1, -1));

    lblVHargaLayanan.setFont(new Font("Montserrat", 0, 14));
    lblVHargaLayanan.setText("-");
    lblVHargaLayanan.setForeground(new Color(255, 255, 255));
    add(lblVHargaLayanan, new AbsoluteConstraints(350, 238, -1, -1));

    jLabel3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/MekanikTiktok.png")));
    jLabel3.setText("jLabel3");
    jLabel3.addMouseListener(this);
    add(jLabel3, new AbsoluteConstraints(-30, 70, 200, -1));

    jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/assets/Riwayat_service.png")));
    jLabel1.setText("jLabel1");
    add(jLabel1, new AbsoluteConstraints(0, 0, 1250, -1));
  }

  private JButton btnCetak;
  private JButton btnKonfirmasi;
  private JComboBox<String> cbMetodePembayaran;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JScrollPane jScrollPane2;
  private JLabel lblBiaya;
  private JLabel lblDetailPembayaran;
  private JLabel lblPerbaikan;
  private JLabel lblVNamaLayanan;
  private JLabel lblNamaLayanan;
  private JLabel lblVHargaLayanan;
  private JLabel lblHargaLayanan;
  private JLabel lblMetodePembayaran;
  private JLabel lblKasir;
  private JLabel lblTotalBiaya;
  private JLabel riwayatoren;
  private JTable tblPembayaran;
  private JTextField tfKasir;
  private JTextField tfPerbaikan;

  private String idPembayaran;

  private String generateID() {
    return IDGenerator.generateID("PY", 10);
  }

  final TemplatePrinter templatePrinter = new TemplatePrinter();

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == btnKonfirmasi) {
      idPembayaran = generateID();
      final HashMap<String, Object> params = processInput();
      final String idPerbaikan = tfPerbaikan.getText().trim();
      if (pembayaranHelper.insertData(idPembayaran, params)) {
        for (Object[] data : dataList) {
          final String idSparepart = data[0].toString();
          final int subtotal = (int) data[3];
          if (!pembayaranHelper.insertDetailPembayaran(idPembayaran, idSparepart, subtotal)) {
            MessageUtil.showErrorMessageDialog(null, "Gagal melakukan konfirmasi pembayaran!");
            return;
          }
        }
        pembayaranHelper.insertPembayaran(idPembayaran, idPerbaikan);
        MessageUtil.showSucessMessageDialog(this, "Berhasil konfirmasi pembayaran!");
        clearInput();
        return;
      }
      MessageUtil.showErrorMessageDialog(this, "Gagal konfirmasi pembayaran!");
    } else if (event.getSource() == btnCetak) {
      if (idPembayaran == null) {
        MessageUtil.showErrorMessageDialog(this, "Pembayaran belum dikonfirmasi!");
        return;
      }
      try {
        templatePrinter.printReceipt(idPembayaran);
      } catch (TemplateException exception) {
        MessageUtil.showErrorMessageDialog(this, "Gagal mencetak pembayaran!");
        exception.printStackTrace();
      }
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
    try {
      final String id = tfPerbaikan.getText().trim();
      final ResultSet resultSet = pembayaranHelper.getDataById(id);
      if (resultSet == null) {
        MessageUtil.showErrorMessageDialog(this, "ID Perbaikan tidak ditemukan!");
        return;
      }
      while (resultSet.next()) {
        lblVNamaLayanan.setText(resultSet.getString("jenis_layanan"));
        lblVHargaLayanan.setText(resultSet.getString("biaya_layanan"));
        final String idSparepart = resultSet.getString("id_sparepart");
        final String jenisSparepart = resultSet.getString("jenis_sparepart");
        final int jumlah = resultSet.getInt("jumlah_sparepart");
        final int harga = (int) dataSpareparts.get(idSparepart);
        final int subtotal = jumlah * harga;
        final Object[] data = { idSparepart, jenisSparepart, jumlah, subtotal };
        dataList.add(data);
      }
      tblPembayaran.setModel(getUpdatedModel(tblPembayaran, dataList));
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void clearInput() {
    tfPerbaikan.setText(null);
    tfKasir.setText(null);
    lblVNamaLayanan.setText(null);
    lblVHargaLayanan.setText(null);
    lblBiaya.setText(null);
    dataList.clear();
    tblPembayaran.setModel(getUpdatedModel(tblPembayaran, dataList));
  }

  @Override
  public HashMap<String, Object> processInput() {
    final String idPerbaikan = tfPerbaikan.getText().trim();
    final String idKasir = tfKasir.getText().trim();
    final String metodePembayaran = cbMetodePembayaran.getSelectedItem().toString();
    final HashMap<String, Object> params = new HashMap<>();
    params.put("id_perbaikan", idPerbaikan);
    params.put("id_kasir", idKasir);
    params.put("metode_pembayaran", metodePembayaran);
    params.put("total_harga", totalPrice);
    return params;
  }

  @Override
  public void keyTyped(KeyEvent event) {}

  @Override
  public void keyPressed(KeyEvent event) {
    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
      loadData();
      calculateTotal();
    }
  }

  private int totalPrice;

  private void calculateTotal() {
    totalPrice = 0;
    for (int i = 0; i < tblPembayaran.getRowCount(); i++) {
      totalPrice += Integer.parseInt(tblPembayaran.getValueAt(i, 3).toString());
    }
    if (isNumber(lblVHargaLayanan.getText().trim())) {
      totalPrice += Integer.parseInt(lblVHargaLayanan.getText());
    }
    lblBiaya.setText(String.valueOf(totalPrice));
  }

  @Override
  public void keyReleased(KeyEvent event) {}
}
