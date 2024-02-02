package com.dreamhouserealty;

import com.dreamhouserealty.model.enums.TaskStatus;

import javax.persistence.*;
import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  private TaskStatus status;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

}
