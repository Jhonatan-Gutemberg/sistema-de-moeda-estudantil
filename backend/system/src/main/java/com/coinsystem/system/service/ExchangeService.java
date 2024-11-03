package com.coinsystem.system.service;

import java.time.LocalDateTime;
import java.util.List;
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
        Student student = getStudentById(studentId);
        Vantage vantage = getVantageById(vantageId);

        validateVantageAvailability(vantage);
        validateStudentBalance(student, vantage);

        processExchange(student, vantage);

        String couponCode = generateCouponCode();

        createNotification(student, vantage, couponCode);
        sendEmailNotification(student, vantage, couponCode);

        return mapToExchangeDTO(student, vantage);
    }

    private Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));
    }

    private Vantage getVantageById(Long vantageId) {
        return vantageRepository.findById(vantageId)
                .orElseThrow(() -> new RuntimeException("Vantage not found."));
    }

    private void validateVantageAvailability(Vantage vantage) {
        if (vantage.getQuantity() <= 0) {
            throw new RuntimeException("Vantage is unavailable.");
        }
    }

    private void validateStudentBalance(Student student, Vantage vantage) {
        if (student.getWallet().getCoins() < vantage.getValue()) {
            throw new RuntimeException("Insufficient balance.");
        }
    }

    private void processExchange(Student student, Vantage vantage) {
        deductStudentCoins(student, vantage);
        decreaseVantageQuantity(vantage);
        saveExchange(student, vantage);
    }

    private void deductStudentCoins(Student student, Vantage vantage) {
        student.getWallet().setCoins(student.getWallet().getCoins() - vantage.getValue());
        studentRepository.save(student);
    }

    private void decreaseVantageQuantity(Vantage vantage) {
        vantage.setQuantity(vantage.getQuantity() - 1);
        vantageRepository.save(vantage);
    }

    private void saveExchange(Student student, Vantage vantage) {
        Exchange exchange = new Exchange(student, vantage, LocalDateTime.now(), vantage.getValue());
        exchangeRepository.save(exchange);
    }

    private String generateCouponCode() {
        return CouponCodeGenerator.generateCouponCode();
    }

    private void createNotification(Student student, Vantage vantage, String couponCode) {
        Notification notification = new Notification(
                couponCode,
                vantage.getPartnerCompany(),
                student);
        notificationRepository.save(notification);
    }

    private ExchangeDTO mapToExchangeDTO(Student student, Vantage vantage) {
        return ExchangeMapper.toDTO(new Exchange(student, vantage, LocalDateTime.now(), vantage.getValue()));
    }

    private void sendEmailNotification(Student student, Vantage vantage, String couponCode) {
        sendEmailToStudent(student, vantage, couponCode);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            try {
                sendEmailToCompany(student, vantage, couponCode);
            } catch (Exception e) {
                System.err.println("Error sending email to the company:" + e.getMessage());
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
            throw new IllegalArgumentException("Company email cannot be null or empty.");
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
                .orElseThrow(() -> new RuntimeException("Notification not found."));
        notification.setStudentConfirmed(true);
        notificationRepository.save(notification);
        dateConfirmReceipt(notificationId);
    }

    @Override
    public void confirmPartnerReceipt(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found."));
        notification.setPartnerConfirmed(true);
        notificationRepository.save(notification);
        dateConfirmReceipt(notificationId);
    }

    private void dateConfirmReceipt(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found."));
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
