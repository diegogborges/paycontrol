package com.paycontrol.exception;

public class MissingParameterException extends BaseControllerException {

  private static final long serialVersionUID = 392850972224453488L;

  public MissingParameterException(Object... parameters) {
    super(parameters);
  }
}
