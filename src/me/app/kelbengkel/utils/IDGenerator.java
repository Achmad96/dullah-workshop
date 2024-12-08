package me.app.kelbengkel.utils;

import java.util.Random;

/**
 * @author Achmad Raihan
 */
public class IDGenerator {

  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public static String generateID(String prefix, int maxLength) {
    final long currentTimeMillis = System.currentTimeMillis();
    String timePart = Long.toString(currentTimeMillis);
    int availableLength = maxLength - prefix.length() - timePart.length();
    if (availableLength < 0) {
      int excessLength = -(availableLength);
      if (timePart.length() > excessLength) {
        timePart = timePart.substring(0, timePart.length() - excessLength);
      }
      return prefix + timePart;
    }
    final String randomPart = getRandomString(availableLength);
    return prefix + timePart + randomPart;
  }

  private static String getRandomString(int length) {
    final Random random = new Random();
    final StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      builder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
    }
    return builder.toString();
  }

}
