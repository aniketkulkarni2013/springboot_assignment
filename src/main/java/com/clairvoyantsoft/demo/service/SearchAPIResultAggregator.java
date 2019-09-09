package com.clairvoyantsoft.demo.service;

import com.clairvoyantsoft.demo.search.SearchCriteria;
import com.clairvoyantsoft.demo.search.SearchOperation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class SearchAPIResultAggregator {

    RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<List<Object>> getSearchResult(int pageNumber, int pageSize, SearchCriteria criteria) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0bG9naW4iLCJhdXRoIjoiQURNSU4sVVNFUiIsImV4cCI6MTU2ODA1NjA0OX0.LaiSvf9RQ297mMsXmH79dQf2KqeUtVv4OmbDXwsHSXgLySiKrUs49SHOgET2ddzERxiDr1hZ5I4dWeukqirbNA");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SearchCriteria> request = new HttpEntity<SearchCriteria>(criteria, headers);
        String url = "http://localhost:8080/api/generic/search?page="+pageNumber+"&size="+pageSize+"&sort=bookingStatus";
        Object[] searchResultAsJsonStr = restTemplate.postForObject(url, request,Object[].class);


        return CompletableFuture.completedFuture(Arrays.asList(searchResultAsJsonStr));
    }

    public List<Object> getResult(){

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setEntity("Booking");
        Map<String,Object> search = new HashMap<>();
        search.put("searchOperation","GREATER_THAN");
        search.put("bookingStatus","COMPLETED");
        searchCriteria.getSearch().add(search);
        List<CompletableFuture<List<Object>>> listCompletableFuture = new ArrayList<>();

        for(int pageNumber=0;pageNumber<10;pageNumber++) {
            listCompletableFuture.add(this.getSearchResult(pageNumber,5,searchCriteria));
        }

        CompletableFuture.allOf(listCompletableFuture.toArray(new CompletableFuture<?>[0])).join();

        List<Object> result = new ArrayList<>();
        for (CompletableFuture<List<Object>> completableFuture : listCompletableFuture) {
            List<Object> objects = null;
            try {
                objects = completableFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            result.addAll(objects);
        }

        System.out.println(result);
        return result;
    }
}
