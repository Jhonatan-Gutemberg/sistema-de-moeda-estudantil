package com.coinsystem.system.mappers;

import com.coinsystem.system.DTO.InstitutionEducationDTO;
import com.coinsystem.system.DTO.PartnerCompanyDTO;
import com.coinsystem.system.DTO.StudentDTO;
import com.coinsystem.system.DTO.TeacherDTO;
import com.coinsystem.system.DTO.UsersDTO;
import com.coinsystem.system.enums.UsersType;
import com.coinsystem.system.model.InstitutionEducation;
import com.coinsystem.system.model.PartnerCompany;
import com.coinsystem.system.model.Student;
import com.coinsystem.system.model.Teacher;
import com.coinsystem.system.model.Users;

public class UsersMapper {

    public static Users UserDtoToModel(UsersDTO userDTO) {
        return new Users(userDTO.name(), userDTO.email(), UsersType.USERS,userDTO.phoneNumber(), userDTO.password(),userDTO.address());
    }

    public static Student StudentDtoToModel(StudentDTO studentDTO) {
        return new Student(studentDTO.name(), studentDTO.email(), UsersType.STUDENT,studentDTO.phoneNumber(), studentDTO.password(),studentDTO.address(),studentDTO.cpf(),studentDTO.rg());
    }

    public static Teacher TeacherDtoToModel(TeacherDTO teacherDTO) {
        return new Teacher(teacherDTO.name(), teacherDTO.email(), UsersType.TEACHER,teacherDTO.phoneNumber(), teacherDTO.password(),teacherDTO.address(),teacherDTO.salary(),teacherDTO.department());
    }

    public static PartnerCompany PartnerCompanyDtoToModel(PartnerCompanyDTO partnerCompanyDTO) {
        return new PartnerCompany(partnerCompanyDTO.name(), partnerCompanyDTO.email(), UsersType.PARTNERCOMPANY,partnerCompanyDTO.phoneNumber(), partnerCompanyDTO.password(),partnerCompanyDTO.address(),partnerCompanyDTO.cnpj());
    }

    public static InstitutionEducation InstitutionEducationDtoToModel(InstitutionEducationDTO institutionEducationDTO) {
        return new InstitutionEducation(institutionEducationDTO.name(), institutionEducationDTO.email(), UsersType.INSTITUTIONEDUCATION,institutionEducationDTO.phoneNumber(), institutionEducationDTO.password(),institutionEducationDTO.address(),institutionEducationDTO.cnpj());
    }
    
}
