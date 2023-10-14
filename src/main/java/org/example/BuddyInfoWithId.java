package org.example;

import lombok.*;

/**
 * SYSC 4806 Lab 4, Fall 2023
 * Class that is used to contain a new BuddyInfo for persistence purposes, along with its AddressBook ID.
 * @author Max Curkovic, 101139937
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuddyInfoWithId{
    private Long addressBookId;
    private BuddyInfo buddy;
    private Long buddyId;

    public BuddyInfoWithId(Long addressBookId){
        this.addressBookId = addressBookId;
        this.buddy = null;
        this.buddyId = null;
    }

    public BuddyInfoWithId(Long addressBookId, BuddyInfo buddy){
        this(addressBookId);
        this.buddy = buddy;
        this.buddyId = buddy.getId();
    }
}