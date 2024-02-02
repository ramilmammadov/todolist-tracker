package com.dreamhouserealty.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
  private static final Properties properties = new Properties();

  static {
    try {
      InputStream inputStream = DatabaseProperties.class.getResourceAsStream("/database.properties");
      properties.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException("Error loading database.properties", e);
    }
  }

  public static String getUrl() {
    return properties.getProperty("database.url");
  }

  public static String getUsername() {
    return properties.getProperty("database.username");
  }

  public static String getPassword() {
    return properties.getProperty("database.password");
  }
}
