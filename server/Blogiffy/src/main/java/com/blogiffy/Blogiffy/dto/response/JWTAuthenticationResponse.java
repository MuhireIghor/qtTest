package com.blogiffy.Blogiffy.dto.response;

import com.blogiffy.Blogiffy.models.User;
import lombok.Data;

@Data
public class JWTAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    User user;

    public JWTAuthenticationResponse(String accessToken, User user){
        this.accessToken = accessToken;
        this.user = user;
    }
}
