package me.app.kelbengkel.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
  /**
   * Checks if a given string is a valid number or not. A valid number is a string
   * that can be parsed to an integer without throwing a
   * {@link NumberFormatException}.
   * 
   * @param str the string to be checked
   * @return true if the string is a valid number, false otherwise
   */
  public static boolean isNumber(String str) {
    try {
      Integer.valueOf(str);
      return true;
    } catch (NumberFormatException exception) {
      return false;
    }
  }

  /**
   * Checks if a given string is a valid email address or not. A valid email
   * address is a string that matches the following pattern:
   * 
   * <pre>
   * ^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$
   * </pre>
   * 
   * @param email the email address to be checked
   * @return true if the email address is valid, false otherwise
   */
  public static boolean isValidEmail(String email) {
    final String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    final Pattern pattern = Pattern.compile(emailRegex);
    if (email == null) {
      return false;
    }
    final Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * Checks if a given password is valid or not. A valid password is a string that
   * contains at least one letter and one number and has a length of at least 8
   * characters.
   * 
   * @param password the password to be checked
   * @return true if the password is valid, false otherwise
   */
  public static boolean isValidPassword(String password) {
    final String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    final Pattern pattern = Pattern.compile(passwordRegex);
    if (password == null) {
      return false;
    }
    final Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }

}
