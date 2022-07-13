package com.ecom.controller;

import com.ecom.entity.Role;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.RoleDto;
import com.ecom.payload.UserDto;
import com.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserDto userDto
    ) {
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    //update

    //delete

    //get all users
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/role/{userId}")
    public ResponseEntity<UserDto> updateRoles(
            @PathVariable Integer userId,
            @RequestBody List<RoleDto> roles
    ) {
        UserDto userDto = this.userService.updateRole(userId, roles);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> deleteUsers(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setSuccess(true);
        apiResponse.setMessage("User deleted successfully !!");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
