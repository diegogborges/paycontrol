package com.paycontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "invoice_paid")
public class InvoicePaid implements Serializable {

    private static final long serialVersionUID = 1319962680812496538L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "person_signed_plan_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_INVOICE_PAID_PERSON_SIGNED_PLAN")
    )
    private PersonSignedPlan personSignedPlan;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Type(type = "numeric_boolean")
    private Boolean paid;

    @Column(name = "pay_day")
    private LocalDateTime payDay;
}
