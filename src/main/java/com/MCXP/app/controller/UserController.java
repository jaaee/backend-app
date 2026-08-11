package com.MCXP.app.controller;

import com.MCXP.app.dto.request.UserRequest;
import com.MCXP.app.dto.response.UserResponse;
import com.MCXP.app.service.UserService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user){
        UserResponse userResponse = userService.createUser(user);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }
}
