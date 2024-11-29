package com.sigmaka.caseestudy.user;

import com.sigmaka.caseestudy.auth.AuthService;
import com.sigmaka.caseestudy.auth.UserDetailsImpl;
import com.sigmaka.caseestudy.user.dto.LoginDto;
import com.sigmaka.caseestudy.user.dto.LoginResponse.JwtToken;
import com.sigmaka.caseestudy.user.dto.RegisterDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthService authService;
  private final AuthenticationManager authenticationManager;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
      AuthService authService, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authService = authService;
    this.authenticationManager = authenticationManager;
  }

  public JwtToken login(LoginDto dto) {
    Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        dto.email(), dto.password()
    ));

    SecurityContextHolder.getContext().setAuthentication(auth);

    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

    String token = authService.generateToken(auth);

    return new JwtToken(token);
  }

  public Users register(RegisterDto dto) {
    Users user = new Users();
    user.setEmail(dto.email());
    user.setPassword(passwordEncoder.encode(dto.password()));
    user.setName(dto.name());

    return userRepository.save(user);
  }
}
