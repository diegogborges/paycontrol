package com.paycontrol.v1.validator;

import com.paycontrol.exception.MissingParameterException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class SignedPlanValidation {

  public void validatesFieldsByMessageType(final Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors, "description", "error.description", "description");

    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors, "price", "error.price", "price");

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
