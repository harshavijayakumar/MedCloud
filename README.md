# FoodHunterCM

NativeBites application is a platform which connects the people who are in search for home cooked food and the people who are willing
to sell their home cooked food. Total four use cases are described in the requirements document. 
They are 
1. User registration(Cater, Diner)
2. Update information 
3. Search information 
4. Order and Payments

All test cases are coded. Below are the actions that current code can perform. 
1. If it is first time user(cater/diner), register new link has to be clicked so that registration process can begin. 
2. One relevant information is created, register button is clicked. This will send a HTTP request to server so that data can be stored in table.
3. For subsequent times, cater/diner can use login screen to enter the Nativebites system. Login also sends a HTTP request to server so that data can be validated. 
4. The user validation is done while doing registration/login 
5. Once registration/login process is success, the user(Cater/Diner) will be redirected to menu page. The menu page shall have following optionswhere
   a. Search food
   b. Update information
   c. Post food(If the user is cater)
   d. About us information
   Once the user selects a food from the menu page, they will redirected to search results.
6. At this stage, the user can order the food by clicking Buy button 
7. Application will prompt the user to provide information about Google wallet. At present only Googe Wallet is kept as payment option.

Activities:
1. Login Activity - This handles login process 
2. Regiser Activity - This is for handling registration process 
3. Search Activity - This is for searching information from the database 
4. CaterUpload Activity - This is for uploading cater information to database 
5. DinerUpload Activity - This is for uploading diner information to database
6. Menu_CaterActivity - This activity is for handling menu options for cater user type
7. Menu_DinerActivity - This activity is for handling menu options for diner user type

The requests to server are also grouped under requests folder 
1. Login request - Sends username and password to the database and queries if they are correct 
2. Register request - Send username, password to the database 
3. CaterUpload request - Sends the cater information to the database 
4. DinerUpload request - Sends the diner information to the database
5. Search Request - Sends the search request to the database

At present following intents are used 
1. Intent for swithcing from login activity to register activity
2. Intent for switching from register activity to MenuCater activity 
3. Intent for switching from register activity to MenuDiner activity 
4. Intent for switching from login activity to MenuCater activity 
5. Intent for switching from login activity to MenuDiner activity 
6. Intent for switching from MenuCater activity to CaterUpload activity
7. Intent for switching from MenuDiner activity to DinerUpload activity
6. Intent for switching from CaterUpload activity to Search activity 
7. Intent for switching from DinerUpload activity to Search activity
8. Intent for switching from Search activity to Google payment activity

Two fragments are used 
1. SearchOptions fragment: This will display the search options for food types that are associated with the application. 
   This fragment runs in MenuCater/MenuDiner activity
2. AboutUs fragment: This will display information about the team involved in building the application. 
   This fragment runs in MenuCater/MenuDiner activity
   

Android Volley framework is used for communication with server

The server database shall have two tables 
1)user table:- This will contain all the information about users(cater/diner). 
  It has fields like username, password, type, location, contact 

2)food table:- This will be used to store food information. 
  It has fileds like price, location, username, food_type, food_description, imageloc, contact, status
  