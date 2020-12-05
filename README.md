# basic spring java book store

How to run
```
./gradlew clean build
./gradlew bootRun

```
- spring jpa
- spring web
- spring h2 database
- spring boot annotation
- spring stub
- postman automatic test

## Design user experience


> ![alt IMAGE](https://github.com/chainrocker55/spring-boot-basic-jpa/blob/966096-exam-book-store/process.png)

## Design database relation
> *Book Table*
-     int id;
      String bookName;
      Integer price;
      int numBook;
      
> *Basket Table*
-     int id;
      List<Book> books;
      Customer customer;
     
> *Customer Table*
-     int id;
      String firstname;
      String lastname;
      Basket basket;
      
> *Purchase Table*
-     int id;
      Basket basket;
      Double totalPrice;
      Double netPrice;
      Double discount;
      
## Design api service
> **Book Service**
* Get book by Id
    > GET /v1/book/{id}
* GET all book
    > GET /v1/books> 
                 
> **Basket Service**
* Build basket
    -   POST /v1/basket/build
        -   *Request model*
              ```
              {
                 customerId:id
              }
* Add book to basket
    -   PUT /v1/basket/add
        -   *Request model*
            ```
              {
                 basketId:id,
                 bookId:id    
              }                            
* GET basket by Id 
    > GET /v1/basket/{id}
                      >
> **Customer Service**                     
* GET customer by Id 
    > GET /v1/customer/{id}
  
> **Purchase Service**                     
* GET order by Id 
    > GET /v1/order/{id} 
* Create order
    -   POST /v1/order/create
    
        -   *Request model*
              ```
              {
                 basketId:id,
                 customerId:id 
              }            
 
## How to run correctly
1. please build basket before add book and find by id
2. please create order before find order id
3. book is already 5 type
4. Postman may not be correct because it is automatic generated


