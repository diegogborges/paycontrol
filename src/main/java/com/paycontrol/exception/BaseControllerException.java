package com.paycontrol.exception;

public class BaseControllerException extends RuntimeException {

  private static final long serialVersionUID = -7500971566870374025L;
  private final Object[] parameters;

  public BaseControllerException(final Object... parameters) {
    this.parameters = parameters;
  }

  public Object[] getParameters() {
    return (Object[])this.parameters.clone();
  }
}
