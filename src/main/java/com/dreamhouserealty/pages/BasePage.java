package com.dreamhouserealty.pages;

import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage {
  public BasePage() {
    add(new HeadPanel("head"));
    add(new NavBarPanel("navbar"));
    add(new FooterPanel("footer"));
  }

}
