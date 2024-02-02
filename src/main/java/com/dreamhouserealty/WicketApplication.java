package com.dreamhouserealty;

import com.dreamhouserealty.pages.HomePage;
import com.dreamhouserealty.util.HibernateUtil;
import com.dreamhouserealty.util.properties.DatabaseProperties;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start
 * class.
 *
 * @see com.dreamhouserealty.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
  private SessionFactory sessionFactory;

  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  @Override
  public Class<? extends WebPage> getHomePage() {
    return HomePage.class;
  }

  /**
   * @see org.apache.wicket.Application#init()
   */
  @Override
  public void init() {
    super.init();
    HibernateUtil.initialize();
    getCspSettings().blocking().disabled();
  }
}
