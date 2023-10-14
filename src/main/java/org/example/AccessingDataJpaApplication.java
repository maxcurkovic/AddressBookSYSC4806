package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * SYSC 4806 Lab 3, Fall 2023
 * Class for accessing all persisted data using JPA and the Spring Framework.
 * @author Max Curkovic, 101139937
 */
@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner demo(BuddyInfoRepository repository1, AddressBookRepository repository2) {
        return (args) -> {

            AddressBook addressBook = new AddressBook();
            addressBook.addBuddy(new BuddyInfo("Max", "123 Engineering Ave", "123"));
            addressBook.addBuddy(new BuddyInfo("Bardia", "456 Software Street", "456"));
            addressBook.addBuddy(new BuddyInfo("Tony", "789 Spring Road", "789"));
            addressBook.addBuddy(new BuddyInfo("Nick", "100 Senators Boulevard", "5769"));
            addressBook.addBuddy(new BuddyInfo("Chris", "123 Dortmund Drive", "123456"));

            // repository1.save(new BuddyInfo("Max", "123 Engineering Ave", "123"));
            // repository1.save(new BuddyInfo("Bardia", "456 Software Street", "456"));
            repository2.save(addressBook);

            System.out.println("Test Queries: ");

            for (BuddyInfo buddyInfo : repository1.findAll()) {
                System.out.println(buddyInfo.toString());
            }

            AddressBook addressBook2 = repository2.findById(1L);
            System.out.println(addressBook2.getAddressBook());


        };
    }
}
