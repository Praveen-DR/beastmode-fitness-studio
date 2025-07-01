package com.beastmode.controllers;

import com.beastmode.dto.request.CreateUserDto;
import com.beastmode.dto.request.LoginRequestDto;
import com.beastmode.dto.request.UpdateUserDto;
import com.beastmode.dto.response.RegisterResponseDto;
import com.beastmode.models.Role;
import com.beastmode.models.User;
import com.beastmode.services.user_service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/web/api/user")
public class UserController {
    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/v1/Create")
    public ResponseEntity<RegisterResponseDto> createUser(@RequestBody CreateUserDto data){
        String message =  userService.createUser(data.email(), data.name(), data.password(), data.role(), data.phoneNo(), data.membershipId());
        return ResponseEntity.status(201).body(new RegisterResponseDto(message));
    }

    @GetMapping("/v1/GetAllUser")
    ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.status(200).body(userService.getAllUser());
    }

    @GetMapping("/v1/GetUserById")
    ResponseEntity<User>getUserById(@RequestParam("userId") String userId){
        return ResponseEntity.status(200).body(userService.getUserById(userId));
    }

    @PostMapping("/v1/UpdateUser")
    ResponseEntity<String> updateUser(@RequestBody UpdateUserDto data) {
        String message = userService.updateUser(data.userId(), data.email(), data.name(), data.password(), data.role());
        return ResponseEntity.status(201).body(message);
    }

    @GetMapping("/v1/DeleteUser")
    ResponseEntity<String> deleteUser(@RequestParam("userId") String userId){
        return ResponseEntity.status(200).body(userService.deleteUser(userId));
    }

    @GetMapping("/v1/GetByRole")
    ResponseEntity<List<User>> getAllByRole(@RequestParam("Role") Role role){
        return ResponseEntity.status(200).body(userService.getAllByRole(role));
    }

    @PostMapping("/v1/login")
    ResponseEntity<User> login(@RequestBody LoginRequestDto data){
        User message = userService.login(data.email(), data.password());
        return ResponseEntity.status(201).body(message);

    }

}
