package com.studentcoinsystem.service.interfaces;

import com.studentcoinsystem.dto.UsersDTO;
import com.studentcoinsystem.model.Users;
import java.util.List;

public interface IUsersService {

    Users register(UsersDTO usersDTO);

    List<Users> getAllUsers();

}
