package com.blogiffy.Blogiffy.dto.request;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
