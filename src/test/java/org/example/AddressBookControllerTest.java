package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * SYSC 4806 Lab 5, Fall 2023
 * Test class that tests the AddressBookController methods.
 * @author Max Curkovic, 101139937
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AddressBookControllerTest {
    @Autowired
    private MockMvc testController;

    @Test
    public void createAddressBook() throws Exception {
        this.testController.perform(get("/addaddressbook")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("<p>Made an AddressBook with the ID <span>2</span></p>")));
    }

    @Test
    public void getAddressBook() throws Exception {
        this.testController.perform(get("/addaddressbook")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("<p>Made an AddressBook with the ID <span>2</span></p>")));
        this.testController.perform(get("/displayaddressbook?id=2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>AddressBook ID: <span>2</span></h1>")));
    }
}