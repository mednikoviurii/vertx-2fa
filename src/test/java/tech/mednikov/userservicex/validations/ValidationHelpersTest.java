package tech.mednikov.userservicex.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.vavr.control.Validation;

class ValidationHelpersTest {

  @Test
  void validateRequiredLengthSuccessTest() {
    String password = "secret";
    int length = 6;
    Validation<String, String> result = ValidationHelpers.validateRequiredLength("password", password, length);
    Assertions.assertTrue(result.isValid());
  }

  @Test
  void validateRequiredLengthFailedTest() {
    String password = "secret";
    int length = 8;
    Validation<String, String> result = ValidationHelpers.validateRequiredLength("password", password, length);
    Assertions.assertTrue(result.isInvalid());
  }

  @Test
  void validateIsEmailSuccessTest() {
    String email = "john.doe@email.com";
    Validation<String, String> result = ValidationHelpers.validateIsEmail("email", email);
    Assertions.assertTrue(result.isValid());
  }

  @Test
  void validateIsEmailFailedTest() {
    String email = "john.doe";
    Validation<String, String> result = ValidationHelpers.validateIsEmail("email", email);
    Assertions.assertTrue(result.isInvalid());
  }
}
