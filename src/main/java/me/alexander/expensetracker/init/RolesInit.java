package me.alexander.expensetracker.init;

import me.alexander.expensetracker.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RolesInit implements CommandLineRunner {

    private final SeedService seedService;

    @Autowired
    public RolesInit(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(seedService.seedRoles());
    }

}
