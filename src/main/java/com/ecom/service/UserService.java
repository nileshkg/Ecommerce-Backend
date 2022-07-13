package com.ecom.service;

import com.ecom.entity.Role;
import com.ecom.payload.RoleDto;
import com.ecom.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateRole(Integer userId, List<RoleDto> roles);

    void deleteUser(Integer userId);
}
