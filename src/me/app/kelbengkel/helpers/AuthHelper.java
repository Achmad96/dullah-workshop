package me.app.kelbengkel.helpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthHelper extends Helper {

  public AuthHelper() {
    super("user");
  }

  public ResultSet getValidationData(String email, String password) {
    try {
      this.createConnection();
      final String sql = "SELECT nama FROM \"" + this.getTableName() + "\" WHERE email = ? AND password = md5(?)";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      System.out.println(sql);
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      preparedStatement.executeQuery();
      return preparedStatement.getResultSet();
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return null;
    } finally {
      this.closeConnection();
    }
  }

  public boolean insertData(String email, ArrayList<String> parameters) {
    try {
      this.createConnection();
      final String sql = "INSERT INTO \"" + this.getTableName() + "\" (email, password, nama, alamat, notelp) VALUES (?, md5(?), ?, ?, ?)";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, email);
      int i = 2;
      for (Object parameter : parameters.toArray()) {
        preparedStatement.setObject(i++, parameter);
      }
      System.out.println(sql);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return false;
    } finally {
      this.closeConnection();
    }
  }
}
