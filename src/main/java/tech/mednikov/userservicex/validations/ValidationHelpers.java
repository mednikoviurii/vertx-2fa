package tech.mednikov.userservicex.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.vavr.control.Validation;

class ValidationHelpers {

  static Validation<String, String> validateRequiredLength (String name, String input, int length) {
    if (input.length() < length) {
      return Validation.invalid(name.concat(": length is less than required"));
    }
    return Validation.valid(input);
  }

  static Validation<String, String> validateAgainstPattern (String name, String input, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    if (!matcher.matches()) {
      return Validation.invalid(name.concat(": does not match a required pattern"));
    }
    return Validation.valid(input);
  }

  static Validation<String, String> validateIsEmail (String name, String input) {
    final String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    return validateAgainstPattern(name, input, regex);
  }
}
