package org.example;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * SYSC 4806 Lab 3, Fall 2023
 * Interface that defines a BuddyInfo repository as used by the Spring framework.
 * @author Max Curkovic, 101139937
 */
@Repository
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long>{
    List<BuddyInfo> findByName(String name);

    BuddyInfo findById(long id);
}
