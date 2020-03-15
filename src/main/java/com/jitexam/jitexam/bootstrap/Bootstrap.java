package com.jitexam.jitexam.bootstrap;

import com.jitexam.jitexam.dto.UserDto;
import com.jitexam.jitexam.entity.User;
import com.jitexam.jitexam.repository.UserRepository;
import com.jitexam.jitexam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap  {
    private final UserRepository userRepository;
    private final BootstrapTableCreator bootstrapTableCreator;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        bootstrapTableCreator.create();
        userRepository.deleteAll();
        log.info("All users deleted from database");
        User user = User.builder()
                .firstName("Alex")
                .lastName("X")
                .username("alex123")
                .password("$2a$04$4vwa/ugGbBVDvbWaKUVZBuJbjyQyj6tqntjSmG8q.hi97.xSdhj/2")
                .build();
        userRepository.save(user);
        log.info("Sample user: {} added to database", user.getUsername());
    }
}
