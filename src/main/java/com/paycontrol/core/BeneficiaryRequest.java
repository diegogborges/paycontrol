package com.paycontrol.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE, staticName = "create")
@Getter
public class BeneficiaryRequest {

    private final String idCustomer;
}
