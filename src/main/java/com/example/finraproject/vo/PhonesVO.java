package com.example.finraproject.vo;

import com.example.finraproject.model.Phones;
import lombok.Data;

import java.util.List;

@Data
public class PhonesVO {
    private List<Phones> phones;
    private Integer currentPage;
    private Integer LastPageNumber;
    private Integer total;

}
