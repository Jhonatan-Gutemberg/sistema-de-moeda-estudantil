package com.coinsystem.system.service.interfaces;

import com.coinsystem.system.DTO.UsersDTO;
import com.coinsystem.system.model.Users;

import java.util.List;

public interface IUsersService {

    Users registerUsers(UsersDTO usersDTO);

    List<Users> getAllUsers();

    Users getUserById(Long id);

    Users update(Long id, UsersDTO usersDTO);

    boolean delete(Long id);

}
