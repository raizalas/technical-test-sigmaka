package com.sigmaka.caseestudy.user.dto;

import com.sigmaka.caseestudy.user.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * DTO for {@link Users}
 */
public record LoginDto(@Email @NotBlank String email, @NotBlank String password) implements
    Serializable {

}