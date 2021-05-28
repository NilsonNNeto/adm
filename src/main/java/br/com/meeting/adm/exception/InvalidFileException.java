package br.com.meeting.adm.exception;

public class InvalidFileException extends RuntimeException {
  public InvalidFileException(String message) {
    super(message);
  }
}
