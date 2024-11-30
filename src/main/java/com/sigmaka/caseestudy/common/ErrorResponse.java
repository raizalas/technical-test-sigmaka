package com.sigmaka.caseestudy.common;

public class ErrorResponse extends ApiResponse<String>{

  public ErrorResponse(int status, String message, String exception) {
    super(status, message, exception);
  }
}
