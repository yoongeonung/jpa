package yoongeonung.webapp.common.exceptions;

public class NotEnoughStcokException extends RuntimeException{

  public NotEnoughStcokException() {
    super(); // 있고 없고의 차이는?
  }

  public NotEnoughStcokException(String message) {
    super(message);
  }

  public NotEnoughStcokException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotEnoughStcokException(Throwable cause) {
    super(cause);
  }
}
