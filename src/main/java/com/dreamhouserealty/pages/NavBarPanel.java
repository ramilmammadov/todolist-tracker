package com.dreamhouserealty.pages;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class NavBarPanel extends Panel {
  public NavBarPanel(String id) {
    super(id);

    Link<Void> homePageLink = new Link<Void>("homePageLink") {
      @Override
      public void onClick() {
        setResponsePage(HomePage.class);
      }
    };
    add(homePageLink);
  }
}
