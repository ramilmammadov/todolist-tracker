package com.dreamhouserealty.model.dto;

import com.dreamhouserealty.model.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskStatusDTO {
  private Long id;
  private TaskStatus status;
}
