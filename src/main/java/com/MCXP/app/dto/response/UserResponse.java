package com.MCXP.app.dto.response;

import com.MCXP.app.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {

   private Long id;
   private String firstName;
   private String lastName;
   private String email;
   private String userName;
   private String passwordHash;
   private String phoneNumber;
   private String role;
   private String status;

}
