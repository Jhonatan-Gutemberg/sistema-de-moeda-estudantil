package com.studentcoinsystem.mappers;

import org.aspectj.apache.bcel.classfile.Module.Uses;

import com.studentcoinsystem.dto.UsersDTO;
import com.studentcoinsystem.model.Users;
import com.studentcoinsystem.model.UsersType;

public class UsersMapper {
    public static Users UserDtoToModel(UsersDTO usersDTO) {
        return new Users(usersDTO.name(),usersDTO.email(),UsersType.USERS,usersDTO.phoneNumber(),usersDTO.password(),usersDTO.address());
    }
}
