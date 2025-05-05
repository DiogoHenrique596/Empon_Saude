package br.com.valecard.mscostcenter.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Cost Center not found.";

  public ResourceNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

}
