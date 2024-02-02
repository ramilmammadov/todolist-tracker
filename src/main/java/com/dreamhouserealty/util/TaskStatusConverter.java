package com.dreamhouserealty.util;


import com.dreamhouserealty.model.enums.TaskStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {

  @Override
  public String convertToDatabaseColumn(TaskStatus status) {
    if (status == null) {
      return null;
    }
    return status.name();
  }

  @Override
  public TaskStatus convertToEntityAttribute(String status) {
    if (status == null) {
      return null;
    }
    return TaskStatus.valueOf(status);
  }
}
