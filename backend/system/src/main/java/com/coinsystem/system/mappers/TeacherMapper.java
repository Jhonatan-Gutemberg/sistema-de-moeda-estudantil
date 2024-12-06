package com.coinsystem.system.mappers;

import com.coinsystem.system.DTO.TeacherDTO;
import com.coinsystem.system.model.Teacher;

public class TeacherMapper {
    public static TeacherDTO teacherToTeacherDTO(Teacher teacher) {
        return new TeacherDTO(
                teacher.getName(),
                teacher.getEmail(),
                teacher.getType(),
                teacher.getPhoneNumber(),
                teacher.getSalary(),
                teacher.getDepartment(),
                teacher.getPassword(),
                teacher.getAddress(),
                WalletMapper.walletToWalletDTO(teacher.getWallet()),
                teacher.getInstitutionEducation().getId()
        );
    }
}
