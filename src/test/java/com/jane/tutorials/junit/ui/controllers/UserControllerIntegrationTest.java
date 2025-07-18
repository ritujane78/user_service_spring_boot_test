package com.jane.tutorials.junit.ui.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="/application-test.properties",
        properties = "server.port=0")
public class UserControllerIntegrationTest {
    @Value("${server.port}")
    int serverPort;

    @LocalServerPort
    int localServerPort;


    @Test
    void contextLoads() {
//        precedence : properties defined in the java code > application-test.properties > application.properties
        System.out.println("server port = "+ serverPort);

        System.out.println("Local server port = " + localServerPort);
    }
}
