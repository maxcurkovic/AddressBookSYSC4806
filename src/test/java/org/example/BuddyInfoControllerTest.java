package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by danielsauve on 2017-02-09.
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


    /*@Test
    public void removeBuddy() throws Exception {
        this.testController.perform(get("/addaddressbook")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("<p>Made an AddressBook with the ID <span>2</span></p>")));
        this.testController.perform(post("/buddy?bookId=1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "\t\"name\":\"Chloe\",\n" +
                "\t\"phoneNumber\":\"456\"\n" +
                "}")).andDo(print()).andExpect(status().isOk());
        this.testController.perform(delete("/buddy?bookId=1&buddyId=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Chloe\"")));

    }*/

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addBuddy() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Test", "123 Test","613-Test");
        a1.addBuddy(b1);
        addressRepo.save(a1);
        this.testController.perform(get("/displayaddressbook?id=2")
                        .content(asJsonString(b1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteBuddy() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Test", "123 Test","613-Test");
        addressRepo.save(a1);
        buddyRepo.save(b1);
        this.testController.perform(get("/displayaddressbook?id=2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

}