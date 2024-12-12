package me.app.kelbengkel.helpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ReservasiHelper extends Helper {
  public ReservasiHelper() {
    super("reservasi");
  }

  public ResultSet getMekanik() {
    try {
      this.createConnection();
      final String sql = "SELECT * FROM mekanik";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
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
  public boolean insertData(String id, HashMap<String, Object> params) {
    try {
      this.createConnection();
      final String sql = "INSERT INTO reservasi (id_kendaraan, id_mekanik) VALUES (?, ?)";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, params.get("id_kendaraan").toString());
      preparedStatement.setString(2, params.get("id_mekanik").toString());
      System.out.println(sql);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException exception) {
      exception.printStackTrace();
      return false;
    } finally {
      this.closeConnection();
    }
  }

  @Override
  public ResultSet getAllData() {
    try {
      this.createConnection();
      final String sql = "SELECT * FROM reservasi";
      PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.executeQuery();
      return preparedStatement.getResultSet();
    } catch (SQLException exception) {
      exception.printStackTrace();
      return null;
    } finally {
      this.closeConnection();
    }
  }
}
