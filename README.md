# springboot_assignment

1. Configure the Spring Boot using annotations and Java config.Use Java 8 - DONE

2. Take a fairly complicated database schema.Create entities showcasing your skillet on Hibernate 
(Try to cover all kinds of relation mapping and most common annotations) 
https://www.researchgate.net/figure/Entity-relationship-diagram-ERD-for-a-simple-library-management-system-Entities-are_fig2_258388421

   - Unidirection 1-1,1-M,M-M-M
   - Bidirection  1-1,1-M,M-M-M
   - FetchType and Cascade and orpahan removal
   -http://www.java2s.com/Tutorials/Java/JPA/0300__JPA_ElementCollection_String_Map.htm
   
 DONE.

3. Configure the Datasource , connection pool ,transaction management,Hibernate.
   DONE. (If possible do manual configuration if time permits for all things)

4. Configure spring JPA repositories.And showcase your skillset in building the queries using JPQL and the method signatures.
         
		  add repository methods
		  write JPQL to fetch User payments.

     Almost DONE


5. Implement search API with pagination, filter, sort features on top of multiple entities.
     
	 Use Pagination with search


6. Use JPA projections in one or more services. - DONE
     
	 write get all for property API with user address and all
	 
	 1. Interface-Based Projections
	 
	     Closed Projections
		 
		 @Value("#{target.firstName + ' ' + target.lastName}")
		 Open Projections
	 
	 2. Class-Based Projections DTO
	 3.Dynamic Projections
	 


7. Show case your skills in properly handling the transactions using the appropriate propagation, isolation modes.
          1. Develop booking API 
                   
				  
                 Frontend booking				  
				  
				   1. Check If booking available   
				   
				   2. Book Rental
				   
				   3. Complete Payment
				   
				   4. Send email / SMS
				   
				  Backoofice booking
				   
				     payment 
				   

8. Configure the Spring Web MVC using the best practices. ViewResolvers,MessageConvertors,Filters,Interceptors etc
    DONE.

9. Create REST API which can respond with either JSON or XML, based on the requests.
    DONE ISO for JSON format


10. Get Data from a REST API and display on a JSP page using XHR (Let say the search API created above has a limitation of max 5 results for each http request but the total results are 50. And the requirement is to display all the 50 records on the UI with single http request from UI.Implement the REST API that call the search API internally asynchronously and aggregate the results from all the calls and send 50 results in the response)


11. Configure Spring Security and use the best practices to secure your application and the REST API.
    DONE







					   
