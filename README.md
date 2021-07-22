# GoCity Task

### By Eduard Schlotter
I set myself 3 hours to complete this task so I wouldn't go overboard. 
There were a lot of other things I would've liked to add or fix but ran out of time unfortunately. 


##### How to run 
Simply build and run this project via maven or in an IDE then run the react project with npm start.

##### User Stories

* As a user, I want to see all products
* As a user, I want to be able to list the products in a UI

On the React front end you're easily able to see all the products and order them by columns.

* As a user, I want to be able to sort the products by Name

I've allowed users to both search by Name or order by clicking on the column name. 

* As a user, I want to be able to filter by Category

I've also added a filter for Categories which looks at all the unique values in the rows and sets each one as a value in the dropdown.

##### Extra things
I wrote a few tests at the beginning which were my goal towards completing this task. 
One of them didn't work because of lombok and jackson compatibility and if I didn't timebox myself I would have focused more on edge cases such as invalid values added by the user as a product.
I also would've wanted a more clear distinction between integration tests and unit tests.

I added the functionality of adding a new product on the backend but didn't on the front end. 
I would've wanted a simple button that then opens a modal with fields for the new product then have it sent to the backend. 

For modifying and deleting I would add respective buttons that send UPDATE and DELETE requests.

Pagination would've been done on the front end as there would'nt be too many products but for scale up I would use pagination from a DB, so requests payload wouldn't be as large.

I would also implement a real DB, I could've used JPA with H2 database to simulate this but it felt like overkill since there would only be 2 tables and not much data. 
2 lists would've done the job just as well since H2 is also in memory. 