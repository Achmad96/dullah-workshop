package me.app.kelbengkel.helpers;

/**
 * @author Achmad Raihan
 */
import me.app.kelbengkel.utils.DatabaseConnection;

import java.sql.*;
import java.util.HashMap;

public class Helper {

  private Connection connection;
  private String tableName;

  public Helper(String tableName) {
    this.tableName = tableName;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getTableName() {
    return tableName;
  }

  protected void createConnection() {
    connection = DatabaseConnection.getConnection();
  }

  protected Connection getConnection() {
    return connection;
  }

  protected void closeConnection() {
    try {
      if (connection.isClosed()) {
        return;
      }
      connection.close();
      System.out.println("Connection closed...");
    } catch (SQLException exception) {
      System.out.println("Failed to close the connection due to " + exception.getMessage());
    }
  }

  public ResultSet getDataById(String id) {
    try {
      this.createConnection();
      final String sql = "SELECT * FROM " + tableName + " WHERE id_" + tableName + " = ? ORDER BY id ASC";
      final PreparedStatement preparedStatement = connection.prepareStatement(sql);
      System.out.println(sql);
      preparedStatement.setString(1, id);
      preparedStatement.executeQuery();
      return preparedStatement.getResultSet();
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return null;
    } finally {
      this.closeConnection();
    }
  }

  public ResultSet getAllData() {
    try {
      this.createConnection();
      final String sql = "SELECT * FROM " + tableName;
      final PreparedStatement preparedStatement = connection.prepareStatement(sql);
      System.out.println(sql);
      return preparedStatement.executeQuery();
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return null;
    } finally {
      this.closeConnection();
    }
  }

  public boolean deleteDataById(String id) {
    try {
      this.createConnection();
      final String sql = "DELETE FROM " + tableName + " WHERE id_" + tableName + " = ?";
      final PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, id);
      System.out.println(sql);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return false;
    } finally {
      this.closeConnection();
    }
  }

  public int deleteAllData() {
    try {
      this.createConnection();
      final String sql = "DELETE FROM " + tableName;
      final PreparedStatement preparedStatement = connection.prepareStatement(sql);
      System.out.println(sql);
      preparedStatement.execute();
      return preparedStatement.executeUpdate();
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return 0;
    } finally {
      this.closeConnection();
    }
  }

  public boolean insertData(String id) {
    try {
      this.createConnection();
      final String sql = "INSERT INTO " + tableName + "(id_" + tableName + ") VALUES (?)";
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, id);
      System.out.println(sql);
      preparedStatement.execute();
      return true;
    } catch (SQLException exception) {
      System.out.println("Terjadi kesalahan, " + exception.getMessage());
      return false;
    } finally {
      this.closeConnection();
    }
  }

  public boolean insertData(String id, HashMap<String, Object> parameters) {
    try {
      this.createConnection();
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
      return false;
    } finally {
      this.closeConnection();
    }
  }

  public boolean updateData(String id, HashMap<String, Object> parameters) {
    try {
      this.createConnection();
      final String sql = processUpdateParameter(parameters);
      final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
      int i = 1;
      for (Object parameter : parameters.values()) {
        if (!parameter.toString().trim().isEmpty()) {
          preparedStatement.setObject(i++, parameter);
        }
      }
      preparedStatement.setString(i, id);
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

  protected String processInsertParameter(HashMap<String, Object> options) {
    return this.processInsertParameter("id_" + tableName, options);
  }

  protected String processUpdateParameter(HashMap<String, Object> options) {
    return this.processUpdateParameter("id_" + tableName, options);
  }

  protected String processInsertParameter(String columnIdName, HashMap<String, Object> options) {
    final StringBuilder stringBuilder = new StringBuilder("INSERT INTO " + tableName + " (" + columnIdName + ", ");
    options.keySet().forEach(k -> stringBuilder.append(k).append(", "));
    stringBuilder.setLength(stringBuilder.length() - 2);
    stringBuilder.append(") VALUES (");
    options.forEach((a, b) -> stringBuilder.append("?, "));
    stringBuilder.append("?)");
    return stringBuilder.toString();
  }

  protected String processUpdateParameter(String columnIdName, HashMap<String, Object> options) {
    final StringBuilder stringBuilder = new StringBuilder("UPDATE " + tableName + " SET ");
    options.forEach((key, value) -> {
      if (!value.toString().trim().isEmpty()) {
        stringBuilder.append(key).append(" = ?, ");
      }
    });
    // Remove the last comma and space from the SET part
    stringBuilder.setLength(stringBuilder.length() - 2);
    stringBuilder.append(" WHERE ").append(columnIdName).append(" = ?");
    return stringBuilder.toString();
  }

}
