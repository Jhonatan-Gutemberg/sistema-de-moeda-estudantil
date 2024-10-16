package com.coinsystem.system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsystem.system.DTO.UsersDTO;
import com.coinsystem.system.exception.UserNotFoundException;
import com.coinsystem.system.mappers.UsersMapper;
import com.coinsystem.system.model.Users;
import com.coinsystem.system.repository.UsersRepository;
import com.coinsystem.system.service.interfaces.IUsersSrevice;

@Service
public class UsersService implements IUsersSrevice {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users registerUsers(UsersDTO usersDTO) {
       Users users = UsersMapper.UserDtoToModel(usersDTO);
       usersRepository.save(users);
       return users;
    }

    @Override
    public List<Users> getAllUsers() {
       return usersRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        Optional<Users> users = usersRepository.findById(id);
       
        return users.orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public Users update(Long id, UsersDTO usersDTO) {
       Optional<Users> optional = usersRepository.findById(id);
       if(optional.isPresent()){
        Users existingUsers = optional.get();
        existingUsers.setName(usersDTO.name());
        existingUsers.setAddress(usersDTO.address());
        existingUsers.setEmail(usersDTO.email());
        existingUsers.setPhoneNumber(usersDTO.phoneNumber());
        existingUsers.setPassword(usersDTO.password());

        usersRepository.save(existingUsers);
        return existingUsers;
       }else{
        throw new UserNotFoundException("User with id " + id + " not found.");
       }
    }

    @Override
    public boolean delete(Long id) {
        Optional<Users> optional = usersRepository.findById(id);
        if (optional.isPresent()) {
            usersRepository.delete(optional.get());
            return true;
        }
        
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    
    
}
