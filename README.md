# DVDRentalClient

This is a Movie Rental System for a local movie store. It consists of two sides, client and server running as seperate projects  
This part of th project is one part of a Movie rental System (DVDRentalClient + DVDRentalServer = Movie Rental System). This is the 
client side of the project where the user interacts with the server.

====================================================================================================================
################ You need to Open both RentalServer and Rental Client as seperate Intellij projects ################
====================================================================================================================

First things first:
*In order to execute this project, you first have to run the server (Run on the 'Server' file)
*"waiting for client" will be displayed by the console
*The data in the tables is read from "Customers", "Rentals" and "Movies" SER files

*Now run the client (run on the 'RunClient')
*The server will listesn for connection from the client then accept (hand shake) the connection.
*All set to go

How to use:
*From here, you can create, update and delete both movie and customer. You can also sort sort each column in ascending or descending 
 order in both tables (customer and movie)   
*The + and - buttons are to add a customer but you can open also add both customers and movies by pressing file and select your option
*You can also search by providing the name of customer and movie by typing on respective text box

Adding Customers and Movies:
*Click the + and - buttons are to add a customer
*On the menu bar, click "add" the select your choice then provide the reuired textboxes
*All data is stored in Access Database residing in the server 

Deleting Movies and Customers:
*Select the customer you want to delete then press on the - button
*On the menu bar, click on customer then make your selection

Hiring:
*To hire, you have to click on a customer with enough credit and select on an available movie then punch hire. This will debit the users
 credit and mark the movie unavailable for hire.
*You can also check for rented movies by clicking 'Screen' on menu bar -> 'Rented'. 
*You can filter the movies by selecting your prefered genre by clicking on the "All" dropdown.

Returning a movie:
*Go to rented movies by clicking 'Screen' on menu bar -> 'Rented'
*Click on the movie to be returned then click on 'return'
