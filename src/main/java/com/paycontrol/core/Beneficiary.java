package com.paycontrol.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
@Getter
public class Beneficiary {

    private final String id;
    private final String nickName;
}
