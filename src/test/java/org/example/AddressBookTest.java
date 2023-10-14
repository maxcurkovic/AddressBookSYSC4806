package org.example;

import static org.junit.Assert.*;

/**
 * SYSC 4806 Lab 2, Fall 2023
 * JUnit test class for AddressBook.
 * @author Max Curkovic, 101139937
 */

public class AddressBookTest {

    @org.junit.Test
    public void addBuddy() {
        BuddyInfo test = new BuddyInfo("Test", "Test", "Test");
        AddressBook testBook = new AddressBook();
        testBook.addBuddy(test);
        assertTrue(testBook.getAddressBook().contains(test));
    }

    @org.junit.Test
    public void removeBuddy() {
        BuddyInfo test = new BuddyInfo("Test", "Test", "Test");
        AddressBook testBook = new AddressBook();
        testBook.addBuddy(test);
        testBook.removeBuddy(test);
        assertFalse(testBook.getAddressBook().contains(test));
    }

}