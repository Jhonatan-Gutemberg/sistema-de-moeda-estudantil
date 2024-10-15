package com.studentcoinsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentcoinsystem.dto.UsersDTO;
import com.studentcoinsystem.mappers.UsersMapper;
import com.studentcoinsystem.model.Users;
import com.studentcoinsystem.repository.UsersRepository;
import com.studentcoinsystem.service.interfaces.IUsersService;

@Service
public class UsersService implements IUsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users register(UsersDTO usersDTO) {
        Users users = UsersMapper.UserDtoToModel(usersDTO);
        usersRepository.save(users);
        return users;
    }

    @Override
    public List<Users> getAllUsers() {

        return usersRepository.findAll();
    }

}
