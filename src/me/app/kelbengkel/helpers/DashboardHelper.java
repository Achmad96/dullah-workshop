package me.app.kelbengkel.helpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardHelper extends Helper {
  public DashboardHelper() {
    super("kendaraan");
  }

  @Override
  public ResultSet getAllData() {
    try {
      this.createConnection();
      final String sql = "SELECT k.id_kendaraan, k.merk AS nama_kendaraan, p.id_pelanggan, p.nama_pelanggan FROM " + this.getTableName() + " k "
          + "INNER JOIN pelanggan p ON k.id_pelanggan = p.id_pelanggan";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      System.out.println(sql);
      return preparedStatement.executeQuery();
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return null;
    } finally {
      this.closeConnection();
    }
  }
}
