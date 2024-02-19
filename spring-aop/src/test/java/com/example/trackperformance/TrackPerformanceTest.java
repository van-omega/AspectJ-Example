package com.example.trackperformance;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.baeldung.Application;
import com.baeldung.example.trackperformance.EmployeeService;
import com.baeldung.example.trackperformance.PerforamanceLoggingAspect;
import com.baeldung.pointcutadvice.PerformanceAspect;
import com.baeldung.pointcutadvice.dao.FooDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class}, loader = AnnotationConfigContextLoader.class)
public class TrackPerformanceTest {
	
	@Autowired
	EmployeeService employeService;
	
	@Autowired
	private FooDao dao;
	
	private Handler logEventHandler;

    private List<String> messages;
    
    
	@Before
    public void setUp() {
        logEventHandler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                messages.add(record.getMessage());
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() throws SecurityException {
            }
        };

        messages = new ArrayList<>();
    }
	
	@Test
    public void testPerformanceAspect_whenEmployeeMethodsAreCalled() throws Exception {
		Logger logger = Logger.getLogger(PerforamanceLoggingAspect.class.getName());
		logger.addHandler(logEventHandler);
		
		employeService.testPublicMethod();
    }
}
