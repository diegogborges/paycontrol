package com.paycontrol.repository;

import com.paycontrol.model.InvoicePaid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicePaidRepository extends JpaRepository<InvoicePaid, Long> {

    InvoicePaid findFirstByPersonSignedPlanIdOrderByIdDesc(final Long personSignedPlanId);

    @Query(
            value = "select psp.sequence " +
                    "  from paycontrol.invoice_paid i " +
                    "inner join paycontrol.person_signed_plan psp " +
                    "        on psp.id = i.person_signed_plan_id " +
                    "inner join paycontrol.signed_plan sp " +
                    "        on sp.id = psp.signed_plan_id " +
                    "where sp.id = ?1 " +
                    "order by 1 desc " +
                    "limit 1;",
            nativeQuery = true)
    Integer getLastSequencePaidBySignedPlanId(Long signedPlanId);
}
