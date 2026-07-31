package com.MCXP.app.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    private String role;
}
