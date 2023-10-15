package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * SYSC 4806 Lab 5, Fall 2023
 * Test class that tests the BuddyInfoController methods.
 * @author Max Curkovic, 101139937
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BuddyInfoControllerTest {
    @Autowired
    private MockMvc testController;

    @Autowired
    private AddressBookRepository addressRepo;
    @Autowired
    private BuddyInfoRepository buddyRepo;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddBuddy() throws Exception{
        this.testController.perform(post("/createdBuddyManual")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("id", "1")
                        .param("name", "Test")
                        .param("address", "123 Test")
                        .param("number", "613-Test"))
                .andExpect(status().is3xxRedirection());

        this.testController.perform(get("/displayaddressbook?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test")));
    }
    @Test
    public void deleteBuddy() throws Exception {
        this.testController.perform(post("/createdBuddyManual")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("id", "1")
                        .param("name", "Test")
                        .param("address", "123 Test")
                        .param("number", "613-Test"))
                .andExpect(status().is3xxRedirection());

        this.testController.perform(get("/displayaddressbook?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Max")));

        this.testController.perform(post("/removingBuddyManual")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("id", "1")
                        .param("name", "Test"))
                .andExpect(status().is3xxRedirection());

        this.testController.perform(get("/displayaddressbook?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Test"))));
    }

}