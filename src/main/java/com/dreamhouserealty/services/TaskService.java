package com.dreamhouserealty.services;

import com.dreamhouserealty.model.dto.TaskDTO;
import com.dreamhouserealty.model.dto.UpdateTaskStatusDTO;
import com.dreamhouserealty.model.entity.Task;
import com.dreamhouserealty.dao.TaskDAO;
import com.dreamhouserealty.model.enums.TaskStatus;

import java.io.Serializable;
import java.util.List;

public class TaskService implements Serializable {

  private final TaskDAO taskDAO;

  public TaskService(TaskDAO taskDAO) {
    this.taskDAO = taskDAO;
  }

  public List<Task> getAllTasks() {
    return taskDAO.getTasks();
  }

  public void saveTask(TaskDTO task) {
    taskDAO.save(task);
  }

  public void updateTaskStatus(Long taskId, TaskStatus newStatus) {
    Task task = taskDAO.getTaskById(taskId);
    if (task != null) {
      UpdateTaskStatusDTO dto = new UpdateTaskStatusDTO();
      dto.setId(task.getId());
      dto.setStatus(newStatus);
      taskDAO.updateStatus(dto);
    }
  }

}
