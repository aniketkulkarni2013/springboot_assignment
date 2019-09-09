package com.clairvoyantsoft.demo.search;


import com.clairvoyantsoft.demo.repository.BookingRepository;
import com.clairvoyantsoft.demo.repository.PaymentRepository;
import com.clairvoyantsoft.demo.repository.PropertyRepository;
import com.clairvoyantsoft.demo.repository.UserRepository;
import com.clairvoyantsoft.demo.service.BookingService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenericSearchComponent {

    private final Map<String, QuerydslPredicateExecutor> repositoryMap = new HashMap<>();

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final PropertyRepository propertyRepository;

    private final PaymentRepository paymentRepository;

    private final BookingService bookingService;

    public GenericSearchComponent(UserRepository userRepository,
                      BookingRepository bookingRepository,
                      PropertyRepository propertyRepository,
                      PaymentRepository paymentRepository,
                      BookingService bookingService){
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.paymentRepository = paymentRepository;
        this.bookingService = bookingService;
    }
    public Predicate buildSearchPredicate(SearchCriteria searchCriteria) {
        BooleanExpression wherePredicate = Expressions.asBoolean(false).isFalse();
        try {
            Class<?> entityClass = Class.forName("com.clairvoyantsoft.demo.domain."+searchCriteria.getEntity());
            PathBuilder<?> entityPath = new PathBuilder(entityClass, searchCriteria.getEntity().toLowerCase());
            List<Predicate> predicates = new ArrayList<>();

            searchCriteria.getSearch().stream().forEach(search -> {

                SearchOperation operation = SearchOperation.valueOf(search.get("searchOperation").toString());

                search.entrySet().forEach(entryset -> {
                    if (!entryset.getKey().equalsIgnoreCase("searchOperation")) {
                        predicates.add(this.getPredicate(operation, entityPath, entryset.getKey().toString(), entryset.getValue()));
                    }
                });
            });

            for (Predicate predicate : predicates) {
                wherePredicate = wherePredicate.and(predicate);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return wherePredicate;
    }


    private Predicate getPredicate(SearchOperation searchOperation, PathBuilder builder, String property, Object value) {

        switch (searchOperation) {
            case EQUAL:
                if (value instanceof String)
                    return builder.get(property, String.class).eq(value.toString());
                if (value instanceof Number)
                    return builder.get(property, Number.class).eq(value);
                if (value instanceof Boolean)
                    return builder.get(property, Boolean.class).eq(value);

                break;
            case NOT_EQUAL:
                if (value instanceof String)
                    return builder.get(property, String.class).ne(value.toString());
                if (value instanceof Number)
                    return builder.get(property, Number.class).ne(value);
                if (value instanceof Boolean)
                    return builder.get(property, Boolean.class).ne(value);

                break;
            case LESS_THAN:
                if (value instanceof Number) {
                    try {
                        return builder.getNumber(property, Number.class).lt(NumberFormat.getInstance().parse(value.toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case GREATER_THAN:
                if (value instanceof Number) {
                    try {
                        return builder.getNumber(property, Number.class).gt(NumberFormat.getInstance().parse(value.toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case START_WITH:
                if (value instanceof String)
                    return builder.getString(property).startsWith(value.toString());
                break;
            case END_WITH:
                if (value instanceof String)
                    return builder.getString(property).endsWith(value.toString());
                break;
            case LIKE:
                if (value instanceof String)
                    return builder.getString(property).like(value.toString());
                break;
            default:
                return null;
        }

        return null;
    }

    public Iterable<Object> search(SearchCriteria searchCriteria, Pageable pageable){

        Predicate predicate = this.buildSearchPredicate(searchCriteria);

        return repositoryMap.get(searchCriteria.getEntity()).findAll(predicate,pageable);
     }

     @PostConstruct
     private void  consructRepositoryMap(){

        repositoryMap.put("User",userRepository);
        repositoryMap.put("Booking",bookingRepository);
        repositoryMap.put("Payment",paymentRepository);
        repositoryMap.put("Property",propertyRepository);
     }

}
