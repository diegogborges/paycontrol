package com.paycontrol.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "person_signed_plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PersonSignedPlan implements Serializable {

  private static final long serialVersionUID = 1319962680812479248L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "person_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_PERSON_SIGNED_PERSON")
  )
  private Person person;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "signed_plan_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_PERSON_SIGNED_SIGNED_PLAN")
  )
  private SignedPlan signedPlan;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "sequence")
  private Integer sequence;

  @Column(name = "initial_date")
  private LocalDateTime initialDate;

  @PrePersist
  private void beforePersist() {
    this.createdAt = LocalDateTime.now();
  }
}
