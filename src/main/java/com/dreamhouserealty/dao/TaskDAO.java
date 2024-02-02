package com.dreamhouserealty.dao;

import com.dreamhouserealty.model.dto.TaskDTO;
import com.dreamhouserealty.model.dto.UpdateTaskStatusDTO;
import com.dreamhouserealty.model.entity.Task;
import com.dreamhouserealty.model.enums.TaskStatus;
import com.dreamhouserealty.util.HibernateUtil;
import jakarta.persistence.FlushModeType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class TaskDAO implements Serializable {

  public void save(TaskDTO taskDTO) {
    Task task = new Task();
    task.setTitle(taskDTO.getTitle());
    task.setDescription(taskDTO.getDescription());
    task.setStatus(TaskStatus.TODO);
    task.setCreationDate(LocalDateTime.now());
    save(task);
  }

  public void save(Task task) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      if (task.getId() == null) {
        session.persist(task);
      } else {
        session.merge(task);
      }
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  public List<Task> getTasks() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery(" from Task", Task.class).list();
    }
  }

  public Task getTaskById(Long taskId) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.get(Task.class, taskId);
    }
  }

  public void updateStatus(UpdateTaskStatusDTO taskStatusDTO) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.setFlushMode(FlushModeType.COMMIT);

      // Retrieve the existing task from the database
      Task existingTask = session.get(Task.class, taskStatusDTO.getId());

      if (existingTask != null) {
        existingTask.setStatus(taskStatusDTO.getStatus());
        // Save changes
        session.merge(existingTask);
      } else {
        // Handle the case where the task does not exist in the database
        System.out.println("Task with ID " + taskStatusDTO.getId() + " does not exist.");
      }

      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw e;
    }
  }

}
