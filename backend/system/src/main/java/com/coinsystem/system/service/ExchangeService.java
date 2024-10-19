package com.coinsystem.system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        String couponCode = generateCouponCode();

        Notification notification = new Notification(
                couponCode,
                vantage.getPartnerCompany(),
                student);
        notificationRepository.save(notification);

        return ExchangeMapper.toDTO(exchange);
    }

    private String generateCouponCode() {
        return UUID.randomUUID().toString();
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
        if(isPartnerConfirmed & isStudentConfirmed == true){
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
