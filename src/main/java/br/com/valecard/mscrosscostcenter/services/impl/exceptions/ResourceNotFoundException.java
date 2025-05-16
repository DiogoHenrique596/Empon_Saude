package br.com.valecard.mscrosscostcenter.services.impl.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Cost Center not found.";

  public ResourceNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

}
