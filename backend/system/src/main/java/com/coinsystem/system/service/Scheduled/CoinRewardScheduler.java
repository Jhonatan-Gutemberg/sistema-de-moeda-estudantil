package com.coinsystem.system.service.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.coinsystem.system.model.Teacher;
import com.coinsystem.system.repository.TeacherRepository;
import com.coinsystem.system.service.TeacherService;

import java.util.List;

@Component
public class CoinRewardScheduler {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;
    @Scheduled(fixedRate = 15_552_000_000L) // 60000 -> 1 MINUTE -> 6 MONTHS = 15_552_000_000L
    public void scheduleCoinRewards() {
        List<Teacher> teachers = teacherRepository.findAll();
        
        for (Teacher teacher : teachers) {
            teacherService.rewardCoins(teacher.getId());
        }
    }
}

