package com.dreamhouserealty.pages;

import com.dreamhouserealty.behavior.UpdateTaskStatusBehavior;
import com.dreamhouserealty.dao.TaskDAO;
import com.dreamhouserealty.model.entity.Task;
import com.dreamhouserealty.model.enums.TaskStatus;
import com.dreamhouserealty.services.TaskService;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HomePage extends BasePage {
  private UpdateTaskStatusBehavior updateTaskStatusBehavior;

  public HomePage() {
    super();
  }

  @Override
  protected void onInitialize() {
    super.onInitialize();
    TaskService taskService = new TaskService(new TaskDAO());
    updateTaskStatusBehavior = new UpdateTaskStatusBehavior(taskService);
    add(updateTaskStatusBehavior);

    Label callbackScript = new Label("callbackScript");
    add(callbackScript);

    List<Task> tasks = taskService.getAllTasks();
    Map<TaskStatus, List<Task>> tasksByStatus = tasks.stream()
        .collect(Collectors.groupingBy(Task::getStatus));

    List<Task> todoTasks = tasksByStatus.get(TaskStatus.TODO);
    ListView<Task> todoListView = createTaskListView("todoListView", "taskCardPanelContainer", todoTasks);
    todoListView.setReuseItems(true);
    add(todoListView);

    List<Task> inProgressTasks = tasksByStatus.get(TaskStatus.IN_PROGRESS);
    ListView<Task> inProgressListView =
        createTaskListView("inProgressListView", "inProgressCardPanelContainer", inProgressTasks);
    inProgressListView.setReuseItems(true);
    add(inProgressListView);

    List<Task> doneTasks = tasksByStatus.get(TaskStatus.DONE);
    ListView<Task> doneListView = createTaskListView("doneListView", "doneCardPanelContainer", doneTasks);
    doneListView.setReuseItems(true);
    add(doneListView);
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    response.render(
        JavaScriptHeaderItem.forScript("var callbackUrl = '" + updateTaskStatusBehavior.getCallbackUrl() + "';",
            "callback-url"));
  }

  private ListView<Task> createTaskListView(String id, String panelId, List<Task> tasks) {
    return new ListView<>(id, tasks) {
      @Override
      protected void populateItem(ListItem<Task> item) {
        Task task = item.getModelObject();

        TaskCardPanel taskCardPanel = new TaskCardPanel(panelId, task);
        item.add(taskCardPanel);
      }
    };
  }
}
