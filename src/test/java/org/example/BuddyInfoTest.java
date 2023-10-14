package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * SYSC 4806 Lab 2, Fall 2023
 * JUnit test class for BuddyInfo.
 * @author Max Curkovic, 101139937
 */

public class BuddyInfoTest {

    BuddyInfo test = new BuddyInfo("TestName", "TestAddress", "TestPhoneNumber");

    @Test
    public void getName() {
        assertEquals(test.getName(), "TestName");
    }

    @Test
    public void getAddress() {
        assertEquals(test.getAddress(), "TestAddress");
    }

    @Test
    public void getPhoneNumber() {
        assertEquals(test.getPhoneNumber(), "TestPhoneNumber");
    }
}