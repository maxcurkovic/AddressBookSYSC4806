package org.example;

import org.junit.Test;

import java.util.List;

import javax.persistence.*;


/**
 * SYSC 4806 Lab 2, Fall 2023
 * Class for testing ORM persistence of AddressBook and BuddyInfo, using JPA.
 * @author Max Curkovic, 101139937
 */

public class JPATest {

    /**
     * Test method for performing JPA on BuddyInfo and AddressBook objects.
     */
    @Test
    public void performJPA() {
        // Creating objects representing some products

        BuddyInfo buddy1 = new BuddyInfo("Max", "123 Engineering Street", "123");


        BuddyInfo buddy2 = new BuddyInfo("Bardia", "456 Software Road", "456");


        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);


        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Persisting the product entity objects
        em.persist(buddy1);
        em.persist(buddy2);
        em.persist(addressBook);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q1 = em.createQuery("SELECT b FROM BuddyInfo b");
        Query q2 = em.createQuery("SELECT a FROM AddressBook a");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> results = q1.getResultList();
        List<AddressBook> results2 = q2.getResultList();

        System.out.println("List of BuddyInfos\n----------------");

        for (BuddyInfo b : results) {

            System.out.println(b.getName() + "," + b.getAddress() + "," + b.getPhoneNumber() + " (id=" + b.getId() + ")");
        }

        System.out.println("List of AddressBooks\n----------------");

        for (AddressBook a: results2) {
            System.out.println(addressBook.getAddressBook());
        }

        // Closing connection
        em.close();

        emf.close();
    }
}