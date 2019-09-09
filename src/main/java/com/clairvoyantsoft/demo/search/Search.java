package com.clairvoyantsoft.demo.search;

import lombok.Data;

@Data
public class Search {

    private String field;

    private Object value;

    private SearchOperation operation;
}
