package hako.rentACar.core.utilities.results;

public class Result {
  private boolean type;
  private String message;

  public Result(boolean type) {
    this.type = type;
  }

  public Result(boolean type, String message) {
    this(type);
    this.message = message;
  }

  public boolean getType() {
    return type;
  }

  public String getMessage() {
    return message;
  }
}
