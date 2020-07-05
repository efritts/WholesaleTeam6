README FIRST:

The application can be launched from src.GUI.DBGui.java

When the initial screen opens, there will be a "Login" button to the top right.  Click this to enter the same command-line prompts as from Mod5.  
!!!NOTE:  The currently stored login arguments are specific to my machine, so you'll have to use your own arguments for your version!!!

Clicking on any of the table names will bring up a query page.
Clicking "Search" with an empty block is the same as "Select * from ...", and will display the entire table.

Right now the search field is only set up to search for the values in column 1.

"Add" button:

Filling in the text boxes and clicking "Add" allows a user to add a new tuple to the database.  The newly added tuple will not be added if it fails to meet the primary key uniqueness constraint.

"Modify" button:

Clicking on any row will highlight that row, and populate the information in the text boxes to the right.  You can change that information, and clicking "Modify" will update the DB with the revised information.  Success depends on whether or not the modification meets primary and foreign key constraints.

"Delete" button:

Clicking on a row and pressing "Delete" will allow the user to delete that tuple from the database entirely.

Things that don't work yet:
-Query Builder

