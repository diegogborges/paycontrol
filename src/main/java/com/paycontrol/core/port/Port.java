package com.paycontrol.core.port;

public interface Port<R, A> {

    R execute(final A argument) throws Exception;
}
