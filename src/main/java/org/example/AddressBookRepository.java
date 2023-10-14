package org.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SYSC 4806 Lab 3, Fall 2023
 * Interface that defines an AddressBook repository as used by the Spring framework.
 * Max Curkovic, 101139937
 */
@Repository
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    AddressBook findById(long id);

    List<AddressBook> findAll();
}
