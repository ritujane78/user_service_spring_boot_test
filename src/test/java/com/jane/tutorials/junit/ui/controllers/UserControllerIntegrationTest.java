package com.jane.tutorials.junit.ui.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="/application-test.properties",
        properties = "server.port=8888")
public class UserControllerIntegrationTest {
    @Value("${server.port}")
    String serverPort;
    @Test
    void contextLoads() {
//        precedence : properties defined in the java code > application-test.properties > application.properties
        System.out.println("server port = "+ serverPort);
    }
}
