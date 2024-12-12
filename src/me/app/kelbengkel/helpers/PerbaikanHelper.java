package me.app.kelbengkel.helpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import me.app.kelbengkel.utils.MessageUtil;

public class PerbaikanHelper extends Helper {
  public PerbaikanHelper() {
    super("riwayat_perbaikan");
  }

  public ResultSet getReservasiById(int id) {
    try {
      final String sql = "SELECT * FROM reservasi WHERE id_reservasi = ?";
      this.createConnection();
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setInt(1, id);
      preparedStatement.executeQuery();
      final ResultSet resultSet = preparedStatement.getResultSet();
      resultSet.next();
      return resultSet;
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return null;
    } finally {
      this.closeConnection();
    }
  }

  public ResultSet getSpareparts() {
    try {
      final String sql = "SELECT * FROM sparepart";
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

  public ResultSet getMechanics() {
    try {
      final String sql = "SELECT * FROM mekanik";
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

  public ResultSet getServices() {
    try {
      final String sql = "SELECT * FROM layanan";
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

  public boolean isReservasiExist(int idReservasi) {
    try {
      final String sql = "SELECT * FROM reservasi WHERE id_reservasi = ?";
      this.createConnection();
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setInt(1, idReservasi);
      return preparedStatement.executeQuery().next();
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return false;
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
      try {
        this.getConnection().rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return false;
    }
  }

  public boolean insertDetailPerbaikan(String idDetailPerbaikan, String idSparepart, int jumlah) {
    try {
      final String sql = "INSERT INTO detail_perbaikan (id_detail_perbaikan, id_sparepart, jumlah_sparepart) VALUES (?, ?, ?)";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, idDetailPerbaikan);
      preparedStatement.setString(2, idSparepart);
      preparedStatement.setInt(3, jumlah);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      try {
        this.getConnection().rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return false;
    }
  }

  public boolean updateSparepartById(String id, int jumlah) {
    try {
      final String sql = "UPDATE sparepart SET stock_sparepart = stock_sparepart - ? WHERE id_sparepart = ?";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setInt(1, jumlah);
      preparedStatement.setString(2, id);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      try {
        this.getConnection().rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return false;
    }
  }

  public boolean insertPerbaikan(String idPerbaikan, int idReservasi) {
    try {
      final String sql = "INSERT INTO perbaikan (id_perbaikan, id_reservasi) VALUES (?, ?)";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, idPerbaikan);
      preparedStatement.setInt(2, idReservasi);
      preparedStatement.executeUpdate();
      MessageUtil.showSucessMessageDialog(null, "Berhasil menambah data perbaikan!");
      this.getConnection().commit();
      return true;
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      MessageUtil.showErrorMessageDialog(null, "Gagal menambah data perbaikan!");
      try {
        this.getConnection().rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return false;
    } finally {
      this.closeConnection();
    }
  }

  public int getKilometerById(String id) {
    try {
      final String sql = "SELECT kilometer FROM kendaraan WHERE id_kendaraan = ?";
      this.createConnection();
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, id);
      preparedStatement.executeQuery();
      final ResultSet resultSet = preparedStatement.getResultSet();
      resultSet.next();
      return resultSet.getInt("kilometer");
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return -1;
    } finally {
      this.closeConnection();
    }
  }
}
