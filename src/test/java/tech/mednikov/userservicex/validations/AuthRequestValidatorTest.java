package tech.mednikov.userservicex.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.vavr.control.Validation;
import tech.mednikov.userservicex.model.AuthRequest;

class AuthRequestValidatorTest {

  @Test
  void validateSuccessTest() {
    AuthRequest request = new AuthRequest("john.doe@email.com", "password");
    AuthRequestValidator validator = new AuthRequestValidator();
    Validation<ValidatonError, AuthRequest> result = validator.validate(request);
    Assertions.assertTrue(result.isValid());
    Assertions.assertEquals(request.getEmail(), result.get().getEmail());
    Assertions.assertEquals(request.getPassword(), result.get().getPassword());
  }

  @Test
  void validateFailedTest() {
    AuthRequest request = new AuthRequest("john.doe", "secret");
    AuthRequestValidator validator = new AuthRequestValidator();
    Validation<ValidatonError, AuthRequest> result = validator.validate(request);
    Assertions.assertTrue(result.isInvalid());
    Assertions.assertNotNull(result.getError().getMessage());
  }
}
