package com.dreamhouserealty.model.entity;

import com.dreamhouserealty.model.enums.TaskStatus;

import java.io.Serializable;
import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable(false)
public class Task implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  @ColumnTransformer(write = "?::task_status")
  private TaskStatus status;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

}
