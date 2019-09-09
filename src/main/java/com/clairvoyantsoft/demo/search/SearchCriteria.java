package com.clairvoyantsoft.demo.search;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SearchCriteria {

    private String entity;

    List<Map<String,Object>> search;

}
