package com.jane.tutorials.junit.ui.controllers;

import com.jane.tutorials.junit.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="/application-test.properties",
        properties = "server.port=0")
public class UserControllerIntegrationTest {
    @Value("${server.port}")
    int serverPort;

    @LocalServerPort
    int localServerPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("User can be created")
    void testCreateUser_whenValidUserDetailsProvided_returnCreatedUserDetails() throws JSONException {
//        Arrange
        JSONObject userDetailsJSONObject = new JSONObject();
        userDetailsJSONObject.put("firstName", "Ritu");
        userDetailsJSONObject.put("lastName","Bafna");
        userDetailsJSONObject.put("email","abc@test.com");
        userDetailsJSONObject.put("password", "12345678");
        userDetailsJSONObject.put("repeatPassword", "12345678");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(userDetailsJSONObject.toString(),headers);

//        Act
        ResponseEntity<UserRest> createdUserDetailsEntity = testRestTemplate.postForEntity("/users",
                request,
                UserRest.class);
        UserRest createdUserDetails = createdUserDetailsEntity.getBody();

//        Assert
        assertEquals(HttpStatus.OK, createdUserDetailsEntity.getStatusCode(), "status code should be OK");
        assertEquals(userDetailsJSONObject.getString("firstName"), createdUserDetails.getFirstName(), "First name is incorrect");
        assertEquals(userDetailsJSONObject.getString("lastName"), createdUserDetails.getLastName(), "Last name is incorrect");
        assertEquals(userDetailsJSONObject.getString("email"), createdUserDetails.getEmail(), "Email is incorrect");
    }

}
