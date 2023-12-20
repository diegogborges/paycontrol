package com.paycontrol.v1.validator;

import com.paycontrol.exception.MissingParameterException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

@Component
public class PersonValidation {

  public void validatesFieldsByMessageType(final Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors, "name", "error.name", "name");

    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors, "email", "error.email", "email");

    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors, "phone", "error.phone", "phone");

    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors, "status", "error.status", "status");

    if (errors.hasErrors()) {
      throw new MissingParameterException("body", messageError(errors.getAllErrors()));
    }
  }

  private String messageError(final List<ObjectError> errors) {
    final List<String> listErrors = new ArrayList<>();
    errors.forEach(e ->
        listErrors.add(e.getDefaultMessage()));
    return listErrors.toString();
  }
}
