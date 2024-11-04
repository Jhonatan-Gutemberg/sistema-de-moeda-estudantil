package com.coinsystem.system.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import com.coinsystem.system.DTO.InstitutionEducationDTO;
import com.coinsystem.system.DTO.PartnerCompanyDTO;
import com.coinsystem.system.DTO.StudentDTO;
import com.coinsystem.system.DTO.TeacherDTO;
import com.coinsystem.system.DTO.WalletDTO;
import com.coinsystem.system.enums.UsersType;

import com.coinsystem.system.repository.InstitutionEducationRepository;
import com.coinsystem.system.repository.PartnerCompanyRepository;
import com.coinsystem.system.repository.StudentRepository;
import com.coinsystem.system.repository.TeacherRepository;
import com.coinsystem.system.repository.UsersRepository;
import com.coinsystem.system.repository.VantageRepository;
import com.coinsystem.system.service.interfaces.IInstitutionEducationService;
import com.coinsystem.system.service.interfaces.IPartnerCompanyService;
import com.coinsystem.system.service.interfaces.IStudentService;
import com.coinsystem.system.service.interfaces.ITeacherService;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private InstitutionEducationRepository institutionEducationRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PartnerCompanyRepository partnerCompanyRepository;
    @Autowired
    private VantageRepository vantageRepository;

    @Autowired
    private IInstitutionEducationService institutionEducationService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IPartnerCompanyService partnerCompanyService;

    @Override
    public void run(String... args) throws Exception {
        createInstition();
        createTeacher();
        createStudent();
        createCompany();

    }

    public void createInstition() {
        InstitutionEducationDTO institutionEducation1 = new InstitutionEducationDTO(
                "Puc Minas",
                "pucminas@sga.com.br",
                UsersType.INSTITUTIONEDUCATION,
                "3198635475",
                "31658495/0001-01",
                "12345",
                "Rua vinte e cinco n52");

        InstitutionEducationDTO institutionEducation2 = new InstitutionEducationDTO(
                "UFMG",
                "ufmg@gov.com.br",
                UsersType.INSTITUTIONEDUCATION,
                "3135978462",
                "316974395/0001-01",
                "12345",
                "Rua das Andradas n1052");

        institutionEducationService.register(institutionEducation1);
        institutionEducationService.register(institutionEducation2);
    }

    public void createTeacher() {
        WalletDTO wallet1 = new WalletDTO(0, null);
        WalletDTO wallet2 = new WalletDTO(0, null);
        TeacherDTO teacher1 = new TeacherDTO(
                "João Pedro Aumori",
                "jpaumorin52@gmail.com.br",
                UsersType.TEACHER,
                "31985876439",
                5263.0,
                "Engenharia",
                "12345",
                "Rua Maria Feliz n36",
                wallet1,
                1L);

        TeacherDTO teacher2 = new TeacherDTO(
                "Paula José Gomes",
                "pagose3@gmail.com.br",
                UsersType.TEACHER,
                "31985897439",
                5263.0,
                "Biologia",
                "12345",
                "Rua Feliz n61",
                wallet2,
                2L);

        teacherService.register(teacher1);
        teacherService.register(teacher2);
    }

    public void createStudent() {
        WalletDTO wallet1 = new WalletDTO(0, null);
        WalletDTO wallet2 = new WalletDTO(0, null);
        StudentDTO student1 = new StudentDTO(
                "Luis Felipe Ferreira",
                "ferluife@gmail.com",
                UsersType.STUDENT,
                "31985637419",
                "89647301871",
                "15975324",
                "12345",
                "Rua Cruzeiro do Sul n12",
                3L,
                wallet1);

        StudentDTO student2 = new StudentDTO(
                "Fabiane Silva Lourenço",
                "fabilou@gmail.com",
                UsersType.STUDENT,
                "3198566719",
                "2647301871",
                "19745324",
                "12345",
                "Rua Treze n193",
                3L,
                wallet2);

        studentService.register(student1);
        studentService.register(student2);
    }

    public void createCompany() {
        PartnerCompanyDTO company1 = new PartnerCompanyDTO(
                "Super Bill+",
                "spbill@gmail.com",
                UsersType.PARTNERCOMPANY,
                "113596247",
                "316597436/0001-01",
                "12345",
                "Rua Estrela n10");

        PartnerCompanyDTO company2 = new PartnerCompanyDTO(
                "Extra",
                "suporteextr@gmail.com",
                UsersType.PARTNERCOMPANY,
                "31356987423",
                "241361436/0001-01",
                "12345",
                "Rua Estrela n110");

        partnerCompanyService.register(company1);
        partnerCompanyService.register(company2);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        clearData();
    }

    public void clearData() {
        usersRepository.deleteAll();
        institutionEducationRepository.deleteAll();
        teacherRepository.deleteAll();
        studentRepository.deleteAll();
        partnerCompanyRepository.deleteAll();
        vantageRepository.deleteAll();

    }

}
