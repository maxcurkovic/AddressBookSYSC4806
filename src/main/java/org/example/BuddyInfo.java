package org.example;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * SYSC 4806 Lab 2, Fall 2023
 * Class for defining a BuddyInfo, which is defined by a name, address and phone number (all string values).
 * @author Max Curkovic, 101139937
 */

@Entity
public class BuddyInfo {

    @Getter
    @Id
    @GeneratedValue
    private Long id;
    /**
     * -- GETTER --
     *  Getter method for the BuddyInfo name.
     *
     * @return A String name.
     */
    @Getter
    private String name;
    /**
     * -- GETTER --
     *  Getter method for the BuddyInfo address.
     *
     * @return A String address.
     */
    @Getter
    private String address;
    /**
     * -- GETTER --
     *  Getter method for the BuddyInfo phoneNumber.
     *
     * @return A String phoneNumber.
     */
    @Getter
    private String phoneNumber;
    @ManyToOne
    private AddressBook addressBook;

    /**
     * Constructor for a BuddyInfo object.
     * @param name A String name.
     * @param address A String address.
     * @param phoneNumber A String phoneNumber.
     */
    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo() {
        this.name = "Hello";
        this.address = "Hello";
        this.phoneNumber = "Hello";
    }


    public void setId(Long id){
        this.id = id;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Overriden toString method to convert BuddyInfo to a readable String format.
     * @return A String form of BuddyInfo.
     */
    @Override
    public String toString() {
        return
                " Name: '" + name + '\'' +
                        ", Address: '" + address + '\'' +
                        ", Phone Number: '" + phoneNumber + '\'';
    }
}