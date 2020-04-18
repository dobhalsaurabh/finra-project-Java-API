package com.example.finraproject.impl;

import com.example.finraproject.model.Phones;
import com.example.finraproject.repository.PhoneRespository;
import com.example.finraproject.service.PhoneNumberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "ABC");
        put("3", "DEF");
        put("4", "GHI");
        put("5", "JKL");
        put("6", "MNO");
        put("7", "PQRS");
        put("8", "TUV");
        put("9", "WXYZ");
        put("0", "0");
        put("1", "1");
    }};

    private List<Phones> output;

    @Autowired
    private PhoneRespository repo;


    @Override
    public List<Phones> saveAllNumbers(String number) {
        output = new ArrayList<>();
        if (number.length() != 0)
            backtrack("", number);
        System.out.println("Answer : " + output.size());

        repo.saveAll(output);

        return output;
    }

    @Transactional
    @Override
    public void deleteAll() {
        System.out.println("Delete started ");
        repo.deleteAllEntries();
        System.out.println("Delete Completed");
    }


    private void backtrack(String combination, String next_digits) {
        // if there is no more digits to check

        if (next_digits.length() == 0) {
            Phones p = new Phones();
            p.setPhoneNumber(combination);
            output.add(p);


        }
        else {
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // output.add( letter + next_digits.substring(1));
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }


    public List<Phones> getPaginatedNumbers(Pageable pageable) {

        Page<Phones> pagedResult = repo.findAll(pageable);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Phones>();
        }
    }

    public Integer lastPageNumber() {
        List<Phones> p = (List<Phones>) repo.findAll();
        return p.size();
    }

}
