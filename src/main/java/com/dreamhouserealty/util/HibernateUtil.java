package com.dreamhouserealty.util;

import com.dreamhouserealty.model.entity.Task;
import com.dreamhouserealty.util.properties.DatabaseProperties;
import jakarta.persistence.Entity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.flywaydb.core.Flyway;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class HibernateUtil {
  private static SessionFactory sessionFactory;

  public static void initialize() {
    try {
      // Initialize Flyway and run migrations
      Flyway flyway = Flyway.configure()
          .dataSource(DatabaseProperties.getUrl(), DatabaseProperties.getUsername(), DatabaseProperties.getPassword())
          .baselineOnMigrate(true)
          .locations("db/migration")
          .load();
      flyway.migrate();

      Configuration configuration = new Configuration();

      // Set Hibernate properties programmatically
      configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
      configuration.setProperty("hibernate.connection.url", DatabaseProperties.getUrl());
      configuration.setProperty("hibernate.connection.username", DatabaseProperties.getUsername());
      configuration.setProperty("hibernate.connection.password", DatabaseProperties.getPassword());
      configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
      configuration.setProperty("hibernate.current_session_context_class", "thread");
      configuration.setProperty("hibernate.cache.use_second_level_cache", "false");
      configuration.setProperty("hibernate.cache.use_query_cache", "false");
      configuration.setProperty("hibernate.hbm2ddl.auto", "update");
      configuration.setProperty("hibernate.hbm2ddl.format_sql", "true");
      configuration.setProperty("hibernate.hbm2ddl.delimiter", ";");
      configuration.setProperty("hibernate.show_sql", "true");
      configuration.addAnnotatedClass(Task.class);

      // Updated Reflections configuration
      Reflections reflections = new Reflections(new ConfigurationBuilder()
          .forPackages("com.dreamhouserealty") // specify your package
          .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new TypeAnnotationsScanner()));

      Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
      for (Class<?> clazz : entityClasses) {
        configuration.addAnnotatedClass(clazz);
      }
      StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
          .applySettings(configuration.getProperties())
          .build();

      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

}
