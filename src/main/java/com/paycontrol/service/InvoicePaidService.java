package com.paycontrol.service;

import com.paycontrol.exception.ResourceNotFoundException;
import com.paycontrol.model.InvoicePaid;
import com.paycontrol.model.PersonSignedPlan;
import com.paycontrol.model.SignedPlan;
import com.paycontrol.repository.InvoicePaidRepository;
import com.paycontrol.repository.PersonSignedPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InvoicePaidService {

    private final PersonSignedPlanRepository personSignedPlanRepository;
    private final SignedPlanService signedPlanService;
    private final InvoicePaidRepository invoicePaidRepository;

    public List<InvoicePaid> findAllInvoicePaid() {
        return invoicePaidRepository.findAll();
    }

    public InvoicePaid save(final Long invoicePaidId, final InvoicePaid invoicePaid) {

        if (Optional.ofNullable(invoicePaidId).isPresent()) {
            final InvoicePaid invoicePaidExists = findById(invoicePaidId);
            invoicePaid.setId(invoicePaidExists.getId());
        }

        return invoicePaidRepository.save(invoicePaid);
    }

    public InvoicePaid findById(Long invoicePaidId) {
        return Optional.of(invoicePaidRepository.findById(invoicePaidId)).get()
                .orElseThrow(() -> new ResourceNotFoundException("InvoicePaid not found with id " + invoicePaidId));
    }

    // in progress
    public void sendInvoiceToPerson() {
        /**
         * roda uma vez ao mês
         * verifica quem deve pagar a conta do plano no mês atual
         * envia notificação
         *
         *
         Uma pessoa pode ter mais de um plano e só uma vez para cada plano.
         Uma pessoa pode sair do plano, assim ficando desativada.
         A próxima pessoa a pagar é a pessoa seguinte da sequência registrada no plano
         O Diego foi o primeiro que tem a sequência 1, o próximo é o da sequência 2, o José.
         Ao acabar o número de sequência, ou seja, finalizou um ciclo, deve voltar para o primeiro número da sequência que está ativo.
         A pessoa a pagar deve ser notificada por WhatsApp e e-mail junto ao PIX para pagamento.

         nunca teve pagamentos
         já teve pagamentos
         final do fluxo

         */

        final InvoicePaid invoicePaid = InvoicePaid.builder()
                .paid(false)
                .payDay(LocalDateTime.now())
                .build();

        List<SignedPlan> signedPlanList = signedPlanService.findAllSignedPlan();

        signedPlanList.forEach(signedPlan -> {

            // para cada plano

            final Integer lastSequencePaidBySignedPlanId =
                    invoicePaidRepository.getLastSequencePaidBySignedPlanId(signedPlan.getId());


            // vazio ou o ultimo do fluxo?
            if (Optional.ofNullable(lastSequencePaidBySignedPlanId).isEmpty()) {
                List<PersonSignedPlan> personSignedPlans =
                        personSignedPlanRepository.findPersonSignedPlanBySignedPlanIdOrderBySequenceAsc(signedPlan.getId());

                invoicePaid.setPersonSignedPlan(personSignedPlans.get(0));
                invoicePaidRepository.save(invoicePaid);
            } else {
                final List<PersonSignedPlan> allBySignedPlanIdAndSequence
                        = personSignedPlanRepository.findAllBySignedPlanIdAndSequence(signedPlan.getId(), lastSequencePaidBySignedPlanId+1);
                invoicePaid.setPersonSignedPlan(allBySignedPlanIdAndSequence.get(0));
                invoicePaidRepository.save(invoicePaid);
            }
        });
    }

}
