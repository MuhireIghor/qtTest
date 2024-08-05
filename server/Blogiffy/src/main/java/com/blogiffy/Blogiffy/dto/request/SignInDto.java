package com.blogiffy.Blogiffy.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class SignInDto {
    @NotBlank
    @Email(message = "Enter a valid email")
    private String email;
    @NotBlank
    private String password;
}
