package com.example.finraproject.service;

import com.example.finraproject.model.Phones;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface PhoneNumberService {

    public List<Phones> saveAllNumbers(String number);

    public List<Phones> getPaginatedNumbers(Pageable pageable);

    public void deleteAll();

    public Integer lastPageNumber();
}
