package org.example;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * SYSC 4806 Lab 2, Fall 2023
 * Class for defining an AddressBook, an ArrayList of BuddyInfo objects.
 * @author Max Curkovic, 101139937
 */

@Entity
public class AddressBook {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = BuddyInfo.class)
    private List<BuddyInfo> buddy;
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Default constructor for AddressBook that initializes the ArrayList.
     */
    public AddressBook(){
        this.buddy = new ArrayList<>();
    }

    /**
     * Method for adding a new BuddyInfo object to the AddressBook.
     * @param newBud A BuddyInfo object.
     */
    public void addBuddy(BuddyInfo newBud){
        buddy.add(newBud);
    }

    /**
     * Method for removing a BuddyInfo object from the AddressBook.
     * @param newBud A BuddyInfo object.
     */
    public boolean removeBuddy(BuddyInfo newBud){
        return buddy.remove(newBud);
    }

    /**
     * Getter method for the AddressBook.
     * @return An AddressBook object.
     */
    public List<BuddyInfo> getAddressBook() {
        return this.buddy;
    }

    /**
     * Method for printing the AddressBook into a readable format.
     */
    public void printAddressBook() {
        System.out.println(buddy);
    }


    public void removeBuddy(int i) {
        BuddyInfo b = this.buddy.remove(i);
        b.setAddressBook(null);
    }

    /**
     * Main method.
     * @param args Not necessary to use in this lab.
     */
    public static void main(String[] args) {
        BuddyInfo buddyA = new BuddyInfo("Max", "123 Abba St", "12345");
        BuddyInfo buddyB = new BuddyInfo("Tony", "456 Abba St", "12346");
        BuddyInfo buddyC = new BuddyInfo("Bardia", "789 Abba St", "12347");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddyA);
        addressBook.addBuddy(buddyB);
        addressBook.addBuddy(buddyC);
        addressBook.printAddressBook();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}