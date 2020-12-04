# Exam java book store
## Design user experience
> [IMAGE] (./process.png)

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

