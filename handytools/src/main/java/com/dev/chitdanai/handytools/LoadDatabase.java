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
            log.info("loading "+repository.save(new Storage("hammer", "kt", "outside storage", false, null)));
            log.info("loading "+repository.save(new Storage("screwdriver", "kt", "toolbox 2nd floor", false, null)));
            log.info("loading"+repository.save(new Storage("paint brush","kt","paint cabinet",false,null)));
        };
    }

}
