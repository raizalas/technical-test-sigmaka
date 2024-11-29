package com.sigmaka.caseestudy.user.dto;

import com.sigmaka.caseestudy.common.ApiResponse;

public class LoginResponse extends ApiResponse<LoginResponse.JwtToken> {

  public LoginResponse(int status, String message, JwtToken jwtToken) {
    super(status, message, jwtToken);
  }


  public record JwtToken(String token) {
  }
}
