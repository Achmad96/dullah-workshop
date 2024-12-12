package me.app.kelbengkel.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class DatabaseConnection {
  private static Connection connection;
  private static String[] data;
  private final static String CONFIG_FILE = "resources/config/config.text";

  private DatabaseConnection() {}

  private static void loadData() {
    data = new String[6];
    try (InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      if (inputStream == null) {
        throw new IOException("Configuration file not found: " + CONFIG_FILE);
      }
      String line;
      while ((line = reader.readLine()) != null) {
        processLine(line);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
      System.err.println("Error reading the configuration file.");
    }
  }

  private static final Map<String, Integer> dataMapping = Map.of("key", 0, "iv", 1, "host", 2, "database", 3, "user", 4, "password", 5);

  private static void processLine(String line) {
    try {
      final String[] split = line.split("=");
      final String key = split[0];
      if (dataMapping.containsKey(key)) {
        if (key.equals("password")) {
          data[0] += "==";
          data[1] += "==";
          split[1] += "==";
          final SecretKey recoveredKey = AESUtil.stringToKey(data[0]);
          final IvParameterSpec recoveredIv = AESUtil.stringToIv(data[1]);
          split[1] = AESUtil.decrypt(split[1], recoveredKey, recoveredIv);
        }
        data[dataMapping.get(key)] = split[1].trim();
      }
    } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException exception) {
      exception.printStackTrace();
    }
  }

  public static Connection getConnection() {
    if (connection == null) {
      generatePassword();
    }
    loadData();
    try {
      final String host = data[2];
      final String database = data[3];
      final String user = data[4];
      final String password = data[5];
      final String URL = "jdbc:postgresql://" + host + "/" + database;
      connection = DriverManager.getConnection(URL, user, password);
      System.out.println("Estabilished connection to database");
    } catch (SQLException exception) {
      exception.printStackTrace();
      System.out.println("Failed to estabilish connection to database");
    }
    return connection;
  }

  private static String getPreviousPassword() {
    loadData();
    return data[5];
  }

  private static void generatePassword() {
    try {
      final SecretKey key = AESUtil.generateKey(128);
      final IvParameterSpec iv = AESUtil.generateIv();
      final String encodedKey = AESUtil.keyToString(key);
      final String encodedIv = AESUtil.ivToString(iv);
      final String encryptedPassword = AESUtil.encrypt(getPreviousPassword(), key, iv);
      final String host = data[2];
      final String database = data[3];
      final String user = data[4];
      final String path = DatabaseConnection.class.getClassLoader().getResource(CONFIG_FILE).toURI().getPath();
      final Path configPath = Paths.get(path.startsWith("/") ? path.substring(1) : path);
      try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(configPath, StandardOpenOption.WRITE), StandardCharsets.UTF_8))) {
        writer.write("key=" + encodedKey);
        writer.newLine();
        writer.write("iv=" + encodedIv);
        writer.newLine();
        writer.write("host=" + host);
        writer.newLine();
        writer.write("database=" + database);
        writer.newLine();
        writer.write("user=" + user);
        writer.newLine();
        writer.write("password=" + encryptedPassword);
      }
    } catch (URISyntaxException | IOException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException | BadPaddingException
        | IllegalBlockSizeException exception) {
      exception.printStackTrace();
    }
  }
}
