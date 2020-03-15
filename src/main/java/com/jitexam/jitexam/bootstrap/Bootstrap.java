package com.jitexam.jitexam.bootstrap;

import com.jitexam.jitexam.dto.UserDto;
import com.jitexam.jitexam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final UserService userService;
    private final BootstrapTableCreator bootstrapTableCreator;


    @Override
    public void run(String... args) {

        bootstrapTableCreator.create();
        userService.deleteAll();
        log.info("All users deleted from database");
        UserDto user = new UserDto(null, "Alex","Knr", "alex123","alex123");
        userService.save(user);
        log.info("Sample user: {} added to database", user.getUsername());
    }
}
