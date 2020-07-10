package tech.mednikov.userservicex.validations;

import io.vavr.control.Validation;

public interface AbstractValidator<T> {

  Validation<ValidatonError, T> validate (T bean);

}
