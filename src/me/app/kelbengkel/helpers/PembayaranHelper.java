package me.app.kelbengkel.helpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import me.app.kelbengkel.utils.MessageUtil;

public class PembayaranHelper extends Helper {
  public PembayaranHelper() {
    super("riwayat_pembayaran");
  }

  public ResultSet getSpareparts() {
    try {
      final String sql = "SELECT id_sparepart, harga_sparepart FROM sparepart";
      this.createConnection();
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      return preparedStatement.executeQuery();
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return null;
    } finally {
      this.closeConnection();
    }
  }

  @Override
  public ResultSet getDataById(String id) {
    try {
      this.createConnection();
      final String sql = """
          SELECT rpn.id_layanan, l.jenis_layanan, l.biaya_layanan, st.id_sparepart, st.jenis_sparepart, dpn.jumlah_sparepart FROM perbaikan p
          INNER JOIN riwayat_perbaikan rpn ON rpn.id_riwayat_perbaikan = p.id_perbaikan
          INNER JOIN detail_perbaikan dpn ON dpn.id_detail_perbaikan = rpn.id_riwayat_perbaikan
          INNER JOIN sparepart st ON st.id_sparepart = dpn.id_sparepart
          INNER JOIN layanan l ON l.id_layanan = rpn.id_layanan
          WHERE p.id_perbaikan = ?""";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, id);
      preparedStatement.executeQuery();
      return preparedStatement.getResultSet();
    } catch (SQLException exception) {
      exception.printStackTrace();
      return null;
    } finally {
      this.closeConnection();
    }
  }

  @Override
  public boolean insertData(String id, HashMap<String, Object> parameters) {
    try {
      this.createConnection();
      this.getConnection().setAutoCommit(false);
      final String sql = processInsertParameter(parameters);
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, id);
      int i = 2;
      for (Object parameter : parameters.values()) {
        preparedStatement.setObject(i++, parameter);
      }
      System.out.println(sql);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      MessageUtil.showErrorMessageDialog(null, "Gagal melakukan konfirmasi pembayaran!");
      try {
        this.getConnection().rollback();
      } catch (SQLException exception2) {
        exception2.printStackTrace();
      }
      return false;
    }
  }

  public boolean insertDetailPembayaran(String idDetailPembayaran, String idSparepart, int subtotal) {
    try {
      final String sql = "INSERT INTO detail_pembayaran (id_detail_pembayaran, id_sparepart, subtotal) VALUES (?, ?, ?)";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, idDetailPembayaran);
      preparedStatement.setString(2, idSparepart);
      preparedStatement.setInt(3, subtotal);
      preparedStatement.executeUpdate();
      System.out.println(sql);
      return true;
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      try {
        this.getConnection().rollback();
      } catch (SQLException exception2) {
        exception2.printStackTrace();
        return false;
      }
      return false;
    }
  }

  public void insertPembayaran(String idPembayaran, String idPerbaikan) {
    try {
      final String sql = "INSERT INTO pembayaran(id_pembayaran, id_perbaikan) VALUES (?, ?)";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, idPembayaran);
      preparedStatement.setString(2, idPerbaikan);
      preparedStatement.executeUpdate();
      this.getConnection().commit();
      System.out.println(sql);
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      MessageUtil.showErrorMessageDialog(null, "Gagal melakukan konfirmasi pembayaran!");
      try {
        this.getConnection().rollback();
      } catch (SQLException exception2) {
        exception2.printStackTrace();
      }
    }
  }
}