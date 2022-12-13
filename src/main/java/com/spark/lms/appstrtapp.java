package com.spark.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.spark.lms.common.coonst;
import com.spark.lms.mdl.User;

import com.spark.lms.service.Userserv;

@Component
public class appstrtapp implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Userserv userService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        initDatabaseEntities();
    }


    private void initDatabaseEntities() {

        if( userService.getAllUsers().size() == 0) {
            userService.adnw(new User("Mr. Admin", "admin", "admin", coonst.roladm));
            userService.adnw(new User("Mr. Librarian", "librarian", "librarian", coonst.rolelibr));
        }

    }
}