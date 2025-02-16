package com.dev.chitdanai.handytools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    public static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner setupData(StorageRepository repository){
        return args -> {
            log.info("loading "+repository.save(new Storage("Camera", "Chitdanai", "Closet", true, "Mother")));
            log.info("loading "+repository.save(new Storage("Atomic Bomb", "Oppenheimer", "Basement", false, null)));
            log.info("loading"+repository.save(new Storage("Kryptonite","Clark","Fortress",true,"Batman")));
        };
    }

}
