package hako.rentACar.core.utilities.results;

public class DataResult<T> extends Result {
  private T data;

  public DataResult(boolean type) {
    super(type);
  }

  public DataResult(boolean type, T data) {
    super(type);
    this.data = data;
  }

  public DataResult(boolean type, String message) {
    super(type, message);
  }

  public DataResult(boolean type, String message,T data) {
    super(type, message);
    this.data = data;
  }

  public T getData() {
    return data;
  }
  
}
