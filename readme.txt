README FIRST:

The folder "sql/" contains the scripts for creating and populating the WholesaleTeam6 database.  In MySQL, running "wholesaler_team6_create.sql" followed by "wholesaler_team6_insert.sql" will build and populate the DB.  

PLEASE NOTE: Running these scripts will create a db named "Wholesaler"



Running the application:

1) Running from command line (Gradle)

Executing "gradle build" followed by "gradle run" on the command line should launch the application

2) Running from an IDE

Import the src folder in your IDE of choice

Add the JDBC connector jar in the "lib" folder as an external library in your IDE

The application can be launched from src.GUI.DBGui.java



When the initial screen opens, there will be a "Login" button to the top left.  Click this to enter the same command-line prompts as from Mod5.  

"URL": This is the location of your mysql db instance.  Example:  "jdbc:mysql://localhost:3306/Wholesaler?autoReconnect=true&useSSL=false&serverTimezone=America/Chicago"

"Username": DB username

"Password": DB password

"Driver": The specific mysql driver.  If using via gradle, the package is build using "mysql-connector-java-5.1.45-bin.jar".  


Clicking on any of the table names will bring up a query page.

Clicking "Search" with an empty block is the same as "Select * from ...", and will display the entire table.

Right now the search field is only set up to search for the values in column 1.

"Add" button:

Filling in the text boxes and clicking "Add" allows a user to add a new tuple to the database.  The newly added tuple will not be added if it fails to meet the primary key uniqueness constraint.

"Modify" button:

Clicking on any row will highlight that row, and populate the information in the text boxes to the right.  You can change that information, and clicking "Modify" will update the DB with the revised information.  Success depends on whether or not the modification meets primary and foreign key constraints.

"Delete" button:

Clicking on a row and pressing "Delete" will allow the user to delete that tuple from the database entirely.



