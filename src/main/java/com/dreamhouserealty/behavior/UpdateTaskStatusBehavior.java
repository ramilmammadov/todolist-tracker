package com.dreamhouserealty.behavior;

import com.dreamhouserealty.model.enums.TaskStatus;
import com.dreamhouserealty.services.TaskService;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.string.StringValue;


public class UpdateTaskStatusBehavior extends AbstractAjaxBehavior {

  private final TaskService taskService;

  public UpdateTaskStatusBehavior(TaskService taskService) {
    this.taskService = taskService;
  }

  @Override
  public void onRequest() {
    Request request = getComponent().getRequest();
    Response response = getComponent().getResponse();

    // Get request parameters
    StringValue taskIdValue = request.getRequestParameters().getParameterValue("taskId");
    StringValue newStatusValue = request.getRequestParameters().getParameterValue("newStatus");

    if (!taskIdValue.isEmpty() && !newStatusValue.isEmpty()) {
      Long taskId = taskIdValue.toLong();
      TaskStatus newStatus = TaskStatus.valueOf(newStatusValue.toString());

      // Update task status
      taskService.updateTaskStatus(taskId, newStatus);
      response.write("Status updated");
    } else {
      // Handle missing parameters
      response.write("Error: Missing parameters");
    }
  }
}
