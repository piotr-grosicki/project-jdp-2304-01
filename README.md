# ECOMMERCE PROJECT
### Description
Hello and welcome to Ecommerce project. The project was designed to create 
an online shop backend application. The application uses REST API endpoints, 
that allows for handling Products, Product Groups, Users, Carts and Orders, 
using specific endpoints as seen below. 

### Getting started
1. Clone repository
2. Create DB with credentials as seen in `application.properties`
3. Build and run

### Endpoint description
The list below contains all available application endpoints. For further 
details navigate to swagger documentation here: 
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

- Products
  - list all products
  - get specific product
  - create new product
  - update product
  - delete product
- Groups
  - list all groups
  - get specific group
  - create new group
  - update group
- Carts
  - get specific cart
  - create cart for specific user
  - add product to cart
  - delete product from cart
  - create new order from cart
- Orders
  - get all orders
  - get specific order
  - create new order
  - update order
  - delete order
- Users
  - create new user
  - block user
  - generate key for specific user

### Credits
This project was created by four developers:
- [jak-hry](https://github.com/jak-hry)
- [RobiBobii](https://github.com/RobiBobii)
- [Mateusz-Surmac](https://github.com/Mateusz-Surmac)
- [QxaM](https://github.com/QxaM)