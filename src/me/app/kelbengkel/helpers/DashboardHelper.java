package me.app.kelbengkel.helpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardHelper extends Helper {
  public DashboardHelper() {
    super("reservasi");
  }

  @Override
  public ResultSet getAllData() {
    try {
      this.createConnection();
      final String sql = "SELECT r.id_reservasi, r.id_kendaraan, r.id_mekanik, rp.id_layanan FROM " + this.getTableName()
          + " r INNER JOIN perbaikan pn ON r.id_reservasi = pn.id_reservasi INNER JOIN riwayat_perbaikan rp ON rp.id_riwayat_perbaikan = pn.id_perbaikan";
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
