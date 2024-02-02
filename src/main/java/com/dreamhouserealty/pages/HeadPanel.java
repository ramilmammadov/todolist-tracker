package com.dreamhouserealty.pages;

import org.apache.wicket.markup.head.*;
import org.apache.wicket.markup.html.panel.Panel;

public class HeadPanel extends Panel {
  public HeadPanel(String id) {
    super(id);
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);

    // Meta tags
    response.render(StringHeaderItem.forString("<meta charset=\"UTF-8\">"));
    response.render(
        StringHeaderItem.forString("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"));
    response.render(StringHeaderItem.forString("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"));

    // Title
    response.render(StringHeaderItem.forString("<title>DreamHouseRealty.com</title>"));

    // External CSS
    response.render(
        CssReferenceHeaderItem.forUrl("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"));
    response.render(
        CssReferenceHeaderItem.forUrl("https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap"));
    response.render(CssReferenceHeaderItem.forUrl("resources/css/index.css"));

    // Inline CSS
    String inlineCss =
        "body, html { height: 100%; } #page-content { flex: 1 0 auto; } #sticky-footer { flex-shrink: none; }";
    response.render(OnDomReadyHeaderItem.forScript(
        "var style = document.createElement('style'); style.innerHTML = '" + inlineCss
            + "'; document.head.appendChild(style);"));

    // External JS
    response.render(
        JavaScriptReferenceHeaderItem.forUrl("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"));
    response.render(
        JavaScriptReferenceHeaderItem.forUrl("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"));
  }
}
