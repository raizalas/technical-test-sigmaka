package com.sigmaka.caseestudy.common;

public class ApiResponse<T> {

  private final int status;

  private final String message;

  private final T data;

  public ApiResponse(int status, String message, T t) {
    this.status = status;
    this.message = message;
    this.data = t;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }
}
