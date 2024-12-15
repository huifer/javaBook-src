package com.github.huifer.ctrpluginexample;

import com.github.huifer.ctrpluginexample.entity.AppEntity;
import com.github.huifer.ctrpluginexample.repo.AppRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CtrPluginExampleApplication {

    @Autowired
    private AppRepo appRepo;

    public static void main(String[] args) {
        SpringApplication.run(CtrPluginExampleApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
//            AppEntity appEntity = new AppEntity();
//            appEntity.setName("abc");
//            appRepo.save(appEntity);
            Optional<AppEntity> byId = appRepo.findById(2L);
            if (byId.isPresent()) {
                AppEntity appEntity = byId.get();
                appEntity.setName("a");
                this.appRepo.save(appEntity);
            }
        };
    }

}
