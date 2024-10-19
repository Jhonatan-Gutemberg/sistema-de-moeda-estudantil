package com.coinsystem.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsystem.system.model.Student;
import com.coinsystem.system.model.Teacher;
import com.coinsystem.system.model.Transaction;
import com.coinsystem.system.model.Vantage;
import com.coinsystem.system.model.Wallet;
import com.coinsystem.system.repository.StudentRepository;
import com.coinsystem.system.repository.TeacherRepository;
import com.coinsystem.system.repository.TransactionRepository;
import com.coinsystem.system.repository.WalletRepository;
import com.coinsystem.system.service.interfaces.IWalletService;

@Service
public class WalletService implements IWalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public boolean transferCoins(Long teacherId, Long studentId, int amount, String description) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher não encontrado"));
        
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    
        Wallet sourceWallet = teacher.getWallet();
        Wallet destinationWallet = student.getWallet();
    
        if (sourceWallet.getCoins() < amount) {
            return false;
        }
    
        sourceWallet.setCoins(sourceWallet.getCoins() - amount);
        destinationWallet.setCoins(destinationWallet.getCoins() + amount);
    
        Transaction transaction = new Transaction(teacherId, studentId, amount, description);
        
        sourceWallet.addTransaction(transaction);
    
        walletRepository.save(sourceWallet);
        walletRepository.save(destinationWallet);
    
        return true;
    }
    @Override
    public List<Wallet> getAllWallet() {
        return walletRepository.findAll();
    } 

  public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    

}
