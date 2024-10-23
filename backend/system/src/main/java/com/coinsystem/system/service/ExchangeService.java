package com.coinsystem.system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsystem.system.DTO.EmailDTO;
import com.coinsystem.system.DTO.ExchangeDTO;
import com.coinsystem.system.mappers.ExchangeMapper;
import com.coinsystem.system.model.Exchange;
import com.coinsystem.system.model.Notification;
import com.coinsystem.system.model.Student;
import com.coinsystem.system.model.Vantage;
import com.coinsystem.system.repository.ExchangeRepository;
import com.coinsystem.system.repository.NotificationRepository;
import com.coinsystem.system.repository.StudentRepository;
import com.coinsystem.system.repository.VantageRepository;
import com.coinsystem.system.service.Utilities.CouponCodeGenerator;
import com.coinsystem.system.service.interfaces.IEmailService;
import com.coinsystem.system.service.interfaces.IExchangeService;

@Service
public class ExchangeService implements IExchangeService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private VantageRepository vantageRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private IEmailService emailService;

    @Override
    public ExchangeDTO exchangeVantage(Long studentId, Long vantageId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado."));
        Vantage vantage = vantageRepository.findById(vantageId)
                .orElseThrow(() -> new RuntimeException("Vantagem não encontrada."));

        if (student.getWallet().getCoins() < vantage.getValue()) {
            throw new RuntimeException("Saldo insuficiente.");
        }

        student.getWallet().setCoins(student.getWallet().getCoins() - vantage.getValue());
        studentRepository.save(student);

        Exchange exchange = new Exchange(student, vantage, LocalDateTime.now(), vantage.getValue());
        exchangeRepository.save(exchange);

        String couponCode = CouponCodeGenerator.generateCouponCode();

        Notification notification = new Notification(
                couponCode,
                vantage.getPartnerCompany(),
                student);
        notificationRepository.save(notification);

        //sendEmailNotification(student, vantage, couponCode); -> This method is not yet operational.

        return ExchangeMapper.toDTO(exchange);
    }

    private void sendEmailNotification(Student student, Vantage vantage, String couponCode) {
        sendEmailToStudent(student, vantage, couponCode);
    
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            try {
                sendEmailToCompany(student, vantage, couponCode);
            } catch (Exception e) {
                System.err.println("Erro ao enviar e-mail para a companhia: " + e.getMessage());
            }
        }, 10, TimeUnit.SECONDS);
    }
    
    private void sendEmailToStudent(Student student, Vantage vantage, String couponCode) {
        String subject = "Troca de Vantagem Realizada com Sucesso";
        String body = createStudentEmailBody(student, vantage, couponCode);
        EmailDTO email = new EmailDTO("suporte@demomailtrap.com", student.getEmail(), subject, body);
        emailService.sendEmail(email);
    }
    
    private String createStudentEmailBody(Student student, Vantage vantage, String couponCode) {
        return String.format("Olá %s,\n\nVocê realizou a troca de moedas por '%s' com sucesso.\n" +
                "Seu código de cupom é: %s.\n\nAproveite sua vantagem!\n\nAtenciosamente,\nEquipe de Suporte",
                student.getName(), vantage.getName(), couponCode);
    }
    
    private void sendEmailToCompany(Student student, Vantage vantage, String couponCode) {
        String subject = "Novo Cupom Gerado para " + student.getName();
        String body = createCompanyEmailBody(student, vantage, couponCode);
        String companyEmail = vantage.getPartnerCompany().getEmail();
    
        if (companyEmail == null || companyEmail.isEmpty()) {
            System.err.println("E-mail da companhia não disponível.");
            return;
        }
    
        EmailDTO emailCompany = new EmailDTO("suporte@demomailtrap.com", companyEmail, subject, body);
        emailService.sendEmail(emailCompany);
    }
    
    private String createCompanyEmailBody(Student student, Vantage vantage, String couponCode) {
        return String.format("Olá,\n\nO estudante %s trocou moedas por uma vantagem: '%s'.\n" +
                "O código do cupom gerado é: %s.\n\nAtenciosamente,\nEquipe de Suporte",
                student.getName(), vantage.getName(), couponCode);
    }
    

    @Override
    public void confirmStudentReceipt(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada."));
        notification.setStudentConfirmed(true);
        notificationRepository.save(notification);
        dateConfirmReceipt(notificationId);
    }

    @Override
    public void confirmPartnerReceipt(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada."));
        notification.setPartnerConfirmed(true);
        notificationRepository.save(notification);
        dateConfirmReceipt(notificationId);
    }

    private void dateConfirmReceipt(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada."));
        boolean isPartnerConfirmed = notification.isPartnerConfirmed();
        boolean isStudentConfirmed = notification.isStudentConfirmed();
        if (isPartnerConfirmed & isStudentConfirmed == true) {
            notification.setDateReceipt(LocalDateTime.now());
        }
        notificationRepository.save(notification);
    }

    @Override
    public List<ExchangeDTO> getExchangeHistory(Long studentId) {
        List<Exchange> exchanges = exchangeRepository.findByStudentId(studentId);
        return exchanges.stream()
                .map(ExchangeMapper::toDTO)
                .collect(Collectors.toList());
    }

}
