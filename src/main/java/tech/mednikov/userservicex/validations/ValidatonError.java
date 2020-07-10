package tech.mednikov.userservicex.validations;

public class ValidatonError extends RuntimeException{

  public ValidatonError(String message){
    super(message);
  }
}
