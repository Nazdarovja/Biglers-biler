# CAR RENTAL API

## SETUP INSTRUCTIONS


- Create database manually, tables are created automatically
- Change Database properties in the persistence.xml

```
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/carrentaldb"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="qqqqqq"/>
```


## APIs


. | HTTP Method | API URL | Description 
--- | --- | --- | ---
1 | GET | <host>/car-rental-api/api/cars | returns all cars also rented
2 | GET | <host>/car-rental-api/api/cars/xxxx  | returns a specific cars with regno=xxxx 
3 | GET | <host>/car-rental-api/api/cars?location=xxxx  |returns only cars vacant from specific location 
4 | GET | <host>/car-rental-api/api/cars?category=xxxx  |returns only cars vacant within that category 
5 | GET | <host>/car-rental-api/api/cars?start=xxxx&end=xxxx  |returns only cars vacant in this period 
6 | GET | <host>/car-rental-api/api/cars?location=xxxx&start=xxxx&end=xxxx  |returns only cars vacant from specific location
7 | GET | <host>/car-rental-api/api/cars?category=xxxx&start=xxxx&end=xxxx  |returns only cars vacant within that category
8 | GET | <host>/car-rental-api/api/cars?location=xxxx&category=xxxx&start=xxxx&end=xxxx  |returns only cars vacant within that category on a specific location 
9 | PUT | <host>/car-rental-api/api/cars/<regno> | Create the new reservation









