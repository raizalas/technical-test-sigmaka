package com.sigmaka.caseestudy.user;

import com.sigmaka.caseestudy.common.ApiResponse;
import com.sigmaka.caseestudy.user.dto.LoginDto;
import com.sigmaka.caseestudy.user.dto.LoginResponse;
import com.sigmaka.caseestudy.user.dto.LoginResponse.JwtToken;
import com.sigmaka.caseestudy.user.dto.RegisterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
    JwtToken login = userService.login(loginDto);

    LoginResponse responseBody = new LoginResponse(HttpStatus.OK.value(),
        HttpStatus.OK.getReasonPhrase(), login);

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }


  @PostMapping("/registeer")
  public ResponseEntity<ApiResponse<Users>> register(@RequestBody RegisterDto registerDto) {

    Users register = userService.register(registerDto);

    ApiResponse<Users> responseBody = new ApiResponse<>(HttpStatus.CREATED.value(),
        HttpStatus.CREATED.getReasonPhrase(), register);


    return new ResponseEntity<>(responseBody, HttpStatus.CREATED);

  }

}
