package com.dreamhouserealty.pages;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import com.dreamhouserealty.model.entity.Task;
import org.apache.wicket.model.Model;

public class TaskCardPanel extends Panel {
  public TaskCardPanel(String id, Task task) {
    super(id);

    WebMarkupContainer cardDiv = new WebMarkupContainer("taskCard");
    cardDiv.setOutputMarkupId(true); // Ensure that the div has an ID
    cardDiv.add(new AttributeAppender("data-task-id", Model.of(String.valueOf(task.getId()))));
    add(cardDiv);

    cardDiv.add(new Label("taskTitle", task.getTitle()));
    cardDiv.add(new Label("taskDescription", task.getDescription()));

    // Set the markup id for child components
    cardDiv.setMarkupId(id);
  }
}
