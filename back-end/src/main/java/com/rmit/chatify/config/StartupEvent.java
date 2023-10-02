package com.rmit.chatify.config;


import com.rmit.chatify.service.Impl.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class StartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private IndexingService service;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            service.initiateIndexing();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
