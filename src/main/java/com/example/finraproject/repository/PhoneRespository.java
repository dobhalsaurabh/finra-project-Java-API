package com.example.finraproject.repository;

import com.example.finraproject.model.Phones;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRespository extends PagingAndSortingRepository<Phones, Long> {

    @Modifying
    @Query(value = "DELETE FROM Phones", nativeQuery = true)
    public void deleteAllEntries();
}
