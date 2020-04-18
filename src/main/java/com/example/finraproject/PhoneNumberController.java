package com.example.finraproject;

import com.example.finraproject.model.Phones;
import com.example.finraproject.service.PhoneNumberService;
import com.example.finraproject.vo.PhonesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService service;

    @GetMapping("/generate/{phoneNum}/{pageNum}/{pageSize}")
    //public ResponseEntity<List<Phones>>  getNumber(@PathVariable String phoneNum) {
    public ResponseEntity<PhonesVO>  getNumber(@PathVariable("phoneNum") String phoneNum, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        System.out.println("Hit me!");
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("id"));
        service.deleteAll();
        service.saveAllNumbers(phoneNum);
        List<Phones> output = service.getPaginatedNumbers(paging);

        PhonesVO vo = new PhonesVO();
        vo.setPhones(output);
        Integer total = service.lastPageNumber() ;
        vo.setTotal(total);
        vo.setLastPageNumber(total/ pageSize);

        return ResponseEntity.ok(vo);
    }
}
