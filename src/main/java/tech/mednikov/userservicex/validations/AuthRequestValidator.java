package tech.mednikov.userservicex.validations;

import java.util.stream.Collectors;

import io.vavr.control.Validation;
import tech.mednikov.userservicex.model.AuthRequest;

public class AuthRequestValidator implements AbstractValidator<AuthRequest> {

  @Override
  public Validation<ValidatonError, AuthRequest> validate(AuthRequest request) {

    String email = request.getEmail();
    String password = request.getPassword();

    return Validation.combine(
          ValidationHelpers.validateIsEmail("Email", email),
          ValidationHelpers.validateRequiredLength("Password", password, 8)
        ).ap((e, p) -> new AuthRequest(e, p))
        .mapError(seq -> {
            String message = seq.toStream().map(s -> s.concat(", ")).collect(Collectors.joining());
            ValidatonError error = new ValidatonError(message);
            return error;
          });
  }

}
