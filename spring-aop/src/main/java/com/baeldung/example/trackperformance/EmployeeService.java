package com.baeldung.example.trackperformance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);


    @TrackPerformance
    public void testPublicMethod() {
        logger.info("Testing Public Method...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testPrivateMethod();
        testProtectedMethod();
    }

    @TrackPerformance
    private void testPrivateMethod() {
        logger.info("Testing Private Method...");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @TrackPerformance
    protected void testProtectedMethod(){
        logger.info("Testing Protected Method...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
