package com.paycontrol.component;

import com.paycontrol.service.InvoicePaidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class RunInvoicePaisMonthly {

    private final InvoicePaidService invoicePaidService;

    @PostConstruct
    private void runInvoicePaid () {
        invoicePaidService.sendInvoiceToPerson();
    }
}
