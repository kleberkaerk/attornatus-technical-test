package com.attornatustechnicaltest.repository;

import com.attornatustechnicaltest.domain.Address;
import com.attornatustechnicaltest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByPerson(Person person);

    @Transactional
    @Modifying
    @Query("update Address a set a.isMain = :isMain where a.id = :id")
    void updateIsMainById(@Param("isMain") boolean isMain, @Param("id") Long id);

    Optional<Address> findByPersonAndId(Person person, Long id);

    @Transactional
    @Modifying
    @Query("update Address a set a.isMain = :isMain where a.person = :person and a.id = :id")
    void updateIsMainByPersonAndId(@Param("isMain") boolean isMain, @Param("person") Person person, @Param("id") Long id);
}
